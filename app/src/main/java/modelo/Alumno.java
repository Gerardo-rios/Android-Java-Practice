package modelo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Alumno {

    private String id;
    private String nombre;
    private String direccion;

    public Alumno(){

    }

    public Alumno(String id, String nombre, String direccion) {
        this.setId(id);
        this.setNombre(nombre);
        this.setDireccion(direccion);
    }

    public Alumno(JSONObject objectJSON){
        try {
            this.setId(objectJSON.getString("idalumno"));
            this.setNombre(objectJSON.getString("nombre"));
            this.setDireccion(objectJSON.getString("direccion"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("malardobro", "No se pudo obtener los datos");
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
