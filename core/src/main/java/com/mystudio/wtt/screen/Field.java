package com.mystudio.wtt.screen;

import org.mini2Dx.core.graphics.Graphics;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import com.badlogic.gdx.Gdx;
import com.mystudio.wtt.utils.InputHandler;
import com.mystudio.wtt.entity.tank.CollisionHandler;
import com.mystudio.wtt.entity.tank.Tank;
import com.mystudio.wtt.entity.Wall;
import com.mystudio.wtt.client.ClientStarter;
import com.mystudio.wtt.entity.Brick;
import com.mystudio.wtt.entity.Bullet;

public class Field{
      private Wall wall;
      private InputHandler inputHandler;
      private CollisionHandler collisionHandler;
      private HashMap<Integer, Tank> tanks;
      private static int bulletNum = 0;
      public static ConcurrentHashMap<Integer, Bullet> bullets = new ConcurrentHashMap<>();

      public Field(HashMap<Integer, Tank> tanks)throws IOException{
            this.wall = new Brick();
            this.tanks = tanks;
            this.collisionHandler = new CollisionHandler();
            this.inputHandler = new InputHandler(this.tanks.get(ClientStarter.clientID()));
            this.setCollision();
            this.setTankSprite();
            //this.addExitListener();
      }

      public void setCollision(){
            this.collisionHandler.setWall(this.wall);
      }

      public void setTankSprite(){
            Iterator<Integer> it = this.tanks.keySet().iterator();
            while(it.hasNext()){
                  this.tanks.get(it.next()).setSprite();
            }
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

      // public void addExitListener(){
      //       Runtime.getRuntime().addShutdownHook(new Thread(){
      //             public void run(){
      //                  System.out.println("What the fuck why are u closing me");
      //             }
      //       });
      // }

      public static void addBullet(int TEAM, int dir, float x, float y){
            Field.bullets.put(Field.bulletNum++, new Bullet(x, y, dir, TEAM));
      }

      public void update(float delta){
            Gdx.input.setInputProcessor(this.inputHandler);
            this.setTankCollision();
            this.wall.update(delta);
            while(Bullet.noSprite.size() != 0){
                  Bullet.noSprite.getFirst().setSprite();
                  Bullet.noSprite.removeFirst();
            }
            Iterator<Integer> it = this.tanks.keySet().iterator();
            while(it.hasNext()){
                  this.tanks.get(it.next()).update(delta);
            }
            it = Field.bullets.keySet().iterator();
            while(it.hasNext()){
                  Field.bullets.get(it.next()).update(delta);
            }
      }

      public void interpolate(float alpha){
            this.wall.interpolate(alpha);
            Iterator<Integer> it = this.tanks.keySet().iterator();
            while(it.hasNext()){
                  this.tanks.get(it.next()).interpolate(alpha);
            }
            it = Field.bullets.keySet().iterator();
            while(it.hasNext()){
                  Field.bullets.get(it.next()).interpolate(alpha);
            }
      }

      public void render(Graphics g){
            this.wall.render(g);
            Iterator<Integer> it = this.tanks.keySet().iterator();
            while(it.hasNext()){
                  this.tanks.get(it.next()).render(g);
            }
            it = Field.bullets.keySet().iterator();
            while(it.hasNext()){
                  Field.bullets.get(it.next()).render(g);
            }
      }
}