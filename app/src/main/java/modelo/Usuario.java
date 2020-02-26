package modelo;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario {

    private String Dni;
    private String Nombre;
    private String Profesion;
    private String Foto;
    private Bitmap img;

    public Usuario(){

    }

    public Usuario(String cedula, String name, String prof, String path) {
        this.setDni(cedula);
        this.setNombre(name);
        this.setProfesion(prof);
        this.setFoto(path);
    }

    public Usuario(JSONObject objectJSON){
        try {
            this.setDni(objectJSON.getString("documento"));
            this.setNombre(objectJSON.getString("nombre"));
            this.setProfesion(objectJSON.getString("profesion"));
            this.setFoto(objectJSON.getString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("malardobro", "No se pudo obtener los datos");
        }
    }


    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getProfesion() {
        return Profesion;
    }

    public void setProfesion(String profesion) {
        Profesion = profesion;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
