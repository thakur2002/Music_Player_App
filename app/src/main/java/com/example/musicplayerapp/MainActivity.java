package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button play,pause;
    SeekBar seekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url="https://spinellasoffthewall.com/Music/Busted.mp3";
        Uri audioUri=Uri.parse(url);
//        MediaPlayer mp=MediaPlayer.create(this,R.raw.music2);
        MediaPlayer mp=new MediaPlayer();
        try {
            mp.setDataSource(this,audioUri);
            mp.prepareAsync();
        } catch (IOException e) {
            Toast.makeText(this,"mp3 not found",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        seekbar=findViewById(R.id.seekBar);
        int maxDuration=mp.getDuration();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });
//        seekbar.setMax(maxDuration);
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                seekbar.setProgress(mp.getCurrentPosition());
//            }
//        },0,900);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromuser) {
                if(fromuser){
                    mp.seekTo(progress*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
            }
        });
    }
}