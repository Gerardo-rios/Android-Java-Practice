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


import modelo.Alumno;

public class ServicioWebAlumnosVolley {

    String host = "http://reneguaman.000webhostapp.com";
    String insert = "/insertar_alumno.php";
    String get = "/obtener_alumnos.php";
    String getbyID = "/obtener_alumno_por_id.php";
    String update = "/actualizar_alumno.php";
    String borrar = "/borrar_alumno.php";

    String datos;
    boolean estado;
    Context contexto;
    JSONObject yeison;

    public ServicioWebAlumnosVolley(Context context) {
        this.contexto = context;
    }

    public boolean Insertar(Alumno a){
        String path = host.concat(insert);
        JSONObject yeis = new JSONObject();
        try {
            yeis.put("nombre", a.getNombre());
            yeis.put("direccion", a.getDireccion());
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(contexto, "NO se pudo escribir JSON", Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, path, yeis, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("estado").equals("1")){
                        estado = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(contexto, "NO se obtener estado", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });
        Singleton.getInstance(contexto).addtoRequestQueue(request);
        return estado;
    }

    /**
     * Metodo para listar todos los alumnos
     * @return el string con todos los datos
     */
    public String Listar(){
        String path = host.concat(get);
        StringRequest request = new StringRequest(Request.Method.GET, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                datos = response;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, "ERROR, NO SE PUDO OBTENER RESPONSE", Toast.LENGTH_LONG).show();
            }
        });
        Singleton.getInstance(contexto).addtoRequestQueue(request);

        return datos;
    }

    public JSONObject ObtenerAlumno(String identi){

        String path = host + getbyID + "?idalumno=" + identi;
        //yeison = new JSONObject();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, yeison, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                yeison = response;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, "ERROR, NO SE PUDO OBTENER RESPONSE", Toast.LENGTH_LONG).show();
            }
        });
        Singleton.getInstance(contexto).addtoRequestQueue(request);
        //Toast.makeText(contexto, yeison.toString(), Toast.LENGTH_SHORT).show();
        return yeison;
    }

    public boolean Modificar(String ide, String name, String direction){
        String path = host.concat(update);
        JSONObject yeis = new JSONObject();
        try {
            yeis.put("idalumno", ide);
            yeis.put("nombre", name);
            yeis.put("direccion", direction);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(contexto, "NO se pudo escribir JSON", Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, path, yeis, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("estado").equals("1")){
                        estado = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(contexto, "NO se obtener estado", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });
        Singleton.getInstance(contexto).addtoRequestQueue(request);

        return estado;
    }

    public boolean Eliminar(String identificador){

        String path = host.concat(borrar);
        JSONObject yeis = new JSONObject();
        try {
            yeis.put("idalumno", identificador);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(contexto, "NO se pudo escribir JSON", Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, path, yeis, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("estado").equals("1")){
                        estado = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(contexto, "NO se obtener estado", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });
        Singleton.getInstance(contexto).addtoRequestQueue(request);

        return estado;
    }


}
