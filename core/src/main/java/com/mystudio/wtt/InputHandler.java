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

      public void sendToServer(char moveDir, int status){
            this.client.sendToServer("Update" + this.tank.getID() + moveDir + Integer.toString(status));
      }
      
      @Override
      public boolean keyDown (int keycode){
            this.keyDown = true;
            switch(keycode){
                  case Keys.UP :
                        this.sendToServer('u', 1);
                        break;
                  case Keys.DOWN :
                        this.sendToServer('d', 1);
                        break;
                  case Keys.LEFT :
                        this.sendToServer('l', 1);
                        break;
                  case Keys.RIGHT :
                        this.sendToServer('r', 1);
                        break;
            }
            return false;
      }

      @Override
      public boolean keyUp (int keycode){
            this.keyDown = false;
            switch(keycode){
                  case Keys.UP :
                        this.sendToServer('u', 0);
                        break;
                  case Keys.DOWN :
                        this.sendToServer('d', 0);
                        break;
                  case Keys.LEFT :
                        this.sendToServer('l', 0);
                        break;
                  case Keys.RIGHT :
                        this.sendToServer('r', 0);
                        break;
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