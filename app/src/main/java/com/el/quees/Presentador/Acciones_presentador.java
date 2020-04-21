package com.el.quees.Presentador;

import com.el.quees.Interface.Acciones_interface;
import com.el.quees.Modelo.Acciones_modelo;
import com.el.quees.Pojo.Acciones;
import com.el.quees.Vista.Fragment.Fragment_Da;
import com.el.quees.Vista.Fragment.Fragment_main;

import java.util.ArrayList;

public class Acciones_presentador implements Acciones_interface.Acciones_presentador {

    com.el.quees.Modelo.Acciones_modelo mModelo;
    Fragment_Da mVista;

    public Acciones_presentador(Fragment_Da mVista) {
        this.mVista = mVista;
        mModelo = new com.el.quees.Modelo.Acciones_modelo(this);
    }


    @Override
    public void sendAcciones() {
        mModelo.getDataUsers();
    }

    @Override
    public void returnAcciones(ArrayList<Acciones> lAcciones) {
        mVista.showAcciones(lAcciones);
    }
}
