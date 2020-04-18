package com.el.quees.Presentador;

import androidx.fragment.app.Fragment;

import com.el.quees.Interface.Main_interface;
import com.el.quees.Modelo.Main_modelo;
import com.el.quees.Vista.Fragment.Fragment_Da;

public class Main_presentador implements Main_interface.Main_presentador {

    Main_modelo mModelo;
    Fragment mVista;

    public Main_presentador(Fragment mVista) {
        this.mVista = mVista;
        mModelo = new Main_modelo(this);
    }

    @Override
    public void sendRequest(String request) {
        mModelo.executeRequest(request);
    }

    @Override
    public void getDataUsers() {
        mModelo.executeDataUser();
    }

    @Override
    public void returnRequest() {

    }
}
