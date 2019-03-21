package com.otikev.sequence;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.kokonetworks.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends androidx.activity.ComponentActivity {

    EditText etRandom;
    Button buttonGenerate;
    RecyclerView listViewSequence;

    CustomAdapter customAdapter;

    Fibonacci fibonacci = new Fibonacci();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setEventListeners();
    }

    void bindViews() {
        etRandom = findViewById(R.id.etRandom);
        buttonGenerate = findViewById(R.id.buttonGenerate);
        listViewSequence = findViewById(R.id.listViewSequence);
        listViewSequence.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        listViewSequence.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    void setEventListeners() {
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInProgress();
                clearItems();
                final String limit = etRandom.getText().toString();
                if (!limit.isEmpty()) {
                    hideKeyboard();
                    generateList(limit);
                }
            }
        });
    }

    void clearItems() {
        if(customAdapter!= null){
            customAdapter.setDataset(new ArrayList<Long>());
        }
    }

    void showInProgress() {
        buttonGenerate.setEnabled(false);
        buttonGenerate.setText(getString(R.string.generating));
    }

    void enableGenerateButton() {
        buttonGenerate.setEnabled(true);
        buttonGenerate.setText(getString(R.string.generate_sequence));
    }

    void generateList(final String limit) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Long> items = fibonacci.generate(Long.parseLong(limit));

                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (!isDestroyed()) {
                            enableGenerateButton();
                            populateList(items);
                        }
                    }
                });
            }
        }).start();
    }

    void populateList(List<Long> items) {
        customAdapter =new CustomAdapter(items);
        listViewSequence.setAdapter(customAdapter);
    }

    void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
