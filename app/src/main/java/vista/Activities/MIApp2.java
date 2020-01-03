package vista.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostzone.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import modelo.Artista;

public class MIApp2 extends AppCompatActivity implements View.OnClickListener{

    Button btn_leerRaw;
    TextView leerRaw;
    List<Artista> lista;
    MIadapter adapter;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miapp2);
        tomarcontrol();

    }

    public void tomarcontrol(){
        btn_leerRaw = findViewById(R.id.btn_leerRAW);
        leerRaw = findViewById(R.id.lbl_leerRAW);
        recycler = findViewById(R.id.recicler_raw);
        btn_leerRaw.setOnClickListener(this);
    }

    private void cargarRecycler(){
        adapter = new MIadapter(lista);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        try {
            InputStream input = getResources().openRawResource(R.raw.archivo_raw);
            BufferedReader lector = new BufferedReader(new InputStreamReader(input));
            String cadena = lector.readLine();
            leerRaw.setText(R.drawable.osama + " " + R.drawable.wazoski + " " + R.drawable.feelsbatman + " " + R.drawable.lanita + " " + R.drawable.riley + " " + R.drawable.yopuesquienmas);
            //leerRaw.setText("Datos listados");
            String[] split1 = cadena.split(";");
            lista = new ArrayList<>();
            for (int i = 0; i < split1.length; i++){
                String[] attrs = split1[i].split(",");
                Artista art = new Artista();
                art.setNombres(attrs[0]);
                art.setNartistico(attrs[1]);
                art.setNacimiento(attrs[2]);
                art.setFoto(Integer.parseInt(attrs[3]));
                lista.add(art);
            }
            cargarRecycler();
        } catch (IOException e) {
            Log.e("Malario", "Recontra mal perro, no se pudo leer");
        }


    }
}
