package com.el.quees.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.el.quees.Interface.Login_interface;
import com.el.quees.Presentador.Login_presentador;
import com.el.quees.R;
import com.el.quees.Utils.ProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_login extends AppCompatActivity implements Login_interface.iVista {

    @BindView(R.id.user_editext) EditText user;
    @BindView(R.id.password_editext) EditText pass;
    public Login_presentador lpresentador;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        lpresentador = new Login_presentador(this);
    }

    @OnClick (R.id.login_button)
    public void send_login(){
        String usuario = user.getText().toString();
        String password = pass.getText().toString();
        if(!usuario.isEmpty() && !password.isEmpty()){
            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    progressDialog.showProgressDialog("Iniciando sesión");
                }
            });
        }
        lpresentador.login(usuario, password);

    }



    @Override
    public void login_result() {
        progressDialog.finishDialog();
        Intent i = new Intent(this, Activity_main.class);
        startActivity(i);
    }
}
