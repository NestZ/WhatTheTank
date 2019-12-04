package com.mystudio.wtt.entity.tank;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import com.mystudio.wtt.entity.Bullet;
import com.mystudio.wtt.entity.Tiles;
import com.mystudio.wtt.utils.Point;
import org.mini2Dx.core.engine.geom.CollisionBox;

/**
 * Class to check all collsion between entities that happened in this game.
 * 
 * @author NestZ
 */

public class CollisionHandler{
      /**
       * Objects that can colliding.
       */
      private ConcurrentHashMap<Point<Integer>, Tiles> tiles;
      private ConcurrentHashMap<Integer, Bullet> bullets;
      private AdjObstacle adjObstacle;

      /**
       * Constructor initialize walls and bullets.
       * @param wall initial wall
       * @param bullets initial bullet
       */
      public CollisionHandler(ConcurrentHashMap<Point<Integer>, Tiles> tiles, ConcurrentHashMap<Integer, Bullet> bullets){
            this.adjObstacle = new AdjObstacle();
            this.tiles = tiles;
            this.bullets = bullets;
      }

      /**
       * Check if particular tank collision with entities.
       * @param tank current tank
       * @param tanks all other tanks
       */
      public void isCollide(Tank tank, HashMap<Integer, Tank> tanks){
            this.adjObstacle.resetFlag();
            this.adjObstacle.updatePlayerCenter(tank);
            this.adjObstacle.updateAdjWall();
            this.wallCollision(tank);
            this.tankCollision(tank, tanks);
            this.adjObstacle.updateValidDir(tank);
      }

      /**
       * Check if bullet collision with entities
       * @param b current bullet
       * @param tanks all other tanks
       */
      public void isCollide(Bullet b, HashMap<Integer, Tank> tanks){
            if(b != null){
                  this.adjObstacle.updateBulletCenter(b);
                  this.adjObstacle.updateBulletAdjWall();
                  this.bulletHit(b, tanks);
            }
      }

      /**
       * Check if particular tank collision with walls
       * @param tank current tank
       */
      public void wallCollision(Tank tank){
            CollisionBox c = tank.CollisionBox();
            if(this.tiles.containsKey(this.adjObstacle.playerAdj.get("leftUp"))){
                  Tiles w = this.tiles.get(this.adjObstacle.playerAdj.get("leftUp"));
                  if(w.collisionBox().intersects(c)){
                        if(Math.abs(tank.getMinX() - w.getMaxX()) > Math.abs(tank.getMinY() - w.getMaxY()) && !this.tiles.containsKey(this.adjObstacle.playerAdj.get("left"))){
                              tank.moveBox().setValidMove('U', false);
                              this.adjObstacle.upFlag = false;
                        }
                        else if(!this.tiles.containsKey(this.adjObstacle.playerAdj.get("up"))){
                              tank.moveBox().setValidMove('L', false);
                              this.adjObstacle.leftFlag = false;
                        }
                  }
            }
            if(this.tiles.containsKey(this.adjObstacle.playerAdj.get("rightUp"))){
                  Tiles w = this.tiles.get(this.adjObstacle.playerAdj.get("rightUp"));
                  if(w.collisionBox().intersects(c)){
                        if(Math.abs(tank.getMaxX() - w.getMinX()) > Math.abs(tank.getMinY() - w.getMaxY()) && !this.tiles.containsKey(this.adjObstacle.playerAdj.get("right"))){
                              tank.moveBox().setValidMove('U', false);
                              this.adjObstacle.upFlag = false;
                        }
                        else if(!this.tiles.containsKey(this.adjObstacle.playerAdj.get("up"))){
                              tank.moveBox().setValidMove('R', false);
                              this.adjObstacle.rightFlag = false;
                        }
                  }
            }
            if(this.tiles.containsKey(this.adjObstacle.playerAdj.get("leftDown"))){
                  Tiles w = this.tiles.get(this.adjObstacle.playerAdj.get("leftDown"));
                  if(this.tiles.get(this.adjObstacle.playerAdj.get("leftDown")).collisionBox().intersects(c)){
                        if(Math.abs(tank.getMinX() - w.getMaxX()) > Math.abs(tank.getMaxY() - w.getMinY()) && !this.tiles.containsKey(this.adjObstacle.playerAdj.get("left"))){
                              tank.moveBox().setValidMove('D', false);
                              this.adjObstacle.downFlag = false;
                        }
                        else if(!this.tiles.containsKey(this.adjObstacle.playerAdj.get("down"))){
                              tank.moveBox().setValidMove('L', false);
                              this.adjObstacle.leftFlag = false;
                        }
                  }
            }
            if(this.tiles.containsKey(this.adjObstacle.playerAdj.get("rightDown"))){
                  Tiles w = this.tiles.get(this.adjObstacle.playerAdj.get("rightDown"));
                  if(this.tiles.get(this.adjObstacle.playerAdj.get("rightDown")).collisionBox().intersects(c)){
                        if(Math.abs(tank.getMaxX() - w.getMinX()) > Math.abs(tank.getMaxY() - w.getMinY()) && !this.tiles.containsKey(this.adjObstacle.playerAdj.get("right"))){
                              tank.moveBox().setValidMove('D', false);
                              this.adjObstacle.downFlag = false;
                        }
                        else if(!this.tiles.containsKey(this.adjObstacle.playerAdj.get("down"))){
                              tank.moveBox().setValidMove('R', false);
                              this.adjObstacle.rightFlag = false;
                        }
                  }
            }
            if(this.wallCollisionNormalDir(tank, "up", 'U', c))this.adjObstacle.upFlag = false;
            if(this.wallCollisionNormalDir(tank, "down", 'D', c))this.adjObstacle.downFlag = false;
            if(this.wallCollisionNormalDir(tank, "left", 'L', c))this.adjObstacle.leftFlag = false;
            if(this.wallCollisionNormalDir(tank, "right", 'R', c))this.adjObstacle.rightFlag = false;
      }

