package com.mystudio.wtt;

public class Key{
      private boolean left;
      private boolean right;
      private boolean up;
      private boolean down;

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

      public boolean left(){
            return this.left;
      }

      public boolean right(){
            return this.right;
      }

      public boolean down(){
            return this.down;
      }

      public boolean up(){
            return this.up;
      }
}