package com.spaceinvaders.limengyan.spaceinvaders;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Random;

public class Estrella {
   private int x,y;
   Bitmap imagen;
   Paint paint=new Paint();
   Rect rectanguloPantalla;
   private int DESPLAZAMIENTO=7;
   public Estrella(Rect rectanguloPantalla, ArrayList<Bitmap> estrellas) {
       Random random= new Random();
       this.rectanguloPantalla= rectanguloPantalla;
       this.imagen= estrellas.get(random.nextInt(estrellas.size()));
       y=-imagen.getHeight();
       x= random.nextInt(rectanguloPantalla.width());
       paint.setAlpha(150);
   }
  
   public void dibujar(Canvas c) {
   c.drawBitmap(imagen, this.x-imagen.getWidth()/2, this.y- imagen.getHeight()/2,paint);
   }
  
   public void mover() {
   y+=DESPLAZAMIENTO;
   }
  
   public boolean estaFuera() {
       if (this.y> rectanguloPantalla.height()+imagen.getHeight()){
          return true;
          }
       return false;
    }
}
