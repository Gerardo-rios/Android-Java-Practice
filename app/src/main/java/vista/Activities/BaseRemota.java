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

import Controller.HiloBaseRemota;
import modelo.Alumno;
import modelo.Usuario;

public class BaseRemota extends AppCompatActivity implements View.OnClickListener {

    TextView datinos;
    Button registrar, listar, modificar, eliminar, obteneruno;
    ImageButton imagen;
    EditText documento, nombre, profesion;
    RecyclerView recycler;

    String host = "http://192.168.0.104/BDremota";
    //String insert = "/wsJSONRegistroMovil.php";
    String insert = "/wsJSONRegistro.php";
    //String getSolotexto = "/wsJSONConsultarLista.php";
    String getLista = "/wsJSONConsultarListaImagenesUrl.php";
    //String getListaImg = "/wsJSONConsultarListaImagenes.php";
    //String getbyID = "/wsJSONConsultarUsuario.php";
    String getbyIDurl = "/wsJSONConsultarUsuarioUrl.php";
    String update = "/wsJSONUpdateMovil.php";
    String borrar = "/wsJSONDeleteMovil.php";

    HiloBaseRemota hbs;

    List<Usuario> users;
    List<Usuario> obtenidobyID;
    userAdapter adapter;

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_remota);
        componentes();
    }

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
    public void onClick(View v) {

        hbs = new HiloBaseRemota();

        switch (v.getId()){

            case R.id.btn_obtener_todos:
                String datod = null;
                try {
                    datod = hbs.execute(host.concat(getLista), "1").get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error de ejecucion", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Se ha interrumpido", Toast.LENGTH_SHORT).show();
                }
                users = new ArrayList<>();
                try{
                    if(!datod.equals("")){
                        try {
                            JSONObject json = new JSONObject(datod);
                            JSONArray jsonArray = json.optJSONArray("usuario");
                            for (int i = 0; i < jsonArray.length(); i++){
                                users.add(new Usuario(jsonArray.getJSONObject(i)));
                            }
                            cargarRecy(users);
                        }catch (JSONException ex){
                            ex.printStackTrace();
                            Log.e("Error con JSON", "No se pudo convertir en YEISON");
                        }
                    } else {
                        Toast.makeText(this, "NO se pudo obtener datos, intentalo de vuelta", Toast.LENGTH_SHORT).show();
                    }
                } catch (NullPointerException ex){
                    Toast.makeText(this, "NO se pudo obtener datos, intentalo de vuelta", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_obtener_usuario:
                String identificador = documento.getText().toString();

                if (!identificador.equals("")){

                    String deruta = host + getbyIDurl + "?documento=" + identificador;

                    String retorno = null;

                    try {

                        retorno = hbs.execute(deruta, "2").get();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        Log.e("malardo", "ERROR DE EJECUCION");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e("malardo", "SE INTERRUMPIO LA JUGADA");
                    }
                    try {
                        if (!retorno.equals("")){
                            try {
                                String[] atributos = retorno.split(",");
                                obtenidobyID = new ArrayList<>();
                                Usuario u = new Usuario(atributos[0], atributos[1], atributos[2], atributos[3]);
                                obtenidobyID.add(u);
                                cargarRecy(obtenidobyID);
                            } catch (ArrayIndexOutOfBoundsException e){
                                Log.e("Mal index", "NO existe esa posicion");
                                Toast.makeText(this, "Ese puerco no existe mi hermano", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "ESE GORRINO NO EXISTE", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException e){
                        Toast.makeText(this, "ESE GORRINO NO EXISTE", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(this, "Ingresa un identificador, GORRINO!", Toast.LENGTH_SHORT).show();

                }

                break;

            case R.id.btn_registrar:

                String ida = documento.getText().toString();
                String neme = nombre.getText().toString();
                String profes =  profesion.getText().toString();
                //String img = imageUri.toString();
                if (!ida.equals("") && !neme.equals("") && !profes.equals("")){
                    String mevoyderuta = host + insert + "?documento=" + ida + "&nombre=" + neme + "&profesion=" + profes;
                    try {
                        Toast.makeText(this, hbs.execute(mevoyderuta, "3").get(), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        Toast.makeText(this, "No se pudo ejectar", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "No se pudo obtener string", Toast.LENGTH_SHORT).show();
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

                String idal = documento.getText().toString();
                if (!idal.equals("")){
                    try {
                        String partusa = host + borrar + "?documento=" + idal;
                        Toast.makeText(this, hbs.execute(partusa, "4").get(), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "No se pudo ejecutar", Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "NO se pudo obtener string", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "DATOS, PUERCO", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_actualizar_usuario:

                String cedu = documento.getText().toString();
                String tuviname = nombre.getText().toString();
                String vieja =  profesion.getText().toString();

                if (!cedu.equals("") && !tuviname.equals("") && !vieja.equals("")){
                    try {
                        Toast.makeText(this, hbs.execute(host.concat(update), "5", cedu, tuviname, vieja).get(), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        Toast.makeText(this, "No se pudo ejectar", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "No se pudo obtener string", Toast.LENGTH_SHORT).show();
                    }
                    documento.setText(null);
                    nombre.setText(null);
                    profesion.setText(null);
                    imagen.setImageURI(null);
                } else {
                    Toast.makeText(this, "DATOS, PUERCO", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_foto:
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                gallery.setType("image/*");
                startActivityForResult(gallery, 100);
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 100){
            imageUri = data != null ? data.getData() : null;
            imagen.setImageURI(imageUri);
        }
    }
}
