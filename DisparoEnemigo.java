
package com.spaceinvaders.limengyan.spaceinvaders;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class DisparoEnemigo {
  private int x,y;
  private Rect rectanguloPantalla;
  private int ancho;
  private Paint paint;
  private final int DESPLAZAMIENTO=20;
  public DisparoEnemigo(Enemigo enemigo) {
     this.x=enemigo.getX();
     this.y=enemigo.getY();
     this.rectanguloPantalla= enemigo.getRectanguloPantalla();
     ancho=this.rectanguloPantalla.width()/100;
     paint= new Paint();
     paint.setColor(Color.RED);
}
  
 public void mover() {
    y+=DESPLAZAMIENTO;
}

  public void dibujar(Canvas c) {
    c.drawRect(x-ancho/2,y,x+ancho/2,y+ancho*2,paint);
}
  
  public boolean estaFuera() {
     if (this.y>rectanguloPantalla.height()){
        return true;
   }
  return false;  
}
  
  
  public boolean colision(Nave navecita) {
     if (navecita.getY()+navecita.getBitmap().getHeight()/2>this.y-ancho*2
         && navecita.getY()-navecita.getBitmap().getHeight()/2<this.y
         && navecita.getX()+navecita.getBitmap().getWidth()/2>x-ancho/2
         && navecita.getX()-navecita.getBitmap().getWidth()/2<x+ancho/2
         ){
     return true;
    }
    return false;
  }
}
