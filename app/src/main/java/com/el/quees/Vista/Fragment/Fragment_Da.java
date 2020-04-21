package com.el.quees.Vista.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.el.quees.Adapters.AdapterAcciones;
import com.el.quees.Interface.Acciones_interface;
import com.el.quees.Pojo.Acciones;
import com.el.quees.Presentador.Acciones_presentador;
import com.el.quees.R;
import com.el.quees.Utils.ProgressDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Da extends Fragment implements Acciones_interface.Acciones_vista {

    Acciones_presentador mPresentador;
    @BindView(R.id.recycler_acciones)
    RecyclerView recyclerView;
    ProgressDialog pDialog;

    public Fragment_Da() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_da, container, false);
        ButterKnife.bind(this, view);
        pDialog = new ProgressDialog(getContext());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresentador = new Acciones_presentador(this);
        getDerechosAcciones();
    }

    public void getDerechosAcciones(){
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
                pDialog.showProgressDialog("Obteniendo datos");
            }
        });
        mPresentador.sendAcciones();
    }

    @Override
    public void showAcciones(ArrayList<Acciones> lAcciones) {
        pDialog.finishDialog();
        AdapterAcciones aAcciones = new AdapterAcciones(lAcciones);
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(aAcciones);            }
        });

    }
}
