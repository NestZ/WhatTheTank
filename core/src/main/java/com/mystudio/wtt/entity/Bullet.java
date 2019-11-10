package com.mystudio.wtt.entity;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.LinkedList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

public class Bullet{
      private Sprite sprite;
      private int dir;
      private float speed;
      private CollisionBox collisionBox;
      private float x;
      private float y;
      public static HashMap<Integer, List<Bullet>> bullets = new HashMap<>();
      public static LinkedList<Bullet> noSprite = new LinkedList<>();

      public Bullet(float x, float y, int dir){
            this.dir = dir;
            this.speed = 15f;
            this.x = x;
            this.y = y;
            noSprite.add(this);
      }

      public static void addBullet(int tankID, int dir, float x, float y){
            List<Bullet> s;
            if(bullets.get(tankID) == null)s = new CopyOnWriteArrayList<>();
            else s = bullets.get(tankID);
            s.add(new Bullet(x, y, dir));
            bullets.put(tankID, s);
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
}