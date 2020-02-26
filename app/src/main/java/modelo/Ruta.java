package modelo;

public class Ruta {

    private Double lati;
    private Double longi;
    private String title;
    private int icon;

    public void Ruta(){

    }

    public Ruta(Double lati, Double longi, String title) {
        this.lati = lati;
        this.longi = longi;
        this.title = title;
    }

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
