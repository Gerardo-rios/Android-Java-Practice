package vista.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostzone.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Artista;

public class Artistas_RecyclerView extends AppCompatActivity {

    RecyclerView recyclerArtistas;

    artistaAdapter adapter;

    List<Artista> listaArtistas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artistas);
        tomarControl();
        cargarRecycler();
    }

    private void tomarControl(){
        recyclerArtistas = findViewById(R.id.recyclerArtistas);
    }

    private void cargarRecycler(){


        Artista artista1 = new Artista();
        artista1.setNombres("Luis Miguel Gallego Basteri");
        artista1.setNartistico("Luis Miguel");
        Date luismi = new Date(1970 - 1900,3,19);
        artista1.setNacido(luismi);
        artista1.setFoto(R.drawable.luismi);
        Artista artista2 = new Artista();
        artista2.setNombres("Gustav Elijah Åhr");
        artista2.setNartistico("Lil Peep");
        Date lilpe = new Date(1996 - 1900, 10, 01);
        artista2.setNacido(lilpe);
        artista2.setFoto(R.drawable.lilpep);
        Artista artista3 = new Artista();
        artista3.setNombres("Alberto Aguilera Valadez");
        artista3.setNartistico("Juan Gabriel");
        Date juanga = new Date(1950 - 1900, 0, 07);
        artista3.setNacido(juanga);
        artista3.setFoto(R.drawable.juanga);
        Artista artista4 = new Artista();
        artista4.setNombres("Billie Eilish Pirate Baird O'Connell");
        artista4.setNartistico("Billie Eilish");
        Date billie = new Date(2001 - 1900, 11,18);
        artista4.setNacido(billie);
        artista4.setFoto(R.drawable.billie);
        Artista artista5 = new Artista();
        artista5.setNombres("Michael Joseph Jackson");
        artista5.setNartistico("Michael Jackson");
        Date michaeld = new Date(1958 - 1900,7,29);
        artista5.setNacido(michaeld);
        artista5.setFoto(R.drawable.michael);
        Artista artista6 = new Artista();
        artista6.setNombres("Kendall Nicole Jenner");
        artista6.setNartistico("Kendall Jenner");
        Date kendal = new Date(1995 - 1900, 10, 03);
        artista6.setNacido(kendal);
        artista6.setFoto(R.drawable.kendall);
        Artista artista7 = new Artista();
        artista7.setNombres("Vernor Winfield MacBriare Smith IV");
        artista7.setNartistico("Mac DeMarco");
        Date mc = new Date(1990 - 1900,03,30);
        artista7.setNacido(mc);
        artista7.setFoto(R.drawable.macdemarco);

        listaArtistas = new ArrayList<>();

        listaArtistas.add(artista1);
        listaArtistas.add(artista2);
        listaArtistas.add(artista3);
        listaArtistas.add(artista4);
        listaArtistas.add(artista5);
        listaArtistas.add(artista6);
        listaArtistas.add(artista7);

        adapter = new artistaAdapter(listaArtistas);
        recyclerArtistas.setLayoutManager(new LinearLayoutManager(this));
        recyclerArtistas.setAdapter(adapter);


    }

}
