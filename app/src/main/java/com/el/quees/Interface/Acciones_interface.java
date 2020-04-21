package com.el.quees.Interface;

import com.el.quees.Pojo.Acciones;

import java.util.ArrayList;

public interface Acciones_interface {

    interface Acciones_modelo{
        void executeAcciones();
    }

    interface Acciones_presentador{
        void sendAcciones();
        void returnAcciones(ArrayList<Acciones> lAcciones);
    }

    interface Acciones_vista{
        void showAcciones(ArrayList<Acciones> lAcciones);
    }

}
