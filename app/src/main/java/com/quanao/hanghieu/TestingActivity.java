package com.quanao.hanghieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestingActivity extends AppCompatActivity {

    EditText txt1,txt2,txtResult;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        txt1 = findViewById(R.id.txt1Testing);
        txt2 = findViewById(R.id.txt2Testing);
        txtResult = findViewById(R.id.resultTesting);
        btn = findViewById(R.id.btnTesting);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fileName = "emailTest";
                String content = "tianhlalop1a@gmail.com, tranminhhuy@gmail.com ,zxcas@gmail.com";

                FileOutputStream outputStream = null;
                try {
                    outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    outputStream.write(content.getBytes());
                    outputStream.close();
                    Toast.makeText(TestingActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String s = readData("emailTest");
                List<String> lst = Arrays.asList(s.split(","));
                Log.d("TAG", "onClick: "+ lst.get(0).toString());
            }
        });

    }
    private String readData(String fileName) {
        try {
            FileInputStream in = this.openFileInput(fileName);

            BufferedReader br= new BufferedReader(new InputStreamReader(in));

            StringBuffer buffer = new StringBuffer();
            String line = null;
            while((line= br.readLine())!= null)  {
                buffer.append(line).append("\n");
            }
            return buffer.toString();

        } catch (Exception e) {
            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}