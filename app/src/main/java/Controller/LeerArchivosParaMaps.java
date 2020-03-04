package Controller;

import android.content.Context;
import android.util.Log;

import com.example.lostzone.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import modelo.Ruta;

public class LeerArchivosParaMaps {

    Context context;

    public LeerArchivosParaMaps(Context contexto){
        this.context = contexto;
    }

    public List<Ruta> Obtener_rutas(){
        InputStream input = context.getResources().openRawResource(R.raw.ruta);
        BufferedReader lector = new BufferedReader(new InputStreamReader(input));
        List<Ruta> puntos = new ArrayList<>();
        String cadena = "";
        try {
            do {
                cadena += lector.readLine();
            } while (!lector.readLine().equals(""));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            Log.e("mal", "nulo");
        }
        Log.d("cadena", cadena);
        String[] rutas = cadena.split("/");
        Log.d("rutsize", String.valueOf(rutas.length));
        for (int i = 0; i < rutas.length; i++){

            String [] latslongsT = rutas[i].split(";");
            String [] ll = latslongsT[0].split(",");
            String [] ts = latslongsT[1].split(",");
            String lati = ll[0];
            String log = ll[1];
            String title = ts[0];
            String desc = ts[1];
            Ruta r = new Ruta(Double.parseDouble(lati), Double.parseDouble(log), title, desc);
            puntos.add(r);

        }

        return puntos;
    }





}
