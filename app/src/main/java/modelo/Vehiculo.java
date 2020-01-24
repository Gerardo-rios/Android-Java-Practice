package modelo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "car")
public class Vehiculo extends Model {

    @Column(name = "placa", unique = true)
    private String placa;
    @Column(name = "marca", notNull = true)
    private String marca;
    @Column(name = "modelo", notNull = true)
    private String modelo;
    @Column(name = "anio", notNull = true)
    private String anio;

    public Vehiculo(){

    }

    public Vehiculo(String placa, String marca, String modelo, String anio) {
        super();
        this.setPlaca(placa);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setAnio(anio);
    }


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public static List<Vehiculo> getAllCarro(){
        return new Select().from(Vehiculo.class).execute();
    }

    public static Vehiculo getCarroPlaca(String placa){
        return new Select().from(Vehiculo.class).where("placa = ?", placa).executeSingle();
    }

    public static void deleteCar(String placa){
        Vehiculo.getCarroPlaca(placa).delete();
    }

}
