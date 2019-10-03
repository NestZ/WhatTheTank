package com.mystudio.wtt;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;

public class Tank{
      private Key key;
      private final int TEAM;
      private final int MAX_HP;
      private final String COLOR;
      private Point pos;
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
            this.pos = new Point(x,y);
            this.key = new Key();
            this.collisionBox = new CollisionBox(0f, 0f, 2f, 2f);
            this.sprite = new Sprite(new Texture(Gdx.files.internal("mini2Dx.png")));
      }

      public void update(){
            if(this.visible){
                  this.updateMove();
                  this.collisionBox.preUpdate();
                  this.collisionBox.set(this.pos.getX(), this.pos.getY());
            }
      }

      public void interpolate(float alpha){
            if(this.visible)this.collisionBox.interpolate(null, alpha);
      }

      public void render(Graphics g){
            if(this.visible)g.drawSprite(this.sprite, this.pos.getX(), this.pos.getY());
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
            if(this.key.upKey)this.setPos(this.getX(), this.getY() - 1);
            if(this.key.downKey)this.setPos(this.getX(), this.getY() + 1);
            if(this.key.leftKey)this.setPos(this.getX() - 1, this.getY());
            if(this.key.rightKey)this.setPos(this.getX() + 1, this.getY());
      }

      public void setPos(float x, float y){
            this.pos.setX(x);
            this.pos.setY(y);
      }

      public float getX(){
            return this.pos.getX();
      }

      public float getY(){
            return this.pos.getY();
      }

      public int team(){
            return this.TEAM;
      }

      public boolean isDead(){
            return this.isDead;
      }
}