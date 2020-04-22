package com.homesit.translater;

import android.content.Context;
import android.content.SharedPreferences;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DictActivity extends AppCompatActivity {

    TextView dict_edit;
    Button save_button;
    SharedPreferences sPref;

    /*void saveText(String text) {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("SavedText", text);
        ed.commit();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }*/

    public void write(String text) {
         try {
            FileOutputStream fileOutputStream = openFileOutput("myText.txt",MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Text Saved",Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict);
        final TextView dict_edit = findViewById(R.id.dict_edit);
        final Button save_button = findViewById(R.id.save_button);
        dict_edit.setText(read());
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = dict_edit.getText().toString();
                write(text);
            }
        });
    }
}
