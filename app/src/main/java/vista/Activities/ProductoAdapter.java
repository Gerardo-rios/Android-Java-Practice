package vista.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostzone.R;

import java.util.List;

import modelo.Producto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProductos> {

    List<Producto> productos;

    public ProductoAdapter(List<Producto> prod){
        this.productos = prod;
    }

    @NonNull
    @Override
    public ViewHolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, null);

        return new ViewHolderProductos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProductos holder, int position) {

        holder.codigo.setText(String.valueOf(productos.get(position).getCodigo()));
        holder.nombre.setText(productos.get(position).getNombre());
        holder.descripcion.setText(productos.get(position).getDespcripcion());
        holder.precio.setText(productos.get(position).getPrecio().toString());
        holder.cantidad.setText(String.valueOf(productos.get(position).getCantidad()));

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }


    public static class ViewHolderProductos extends RecyclerView.ViewHolder{

        TextView codigo, nombre, descripcion, precio, cantidad;

        public ViewHolderProductos(@NonNull View itemView) {
            super(itemView);

            codigo = itemView.findViewById(R.id.txt_productoCode);
            nombre = itemView.findViewById(R.id.txt_ProductoName);
            descripcion = itemView.findViewById(R.id.txt_ProductoDescription);
            precio = itemView.findViewById(R.id.txt_ProductoCost);
            cantidad = itemView.findViewById(R.id.txt_ProductoExistencies);

        }
    }
}
