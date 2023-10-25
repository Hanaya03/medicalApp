package com.zc.medical_ai;
import java.util.*;

public class dataStorage {
    // Private constructor to prevent external instantiation
    private dataStorage() {

    }

    // Private static instance variable
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
    public void storeAns(String ans) {
        answerMap.put(0, ans);
    }

    public String getAnswer(int index){
        return answerMap.get(index);
    }
}

