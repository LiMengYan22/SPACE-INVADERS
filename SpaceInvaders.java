package com.spaceinvaders.limengyan.spaceinvaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SpaceInvaders extends AppCompatActivity {
   private Superficie superficie;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       superficie= new Superficie(this.getApplicationContext());
       this.setContentView(superficie);
    }
}


