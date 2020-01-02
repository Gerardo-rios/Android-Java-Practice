package vista.Fragmentos;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.lostzone.R;

public class Fragemento_Activity extends AppCompatActivity implements View.OnClickListener, FrgUno.OnFragmentInteractionListener, FrgDos.OnFragmentInteractionListener {

    Button btnfrag1, btnfrag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragemento_);
        cargarComponentes();
    }

    private void cargarComponentes(){
        btnfrag1 = findViewById(R.id.btn_frg1);
        btnfrag2 = findViewById(R.id.btn_frg2);
        btnfrag1.setOnClickListener(this);
        btnfrag2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_frg1:
                FrgUno fragmento1 = new FrgUno(); //se crea objeto tipo fragmento
                FragmentTransaction transaccion1 = getSupportFragmentManager().beginTransaction(); //se crea transaccion de fragmento
                transaccion1.replace(R.id.contenedor_fragmentos, fragmento1); //hacemos el remplazo
                transaccion1.commit(); //guardamos el remplazo
                break;
            case R.id.btn_frg2:
                FrgDos fragmento2 = new FrgDos();
                FragmentTransaction transaccion2 = getSupportFragmentManager().beginTransaction();
                transaccion2.replace(R.id.contenedor_fragmentos, fragmento2);
                transaccion2.commit();
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
