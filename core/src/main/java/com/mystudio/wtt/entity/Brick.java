package com.mystudio.wtt.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

public class Brick extends Wall{
      private Sprite sprite;
      private CollisionBox collisionBox;

      public Brick(){
            this.sprite = new Sprite(new Texture(Gdx.files.internal("wall.png")));
            this.collisionBox = new CollisionBox(250f, 250f, this.sprite.getWidth(), this.sprite.getHeight());
      }

      public CollisionBox collisionBox(){
            return this.collisionBox;
      }

      public void update(float delta){
            this.collisionBox.preUpdate();
            this.collisionBox.set(250f, 250f);
      }

      public void interpolate(float alpha){
            this.collisionBox.interpolate(null, alpha);
      }

      public void render(Graphics g){
            g.drawSprite(this.sprite, this.collisionBox.getX(), this.collisionBox.getY());
      }
}