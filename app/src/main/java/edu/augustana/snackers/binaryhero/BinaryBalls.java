package edu.augustana.snackers.binaryhero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;


public class BinaryBalls {
    private final int RADIUS = 50;
    private final int REVERSE = -1;
    private float x;
    private float y;
    private int velX;
    private int velY;


    public BinaryBalls(float posX, float posY) {
        x = posX;
        y = posY;
        velX = 0;
        velY = 1;
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
        } else if (y < topWall + RADIUS) {
            y = topWall + RADIUS;
           // velY *= REVERSE;
        }

        if (x > rightWall - RADIUS) {
            x = rightWall - RADIUS;
            velX *= REVERSE;
        } else if (x < leftWall + RADIUS) {
            x = leftWall + RADIUS;
            velX *= REVERSE;
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

    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        canvas.drawCircle(x, y, RADIUS, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(RADIUS);
        canvas.drawText("000", x-40, y+25,paint);

    }
    public float getY(){
        return y;
    }
}
