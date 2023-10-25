package com.zc.medical_ai;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import androidx.core.app.ActivityCompat;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import speech.GoogleVoiceTypingDisabledException;
import speech.Logger;
import speech.Speech;
import speech.SpeechDelegate;
import speech.SpeechRecognitionNotAvailable;
import speech.SpeechUtil;
import speech.SupportedLanguagesListener;
import speech.TextToSpeechCallback;
import speech.UnsupportedReason;
import speech.ui.SpeechProgressView;
import android.widget.Button;


import java.util.*;

import ch.qos.logback.core.net.SyslogOutputStream;

public class MainActivity2 extends AppCompatActivity implements SpeechDelegate {
    private ArrayList<String> questionList = new ArrayList<String>();
    private dataStorage storage = dataStorage.getInstance();
    private TextView questionText;
    private ImageButton recButton;
    private final int PERMISSIONS_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        questionList.add("At what time did you go to sleep?");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        questionText = (TextView)findViewById(R.id.questionView);
        recButton = (ImageButton)findViewById(R.id.recButton);
        recButton.setOnClickListener(view -> onButtonPress());


        askQuestion(questionList.get(0).toCharArray());
    }

    private void askQuestion(char[] q){
        questionText.setText(q, 0, q.length);
    }

    private void storeAnswer(String ans){
        storage.storeAns(ans);
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }

    private void onButtonPress(){
        if (Speech.getInstance().isListening()) {
            Speech.getInstance().stopListening();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                onRecordAudioPermissionGranted();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST);
            }
        }
    }

    public void onStartOfSpeech() {
    }
    public void onSpeechRmsChanged(float value) {
        //Log.d(getClass().getSimpleName(), "Speech recognition rms is now " + value +  "dB");
    }
    public void onSpeechPartialResults(List<String> results) {
//        text.setText("");
//        for (String partial : results) {
//            text.append(partial + " ");
//        }
    }
    public void onSpeechResult(String result) {

        storeAnswer(result);
    }

    private void onRecordAudioPermissionGranted() {
        recButton.setVisibility(View.GONE);

        try {
            Speech.getInstance().stopTextToSpeech();
            Speech.getInstance().startListening(MainActivity2.this);

        } catch (SpeechRecognitionNotAvailable exc) {
            showSpeechNotSupportedDialog();

        } catch (GoogleVoiceTypingDisabledException exc) {
            showEnableGoogleVoiceTyping();
        }
    }

    private void showSpeechNotSupportedDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        SpeechUtil.redirectUserToGoogleAppOnPlayStore(MainActivity2.this);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.speech_not_available)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.no, dialogClickListener)
                .show();
    }

    private void showEnableGoogleVoiceTyping() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.enable_google_voice_typing)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                })
                .show();
    }
}