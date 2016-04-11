package edu.augustana.snackers.binaryhero;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * copied from textbook needs commenting
 */
public class BounceThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameArena gameArena;
    private boolean isRunning;

    public BounceThread(SurfaceHolder sh, GameArena gameArena) {

        isRunning = true;
        surfaceHolder = sh;
        this.gameArena = gameArena;
    }

    public void run() {
        try {
            while (isRunning) {
                Canvas canvas = surfaceHolder.lockCanvas();

                gameArena.update(canvas.getWidth(),
                        canvas.getHeight());
                gameArena.draw(canvas);
                gameArena.drawPlayLabel(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void endBounce() {
        isRunning = false;
    }
}
