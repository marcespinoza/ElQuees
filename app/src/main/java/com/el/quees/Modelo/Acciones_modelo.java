package com.el.quees.Modelo;

import android.content.Context;
import android.content.SharedPreferences;

import com.el.quees.Interface.Acciones_interface;
import com.el.quees.Pojo.Acciones;
import com.el.quees.Presentador.Acciones_presentador;
import com.el.quees.Vista.GlobalApplication;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Acciones_modelo implements Acciones_interface.Acciones_modelo {

    Acciones_presentador mPresentador;
    SharedPreferences sPreferences;
    Context context;

    public Acciones_modelo(Acciones_presentador mPresentador) {
        this.mPresentador = mPresentador;
        context = GlobalApplication.getContext();
        sPreferences = context.getSharedPreferences("quees", Context.MODE_PRIVATE);
    }

    @Override
    public void executeAcciones() {
        getDataUsers();
    }

    public void getDataUsers() {
        String token = sPreferences.getString("token","");
        String id = sPreferences.getString("id_usuario","");

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://elquees.com/api/users/"+id+"/shares").newBuilder();
        urlBuilder.addQueryParameter("token", token);

        String url = urlBuilder.build().toString();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if(jsonObject.has("data")){
                            ArrayList<Acciones> lAcciones = new ArrayList<>();
                            JSONArray ja = new JSONArray(jsonObject.getString("data"));
                            for(int i = 0; i < ja.length(); i++){
                                Acciones acciones = new Acciones();
                                String id = ja.getJSONObject(i).getString("id");
                                String fecha = ja.getJSONObject(i).getString("created_at");
                                String amount = ja.getJSONObject(i).getString("amount");
                                String details = ja.getJSONObject(i).getString("details");
                                acciones.setId(id);
                                acciones.setCreated_at(fecha);
                                acciones.setAmount(amount);
                                acciones.setDetails(details);
                                lAcciones.add(acciones);
                            }
                            mPresentador.returnAcciones(lAcciones);
                        }else{

                        }

                    } catch (JSONException e) {

                    }
                }else{
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

        });
    }


}
