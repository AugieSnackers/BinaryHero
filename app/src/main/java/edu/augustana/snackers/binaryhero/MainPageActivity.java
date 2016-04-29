package edu.augustana.snackers.binaryhero;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.EditText;

import com.com.example.nelly.binaryhero.R;


public class MainPageActivity extends AppCompatActivity {

    private ToggleButton baseSwitch;
    private Typeface myTypeface;
    private int passwordLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseSwitch = (ToggleButton) findViewById(R.id.toggleButton);
        myTypeface = Typeface.createFromAsset(getAssets(), "Sansation-Regular.ttf");

        Button startBtn = (Button) findViewById(R.id.start_btn);

        assert startBtn != null;
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), GameArenaActivity.class);
                Bundle extras = new Bundle();
                EditText passWordText = (EditText) findViewById(R.id.password_Field);
                assert passWordText != null;
                passWordText.setTypeface(myTypeface);

                if(!isEmpty(passWordText)){
                    for(int i = 0 ; i < LevelsDatabase.passwords.length; i++){
                        if(LevelsDatabase.passwords[i]==Integer.parseInt((passWordText.getText().toString()))){
                            passwordLevel = i + 1;
                        }
                    }
                }

                //passwordLevel is started at 0 if no correct password
                //does this work?  need start level method normal start was at 0
                extras.putInt(GameArenaActivity.PLAYER_LEVEL_EXTRA, passwordLevel);
                //TODO: also make the GAME_MODE extra string into a constant...
                extras.putBoolean("GAME_MODE", baseSwitch.isChecked());
                intent.putExtras(extras);
                startActivity(intent);
            }
        });


        Button settingBtn = (Button) findViewById(R.id.settings_btn);
        assert settingBtn != null;
        settingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        startBtn.setTypeface(myTypeface);
        settingBtn.setTypeface(myTypeface);
        baseSwitch.setTypeface(myTypeface);

    }


        public boolean isEmpty(EditText passwordText){
            if(passwordText.getText().toString().trim().length() > 0) {
                return false;
            }
            return true;

        }
    public void showLevelPassword(int mPlayerLevel) {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Password");
        helpBuilder.setMessage("Congrats the password for this level is" + LevelsDatabase.passwords[mPlayerLevel]);
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
}


