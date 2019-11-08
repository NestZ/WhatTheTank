package com.mystudio.wtt.entity.tank;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;

/**
 * A class for player's tank
 */
public class Tank{
      private Key key;
      private MoveBox moveBox;
      private final int TEAM;
      private final int MAX_HP;
      private final String COLOR;
      private boolean isDead;
      private boolean visible;
      private int hp;
      private CollisionBox collisionBox;
      private Sprite sprite;
      private int direction;
      private int ID;

      /**
       * Constructor for Tank's class
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
       * Method for update Tank
       * @param delta delta time since last update
       */
      public void update(float delta){
            if(this.visible){
                  this.collisionBox.preUpdate();
                  this.moveBox.update(delta, this.key);
            }
      }

      /**
       * Method for interpolate Tank
       * @param alpha alpha
       */
      public void interpolate(float alpha){
            if(this.visible)this.collisionBox.interpolate(null, alpha);
      }

      /**
       * Method for render Tank
       * @param g Graphics to render at
       */
      public void render(Graphics g){
            if(this.visible)g.drawSprite(this.sprite, this.collisionBox.getRenderX(), this.collisionBox.getRenderY());
      }

      public CollisionBox CollisionBox(){
            return this.collisionBox;
      }

      public MoveBox moveBox(){
            return this.moveBox;
      }

      public Key key(){
            return this.key;
      }

      public void setPos(float x, float y, int dir){
            this.collisionBox.set(x,y);
            this.direction = dir;
      }

      public void setSprite(){
            this.sprite = new Sprite(new Texture(Gdx.files.internal("tank.png")));
            this.collisionBox.setWidth(this.sprite.getWidth());
            this.collisionBox.setHeight(this.sprite.getHeight());
            this.visible = true;
      }

      public Sprite getSprite(){
            return this.sprite;
      }

      public int getID(){
            return this.ID;
      }

      public int getDir(){
            return this.direction;
      }

      public float getX(){
            return this.collisionBox.getRenderX();
      }

      public float getY(){
            return this.collisionBox.getRenderY();
      }

      public float getWidth(){
            return this.sprite.getWidth();
      }

      public float getHeight(){
            return this.sprite.getHeight();
      }

      public int team(){
            return this.TEAM;
      }

      public boolean isDead(){
            return this.isDead;
      }
}