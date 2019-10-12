package com.mystudio.wtt;

import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.Gdx;

public class Field{
      private Tank tank;
      private Wall wall;
      private InputHandler inputHandler;
      private CollisionHandler collisionHandler;
      private ServerStarter server;
      private ClientStarter client;

      public Field(){
            this.tank = new Tank("c", 0f, 0f, 1, -1);
            this.wall = new Brick();
            this.inputHandler = new InputHandler(this.tank);
            Gdx.input.setInputProcessor(this.inputHandler);
            this.collisionHandler = new CollisionHandler();
            this.setCollision();
            this.server = new ServerStarter();
            this.client = new ClientStarter("127.0.0.1", 11111, 0, 0, 0);
      }

      public void setCollision(){
            this.collisionHandler.setTank(this.tank);
            this.collisionHandler.setWall(this.wall);
      }

      public void update(float delta){
            this.checkTankCollision();
            this.tank.update(delta);
            this.wall.update(delta);
      }

      public void checkTankCollision(){
            switch(this.collisionHandler.isCollide()){
                  case 'R' :
                        this.tank.setValidMove('R', false);break;
                  case 'L' :
                        this.tank.setValidMove('L', false);break;
                  case 'U' :
                        this.tank.setValidMove('U', false);break;
                  case 'D' :
                        this.tank.setValidMove('D', false);break;
                  default :
                        this.tank.setValidMove('R', true);
                        this.tank.setValidMove('L', true);
                        this.tank.setValidMove('U', true);
                        this.tank.setValidMove('D', true);
            }
      }

      public void interpolate(float alpha){
            this.tank.interpolate(alpha);
            this.wall.interpolate(alpha);
      }

      public void render(Graphics g){
            this.tank.render(g);
            this.wall.render(g);
      }
}