package modelo;

import java.util.Date;

public class Artista {

    private String nombres;
    private String nartistico;
    private int foto;
    private Date nacido;
    private String path;
    private String nacimiento;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getNacido() {
        return nacido;
    }

    public void setNacido(Date nacido) {
        this.nacido = nacido;
    }

    public String getNartistico() {
        return nartistico;
    }

    public void setNartistico(String nartistico) {
        this.nartistico = nartistico;
    }


    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }
}
