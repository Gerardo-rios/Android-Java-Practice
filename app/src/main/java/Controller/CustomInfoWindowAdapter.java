package Controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.lostzone.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;


public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {


    private final View ventana;
    private Context contexto;

    public CustomInfoWindowAdapter(Context contexto) {
        this.contexto = contexto;
        ventana = LayoutInflater.from(contexto).inflate(R.layout.dialogo_info_marcador_mapa, null);
    }

    private void RenderizarTextoenVentana(Marker marker, View view){

        String title = marker.getTitle();
        TextView titulo = view.findViewById(R.id.lbl_titulo_marker);
        try {
            if (!title.equals("")){
                titulo.setText(title);
            }
        }catch (NullPointerException e){
            Log.e("nulo", "que procede");
        }

        String snipet = marker.getSnippet();
        TextView descripcion = view.findViewById(R.id.lbl_descripcion_marker);
        try {
            if (!snipet.equals("")){
                descripcion.setText(snipet);
            }
        } catch (NullPointerException e){
            Log.e("nulo", "que procede");
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        RenderizarTextoenVentana(marker, ventana);
        return ventana;
    }

    @Override
    public View getInfoContents(Marker marker) {
        RenderizarTextoenVentana(marker, ventana);
        return ventana;
    }
}
