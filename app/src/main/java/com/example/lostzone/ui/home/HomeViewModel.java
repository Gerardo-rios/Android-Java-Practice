package com.example.lostzone.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("La aplicacion que ahora se esta ejecutando en tu dispositivo movil,\n" +
                "        es una aplicacion que fue realizada durante las clases de programacion avanzada\n" +
                "        con el fin de aprender programacion de aplicaciones moviles en ANDROID STUDIO");
    }

    public LiveData<String> getText() {
        return mText;
    }
}