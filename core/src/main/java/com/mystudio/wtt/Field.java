package com.mystudio.wtt;

import org.mini2Dx.core.graphics.Graphics;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;

public class Field{
      private Wall wall;
      private InputHandler inputHandler;
      private CollisionHandler collisionHandler;
      private ServerStarter server;
      private ClientStarter client;
      private HashMap<Integer, Tank> tanks;
      private int clientsNum = 0;
      private int clientID;

      public Field(){
            this.wall = new Brick();
            this.collisionHandler = new CollisionHandler();
            this.server = new ServerStarter();
            this.client = new ClientStarter("127.0.0.1", 1234, 0, 0, 0);
            this.clientID = this.client.clientThread.getID();
            this.tanks = this.client.clientThread.getTanks();
            this.inputHandler = new InputHandler(this.tanks.get(this.clientID), this.client);
            this.setCollision();
            Gdx.input.setInputProcessor(this.inputHandler);
      }

      public void setCollision(){
            this.collisionHandler.setTank(this.tanks.get(this.clientID));
            this.collisionHandler.setWall(this.wall);
      }

      public void update(float delta){
            if(this.clientsNum != this.tanks.size())this.registerNewTank();
            this.checkTankCollision();
            for(int i = 0;i < this.tanks.size();i++)this.tanks.get(i).update(delta);
            if(this.inputHandler.keyDown)this.inputHandler.sendToServer();
            this.wall.update(delta);
      }

      public void registerNewTank(){
            for(int i = 0;i < this.tanks.size();i++){
                  if(this.tanks.get(i).getSprite() == null){
                        this.tanks.get(i).setSprite();
                  }
            }
            this.clientsNum = this.tanks.size();
      }

      public void checkTankCollision(){
            switch(this.collisionHandler.isCollide()){
                  case 'R' :
                        this.tanks.get(this.clientID).setValidMove('R', false);break;
                  case 'L' :
                        this.tanks.get(this.clientID).setValidMove('L', false);break;
                  case 'U' :
                        this.tanks.get(this.clientID).setValidMove('U', false);break;
                  case 'D' :
                        this.tanks.get(this.clientID).setValidMove('D', false);break;
                  default :
                        this.tanks.get(this.clientID).setValidMove('R', true);
                        this.tanks.get(this.clientID).setValidMove('L', true);
                        this.tanks.get(this.clientID).setValidMove('U', true);
                        this.tanks.get(this.clientID).setValidMove('D', true);
            }
      }

      public void interpolate(float alpha){
            for(int i = 0;i < this.tanks.size();i++)this.tanks.get(i).interpolate(alpha);
            this.wall.interpolate(alpha);
      }

      public void render(Graphics g){
            for(int i = 0;i < this.tanks.size();i++)this.tanks.get(i).render(g);
            this.wall.render(g);
      }
}