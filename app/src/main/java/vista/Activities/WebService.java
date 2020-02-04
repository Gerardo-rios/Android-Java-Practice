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
import java.net.URLConnection;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import modelo.Alumno;

public class WebService extends AppCompatActivity implements View.OnClickListener {

    EditText id_A, name, direction;
    Button add, enlist, delete, modify, search;
    RecyclerView recycler;
    TextView dates;
    SWadapter adapter;
    List<Alumno> alumnos = new ArrayList<>();
    List<Alumno> obtenido;

    String host = "http://reneguaman.000webhostapp.com";
    String insert = "/insertar_alumno.php";
    String get = "/obtener_alumnos.php";
    String getbyID = "/obtener_alumno_por_id.php";
    String update = "/actualizar_alumno.php";
    String borrar = "/borrar_alumno.php";

    ServicioWeb sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        cargarComponentes();
    }

    //Accediendo al servicio web mediante un hilo, de tu vieja :)

    class ServicioWeb extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... parameters) {
            String consulta = "";
            URL url;
            String ruta = parameters[0];
            String alumne = "";
            String respuesta = "";
            if (parameters[1].equals("1")){
                try {
                    url = new URL(ruta);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int codigoResponse = connection.getResponseCode();
                    if (codigoResponse == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader(in));
                        consulta += lector.readLine();
                        //Log.e("datos", consulta);
                        JSONObject json = new JSONObject(consulta);
                        String estadoJson = json.getString("estado");
                        if (estadoJson.equals("1")){
                            JSONArray jsonArray = json.optJSONArray("alumnos");
                            for (int i = 0; i < jsonArray.length(); i++){
                                alumnos.add(new Alumno(jsonArray.getJSONObject(i)));
                            }
                        }else {
                            Log.e("NO hay", "No hay ningun puerco") ;
                        }

                    } else {
                        Log.e("Errorsisimo", "NO hubo codigo de respuesta ok");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("Error", "NO se encontro la ruta");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Malardo", "NO se pudo hacer coneccion");
                }catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("MalardoJSON", "NO se pudo hacer el objetojson");
                }

            } else if (parameters[1].equals("2")){
                //Log.e("entro", "entrar");
                try {
                    url = new URL(ruta);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.connect();

                    JSONObject json = new JSONObject();

                    //json.put("idalumno", parameters[2]);
                    json.put("nombre", parameters[2]);
                    json.put("direccion", parameters[3]);
                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(json.toString());
                    writer.flush();
                    writer.close();
                    int response = connection.getResponseCode();
                    if (response == HttpURLConnection.HTTP_OK){
                        Log.e("estaok", "OK");
                        respuesta = "Guardado manito";
                    } else {
                        Log.e("estamalardo", "malardo no ok");
                        respuesta = "Fuentes, no se guardo";
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("Error", "NO se encontro la ruta");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Malardo", "NO se pudo hacer coneccion");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("MalardoJSON", "NO se pudo hacer putJSON");
                }

                return respuesta;

            } else if (parameters[1].equals("3")){

                try {
                    url = new URL(ruta);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int codigoResponse = connection.getResponseCode();
                    if (codigoResponse == HttpURLConnection.HTTP_OK ){
                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        consulta += reader.readLine();
                        JSONObject yeison = new JSONObject(consulta);
                        String resultYeison = yeison.getString("estado");

                        if (resultYeison.equals("1")){

                            alumne = yeison.getJSONObject("alumno").getString("idAlumno") + "," +
                                    yeison.getJSONObject("alumno").getString("nombre") + "," +
                                    yeison.getJSONObject("alumno").getString("direccion");

                        }else {
                            Log.e("NO hay", "Ese puerco no existe");
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("Mal ruta", "malardo bro, la ruta esta pesimo");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Mal connectio", "malardo bro, la coneccion esta pesimo");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Mal jsonObject", "malardo bro, el json esta pesimo");
                }

                return alumne;

            } else if (parameters[1].equals("4")){

                try {
                    url = new URL(ruta);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.connect();
                    JSONObject jeison = new JSONObject();
                    jeison.put("idalumno", parameters[2]);

                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jeison.toString());
                    writer.flush();
                    writer.close();
                    int response = connection.getResponseCode();

                    if (response == HttpURLConnection.HTTP_OK){
                        respuesta = "BORRADO";
                    } else {
                        respuesta = "No se pudo borrar";
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("ruta", "NO RUTA");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("mal conection", "No conection");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("jsonerror", "MAL NO SE PUDO ESCRIBIR EN JSOOON");
                }

                return respuesta;

            } else if (parameters[1].equals("5")){

                try {
                    url = new URL(ruta);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.connect();
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("idalumno",parameters[2]);
                    jsonParam.put("nombre", parameters[3]);
                    jsonParam.put("direccion", parameters[4]);

                    OutputStream os = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int responseCode = connection.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_OK){
                        respuesta = "Buenardo, actualizado exitosamente";
                    } else {
                        respuesta = "Malardo, no se pudo actualizar";
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("ruta", "NO RUTA");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("mal conection", "No conection");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("jsonerror", "MAL NO SE PUDO ESCRIBIR EN JSOOON");
                }

                return respuesta;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            dates.setText(s);
        }
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

        sw = new ServicioWeb();

        switch (v.getId()){

            case R.id.btn_ListarA:
                sw.execute(host.concat(get), "1"); //ejecuta el hilo de tu vieja en el background
                cargarRecy(alumnos);
                break;
            case R.id.btn_anadirA:
                //String ida = id_A.getText().toString();
                String neme = name.getText().toString();
                String dire =  direction.getText().toString();
                if (!neme.equals("") && !dire.equals("")){
                    sw.execute(host.concat(insert), "2", neme, dire);
                    Toast.makeText(this, dates.getText().toString(), Toast.LENGTH_SHORT).show();
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
                    sw.execute(ure, "3");
                    String puerco = dates.getText().toString();
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
                    sw.execute(host.concat(update), "5", idalumnox, nemealu, direalu);
                    Toast.makeText(this, dates.getText().toString(), Toast.LENGTH_SHORT).show();
                    name.setText(null);
                    direction.setText(null);
                    id_A.setText(null);
                }
                break;
            case R.id.btn_EliminarAllA:
                String idal = id_A.getText().toString();
                if (!idal.equals("")){
                    sw.execute(host.concat(borrar), "4", idal);
                    Toast.makeText(this, dates.getText().toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "DATOS, PUERCO", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
