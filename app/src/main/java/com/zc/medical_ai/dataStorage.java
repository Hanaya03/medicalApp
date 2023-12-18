package com.zc.medical_ai;
import java.util.*;

public class dataStorage {
    // Private constructor to prevent external instantiation
    private dataStorage() {

    }

    // Private static instance variable
    private String date;
    private Map<Integer, String> answerMap = new HashMap<Integer, String>();
    public static dataStorage instance;

    // Public static method to access the instance
    public static dataStorage getInstance() {
        if (instance == null) {
            instance = new dataStorage();
        }
        return instance;
    }

    // Other methods and properties of the Singleton class
    public void storeAns(int i, String ans) {
        answerMap.put(i, ans);
    }

    public String getAnswer(int index){
        return answerMap.get(index);
    }

    public int getSize() {
        return answerMap.size();
    }

    public  void storeDate(String d){ date = d; }

    public String getDate() { return date;}
}