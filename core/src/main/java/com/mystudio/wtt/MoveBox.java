package com.mystudio.wtt;

import org.mini2Dx.core.engine.geom.CollisionBox;

public class MoveBox {
      private float moveSpeed;
      private boolean RValid;
      private boolean LValid;
      private boolean UValid;
      private boolean DValid;
      private CollisionBox R;
      private CollisionBox L;
      private CollisionBox U;
      private CollisionBox D;
      private CollisionBox collisionBox;

      public MoveBox(CollisionBox collisionBox, float moveSpeed){
            this.R = new CollisionBox();
            this.L = new CollisionBox();
            this.U = new CollisionBox();
            this.D = new CollisionBox();
            this.moveSpeed = moveSpeed;
            this.collisionBox = collisionBox;
      }

      public void setValidMove(char c,boolean b){
            switch (c){
                  case 'R' :
                        this.RValid = b;
                        break;
                  case 'L' :
                        this.LValid = b;
                        break;
                  case 'U' :
                        this.UValid = b;
                        break;
                  case 'D' :
                        this.DValid = b;
            }
      }

      public void update(float delta, Key key){
            if(key.up() && this.UValid()){
                  this.moveUp(delta);
            }
            if(key.down() && this.DValid()){
                  this.moveDown(delta);
            }
            if(key.left() && this.LValid()){
                  this.moveLeft(delta);
            }
            if(key.right() && this.RValid()){
                  this.moveRight(delta);
            }
      }

      public void moveLeft(float delta){
            this.collisionBox.set(this.collisionBox.getX() - (this.moveSpeed * delta * 100), this.collisionBox.getY());
      }

      public void moveRight(float delta){
            this.collisionBox.set(this.collisionBox.getX() + (this.moveSpeed * delta * 100), this.collisionBox.getY());
      }

      public void moveUp(float delta){
            this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() - (this.moveSpeed * delta * 100));
      }

      public void moveDown(float delta){
            this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() + (this.moveSpeed * delta * 100));
      }

      public boolean RValid(){
            return this.RValid;
      }

      public boolean LValid(){
            return this.LValid;
      }

      public boolean UValid(){
            return this.UValid;
      }

      public boolean DValid(){
            return this.DValid;
      }

      public CollisionBox R(){
            return this.R;
      }
      
      public CollisionBox L(){
            return this.L;
      }

      public CollisionBox U(){
            return this.U;
      }

      public CollisionBox D(){
            return this.D;
      }
}