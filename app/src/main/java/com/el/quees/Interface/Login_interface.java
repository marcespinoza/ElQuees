package com.el.quees.Interface;

public interface Login_interface  {

    interface iModelo{
        void login_post(String u, String p);
    }

    interface iPresentador{
        void login(String u, String p);
        void return_login();
    }

    interface iVista{
        void login_result();
    }

}
