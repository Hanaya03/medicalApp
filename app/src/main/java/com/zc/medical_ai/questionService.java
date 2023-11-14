package com.zc.medical_ai;
import java.util.*;
import android.content.Context;
import java.util.Scanner;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import openweathermaplib.model.common.Sys;

public class questionService {
    // Private constructor to prevent external instantiation
    private questionService(Context context) {
        main(context);
    }

    // Private static instance variable
    private Map<Integer, String> questionMap = new HashMap<Integer, String>();
    public static questionService instance;

    // Public static method to access the instance
    public static questionService getInstance(Context context) {
        if (instance == null) {
            instance = new questionService(context);
        }
        return instance;
    }

    // Other methods and properties of the Singleton class
    public void main(Context context) {
//        questionMap.put(0, "In total, how long did you sleep?");
//        questionMap.put(1, "At what time did you go to bed?");
//        questionMap.put(2, "At what time did you try to go to sleep?");
//        questionMap.put(3, "How long did it take you to fall asleep?");
//        questionMap.put(4, "How many times did you wake up, not counting your final awakening?");
//        questionMap.put(5, "In total, how long did these awakenings last?");
//        questionMap.put(6, "At what time was your final awakening?");
//        questionMap.put(7, "Did you wake up earlier than you desired?");
//        questionMap.put(8, "If yes, how many minutes?");
//        questionMap.put(9, "At what time did you get out of bed for the day?");
//        questionMap.put(10, "How would you rate the quality of your sleep?");

        StringBuilder stringBuilder = new StringBuilder();

        try {
            int i = 0;
            InputStream inputStream = context.getAssets().open("questions.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                questionMap.put(i, line);
                stringBuilder.append(line).append("\n");
                i++;
            }
            System.out.println(questionMap.size());
            for(int j = 0; j < questionMap.size(); j++)
                System.out.println(questionMap.get(j));
            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getQuestion(int index) {
        return questionMap.get(index);
    }

    public int getMapSize(){return questionMap.size();}
}

