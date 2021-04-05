package com.spaceinvaders.limengyan.spaceinvaders;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Environment;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
public class Juego {
  private Rect rectanguloPantalla;
  private Paint paint;
  private Paint paint2;
  private Paint paint3;
  private Bitmaps bitmaps;
  private Nave navecita;
  private ArrayList<Disparo> disparos = new ArrayList<>();
  private ArrayList<DisparoEnemigo> disparosEnemigos = new ArrayList<>();
  private ArrayList<Estrella> estrellas = new ArrayList<>();
  private ArrayList<Enemigo> enemigos = new ArrayList<>();
  private Disparo disparoAux;
  private Enemigo enemigoAux;
  private Handler handler= new Handler();
  private int contadorTiempoEstrellas;
  private boolean bordeAlcanzado;
  private int vidas;
  private final int VIDAS=4;
  private boolean muerto;
  private int puntos;
  private int enemigoQueDispara;
  private Random random=new Random();
  private int tocaDisparar;
  
  public Juego(Rect rectanguloPantalla, Bitmaps bitmaps) {
    this.rectanguloPantalla= rectanguloPantalla;
    navecita= new Nave(bitmaps,rectanguloPantalla);
    this.bitmaps=bitmaps;
    this.paint= new Paint();
    this.paint2= new Paint();
    this.paint3= new Paint();
    // this.paint.setColor(Color.rgb(32,111,170));
    this.paint.setColor(Color.rgb(11,11,30));
    this.paint2.setColor(Color.rgb(200,200,200));
    this.paint2.setTextAlign(Paint.Align.CENTER);
    this.paint2.setTextSize(rectanguloPantalla.width()/8);
    this.paint3.setColor(Color.rgb(111,111,111));
    this.paint3.setTextAlign(Paint.Align.CENTER);this.paint3.setTextSize(rectanguloPantalla.width()/15);
    //this.paint3.setStandardFontFamily (String.Consolas);
    
    contadorTiempoEstrellas=0;
    // this.paint.setStyle(Paint.Style.STROKE);
    // this.paint.setStrokeWidth(8);
    vidas=vidas;
    vidas=VIDAS;
    muerto=false;
    puntos=0;
    tocaDisparar=1;
    iniciarJuego();
    }
  
private void iniciarJuego() {
     if (vidas<=0) {
       muerto=true;
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
              bordeAlcanzado = false;
              disparos.clear();
              disparosEnemigos.clear();
              colocarEnemigos();
              vidas = VIDAS;
              puntos=0;
              muerto = false;
              tocaDisparar=1;
           }
        } ,2500);
          return;
        }
  
    tocaDisparar=1;
    bordeAlcanzado=false;
    disparos.clear();
    disparosEnemigos.clear();
    colocarEnemigos();
  }
  
  
