package com.kokonetworks.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etRandom;
    Button buttonGenerate;
    ListView listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setEventListeners();
    }

    public static int fibbonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibbonacci(n - 1) + fibbonacci(n - 2);
        }
    }

    List<String> random(int n) {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            items.add(String.valueOf(fibbonacci(i)));
        }
        return items;
    }

    void bindViews() {
        etRandom = findViewById(R.id.etRandom);
        buttonGenerate = findViewById(R.id.buttonGenerate);
        listItems = findViewById(R.id.listItems);
    }

    void setEventListeners() {
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String limit = etRandom.getText().toString();
                List<String> items = random(Integer.parseInt(limit));

                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), items);
                listItems.setAdapter(adapter);
            }
        });
    }
}