      /**
       * Check if tank collision with wall in normal direction (up, down, left, right).
       * @param tank current tank
       * @param dir current direction in string
       * @param dirChar current direction in character
       * @param c tank's collision box
       * 
       * @return true if collision happened otherwise return false
       */
      public boolean wallCollisionNormalDir(Tank tank, String dir, char dirChar, CollisionBox c){
            if(this.tiles.containsKey(this.adjObstacle.playerAdj.get(dir))){
                  if(this.tiles.get(this.adjObstacle.playerAdj.get(dir)).collisionBox().intersects(c)){
                        tank.moveBox().setValidMove(dirChar, false);
                        return true;
                  }
            }
            return false;
      }

      /**
       * Check if particular tank collision with other tanks.
       * @param tank current tank
       * @param tanks other tanks
       */
      public void tankCollision(Tank tank, HashMap<Integer, Tank> tanks){
            Iterator<Integer> it = tanks.keySet().iterator();
            CollisionBox c = tank.CollisionBox();
            while(it.hasNext()){
                  Tank t = tanks.get(it.next());
                  if(t.getID() != tank.getID()){
                        CollisionBox c2 = t.CollisionBox();
                        Point<Integer> p = this.adjObstacle.calPoint(t);
                        if(this.adjObstacle.playerAdj.get("leftUp").equals(p)){
                              if(c.intersects(c2)){
                                    if(Math.abs(tank.getMinX() - t.getMaxX()) > Math.abs(tank.getMinY() - t.getMaxY())){
                                          tank.moveBox().setValidMove('U', false);
                                          this.adjObstacle.upFlag = false;
                                    }
                                    else{
                                          tank.moveBox().setValidMove('L', false);
                                          this.adjObstacle.leftFlag = false;
                                    }
                              }
                        }
                        if(this.adjObstacle.playerAdj.get("rightUp").equals(p)){
                              if(c.intersects(c2)){
                                    if(Math.abs(tank.getMaxX() - t.getMinX()) > Math.abs(tank.getMinY() - t.getMaxY())){
                                          tank.moveBox().setValidMove('U', false);
                                          this.adjObstacle.upFlag = false;
                                    }
                                    else{
                                          tank.moveBox().setValidMove('R', false);
                                          this.adjObstacle.rightFlag = false;
                                    }
                              }
                        }
                        if(this.adjObstacle.playerAdj.get("leftDown").equals(p)){
                              if(c.intersects(c2)){
                                    if(Math.abs(tank.getMinX() - t.getMaxX()) > Math.abs(tank.getMaxY() - t.getMinY())){
                                          tank.moveBox().setValidMove('D', false);
                                          this.adjObstacle.downFlag = false;
                                    }
                                    else{
                                          tank.moveBox().setValidMove('L', false);
                                          this.adjObstacle.leftFlag = false;
                                    }
                              }
                        }
                        if(this.adjObstacle.playerAdj.get("rightDown").equals(p)){
                              if(c.intersects(c2)){
                                    if(Math.abs(tank.getMaxX() - t.getMinX()) > Math.abs(tank.getMaxY() - t.getMinY())){
                                          tank.moveBox().setValidMove('D', false);
                                          this.adjObstacle.downFlag = false;
                                    }
                                    else{
                                          tank.moveBox().setValidMove('R', false);
                                          this.adjObstacle.rightFlag = false;
                                    }
                              }
                        }
                        if(this.tankCollisionNormalDir(tank, c, c2, p, "up", 'U'))this.adjObstacle.upFlag = false;
                        if(this.tankCollisionNormalDir(tank, c, c2, p, "down", 'D'))this.adjObstacle.downFlag = false;
                        if(this.tankCollisionNormalDir(tank, c, c2, p, "left", 'L'))this.adjObstacle.leftFlag = false;
                        if(this.tankCollisionNormalDir(tank, c, c2, p, "right", 'R'))this.adjObstacle.rightFlag = false;
                  }
            }
      }

