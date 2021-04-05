package com.spaceinvaders.limengyan.spaceinvaders;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Nave {
   private Bitmaps bitmaps;
   private Rect rectanguloPantalla;
   private Paint paint;
   private Bitmap bitmap;
   private int x;
   private int y;
   private final int DESPLAZAMIENTO=14;
   private int mover;
  
   public Nave(Bitmaps bitmaps, Rect rectanguloPantalla) {
      this.bitmaps = bitmaps;
      bitmap=bitmaps.getNavecita();
      this.rectanguloPantalla= rectanguloPantalla;
      x= rectanguloPantalla.centerX();
      y= rectanguloPantalla.height()*17/20;
      paint= new Paint();
      mover=0;
    }
  
    public void dibujar(Canvas c) {
      c.drawBitmap(bitmap, this.x-bitmap.getWidth()/2, this.y-
       bitmap.getHeight()/2,paint);
    }
  
    public void moverIzda() {
       x-=DESPLAZAMIENTO;
       if (x<bitmap.getWidth()/2){
           x=bitmap.getWidth()/2;
       }
    }
  
    public void moverDcha() {
        x+=DESPLAZAMIENTO;
        if (x>rectanguloPantalla.width()-bitmap.getWidth()/2){
            x=rectanguloPantalla.width()-bitmap.getWidth()/2;
         }
    }
  
    public void intentarMoverIzda() {
         mover=-1;
    }
  
    public void intentarMoverDcha() {
         mover=1;
    } 
    public void intentarParar() {
         mover=0;
    }
    public void mover() {
        if (mover==-1){
        moverIzda();
        return;
        }
        if (mover==1){
        moverDcha();
        return;
        }
     }
  
    public int getX() {
         return x;
    }

    public int getY() {
         return y;
    }
  
    public Bitmap getBitmap() {
         return bitmap;
    }
  
    public Rect getRectanguloPantalla() {
         return rectanguloPantalla;
    }
}







