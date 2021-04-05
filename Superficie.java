package com.spaceinvaders.limengyan.spaceinvaders;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class Superficie extends SurfaceView implements SurfaceHolder.Callback {
   private Rect rectanguloPantalla;
   private Hilo hilo;
   private Juego juego;
   private Bitmaps bitmaps;
   public Superficie(Context context) {
      super(context);
      this.getHolder().addCallback(this);
      DisplayMetrics dm= context.getResources().getDisplayMetrics();
      rectanguloPantalla= new Rect(0,0,dm.widthPixels,dm.heightPixels);
      bitmaps= new Bitmaps(this.getResources(),rectanguloPantalla.width());
      juego= new Juego(rectanguloPantalla,bitmaps);
    }
@Override
public void draw(Canvas c){
    super.draw(c);
    juego.dibujar(c);
}
@Override
public void surfaceCreated(SurfaceHolder holder) {
   hilo = new Hilo(this.getHolder(), this);
   hilo.setRunning(true);
   hilo.start();
}
@Override
public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
}
@Override
public void surfaceDestroyed(SurfaceHolder holder) {
   hilo.setRunning(false);
   while (hilo != null) {
   try {
       hilo.join();
       hilo = null;
    } catch (InterruptedException e) {
       e.printStackTrace();
    }
  }
  hilo = null;
}
@Override
public boolean onTouchEvent(MotionEvent me){
   //Toast.makeText(this.getContext(),"pulsado",Toast.LENGTH_SHORT).show();
   return juego.pulsado(me);
  }
}
