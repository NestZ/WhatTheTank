package com.mystudio.wtt.entity.tank;

/**
 * Class to store client's current key status and send to update module.
 */

public class Key{
      /**
       * All of possible key.
       */
      private boolean left;
      private boolean right;
      private boolean up;
      private boolean down;

      /**
       * Set client's key status.
       * @param moveDir direction that client is moving
       * @param status true if client is pushing key and going in that direction
       * otherwise false
       */
      public void set(char moveDir, int status){
            boolean b = false;
            if(status == 1)b = true;
            switch(moveDir){
                  case 'u' :
                        this.up = b;
                        break;
                  case 'd' :
                        this.down = b;
                        break;
                  case 'l' :
                        this.left = b;
                        break;
                  case 'r' :
                        this.right = b;
            }
      }

      /**
       * Return left arrow key's status.
       * @return true if client is pushing left arrow key otherwise false
       */
      public boolean left(){
            return this.left;
      }

      /**
       * Return right arrow key's status.
       * @return true if client is pushing right arrow key otherwise false
       */
      public boolean right(){
            return this.right;
      }

      /**
       * Return down arrow key's status.
       * @return true if client is pushing down arrow key otherwise false
       */
      public boolean down(){
            return this.down;
      }

      /**
       * Return up arrow key's status.
       * @return true if client is pushing up arrow key otherwise false
       */
      public boolean up(){
            return this.up;
      }
}