package com.mystudio.wtt.entity;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;

public class NonCollisionTile implements Tiles{
      private Sprite sprite;
      private CollisionBox collisionBox;

      public NonCollisionTile(float x, float y, String sprite){
            this.sprite = new Sprite(new Texture(Gdx.files.internal(sprite)));
            this.collisionBox = new CollisionBox(x, y, this.sprite.getWidth(), this.sprite.getHeight());
      }

      @Override
      public CollisionBox collisionBox(){
            return this.collisionBox;
      }

      public float getMaxX(){
            return 0f;
      }

      public float getMinX(){
            return 0f;
      }

      public float getMaxY(){
            return 0f;
      }

      public float getMinY(){
            return 0f;
      }

      @Override
      public void shot() {
            return;
      }

      @Override
      public boolean isVisible(){
            return true;
      }

      /**
       * Update brick's appearance 
       */
      public void update(float delta){
            this.collisionBox.preUpdate();
            this.collisionBox.set(this.collisionBox.getRenderX(), this.collisionBox.getRenderY());
      }

      /**
       * Interpolate brick's sprite
       */
      public void interpolate(float alpha){
            this.collisionBox.interpolate(null, alpha);
      }

      /**
       * Render brick's sprite on g at current position
       */
      public void render(Graphics g){
            g.drawSprite(this.sprite, this.collisionBox.getX(), this.collisionBox.getY());
      }
}