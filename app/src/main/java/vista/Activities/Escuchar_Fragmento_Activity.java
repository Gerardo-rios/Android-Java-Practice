package vista.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.lostzone.R;
import vista.Fragmentos.frg4_nclicks;

import modelo.Comunicador;
import vista.Fragmentos.frg3_boton;

public class Escuchar_Fragmento_Activity extends AppCompatActivity implements Comunicador, View.OnClickListener, frg3_boton.OnFragmentInteractionListener, frg4_nclicks.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setContentView(R.layout.activity_escuchar__fragmento_);
        FragmentTransaction transa = getSupportFragmentManager().beginTransaction();
        frg3_boton bot = new frg3_boton();
        transa.replace(R.id.content_btn, bot);
        transa.commit();

        FragmentTransaction transan = getSupportFragmentManager().beginTransaction();
        frg4_nclicks nclick = new frg4_nclicks();
        transan.replace(R.id.content_txt, nclick);
        transan.commit();*/

        setContentView(R.layout.activity_escuchar__fragmento_);


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void responder(String datos) {

        FragmentManager manejadorFragmento = getSupportFragmentManager();

        frg4_nclicks frgRecibir = (frg4_nclicks) manejadorFragmento.findFragmentById(R.id.frag_txt);

        frgRecibir.cambiarTexto(datos);
    }
}
