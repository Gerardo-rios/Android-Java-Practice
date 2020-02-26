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

import modelo.Usuario;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolderUsers>{

    List<Usuario> lista;

    public userAdapter(List<Usuario> usuarios){
        this.lista = usuarios;
    }

    @NonNull
    @Override
    public ViewHolderUsers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vision = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, null);

        return new ViewHolderUsers(vision);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUsers holder, int position) {

        holder.cedula.setText(lista.get(position).getDni());
        holder.nombre.setText(lista.get(position).getNombre());
        holder.profesion.setText(lista.get(position).getProfesion());
        //if (!lista.get(position).getFoto().equals("")){
            //holder.foto.setImageURI();
        //}
        //holder.foto.setImageURI(Uri.parse(lista.get(position).getFoto()));


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolderUsers extends RecyclerView.ViewHolder {

        TextView cedula, nombre, profesion;
        ImageView foto;

        public ViewHolderUsers(@NonNull View itemView) {
            super(itemView);
            cedula = itemView.findViewById(R.id.lbl_dni_user);
            nombre = itemView.findViewById(R.id.lbl_nombre_user);
            profesion = itemView.findViewById(R.id.lbl_profesion_user);
            foto = itemView.findViewById(R.id.img_user);
        }

    }

}
