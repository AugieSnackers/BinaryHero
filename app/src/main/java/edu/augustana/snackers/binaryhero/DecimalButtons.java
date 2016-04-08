package edu.augustana.snackers.binaryhero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Button;

import com.com.example.nelly.binaryhero.R;

/**
 * Created by Nelly on 4/7/2016.
 */
public class DecimalButtons {
    private float x;
    private float y;
    private String binaryText;

     public DecimalButtons(int x, int y,String binaryText){

         this.x = x;
         this.y = y;
         this.binaryText = binaryText;

     }
    public float getY() {
        return y;
    }


    public float getX() {
        return y;
    }

    public String getBinary() {
        return binaryText;
    }

}
