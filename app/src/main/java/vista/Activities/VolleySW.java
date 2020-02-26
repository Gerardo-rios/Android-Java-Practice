package vista.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lostzone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Controller.ServicioWebAlumnosVolley;
import modelo.Alumno;

public class VolleySW extends AppCompatActivity implements View.OnClickListener {

    EditText id_A, name, direction;
    Button add, enlist, delete, modify, search;
    RecyclerView recycler;
    SWadapter adapter;
    List<Alumno> alumnos;
    List<Alumno> obtenido;

    ServicioWebAlumnosVolley sw = new ServicioWebAlumnosVolley(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_sw);
        cargarComponentes();
    }

    private void cargarComponentes(){
        id_A = findViewById(R.id.txt_idalumnoVolley);
        name = findViewById(R.id.txt_NombreAlumnoVolley);
        direction = findViewById(R.id.txt_DireccionALumnoVolley);
        add = findViewById(R.id.btn_anadirAVolley);
        enlist = findViewById(R.id.btn_ListarAVolley);
        delete = findViewById(R.id.btn_EliminarAVolley);
        modify = findViewById(R.id.btn_ModificarAVolley);
        search = findViewById(R.id.btn_BuscarAidVolley);
        recycler = findViewById(R.id.recycler_alumnosVolley);
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

    private String anadirA(String nombre, String direction){
        Alumno a = new Alumno();
        a.setNombre(nombre);
        a.setDireccion(direction);
        if (sw.Insertar(a)){
            return "INSERTADO CON EXITO";
        } else {
            return "NO SE PUDO INSERTAR";
        }
    }

    private String modificarA(String ide, String nombre, String direction){
        if (sw.Modificar(ide, nombre, direction)){
            return "MODIFICADO CON EXITO";
        } else {
            return "NO SE PUDO MODIFICAR";
        }
    }

    private void buscarporid(){
        try {
            String cajaid = id_A.getText().toString();
            obtenido = new ArrayList<>();
            if (!cajaid.equals("")){
                JSONObject yeis = sw.ObtenerAlumno(cajaid);
                try {
                    String stadu = yeis.getString("estado");
                    if (stadu.equals("1")){
                        Alumno ax = new Alumno(yeis.getJSONObject("alumno").getString("idAlumno"), yeis.getJSONObject("alumno").getString("nombre"), yeis.getJSONObject("alumno").getString("direccion"));
                        obtenido.add(ax);
                    } else {
                        Toast.makeText(this, "No existe ese puerco", Toast.LENGTH_SHORT).show();
                    }
                    cargarRecy(obtenido);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "No se pudo obtener JSON", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Ingresa datos, maldito PUERCO!", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException ex){
            ex.printStackTrace();
            Toast.makeText(this, "No se pudo Obtener Datos, Intenta denuevo", Toast.LENGTH_SHORT).show();
        }
    }

    private void listartodos(){
        String hola = sw.Listar();
        try{
            if (hola.equals("")){
                Toast.makeText(this, "No se pudo Obtener Datos, Intenta denuevo", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject jeison = new JSONObject(hola);
                    String status = jeison.getString("estado");
                    alumnos = new ArrayList<>();
                    if (status.equals("1")){
                        JSONArray jsonArray = jeison.optJSONArray("alumnos");
                        for (int i = 0; i < jsonArray.length(); i++){
                            alumnos.add(new Alumno(jsonArray.getJSONObject(i)));
                        }
                        cargarRecy(alumnos);
                    } else {
                        Toast.makeText(this, "No existe ningun puerco", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "No se pudo Obtener JSON", Toast.LENGTH_SHORT).show();
                }
            }

        }catch (NullPointerException ex){
            ex.getMessage();
            Toast.makeText(this, "No se pudo Obtener Datos, Intenta denuevo", Toast.LENGTH_SHORT).show();
        }
    }

    private String eliminarA(String ide){
        if (sw.Eliminar(ide)){
            return "ELIMINADA CON EXITO";
        } else {
            return "NO SE PUDO ELIMINAR";
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_ListarAVolley:
                listartodos();
                break;
            case R.id.btn_anadirAVolley:
                String n = name.getText().toString();
                String d = direction.getText().toString();
                if (n.equals("") || d.equals("")){
                    Toast.makeText(this, "Ingresa datos, maldito PUERCO!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, anadirA(n,d), Toast.LENGTH_SHORT).show();
                }
                name.setText(null);
                direction.setText(null);
                break;
            case R.id.btn_ModificarAVolley:
                String identi = id_A.getText().toString();
                String ne = name.getText().toString();
                String de = direction.getText().toString();
                if (ne.equals("") || de.equals("") || identi.equals("")){
                    Toast.makeText(this, "Ingresa datos, maldito PUERCO!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, modificarA(identi, ne,de), Toast.LENGTH_SHORT).show();
                }
                id_A.setText(null);
                name.setText(null);
                direction.setText(null);
                break;
            case R.id.btn_BuscarAidVolley:
                buscarporid();
                break;
            case R.id.btn_EliminarAVolley:
                String identificator = id_A.getText().toString();
                if (identificator.equals("")){
                    Toast.makeText(this, "Ingresa datos, maldito PUERCO!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, eliminarA(identificator), Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
