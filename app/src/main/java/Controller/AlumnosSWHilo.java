package Controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.toolbox.StringRequest;

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

public class AlumnosSWHilo extends AsyncTask<String, Void, String> {

    public String devolucion;

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
                    return consulta;
                } else {
                    Log.e("Errorsisimo", "NO hubo codigo de respuesta ok");
                }
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("Error", "NO se encontro la ruta");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Malardo", "NO se pudo hacer coneccion");
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

                connection.disconnect();

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
                connection.disconnect();
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

                connection.disconnect();

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

                connection.disconnect();

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

    protected void onPostExecute(String s) {
        //super.onPostExecute(s);
        //dates.setText(s);
        devolucion = s;
    }

}
