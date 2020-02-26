package Controller;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseRemotaVolley {

    String host = "http://192.168.0.104/BDremota";
    String insert = "/wsJSONRegistro.php";
    String getLista = "/wsJSONConsultarListaImagenesUrl.php";
    String getbyIDurl = "/wsJSONConsultarUsuarioUrl.php";
    String update = "/wsJSONUpdateMovil.php";
    String borrar = "/wsJSONDeleteMovil.php";

    boolean estado;
    String datos;
    JSONObject yeison;

    Context context;

    public BaseRemotaVolley(Context contexto){
        this.context = contexto;
    }

    public boolean Insertar(String docu, String nomb, String prof){

        String url = host + insert + "?documento=" + docu + "&nombre=" + nomb + "&profesion=" + prof;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                estado = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });
        Singleton.getInstance(context).addtoRequestQueue(request);
        return estado;
    }

    public String Listar(){

        String url = host.concat(getLista);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                datos = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "ERROR, NO SE PUDO OBTENER DATOS", Toast.LENGTH_LONG).show();
            }
        });
        Singleton.getInstance(context).addtoRequestQueue(request);
        return datos;
    }

    public boolean Deletear(String identificador){

        String url = host + borrar + "?documento=" + identificador;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                estado = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });
        Singleton.getInstance(context).addtoRequestQueue(request);
        return estado;
    }

    public JSONObject GetbyID(String ide){

        String url = host + getbyIDurl + "?documento=" + ide;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, yeison, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                yeison = response;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "ERROR, NO SE PUDO OBTENER RESPONSE", Toast.LENGTH_LONG).show();
            }
        });
        Singleton.getInstance(context).addtoRequestQueue(request);

        return yeison;
    }

    public boolean Modificar(String ide, String name, String direction){
        String path = host.concat(update);
        JSONObject yeis = new JSONObject();
        try {
            yeis.put("documento", ide);
            yeis.put("nombre", name);
            yeis.put("profesion", direction);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "NO se pudo escribir JSON", Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, path, yeis, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                estado = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });
        Singleton.getInstance(context).addtoRequestQueue(request);

        return estado;
    }

}
