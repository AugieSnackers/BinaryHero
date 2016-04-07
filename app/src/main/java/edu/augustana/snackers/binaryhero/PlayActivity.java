package edu.augustana.snackers.binaryhero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


import com.example.nelly.binaryhero.R;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        // REFERENCE THE FRAMELAYOUT ELEMENT
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        // INSTANTIATE A CUSTOM SURFACE VIEW
        // ADD IT TO THE FRAMELAYOUT

        BounceSurfaceView bounceSurfaceView = new BounceSurfaceView(this, null);
        frameLayout.addView(bounceSurfaceView);
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                AnimationArena.findBall(x, y);
                return false;
            }
        });
//        Button btn1 = (Button)findViewById(R.id.button2);
//        frameLayout.addView(btn1);
//        Button btn2 = (Button)findViewById(R.id.button3);
//        frameLayout.addView(btn2);
//        Button btn3 = (Button)findViewById(R.id.button4);
//        frameLayout.addView(btn3);
    }


}

