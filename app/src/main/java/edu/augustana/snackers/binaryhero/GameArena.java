package edu.augustana.snackers.binaryhero;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.Chronometer;
import android.widget.TextView;

import com.com.example.nelly.binaryhero.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TimerTask;
//TODO START A NEW ACTIVITY AFTER GAME OVER---NEXT_LEVEL_ACTIVITY

/**
 * This class contains the gameArena, balls falling with the right binarystring enclosed
 *
 * @Author Nelly Cheboi
 */
public class GameArena {
    ArrayList<BinaryBall> allBinaryBalls;

    //Timer related variables



    private int threshold;//how times on/off the its rn before calling game over
    private int binaryLen;//how many binary Balls represented inside the ball
    private int numBalls;//how balls on the screen
    private BinaryBall currentBallToFind = null;
    boolean gameIsOver;
    private int mPlayerLevel;
    private Activity activity;
    private boolean isBinary;


    public GameArena(int level, boolean isBinary, Activity activity) {
        mPlayerLevel = level;
        this.activity = activity;
        this.isBinary = isBinary;
        nextLevel(level);

    }

    public void nextLevel(int level) {
        Random rand;
        gameIsOver = false;
        int radius = LevelsDatabase.radius[level];
        threshold = LevelsDatabase.threshhold[level];

        binaryLen = LevelsDatabase.binaryLen[level];
        numBalls = LevelsDatabase.numBalls[level];


        //this.mPlayerLevel =mPlayerLevel;


        allBinaryBalls = new ArrayList<BinaryBall>();
        rand = new Random();//needed to randomly place the balls
        //for loop to create the given number of balls
        for (int i = 0; i < numBalls; i++) {
            int nextX = (rand.nextInt(LevelsDatabase.screenWidth)) % (LevelsDatabase.screenWidth - (radius));
            int nextY = (rand.nextInt(LevelsDatabase.screenHeight)) % (LevelsDatabase.screenHeight - (radius));

            if (nextX < radius) {
                nextX = nextX + radius;
            }
            //checks if the given position is already taking, avoids balls stacking on top of each other
            while (checkForOverStacking(nextX, nextY) && i > 0) {
                nextX = (rand.nextInt(LevelsDatabase.screenWidth)) % (LevelsDatabase.screenWidth - (radius));
                nextY = (rand.nextInt(LevelsDatabase.screenHeight)) % (LevelsDatabase.screenHeight - (radius));
                if (nextX < radius) {
                    nextX = nextX + radius;
                }
            }


            BinaryBall binaryBall = new BinaryBall(nextX, nextY, radius, generateBinary(i), i, this);
            allBinaryBalls.add(binaryBall);


        }
        //shuffle them balls baby
        Collections.shuffle(allBinaryBalls);
    }


    //returns the threshold, how many times on/off the screen before calling game over
    public int getThreshold() {
        return threshold;
    }

    /**
     * Removes the ball from the arraylist once touched
     *
     * @param x
     * @param y
     */
    public void findBall(float x, float y) {
        try {
            for (int i = 0; i < allBinaryBalls.size(); i++) {
                BinaryBall binaryBall = allBinaryBalls.get(i);
                float xDiff = x - allBinaryBalls.get(i).getX();
                float yDiff = y - allBinaryBalls.get(i).getY();
                float diameter = allBinaryBalls.get(i).getRadius() * 2;
                if ((xDiff * xDiff + yDiff * yDiff) <= diameter * diameter) {
                    if (binaryBall.getDecimalValue() == currentBallToFind.getDecimalValue()) {
                        removeBall(allBinaryBalls.get(i));
                    }


                }
            }
        } catch(NullPointerException e) {

        }
    }
    /**
     * removes the ball from the arraylist
     *
     * @param ball
     */
    public void removeBall(BinaryBall ball) {
        allBinaryBalls.remove(ball);
        Collections.shuffle(allBinaryBalls);
    }

    /**
     * used when creating balls to check if the balls stack over each other
     *
     * @param nextX
     * @param nextY
     * @return
     */
    public boolean checkForOverStacking(int nextX, int nextY) {

        for (int i = 0; i < allBinaryBalls.size(); i++) {

            float xDiff = nextX - allBinaryBalls.get(i).getX();
            float yDiff = nextY - allBinaryBalls.get(i).getY();
            float diameter = allBinaryBalls.get(i).getRadius() * 2;
            if ((xDiff * xDiff + yDiff * yDiff) <= diameter * diameter) {
                return true;
            }
        }


        return false;
    }

    /**
     * redraw the ball to simulate the moving
     *
     * @param width
     * @param height
     */
    public void update(int width, int height) {
        for (int i = 0; i < allBinaryBalls.size(); i++) {
            allBinaryBalls.get(i).move(0, 0, width, height);
        }
    }

    /**
     * generate the binary value. Notice the clever use of the Integer.toString(x, 2)
     *
     * @param x the integer to convert to binary
     * @return the string representation of the binary number
     */
    public String generateBinary(int x) {
        String binary = Integer.toString(x, 2);
        int diff = binaryLen - binary.length();
        String zeroes = "";
        for (int i = 1; i <= diff; i++) {
            zeroes = zeroes + "0";
        }
        return zeroes + binary;
    }

    /**
     * Draws the game arena including the balls
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        //WIPE THE CANVAS CLEAN
        canvas.drawRGB(176, 175, 175);
        Paint paint = new Paint();
        paint.setColor(LevelsDatabase.textColor);

        if (allBinaryBalls.size() > 0) {


            int prev;
            for (int i = 0; i < allBinaryBalls.size(); i++) {

                //DRAW THE BALL
                if (isBinary) {
                    allBinaryBalls.get(i).draw(canvas, allBinaryBalls.get(i).getBinary(), isBinary);

                } else {
                    allBinaryBalls.get(i).draw(canvas, allBinaryBalls.get(i).getDecimalText(), isBinary);
                }


                //Log.d("BHERO", "x value = " + allBinaryBalls.get(i).getX());

            }

            paint.setTextSize(50 * 2);

            if (!gameIsOver) {
                currentBallToFind = allBinaryBalls.get(0);
                canvas.drawText("" + mPlayerLevel, 10, 100, paint);
                if (isBinary) {
                    canvas.drawText("FIND " + currentBallToFind.getDecimalValue(), 100, 600, paint);
                } else {
                    canvas.drawText("FIND " + currentBallToFind.getBinary(), 100, 600, paint);
                }

            } else {
                currentBallToFind = null;
                canvas.drawText("GAME OVER!", 10, 300, paint);
                mPlayerLevel = -1;//THE CALL TO START LEVEL IS A PRE-INCREMENT
            }
        } else {
            paint.setTextSize(50);

            //canvas.drawText("YOU WON THIS ROUND", 20, 300, paint);
            //TODO add pop up button on options of the game
            //NEXT LEVEL
            if (mPlayerLevel < 5) {
                nextLevel(++mPlayerLevel);
            } else {

                canvas.drawText("YOU FINISHED GAME", 20, 300, paint);
            }
        }

    }

    public void drawPlayLabel(Canvas canvas) {

    }



}
