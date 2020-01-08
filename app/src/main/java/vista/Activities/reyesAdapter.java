package vista.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostzone.R;

import java.util.List;

import modelo.Rey;

public class reyesAdapter extends RecyclerView.Adapter<reyesAdapter.ViewHolderReyes> {

    List<Rey> reyes;

    public reyesAdapter(List<Rey> reyes){
        this.reyes = reyes;
    }

    @NonNull
    @Override
    public ViewHolderReyes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rey, null);

        return new ViewHolderReyes(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReyes holder, int position) {

        holder.godo.setText(reyes.get(position).getGodo());
        holder.period.setText(reyes.get(position).getPeriodo());

    }

    @Override
    public int getItemCount() {
        return reyes.size();
    }

    public static class ViewHolderReyes extends RecyclerView.ViewHolder{

        TextView godo;
        TextView period;


        public ViewHolderReyes(@NonNull View itemView) {
            super(itemView);

            godo = itemView.findViewById(R.id.txt_godo);
            period = itemView.findViewById(R.id.txt_periodo);

        }
    }
}
