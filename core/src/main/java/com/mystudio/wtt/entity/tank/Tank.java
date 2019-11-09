package com.mystudio.wtt.entity.tank;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;

/**
 * A class for store and manipulate client's current tank information.
 * 
 * @author NestZ
 */

public class Tank{
      /**
       * Tank's information.
       * Final fields are permanently initialize by constructor. 
       */
      private final int TEAM;
      private final int MAX_HP;
      private final int ID;
      private final String COLOR;
      private Key key;
      private MoveBox moveBox;
      private boolean isDead;
      private boolean visible;
      private int hp;
      private CollisionBox collisionBox;
      private Sprite sprite;
      private int direction;

      /**
       * Constructor for Tank's class.
       * @param color Tank's color
       * @param x Tank's x position
       * @param y Tank's y position
       * @param team Tank's team ID
       * @param ID Tank's ID
       */
      public Tank(String color, float x, float y, int team, int ID){
            this.TEAM = team;
            this.MAX_HP = 3;
            this.COLOR = color;
            this.hp = MAX_HP;
            this.isDead = false;
            this.visible = false;
            this.ID = ID;
            this.key = new Key();
            this.collisionBox = new CollisionBox(x, y, 0, 0);
            this.moveBox = new MoveBox(collisionBox, 2f);
      }

      /**
       * Method for update Tank's position.
       * @param delta delta time since last update
       */
      public void update(float delta){
            if(this.visible){
                  this.collisionBox.preUpdate();
                  this.moveBox.update(delta, this.key);
            }
      }

      /**
       * Method for interpolate previous and next frame.
       * @param alpha delta time since last render
       */
      public void interpolate(float alpha){
            if(this.visible)this.collisionBox.interpolate(null, alpha);
      }

      /**
       * Method for render Tank's sprite at current position.
       * @param g Graphics to render at
       */
      public void render(Graphics g){
            if(this.visible)g.drawSprite(this.sprite, this.collisionBox.getRenderX(), this.collisionBox.getRenderY());
      }

      /**
       * Getter for tank's collision box.
       * @return tank's collision box
       */
      public CollisionBox CollisionBox(){
            return this.collisionBox;
      }

      /**
       * Getter for tank's move box.
       * @return tank's move box
       */
      public MoveBox moveBox(){
            return this.moveBox;
      }

      /**
       * Getter for tank's current key status.
       * @return tank's current key status
       */
      public Key key(){
            return this.key;
      }

      /**
       * Setter for tank's position in x, y.
       * @param x set tank's x position
       * @param y set tank's y position
       * @param dir set tank's face direction
       * 1 : face up
       * 2 : face down
       * 3 : face left
       * 4 : face right
       */
      public void setPos(float x, float y, int dir){
            this.collisionBox.set(x,y);
            this.direction = dir;
      }

      /**
       * Set tank's sprite when tank's is created.
       */
      public void setSprite(){
            this.sprite = new Sprite(new Texture(Gdx.files.internal("tank.png")));
            this.collisionBox.setWidth(this.sprite.getWidth());
            this.collisionBox.setHeight(this.sprite.getHeight());
            this.visible = true;
      }

      /**
       * Getter for tank's sprite.
       * @return tank's sprite
       */
      public Sprite getSprite(){
            return this.sprite;
      }

      /**
       * Getter for tank's ID.
       * @return tank's ID
       */
      public int getID(){
            return this.ID;
      }

      /**
       * Getter for tank's face direction.
       * @return tank's face direction
       * 1 : face up
       * 2 : face down
       * 3 : face left
       * 4 : face right
       */
      public int getDir(){
            return this.direction;
      }

      /**
       * Getter for tank's x position.
       * @return tank's x position
       */
      public float getX(){
            return this.collisionBox.getRenderX();
      }

      /**
       * Getter for tank's y position.
       * @return tank's y direction
       */
      public float getY(){
            return this.collisionBox.getRenderY();
      }

      /**
       * Getter for tank's width.
       * @return tank's width (in pixel)
       */
      public float getWidth(){
            return this.sprite.getWidth();
      }

      /**
       * Getter for tank's height.
       * @return tank's height (in pixel)
       */
      public float getHeight(){
            return this.sprite.getHeight();
      }

      /**
       * Getter for tank's team ID.
       * @return tank's team ID
       */
      public int team(){
            return this.TEAM;
      }

      /**
       * Tank's status (dead or not).
       * @return true if tank is dead otherwise return false
       */
      public boolean isDead(){
            return this.isDead;
      }
}