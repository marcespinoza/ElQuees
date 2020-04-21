package com.el.quees.Interface;

import com.el.quees.Pojo.Question;

import java.util.ArrayList;

public interface Question_interface {

    interface iModelo{
        void postQuestions();
    }

    interface iPresentador{
        void getQuestions();
        void returnQuestions(ArrayList<Question> lQuestion);
    }

    interface iVista{
        void showQuestions(ArrayList<Question> lQuestion);
    }

}
