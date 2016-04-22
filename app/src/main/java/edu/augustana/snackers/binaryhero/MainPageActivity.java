package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;
import android.widget.EditText;

import com.com.example.nelly.binaryhero.R;


public class MainPageActivity extends AppCompatActivity {

    private boolean isBinary;
    ToggleButton baseSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseSwitch = (ToggleButton) findViewById(R.id.toggleButton);

        baseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isBinary = true;
                } else {
                    isBinary = false;
                }
                // true if the switch is in the On position
            }
        });
        if (baseSwitch.isChecked()) {
            isBinary = true;
        } else {
            isBinary = false;
        }

        Button startBtn = (Button) findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), GameArenaActivity.class);
                Bundle extras = new Bundle();
                EditText passWordText = (EditText) findViewById(R.id.password_Field);
                if(isEmpty(passWordText)){
                    
                }
                extras.putInt("PLAYER_LEVEL", 0);
                extras.putBoolean("GAME_MODE", isBinary);
                intent.putExtras(extras);
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


        public boolean isEmpty(EditText passwordText){
            if(passwordText.getText().toString().trim().length() > 0) {
                return false;
            }
            return true;

        }
}


