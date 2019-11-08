package com.mystudio.wtt.entity.tank;

import org.mini2Dx.core.engine.geom.CollisionBox;

/**
 * Class to store and control tank's move box and collision box.
 * 
 * @author NestZ
 */

public class MoveBox {
      /**
       * All of tank's collision box and colliding status.
       */
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

      /**
       * Constructor to set collision box and initialize move speed.
       * @param collisionBox tank's collision box
       * @param moveSpeed tank's initial move speed
       */
      public MoveBox(CollisionBox collisionBox, float moveSpeed){
            this.R = new CollisionBox();
            this.L = new CollisionBox();
            this.U = new CollisionBox();
            this.D = new CollisionBox();
            this.moveSpeed = moveSpeed;
            this.collisionBox = collisionBox;
      }

      /**
       * Set tank's current valid move.
       * @param c direction to set
       * @param b status to set (true if that direction is movable otherwise false)
       */
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

      /**
       * Update client's move box if needed.
       * @param delta game's delta time
       * @param key client's key status
       * true if key pushed otherwise false
       */
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

      /**
       * Move client's tank to left (calculate with deltatime and speed).
       * @param delta game's delta time
       */
      public void moveLeft(float delta){
            this.collisionBox.set(this.collisionBox.getX() - (this.moveSpeed * delta * 100), this.collisionBox.getY());
      }

      /**
       * Move client's tank to right (calculate with deltatime and speed).
       * @param delta game's delta time
       */
      public void moveRight(float delta){
            this.collisionBox.set(this.collisionBox.getX() + (this.moveSpeed * delta * 100), this.collisionBox.getY());
      }

      /**
       * Move client's tank up (calculate with deltatime and speed).
       * @param delta game's delta time
       */
      public void moveUp(float delta){
            this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() - (this.moveSpeed * delta * 100));
      }

      /**
       * Move client's tank down (calculate with deltatime and speed).
       * @param delta game's delta time
       */
      public void moveDown(float delta){
            this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() + (this.moveSpeed * delta * 100));
      }

      /**
       * Return true if right-side of tank is movable.
       * @return true if right move is valid otherwise return false
       */
      public boolean RValid(){
            return this.RValid;
      }

      /**
       * Return true if left-side of tank is movable.
       * @return true if left move is valid otherwise return false
       */
      public boolean LValid(){
            return this.LValid;
      }

      /**
       * Return true if up-side of tank is movable.
       * @return true if up move is valid otherwise return false
       */
      public boolean UValid(){
            return this.UValid;
      }

      /**
       * Return true if down-side of tank is movable.
       * @return true if down move is valid otherwise return false
       */
      public boolean DValid(){
            return this.DValid;
      }

      /**
       * Return tank's right collision box.
       * @return tank's right collision box
       */
      public CollisionBox R(){
            return this.R;
      }
      
      /**
       * Return tank's left collision box.
       * @return tank's left collision box
       */
      public CollisionBox L(){
            return this.L;
      }

      /**
       * Return tank's up collision box.
       * @return tank's up collision box
       */
      public CollisionBox U(){
            return this.U;
      }

      /**
       * Return tank's down collision box.
       * @return tank's down collision box
       */
      public CollisionBox D(){
            return this.D;
      }
}