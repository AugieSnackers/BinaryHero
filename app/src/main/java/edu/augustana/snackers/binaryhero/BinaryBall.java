package edu.augustana.snackers.binaryhero;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

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



    /**
     *
     * constructor method for the BinaryBall class that sets values for variables
     *
     * @param  posX integer value of the x position of the ball
     * @param  posY integer value of the y postiton of the ball
     * @param  rad integer value of the raidus of the ball
     * @param text string that is displayed on ball
     * @param oppositeBaseValue integer that represents the value of the number on the ball in
     *                          the opposite base (base 10 or 2)
     * @param primaryColor int that represents the color the ball is
     * @param textColor int that represents the color fo the text
     *
     */



    public BinaryBall(int posX, int posY, int rad, String text, int oppositeBaseValue, int primaryColor, int textColor) {
        x = posX;
        y = posY;
        //devices have different resoulution
        velY =  Math.round(LevelsDatabase.SCREEN_HEIGHT / 800);
        radius = rad + 10 * (velY);

        //LevelsDatabase.SCREEN_HEIGHT/600;
        //TODO: Eventually clear out all DEBUG logging code
        Log.d("velY", " " + velY);
        Log.d("velY", " " + LevelsDatabase.SCREEN_HEIGHT);
        textBinary = text;
        this.decimalValue = oppositeBaseValue;
        numTimesOffScreen = 0;
        this.primaryColor = primaryColor;
        this.textColor = textColor;
    }

    /**
     * method that causes the ball to fall in the y direction. Also checks if the ball
     *      has reached the bottom of the screen and if it has moves it to the top while adding
     *      1 to the variable numTImesOffScreen which represents the number of times ball has been
     *      reset to top
     * @param topBound integer that represents the top boundry of the screen
     * @param bottomBound integer that represents the bottom of the screen
     */

    public void move(int topBound, int bottomBound) {
        //MOVE BALL
        y += velY;


        //checks if balls reaching the bottom and what to do with it
        if (y > bottomBound) {
            y = topBound;
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

    /**
     * draws the balls that are shown on screen.  sets color and places text inside them
     * depending on length of text value inside ball changes the radius of the ball if needed
     *
     *
     * @param canvas Canvas object that is drawn on
     * @param text String object that represents the numbers to be placed on ball
     * @param binaryMode boolean value that represents whether the user is in base 10 or 2
     *
     */

    public void draw(Canvas canvas, String text, boolean binaryMode) {
        //TODO MAKE THE  BALLS BETTER LOOKING
        Paint paint = new Paint();
        paint.setColor(primaryColor);
        // TODO: Fade out BinaryBalls as they approach the screen bottom using paint.setAlpha()
        // Max value for setAlpha appears to be 255
        canvas.drawCircle(x, y, radius, paint);
        paint.setColor(textColor);
        if (binaryMode) {
            if(text.length()>4){
                paint.setTextSize(radius-30);
            }else{
                paint.setTextSize(radius - 10);
            }
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


    /**
     *@returns string representation of base 10 decimal value
     */
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