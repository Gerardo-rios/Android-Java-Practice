package com.example.lostzone.ui.webservice;

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
import android.widget.Toast;

import com.example.lostzone.R;

import vista.Activities.BaseRVolley;
import vista.Activities.BaseRemota;
import vista.Activities.VolleySW;
import vista.Activities.WebService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WebserviceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WebserviceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebserviceFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button hilo, volley, bdhilo, bdvolly;

    private OnFragmentInteractionListener mListener;

    public WebserviceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebserviceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebserviceFragment newInstance(String param1, String param2) {
        WebserviceFragment fragment = new WebserviceFragment();
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
        return inflater.inflate(R.layout.fragment_webservice, container, false);
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
        hilo = getActivity().findViewById(R.id.button_hilos);
        volley = getActivity().findViewById(R.id.button_volley);
        bdhilo = getActivity().findViewById(R.id.button_bdhilos);
        bdvolly = getActivity().findViewById(R.id.button_bdvolley);
        hilo.setOnClickListener(this);
        volley.setOnClickListener(this);
        bdvolly.setOnClickListener(this);
        bdhilo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intencion = new Intent();

        switch (v.getId()) {

            case R.id.button_hilos:
                intencion = new Intent(getContext(), WebService.class);
                startActivity(intencion);
                break;
            case R.id.button_volley:
                intencion = new Intent(getContext(), VolleySW.class);
                startActivity(intencion);
                break;
            case R.id.button_bdhilos:
                intencion = new Intent(getContext(), BaseRemota.class);
                startActivity(intencion);
                break;
            case R.id.button_bdvolley:
                intencion = new Intent(getContext(), BaseRVolley.class);
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
