package com.zc.medical_ai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class questionnaireHistory extends AppCompatActivity {
    private LinearLayout linearLayout;
    private questionService questions;
    private dataStorage answers;

    questionnaireEntry entryFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_history);

        linearLayout = findViewById(R.id.linearLayout);
        try {
            questions = questionService.getInstance(this);
            answers = dataStorage.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("is answers null? " + answers);
        if(answers != null){
            System.out.println("not null");
            makeNewRow("entry " + answers.getDate());
            makeNewRow("entry " + answers.getDate());
        }

        entryFormat = new questionnaireEntry();
    }

    private void makeNewRow(String s){
        LinearLayout newRow = new LinearLayout(this);
        newRow.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        newRow.setOrientation(LinearLayout.HORIZONTAL);
        newRow.setGravity(Gravity.CENTER);

        Button buttonView = new Button(this);
        buttonView.setText(s);
        buttonView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        newRow.addView(buttonView);

        buttonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Click! Switching to questionnaireEntry");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                System.out.println("before transaction.replace");
                transaction.replace(R.id.fragment_container, entryFormat);
                transaction.addToBackStack(null); // Optional: add to back stack
                transaction.commit();
            }
        });

        linearLayout.addView(newRow);
    }
}