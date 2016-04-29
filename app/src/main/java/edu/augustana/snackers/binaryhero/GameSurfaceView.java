package edu.augustana.snackers.binaryhero;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Copied from the textBook need more commenting
 */

public class
        GameSurfaceView extends SurfaceView implements
        SurfaceHolder.Callback {

    private GameThread bounceThread;

    public GameSurfaceView(Context context, GameArena gameArena, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        //CREATE A NEW THREAD
        bounceThread = new GameThread(holder, gameArena);
    }

    //IMPLEMENT THE INHERITED ABSTRACT METHODS
    public void surfaceChanged(SurfaceHolder holder, int format,
                               int width, int height) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        bounceThread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        bounceThread.endBounce();
        Thread dummyThread = bounceThread;
        bounceThread = null;
        dummyThread.interrupt();
    }
}