      /**
       * Check if particular tank collision with others in normal direction (up, down, left, right).
       * @param tank current tank
       * @param c current tank's collision box
       * @param c2 other tank's collision box
       * @param p other tank's point in x,y
       * @param dir current direction in string
       * @param dirChar current direction in character
       * 
       * @return true if current tank collision with others
       */
      public boolean tankCollisionNormalDir(Tank tank, CollisionBox c, CollisionBox c2, Point<Integer> p, String dir, char dirChar){
            if(this.adjObstacle.playerAdj.get(dir).equals(p)){
                  if(c.intersects(c2)){
                        tank.moveBox().setValidMove(dirChar, false);
                        return true;
                  }
            }
            return false;
      }

      /**
       * Check if particular bullet collisions with other entities.
       * @param b current bullet
       * @param tanks other tanks
       */
      public void bulletHit(Bullet b, HashMap<Integer, Tank> tanks){
            switch(b.getDir()){
                  case 1 :
                        String [] upDir = {"up", "center", "left", "right"};
                        if(this.bulletAndWallCollision(b, upDir))break;
                        this.bulletAndTankCollision(b, tanks, upDir);
                        break;
                  case 2 :
                        String [] downDir = {"down", "center", "left", "right"};
                        if(this.bulletAndWallCollision(b, downDir))break;
                        this.bulletAndTankCollision(b, tanks, downDir);
                        break;
                  case 3 :
                        String [] leftDir = {"left", "center", "up", "down"};
                        if(this.bulletAndWallCollision(b, leftDir))break;
                        this.bulletAndTankCollision(b, tanks, leftDir);
                        break;
                  case 4 :
                        String [] rightDir = {"right", "center", "up", "down"};
                        if(this.bulletAndWallCollision(b, rightDir))break;
                        this.bulletAndTankCollision(b, tanks, rightDir);
                        break;
            };
       }
 
       /**
        * Check if particular bullet collisions with wall.
        * @param b current bullet
        * @param dir checking direction

        * @return true if collision happened otherwise return false
        */
       public boolean bulletAndWallCollision(Bullet b, String [] dir){
            for(String s : dir){
                  if(this.tiles.containsKey(this.adjObstacle.bulletAdj.get(s))){
                        if(b.collisionBox().intersects(this.tiles.get(this.adjObstacle.bulletAdj.get(s)).collisionBox())){
                              this.tiles.get(this.adjObstacle.bulletAdj.get(s)).shot();
                              this.bullets.remove(b.getID());
                              return true;
                        }
                  }
            }
            return false;
       }
 
       /**
        * Check if particular bullet collisions with tank.
        * @param b current bullet
        * @param tanks other tanks
        * @param dir checking direction
        */
       public void bulletAndTankCollision(Bullet b, HashMap<Integer, Tank> tanks, String [] dir){
            Iterator<Integer> it = tanks.keySet().iterator();
            while(it.hasNext()){
                  Tank t = tanks.get(it.next());
                  Point<Integer> p = this.adjObstacle.calPoint(t);
                  if(t.getID() != b.getShooterID()){
                        for(String s : dir){
                              if(this.adjObstacle.bulletAdj.get(s).equals(p)){
                                    if(b.collisionBox().intersects(t.CollisionBox())){
                                          this.bullets.remove(b.getID());
                                          if(t.team() != tanks.get(b.getShooterID()).team())t.shot();
                                          break;
                                    }
                              }
                        }
                  }
            }
       }

