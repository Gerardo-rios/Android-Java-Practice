package vista.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lostzone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HiloOpenWeather extends AppCompatActivity implements View.OnClickListener {

    TextView coordenadas, clima, base, principal, visibilidad, viento, nubes, dt, sys, ide, nombre, codigo;
    Button btn;
    String url = "https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22";
    Servicio s;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilo_open_weather);
        componentes();
    }

    private void componentes(){
        coordenadas = findViewById(R.id.lbl_coordenadas);
        clima = findViewById(R.id.lbl_clima);
        base = findViewById(R.id.lbl_stations);
        principal = findViewById(R.id.lbl_principal);
        visibilidad = findViewById(R.id.lbl_visibilidad);
        viento = findViewById(R.id.lbl_viento);
        nubes = findViewById(R.id.lbl_nubes);
        dt = findViewById(R.id.lbl_dt);
        sys = findViewById(R.id.lbl_sys);
        ide = findViewById(R.id.lbl_idt);
        nombre = findViewById(R.id.lbl_nombreLugar);
        codigo = findViewById(R.id.lbl_codigoLugar);
        btn = findViewById(R.id.btn_obtJSON);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        s = new Servicio();

        switch (v.getId()){
            case R.id.btn_obtJSON:
                s.execute(url, "1");
                leer(result);
                break;
        }

    }

    private void leer(String datos){
        try {
            JSONObject json = new JSONObject(datos);
            String cordes = "Longitud: " + json.getJSONObject("coord").getString("lon") + "                 " +
                    "Latitud: " + json.getJSONObject("coord").getString("lat");
            coordenadas.setText(cordes);
            JSONArray jsonArray = json.optJSONArray("weather");
            JSONObject objetoClima = null;
            for (int i = 0; i < jsonArray.length(); i++){
                objetoClima = jsonArray.getJSONObject(i);
            }
            String climats = "Id: " + objetoClima.getString("id") + "               " + "Principal: " + objetoClima.getString("main") + "\n" +
                    "Descripcion: " + objetoClima.getString("description") + "                  " + "Icono: " + objetoClima.getString("icon");
            clima.setText(climats);
            String beis = json.getString("base");
            base.setText(beis);
            String mein = "Temperatura: " + json.getJSONObject("main").getString("temp") + "              "
                    + "Presion: " + json.getJSONObject("main").getString("pressure") + "              " +
                    "Humedad: " + json.getJSONObject("main").getString("humidity") + "\n" +
                    "Temperatura minima: " + json.getJSONObject("main").getString("temp_min") + "\n" +
                    "Temperatura maxima: " + json.getJSONObject("main").getString("temp_max");
            principal.setText(mein);
            String visibili = "Visibilidad: " + json.getString("visibility");
            visibilidad.setText(visibili);
            String wind = "Velocidad: " + json.getJSONObject("wind").getString("speed") + "                   " +
                    "Deg: " + json.getJSONObject("wind").getString("deg");
            viento.setText(wind);
            String clouds = "Todas: " + json.getJSONObject("clouds").getString("all");
            nubes.setText(clouds);
            String dete = json.getString("dt");
            dt.setText(dete);
            String sis = "Tipo: " + json.getJSONObject("sys").getString("type") + "         " +
                    "Identificador: " + json.getJSONObject("sys").getString("id") + "         " +
                    "Mensaje: " + json.getJSONObject("sys").getString("message") + "\n" +
                    "Pais: " + json.getJSONObject("sys").getString("country") + "\n" +
                    "Amanecer: " + json.getJSONObject("sys").getString("sunrise") + "\n" +
                    "Atardecer: " + json.getJSONObject("sys").getString("sunset");
            sys.setText(sis);
            String identificator = json.getString("id");
            ide.setText(identificator);
            String name = json.getString("name");
            nombre.setText(name);
            String code = json.getString("cod");
            codigo.setText(code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class Servicio extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String ruta = strings[0];
            String respuesta = "";
            URL url;

            if (strings[1].equals("1")){
                try {
                    url = new URL(ruta);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int codigoResponse = connection.getResponseCode();
                    if (codigoResponse == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        respuesta += reader.readLine();
                        return respuesta;
                    } else {
                        Log.e("Errorsisimo", "NO hubo codigo de respuesta ok");
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e("Mal RUTA", "Intenta con otra ruta");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Mal COnection", "No se pudo realizar coneccion");
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            result = s;
        }
    }
}
