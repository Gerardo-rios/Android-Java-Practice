package vista.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostzone.R;

public class logear extends AppCompatActivity implements View.OnClickListener{

    EditText usuario, clave;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logear);
        cargarComponentes();
    }

    private void cargarComponentes(){
        usuario = findViewById(R.id.txt_username);
        clave = findViewById(R.id.pass_clave);
        boton = findViewById(R.id.btn_logear);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = usuario.getText().toString();
        String password = clave.getText().toString();
        if (username.equals("") && password.equals("")){
            Toast.makeText(this, "INSERTA DATOS, PUERCO", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(logear.this, "Usuario: " + username
                    + "\n" +"Clave: " + password, Toast.LENGTH_SHORT).show();
            usuario.setText(null);
            clave.setText(null);
        }

    }
}
