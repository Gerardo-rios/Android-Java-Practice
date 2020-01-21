package vista.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lostzone.R;

import vista.Fragmentos.FragmentoMI;
import vista.Fragmentos.FragmentoPR;
import vista.Fragmentos.FragmentoSD;

public class Archivos_Memoria extends AppCompatActivity implements FragmentoMI.OnFragmentInteractionListener, FragmentoSD.OnFragmentInteractionListener, FragmentoPR.OnFragmentInteractionListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivos__memoria);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_buttons, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        FragmentTransaction transa = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){

            case R.id.action_MI:
                FragmentoMI fragmentMI = new FragmentoMI();
                transa.replace(R.id.frame_archivos, fragmentMI);
                transa.commit();

                break;
            case R.id.action_SD:
                FragmentoSD fragmentSD = new FragmentoSD();
                transa.replace(R.id.frame_archivos, fragmentSD);
                transa.commit();

                break;
            case R.id.action_programa:
                FragmentoPR fragmentRAW = new FragmentoPR();
                transa.replace(R.id.frame_archivos, fragmentRAW);
                transa.commit();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
