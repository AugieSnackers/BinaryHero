package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.com.example.nelly.binaryhero.R;

/**
 * And activity class for the pick level. Passes in the right values depending on the level
 */

public class PickLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_level);
        Button level000Btn = (Button) findViewById(R.id.btn000);
        level000Btn.setOnClickListener(new View.OnClickListener() {
          public void onClick(View btn) {
        levelChanged(0);
    }
        });

        Button level001Btn = (Button) findViewById(R.id.btn001);
        level001Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                levelChanged(1);
            }
        });

        Button level011Btn = (Button) findViewById(R.id.btn011);
        level011Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                levelChanged(2);
            }
        });

        Button level100Btn = (Button) findViewById(R.id.btn100);
        level100Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                levelChanged(3);
            }
        });

        Button level101Btn = (Button) findViewById(R.id.btn101);
        level101Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                levelChanged(4);
            }
        });

        Button level110Btn = (Button) findViewById(R.id.btn110);
        level110Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                levelChanged(5);

            }
        });

        Button level111Btn = (Button) findViewById(R.id.btn111);
        level111Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                levelChanged(6);

            }
        });
    }
    public  void levelChanged(int level){
    Intent intent = new Intent(getApplicationContext(), GameArenaActivity.class);
    intent.putExtra("PLAYER_lEVEL",level);
    startActivity(intent);
    }

}