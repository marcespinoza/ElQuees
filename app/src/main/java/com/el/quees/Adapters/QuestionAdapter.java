package com.el.quees.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.el.quees.Pojo.Question;
import com.el.quees.R;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    ArrayList<Question> lQuestions;

    public QuestionAdapter(ArrayList<Question> lQuestions) {
        this.lQuestions = lQuestions;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_question, parent, false);
        QuestionHolder questionHolder = new QuestionHolder(listItem);
        return questionHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
            Question question = lQuestions.get(position);
            holder.title.setText(question.getTitle());
            holder.body.setText(question.getBody());
    }

    @Override
    public int getItemCount() {
        return lQuestions.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView body;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }

}
