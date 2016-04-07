package edu.augustana.snackers.binaryhero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;


public class BinaryBall {
    private final int RADIUS = 50;
    private final int REVERSE = -1;
    private float x;
    private float y;
    private int velX;
    private int velY;
    private String textBinary;

    public BinaryBall(float posX, float posY,String text) {
        x = posX;
        y = posY;
        velX = 0;
        velY = 1;
        textBinary =text;
    }

    public void move(int leftWall, int topWall,
                     int rightWall, int bottomWall) {
        //MOVE BALL
        x += velX;
        y += velY;

        //CHECK FOR COLLISIONS ALONG WALLS
        if (y > bottomWall - RADIUS) {
            y = topWall - RADIUS;
            //velY *= REVERSE;
        }

        //http://stackoverflow.com/questions/22909446/ball-to-ball-collision-resolution
//        for(int i = 0; i< allBinaryImages.size(); i++) {
//            if (this != allBinaryImages.get(i)) {
//                float xDiff = x - allBinaryImages.get(i).x;
//                float yDiff = y - allBinaryImages.get(i).y;
//                int radii = allBinaryImages.get(i).RADIUS + RADIUS;
//                if ((xDiff * xDiff + yDiff * yDiff) < radii * radii) {
//                    velX *= REVERSE;
//                    velY *= REVERSE;
//                }
//            }
//        }
    }

    public void draw(Canvas canvas, String text) {

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x, y, RADIUS, paint);
        paint.setColor(Color.WHITE);

        paint.setTextSize(RADIUS-10);
        canvas.drawText(text, (x-RADIUS)+5, y+(RADIUS/2)-5,paint);

    }
    public float getY(){
        return y;
    }

    public float getX(){
        return x;
    }
    public String getBinary(){
        return textBinary;
    }
}
