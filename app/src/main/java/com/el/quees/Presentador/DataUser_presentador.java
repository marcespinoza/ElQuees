package com.el.quees.Presentador;

import com.el.quees.Interface.DataUser_interface;
import com.el.quees.Modelo.DataUser_modelo;
import com.el.quees.Vista.Fragment.Fragment_Da;
import com.el.quees.Vista.Fragment.Fragment_main;

public class DataUser_presentador implements DataUser_interface.DataUser_presentador {

    DataUser_interface.DataUser_vista iVista;
    DataUser_interface.DataUser_modelo iModelo;

    public DataUser_presentador(Fragment_main iVista) {
        this.iVista = iVista;
        iModelo = new DataUser_modelo(this);
    }

    @Override
    public void requestUserData() {
        iModelo.sendUserData();
    }

    @Override
    public void returnResponse(boolean response) {
        iVista.showResponse();
    }
}
