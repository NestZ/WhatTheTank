package com.mystudio.wtt.entity;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;

public class CollisionTile implements Tiles{
      private int HP;
      private boolean isVisible;
      private boolean shootAble;
      private Sprite sprite;
      private CollisionBox collisionBox;

      public CollisionTile(float x, float y, String sprite, boolean shootAble){
            this.shootAble = shootAble;
            if(this.shootAble)this.HP = 3;
            this.isVisible = true;
            this.sprite = new Sprite(new Texture(Gdx.files.internal(sprite)));
            this.collisionBox = new CollisionBox(x, y, this.sprite.getWidth(), this.sprite.getHeight());
      }

      @Override
      public CollisionBox collisionBox(){
            return this.collisionBox;
      }

      public float getMaxX(){
            return this.collisionBox.getMaxX();
      }

      public float getMinX(){
            return this.collisionBox.getMinX();
      }

      public float getMaxY(){
            return this.collisionBox.getMaxY();
      }

      public float getMinY(){
            return this.collisionBox.getMinY();
      }

      @Override
      public void shot(){
            if(this.shootAble){
                  if(this.HP > 2)this.HP--;
                  else if(this.HP > 1)this.sprite = new Sprite(new Texture(Gdx.files.internal("Wall_" + (--this.HP) + ".png")));
                  else{
                        this.isVisible = false;
                        this.collisionBox = new CollisionBox(-1, -1, 0, 0);
                  }
            }
      }

      @Override
      public boolean isVisible(){
            return this.isVisible;
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