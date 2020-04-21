package com.el.quees.Interface;

public interface DataUser_interface {

    interface DataUser_modelo{
        void sendUserData();
    }

    interface DataUser_presentador{
        void requestUserData();
        void returnResponse(boolean response);
    }

    interface DataUser_vista{
         void showResponse();
    }

}
