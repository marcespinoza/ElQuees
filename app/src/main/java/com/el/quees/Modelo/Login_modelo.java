package com.el.quees.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.el.quees.Interface.ApiInterface;
import com.el.quees.Interface.Login_interface;
import com.el.quees.Presentador.Login_presentador;
import com.el.quees.Utils.MyErrorMessage;
import com.el.quees.Utils.RetrofitClientInstance;
import com.el.quees.Vista.GlobalApplication;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Login_modelo implements Login_interface.iModelo {

    public Login_presentador lpresentador;
    SharedPreferences sPreferences;
    Context context;

    public Login_modelo(Login_presentador lpresentador) {
        this.lpresentador = lpresentador;
        context = GlobalApplication.getContext();
        sPreferences = context.getSharedPreferences("quees", Context.MODE_PRIVATE);
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
                        SharedPreferences.Editor editor = sPreferences.edit();
                        editor.putString("token", token);
                        editor.commit();
                        Log.i("token",""+token);
                        lpresentador.return_login();
                        // Do something here
                    } catch (JSONException e) {

                    }
                }else{
                    lpresentador.retornarMensaje("Error. Intente nuevamente");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

        });
    }
}
