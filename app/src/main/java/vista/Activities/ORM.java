package vista.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostzone.R;

import java.util.ArrayList;
import java.util.List;

import modelo.Vehiculo;

public class ORM extends AppCompatActivity implements View.OnClickListener {


    EditText placa, marca, model, ano;
    Button agregar, eliminar, modificar, buscarCarro, buscarPlaca;
    RecyclerView recycler;
    ormAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orm);
        control();
    }

    public void control(){
        placa = findViewById(R.id.txt_Placa);
        marca = findViewById(R.id.txt_Marca);
        model = findViewById(R.id.txt_Modelo);
        ano = findViewById(R.id.txt_Anio);

        agregar = findViewById(R.id.btn_AgregarCar);
        eliminar = findViewById(R.id.btn_EliminarCar);
        modificar = findViewById(R.id.btn_ModificarCar);
        buscarCarro = findViewById(R.id.btn_BuscarCar);
        buscarPlaca = findViewById(R.id.btn_BuscarPlaca);

        recycler = findViewById(R.id.recycler_car);

        agregar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        modificar.setOnClickListener(this);
        buscarCarro.setOnClickListener(this);
        buscarPlaca.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_AgregarCar:

                String plaque = placa.getText().toString();
                String marque = marca.getText().toString();
                String modele = model.getText().toString();
                String ane = ano.getText().toString();

                if (plaque.equals("") || marque.equals("") || modele.equals("") || ane.equals("")){

                    Toast.makeText(this, "INGRESA DATOS, PUERCO", Toast.LENGTH_SHORT).show();

                } else{
                    Vehiculo ve = new Vehiculo();
                    ve.setPlaca(plaque);
                    ve.setMarca(marque);
                    ve.setModelo(modele);
                    ve.setAnio(ane);
                    ve.save();

                    Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();

                    placa.setText(null);
                    marca.setText(null);
                    model.setText(null);
                    ano.setText(null);

                }

                break;

            case R.id.btn_BuscarCar:

                adapter = new ormAdapter(Vehiculo.getAllCarro());
                recycler.setLayoutManager(new LinearLayoutManager(this));
                recycler.setAdapter(adapter);

                break;


            case R.id.btn_BuscarPlaca:

                String plaquina = placa.getText().toString();

                if (!plaquina.equals("")){

                    Vehiculo automovil = Vehiculo.getCarroPlaca(plaquina);

                    List<Vehiculo> auto = new ArrayList<>();

                    auto.add(automovil);

                    adapter = new ormAdapter(auto);
                    recycler.setLayoutManager(new LinearLayoutManager(this));
                    recycler.setAdapter(adapter);

                } else {

                    Toast.makeText(this, "INGRESA PLACA, CERDO", Toast.LENGTH_SHORT).show();

                }

                break;

            case R.id.btn_EliminarCar:

                String claca = placa.getText().toString();

                if (claca.equals("")){
                    Toast.makeText(this, "INGRESA PLACA, PUERCO", Toast.LENGTH_SHORT).show();
                } else{
                    Vehiculo.deleteCar(claca);

                    adapter = new ormAdapter(Vehiculo.getAllCarro());
                    recycler.setLayoutManager(new LinearLayoutManager(this));
                    recycler.setAdapter(adapter);
                }

                break;

            case R.id.btn_ModificarCar:

                String plaquicina = placa.getText().toString();
                Vehiculo carre = null;
                if (!plaquicina.equals("")){
                    carre = Vehiculo.getCarroPlaca(placa.getText().toString());
                } else {
                    Toast.makeText(this, "INGRESA PLACA, PUERCO", Toast.LENGTH_SHORT).show();
                }
                String marcina = marca.getText().toString();
                String modelsino = model.getText().toString();
                String anino = ano.getText().toString();
                if (carre != null){

                    if (marcina.equals("") || modelsino.equals("") || anino.equals("")){
                        Toast.makeText(this, "No puedes modificar con datos vacios, warro", Toast.LENGTH_SHORT).show();
                    } else{
                        carre.setMarca(marcina);
                        carre.setModelo(modelsino);
                        carre.setAnio(anino);

                        carre.save();

                        adapter = new ormAdapter(Vehiculo.getAllCarro());
                        recycler.setLayoutManager(new LinearLayoutManager(this));
                        recycler.setAdapter(adapter);

                    }

                } else {

                    Toast.makeText(this, "No existe ese registro", Toast.LENGTH_SHORT).show();

                }

                break;
        }


    }
}
