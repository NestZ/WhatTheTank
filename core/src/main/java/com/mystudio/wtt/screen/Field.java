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
import com.mystudio.wtt.entity.Tiles;
import com.mystudio.wtt.client.ClientThread;
import com.mystudio.wtt.entity.Bullet;
import com.mystudio.wtt.entity.Map;

public class Field{
      private ConcurrentHashMap<Point<Integer>, Tiles> brick;
      private HashMap<Point<Integer>, Tiles> floating;
      private HashMap<Point<Integer>, Tiles> land;
      private HashMap<Integer, Tank> tanks;
      private InputHandler inputHandler;
      private CollisionHandler collisionHandler;
      private static int bulletNum = 0;
      public static ConcurrentHashMap<Integer, Bullet> bullets = new ConcurrentHashMap<>();

      public Field(HashMap<Integer, Tank> tanks, Map map)throws IOException{
            this.brick = map.getBrick();
            this.land = map.getLand();
            this.floating = map.getFloating();
            this.tanks = tanks;
            this.collisionHandler = new CollisionHandler(this.brick, Field.bullets);
            this.inputHandler = new InputHandler(this.tanks.get(ClientThread.clientID()));
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

      public static void addBullet(int TEAM, int dir, float x, float y, int id){
            Field.bullets.put(Field.bulletNum, new Bullet(x, y, dir, TEAM, Field.bulletNum++, id));
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
                  Bullet b = Field.bullets.get(it.next());
                  this.collisionHandler.isCollide(b, this.tanks);
                  b.update(delta);
            }
            Iterator<Point<Integer>> it2 = this.brick.keySet().iterator();
            while(it2.hasNext()){
                  Point<Integer> p = it2.next();
                  Tiles w = this.brick.get(p);
                  if(w.isVisible())w.update(delta);
                  else if(this.brick.containsKey(p))this.brick.remove(p);
            }
      }

      public void interpolate(float alpha){
            Iterator<Integer> it = this.tanks.keySet().iterator();
            while(it.hasNext()){
                  Tank t = this.tanks.get(it.next());
                  if(t.visible())t.interpolate(alpha);
            }
            it = Field.bullets.keySet().iterator();
            while(it.hasNext()){
                  Field.bullets.get(it.next()).interpolate(alpha);
            }
            Iterator<Point<Integer>> it2 = this.brick.keySet().iterator();
            while(it2.hasNext()){
                  Tiles w = this.brick.get(it2.next());
                  if(w.isVisible())w.interpolate(alpha);
            }
      }

      public void render(Graphics g){
            Iterator<Point<Integer>> it = this.land.keySet().iterator();
            while(it.hasNext()){
                  this.land.get(it.next()).render(g);
            }
            Iterator<Integer> it2 = Field.bullets.keySet().iterator();
            while(it2.hasNext()){
                  Field.bullets.get(it2.next()).render(g);
            }
            it2 = this.tanks.keySet().iterator();
            while(it2.hasNext()){
                  Tank t = this.tanks.get(it2.next());
                  if(t.visible())t.render(g);
            }
            it = this.brick.keySet().iterator();
            while(it.hasNext()){
                  Tiles w = this.brick.get(it.next());
                  if(w.isVisible())w.render(g);
            }
            it = this.floating.keySet().iterator();
            while(it.hasNext()){
                  this.floating.get(it.next()).render(g);
            }
      }
}