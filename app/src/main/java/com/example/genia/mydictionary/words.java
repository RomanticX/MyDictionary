package com.example.genia.mydictionary;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class words extends AppCompatActivity {
    EditText ed_eng, ed_rus;
    Dialog dialog;
    String s_eng, s_rus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        dialog = new Dialog(words.this);
    }



    public void btn_eng(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout_words, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.to_main:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.add_words:
                dialog.setContentView(R.layout.panel_add_words);
                dialog.show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void add_new_word(View view) {
        ed_eng = (EditText) dialog.findViewById(R.id.ed_eng_word);
        ed_rus = (EditText) dialog.findViewById(R.id.ed_rus_word);
        s_eng = ed_eng.getText().toString();
        s_rus = ed_rus.getText().toString();
        Log.i("Class_wods", "Check input words: Eng word: "
                + s_eng + "Rus word: " + s_rus);
        dialog.dismiss();

    }
}
