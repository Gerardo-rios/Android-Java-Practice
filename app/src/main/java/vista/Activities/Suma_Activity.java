package vista.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.lostzone.R;

public class Suma_Activity extends AppCompatActivity implements View.OnClickListener{

    EditText num1, num2;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma_);
        cargarComponentes();
    }

    private void cargarComponentes(){
        num1 = findViewById(R.id.txt_num1);
        num2 = findViewById(R.id.txt_num2);
        boton = findViewById(R.id.btn_calcular);
        boton.setOnClickListener(this);
    }

    public Double sumar(){
        Double resultado = null;

        try {

            resultado = Double.parseDouble(num1.getText().toString()) + Double.parseDouble(num2.getText().toString());


        } catch (NumberFormatException ex){

            Toast.makeText(this, "Ingresa un numero, CERDO", Toast.LENGTH_SHORT).show();

        }
        return resultado;
    }


    @Override
    public void onClick(View v) {

        Toast.makeText(Suma_Activity.this, "La suma es: " + sumar(), Toast.LENGTH_SHORT).show();

    }
}
