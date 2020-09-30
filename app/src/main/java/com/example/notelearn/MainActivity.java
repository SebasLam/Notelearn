package com.example.notelearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // When the logo on the main menu is clicked, it plays a sound then disables the button from being clicked again
    public void playNote(View v) {
        final MediaPlayer noteSound = MediaPlayer.create(this, R.raw.smooth_guitar_note_f_major);
        findViewById(R.id.button_logo).setEnabled(false);
        noteSound.start();
    }

    public void openNoteGame(View v) {
        findViewById(R.id.button_logo).setEnabled(true);
        Intent i = new Intent(this, NoteGame.class);
        startActivity(i);
    }

    public void openProgress(View v) {
        findViewById(R.id.button_logo).setEnabled(true);
        Intent i = new Intent(this, Progress.class);
        startActivity(i);
    }

}