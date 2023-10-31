package com.zc.medical_ai;
import java.util.*;
import java.util.Scanner;
import java.io.File;

public class questionService {
    // Private constructor to prevent external instantiation
    private questionService() {
        main();
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
    public void main() {
        try{
            int i = 0;
            System.out.println("trying to find file");
            File questionFile = new File("./questions.txt");
            System.out.println(questionFile);
            System.out.println("file found");
            System.out.println("trying to start scanner");
            Scanner read = new Scanner(questionFile);
            System.out.println("scanner made");
            System.out.println("trying to read file");
            while(read.hasNextLine()){
                questionMap.put(i, read.nextLine());
            }
            System.out.println("read successfully");
            read.close();
        } catch(java.io.FileNotFoundException e) {
            System.out.println("there was an error");
        }
    }

    public String getQuestion(int index){
        return questionMap.get(index);
    }
}

