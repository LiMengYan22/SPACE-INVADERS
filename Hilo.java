package com.spaceinvaders.limengyan.spaceinvaders;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Hilo extends Thread{
    // private boolean running;
   private SurfaceHolder surfaceHolder;
   private Superficie superficie;
   private boolean run = false;
   private Canvas c;
   public Hilo(SurfaceHolder holder, Superficie superficie) {
       this.surfaceHolder= holder;
       this.superficie= superficie;
   }
  
   public void setRunning(boolean running) {
       this.run = running;
   }
  
   @Override
   public void run() {
        while (run) {
           c = null;
           try {
           c = surfaceHolder.lockCanvas(null);
           synchronized (surfaceHolder) {
               superficie.draw(c);
               }
           } catch (Exception e) {
             e.printStackTrace();
           }
           finally {
             if (c != null) {
                 surfaceHolder.unlockCanvasAndPost(c);
             }
           }
          try {
              sleep(25);
          } catch (InterruptedException e) {
            e.printStackTrace();
            }
          }
     }
}
