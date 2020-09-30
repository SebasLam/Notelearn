package com.example.notelearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class Progress extends AppCompatActivity {

    public EditText lastScore;
    public EditText averageScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        SharedPreferences prefs = getSharedPreferences("progressValues", 0);
        int score = prefs.getInt("lastScore", 0);
        float average = prefs.getFloat("average", 0);
        lastScore = findViewById(R.id.lastScore);
        averageScore = findViewById(R.id.averageScore);
        lastScore.setText(String.valueOf(score));
        averageScore.setText(String.valueOf(average));
        // getSharedPreferences("progressValues", 0).edit().clear().commit();
    }
}