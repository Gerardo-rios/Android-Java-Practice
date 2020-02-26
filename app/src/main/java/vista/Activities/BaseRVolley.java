package vista.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostzone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Controller.BaseRemotaVolley;
import modelo.Usuario;

public class BaseRVolley extends AppCompatActivity implements View.OnClickListener
{

    TextView datinos;
    Button registrar, listar, modificar, eliminar, obteneruno;
    ImageButton imagen;
    EditText documento, nombre, profesion;
    RecyclerView recycler;

    BaseRemotaVolley brv = new BaseRemotaVolley(this);

    List<Usuario> users;
    List<Usuario> obtenidobyID;
    userAdapter adapter;

    Uri imageUri;

    private void componentes(){
        datinos = findViewById(R.id.lbl_datos_usuarios);
        documento = findViewById(R.id.txt_dni);
        nombre = findViewById(R.id.txt_nickname);
        profesion = findViewById(R.id.txt_profesion);
        registrar = findViewById(R.id.btn_registrar);
        listar = findViewById(R.id.btn_obtener_todos);
        obteneruno = findViewById(R.id.btn_obtener_usuario);
        modificar = findViewById(R.id.btn_actualizar_usuario);
        eliminar = findViewById(R.id.btn_borrar_usuario);
        imagen = findViewById(R.id.btn_foto);
        recycler = findViewById(R.id.recycler_usuarios);
        imagen.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        modificar.setOnClickListener(this);
        listar.setOnClickListener(this);
        obteneruno.setOnClickListener(this);
        registrar.setOnClickListener(this);

    }

    private void cargarRecy(List<Usuario> lix){
        adapter = new userAdapter(lix);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_remota);
        componentes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 100){
            imageUri = data != null ? data.getData() : null;
            imagen.setImageURI(imageUri);
        }
    }

    private void listartodos(){
        String datoxd = brv.Listar();
        try{
            if (datoxd.equals("")){
                Toast.makeText(this, "No se pudo Obtener Datos, Intenta denuevo", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    users = new ArrayList<>();
                    try{
                        JSONObject json = new JSONObject(datoxd);
                        JSONArray jsonArray = json.optJSONArray("usuario");
                        for (int i = 0; i < jsonArray.length(); i++){
                            users.add(new Usuario(jsonArray.getJSONObject(i)));
                        }
                        cargarRecy(users);
                    } catch (NullPointerException ex){
                        Toast.makeText(this, "NO se pudo obtener datos, intentalo de vuelta", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "No se pudo Obtener JSON", Toast.LENGTH_SHORT).show();
                }
            }

        }catch (NullPointerException ex){
            ex.getMessage();
            Toast.makeText(this, "No se pudo Obtener Datos, Intenta denuevo", Toast.LENGTH_SHORT).show();
        }
    }

    private void buscarporid(){
        try {
            String cajaid = documento.getText().toString();
            obtenidobyID = new ArrayList<>();
            if (!cajaid.equals("")){
                JSONObject yeis = brv.GetbyID(cajaid);
                try {
                    JSONArray arr = yeis.getJSONArray("usuario");
                    for (int i = 0; i < arr.length(); i++){
                        obtenidobyID.add(new Usuario(arr.getJSONObject(i)));
                    }
                    cargarRecy(obtenidobyID);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "No se pudo obtener JSON", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Ingresa datos, maldito PUERCO!", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException ex){
            ex.printStackTrace();
            Toast.makeText(this, "No se pudo Obtener Datos, Intenta denuevo", Toast.LENGTH_SHORT).show();
        }
    }

    private String modificarA(String ide, String nombre, String direction){
        if (brv.Modificar(ide, nombre, direction)){
            return "MODIFICADO CON EXITO";
        } else {
            return "NO SE PUDO MODIFICAR";
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_obtener_todos:
                listartodos();
                break;

            case R.id.btn_obtener_usuario:
                buscarporid();
                break;

            case R.id.btn_registrar:

                String ida = documento.getText().toString();
                String neme = nombre.getText().toString();
                String profes =  profesion.getText().toString();
                //String img = imageUri.toString();
                if (!ida.equals("") && !neme.equals("") && !profes.equals("")){
                    if(!brv.Insertar(ida, neme, profes)){
                        Toast.makeText(this, "REGISTRADO", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "NO REGISTRADO", Toast.LENGTH_SHORT).show();
                    }
                    documento.setText(null);
                    nombre.setText(null);
                    profesion.setText(null);
                    imagen.setImageURI(null);
                } else {
                    Toast.makeText(this, "DATOS, PUERCO", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_borrar_usuario:
                String delid = documento.getText().toString();
                if (delid.equals("")){
                    Toast.makeText(this, "DATOS, PUERCO", Toast.LENGTH_SHORT).show();
                } else {
                    if(brv.Deletear(delid)){
                        Toast.makeText(this, "BORRADASO", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "NO SE PUDO BORRAR", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btn_actualizar_usuario:

                String identi = documento.getText().toString();
                String ne = nombre.getText().toString();
                String de = profesion.getText().toString();
                if (ne.equals("") || de.equals("") || identi.equals("")){
                    Toast.makeText(this, "Ingresa datos, maldito PUERCO!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, modificarA(identi, ne,de), Toast.LENGTH_SHORT).show();
                }
                documento.setText(null);
                nombre.setText(null);
                profesion.setText(null);
                imagen.setImageURI(null);
                break;

            case R.id.btn_foto:
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                gallery.setType("image/*");
                startActivityForResult(gallery, 100);
                break;

        }
    }
}
