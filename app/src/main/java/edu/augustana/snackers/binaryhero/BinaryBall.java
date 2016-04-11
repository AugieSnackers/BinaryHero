package edu.augustana.snackers.binaryhero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * This class cointains the BinaryBall object
 */

public class BinaryBall {
    private int radius;
    private float x;
    private float y;
    private int numOfTimeOutOfScreen = 0;
    private int velY;
    private String textBinary;//corresponding binary value
    private int decimalValue;

    public BinaryBall(float posX, float posY, int rad, String text, int decimalValue) {
        x = posX;
        y = posY;
        radius = rad;
        velY = 1;
        textBinary = text;
        this.decimalValue = decimalValue;
    }

    public void move(int leftWall, int topWall,
                     int rightWall, int bottomWall) {
        //MOVE BALL
        y += velY;


        //checks if balls reaching the bottom and what to do with it
        if (y > bottomWall) {
            y = topWall;
            numOfTimeOutOfScreen++;
            GameArena.numOfTimesOfScreen++;
            if (numOfTimeOutOfScreen >= GameArena.getThreshold()) {
                GameArena.removeBall(this);
            }
            //velY *= REVERSE;
        }
//        if (x > rightWall - radius) {
//            x = rightWall - radius;
//
//        } else if (x < leftWall + radius) {
//            x = leftWall + radius;
//
//        }


    }

    public void draw(Canvas canvas, String text) {

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x, y, radius, paint);
        paint.setColor(Color.WHITE);

        paint.setTextSize(radius - 10);
        canvas.drawText(text, (x - radius) + 5, y + (radius / 2) - 5, paint);

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

    public int getNumOfTimesOfScreen() {
        return numOfTimeOutOfScreen;
    }

    public String getBinary() {
        return textBinary;
    }

    public int getDecimalValue() {
        return decimalValue;
    }
}
