package Controller;

import android.icu.lang.UScript;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
import java.net.URLEncoder;

public class HiloBaseRemota extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        String consulta = "";
        String user = "";
        URL url;
        String ruta = strings[0];

        if (strings[1].equals("1")){
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
        } else if (strings[1].equals("2")){
            try {
                url = new URL(ruta);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int codigoResponse = connection.getResponseCode();
                if (codigoResponse == HttpURLConnection.HTTP_OK ){
                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    consulta += reader.readLine();
                    JSONObject yeison = new JSONObject(consulta);
                    JSONArray yeisonArray = yeison.getJSONArray("usuario");
                    JSONObject userObjeto = yeisonArray.getJSONObject(0);
                    user = userObjeto.getString("documento") + "," + userObjeto.getString("nombre") + "," +
                            userObjeto.getString("profesion") + "," + userObjeto.getString("ruta_imagen");
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

            return user;
        } else if (strings[1].equals("3")){

            try {
                url = new URL(ruta);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int codigoResponse = connection.getResponseCode();
                if (codigoResponse == HttpURLConnection.HTTP_OK ){
                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    consulta += reader.readLine();
                }
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("Mal ruta", "malardo bro, la ruta esta pesimo");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Mal connectio", "malardo bro, la coneccion esta pesimo");
            }

            return consulta;

        } else if (strings[1].equals("4")){

            try {
                url = new URL(ruta);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int codigoResponse = connection.getResponseCode();
                if (codigoResponse == HttpURLConnection.HTTP_OK ){
                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    consulta += reader.readLine();
                }
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("Mal ruta", "malardo bro, la ruta esta pesimo");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Mal connectio", "malardo bro, la coneccion esta pesimo");
            }

            return consulta;

        } else if (strings[1].equals("5")){

            try {
                url = new URL(ruta);
                String docu = "documento=" + strings[2] + "nombre=" + strings[3] + "profesion=" + strings[4];
                /*String n = strings[3];
                String p = strings[4];*/
                String data = URLEncoder.encode(docu,"UTF-8");
                /*String datan = "nombre=" + URLEncoder.encode(n, "UTF-8");
                String datap = "profesion=" + URLEncoder.encode(p, "UTF-8");*/
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.connect();

                /*JSONObject jsonParam = new JSONObject();
                jsonParam.put("documento",strings[2]);
                jsonParam.put("nombre", strings[3]);
                jsonParam.put("profesion", strings[4]);*/
                OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                /*OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));*/
                out.write(data.getBytes());
                /*out.write(datan.getBytes());
                out.write(datap.getBytes());*/
                out.flush();
                out.close();

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK){
                    consulta = "Buenardo, actualizado exitosamente";
                } else {
                    consulta = "Malardo, no se pudo actualizar";
                }

                connection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("ruta", "NO RUTA");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("mal conection", "No conection");
            }

            return consulta;

        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
