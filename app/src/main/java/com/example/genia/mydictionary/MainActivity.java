package com.example.genia.mydictionary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button btn_eng;
    String release;
    String RELEASE = "release";
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_eng = (Button) findViewById(R.id.button);

        release = Build.VERSION.RELEASE;
        sPref = getSharedPreferences("release", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(RELEASE,release);
        ed.apply();

        if (Objects.equals(release, "4.4.2"))
        {
            Log.i("Class_MainActivity", "OC - 4.4.2");
        }



    }


    public void btn_eng(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
