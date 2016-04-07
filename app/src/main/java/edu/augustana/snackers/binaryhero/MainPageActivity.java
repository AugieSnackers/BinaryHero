
package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nelly.binaryhero.R;


public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn =(Button) findViewById(R.id.button13);
        startBtn.setOnClickListener(start);
    }


    public void launchTargetActivity(View view){
        Intent intent = new Intent(getApplicationContext(), PickLevelActivity.class);
        startActivity(intent);
    }
    private View.OnClickListener start= new View.OnClickListener() {
        public void onClick(View btn) {
            // COLLECT THE INFORMATION STORED ABOUT THE PAINTING


            // MAKE A METHOD CALL TO DISPLAY THE INFORMATION
            launchTargetActivity(btn);
        }
    };
}
