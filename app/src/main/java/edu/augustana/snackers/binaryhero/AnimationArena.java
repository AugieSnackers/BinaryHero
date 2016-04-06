package edu.augustana.snackers.binaryhero;



import android.graphics.Canvas;

import java.util.ArrayList;

public class AnimationArena {
    private BinaryBalls mBinaryImages;
    static ArrayList<BinaryBalls> allBinaryImages = new ArrayList<BinaryBalls>();
    public AnimationArena () {
        //INSTANTIATE THE BALL
        for(int i =0; i<4;i++){
            mBinaryImages = new BinaryBalls(10*i,10);
            allBinaryImages.add(mBinaryImages);
        }

    }


    public void update (int width, int height) {
        mBinaryImages.move(0, 0, width, height);
        for(int i =0;i< allBinaryImages.size();i++){
            allBinaryImages.get(i).move(0,0, width,height);
        }
    }

    public void draw (Canvas canvas) {
        //WIPE THE CANVAS CLEAN
        canvas.drawRGB(156, 174, 216);

        //DRAW THE BALL
        mBinaryImages.draw(canvas);
        for(int i =0;i< allBinaryImages.size();i++){
            allBinaryImages.get(i).draw(canvas);
        }
    }
    public static void findBall(float x, float y){
        for(int i = 0; i< allBinaryImages.size(); i++) {
           if( allBinaryImages.get(i).getY() - y<=10){
                allBinaryImages.get(i);
           }
        }

    }
}
