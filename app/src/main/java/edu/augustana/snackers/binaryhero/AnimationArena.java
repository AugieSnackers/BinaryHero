package edu.augustana.snackers.binaryhero;



import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

public class AnimationArena {
    private BinaryBall binaryBall;
    static ArrayList<BinaryBall> allBinaryImages = new ArrayList<BinaryBall>();
    public AnimationArena () {
        //INSTANTIATE THE BALL
        float current=50;
        for(int i =0; i<5;i++){

            binaryBall = new BinaryBall(current,10+current,generateBinary(i));
            allBinaryImages.add(binaryBall);
           // Log.d("BHERO", "x value = " + allBinaryImages.get(i).getX());
            current=current+150;
        }

    }


    public void update (int width, int height) {
        for(int i =0;i< allBinaryImages.size();i++){
            allBinaryImages.get(i).move(0,0, width,height);
        }
    }
    public String generateBinary(int x){
        String binary = Integer.toString(x, 2);
        int diff = 4-binary.length();
        String zeroes ="";
        for(int i=1;i<=diff;i++){
           zeroes =zeroes+"0";
        }
        return zeroes+binary;
    }
    public void draw (Canvas canvas) {
        //WIPE THE CANVAS CLEAN
        canvas.drawRGB(156, 174, 216);


        for(int i =0;i< allBinaryImages.size();i++){
            //DRAW THE BALL
            allBinaryImages.get(i).draw(canvas,allBinaryImages.get(i).getBinary());
            //Log.d("BHERO", "x value = " + allBinaryImages.get(i).getX());

        }
    }
    public static void findBall(float x, float y){
        for(int i = 0; i< allBinaryImages.size(); i++) {
           if( Math.abs(allBinaryImages.get(i).getY() - y)<=10&&Math.abs(allBinaryImages.get(i).getX() - x)<=10){
               allBinaryImages.remove(i);
           }
        }

    }
}
