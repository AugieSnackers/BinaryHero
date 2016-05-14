package edu.augustana.snackers.binaryhero;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.TypedValue;


import com.com.example.nelly.binaryhero.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
//TODO START A NEW ACTIVITY AFTER GAME OVER---NEXT_LEVEL_ACTIVITY

/**
 * This class contains the gameArena, balls falling with the right binarystring enclosed
 *
 * @Author Nelly Cheboi
 */

// code from http://www.androiddom.com/2011/06/displaying-android-pop-up-dialog.html
public class GameArena {
    ArrayList<BinaryBall> allBinaryBalls;

    //Timer related variables


    private int threshold;//how times on/off the its rn before calling game over
    private int binaryLen;//how many binary Balls represented inside the ball
    private int numBalls;//how balls on the screen
    private int wrongGuesses;
    private BinaryBall currentBallToFind = null;
    private boolean gameIsOver;
    private int mPlayerLevel;
    private Activity activity;
    private boolean isBinary;
    private long startLevelTime;
    private boolean displayWindow;
    private int ballSpawnPoint;
    private int ballsToClear;



    public GameArena(int level, boolean isBinary, Activity activity) {
        this.activity = activity;
        this.isBinary = isBinary;
        nextLevel(level);
    }

    /**
     * sets values for attributes pertaining to next level. Makes new array list of Binary Balls
     *      and places all the balls on the screeen and makes sure they are not stacked on top of
     *      each other or off of the screen
     * @param level integer that represents what level the user is on
     */
    public synchronized void nextLevel(int level) {
        Random rand;
        gameIsOver = false;
        mPlayerLevel = level;
        int radius = LevelsDatabase.RADIUS[level];
        threshold = LevelsDatabase.THRESHOLD[level];
        wrongGuesses = 0;

        displayWindow = true;
        binaryLen = LevelsDatabase.BINARY_LEN[level];
        numBalls = LevelsDatabase.NUM_BALLS[level];

        allBinaryBalls = new ArrayList<BinaryBall>();
        rand = new Random();//needed to randomly place the balls
        int distanceOffScreen = 0;
        //for loop to create the given number of balls
        for (int i = 0; i < numBalls; i++) {
            int nextX = (rand.nextInt(LevelsDatabase.SCREEN_WIDTH)) % (LevelsDatabase.SCREEN_WIDTH - (radius));
            int nextY = rand.nextInt(LevelsDatabase.SCREEN_HEIGHT + distanceOffScreen) - distanceOffScreen;

            if (nextX < radius) {
                nextX = nextX + radius;
            }
            //checks if the given position is already taking, avoids balls stacking on top of each other
            while (checkForOverStacking(nextX, nextY) && i > 0) {
                distanceOffScreen += 25;
                nextX = (rand.nextInt(LevelsDatabase.SCREEN_WIDTH)) % (LevelsDatabase.SCREEN_WIDTH - (radius));
                nextY = rand.nextInt(LevelsDatabase.SCREEN_HEIGHT + distanceOffScreen) - distanceOffScreen;
                if (nextX < radius) {
                    nextX = nextX + radius;
                }
            }

            BinaryBall binaryBall = new BinaryBall(nextX, nextY, radius, generateBinary(i), i, LevelsDatabase.COLOR[0], Color.WHITE);
            allBinaryBalls.add(binaryBall);
        }
        //shuffle them balls baby
        Collections.shuffle(allBinaryBalls);
        startLevelTime = System.currentTimeMillis();

        ballSpawnPoint = -(distanceOffScreen + radius);
        ballsToClear = allBinaryBalls.size();
    }


    //returns the threshold, how many times on/off the screen before calling game over
    public int getThreshold() {
        return threshold;
    }

    /**
     * Removes the ball from the arraylist once touched
     *
     * @param x float representing the x position of the touch
     * @param y float representing the y postion of teh touch
     * @return boolean value true or false depending on whether the user touched
     *      the ball or not
     */
    public boolean findBall(float x, float y) {
        try {
            for (int i = 0; i < allBinaryBalls.size(); i++) {
                BinaryBall binaryBall = allBinaryBalls.get(i);
                float xDiff = x - binaryBall.getX();
                float yDiff = y - binaryBall.getY();
                float diameter = binaryBall.getRadius() * 2;
                if ((xDiff * xDiff + yDiff * yDiff) <= diameter * diameter) {
                    if (binaryBall.getDecimalValue() == currentBallToFind.getDecimalValue()) {
                        removeBall(allBinaryBalls.get(i));
                        ballsToClear--;
                        return true;
                    }
                }
            }
        } catch (NullPointerException e) {

        }
        return false;
    }

