package vista.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostzone.R;

public class IgresarNombreApellido extends AppCompatActivity implements View.OnClickListener {

    Button boton;
    EditText apellido, nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igresar_nombre_apellido);
        cargarComponentes();
    }

    public void cargarComponentes(){
        nombre = findViewById(R.id.txt_nombre);
        apellido = findViewById(R.id.txt_apellido);
        boton = findViewById(R.id.btn_enviar);
        boton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        Intent intencion = new Intent(IgresarNombreApellido.this, verNombreApellido.class);
        Bundle bundle = new Bundle();

        /*intencion = new Intent(IgresarNombreApellido.this, verNombreApellido.class);
        intencion.putExtra("nombre", nombre.getText().toString());
        intencion.putExtra("apellido", apellido.getText().toString()); */

        //otra forma
        String name = nombre.getText().toString();
        String lastname = apellido.getText().toString();

        if (!name.equals("") && !lastname.equals("")){

            bundle.putString("nombre", name);
            bundle.putString("apellido", lastname);
            intencion = intencion.putExtras(bundle);

            startActivity(intencion);

            nombre.setText(null);
            apellido.setText(null);

        } else{

            Toast.makeText(this, "No haz ingresado datos, Calvo", Toast.LENGTH_SHORT).show();

        }



    }
}
