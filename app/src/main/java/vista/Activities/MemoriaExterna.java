package vista.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lostzone.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import modelo.Artista;

public class MemoriaExterna extends AppCompatActivity implements View.OnClickListener{

    private static final int PICK_IMAGE = 100;
    TextView datos;
    EditText cajaNombre, cajaApellido, cajaArtistico, nacio;
    Button guardar, buscar;
    ImageButton imagen;
    RecyclerView recycler;
    Uri imageUri;
    List<Artista> lista = new ArrayList<>();
    MIadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria_externa);
        obtenerComponentes();
    }

    private void obtenerComponentes(){
        datos = findViewById(R.id.lbl_datosME);
        cajaNombre = findViewById(R.id.txt_NombreME);
        cajaApellido = findViewById(R.id.txt_ApellidoME);
        cajaArtistico = findViewById(R.id.txt_artisticoME);
        nacio = findViewById(R.id.txt_nacimientoME);
        guardar = findViewById(R.id.btn_guardarME);
        buscar = findViewById(R.id.btn_buscarTodosME);
        recycler = findViewById(R.id.recycler_listaME);
        imagen = findViewById(R.id.btn_imagenME);
        imagen.setOnClickListener(this);
        nacio.setOnClickListener(this);
        guardar.setOnClickListener(this);
        buscar.setOnClickListener(this);
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
                nacio.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_guardarME:
                File ruta = Environment.getExternalStorageDirectory();
                File file = new File(ruta.getAbsoluteFile(), "archivo1.txt");
                try {
                    OutputStreamWriter escritor = new OutputStreamWriter(new FileOutputStream(file, true));
                    escritor.write(cajaNombre.getText().toString() + " " + cajaApellido.getText().toString() + "," + cajaArtistico.getText().toString() + "," + nacio.getText().toString() + "," + imageUri.toString() + ";");
                    datos.setText("Guardado Exitosamente");
                    cajaNombre.setText(null);
                    cajaApellido.setText(null);
                    cajaArtistico.setText(null);
                    nacio.setText(null);
                    imagen.setImageURI(null);
                    escritor.close();
                } catch (Exception ex){
                    Log.e("ArchivoME", "error de escritura" + ex.getMessage());
                }

                break;
            case R.id.btn_buscarTodosME:
                File path = Environment.getExternalStorageDirectory();
                File archivo = new File(path.getAbsoluteFile(), "archivo1.txt");
                try {
                    BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
                    cargarRecycler(lector.readLine());
                    //datos.setText(lector.readLine());
                    lector.close();
                } catch (FileNotFoundException e) {
                    Log.e("ArchivoME", "error de lectura" + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.txt_nacimientoME:
                showDatePickerDialog();
                break;
            case R.id.btn_imagenME:
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                gallery.setType("image/*");
                if ( Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP ) {
                    gallery.setClipData( ClipData.newRawUri( "", imageUri ) );
                    gallery.addFlags( Intent.FLAG_GRANT_WRITE_URI_PERMISSION|Intent.FLAG_GRANT_READ_URI_PERMISSION );
                }
                startActivityForResult(gallery, PICK_IMAGE);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data != null ? data.getData() : null;
            imagen.setImageURI(imageUri);
        }
    }

}
