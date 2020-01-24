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

                Vehiculo ve = new Vehiculo();
                ve.setPlaca(placa.getText().toString());
                ve.setMarca(marca.getText().toString());
                ve.setModelo(model.getText().toString());
                ve.setAnio(ano.getText().toString());
                ve.save();

                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();

                placa.setText(null);
                marca.setText(null);
                model.setText(null);
                ano.setText(null);

                break;

            case R.id.btn_BuscarCar:

                adapter = new ormAdapter(Vehiculo.getAllCarro());
                recycler.setLayoutManager(new LinearLayoutManager(this));
                recycler.setAdapter(adapter);

                break;


            case R.id.btn_BuscarPlaca:

                Vehiculo automovil = Vehiculo.getCarroPlaca(placa.getText().toString());

                List<Vehiculo> auto = new ArrayList<>();

                auto.add(automovil);

                adapter = new ormAdapter(auto);
                recycler.setLayoutManager(new LinearLayoutManager(this));
                recycler.setAdapter(adapter);

                break;

            case R.id.btn_EliminarCar:

                Vehiculo.deleteCar(placa.getText().toString());

                adapter = new ormAdapter(Vehiculo.getAllCarro());
                recycler.setLayoutManager(new LinearLayoutManager(this));
                recycler.setAdapter(adapter);

                break;

            case R.id.btn_ModificarCar:

                Vehiculo carre = Vehiculo.getCarroPlaca(placa.getText().toString());

                carre.setMarca(marca.getText().toString());
                carre.setModelo(model.getText().toString());
                carre.setAnio(ano.getText().toString());

                carre.save();

                adapter = new ormAdapter(Vehiculo.getAllCarro());
                recycler.setLayoutManager(new LinearLayoutManager(this));
                recycler.setAdapter(adapter);

                break;
        }


    }
}