    /**
     * removes the ball from the arraylist
     *
     * @param ball
     */
    public synchronized void removeBall(BinaryBall ball) {
        allBinaryBalls.remove(ball);
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
     * @param height integer that represents where the ball is on the screen
     */
    public synchronized void update(int height) {
        for (int i = 0; i < allBinaryBalls.size(); i++) {
            int ballRadius = (int)allBinaryBalls.get(i).getRadius();
            allBinaryBalls.get(i).move(ballSpawnPoint, height + ballRadius);
            if (allBinaryBalls.get(i).getNumTimesOffScreen() >= threshold - 1) {
                if (allBinaryBalls.get(i).getNumTimesOffScreen() == threshold) {
                    removeBall(allBinaryBalls.get(i));
                    gameIsOver = true;
                } else {
                    allBinaryBalls.get(i).setColor(LevelsDatabase.COLOR[1]);
                }
            }
        }
    }

    /**
     * generates the binary value
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
    public synchronized void draw(Canvas canvas) {
        //WIPE THE CANVAS CLEAN
        canvas.drawRGB(176, 175, 175);
        Paint paint = new Paint();
        paint.setColor(LevelsDatabase.TEXT_COLOR);


        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int density = (int)metrics.density;
        int x = metrics.widthPixels / 2;
        int y = metrics.heightPixels - density * 100;


        if (allBinaryBalls.size() > 0) {

            for (int i = 0; i < allBinaryBalls.size(); i++) {

                //DRAW THE BALL
                if (isBinary) {
                    allBinaryBalls.get(i).draw(canvas, allBinaryBalls.get(i).getBinary(), isBinary);

                } else {
                    allBinaryBalls.get(i).draw(canvas, allBinaryBalls.get(i).getDecimalText(), isBinary);
                }

            }

            paint.setTextSize(100);

            if (!gameIsOver) {
                currentBallToFind = allBinaryBalls.get(0);
                canvas.drawText("" + mPlayerLevel, 10, 100, paint);
                if (isBinary) {
                    canvas.drawText("FIND " + currentBallToFind.getDecimalValue(), x - 150, y, paint);
                } else {
                    canvas.drawText("FIND " + currentBallToFind.getBinary(), x - 225, y, paint);
                }
            } else {
                // GameArenaActivity.stopTimer();
                if(displayWindow) {
                    displayWindow = false;
                    showGameOver();
                }
                currentBallToFind = null;

            }
        } else {
            //long finishTime = System.currentTimeMillis();
            paint.setTextSize(50);
            //Log.d("Your Time", ": " + finishTime);
            //LevelsDatabase.updateScore(mPlayerLevel, finishTime);

            //canvas.drawText("YOU WON THIS ROUND", 20, 300, paint);
            //showLevelPassword();
            //TODO add pop up button on options of the game
            //NEXT LEVEL
            if (ballsToClear == 0) {
                if (mPlayerLevel < LevelsDatabase.HIGHEST_LEVEL && displayWindow) {
                    displayWindow = false;
                    showLevelPassword();

                } else if (mPlayerLevel >= LevelsDatabase.HIGHEST_LEVEL && displayWindow) {
                    displayWindow = false;
                    showEndGame();
                }
            } else if (displayWindow){
                displayWindow = false;
                showGameOver();
                currentBallToFind = null;
            }
        }

    }

    /**
     * Increases binary ball velocity for every nth wrong guess.
     *
     * @return boolean value if velocity was increased or not
     */
    public boolean increaseBallVelocity() {
        int n = 5;
        if (wrongGuesses % n == 0) {
            for (int i = 0; i < allBinaryBalls.size(); i++) {
                allBinaryBalls.get(i).increaseVelocity();
            }
            return true;
        }
        return false;
    }

    /**
     * Increments the counter which tracks the number of incorrect guesses.
     */
    public void increaseWrongGuessCount() {
        wrongGuesses++;
    }


    /**
     * shows level password at end of the level beaten by using array list passwords in LevelsDatabase
     *
     */
    public void showLevelPassword() {
        final long elapsedTime = (System.currentTimeMillis() - startLevelTime) / 1000;
        LevelsDatabase.updateScore(mPlayerLevel, elapsedTime);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder helpBuilder = new AlertDialog.Builder(activity);
                helpBuilder.setTitle("LEVEL COMPLETE");
                String message = "This level took you " + elapsedTime + " seconds.";
                message += "\nThe fastest time: " + LevelsDatabase.getLevelScore(mPlayerLevel) + " seconds.";
                message += "\n\n" + "Level complete! The password for this level is" + " \"" + LevelsDatabase.passwords[mPlayerLevel] + "\".";
                message += "\n" + LevelsDatabase.passwordMeaning[mPlayerLevel];
                helpBuilder.setMessage(message);
                helpBuilder.setPositiveButton("GOT IT!",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                nextLevel(mPlayerLevel + 1);
                            }
                        });

                // Remember, create doesn't show the dialog
                AlertDialog helpDialog = helpBuilder.create();
                helpDialog.show();
            }
        });

    }

    /**
     * shows a message at the very end of the game letting the user know they have beaten the
     *      whole game.
     */

    public void showEndGame() {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder helpBuilder = new AlertDialog.Builder(activity);
                helpBuilder.setTitle("CONGRATULATIONS");
                helpBuilder.setMessage("YOU ARE THE NEW BINARY HERO");
                helpBuilder.setPositiveButton("THANK YOU",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                activity.finish();
                            }

                        });

                // Remember, create doesn't show the dialog
                AlertDialog helpDialog = helpBuilder.create();
                helpDialog.show();
            }
        });
    }

    /**
     * shows that the game is over because the user lost.  asks if they would like to continue or not
     */

    public void showGameOver() {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder helpBuilder = new AlertDialog.Builder(activity);
                helpBuilder.setTitle("GAME OVER")
                        .setMessage("CONTINUE")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                nextLevel(mPlayerLevel);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {(activity).finish();

                            }
                        });

                AlertDialog helpDialog = helpBuilder.create();
                helpDialog.show();
            }
        });
    }

}
