package com.zc.medical_ai;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.zc.medical_ai.databinding.ActivityQuestinonnaireEntriesBinding;

import java.io.IOException;

public class questionnaireEntries extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityQuestinonnaireEntriesBinding binding;

    private questionService questions;
    private dataStorage answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

//        super.onCreate(savedInstanceState);
        try {
            questions = questionService.getInstance(this);
            answers = dataStorage.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(answers != null){
            for(int i = 0; i <= answers.getSize(); i++) {
                tableLayout.addView(makeNewRow(questions.getQuestion(i), answers.getAnswer(i)));
            }
        }
    }

    private TableRow makeNewRow(String q, String a){
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        tableRow.setOrientation(LinearLayout.HORIZONTAL);
        tableRow.setGravity(Gravity.LEFT);

        TextView questionTextView = new TextView(this);
        questionTextView.setText(q);
        questionTextView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView answerTextView = new TextView(this);
        answerTextView.setText(q);
        answerTextView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        tableRow.addView(questionTextView);
        tableRow.addView(answerTextView);

        return tableRow;
    }
}