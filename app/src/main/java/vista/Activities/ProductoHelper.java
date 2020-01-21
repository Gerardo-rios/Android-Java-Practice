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
import android.widget.Toast;

import com.example.lostzone.R;

import java.util.ArrayList;
import java.util.List;

import Controller.HelperProducto;
import modelo.Producto;

public class ProductoHelper extends AppCompatActivity implements View.OnClickListener {

    EditText codigo, nombre, descripcion, precio, cantidad;
    TextView datos;
    Button guardar, buscar, modificar, eliminarUno, eliminarTodo, buscarUno;
    RecyclerView recycler;
    ProductoAdapter adapter;
    HelperProducto helper = new HelperProducto(this, "Tienda", null, 1);
    Producto p;
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
        modificar = findViewById(R.id.btn_modificarProducto);
        eliminarUno = findViewById(R.id.btn_eliminarProducto);
        eliminarTodo = findViewById(R.id.btn_eliminartodoProducto);
        buscarUno = findViewById(R.id.btn_buscarproductobycode);
        buscarUno.setOnClickListener(this);
        eliminarUno.setOnClickListener(this);
        eliminarTodo.setOnClickListener(this);
        modificar.setOnClickListener(this);
        guardar.setOnClickListener(this);
        buscar.setOnClickListener(this);

    }

    private void cargarRecy(List <Producto> productes){
        adapter = new ProductoAdapter(productes);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_guardarProducto:

                p = new Producto();

                try {

                    p.setCodigo(Integer.parseInt(codigo.getText().toString()));
                    p.setNombre(nombre.getText().toString());
                    p.setDespcripcion(descripcion.getText().toString());
                    p.setPrecio(Double.parseDouble(precio.getText().toString()));
                    p.setCantidad(Integer.parseInt(cantidad.getText().toString()));

                    helper.insertar(p);

                    datos.setText("Guardado");


                } catch (NumberFormatException ex){

                    Toast.makeText(this, "No se han llenado los campos", Toast.LENGTH_SHORT).show();

                }

                break;

            case R.id.btn_buscarTodosProductos:


                cargarRecy(helper.getAll());

                break;

            case R.id.btn_modificarProducto:

                p = new Producto();

                try {

                    p.setCodigo(Integer.parseInt(codigo.getText().toString()));
                    p.setNombre(nombre.getText().toString());
                    p.setDespcripcion(descripcion.getText().toString());
                    p.setPrecio(Double.parseDouble(precio.getText().toString()));
                    p.setCantidad(Integer.parseInt(cantidad.getText().toString()));

                    helper.modificar(p);

                    datos.setText("Modificado");

                    cargarRecy(helper.getAll());

                } catch (NumberFormatException ex){

                    Toast.makeText(this, "No se han llenado los campos", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_eliminarProducto:

                String date = codigo.getText().toString();

                if (date.length() != 0){

                    helper.eliminarProductobycode(date);

                    datos.setText("Eliminado");

                    cargarRecy(helper.getAll());

                } else {

                    Toast.makeText(this, "Ingresa un codigo", Toast.LENGTH_SHORT).show();

                }

                break;

            case R.id.btn_buscarproductobycode:

                String dato = codigo.getText().toString();

                if (dato.length() != 0) {

                    cargarRecy(helper.getProductbyID(dato));

                } else {

                    Toast.makeText(this, "Ingresa un codigo", Toast.LENGTH_SHORT).show();

                }

                break;

            case R.id.btn_eliminartodoProducto:

                helper.eliminarTodo();

                datos.setText("Eliminado");

                cargarRecy(helper.getAll());

                break;
        }

    }
}
