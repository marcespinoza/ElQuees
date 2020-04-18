package com.el.quees.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.el.quees.Interface.Main_interface;
import com.el.quees.Presentador.Main_presentador;
import com.el.quees.Vista.GlobalApplication;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Main_modelo implements Main_interface.Main_modelo {

    Main_presentador mPresentador;
    SharedPreferences sPreferences;
    Context context;

    public Main_modelo(Main_presentador mPresentador) {
        this.mPresentador = mPresentador;
        context = GlobalApplication.getContext();
        sPreferences = context.getSharedPreferences("quees", Context.MODE_PRIVATE);
    }

    @Override
    public void executeRequest(String request) {

    }

    @Override
    public void executeDataUser() {
        getDataUsers();
    }

    public void postShares(String u, String p) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token", u)
                .build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://elquees.com/api/users")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject jo = new JSONObject(jsonObject.getString("data"));
                        // Do something here
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

    public void getDataUsers() {
        String token = sPreferences.getString("token","");
        HttpUrl url = chain.request().httpUrl()
                .newBuilder()
                .addQueryParameter("api_key", mApiKey)
                .build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://elquees.com/api/me")
                .post(requestBody)
                .build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject jo = new JSONObject(jsonObject.getString("data"));
                        // Do something here
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
