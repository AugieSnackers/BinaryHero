package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.EditText;

import com.com.example.nelly.binaryhero.R;


public class MainPageActivity extends AppCompatActivity {

    private ToggleButton baseSwitch;
    private Typeface sansationFont;
    private int passwordLevel = 0;
    private View popupViewTut, popupViewAbout;
    private PopupWindow popupWindowTut, popupWindowAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        baseSwitch = (ToggleButton) findViewById(R.id.toggleButton);
        sansationFont = Typeface.createFromAsset(getAssets(), "Sansation-Regular.ttf");

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


        startBtn.setTypeface(sansationFont);
        baseSwitch.setTypeface(sansationFont);
        passWordTextFont.setTypeface(sansationFont);

    }


    /**
     * checks if the user entered in a password or not
     * @param passwordText EditText that is the text in the password text field
     * @return boolean value false if there is text entered in and false is not
     */
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


    /**
     * shows tutorial or about page if user clicks on them
     * @param item MenuItem that holds the information about what the user clicked
     * @return item selected
     */
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


    /**
     * calls activity_tutorial layout to show the text explaining how to play the game
     */
    private void showTutorial() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout2);
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupViewTut = layoutInflater.inflate(R.layout.activity_tutorial, null);
        popupWindowTut = new PopupWindow(popupViewTut, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        popupWindowTut.showAtLocation(frameLayout, Gravity.CENTER, 0, 0);

        TextView tutorial = (TextView)popupViewTut.findViewById(R.id.textViewTut0);
        tutorial.setTypeface(sansationFont);

        Button closeButton = (Button) popupViewTut.findViewById(R.id.buttonTut);
        closeButton.setTypeface(sansationFont);
        closeButton.setBackgroundColor(0xFF000000);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowTut.dismiss();
            }
        });
    }

    /**
     * calls activity_about layout to show the about page
     */
    private void showAboutPage() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout2);
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupViewAbout = layoutInflater.inflate(R.layout.activity_about, null);
        popupWindowAbout = new PopupWindow(popupViewAbout, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        popupWindowAbout.showAtLocation(frameLayout, Gravity.CENTER, 0, 0);

        TextView teamName = (TextView) popupViewAbout.findViewById(R.id.aboutTextView0);
        teamName.setTypeface(sansationFont);
        TextView teamMembers = (TextView) popupViewAbout.findViewById(R.id.aboutTextView1);
        teamMembers.setTypeface(sansationFont);
        TextView graphicsTV = (TextView) popupViewAbout.findViewById(R.id.graphics);
        graphicsTV.setTypeface(sansationFont);
        TextView musicTV = (TextView) popupViewAbout.findViewById(R.id.music);
        musicTV.setTypeface(sansationFont);
        TextView soundTV = (TextView) popupViewAbout.findViewById(R.id.sound);
        soundTV.setTypeface(sansationFont);
        Button closeButton = (Button) popupViewAbout.findViewById(R.id.buttonAbout);
        closeButton.setTypeface(sansationFont);
        closeButton.setBackgroundColor(0xFF000000);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowAbout.dismiss();
            }
        });
    }



}


