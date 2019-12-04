package com.mystudio.wtt.entity;

import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

public class Bullet{
      private Sprite sprite;
      private int dir;
      private int TEAM;
      private int shooterID;
      private int id;
      private float speed;
      private CollisionBox collisionBox;
      private float x;
      private float y;
      public static LinkedList<Bullet> noSprite = new LinkedList<>();

      public Bullet(float x, float y, int dir, int TEAM, int id, int shooterID){
            this.dir = dir;
            this.TEAM = TEAM;
            this.speed = 20f;
            this.x = x;
            this.y = y;
            this.id = id;
            this.shooterID = shooterID;
            this.collisionBox = new CollisionBox();
            Bullet.noSprite.add(this);
      }

      public void setSprite(){
            this.sprite = new Sprite(new Texture(Gdx.files.internal("Bullet_" + this.shooterID + ".png")));
            switch(this.dir){
                  case 1 :
                        this.x -= this.sprite.getHeight() / 2f + 3;
                        this.sprite.setRotation(-90);
                        break;
                  case 2 :
                        this.x -= this.sprite.getHeight() / 2f + 3;
                        this.sprite.setRotation(90);
                        break;
                  case 3 :
                        this.y -= this.sprite.getWidth() / 2f - 2;
                        this.sprite.setRotation(180);
                        break;
                  case 4 :
                        this.y -= this.sprite.getWidth() / 2f - 2;
                        this.sprite.setRotation(0);
                        break;
            }
            this.collisionBox = new CollisionBox(this.x, this.y, this.sprite.getWidth(), this.sprite.getHeight());
      }

      public void update(float delta){
            if(this.collisionBox != null){
                  float absSpeed = this.speed * delta * 100;
                  this.collisionBox.preUpdate();
                  switch(this.dir){
                        case 1 :
                              this.collisionBox.set(this.collisionBox.getRenderX(), this.collisionBox.getRenderY() - absSpeed);
                              break;
                        case 2 :
                              this.collisionBox.set(this.collisionBox.getRenderX(), this.collisionBox.getRenderY() + absSpeed);
                              break;
                        case 3 :
                              this.collisionBox.set(this.collisionBox.getRenderX() - absSpeed, this.collisionBox.getRenderY());
                              break;
                        case 4 :
                              this.collisionBox.set(this.collisionBox.getRenderX() + absSpeed, this.collisionBox.getRenderY());
                              break;
                  }
            }
      }

      public void interpolate(float alpha){
            if(this.collisionBox != null){
                  this.collisionBox.interpolate(null, alpha);
            }
      }

      public void render(Graphics g){
            if(this.sprite != null){
                  g.drawSprite(this.sprite, this.collisionBox.getRenderX(), this.collisionBox.getRenderY());
            }
      }

      public int getShooterID(){
            return this.shooterID;
      }

      public int getID(){
            return this.id;
      }

      public int getDir(){
            return this.dir;
      }

      public float getMaxX(){
            return this.collisionBox.getMaxX();
      }

      public float getMaxY(){
            return this.collisionBox.getMaxY();
      }

      public float getMinX(){
            return this.collisionBox.getMinX();
      }

      public float getMinY(){
            return this.collisionBox.getMinY();
      }

      public CollisionBox collisionBox(){
            return this.collisionBox;
      }

      public int TEAM(){
            return this.TEAM;
      }
}