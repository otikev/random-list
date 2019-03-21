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
    Button buttonGenerateRecursive;
    Button buttonGenerateIterative;
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
        buttonGenerateRecursive = findViewById(R.id.buttonGenerateRecursive);
        buttonGenerateIterative= findViewById(R.id.buttonGenerateIterative);
        listViewSequence = findViewById(R.id.listViewSequence);
        listViewSequence.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        listViewSequence.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    void setEventListeners() {
        buttonGenerateIterative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInProgress(false);
                clearItems();
                final String limit = etRandom.getText().toString();
                if (!limit.isEmpty()) {
                    hideKeyboard();
                    generateListIterative(limit);
                }
            }
        });

        buttonGenerateRecursive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInProgress(true);
                clearItems();
                final String limit = etRandom.getText().toString();
                if (!limit.isEmpty()) {
                    hideKeyboard();
                    generateListRecursive(limit);
                }
            }
        });
    }

    void clearItems() {
        if(customAdapter!= null){
            customAdapter.setDataset(new ArrayList<Long>());
        }
    }

    void showInProgress(boolean recursive) {
        if(recursive){
            buttonGenerateRecursive.setText(getString(R.string.generating));
        }else{
            buttonGenerateIterative.setText(getString(R.string.generating));
        }
        buttonGenerateRecursive.setEnabled(false);
        buttonGenerateIterative.setEnabled(false);
    }

    void enableGenerateButton(boolean recursive) {
        if(recursive){
            buttonGenerateRecursive.setText(getString(R.string.generate_sequence_r));
        }else{
            buttonGenerateIterative.setText(getString(R.string.generate_sequence_i));
        }

        buttonGenerateRecursive.setEnabled(true);
        buttonGenerateIterative.setEnabled(true);
    }

    void generateListRecursive(final String limit) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Long> items = fibonacci.generateRecursive(Long.parseLong(limit));
                updateUi(items,true);
            }
        }).start();
    }

    void generateListIterative(final String limit) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Long> items = fibonacci.generateIterative(Long.parseLong(limit));
                updateUi(items,false);
            }
        }).start();
    }

    void updateUi(final List<Long> items,final boolean recursive){
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (!isDestroyed()) {
                    enableGenerateButton(recursive);
                    populateList(items);
                }
            }
        });
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
