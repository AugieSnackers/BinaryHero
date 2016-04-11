package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.com.example.nelly.binaryhero.R;

public class NextLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);

        Button continue_btn = (Button) findViewById(R.id.continue_btn);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), GameArenaActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("RADIUS", 50);
                extras.putInt("BINARY_LEN", 4);
                extras.putInt("NUMBER_BALLS", 8);
                extras.putInt("THRESHOLD", 5); //HOW MANY TIMES DO THE BALL FALL OUT AND IN THE SCREEN BEFORE WE CALL GAME OVER
                intent.putExtras(extras);
                startActivity(intent);
                startActivity(intent);
            }
        });

        Button exit_btn = (Button) findViewById(R.id.exit_btn);
        exit_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {

                System.exit(0);
            }
        });

    }
}
