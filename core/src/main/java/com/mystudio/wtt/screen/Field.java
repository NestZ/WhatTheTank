package com.mystudio.wtt.screen;

import org.mini2Dx.core.graphics.Graphics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.mystudio.wtt.client.ClientStarter;
import com.mystudio.wtt.utils.InputHandler;
import com.mystudio.wtt.entity.tank.CollisionHandler;
import com.mystudio.wtt.entity.tank.Tank;
import com.mystudio.wtt.entity.Wall;
import com.mystudio.wtt.entity.Brick;
import com.mystudio.wtt.entity.Bullet;

public class Field{
      private Wall wall;
      private InputHandler inputHandler;
      private CollisionHandler collisionHandler;
      private ClientStarter client;
      private int clientsNum = 0;
      private int clientID;
      private HashMap<Integer, Tank> tanks;

      public Field(){
            this.wall = new Brick();
            this.collisionHandler = new CollisionHandler();
            // try{
            //       this.client = new ClientStarter("127.0.0.1");
            // }
            // catch(IOException e){
            //       e.printStackTrace();
            // }
            // while(!ClientStarter.isReady());
            // this.clientID = this.client.thread().getID();
            // this.tanks = this.client.thread().getTanks();
            this.inputHandler = new InputHandler(this.tanks.get(this.clientID), this.client);
            this.setCollision();
            this.addExitListener();
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

      public void addExitListener(){
            Runtime.getRuntime().addShutdownHook(new Thread(){
                  public void run(){
                       System.out.println("What the fuck why are u closing me");
                  }
            });
      }

      public void update(float delta){
            Gdx.input.setInputProcessor(this.inputHandler);
            if(this.clientsNum != this.tanks.size())this.registerNewTank();
            this.setTankCollision();
            this.wall.update(delta);
            while(Bullet.noSprite.size() != 0){
                  Bullet.noSprite.getFirst().setSprite();
                  Bullet.noSprite.removeFirst();
            }
            for(int i = 0;i < this.tanks.size();i++){
                  this.tanks.get(i).update(delta);
                  Iterator<Integer> it = Bullet.bulletss.keySet().iterator();
                  while(it.hasNext()){
                        Bullet.bulletss.get(it.next()).update(delta);
                  }
            }
      }

      public void interpolate(float alpha){
            this.wall.interpolate(alpha);
            for(int i = 0;i < this.tanks.size();i++){
                  this.tanks.get(i).interpolate(alpha);
                  Iterator<Integer> it = Bullet.bulletss.keySet().iterator();
                  while(it.hasNext()){
                        Bullet.bulletss.get(it.next()).interpolate(alpha);
                  }
            }
      }

      public void render(Graphics g){
            this.wall.render(g);
            for(int i = 0;i < this.tanks.size();i++){
                  this.tanks.get(i).render(g);
                  Iterator<Integer> it = Bullet.bulletss.keySet().iterator();
                  while(it.hasNext()){
                        Bullet.bulletss.get(it.next()).render(g);
                  }
            }
      }
}