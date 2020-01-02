package vista.Activities;

import android.app.Dialog;
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

        final String name = (String) holder.dato_nombre.getText();
        final String nameArt = (String) holder.dato_nartistico.getText();
        final String wasborn = (String) holder.dato_date.getText();
        final int picture = (int) lista.get(position).getFoto();
        final String uri = (String) lista.get(position).getPath();

        holder.img_artista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dlg = new Dialog(v.getContext());
                dlg.setContentView(R.layout.artisan);
                TextView dlg_name = dlg.findViewById(R.id.lbl_nombre_artista_dlg);
                dlg_name.setText(name);
                TextView dlg_nameArtis = dlg.findViewById(R.id.lbl_nombre_artistico_dlg);
                dlg_nameArtis.setText(nameArt);
                TextView dlg_date = dlg.findViewById(R.id.lbl_date_artista_dlg);
                dlg_date.setText(wasborn);
                ImageView dlg_img = dlg.findViewById(R.id.image);
                if (uri != null){
                    dlg_img.setImageURI(Uri.parse(uri));
                } else if (picture != 0){
                    dlg_img.setImageResource(picture);
                }
                dlg.show();
            }
        });
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
