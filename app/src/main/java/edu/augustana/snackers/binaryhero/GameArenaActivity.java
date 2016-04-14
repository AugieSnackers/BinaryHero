package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;

import com.com.example.nelly.binaryhero.R;


public class GameArenaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_arena);


        // REFERENCE THE FRAMELAYOUT ELEMENT
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final GameArena gameArena = new GameArena(extras.getInt("PLAYER_lEVEL", 0), extras.getBoolean("GAME_MODE",true),this);
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

    //disabling the back button
//@Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            GameArena.clearHistory();
//            onBackPressed();
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}

