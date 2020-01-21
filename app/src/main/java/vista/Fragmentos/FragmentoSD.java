package vista.Fragmentos;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lostzone.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import modelo.Artista;
import vista.Activities.DatePickerFragment;
import vista.Activities.MIadapter;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoSD.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoSD#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoSD extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //////////////////////COMPONENTEES//////////////////////////
    TextView datos;
    EditText cajaNombre, cajaApellido, cajaArtistico, nacio;
    Button guardar, buscar;
    ImageButton imagen;
    RecyclerView recycler;
    Uri imageUri;
    List<Artista> lista = new ArrayList<>();
    MIadapter adapter;
    //////////////////////COMPONENTEES//////////////////////////

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentoSD() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoSD.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoSD newInstance(String param1, String param2) {
        FragmentoSD fragment = new FragmentoSD();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_sd, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        obtenerComponentes();
    }

    private void obtenerComponentes(){
        datos = getActivity().findViewById(R.id.lbl_datosME_frg);
        cajaNombre = getActivity().findViewById(R.id.txt_NombreME_frg);
        cajaApellido = getActivity().findViewById(R.id.txt_ApellidoME_frg);
        cajaArtistico = getActivity().findViewById(R.id.txt_artisticoME_frg);
        nacio = getActivity().findViewById(R.id.txt_nacimientoME_frg);
        guardar = getActivity().findViewById(R.id.btn_guardarME_frg);
        buscar = getActivity().findViewById(R.id.btn_buscarTodosME_frg);
        recycler = getActivity().findViewById(R.id.recycler_listaME_frg);
        imagen = getActivity().findViewById(R.id.btn_imagenME_frg);
        imagen.setOnClickListener(this);
        nacio.setOnClickListener(this);
        guardar.setOnClickListener(this);
        buscar.setOnClickListener(this);
    }

    private void cargarRecycler(String datos){
        String [] artistas = datos.split(";");
        for (int i = 0; i < artistas.length; i++){
            String [] atributos = artistas[i].split(",");
            Artista art = new Artista();
            art.setNombres(atributos[0]);
            art.setNartistico(atributos[1]);
            art.setNacimiento(atributos[2]);
            art.setPath(atributos[3]);
            lista.add(art);
        }
        adapter = new MIadapter(lista);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                nacio.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void cargarImagen(){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        gallery.setType("image/");
        startActivityForResult(gallery.createChooser(gallery, "Selecciona la APP"), 10);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            imageUri = data.getData();
            imagen.setImageURI(imageUri);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_guardarME_frg:
                File ruta = Environment.getExternalStorageDirectory();
                File file = new File(ruta.getAbsoluteFile(), "archivoXs.txt");
                try {
                    OutputStreamWriter escritor = new OutputStreamWriter(new FileOutputStream(file, true));
                    escritor.write(cajaNombre.getText().toString() + " " + cajaApellido.getText().toString() + "," + cajaArtistico.getText().toString() + "," + nacio.getText().toString() + "," + imageUri.toString() + ";");
                    datos.setText("Guardado Exitosamente");
                    cajaNombre.setText(null);
                    cajaApellido.setText(null);
                    cajaArtistico.setText(null);
                    nacio.setText(null);
                    imagen.setImageURI(null);
                    escritor.close();
                } catch (Exception ex){
                    Log.e("ArchivoME", "error de escritura" + ex.getMessage());
                }

                break;
            case R.id.btn_buscarTodosME_frg:
                File path = Environment.getExternalStorageDirectory();
                File archivo = new File(path.getAbsoluteFile(), "archivoXs.txt");
                try {
                    BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
                    cargarRecycler(lector.readLine());
                    //datos.setText(lector.readLine());
                    lector.close();
                } catch (FileNotFoundException e) {
                    Log.e("ArchivoME", "error de lectura" + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.txt_nacimientoME_frg:
                showDatePickerDialog();
                break;
            case R.id.btn_imagenME_frg:
                cargarImagen();
                break;
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
