package edu.augustana.snackers.binaryhero;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class cointains the gameArena, balls falling with the right binarystring enclosed
 *
 * @Author Nelly Cheboi
 */
public class GameArena {
    private BinaryBall binaryBall;
    private int radius;//balls radius
    private static int threshold;//how times on/off the screen before calling game over
    private static int binaryLen;//how many binary bits represented inside the ball
    private static int numBalls;//how balls on the screen
    static ArrayList<BinaryBall> allBinaryImages = new ArrayList<BinaryBall>();

    public GameArena(int rad, int len, int nBalls, int thresh) {
        //INSTANTIATE THE BALL
        radius = rad;
        threshold = thresh;
        binaryLen = len;
        numBalls = nBalls;
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        int current = radius * 2;//used in placing the balls on the screen


        Random rand = new Random();//needed to randomly place the balls
        //for loop to create the given number of balls
        for (int i = 0; i < numBalls; i++) {
            int nextX = (current + rand.nextInt(screenWidth)) % (screenWidth - (radius * 2));
            int nextY = (current + rand.nextInt(screenHeight)) % (screenHeight - (radius * 2));
            //checks if the given position is already taking, avoids balls stacking on top of each other
            while (checkForOverStacking(nextX, nextY) && i > 0) {
                //Log.d("BHERO", "x value = " + allBinaryImages.get(i - 1).getX() + " COMPARE x = " + nextX + " x value = " + allBinaryImages.get(i - 1).getY() + " COMPARE Y = " + nextY);
                nextX = (current + rand.nextInt(screenWidth)) % (screenWidth - (radius * 2));
                nextY = (current + rand.nextInt(screenHeight)) % (screenHeight - (radius * 2));
                //Log.d("BHERO", "x value = " + allBinaryImages.get(i-1).getX()+"COMPARE x = " +nextX+"Check rand y" +rand.nextInt(screenHeight));
            }

            binaryBall = new BinaryBall(nextX, nextY, radius, generateBinary(i));
            allBinaryImages.add(binaryBall);

            current = current + radius * 2;
        }
        //shuffle them balls baby
        Collections.shuffle(allBinaryImages);
    }

    //returns the threshold, how many times on/off the screen before calling game over
    public static float getThreshold() {
        return threshold;
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
     * Draws the balls
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        //WIPE THE CANVAS CLEAN
        canvas.drawRGB(176, 175, 175);

        for (int i = 0; i < allBinaryImages.size(); i++) {
            //DRAW THE BALL
            allBinaryImages.get(i).draw(canvas, allBinaryImages.get(i).getBinary());
            //Log.d("BHERO", "x value = " + allBinaryImages.get(i).getX());

        }
    }

    /**
     * Removes the ball from the arraylist once touched
     *
     * @param x
     * @param y
     */
    public static void findBall(float x, float y) {
        for (int i = 0; i < allBinaryImages.size(); i++) {
            if (Math.abs(allBinaryImages.get(i).getY() - y) <= 10 && Math.abs(allBinaryImages.get(i).getX() - x) <= 10) {
                allBinaryImages.remove(i);
            }
        }

    }

    /**
     * removes the ball from the arraylist
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
    public static boolean checkForOverStacking(int nextX, int nextY) {

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
}
