
package com.spaceinvaders.limengyan.spaceinvaders;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import java.util.ArrayList;

public class Enemigo {
  private ArrayList<Bitmap> enemigos;
  private Rect rectanguloPantalla;
  private int x, y;
  private Bitmap bitmap;
  private Handler handler;
  private Paint paint = new Paint();
  private int direccion;
  private final int DESPLAZAMIENTO=5;
  
  public Enemigo(int i, int j, ArrayList<Bitmap> enemigos, Rect rectanguloPantalla,Handler 
                 handler) {
     this.handler=handler;
     this.enemigos= enemigos;
     this.rectanguloPantalla = rectanguloPantalla;
     x= (j+1) * rectanguloPantalla.width()/5;
     y= (i+1) * rectanguloPantalla.width()/5;
     bitmap= enemigos.get(1);
     cambiarImagen();
     direccion=-1;
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
  
private void cambiarImagen() {
   if (bitmap==enemigos.get(1)){
       bitmap=enemigos.get(0);
  }else{
       bitmap=enemigos.get(1);
       }
    handler.postDelayed(new Runnable() {
       @Override
       public void run() {
         cambiarImagen();
       }
     },300);
   }
  
  public void dibujar(Canvas c) {
      c.drawBitmap(bitmap, this.x-bitmap.getWidth()/2, this.y-bitmap.getHeight()/2,paint);
   }
  
  public boolean mover() {
      x+=DESPLAZAMIENTO*direccion;
      if(x-bitmap.getWidth()/2<0){
      //x=bitmap.getWidth()/2;
      return true;
      }
      if(x+bitmap.getWidth()/2>rectanguloPantalla.width()){
      //x=rectanguloPantalla.width()-bitmap.getWidth()/2;
      return true;
      }
   return false;
  }
  
  public void cambiarDireccion() {
      direccion*=-1;
      }

  public void bajar() { y+=bitmap.getHeight()/2;
      }
  
  public boolean colision(Nave navecita) {
      if (navecita.getY() + navecita.getBitmap().getHeight() / 2 > this.y -
      this.bitmap.getHeight() / 2
      && navecita.getY() - navecita.getBitmap().getHeight() / 2 < this.y +
      this.bitmap.getHeight() / 2
      && navecita.getX() + navecita.getBitmap().getWidth() / 2 > x -
      this.bitmap.getWidth() / 2
      && navecita.getX() - navecita.getBitmap().getWidth() / 2 < x +
      this.bitmap.getWidth() / 2
      ) {
      return true;
     }
   return false;
}
  
   public Rect getRectanguloPantalla() {
     return rectanguloPantalla;
     }
}
