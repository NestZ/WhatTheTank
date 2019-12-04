package com.mystudio.wtt.utils;

import com.mystudio.wtt.entity.tank.Tank;
import com.mystudio.wtt.screen.Field;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.mystudio.wtt.client.ClientThread;
import com.mystudio.wtt.client.Protocol;

/**
 * Handle all input from user include moving, shooting.
 * Implement from InputProcessor.
 * 
 * @see com.badlogic.gdx.InputProcessor
 * 
 * @author NestZ
 */

public class InputHandler implements InputProcessor{
      /**
       * Store controlling tank and key status.
       */
      private Tank tank;
      public boolean keyDown = false;
      
      /**
       * Constructor for set controlling tank and current client.
       * @param tank controlling tank
       * @param client current client
       */
      public InputHandler(Tank tank){
            this.tank = tank;
      }

      /**
       * Send moving package to server.
       * @param moveDir moving direction
       * @param status key status
       * 0 : key up
       * 1 : key down
       */
      public void sendMove(char moveDir, int status){
            ClientThread.sendToServer(Protocol.updatePackage(moveDir, status, this.tank.getID(), this.tank.getX(), this.tank.getY()));
      }

      /**
       * Send shooting package and client's information to server.
       */
      public void sendShoot(){
            if(this.tank.shootAble()){
                  ClientThread.sendToServer(Protocol.shootPackage(this.tank.getID(), this.tank.getDir(),
                  this.tank.getX() + this.tank.getWidth() / 2, this.tank.getY() + this.tank.getHeight() / 2));
            }
      }
      
      /**
       * Overrided method.
       * Called when player's key is pushed.
       * @param keycode pushed keycode
       */
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
                        Field.s.playshotsound();
                        break;
            }
            return false;
      }

      /**
       * Overrided method.
       * Called when player's key is released.
       * @param keycode released keycode
       */
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

      /**
       * Overrided empty method.
       */
      @Override
      public boolean keyTyped (char character){
            return false;
      }

      /**
       * Overrided empty method.
       */
      @Override
      public boolean mouseMoved (int x, int y){
            return false;
      }

      /**
       * Overrided empty method.
       */
      @Override
      public boolean touchDown (int x, int y, int pointer, int button){
            return false;
      }

      /**
       * Overrided empty method.
       */
      @Override
      public boolean touchUp (int x, int y, int pointer, int button){
            return false;
      }

      /**
       * Overrided empty method.
       */
      @Override
      public boolean touchDragged (int x, int y, int pointer){
            return false;
      }

      /**
       * Overrided empty method.
       */
      @Override
      public boolean scrolled (int amount){
            return false;
      }
}