package com.el.quees.Modelo;

import android.util.Log;

import com.el.quees.Interface.ApiInterface;
import com.el.quees.Interface.Login_interface;
import com.el.quees.Presentador.Login_presentador;
import com.el.quees.Utils.MyErrorMessage;
import com.el.quees.Utils.RetrofitClientInstance;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class Login_modelo implements Login_interface.iModelo {

    public Login_presentador lpresentador;

    public Login_modelo(Login_presentador lpresentador) {
        this.lpresentador = lpresentador;
    }

    @Override
    public void login_post(String u, String p) {
        postLogin(u, p);
    }

    public void postLogin(String u, String p) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", u)
                .addFormDataPart("password", p)
                .build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://elquees.com/api/login")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject jo = new JSONObject(jsonObject.getString("data"));
                        String token = jo.getString("access_token");
                        lpresentador.return_login();
                        // Do something here
                    } catch (JSONException e) {

                    }

                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

        });
    }
}
