package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import com.com.example.nelly.binaryhero.R;


public class GameArenaActivity extends AppCompatActivity {

    long startTime;
    long endTime;
    long duration;
    //public Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_arena);
        // startTimer();
        startTime = System.nanoTime();

        // REFERENCE THE FRAMELAYOUT ELEMENT
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final GameArena gameArena = new GameArena(extras.getInt("PLAYER_lEVEL", 0), extras.getBoolean("GAME_MODE", true), this);
        // INSTANTIATE A CUSTOM SURFACE VIEW
        // ADD IT TO THE FRAMELAYOUT

        BounceSurfaceView bounceSurfaceView = new BounceSurfaceView(this, gameArena, null);
        frameLayout.addView(bounceSurfaceView);
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                gameArena.findBall(x, y);
                return false;
            }
        });
    }

        public long gameWon(){
            endTime = System.nanoTime();
            duration = (endTime - startTime);


            //call popup window, pass in duration
            return duration;



    }


    }

//    public void startTimer(){
//
//        chronometer.start();
//
//    }
//    public  void stopTimer(){
//        chronometer.stop();
//
//    }






    //disabling the back button
//@Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            GameArena.clearHistory();
//            onBackPressed();
//        }
//        return super.onKeyDown(keyCode, event);
//    }



