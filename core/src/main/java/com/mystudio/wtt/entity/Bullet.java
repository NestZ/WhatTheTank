package com.mystudio.wtt.entity;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;

public class Bullet{
      private Sprite sprite;
      private int dir;
      private float speed;
      private CollisionBox collisionBox;

      public Bullet(float x, float y, int dir){
            this.dir = dir;
            this.speed = 1f;
            this.sprite = new Sprite(new Texture(Gdx.files.internal("Bullet.png")));
            this.collisionBox = new CollisionBox(x, y, this.sprite.getX(), this.sprite.getY());
      }

      public void update(float delta){
            this.collisionBox.preUpdate();
            switch(this.dir){
                  case 1 :
                        this.collisionBox.set(this.collisionBox.getRenderX() - this.speed, this.collisionBox.getRenderY());
                        break;
                  case 2 :
                        this.collisionBox.set(this.collisionBox.getRenderX() + this.speed, this.collisionBox.getRenderY());
                        break;
                  case 3 :
                        this.collisionBox.set(this.collisionBox.getRenderX(), this.collisionBox.getRenderY() - this.speed);
                        break;
                  case 4 :
                        this.collisionBox.set(this.collisionBox.getRenderX(), this.collisionBox.getRenderY() + this.speed);
                        break;
            }
      }

      public void interpolate(float alpha){
            this.collisionBox.interpolate(null, alpha);
      }

      public void render(Graphics g){
            g.drawSprite(this.sprite, this.collisionBox.getRenderX(), this.collisionBox.getRenderY());
      }
}