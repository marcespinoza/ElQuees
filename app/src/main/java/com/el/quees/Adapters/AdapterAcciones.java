package com.el.quees.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.el.quees.Pojo.Acciones;
import com.el.quees.R;

import java.util.ArrayList;

public class AdapterAcciones extends RecyclerView.Adapter<AdapterAcciones.Accion_holder> {

    ArrayList<Acciones> lAcciones;

    public AdapterAcciones(ArrayList<Acciones> lAcciones){
        this.lAcciones = lAcciones;
    }

    @NonNull
    @Override
    public AdapterAcciones.Accion_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_accion, parent, false);
        Accion_holder accion_holder= new Accion_holder(listItem);
        return accion_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Accion_holder holder, int position) {
            Acciones acciones = lAcciones.get(position);
            holder.id.setText(acciones.getId());
            holder.fecha.setText(acciones.getCreated_at());
            holder.derechos.setText(acciones.getAmount());
            holder.detalles.setText(acciones.getDetails());
    }

    @Override
    public int getItemCount() {
        return lAcciones.size();
    }

    public static class Accion_holder extends RecyclerView.ViewHolder {

        TextView id;
        TextView fecha;
        TextView derechos;
        TextView detalles;


        public Accion_holder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_transaccion);
            fecha = itemView.findViewById(R.id.fecha_transaccion);
            derechos = itemView.findViewById(R.id.derechos);
            detalles = itemView.findViewById(R.id.detalles);
        }
    }

}
