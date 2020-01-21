package vista.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lostzone.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import modelo.Artista;
import vista.Activities.MIadapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoPR.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoPR#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoPR extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //////////////////////COMPONENTEES//////////////////////////
    Button btn_leerRaw;
    TextView leerRaw;
    List<Artista> lista;
    MIadapter adapter;
    RecyclerView recycler;
    //////////////////////COMPONENTEES//////////////////////////


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentoPR() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoPR.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoPR newInstance(String param1, String param2) {
        FragmentoPR fragment = new FragmentoPR();
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
        return inflater.inflate(R.layout.fragment_fragmento_pr, container, false);
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
        tomarcontrol();
    }

    public void tomarcontrol(){
        btn_leerRaw = getActivity().findViewById(R.id.btn_leerRAW_frg);
        leerRaw = getActivity().findViewById(R.id.lbl_leerRAW_frg);
        recycler = getActivity().findViewById(R.id.recicler_raw_frg);
        btn_leerRaw.setOnClickListener(this);
    }

    private void cargarRecycler(){
        adapter = new MIadapter(lista);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        try {
            InputStream input = getResources().openRawResource(R.raw.archivo_raw);
            BufferedReader lector = new BufferedReader(new InputStreamReader(input));
            String cadena = lector.readLine();
            //leerRaw.setText(R.drawable.osama + " " + R.drawable.wazoski + " " + R.drawable.feelsbatman + " " + R.drawable.lanita + " " + R.drawable.riley + " " + R.drawable.yopuesquienmas);
            leerRaw.setText("Datos listados");
            String[] split1 = cadena.split(";");
            lista = new ArrayList<>();
            for (int i = 0; i < split1.length; i++){
                String[] attrs = split1[i].split(",");
                Artista art = new Artista();
                art.setNombres(attrs[0]);
                art.setNartistico(attrs[1]);
                art.setNacimiento(attrs[2]);
                art.setFoto(Integer.parseInt(attrs[3]));
                lista.add(art);
            }
            cargarRecycler();
        } catch (IOException e) {
            Log.e("Malario", "Recontra mal perro, no se pudo leer");
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
