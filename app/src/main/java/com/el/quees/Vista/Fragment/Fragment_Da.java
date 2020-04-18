package com.el.quees.Vista.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.el.quees.Interface.Main_interface;
import com.el.quees.Presentador.Main_presentador;
import com.el.quees.R;

public class Fragment_Da extends Fragment implements Main_interface.Main_vista {

    Main_presentador mPresentador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_da, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresentador = new Main_presentador(this);
    }

    @Override
    public void showRequest() {

    }
}
