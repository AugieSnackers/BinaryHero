package edu.augustana.snackers.binaryhero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * This class cointains the BinaryBall object
 */

public class BinaryBall {
    private int radius;
    private int x;
    private int y;
    private int numOfTimeOutOfScreen = 0;
    private int changeColorLevel = 0;
    private int velY;
    private String textBinary;//corresponding binary value
    private int decimalValue;
    private GameArena gameArena;


    public BinaryBall(int posX, int posY, int rad, String text, int decimalValue, GameArena gameArena) {
        x = posX;
        y = posY;
        radius = rad;
        if(LevelsDatabase.SCREEN_HEIGHT <1000){
            velY = 1;
            radius = rad;
        }else{
            velY=2;
            radius = rad*3/2;
        }
        //LevelsDatabase.SCREEN_HEIGHT/600;
        Log.d("velY", " " + velY);
        Log.d("velY", " " + LevelsDatabase.SCREEN_HEIGHT);
        textBinary = text;
        this.decimalValue = decimalValue;
        this.gameArena = gameArena;
    }

    public void move(int leftWall, int topWall,
                     int rightWall, int bottomWall) {
        //MOVE BALL
        y += velY;


        //checks if balls reaching the bottom and what to do with it
        if (y > bottomWall) {
            y = topWall;
            numOfTimeOutOfScreen++;
            if (numOfTimeOutOfScreen == gameArena.getThreshold() - 1) {
                changeColorLevel = 1;

            }

            if (numOfTimeOutOfScreen >= gameArena.getThreshold()) {
                gameArena.gameIsOver = true;
                gameArena.removeBall(this);

            }
            //velY *= REVERSE;
        }
//        if (x > rightWall - RADIUS) {
//            x = rightWall - RADIUS;
//
//        } else if (x < leftWall + RADIUS) {
//            x = leftWall + RADIUS;
//
//        }


    }

    public void draw(Canvas canvas, String text, boolean binaryMode) {
//TODO MAKE THE  BALLS BETTER LOOKING
        Paint paint = new Paint();
        paint.setColor(LevelsDatabase.COLOR[changeColorLevel]);
        canvas.drawCircle(x, y, radius, paint);
        paint.setColor(Color.WHITE);
        if (binaryMode) {
            paint.setTextSize(radius - 10);
            canvas.drawText(text, (x - radius) + 5, y + (radius / 2) - 5, paint);
        } else {
            paint.setTextSize((radius * 3) / 2);
            canvas.drawText(text, (x - radius)+15 , y+(radius / 2)-5 , paint);
        }


    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public float getX() {
        return x;
    }

    public String getDecimalText() {
        if(decimalValue<10){
            return "0"+decimalValue;
        }
        return decimalValue+"";
    }

    public String getBinary() {
        return textBinary;
    }

    public int getDecimalValue() {
        return decimalValue;
    }
}
