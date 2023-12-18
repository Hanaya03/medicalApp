package com.zc.medical_ai;
import java.util.*;
import java.lang.*;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.content.Context;
import android.widget.Toast;

import speech.Logger;
import speech.Speech;

public class ttsService {
    // Private constructor to prevent external instantiation
    private ttsService(Context context) {
        ctx = context;
        mTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                configTTS();
            }
        });
    }

    // Private static instance variable
    private static Context ctx;
    private TextToSpeech mTTS;
    private static boolean TTSready = false;


//    private TextToSpeech.OnInitListener mTttsInitListener = new TextToSpeech.OnInitListener() {
//        @Override
//        public void onInit(final int status) {
//            switch (status) {
//                case TextToSpeech.SUCCESS:
//                    System.out.println("TextToSpeech engine successfully started");
//                    break;
//
//                case TextToSpeech.ERROR:
//                    System.out.println("Error while initializing TextToSpeech engine!");
//                    break;
//
//                default:
//                    System.out.println("Unknown TextToSpeech status: " + status);
//                    break;
//            }
//        }
//    };
    public static ttsService instance;

    // Public static method to access the instance
    public static ttsService getInstance(Context context) {
        if (instance == null) {
            instance = new ttsService(context);
        }
        return instance;
    }

    // Other methods and properties of the Singleton class
    private void configTTS() {
        Toast.makeText(ctx, "supports " + mTTS.isLanguageAvailable(Locale.getDefault()), Toast.LENGTH_LONG).show();
        int available = mTTS.isLanguageAvailable(Locale.getDefault());
        if( available != TextToSpeech.LANG_MISSING_DATA
                && available != TextToSpeech.LANG_NOT_SUPPORTED ){
            mTTS.setLanguage(Locale.getDefault());
            TTSready = true;
            System.out.println("successfully initialized");

        } else {
            System.out.println("tts not available");
            /** TODO SAVE */
        }
    }

    public void speakSentence(String sentence){
        System.out.println("speaking rn");

        mTTS.speak(sentence, TextToSpeech.QUEUE_ADD, null,null);
    }

    public boolean isTTSReady(){
        return TTSready;
    }
}
