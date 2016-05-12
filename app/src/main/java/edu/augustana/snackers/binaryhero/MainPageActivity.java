package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.ToggleButton;
import android.widget.EditText;

import com.com.example.nelly.binaryhero.R;


public class MainPageActivity extends AppCompatActivity {

    private ToggleButton baseSwitch;
    private Typeface myTypeface;
    private int passwordLevel = 0;
    private View popupViewTut, popupViewAbout;
    private PopupWindow popupWindowTut, popupWindowAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        baseSwitch = (ToggleButton) findViewById(R.id.toggleButton);
        myTypeface = Typeface.createFromAsset(getAssets(), "Sansation-Regular.ttf");

        Button startBtn = (Button) findViewById(R.id.start_btn);
        EditText passWordTextFont = (EditText) findViewById(R.id.password_Field);

        assert startBtn != null;
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), GameArenaActivity.class);
                Bundle extras = new Bundle();
                EditText passWordText = (EditText) findViewById(R.id.password_Field);
                assert passWordText != null;
                passwordLevel = 0;
                if(!isEmpty(passWordText)) {
                    for (int i = 0; i < LevelsDatabase.passwords.length; i++) {
                        if (LevelsDatabase.passwords[i].equals(passWordText.getText().toString())) {
                            passwordLevel = i;
                            break;
                        }
                    }
                }

                extras.putInt(GameArenaActivity.PLAYER_LEVEL_EXTRA, passwordLevel);
                extras.putBoolean(GameArenaActivity.GAME_MODE_EXTRA, baseSwitch.isChecked());
                intent.putExtras(extras);
                startActivity(intent);

            }
        });


//        Button settingBtn = (Button) findViewById(R.id.settings_btn);
//        assert settingBtn != null;
//        settingBtn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View btn) {
//                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
//                startActivity(intent);
//            }
//        });

        startBtn.setTypeface(myTypeface);
        baseSwitch.setTypeface(myTypeface);
        passWordTextFont.setTypeface(myTypeface);

    }


        public boolean isEmpty(EditText passwordText){
            if(passwordText.getText().toString().trim().length() > 0) {
                return false;
            }
            return true;

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.tutorial) {
            showTutorial();
            return true;
        } else if (id == R.id.about){
            showAboutPage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showTutorial() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout2);
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupViewTut = layoutInflater.inflate(R.layout.activity_tutorial, null);
        popupWindowTut = new PopupWindow(popupViewTut, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        popupWindowTut.showAtLocation(frameLayout, Gravity.CENTER, 0, 0);
        Button closeButton = (Button) popupViewTut.findViewById(R.id.buttonTut);
        closeButton.setTypeface(myTypeface);
        closeButton.setBackgroundColor(0xFF000000);
        assert closeButton != null;
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowTut.dismiss();
            }
        });
    }

    private void showAboutPage() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout2);
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupViewTut = layoutInflater.inflate(R.layout.activity_about, null);
        popupWindowAbout = new PopupWindow(popupViewTut, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        popupWindowAbout.showAtLocation(frameLayout, Gravity.NO_GRAVITY, 500, 500);

        popupViewTut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                popupWindowAbout.dismiss();
                return true;
            }
        });
    }



}


