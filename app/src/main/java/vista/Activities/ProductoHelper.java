package vista.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lostzone.R;

import java.util.ArrayList;
import java.util.List;

import Controller.HelperProducto;
import modelo.Producto;

public class ProductoHelper extends AppCompatActivity implements View.OnClickListener {

    EditText codigo, nombre, descripcion, precio, cantidad;
    TextView datos;
    Button guardar, buscar;
    RecyclerView recycler;
    ProductoAdapter adapter;
    HelperProducto helper = new HelperProducto(this, "Tienda", null, 1);
    List<Producto> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_helper);
        obtenerComponents();
    }

    private void obtenerComponents(){

        codigo = findViewById(R.id.txt_codigo);
        nombre = findViewById(R.id.txt_nombre_producto);
        descripcion = findViewById(R.id.txt_Descripcion);
        precio = findViewById(R.id.txt_precio);
        cantidad = findViewById(R.id.txt_cantidad);
        datos = findViewById(R.id.lbl_datosProductos);
        guardar = findViewById(R.id.btn_guardarProducto);
        buscar = findViewById(R.id.btn_buscarTodosProductos);
        recycler = findViewById(R.id.recycler_productos);
        guardar.setOnClickListener(this);
        buscar.setOnClickListener(this);

    }

    private void cargarRecy(){
        adapter = new ProductoAdapter(lista);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_guardarProducto:

                Producto p = new Producto();
                p.setCodigo(Integer.parseInt(codigo.getText().toString()));
                p.setNombre(nombre.getText().toString());
                p.setDespcripcion(descripcion.getText().toString());
                p.setPrecio(Double.parseDouble(precio.getText().toString()));
                p.setCantidad(Integer.parseInt(cantidad.getText().toString()));

                helper.insertar(p);
                lista.add(p);

                datos.setText("Guardado");

                break;
            case R.id.btn_buscarTodosProductos:

                helper.getAll();
                cargarRecy();

                break;

        }

    }
}
