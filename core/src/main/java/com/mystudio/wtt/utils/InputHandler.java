package com.mystudio.wtt.utils;

import com.mystudio.wtt.entity.tank.Tank;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.mystudio.wtt.client.ClientStarter;
import com.mystudio.wtt.client.Protocol;

public class InputHandler implements InputProcessor{
      private Tank tank;
      private ClientStarter client;
      public boolean keyDown = false;
      
      public InputHandler(Tank tank, ClientStarter client){
            this.tank = tank;
            this.client = client;
      }

      public void sendMove(char moveDir, int status){
            this.client.sendToServer(Protocol.updatePackage(moveDir, status, this.tank.getID(), this.tank.getX(), this.tank.getY()));
      }

      public void sendShoot(){
            this.client.sendToServer(Protocol.shootPackage(this.tank.getID(), this.tank.getDir(),
                                    this.tank.getX() + this.tank.getWidth() / 2, this.tank.getY() + this.tank.getHeight() / 2));
      }
      
      @Override
      public boolean keyDown (int keycode){
            this.keyDown = true;
            switch(keycode){
                  case Keys.UP :
                        this.sendMove('u', 1);
                        break;
                  case Keys.DOWN :
                        this.sendMove('d', 1);
                        break;
                  case Keys.LEFT :
                        this.sendMove('l', 1);
                        break;
                  case Keys.RIGHT :
                        this.sendMove('r', 1);
                        break;
                  case Keys.SPACE :
                        this.sendShoot();
                        break;
            }
            return false;
      }

      @Override
      public boolean keyUp (int keycode){
            this.keyDown = false;
            switch(keycode){
                  case Keys.UP :
                        this.sendMove('u', 0);
                        break;
                  case Keys.DOWN :
                        this.sendMove('d', 0);
                        break;
                  case Keys.LEFT :
                        this.sendMove('l', 0);
                        break;
                  case Keys.RIGHT :
                        this.sendMove('r', 0);
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