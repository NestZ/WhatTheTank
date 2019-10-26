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
      public CollisionBox tankR;
      public CollisionBox tankL;
      public CollisionBox tankU;
      public CollisionBox tankD;
      private int ID;
      private float moveSpeed;

      public Tank(String color, float x, float y, int team, int ID){
            this.TEAM = team;
            this.MAX_HP = 3;
            this.COLOR = color;
            this.moveSpeed = 1.5f;
            this.hp = MAX_HP;
            this.isDead = false;
            this.visible = false;
            this.key = new Key();
            this.ID = ID;
            this.collisionBox = new CollisionBox(x, y, 0, 0);
            this.tankR = new CollisionBox();
            this.tankL = new CollisionBox();
            this.tankU = new CollisionBox();
            this.tankD = new CollisionBox();
      }

      public void update(float delta){
            if(this.visible){
                  this.collisionBox.preUpdate();
                  this.updateMove(delta);
            }
      }

      public void interpolate(float alpha){
            if(this.visible)this.collisionBox.interpolate(null, alpha);
      }

      public void render(Graphics g){
            if(this.visible)g.drawSprite(this.sprite, this.collisionBox.getRenderX(), this.collisionBox.getRenderY());
      }

      private void updateMove(float delta){
            if(this.key.upKey && this.isUValid){
                  this.moveUp(delta);
            }
            if(this.key.downKey && this.isDValid){
                  this.moveDown(delta);
            }
            if(this.key.leftKey && this.isLValid){
                  this.moveLeft(delta);
            }
            if(this.key.rightKey && this.isRValid){
                  this.moveRight(delta);
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

      private void moveLeft(float delta){
            this.collisionBox.set(this.collisionBox.getX() - (this.moveSpeed * delta * 100), this.collisionBox.getY());
      }

      private void moveRight(float delta){
            this.collisionBox.set(this.collisionBox.getX() + (this.moveSpeed * delta * 100), this.collisionBox.getY());
      }

      private void moveUp(float delta){
            this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() - (this.moveSpeed * delta * 100));
      }

      private void moveDown(float delta){
            this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() + (this.moveSpeed * delta * 100));
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