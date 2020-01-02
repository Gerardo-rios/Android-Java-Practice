package vista.Activities;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostzone.R;

import java.util.List;

import modelo.Artista;

public class MIadapter extends RecyclerView.Adapter<MIadapter.ViewHolderArts> {

    List<Artista> lista;

    public MIadapter(List<Artista> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolderArts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artista, null);

        return new ViewHolderArts(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArts holder, int position) {
        holder.dato_nombre.setText(lista.get(position).getNombres());
        holder.dato_nartistico.setText(lista.get(position).getNartistico());
        if (lista.get(position).getPath() != null){
            holder.img_artista.setImageURI(Uri.parse(lista.get(position).getPath()));
        } else if (lista.get(position).getFoto() != 0){
            holder.img_artista.setImageResource(lista.get(position).getFoto());
        }
        holder.dato_date.setText(lista.get(position).getNacimiento());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolderArts extends RecyclerView.ViewHolder{

        TextView dato_nombre;
        TextView dato_nartistico;
        ImageView img_artista;
        TextView dato_date;

        public ViewHolderArts(@NonNull View itemView) {
            super(itemView);
            dato_nombre = itemView.findViewById(R.id.lbl_nombre_artista);
            dato_nartistico = itemView.findViewById(R.id.lbl_nombre_artistico);
            img_artista = itemView.findViewById(R.id.img_artista);
            dato_date = itemView.findViewById(R.id.lbl_date_artista);
        }
    }

}
