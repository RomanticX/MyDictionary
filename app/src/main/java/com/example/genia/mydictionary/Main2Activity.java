package com.example.genia.mydictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void btn_word(View view) {
        Intent intent = new Intent(this, words.class);
        startActivity(intent);
    }

    public void btn_phrases(View view) {
        Intent intent = new Intent(this, phrases.class);
        startActivity(intent);
    }
}
