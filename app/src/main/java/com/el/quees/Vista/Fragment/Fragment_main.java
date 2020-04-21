package com.el.quees.Vista.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.el.quees.Interface.Acciones_interface;
import com.el.quees.Interface.DataUser_interface;
import com.el.quees.Pojo.Acciones;
import com.el.quees.Presentador.DataUser_presentador;
import com.el.quees.R;
import com.el.quees.Utils.ProgressDialog;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class Fragment_main extends Fragment implements DataUser_interface.DataUser_vista {

    DataUser_interface.DataUser_presentador dPresentador;
    ProgressDialog pDialog;

    public Fragment_main() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        pDialog = new ProgressDialog(getContext());
        ButterKnife.bind(this, view);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dPresentador = new DataUser_presentador(this);
        request();
    }

    public void request(){
        pDialog.showProgressDialog("Leyendo datos de usuario..");
        dPresentador.requestUserData();
    }


    @Override
    public void showResponse() {
        pDialog.finishDialog();
    }
}
