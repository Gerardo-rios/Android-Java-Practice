package com.example.lostzone.ui.interfaces;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lostzone.MainActivity;
import com.example.lostzone.R;

import vista.Activities.Artistas_RecyclerView;
import vista.Activities.Escuchar_Fragmento_Activity;
import vista.Fragmentos.Fragemento_Activity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InterfacesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InterfacesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterfacesFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button color, clicks, dialogo, recicler;

    private OnFragmentInteractionListener mListener;

    public InterfacesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InterfacesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InterfacesFragment newInstance(String param1, String param2) {
        InterfacesFragment fragment = new InterfacesFragment();
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
        return inflater.inflate(R.layout.fragment_interfaces, container, false);
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
        color = getActivity().findViewById(R.id.btn_btncolores);
        clicks = getActivity().findViewById(R.id.btn_btncliks);
        dialogo = getActivity().findViewById(R.id.btn_btndialogo);
        recicler = getActivity().findViewById(R.id.btn_btnrecicler);
        color.setOnClickListener(this);
        clicks.setOnClickListener(this);
        dialogo.setOnClickListener(this);
        recicler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intencion = new Intent();

        switch (v.getId()){

            case R.id.btn_btncolores:
                intencion = new Intent(getContext(), Fragemento_Activity.class);
                startActivity(intencion);
                break;
            case R.id.btn_btncliks:
                intencion = new Intent(getContext(), Escuchar_Fragmento_Activity.class);
                startActivity(intencion);
                break;
            case R.id.btn_btndialogo:
                final Dialog dlg = new Dialog(getContext());
                dlg.setContentView(R.layout.layout_dialogo);
                final EditText txt_dlg1 = dlg.findViewById(R.id.txt_dlg1);
                final EditText txt_dlg2 = dlg.findViewById(R.id.txt_dlg2);
                Button btn_dlg = dlg.findViewById(R.id.btn_dlg);
                btn_dlg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Double suma = Double.parseDouble(txt_dlg1.getText().toString()) + Double.parseDouble(txt_dlg2.getText().toString());

                            Toast.makeText(getContext(), "La suma es: " + suma, Toast.LENGTH_SHORT).show();

                            dlg.hide();
                        } catch (NumberFormatException ex){
                            Toast.makeText(getContext(), "Ingresa Numeros, CERDO", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dlg.show();
                break;
            case R.id.btn_btnrecicler:
                intencion = new Intent(getContext(), Artistas_RecyclerView.class);
                startActivity(intencion);
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
