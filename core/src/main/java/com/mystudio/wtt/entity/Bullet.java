package com.mystudio.wtt.entity;

import java.util.concurrent.ConcurrentHashMap;
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
      private float speed;
      private CollisionBox collisionBox;
      private float x;
      private float y;
      private static int bulletNum = 0;
      public static ConcurrentHashMap<Integer, Bullet> bullets = new ConcurrentHashMap<>();
      public static LinkedList<Bullet> noSprite = new LinkedList<>();

      public Bullet(float x, float y, int dir, int TEAM){
            this.dir = dir;
            this.TEAM = TEAM;
            this.speed = 20f;
            this.x = x;
            this.y = y;
            Bullet.noSprite.add(this);
      }

      public static void addBullet(int TEAM, int dir, float x, float y){
            Bullet.bullets.put(Bullet.bulletNum++, new Bullet(x, y, dir, TEAM));
      }

      public void setSprite(){
            this.sprite = new Sprite(new Texture(Gdx.files.internal("Bullet.png")));
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

      public int TEAM(){
            return this.TEAM;
      }
}