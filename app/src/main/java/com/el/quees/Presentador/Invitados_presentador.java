package com.el.quees.Presentador;

import com.el.quees.Interface.Invitados_interface;
import com.el.quees.Modelo.Invitados_modelo;
import com.el.quees.Vista.Fragment.Fragment_invitados;

public class Invitados_presentador implements Invitados_interface.Presentador {

    public Invitados_interface.Modelo iModelo;
    public Invitados_interface.Vista iVista;

    public Invitados_presentador(Fragment_invitados iVista) {
        this.iVista = iVista;
        iModelo = new Invitados_modelo(this);
    }

    @Override
    public void sendRequestContacts() {
        iModelo.readContacts();
    }

    @Override
    public void returnContacts() {

    }
}
