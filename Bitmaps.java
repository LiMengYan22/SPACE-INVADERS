
package com.spaceinvaders.limengyan.spaceinvaders;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import java.util.ArrayList;

public class Bitmaps {
  Bitmap navecita, navecita2;
  Bitmap vida;
  ArrayList<Bitmap> estrellas = new ArrayList<>();
  ArrayList<Bitmap> enemigos = new ArrayList<>();
  public Bitmaps(Resources res, int width) {
  navecita=redimensionarEnAncho(BitmapFactory.decodeResource(res, R.drawable.nave1), width, (float)0.15);
  vida=redimensionarEnAncho(BitmapFactory.decodeResource(res, R.drawable.nave1), width, (float)0.05);
  navecita2=redimensionarEnAncho(BitmapFactory.decodeResource(res, R.drawable.nave2), width, (float)0.15);
  estrellas.add(redimensionarEnAncho(BitmapFactory.decodeResource(res, R.drawable.estrella1), width, (float)0.15));
  estrellas.add(redimensionarEnAncho(BitmapFactory.decodeResource(res, R.drawable.estrella2), width, (float)0.08));
  estrellas.add(redimensionarEnAncho(BitmapFactory.decodeResource(res, R.drawable.estrella3), width, (float)0.15));
  estrellas.add(redimensionarEnAncho(BitmapFactory.decodeResource(res, R.drawable.estrella4), width, (float)0.8));
  enemigos.add(redimensionarEnAncho(BitmapFactory.decodeResource(res, R.drawable.enemigo1), width, (float)0.15));
  enemigos.add(redimensionarEnAncho(BitmapFactory.decodeResource(res, R.drawable.enemigo2), width, (float)0.15));
  }
  public Bitmap getNavecita() {
  return navecita;
  }
  public Bitmap getNavecita2() {
  return navecita2;
  }
  public ArrayList<Bitmap> getEstrellas() {
  return estrellas;
  }
  public Bitmap getVida() {
  return vida;
  }
  public ArrayList<Bitmap> getEnemigos() {
  return enemigos;
  }
  private Bitmap redimensionarEnAncho(Bitmap b, float ancho, float r) {
  Matrix matrix = new Matrix();
  float xScale;
  xScale=ancho*r/b.getWidth();
  matrix.postScale(xScale, xScale);
  return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, false);
 }
}
