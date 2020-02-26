package vista.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostzone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Controller.AlumnosSWHilo;
import modelo.Alumno;

public class WebService extends AppCompatActivity implements View.OnClickListener {

    EditText id_A, name, direction;
    Button add, enlist, delete, modify, search;
    RecyclerView recycler;
    TextView dates;
    SWadapter adapter;
    List<Alumno> alumnos;
    List<Alumno> obtenido;

    String host = "http://reneguaman.000webhostapp.com";
    String insert = "/insertar_alumno.php";
    String get = "/obtener_alumnos.php";
    String getbyID = "/obtener_alumno_por_id.php";
    String update = "/actualizar_alumno.php";
    String borrar = "/borrar_alumno.php";

    AlumnosSWHilo sw;

    String datooos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        cargarComponentes();
    }

    private void cargarComponentes(){
        id_A = findViewById(R.id.txt_idAlumno);
        name = findViewById(R.id.txt_NombreAlumno);
        direction = findViewById(R.id.txt_DireccionALumno);
        add = findViewById(R.id.btn_anadirA);
        enlist = findViewById(R.id.btn_ListarA);
        delete = findViewById(R.id.btn_EliminarAllA);
        modify = findViewById(R.id.btn_ModificarA);
        search = findViewById(R.id.btn_BuscarAid);
        recycler = findViewById(R.id.recycler_alumnos);
        dates = findViewById(R.id.lbl_datesA);
        add.setOnClickListener(this);
        enlist.setOnClickListener(this);
        delete.setOnClickListener(this);
        modify.setOnClickListener(this);
        search.setOnClickListener(this);

    }

    private void cargarRecy(List<Alumno> liste){
        adapter = new SWadapter(liste);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {

        sw = new AlumnosSWHilo();

        switch (v.getId()){

            case R.id.btn_ListarA:
                 //ejecuta el hilo de tu vieja en el background
                //String datinos = dates.getText().toString();
                String datinos = null;
                try {
                    datinos = sw.execute(host.concat(get), "1").get();
                } catch (ExecutionException e) {
                    Toast.makeText(this, "No se pudo ejectar", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "No se pudo obtener string", Toast.LENGTH_SHORT).show();
                }
                alumnos = new ArrayList<>();
                try{
                    if(!datinos.equals("")){
                        try {
                            JSONObject json = new JSONObject(datinos);
                            String estadoJson = json.getString("estado");
                            if (estadoJson.equals("1")){
                                JSONArray jsonArray = json.optJSONArray("alumnos");
                                for (int i = 0; i < jsonArray.length(); i++){
                                    alumnos.add(new Alumno(jsonArray.getJSONObject(i)));
                                }
                                cargarRecy(alumnos);
                            }else {
                                Toast.makeText(this, "No hay ningun puerco", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException ex){
                            ex.printStackTrace();
                            Log.e("Error con JSON", "No se pudo convertir en YEISON");
                        }
                    } else {
                        Toast.makeText(this, "NO se pudo obtener datos, intentalo de vuelta", Toast.LENGTH_SHORT).show();
                    }
                } catch (NullPointerException ex){
                    Toast.makeText(this, "NO se pudo obtener datos, intentalo de vuelta", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_anadirA:
                //String ida = id_A.getText().toString();
                String neme = name.getText().toString();
                String dire =  direction.getText().toString();
                if (!neme.equals("") && !dire.equals("")){
                    try {
                        Toast.makeText(this, sw.execute(host.concat(insert), "2", neme, dire).get(), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        Toast.makeText(this, "No se pudo ejectar", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "No se pudo obtener string", Toast.LENGTH_SHORT).show();
                    }
                    name.setText(null);
                    direction.setText(null);
                    id_A.setText(null);
                } else {
                    Toast.makeText(this, "DATOS, PUERCO", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_BuscarAid:
                String ida = id_A.getText().toString();
                if (!ida.equals("")){
                    String ure = host + getbyID + "?idalumno=" + ida;
                    String puerco = null;
                    try {
                        puerco = sw.execute(ure, "3").get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "No se pudo ejectar", Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "No se pudo obtener string", Toast.LENGTH_SHORT).show();
                    }
                    if (!puerco.equals("")){
                        try {
                            String[] atributos = puerco.split(",");
                            obtenido = new ArrayList<>();
                            Alumno l = new Alumno(atributos[0], atributos[1], atributos[2]);
                            obtenido.add(l);
                            cargarRecy(obtenido);
                            Toast.makeText(this, "Yalasa", Toast.LENGTH_SHORT).show();
                        } catch (ArrayIndexOutOfBoundsException e){
                            Log.e("Mal index", "NO existe esa posicion");
                            Toast.makeText(this, "Ese puerco no existe mi hermano", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(this, "DATOS, PUERCO", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_ModificarA:
                String idalumnox = id_A.getText().toString();
                String nemealu = name.getText().toString();
                String direalu =  direction.getText().toString();
                if (nemealu.equals("") || direalu.equals("") || idalumnox.equals("")){
                    Toast.makeText(this, "METE DATOS, PUERCO", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Toast.makeText(this, sw.execute(host.concat(update), "5", idalumnox, nemealu, direalu).get(), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "No se pudo ejecutar", Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "NO se pudo obtener string", Toast.LENGTH_SHORT).show();
                    }
                    name.setText(null);
                    direction.setText(null);
                    id_A.setText(null);
                }
                break;
            case R.id.btn_EliminarAllA:
                String idal = id_A.getText().toString();
                if (!idal.equals("")){
                    try {
                        Toast.makeText(this, sw.execute(host.concat(borrar), "4", idal).get(), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "No se pudo ejecutar", Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "NO se pudo obtener string", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "DATOS, PUERCO", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