private void colocarEnemigos() {
   enemigos.clear();
   disparos.clear();
   disparosEnemigos.clear();
   for (int i=0; i<4;i++){
      for (int j=0;j<4;j++){
      enemigos.add(new Enemigo(i,j,bitmaps.getEnemigos(),rectanguloPantalla, handler));
      }
    }
}
  
  
public void dibujar(Canvas c) {
   if (!muerto) {
      navecita.mover();
   for (int i=0;i<disparos.size();i++){
      disparos.get(i).mover();
   }
   for (int i=0;i<disparos.size();i++){
     if (disparos.get(i).estaFuera()){
     disparos.remove(disparos.get(i));
    }
}  
     
    for (int i =0; i<disparosEnemigos.size();i++){
         disparosEnemigos.get(i).mover();
    }
    for (int i =0; i<disparosEnemigos.size();i++){
       if (disparosEnemigos.get(i).estaFuera()){
             disparosEnemigos.remove(disparosEnemigos.get(i));
       }
}
     
if (tocaDisparar%50==0){
   enemigoQueDispara=random.nextInt(enemigos.size());
   dispararEnemigo(enemigos.get(enemigoQueDispara));
   tocaDisparar=0;
}
tocaDisparar++;
     
     
for (int i = 0; i < enemigos.size(); i++) {
    if (enemigos.get(i).mover()) {
        bordeAlcanzado = true;
    }
//////////////////////////////////////// dispararEnemigo(enemigos.get(i));
}
     
    if (bordeAlcanzado){
        bajarYCambiarDirEnemigos();
    }
    generarEstrella();
    for (int i=0;i<estrellas.size();i++){
         estrellas.get(i).mover();
    }
    for (int i=0;i<estrellas.size();i++){
        if (estrellas.get(i).estaFuera()){
            estrellas.remove(estrellas.get(i));
         }
     }
    for (int i=0; i<disparos.size();i++){
        disparoAux=disparos.get(i);
        for (int j=0;j<enemigos.size();j++){
            enemigoAux=enemigos.get(j);
            if (disparoAux.colision(enemigoAux)){
               //enemigos.get(j).explotar();
                 enemigos.remove(enemigoAux);
                 disparos.remove(disparoAux);
                 puntos+=100;
             }
          }
      }
     
     for (int i=0;i<disparosEnemigos.size();i++){
           if (disparosEnemigos.get(i).colision(navecita)){
           //navecitas.explotar();
           vidas--;
           iniciarJuego();
           }
      }
     
     
      for (int i=0;i<enemigos.size();i++){
           if (enemigos.get(i).colision(navecita) || enemigos.get(i).getY()
+enemigos.get(i).getBitmap().getHeight()/2>rectanguloPantalla.height()){
               vidas--;
               iniciarJuego();
          }
       }
   }
   ////////barrera de separacion entre mover y dibujar////////////////////////////
   c.drawRect(0,0,rectanguloPantalla.width(),rectanguloPantalla.height(),paint);
   for (int i=0;i<estrellas.size();i++){
       estrellas.get(i).dibujar(c);
   }
   for (int i=0;i<enemigos.size();i++){
       enemigos.get(i).dibujar(c);
   }
   for (int i=0;i<disparos.size();i++){
       disparos.get(i).dibujar(c);
   }
   for (int i=0;i<disparosEnemigos.size();i++){
       disparosEnemigos.get(i).dibujar(c);
   }
   for (int i=1;i<vidas;i++){
       c.drawBitmap(bitmaps.getVida(),
           i *rectanguloPantalla.width()/15 - bitmaps.getVida().getWidth(),
           rectanguloPantalla.height()- bitmaps.getVida().getHeight()*11/10,
           paint);
      }
      c.drawText(puntos+"",rectanguloPantalla.centerX(),
rectanguloPantalla.height()/20, paint3);
     if (muerto) {
     c.drawText(" GAME OVER ", rectanguloPantalla.centerX(),
rectanguloPantalla.centerY(), paint2);
     c.drawText("solomongo.com", rectanguloPantalla.centerX() -
bitmaps.getVida().getWidth(),
     rectanguloPantalla.centerY()*11/10,paint3);
    }else{
        navecita.dibujar(c);
    }
    //c.drawText(enemigos.size()+"",100,100,paint2);
    if (enemigos.size()==0){
        iniciarJuego();
        }
    }
  
  
    private void dispararEnemigo(Enemigo enemigo) {
        disparosEnemigos.add(new DisparoEnemigo(enemigo));
    }
    private void bajarYCambiarDirEnemigos() {
    for (int i=0;i<enemigos.size();i++){
        enemigos.get(i).bajar();
    }
    for (int i=0;i<enemigos.size();i++){
        enemigos.get(i).cambiarDireccion();
    }
    bordeAlcanzado=false;
 } 
  
    private void generarEstrella() {
       contadorTiempoEstrellas++;
       if (contadorTiempoEstrellas%8==0){
           estrellas.add(new Estrella(rectanguloPantalla, bitmaps.getEstrellas()));
           contadorTiempoEstrellas=0;
       }
    }
  
    public boolean pulsado(MotionEvent me) {
    //no funciona si mantengo pulsado en la zona de disparar
    for (int i=0;i<me.getPointerCount();i++) {
        if ((me.getActionMasked() == MotionEvent.ACTION_DOWN ||
             me.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN)
            && me.getY(i) < rectanguloPantalla.height() * 7 / 10) {
          disparar();
       }
       if (me.getActionMasked() == MotionEvent.ACTION_UP) {
           navecita.intentarParar();
       }
       if (me.getActionMasked() == MotionEvent.ACTION_DOWN ||
                me.getActionMasked() == MotionEvent.ACTION_MOVE) {
          if (me.getX(i) < rectanguloPantalla.centerX() &&
                me.getY(i) > rectanguloPantalla.height() * 7 / 10) {
             navecita.intentarMoverIzda();
             return true;
          }
       if (me.getX(i) > rectanguloPantalla.centerX() &&
              me.getY(i) > rectanguloPantalla.height() * 7 / 10) {
         navecita.intentarMoverDcha();
         return true;
         }
        }
     }
     return false;
    }

    private void disparar() {
       if (disparos.size()<2){
       disparos.add(new Disparo(navecita));
     }
}
}

