package com.example.lostzone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import vista.Activities.Archivos_Memoria;
import vista.Activities.Artistas_RecyclerView;
import vista.Activities.BaseRemota;
import vista.Activities.Escuchar_Fragmento_Activity;
import vista.Activities.HiloOpenWeather;
import vista.Activities.IgresarNombreApellido;
import vista.Activities.Luz;
import vista.Activities.MapsActivity;
import vista.Activities.ORM;
import vista.Activities.ProductoHelper;
import vista.Activities.Acelerometro;
import vista.Activities.Proximidad;
import vista.Activities.Suma_Activity;
import vista.Activities.VolleySW;
import vista.Activities.WebService;
import vista.Activities.logear;
import vista.Activities.reyesMagos;
import vista.Fragmentos.Fragemento_Activity;

import com.example.lostzone.ui.archivos.ArchivosFragment;
import com.example.lostzone.ui.database.DatabaseFragment;
import com.example.lostzone.ui.eventos.EventosFragment;
import com.example.lostzone.ui.interfaces.InterfacesFragment;
import com.example.lostzone.ui.maps.MapsFragment;
import com.example.lostzone.ui.sensores.SensoresFragment;
import com.example.lostzone.ui.webservice.WebserviceFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements EventosFragment.OnFragmentInteractionListener, InterfacesFragment.OnFragmentInteractionListener,
        SensoresFragment.OnFragmentInteractionListener, ArchivosFragment.OnFragmentInteractionListener, DatabaseFragment.OnFragmentInteractionListener,
        WebserviceFragment.OnFragmentInteractionListener, MapsFragment.OnFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_eventos, R.id.nav_Interfaces,
                R.id.nav_sensores, R.id.nav_archivos,R.id.nav_database, R.id.nav_webservice,
                R.id.nav_maps, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if(validaPermisos()){
            Log.e("Permisos", "Confirmo!");
        }else{
            Toast.makeText(MainActivity.this, "Niego", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) ||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},100);
        }

        return false;
    }

    private void cargarDialogoRecomendacion() {

        AlertDialog.Builder dialogo=new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},100);
                }
            }
        });
        dialogo.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Confirmo", Toast.LENGTH_SHORT).show();
            }else{
                solicitarPermisosManual();
            }
        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(MainActivity.this);
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri= Uri.fromParts("package",getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //
        Intent intencion = new Intent();
        switch (item.getItemId()){
            case R.id.opcionLogin:
                intencion = new Intent(MainActivity.this, logear.class);
                startActivity(intencion);
                break;
            case R.id.opcionSumar:
                intencion = new Intent(MainActivity.this, Suma_Activity.class);
                startActivity(intencion);
                break;
            case R.id.opcionParametros:
                intencion = new Intent(MainActivity.this, IgresarNombreApellido.class);
                startActivity(intencion);
                break;
            case R.id.opcionColores:
                intencion = new Intent(MainActivity.this, Fragemento_Activity.class);
                startActivity(intencion);
                break;
            case R.id.opcionClicks:
                intencion = new Intent(MainActivity.this, Escuchar_Fragmento_Activity.class);
                startActivity(intencion);
                break;
            case R.id.opcionDialogo:
                final Dialog dlg = new Dialog(MainActivity.this);
                dlg.setContentView(R.layout.layout_dialogo);
                final EditText txt_dlg1 = dlg.findViewById(R.id.txt_dlg1);
                final EditText txt_dlg2 = dlg.findViewById(R.id.txt_dlg2);
                Button btn_dlg = dlg.findViewById(R.id.btn_dlg);
                btn_dlg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Double suma = Double.parseDouble(txt_dlg1.getText().toString()) + Double.parseDouble(txt_dlg2.getText().toString());

                            Toast.makeText(MainActivity.this, "La suma es: " + suma, Toast.LENGTH_SHORT).show();

                            dlg.hide();
                        } catch (NumberFormatException ex){
                            Toast.makeText(MainActivity.this, "Ingresa Numeros, CERDO", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dlg.show();
                break;
            case R.id.recycler:
                intencion = new Intent(MainActivity.this, Artistas_RecyclerView.class);
                startActivity(intencion);
                break;
            /*case R.id.leerRaw:
                intencion = new Intent(MainActivity.this, MIApp2.class);
                startActivity(intencion);
                break;
            case R.id.leerArchivo:
                intencion = new Intent(MainActivity.this, ActividadMemoriaInterna.class);
                startActivity(intencion);
                break;*/
            case R.id.leerXml:
                intencion = new Intent(MainActivity.this, reyesMagos.class);
                startActivity(intencion);
                break;
            /*case R.id.opcionSD:
                intencion = new Intent(MainActivity.this, MemoriaExterna.class);
                startActivity(intencion);
                break;*/
            case R.id.opcionHelper:
                intencion = new Intent(MainActivity.this, ProductoHelper.class);
                startActivity(intencion);
                break;
            case R.id.opcionArchives:
                intencion = new Intent(MainActivity.this, Archivos_Memoria.class);
                startActivity(intencion);
                break;
            case R.id.opcionORM:
                intencion = new Intent(MainActivity.this, ORM.class);
                startActivity(intencion);
                break;
            case R.id.hiloalumno:
                intencion = new Intent(MainActivity.this, WebService.class);
                startActivity(intencion);
                break;
            case R.id.hiloclima:
                intencion = new Intent(MainActivity.this, HiloOpenWeather.class);
                startActivity(intencion);
                break;
            case R.id.opcionVolley:
                intencion = new Intent(MainActivity.this, VolleySW.class);
                startActivity(intencion);
                break;
            case R.id.opcionRemota:
                intencion = new Intent(MainActivity.this, BaseRemota.class);
                startActivity(intencion);
                break;
            case R.id.opcionMapas:
                intencion = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intencion);
                break;
            case R.id.opcionAcelerometro:
                intencion = new Intent(MainActivity.this, Acelerometro.class);
                startActivity(intencion);
                break;
            case R.id.opcionProximidad:
                intencion = new Intent(MainActivity.this, Proximidad.class);
                startActivity(intencion);
                break;
            case R.id.opcionLuz:
                intencion = new Intent(MainActivity.this, Luz.class);
                startActivity(intencion);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
