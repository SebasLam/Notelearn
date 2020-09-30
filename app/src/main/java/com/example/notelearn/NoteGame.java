package com.example.notelearn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NoteGame extends AppCompatActivity {

    // 57 elements in total String name = v.getTag().toString();
    public static int[] imageResources = {
            R.drawable.a_flat_1, R.drawable.a_flat_2, R.drawable.a_flat_3,
            R.drawable.a_natural_1, R.drawable.a_natural_2, R.drawable.a_natural_3,
            R.drawable.a_sharp_1, R.drawable.a_sharp_2, R.drawable.a_sharp_3,
            R.drawable.b_flat_1, R.drawable.b_flat_2, R.drawable.b_flat_3,
            R.drawable.b_natural_1, R.drawable.b_natural_2, R.drawable.b_natural_3,
            R.drawable.b_sharp_1, R.drawable.b_sharp_2, R.drawable.b_sharp_3,
            R.drawable.c_flat_1, R.drawable.c_flat_2, R.drawable.c_flat_3,
            R.drawable.c_natural_1, R.drawable.c_natural_2, R.drawable.c_natural_3,
            R.drawable.c_sharp_1, R.drawable.c_sharp_2, R.drawable.c_sharp_3,
            R.drawable.d_flat_1, R.drawable.d_flat_2, R.drawable.d_flat_3,
            R.drawable.d_natural_1, R.drawable.d_natural_2, R.drawable.d_natural_3,
            R.drawable.d_sharp_1, R.drawable.d_sharp_2, R.drawable.d_sharp_3,
            R.drawable.e_flat_1, R.drawable.e_flat_2,
            R.drawable.e_natural_1, R.drawable.e_natural_2,
            R.drawable.e_sharp_1, R.drawable.e_sharp_2,
            R.drawable.f_flat_1, R.drawable.f_flat_2,
            R.drawable.f_natural_1, R.drawable.f_natural_2,
            R.drawable.f_sharp_1, R.drawable.f_sharp_2,
            R.drawable.g_flat_1, R.drawable.g_flat_2, R.drawable.g_flat_3,
            R.drawable.g_natural_1, R.drawable.g_natural_2, R.drawable.g_natural_3,
            R.drawable.g_sharp_1, R.drawable.g_sharp_2, R.drawable.g_sharp_3,
    };

    public static Random random = new Random();
    public static final int NUM_IMAGES = 57;
    public static TextView mTextView;

    public TextView timer;
    public TextView score;
    public ImageView noteImage;
    public int currentImageResource;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_game);
        mTextView = findViewById(R.id.score);
        context = getApplicationContext();
        timer = findViewById(R.id.timer);
        score = findViewById(R.id.score);
        noteImage = findViewById(R.id.noteImage);
        refreshImage();
        timerStart();
    }

    public void timerStart() {
        new CountDownTimer(31000, 1000) {

            public void onTick(long millisUntilFinished) {
                modifyTimer(millisUntilFinished);
            }

            public void onFinish() {
                saveScore();
            }
        }.start();
    }

    public void refreshImage() {
        currentImageResource = imageResources[random.nextInt(NUM_IMAGES)];
        noteImage.setImageResource(currentImageResource);
    }

    public void modifyTimer(long millis) {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        timer.setText(seconds + " seconds");
    }

    public void saveScore() {
        int previousScore = Integer.parseInt(score.getText().toString());
        SharedPreferences prefs = getSharedPreferences("progressValues", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("lastScore", previousScore);
        int j = prefs.getInt("gamesPlayed", 0);
        editor.putInt("gamesPlayed", ++j);
        float avg = prefs.getFloat("average", previousScore);
        if (j == 1) {
            editor.putFloat("average", avg);
        } else {
            float newAvg = ((avg * (j - 1) + previousScore) / j);
            editor.putFloat("average", newAvg);
        }
        editor.commit();
        Intent i = new Intent(this, Progress.class);
        startActivity(i);
    }

    public void checkAnswer(View v) {
        Button b = (Button) v;
        String imageName = getResources().getResourceEntryName(currentImageResource);
        String buttonName = getResources().getResourceEntryName(b.getId());
        if (imageName.contains(buttonName)) {
            int i = Integer.parseInt(score.getText().toString());
            score.setText(String.valueOf(++i));
        }
        refreshImage();
    }

}