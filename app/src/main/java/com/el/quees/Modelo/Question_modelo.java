package com.el.quees.Modelo;

import android.content.Context;
import android.content.SharedPreferences;

import com.el.quees.Interface.Question_interface;
import com.el.quees.Pojo.Question;
import com.el.quees.Presentador.Question_presentador;
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
import okhttp3.OkHttpClient;

public class Question_modelo implements Question_interface.iModelo{

    public Question_interface.iPresentador qPresentador;
    Context context;
    SharedPreferences sPreferences;

    public Question_modelo(Question_presentador qPresentador) {
        this.qPresentador = qPresentador;
        context = GlobalApplication.getContext();
        sPreferences = context.getSharedPreferences("quees", Context.MODE_PRIVATE);
    }

    @Override
    public void postQuestions() {
        postShares();
    }

    public void postShares() {
        String token = sPreferences.getString("token","");

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://elquees.com/api/questions").newBuilder();
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
                                ArrayList<Question> lQuestion = new ArrayList<>();
                                JSONArray ja = new JSONArray(jsonObject.getString("data"));
                                for(int i = 0; i < ja.length(); i++){
                                    Question question = new Question();
                                    String title = ja.getJSONObject(i).getString("title");
                                    String body = ja.getJSONObject(i).getString("body");
                                    question.setTitle(title);
                                    question.setBody(body);
                                    lQuestion.add(question);
                                }
                                qPresentador.returnQuestions(lQuestion);
                            }else{

                            }

                    } catch (JSONException e) {}


                }else{
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

        });
    }


}
