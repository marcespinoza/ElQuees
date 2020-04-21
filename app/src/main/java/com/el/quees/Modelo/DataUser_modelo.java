package com.el.quees.Modelo;

import android.content.Context;
import android.content.SharedPreferences;

import com.el.quees.Interface.DataUser_interface;
import com.el.quees.Pojo.Acciones;
import com.el.quees.Presentador.DataUser_presentador;
import com.el.quees.Utils.ProgressDialog;
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

public class DataUser_modelo implements DataUser_interface.DataUser_modelo{

    SharedPreferences sPreferences;
    Context context;
    DataUser_interface.DataUser_presentador pDatauser;

    public DataUser_modelo(DataUser_presentador pDatauser) {
        this.pDatauser = pDatauser;
        context = GlobalApplication.getContext();
        sPreferences = context.getSharedPreferences("quees", Context.MODE_PRIVATE);
    }


    @Override
    public void sendUserData() {
        postShares();
    }

    public void postShares() {
        String token = sPreferences.getString("token","");

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://elquees.com/api/me").newBuilder();
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
                            JSONObject jo = new JSONObject(jsonObject.getString("data"));
                            String id = jo.getString("id");
                            SharedPreferences.Editor editor = sPreferences.edit();
                            editor.putString("id_usuario",id);
                            editor.commit();
                            pDatauser.returnResponse(true);
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
