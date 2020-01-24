package vista.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostzone.R;

import java.util.List;

import modelo.Vehiculo;

public class ormAdapter extends RecyclerView.Adapter<ormAdapter.ViewHolderAutomoviles>{

    List<Vehiculo> automoviles;

    public ormAdapter(List<Vehiculo> autos){
        this.automoviles = autos;
    }

    @NonNull
    @Override
    public ViewHolderAutomoviles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, null);

        return new ViewHolderAutomoviles(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAutomoviles holder, int position) {

        holder.placa.setText(automoviles.get(position).getPlaca());
        holder.model.setText(automoviles.get(position).getModelo());
        holder.marca.setText(automoviles.get(position).getMarca());
        holder.ano.setText(String.valueOf(automoviles.get(position).getAnio()));

    }

    @Override
    public int getItemCount() {
        return automoviles.size();
    }

    public static class ViewHolderAutomoviles extends RecyclerView.ViewHolder{


        TextView placa, model, marca, ano;

        public ViewHolderAutomoviles(@NonNull View itemView) {
            super(itemView);

            placa = itemView.findViewById(R.id.txt_carPlaca);
            model = itemView.findViewById(R.id.txt_CarModelo);
            marca = itemView.findViewById(R.id.txt_CarMarca);
            ano = itemView.findViewById(R.id.txt_CarAnio);

        }

    }

}
