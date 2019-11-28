package com.mystudio.wtt.screen;

import org.mini2Dx.core.graphics.Graphics;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import com.badlogic.gdx.Gdx;
import com.mystudio.wtt.utils.InputHandler;
import com.mystudio.wtt.utils.Point;
import com.mystudio.wtt.entity.tank.CollisionHandler;
import com.mystudio.wtt.entity.tank.Tank;
import com.mystudio.wtt.entity.Wall;
import com.mystudio.wtt.client.ClientStarter;
import com.mystudio.wtt.entity.Bullet;
import com.mystudio.wtt.entity.Map;

public class Field{
      private HashMap<Point<Integer>, Wall> wall;
      private HashMap<Integer, Tank> tanks;
      private InputHandler inputHandler;
      private CollisionHandler collisionHandler;
      private static int bulletNum = 0;
      public static ConcurrentHashMap<Integer, Bullet> bullets = new ConcurrentHashMap<>();

      public Field(HashMap<Integer, Tank> tanks, Map map)throws IOException{
            this.wall = map.getWallMap();
            this.tanks = tanks;
            this.collisionHandler = new CollisionHandler();
            this.inputHandler = new InputHandler(this.tanks.get(ClientStarter.clientID()));
            this.collisionHandler.setWall(this.wall);
            this.setTankSprite();
            //this.addExitListener();
      }

      public void setTankSprite(){
            Iterator<Integer> it = this.tanks.keySet().iterator();
            while(it.hasNext()){
                  this.tanks.get(it.next()).setSprite();
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
            while(Bullet.noSprite.size() != 0){
                  Bullet.noSprite.getFirst().setSprite();
                  Bullet.noSprite.removeFirst();
            }
            Iterator<Integer> it = this.tanks.keySet().iterator();
            while(it.hasNext()){
                  Tank t = this.tanks.get(it.next());
                  this.collisionHandler.isCollide(t, tanks);
                  t.update(delta);
            }
            it = Field.bullets.keySet().iterator();
            while(it.hasNext()){
                  Field.bullets.get(it.next()).update(delta);
            }
            Iterator<Point<Integer>> it2 = this.wall.keySet().iterator();
            while(it2.hasNext()){
                  this.wall.get(it2.next()).update(delta);
            }
      }

      public void interpolate(float alpha){
            Iterator<Integer> it = this.tanks.keySet().iterator();
            while(it.hasNext()){
                  this.tanks.get(it.next()).interpolate(alpha);
            }
            it = Field.bullets.keySet().iterator();
            while(it.hasNext()){
                  Field.bullets.get(it.next()).interpolate(alpha);
            }
            Iterator<Point<Integer>> it2 = this.wall.keySet().iterator();
            while(it2.hasNext()){
                  this.wall.get(it2.next()).interpolate(alpha);
            }
      }

      public void render(Graphics g){
            Iterator<Integer> it = this.tanks.keySet().iterator();
            while(it.hasNext()){
                  this.tanks.get(it.next()).render(g);
            }
            it = Field.bullets.keySet().iterator();
            while(it.hasNext()){
                  Field.bullets.get(it.next()).render(g);
            }
            Iterator<Point<Integer>> it2 = this.wall.keySet().iterator();
            while(it2.hasNext()){
                  this.wall.get(it2.next()).render(g);
            }
      }
}