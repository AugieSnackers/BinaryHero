package edu.augustana.snackers.binaryhero;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * copied from textbook needs commenting
 */
public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameArena gameArena;
    private boolean isRunning;

    public GameThread(SurfaceHolder sh, GameArena gameArena) {

        isRunning = true;
        surfaceHolder = sh;
        this.gameArena = gameArena;
    }

    public void run() {
        try {
            while (isRunning) {
                // record current time millis
                Canvas canvas = surfaceHolder.lockCanvas();

                gameArena.update(canvas.getWidth(),
                        canvas.getHeight());
                gameArena.draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
                // find elapsed time
                //Thread.sleep(100 - elapsed time maybe?);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void endBounce() {
        isRunning = false;
    }
}
