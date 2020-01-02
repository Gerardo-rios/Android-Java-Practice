package vista.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lostzone.R;
import modelo.Comunicador;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frg3_boton.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frg3_boton#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg3_boton extends Fragment implements View.OnClickListener{

    Button boton;
    int contador = 0;
    //TextView txt_view; este es con el metodo mas facil, vite
    Comunicador comunicador;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frg3_boton() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg3_boton.
     */
    // TODO: Rename and change types and number of parameters
    public static frg3_boton newInstance(String param1, String param2) {
        frg3_boton fragment = new frg3_boton();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        comunicador = (Comunicador) getActivity();

        boton = (Button) getActivity().findViewById(R.id.btn_click);
        boton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        contador++;

        if (contador == 200){
            Toast.makeText(getContext(), "Ganaste Desocupado RCTM", Toast.LENGTH_SHORT).show();
        } else if (contador == 300){
            Toast.makeText(getContext(), "FUERA MRD!!", Toast.LENGTH_SHORT).show();
            contador = 0;
        }

        comunicador.responder(contador + "  " + "clicks");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.fragment_frg3_boton, container, false);

        /*forma con contenedores y mas ez

        boton = (Button) vista.findViewById(R.id.btn_click);

        boton.setOnClickListener(new View.OnClickListener() {
            int nclicks = 0;
            @Override
            public void onClick(View v) {

                txt_view = (TextView) getActivity().findViewById(R.id.txt_nclicks);

                if (nclicks == 200){
                    Toast.makeText(getContext(), "Ganaste desocupado rctm", Toast.LENGTH_SHORT).show();
                    nclicks = 0;
                } else {
                    nclicks = nclicks + 1;
                }

                txt_view.setText(String.valueOf(nclicks) +  "   "  + "clicks");

            }
        }); */

        return vista;
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
