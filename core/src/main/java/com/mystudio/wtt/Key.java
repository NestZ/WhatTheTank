package com.mystudio.wtt;

public class Key{
      public boolean leftKey;
      public boolean rightKey;
      public boolean upKey;
      public boolean downKey;

      public void setUp(boolean b){
            if(this.downKey && b)this.downKey = false;
            this.upKey = b;
      }

      public void setDown(boolean b){
            if(this.upKey && b)this.upKey = false;
            this.downKey = b;
      }

      public void setLeft(boolean b){
            if(this.rightKey && b)this.rightKey = false;
            this.leftKey = b;
      }

      public void setRight(boolean b){
            if(this.leftKey && b)this.leftKey = false;
            this.rightKey = b;
      }
}