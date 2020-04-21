package com.el.quees.Presentador;

import com.el.quees.Interface.Question_interface;
import com.el.quees.Modelo.Question_modelo;
import com.el.quees.Pojo.Question;
import com.el.quees.Vista.Fragment.Fragment_questions;

import java.util.ArrayList;

public class Question_presentador implements Question_interface.iPresentador {

    public Question_interface.iModelo qModelo;
    public Question_interface.iVista qVista;

    public Question_presentador(Fragment_questions qVista) {
        this.qVista = qVista;
        qModelo = new Question_modelo(this);
    }

    @Override
    public void getQuestions() {
        qModelo.postQuestions();
    }

    @Override
    public void returnQuestions(ArrayList<Question> lQuestion) {
        qVista.showQuestions(lQuestion);
    }
}
