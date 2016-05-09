package edu.augustana.snackers.binaryhero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.logging.Level;

/**
 * This class contains the BinaryBall object
 */

public class BinaryBall {
    private int radius;
    private int x;
    private int y;
    //TODO: make ball count down instead of UP
    private int numTimesOffScreen = 0;
    private int velY;
    private String textBinary;//corresponding binary value
    private int decimalValue;
    private int primaryColor, textColor;
    private static final double VELOCITY_PENALTY = 1;
    private static final double VELOCITY_LIMIT = 5;


    public BinaryBall(int posX, int posY, int rad, String text, int decimalValue, int primaryColor, int textColor) {
        x = posX;
        y = posY;
        radius = rad;
        if (LevelsDatabase.SCREEN_HEIGHT < 1000) {
            velY = 1;
            radius = rad;
        } else {
            velY = 2;
            radius = rad * 3 / 2;
        }

        //LevelsDatabase.SCREEN_HEIGHT/600;
        //TODO: Eventually clear out all DEBUG logging code
        Log.d("velY", " " + velY);
        Log.d("velY", " " + LevelsDatabase.SCREEN_HEIGHT);
        textBinary = text;
        this.decimalValue = decimalValue;
        numTimesOffScreen = 0;
        this.primaryColor = primaryColor;
        this.textColor = textColor;
    }

    public void move(int topWall, int bottomWall) {
        //MOVE BALL
        y += velY;


        //checks if balls reaching the bottom and what to do with it
        if (y > bottomWall) {
            y = topWall;
            numTimesOffScreen++;
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
        paint.setColor(primaryColor);
        // TODO: Fade out BinaryBalls as they approach the screen bottom using paint.setAlpha()
        // Max value for setAlpha appears to be 255
        canvas.drawCircle(x, y, radius, paint);
        paint.setColor(textColor);
        if (binaryMode) {
            paint.setTextSize(radius - 10);
            canvas.drawText(text, (x - radius) + 5, y + (radius / 2) - 5, paint);
        } else {
            paint.setTextSize((radius * 3) / 2);
            canvas.drawText(text, (x - radius) + 15, y + (radius / 2) - 5, paint);
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
        if (decimalValue < 10) {
            return "0" + decimalValue;
        }
        return decimalValue + "";
    }

    public String getBinary() {
        return textBinary;
    }

    public int getDecimalValue() {
        return decimalValue;
    }

    /**
     * Increases the velocity of this ball by the penalty amount without exceeding the velocity limit.
     */
    public void increaseVelocity() {
        if (velY < VELOCITY_LIMIT) {
            velY += VELOCITY_PENALTY;
        }
    }

    public int getNumTimesOffScreen() {
        return numTimesOffScreen;
    }

    public void setColor(int newColor) {
        primaryColor = newColor;
    }

}