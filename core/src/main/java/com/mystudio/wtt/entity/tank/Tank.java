package com.mystudio.wtt.entity.tank;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.wtt.screen.Field;
import com.mystudio.wtt.utils.Point;
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
      private final int ID;
      private Point<Float> initPos;
      private float SHOOT_DELAY;
      private float DEAD_DELAY;
      private float BLINK_DELAY;
      private float currShootDelay;
      private float currDeadDelay;
      private float currBlinkDelay;
      private Key key;
      private MoveBox moveBox;
      private boolean visible;
      private boolean isDead;
      private boolean isBlink;
      private boolean shootAble;
      private Sprite sprite;

      /**
       * Constructor for Tank's class.
       * @param color Tank's color
       * @param x Tank's x position
       * @param y Tank's y position
       * @param team Tank's team ID
       * @param ID Tank's ID
       */
      public Tank(float x, float y, int team, int ID){
            this.TEAM = team;
            this.SHOOT_DELAY = 0.35f;
            this.DEAD_DELAY = 3f;
            this.BLINK_DELAY = 2f;
            this.currBlinkDelay = 0f;
            this.visible = true;
            this.shootAble = false;
            this.isDead = false;
            this.isBlink = false;
            this.ID = ID;
            this.key = new Key();
            this.initPos = new Point<>(x, y);
            this.moveBox = new MoveBox(new CollisionBox(x, y, 0, 0), 3f);
      }

      /**
       * Main method for update Tank's position and sprite.
       * @param delta delta time since last update
       */
      public void update(float delta){
            if(this.currShootDelay < this.SHOOT_DELAY)this.currShootDelay += delta;
            else this.shootAble = true;
            if(this.isDead && this.currDeadDelay < this.DEAD_DELAY)this.currDeadDelay += delta;
            else{
                  if(this.isDead){
                        this.moveBox.collisionBox().set(this.initPos.getX(), this.initPos.getY(), this.sprite.getWidth(), this.sprite.getHeight());
                        this.isBlink = true;
                        this.currBlinkDelay = 0f;
                  }
                  this.visible = true;
                  this.isDead = false;
            }
            if(this.isBlink && this.currBlinkDelay < this.BLINK_DELAY){
                  int x = ((int)(currBlinkDelay * 100)) % 100;
                  if(x > 75 || (x > 25 && x < 50))this.visible = false;
                  else this.visible = true;
                  this.currBlinkDelay += delta;
            }
            else this.isBlink = false;
            if(!this.isDead && !this.isBlink)this.visible = true;
            if(this.visible())this.moveBox.update(delta, this.key);
      }

      /**
       * Method for interpolate previous and next frame.
       * @param alpha delta time since last render
       */
      public void interpolate(float alpha){
            this.moveBox.interpolate(alpha);
      }

      /**
       * Method for render Tank's sprite at current position.
       * @param g Graphics to render at
       */
      public void render(Graphics g){
            this.moveBox.render(g);
      }

      /**
       * Created and set bullet position, direction.
       * @param dir tank's face direction
       * 1 : face up
       * 2 : face down
       * 3 : face left
       * 4 : face right
       * @param x bullet's initial x position
       * @param y bullet's initial y position
       */
      public void shoot(int dir, float x, float y){
            if(this.shootAble){
                  Field.addBullet(this.TEAM, dir, x, y, this.ID);
                  this.currShootDelay = 0f;
                  this.shootAble = false;
            }
      }

      public void shot(){
            this.visible = false;
            this.isDead = true;
            this.currDeadDelay = 0f;
            this.moveBox.collisionBox().set(-1, -1, 0, 0);
            Field.s.playboomsound();
      }

      public boolean visible(){
            return this.visible;
      }

      /**
       * Getter for tank's collision box.
       * @return tank's collision box
       */
      public CollisionBox CollisionBox(){
            return this.moveBox.collisionBox();
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
            this.moveBox.collisionBox().set(x,y);
            this.moveBox.direction(dir);
      }

      /**
       * Set tank's sprite when tank's is created.
       * Initial sprite direction is face up.
       */
      public void setSprite(){
            this.sprite = new Sprite(new Texture(Gdx.files.internal("tank_" + this.ID + ".png")));
            this.moveBox.sprite(this.sprite);
            this.moveBox.collisionBox().setWidth(this.sprite.getWidth());
            this.moveBox.collisionBox().setHeight(this.sprite.getHeight());
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
            return this.moveBox.direction();
      }

      /**
       * Getter for tank's x position.
       * @return tank's x position
       */
      public float getX(){
            return this.moveBox.collisionBox().getRenderX();
      }

      public float getMaxX(){
            return this.moveBox.collisionBox().getMaxX();
      }

      public float getMinX(){
            return this.moveBox.collisionBox().getMinX();
      }

      /**
       * Getter for tank's y position.
       * @return tank's y direction
       */
      public float getY(){
            return this.moveBox.collisionBox().getRenderY();
      }

      public float getMaxY(){
            return this.moveBox.collisionBox().getMaxY();
      }

      public float getMinY(){
            return this.moveBox.collisionBox().getMinY();
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

      public boolean shootAble(){
            return this.shootAble;
      }
}