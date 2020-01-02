package vista.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostzone.R;

public class verNombreApellido extends AppCompatActivity {

    TextView Vnombre, Vapellido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nombre_apellido);
        cargar();

    }

    public void cargar(){

        Vnombre = findViewById(R.id.lbl_nombre);
        Vapellido = findViewById(R.id.lbl_apellido);

        /*Vnombre.setText(getIntent().getStringExtra("nombre"));
        Vapellido.setText(getIntent().getStringExtra("apellido"));*/
        //otra forma
        Bundle bundle = this.getIntent().getExtras();
        Vnombre.setText(bundle.getString("nombre"));
        Vapellido.setText(bundle.getString("apellido"));
    }

}
