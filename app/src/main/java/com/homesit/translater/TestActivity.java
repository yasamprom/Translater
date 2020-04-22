package com.homesit.translater;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TestActivity extends AppCompatActivity {

    public static TextView task, result, center;
    public static Button ans1, ans2, ans3, ans4, next;


    public String read() {
        try {
            FileInputStream fileInputStream= openFileInput("myText.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines=bufferedReader.readLine())!=null) {
                stringBuffer.append(lines+"\n");
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map parseWords(String text){
        Map<String, String> map = new HashMap<>();
        try {
            String[] pairs = text.split("\n");
            for (int i = 0; i < pairs.length; i++) {
                if (pairs[i].split("  ").length != 2) {
                    Toast.makeText(getApplicationContext(), "Incorrect dict format", Toast.LENGTH_LONG).show();
                    return null;
                }
                map.put(pairs[i].split("  ")[0], pairs[i].split("  ")[1]);
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Incorrect dict format", Toast.LENGTH_LONG).show();
            return null;
        }
        return map;
    }
    public void correctAnswer(final Map<String, String> pairs){
        next.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);
        result.setText("Correct");
        result.setTextColor(Color.parseColor("#01DF3A"));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               createTask(pairs);
            }
        });
    }

    public void createTask(final Map<String, String> pairs){

        result.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        Random rand = new Random();
        int rand_int = rand.nextInt(pairs.size());
        //ans1.setText(rand_int);
        String key = pairs.keySet().toArray()[rand_int].toString();
        Log.e("TEST", key);
        task.setText(key);
        final String txt0, txt2, txt3, txt1;
        int i0 = rand.nextInt(pairs.keySet().size());
        txt0 = pairs.get(pairs.keySet().toArray()[i0]);
        int i1 = rand.nextInt(pairs.keySet().size());
        txt1 = pairs.get(pairs.keySet().toArray()[i1]);
        int i2 = rand.nextInt(pairs.keySet().size());
        txt2 = pairs.get(pairs.keySet().toArray()[i2]);
        int i3 = rand.nextInt(pairs.keySet().size());
        txt3 = pairs.get(pairs.keySet().toArray()[i3]);
        final Button[] btn = new Button[] {ans1, ans2, ans3, ans4};
        btn[0].setText(txt0);
        btn[1].setText(txt1);
        btn[2].setText(txt2);
        btn[3].setText(txt3);
        final String answer = pairs.get(key);
        int i_ans = rand.nextInt(4);
        btn[i_ans].setText(answer);
        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn[0].getText().equals(answer)){
                    correctAnswer(pairs);
                }
                else{
                    result.setText("wrong");
                    result.setTextColor(Color.parseColor("#FF0000"));
                    result.setVisibility(View.VISIBLE);
                }
            }
        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn[1].getText().equals(answer)){
                    correctAnswer(pairs);
                }
                else{
                    result.setText("wrong");
                    result.setTextColor(Color.parseColor("#FF0000"));
                    result.setVisibility(View.VISIBLE);
                }
            }
        });
        btn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn[2].getText().equals(answer)){
                    correctAnswer(pairs);
                }
                else{
                    result.setText("wrong");
                    result.setTextColor(Color.parseColor("#FF0000"));
                    result.setVisibility(View.VISIBLE);
                }
            }
        });
        btn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn[3].getText().equals(answer)){
                    correctAnswer(pairs);
                }
                else{
                    result.setText("wrong");
                    result.setTextColor(Color.parseColor("#FF0000"));
                    result.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        task = findViewById(R.id.task);
        result = findViewById(R.id.result);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        next = findViewById(R.id.next);
        center = findViewById(R.id.center);
        String text = read();
        Map<String, String> pairs = parseWords(text);
        if (pairs == null) {
            finish();
            return;
        }
        createTask(pairs);
    }
}
