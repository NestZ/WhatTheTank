package com.mystudio.wtt;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;

public class Tank extends Entity{
      private Key key;
      private final int TEAM;
      private final int MAX_HP;
      private final String COLOR;
      private boolean isDead;
      private boolean visible;
      private int hp;
      private CollisionBox collisionBox;
      private Sprite sprite;

      public Tank(String color, float x, float y, int team){
            this.TEAM = team;
            this.MAX_HP = 3;
            this.COLOR = color;
            this.hp = MAX_HP;
            this.isDead = false;
            this.visible = true;
            this.key = new Key();
            this.sprite = new Sprite(new Texture(Gdx.files.internal("tank.png")));
            this.collisionBox = new CollisionBox(x, y, this.sprite.getWidth(), this.sprite.getHeight());
      }

      public void update(){
            if(this.visible)this.updateMove();
      }

      public void interpolate(float alpha){
            if(this.visible)this.collisionBox.interpolate(null, alpha);
      }

      public void render(Graphics g){
            if(this.visible)g.drawSprite(this.sprite, this.collisionBox.getX(), this.collisionBox.getY());
      }

      public void setMove(int move){
            switch(move){
                  case 1 : 
                        this.key.setUp(true);break;
                  case 2 :
                        this.key.setDown(true);break;
                  case 3 :
                        this.key.setLeft(true);break;
                  case 4 :
                        this.key.setRight(true);break;
                  case 5 :
                        this.key.setUp(false);break;
                  case 6 :
                        this.key.setDown(false);break;
                  case 7 :
                        this.key.setLeft(false);break;
                  case 8 :
                        this.key.setRight(false);
            }
      }

      public void updateMove(){
            System.out.println(this.collisionBox.toString());
            if(this.key.upKey)this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() - 2);
            if(this.key.downKey)this.collisionBox.set(this.collisionBox.getX(), this.collisionBox.getY() + 2);
            if(this.key.leftKey)this.collisionBox.set(this.collisionBox.getX() - 2, this.collisionBox.getY());
            if(this.key.rightKey)this.collisionBox.set(this.collisionBox.getX() + 2, this.collisionBox.getY());
      }

      public void setPos(float x, float y){
            this.collisionBox.set(x,y);
      }

      public float getX(){
            return this.collisionBox.getX();
      }

      public float getY(){
            return this.collisionBox.getY();
      }

      public int team(){
            return this.TEAM;
      }

      public boolean isDead(){
            return this.isDead;
      }
}