package com.el.quees.Presentador;

import com.el.quees.Interface.Login_interface;
import com.el.quees.Modelo.Login_modelo;
import com.el.quees.Vista.Activity_login;

public class Login_presentador implements Login_interface.iPresentador {

    public Login_modelo lmodelo;
    public Activity_login lvista;

    public Login_presentador(Activity_login lvista) {
        this.lvista = lvista;
        lmodelo = new Login_modelo(this);
    }

    @Override
    public void login(String u, String p) {
        lmodelo.login_post(u , p);
    }

    @Override
    public void return_login() {
        lvista.login_result();
    }
}
