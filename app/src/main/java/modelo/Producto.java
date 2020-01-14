package modelo;

public class Producto {

    private int codigo;
    private String despcripcion;
    private Double precio;
    private int cantidad;
    private String nombre;

    public Producto(int codigo, String nombre, String despcripcion, Double precio, int cantidad) {
        this.setCodigo(codigo);
        this.setDespcripcion(despcripcion);
        this.setPrecio(precio);
        this.setCantidad(cantidad);
        this.setNombre(nombre);
    }

    public  Producto(){

    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDespcripcion() {
        return despcripcion;
    }

    public void setDespcripcion(String despcripcion) {
        this.despcripcion = despcripcion;
    }



    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
