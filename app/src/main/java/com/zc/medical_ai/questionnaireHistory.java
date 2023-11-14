package com.zc.medical_ai;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.zc.medical_ai.databinding.ActivityQuestionnaireHistoryBinding;

public class questionnaireHistory extends AppCompatActivity {
    private dataStorage storage = dataStorage.getInstance();
    private questionService questions;

    private TableLayout tl;

    private AppBarConfiguration appBarConfiguration;
    private ActivityQuestionnaireHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.getApplicationContext() != null) {
            System.out.println("context is not null");
            questions = questionService.getInstance(this.getApplicationContext());
        }
    }

    private void fillTable(){
        if(storage.getAnswer(0) != null) {
            for (int i = 0; i < storage.getSize(); i++) {

            }
        }
    }

    private void Row(String text){

    }
}