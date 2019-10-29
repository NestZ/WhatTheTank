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
            while(!ClientStarter.isReady());
            this.clientID = this.client.thread().getID();
            this.tanks = this.client.thread().getTanks();
            this.inputHandler = new InputHandler(this.tanks.get(this.clientID), this.client);
            this.setCollision();
            Gdx.input.setInputProcessor(this.inputHandler);
      }

      public void setCollision(){
            this.collisionHandler.setWall(this.wall);
      }

      public void registerNewTank(){
            for(int i = 0;i < this.tanks.size();i++){
                  try{
                        if(this.tanks.get(i).getSprite() == null)this.tanks.get(i).setSprite();
                  }
                  catch(Exception e){
                        e.printStackTrace();
                  }
            }
            this.clientsNum = this.tanks.size();
      }

      public void setTankCollision(){
            for(int i = 0;i < tanks.size();i++){
                  Tank tank = this.tanks.get(i);
                  switch(this.collisionHandler.isCollide(tank)){
                        case 'R' :
                              tank.moveBox().setValidMove('R', false);break;
                        case 'L' :
                              tank.moveBox().setValidMove('L', false);break;
                        case 'U' :
                              tank.moveBox().setValidMove('U', false);break;
                        case 'D' :
                              tank.moveBox().setValidMove('D', false);break;
                        default :
                              tank.moveBox().setValidMove('R', true);
                              tank.moveBox().setValidMove('L', true);
                              tank.moveBox().setValidMove('U', true);
                              tank.moveBox().setValidMove('D', true);
                  }
            }
      }

      public void update(float delta){
            if(this.clientsNum != this.tanks.size())this.registerNewTank();
            this.setTankCollision();
            for(int i = 0;i < this.tanks.size();i++)this.tanks.get(i).update(delta);
            this.wall.update(delta);
      }

      public void interpolate(float alpha){
            try{
                  for(int i = 0;i < this.tanks.size();i++)this.tanks.get(i).interpolate(alpha);
                  this.wall.interpolate(alpha);
            }
            catch(NullPointerException e){
                  System.out.println("kuyyyyyyyyyyyy");
            }
      }

      public void render(Graphics g){
            for(int i = 0;i < this.tanks.size();i++)this.tanks.get(i).render(g);
            this.wall.render(g);
      }
}