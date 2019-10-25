package com.mystudio.wtt;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;

public class Tank extends Entity{
      public Key key;
      private final int TEAM;
      private final int MAX_HP;
      private final String COLOR;
      private boolean isDead;
      private boolean visible;
      private int hp;
      private CollisionBox collisionBox;
      private Sprite sprite;
      private int direction;
      private boolean isRValid;
      private boolean isLValid;
      private boolean isUValid;
      private boolean isDValid;
      private int ID;
      private float moveSpeed;

      public Tank(String color, float x, float y, int team, int ID){
            this.TEAM = team;
            this.MAX_HP = 3;
            this.COLOR = color;
            this.moveSpeed = 2;
            this.hp = MAX_HP;
            this.isDead = false;
            this.visible = false;
            this.key = new Key();
            this.ID = ID;
            this.collisionBox = new CollisionBox(x, y, 0, 0);
      }

      public void update(float delta){
            if(this.visible){
                  this.collisionBox.preUpdate();
                  this.updateMove();
            }
      }

      public void interpolate(float alpha){
            if(this.visible)this.collisionBox.interpolate(null, alpha);
      }

      public void render(Graphics g){
            if(this.visible)g.drawSprite(this.sprite, this.collisionBox.getRenderX(), this.collisionBox.getRenderY());
      }

      private void updateMove(){
            //System.out.println("u " + this.key.upKey + " d " + this.key.downKey + " l " + this.key.leftKey + " r " + this.key.rightKey);
            if(this.key.upKey && this.isUValid){
                  this.moveUp();
            }
            if(this.key.downKey && this.isDValid){
                  this.moveDown();
            }
            if(this.key.leftKey && this.isLValid){
                  this.moveLeft();
            }
            if(this.key.rightKey && this.isRValid){
                  this.moveRight();
            }
      }

      public void setValidMove(char c,boolean b){
            switch (c){
                  case 'R' :
                        this.isRValid = b;
                        break;
                  case 'L' :
                        this.isLValid = b;
                        break;
                  case 'U' :
                        this.isUValid = b;
                        break;
                  case 'D' :
                        this.isDValid = b;
            }
      }

      public CollisionBox CollisionBox(){
            return this.collisionBox;
      }

      private void moveLeft(){
            this.collisionBox.set(this.collisionBox.getX() - this.moveSpeed, this.collisionBox.getY());
      }

      private void moveRight(){
            this.collisionBox.set(this.collisionBox.getX() + this.moveSpeed, this.collisionBox.getY());
      }

      private void moveUp(){
            this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() - this.moveSpeed);
      }

      private void moveDown(){
            this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() + this.moveSpeed);
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