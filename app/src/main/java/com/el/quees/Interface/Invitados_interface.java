package com.el.quees.Interface;

public interface Invitados_interface {

    interface Vista{}

    interface Modelo{
        void readContacts();
    }

    interface Presentador{
        void sendRequestContacts();
        void returnContacts();
    }

}


