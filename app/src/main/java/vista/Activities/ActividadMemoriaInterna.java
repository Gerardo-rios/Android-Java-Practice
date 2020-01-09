package vista.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostzone.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import modelo.Artista;

public class ActividadMemoriaInterna extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE = 100;
    Button guardar, buscarTodos;
    EditText cajaName, cajaLastname, artistico, wasborn;
    TextView datos;
    RecyclerView recycler;
    ImageButton foto;
    Uri imageUri;
    List<Artista> lista = new ArrayList<>();
    MIadapter adapter;
    String lineas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_memoria_interna);
        tomarControl();
    }

    private void tomarControl(){

        guardar = findViewById(R.id.btn_guardar);
        buscarTodos = findViewById(R.id.btn_buscarTodos);
        cajaName = findViewById(R.id.txt_NombreMI);
        cajaLastname = findViewById(R.id.txt_ApellidoMI);
        artistico = findViewById(R.id.txt_artisticoMI);
        datos = findViewById(R.id.lbl_datosMI);
        recycler = findViewById(R.id.recycler_lista);
        foto = findViewById(R.id.btn_imagen);
        wasborn = findViewById(R.id.txt_nacimiento);
        wasborn.setOnClickListener(this);
        foto.setOnClickListener(this);
        guardar.setOnClickListener(this);
        buscarTodos.setOnClickListener(this);

    }

    private void cargarRecycler(String datos){
        String [] artistas = datos.split(";");
        for (int i = 0; i < artistas.length; i++){
            String [] atributos = artistas[i].split(",");
            Artista art = new Artista();
            art.setNombres(atributos[0]);
            art.setNartistico(atributos[1]);
            art.setNacimiento(atributos[2]);
            art.setPath(atributos[3]);
            lista.add(art);
        }
        adapter = new MIadapter(lista);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                wasborn.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_guardar:
                try {
                    OutputStreamWriter escritor = new OutputStreamWriter(openFileOutput("archivo2.txt", Context.MODE_APPEND));
                    escritor.write(cajaName.getText().toString() + " " + cajaLastname.getText().toString() + "," + artistico.getText().toString() + "," + wasborn.getText().toString() + "," + imageUri.toString() + ";");
                    datos.setText("Guardado Exitosamente");
                    cajaName.setText(null);
                    cajaLastname.setText(null);
                    artistico.setText(null);
                    wasborn.setText(null);
                    foto.setImageURI(null);
                    escritor.close();
                } catch (Exception ex){
                    Log.e("ArchivoMI", "error de escritura" + ex.getMessage());
                }
            break;

            case R.id.btn_buscarTodos:
                try {
                    BufferedReader lector = new BufferedReader(new InputStreamReader(openFileInput("archivo2.txt")));
                    lineas = lector.readLine();
                    cargarRecycler(lineas);
                    //datos.setText(lineas);
                    lector.close();
                } catch (Exception ex){
                    Log.e("ArchivoMI", "error de lectura" + ex.getMessage());
                }
            break;

            case R.id.btn_imagen:
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                gallery.setType("image/*");
                startActivityForResult(gallery, PICK_IMAGE);
            break;

            case R.id.txt_nacimiento:
                showDatePickerDialog();
            break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data != null ? data.getData() : null;
            foto.setImageURI(imageUri);
        }

    }


}
