package com.zc.medical_ai;
import java.util.*;

public class questionService {
    // Private constructor to prevent external instantiation
    private questionService() {

    }

    // Private static instance variable
    private Map<Integer, String> questionMap = new HashMap<Integer, String>();
    public static questionService instance;

    // Public static method to access the instance
    public static questionService getInstance() {
        if (instance == null) {
            instance = new questionService();
        }
        return instance;
    }

    // Other methods and properties of the Singleton class
    public String getQuestion(int index){
        return questionMap.get(index);
    }
}

