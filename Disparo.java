package com.spaceinvaders.limengyan.spaceinvaders;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Disparo {
  private int x,y;
  private Rect rectanguloPantalla;
  private int ancho;
  private Paint paint;
  private final int DESPLAZAMIENTO=30;
  public Disparo(Nave navecita) {
     this.x=navecita.getX();
     this.y=navecita.getY();
     this.rectanguloPantalla= navecita.getRectanguloPantalla();
     ancho=this.rectanguloPantalla.width()/100;
     paint= new Paint();
     paint.setColor(Color.YELLOW);
}
  
public void mover() {
   y-=DESPLAZAMIENTO;
}
  
public void dibujar(Canvas c) {
   c.drawRect(x-ancho/2,y-ancho*2,x+ancho/2,y,paint);
}
  
public boolean estaFuera() {
   if (this.y< ancho*-3){
   return true;
   }
  return false;
}

  
public boolean colision(Enemigo enemigo) {
   if (enemigo.getY()+enemigo.getBitmap().getHeight()/2>this.y-ancho*2
       && enemigo.getY()-enemigo.getBitmap().getHeight()/2<this.y
       && enemigo.getX()+enemigo.getBitmap().getWidth()/2>x-ancho/2
       && enemigo.getX()-enemigo.getBitmap().getWidth()/2<x+ancho/2
       ){
    return true;
    }
  return false;
  }
}
