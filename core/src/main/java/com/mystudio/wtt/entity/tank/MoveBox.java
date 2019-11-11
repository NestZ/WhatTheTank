package com.mystudio.wtt.entity.tank;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;

/**
 * Class to store and control tank's move box and collision box.
 * 
 * @author NestZ
 */

public class MoveBox {
      /**
       * All of tank's collision box and colliding status.
       */
      private int direction;
      private Sprite sprite;
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
       * Update client's move box and face direction if needed.
       * @param delta game's delta time
       * @param key client's key status
       * true if key pushed otherwise false
       */
      public void update(float delta, Key key){
            this.collisionBox.preUpdate();
            if(key.up()){
                  this.sprite.setRotation(0);
                  this.direction = 1;
                  if(this.UValid())this.move(1, delta);
            }
            else if(key.down()){
                  this.sprite.setRotation(180);
                  this.direction = 2;
                  if(this.DValid())this.move(2, delta);
            }
            else if(key.left()){
                  this.sprite.setRotation(-90);
                  this.direction = 3;
                  if(this.LValid())this.move(3, delta);
            }
            else if(key.right()){
                  this.sprite.setRotation(90);
                  this.direction = 4;
                  if(this.RValid())this.move(4, delta);
            }
      }

      /**
       * Method for interpolate previous and next frame.
       * @param alpha delta time since last render
       */
      public void interpolate(float alpha){
            this.collisionBox.interpolate(null, alpha);
      }

      /**
       * Method for render Tank's sprite at current position.
       * @param g Graphics to render at
       */
      public void render(Graphics g){
            g.drawSprite(this.sprite, this.collisionBox.getRenderX(), this.collisionBox.getRenderY());
      }

      /**
       * Move client's tank to given direction.
       * @param dir tank's move direction
       * 1 : move up
       * 2 : move down
       * 3 : move left
       * 4 : move right
       * @param delta delta time
       */
      public void move(int dir, float delta){
            float absSpeed = this.moveSpeed * delta * 100;
            switch(dir){
                  case 1 :
                        this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() - absSpeed);
                        break;
                  case 2 :
                        this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() + absSpeed);
                        break;
                  case 3 :
                        this.collisionBox.set(this.collisionBox.getX() - absSpeed, this.collisionBox.getY());
                        break;
                  case 4 :
                        this.collisionBox.set(this.collisionBox.getX() + absSpeed, this.collisionBox.getY());
                        break;
            }
      }

      /**
       * Setter for sprite.
       * @param sprite tank's sprite
       */
      public void sprite(Sprite sprite){
            this.sprite = sprite;
      }

      /**
       * Getter for tank's collision box.
       * @return tank's collision box
       */
      public CollisionBox collisionBox(){
            return this.collisionBox;
      }

      /**
       * Getter for tank's face direction.
       * @return tank's face direction
       * 1 : face up
       * 2 : face down
       * 3 : face left
       * 4 : face right
       */
      public int direction(){
            return this.direction;
      }

      /**
       * Setter for tank's face direction.
       * @param dir tank's face direction
       * 1 : face up
       * 2 : face down
       * 3 : face left
       * 4 : face right
       */
      public void direction(int dir){
            this.direction = dir;
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