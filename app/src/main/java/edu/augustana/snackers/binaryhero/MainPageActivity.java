package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.com.example.nelly.binaryhero.R;


public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), PickLevelActivity.class);
                startActivity(intent);
            }
        });

        Button helpBtn = (Button) findViewById(R.id.help_btn);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });
        Button settingBtn = (Button) findViewById(R.id.settings_btn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }


}
