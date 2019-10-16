package com.mystudio.wtt;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class InputHandler implements InputProcessor{
      private Tank tank;
      private ClientStarter client;
      public boolean keyDown = false;
      
      public InputHandler(Tank tank, ClientStarter client){
            this.tank = tank;
            this.client = client;
      }

      public void sendToServer(){
            this.client.sendToServer("Update" + this.tank.getID() + "x" + this.tank.getX() + "y" + this.tank.getY() + ":" + this.tank.getDir());
      }
      
      @Override
      public boolean keyDown (int keycode){
            this.keyDown = true;
            switch(keycode){
                  case Keys.UP : 
                        this.tank.setMove(1);
                        break;
                  case Keys.DOWN :
                        this.tank.setMove(2);
                        break;
                  case Keys.LEFT :
                        this.tank.setMove(3);
                        break;
                  case Keys.RIGHT :
                        this.tank.setMove(4);
                        break;
                  default : this.tank.setMove(0);
            }
            return false;
      }

      @Override
      public boolean keyUp (int keycode){
            this.keyDown = false;
            switch(keycode){
                  case Keys.UP : 
                        this.tank.setMove(5);
                        break;
                  case Keys.DOWN :
                        this.tank.setMove(6);
                        break;
                  case Keys.LEFT :
                        this.tank.setMove(7);
                        break;
                  case Keys.RIGHT :
                        this.tank.setMove(8);
                        break;
                  default : this.tank.setMove(0);
            }
            return false;
      }

      @Override
      public boolean keyTyped (char character){
            return false;
      }

      @Override
      public boolean mouseMoved (int x, int y){
            return false;
      }

      @Override
      public boolean touchDown (int x, int y, int pointer, int button){
            return false;
      }

      @Override
      public boolean touchUp (int x, int y, int pointer, int button){
            return false;
      }

      @Override
      public boolean touchDragged (int x, int y, int pointer){
            return false;
      }

      @Override
      public boolean scrolled (int amount){
            return false;
      }
}