package com.mystudio.wtt.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

/**
 * Class to created bricks this class is extends form class Wall.
 * One of many entities in this game.
 * 
 * @see com.com.mystudio.wtt.entity.Wall
 * 
 * @author NestZ
 */

public class Brick extends Wall{
      /**
       * Brick's sprite and collision box.
       */
      private Sprite sprite;
      private CollisionBox collisionBox;

      /**
       * Constructor to set brick's sprite and collision box.
       */
      public Brick(){
            this.sprite = new Sprite(new Texture(Gdx.files.internal("wall.png")));
            this.collisionBox = new CollisionBox(250f, 250f, this.sprite.getWidth(), this.sprite.getHeight());
      }

      /**
       * Getter for collision box
       * @return this brick's collision box
       */
      public CollisionBox collisionBox(){
            return this.collisionBox;
      }

      /**
       * Update brick's appearance 
       */
      public void update(float delta){
            this.collisionBox.preUpdate();
            this.collisionBox.set(250f, 250f);
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