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

import com.el.quees.Adapters.QuestionAdapter;
import com.el.quees.Interface.Question_interface;
import com.el.quees.Pojo.Question;
import com.el.quees.Presentador.Question_presentador;
import com.el.quees.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_questions extends Fragment implements Question_interface.iVista {

    @BindView(R.id.recycler_question)
    RecyclerView recyclerView;
    public Question_interface.iPresentador qPresentador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, view);
        qPresentador = new Question_presentador(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        qPresentador.getQuestions();
    }

    @Override
    public void showQuestions(ArrayList<Question> lQuestion) {
        QuestionAdapter qAdapter = new QuestionAdapter(lQuestion);
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(qAdapter);            }
        });
    }
}
