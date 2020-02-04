package vista.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostzone.R;

import java.util.List;

import modelo.Alumno;

public class SWadapter extends RecyclerView.Adapter<SWadapter.ViewHolderAlumnos>{

    List<Alumno> lista;

    public SWadapter(List<Alumno> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolderAlumnos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, null);

        return new ViewHolderAlumnos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAlumnos holder, int position) {

        holder.ida.setText(lista.get(position).getId());
        holder.dire.setText(lista.get(position).getDireccion());
        holder.nom.setText(lista.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolderAlumnos extends RecyclerView.ViewHolder{

        TextView ida, nom, dire;

        public ViewHolderAlumnos(@NonNull View itemView) {
            super(itemView);
            ida = itemView.findViewById(R.id.lbl_idA);
            nom = itemView.findViewById(R.id.lbl_nombreA);
            dire = itemView.findViewById(R.id.lbl_direccionA);
        }
    }

}
