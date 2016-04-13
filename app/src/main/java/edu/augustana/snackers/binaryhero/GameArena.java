package edu.augustana.snackers.binaryhero;


import android.app.AlertDialog;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
//TODO START A NEW ACTIVITY AFTER GAME OVER---NEXT_LEVEL_ACTIVITY

/**
 * This class contains the gameArena, balls falling with the right binarystring enclosed
 *
 * @Author Nelly Cheboi
 */
public class GameArena {
    static ArrayList<BinaryBall> allBinaryImages;
    private static int radius;//balls radius
    private static int threshold;//how times on/off the screen before calling game over
    private int binaryLen;//how many binary bits represented inside the ball
    private int numBalls;//how balls on the screen
    private static BinaryBall currentBallToFind = null;
    static int numOfTimesOfScreen;
    static int mPlayerLevel;
    private BinaryBall binaryBall;
    private Random rand;

    public GameArena(int level) {
        mPlayerLevel=level;
        nextLevel(level);

    }

    //returns the threshold, how many times on/off the screen before calling game over
    public static int getThreshold() {
        return threshold;
    }

    /**
     * Removes the ball from the arraylist once touched
     *
     * @param x
     * @param y
     */
    public static void findBall(float x, float y) {
        for (int i = 0; i < allBinaryImages.size(); i++) {
            if (Math.abs(allBinaryImages.get(i).getY() - y) <= radius && Math.abs(allBinaryImages.get(i).getX() - x) <= radius) {
                if (allBinaryImages.get(i).getDecimalValue() == currentBallToFind.getDecimalValue()) {
                    numOfTimesOfScreen = numOfTimesOfScreen - allBinaryImages.get(i).getNumOfTimesOfScreen();
                    removeBall(allBinaryImages.get(i));
                }


            }
        }
    }

    /**
     * removes the ball from the arraylist
     *
     *
     * @param ball
     */
    public static void removeBall(BinaryBall ball) {
        allBinaryImages.remove(ball);
        Collections.shuffle(allBinaryImages);
    }

    /**
     * used when creating balls to check if the balls stack over each other
     *
     * @param nextX
     * @param nextY
     * @return
     */
    public boolean checkForOverStacking(int nextX, int nextY) {

        for (int i = 0; i < allBinaryImages.size(); i++) {

            float xDiff = nextX - allBinaryImages.get(i).getX();
            float yDiff = nextY - allBinaryImages.get(i).getY();
            float diameter = allBinaryImages.get(i).getRadius() * 2;
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
        for (int i = 0; i < allBinaryImages.size(); i++) {
            allBinaryImages.get(i).move(0, 0, width, height);
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
        paint.setColor(Color.RED);

        if(allBinaryImages.size() > 0 ) {


        int prev;
        for (int i = 0; i < allBinaryImages.size(); i++) {
            //DRAW THE BALL
            allBinaryImages.get(i).draw(canvas, allBinaryImages.get(i).getBinary());

            prev = numOfTimesOfScreen;
            numOfTimesOfScreen = allBinaryImages.get(i).getNumOfTimesOfScreen();
            if (numOfTimesOfScreen < prev) {
                numOfTimesOfScreen = prev;
            }
            //Log.d("BHERO", "x value = " + allBinaryImages.get(i).getX());

        }

            paint.setTextSize(radius * 2);
            //TODO fix bug
            if (numOfTimesOfScreen <(threshold - 1) * allBinaryImages.size()) {
                currentBallToFind = allBinaryImages.get(0);
                canvas.drawText(""+mPlayerLevel, 10, 100, paint);
                canvas.drawText("FIND " + currentBallToFind.getDecimalValue(), 100, 600, paint);
            } else {
                canvas.drawText("GAME OVER!", 10, 300, paint);
                mPlayerLevel=-1;//THE CALL TO START LEVEL IS A PRE-INCREMENT
            }
        }else{
            paint.setTextSize(radius);
            //canvas.drawText("YOU WON THIS ROUND", 20, 300, paint);
            //TODO add pop up button on options of the game
            //NEXT LEVEL
            if(mPlayerLevel<5) {
                nextLevel(++mPlayerLevel);
            }else{
                canvas.drawText("YOU FINISHED GAME", 20, 300, paint);
            }
        }

    }

    public void drawPlayLabel(Canvas canvas) {

    }
    public void nextLevel(int level){
    radius = LevelsDatabase.radius[level];
    threshold = LevelsDatabase.threshhold[level];;
    binaryLen = LevelsDatabase.binaryLen[level];;
    numBalls = LevelsDatabase.numBalls[level];;
    numOfTimesOfScreen = 0;
    //this.mPlayerLevel =mPlayerLevel;
    DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    int screenWidth = metrics.widthPixels;
    int screenHeight = metrics.heightPixels;

    allBinaryImages = new ArrayList<BinaryBall>();
    rand = new Random();//needed to randomly place the balls
    //for loop to create the given number of balls
    for (int i = 0; i < numBalls; i++) {
        int nextX = (rand.nextInt(screenWidth)) % (screenWidth - (radius));
        int nextY = (rand.nextInt(screenHeight)) % (screenHeight - (radius));

        if (nextX < radius) {
            nextX = nextX + radius;
        }
        //checks if the given position is already taking, avoids balls stacking on top of each other
        while (checkForOverStacking(nextX, nextY) && i > 0) {
            nextX = (rand.nextInt(screenWidth)) % (screenWidth - (radius));
            nextY = (rand.nextInt(screenHeight)) % (screenHeight - (radius));
            if (nextX < radius) {
                nextX = nextX + radius;
            }
        }


        binaryBall = new BinaryBall(nextX, nextY, radius, generateBinary(i), i);
        allBinaryImages.add(binaryBall);


    }
    //shuffle them balls baby
    Collections.shuffle(allBinaryImages);
}

}
