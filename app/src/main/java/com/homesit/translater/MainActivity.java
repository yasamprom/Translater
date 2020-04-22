package com.homesit.translater;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button dict;
    Button test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button dict = findViewById(R.id.dict);
        final Button test = findViewById(R.id.test);
        dict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code
                Intent intent = new Intent(MainActivity.this, DictActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
