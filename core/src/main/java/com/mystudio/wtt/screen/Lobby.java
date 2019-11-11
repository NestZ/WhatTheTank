package com.mystudio.wtt.screen;

import org.mini2Dx.core.game.GameContainer;

public class Lobby extends Screen {
      public static final int ID = 10;

      @Override
      public void initialise(GameContainer gc){
            this.assetLoad(gc);
      }

      @Override
      public int getId(){
            return ID;
      }
}