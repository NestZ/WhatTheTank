package com.mystudio.wtt;

public class Key{
      public boolean leftKey;
      public boolean rightKey;
      public boolean upKey;
      public boolean downKey;

      public void setKey(char moveDir, int status){
            boolean b = false;
            if(status == 1)b = true;
            switch(moveDir){
                  case 'u' :
                        this.upKey = b;
                        break;
                  case 'd' :
                        this.downKey = b;
                        break;
                  case 'l' :
                        this.leftKey = b;
                        break;
                  case 'r' :
                        this.rightKey = b;
            }
      }
}