      /**
       * A class to store adjacency obstacle used to calculate collisions.
       * 
       * @@author NestZ
       */
      private class AdjObstacle{
            /**
             * Flags and adjacent maps.
             */
            private boolean upFlag;
            private boolean downFlag;
            private boolean leftFlag;
            private boolean rightFlag;
            private HashMap<String, Point<Integer>> bulletAdj;
            private HashMap<String, Point<Integer>> playerAdj;

            /**
             * Constructor to initialize maps.
             */
            public AdjObstacle(){
                  this.bulletAdj = new HashMap<>();
                  this.playerAdj = new HashMap<>();
            }

            /**
             * Used to reset flag every frame.
             */
            public void resetFlag(){
                  this.upFlag = true;
                  this.downFlag = true;
                  this.leftFlag = true;
                  this.rightFlag = true;
            }

            /**
             * Update tank's valid move direction.
             * @param tank current tank
             */
            public void updateValidDir(Tank tank){
                  if(this.upFlag)tank.moveBox().setValidMove('U', true);
                  if(this.downFlag)tank.moveBox().setValidMove('D', true);
                  if(this.leftFlag)tank.moveBox().setValidMove('L', true);
                  if(this.rightFlag)tank.moveBox().setValidMove('R', true);
            }

            /**
             * Update player's center position.
             * @param tank tank to update
             */
            public void updatePlayerCenter(Tank tank){
                  this.playerAdj.put("center", this.calPoint(tank));
            }

            /**
             * Update bullet's center position.
             * @param b bullet to update
             */
            public void updateBulletCenter(Bullet b){
                  this.bulletAdj.put("center", new Point<Integer>((int)((b.getMaxY() + b.getMinY()) / 128),(int)((b.getMaxX() + b.getMinX()) / 128)));
            }

            /**
             * Update player's adjacent wall.
             */
            public void updateAdjWall(){
                  this.playerAdj.put("up", new Point<>(this.playerAdj.get("center").getX() - 1, this.playerAdj.get("center").getY()));
                  this.playerAdj.put("down", new Point<>(this.playerAdj.get("center").getX() + 1, this.playerAdj.get("center").getY()));
                  this.playerAdj.put("left", new Point<>(this.playerAdj.get("center").getX(), this.playerAdj.get("center").getY() - 1));
                  this.playerAdj.put("right", new Point<>(this.playerAdj.get("center").getX(), this.playerAdj.get("center").getY() + 1));
                  this.playerAdj.put("leftUp", new Point<>(this.playerAdj.get("center").getX() - 1, this.playerAdj.get("center").getY() - 1));
                  this.playerAdj.put("rightUp", new Point<>(this.playerAdj.get("center").getX() - 1, this.playerAdj.get("center").getY() + 1));
                  this.playerAdj.put("leftDown", new Point<>(this.playerAdj.get("center").getX() + 1, this.playerAdj.get("center").getY() - 1));
                  this.playerAdj.put("rightDown", new Point<>(this.playerAdj.get("center").getX() + 1, this.playerAdj.get("center").getY() + 1));
            }

            /**
             * Update bullet's adjacent wall.
             */
            public void updateBulletAdjWall(){
                  this.bulletAdj.put("up", new Point<>(this.bulletAdj.get("center").getX() - 1, this.bulletAdj.get("center").getY()));
                  this.bulletAdj.put("down", new Point<>(this.bulletAdj.get("center").getX() + 1, this.bulletAdj.get("center").getY()));
                  this.bulletAdj.put("left", new Point<>(this.bulletAdj.get("center").getX(), this.bulletAdj.get("center").getY() - 1));
                  this.bulletAdj.put("right", new Point<>(this.bulletAdj.get("center").getX(), this.bulletAdj.get("center").getY() + 1));
            }

            /**
             * Calculate x,y position of tank.
             * @param tank current tank
             * 
             * @return tank's position in x,y
             */
            public Point<Integer> calPoint(Tank tank){
                  return new Point<Integer>((int)((tank.getMaxY() + tank.getMinY()) / 128), (int)((tank.getMaxX() + tank.getMinX()) / 128));
            }
      }
}