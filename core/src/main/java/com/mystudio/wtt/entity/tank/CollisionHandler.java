package com.mystudio.wtt.entity.tank;

import java.util.HashMap;
import java.util.Iterator;
import com.mystudio.wtt.entity.Wall;
import com.mystudio.wtt.utils.Point;

import org.mini2Dx.core.engine.geom.CollisionBox;

/**
 * Class to check all collsion that happened in this game.
 * 
 * @author NestZ
 */

public class CollisionHandler{
      /**
       * Objects that can colliding.
       */
      private HashMap<Point<Integer>, Wall> wall;
      private AdjObstacle adjObstacle;

      public CollisionHandler(){
            this.adjObstacle = new AdjObstacle();
      }

      /**
       * Set object to check collision with.
       * @param wall every wall in the map
       */
      public void setWall(HashMap<Point<Integer>, Wall> wall){
            this.wall = wall;
      }

      public void isCollide(Tank tank, HashMap<Integer, Tank> tanks){
            this.adjObstacle.resetFlag();
            this.adjObstacle.updatePlayerCenter(tank);
            this.adjObstacle.updateAdjWall();
            this.wallCollision(tank);
            this.tankCollision(tank, tanks);
            this.adjObstacle.updateValidDir(tank);
      }

      public void wallCollision(Tank tank){
            if(this.wall.containsKey(this.adjObstacle.leftUp)){
                  Wall w = this.wall.get(this.adjObstacle.leftUp);
                  if(w.collisionBox().intersects(tank.moveBox().collisionBox())){
                        if(Math.abs(tank.getMinX() - w.getMaxX()) > Math.abs(tank.getMinY() - w.getMaxY()) && !this.wall.containsKey(this.adjObstacle.left)){
                              tank.moveBox().setValidMove('U', false);
                              this.adjObstacle.upFlag = false;
                        }
                        else if(!this.wall.containsKey(this.adjObstacle.up)){
                              tank.moveBox().setValidMove('L', false);
                              this.adjObstacle.leftFlag = false;
                        }
                  }
            }
            if(this.wall.containsKey(this.adjObstacle.rightUp)){
                  Wall w = this.wall.get(this.adjObstacle.rightUp);
                  if(w.collisionBox().intersects(tank.moveBox().collisionBox())){
                        if(Math.abs(tank.getMaxX() - w.getMinX()) > Math.abs(tank.getMinY() - w.getMaxY()) && !this.wall.containsKey(this.adjObstacle.right)){
                              tank.moveBox().setValidMove('U', false);
                              this.adjObstacle.upFlag = false;
                        }
                        else if(!this.wall.containsKey(this.adjObstacle.up)){
                              tank.moveBox().setValidMove('R', false);
                              this.adjObstacle.rightFlag = false;
                        }
                  }
            }
            if(this.wall.containsKey(this.adjObstacle.leftDown)){
                  Wall w = this.wall.get(this.adjObstacle.leftDown);
                  if(this.wall.get(this.adjObstacle.leftDown).collisionBox().intersects(tank.moveBox().collisionBox())){
                        if(Math.abs(tank.getMinX() - w.getMaxX()) > Math.abs(tank.getMaxY() - w.getMinY()) && !this.wall.containsKey(this.adjObstacle.left)){
                              tank.moveBox().setValidMove('D', false);
                              this.adjObstacle.downFlag = false;
                        }
                        else if(!this.wall.containsKey(this.adjObstacle.down)){
                              tank.moveBox().setValidMove('L', false);
                              this.adjObstacle.leftFlag = false;
                        }
                  }
            }
            if(this.wall.containsKey(this.adjObstacle.rightDown)){
                  Wall w = this.wall.get(this.adjObstacle.rightDown);
                  if(this.wall.get(this.adjObstacle.rightDown).collisionBox().intersects(tank.moveBox().collisionBox())){
                        if(Math.abs(tank.getMaxX() - w.getMinX()) > Math.abs(tank.getMaxY() - w.getMinY()) && !this.wall.containsKey(this.adjObstacle.right)){
                              tank.moveBox().setValidMove('D', false);
                              this.adjObstacle.downFlag = false;
                        }
                        else if(!this.wall.containsKey(this.adjObstacle.down)){
                              tank.moveBox().setValidMove('R', false);
                              this.adjObstacle.rightFlag = false;
                        }
                  }
            }
            if(this.wall.containsKey(this.adjObstacle.up)){
                  if(this.wall.get(this.adjObstacle.up).collisionBox().intersects(tank.moveBox().collisionBox())){
                        tank.moveBox().setValidMove('U', false);
                        this.adjObstacle.upFlag = false;
                  }
            }
            if(this.wall.containsKey(this.adjObstacle.down)){
                  if(this.wall.get(this.adjObstacle.down).collisionBox().intersects(tank.moveBox().collisionBox())){
                        tank.moveBox().setValidMove('D', false);
                        this.adjObstacle.downFlag = false;
                  }
            }
            if(this.wall.containsKey(this.adjObstacle.left)){
                  if(this.wall.get(this.adjObstacle.left).collisionBox().intersects(tank.moveBox().collisionBox())){
                        tank.moveBox().setValidMove('L', false);
                        this.adjObstacle.leftFlag = false;
                  }
            }
            if(this.wall.containsKey(this.adjObstacle.right)){
                  if(this.wall.get(this.adjObstacle.right).collisionBox().intersects(tank.moveBox().collisionBox())){
                        tank.moveBox().setValidMove('R', false);
                        this.adjObstacle.rightFlag = false;
                  }
            }
      }

      public void tankCollision(Tank tank, HashMap<Integer, Tank> tanks){
            Iterator<Integer> it = tanks.keySet().iterator();
            CollisionBox c = tank.CollisionBox();
            while(it.hasNext()){
                  Tank t = tanks.get(it.next());
                  if(t.getID() != tank.getID()){
                        CollisionBox c2 = t.CollisionBox();
                        Point<Integer> p = this.adjObstacle.calPoint(t);
                        if(this.adjObstacle.leftUp.equals(p)){
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
                        if(this.adjObstacle.rightUp.equals(p)){
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
                        if(this.adjObstacle.leftDown.equals(p)){
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
                        if(this.adjObstacle.rightDown.equals(p)){
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
                        if(this.adjObstacle.up.equals(p)){
                              if(c.intersects(c2)){
                                    tank.moveBox().setValidMove('U', false);
                                    this.adjObstacle.upFlag = false;
                              }
                        }
                        if(this.adjObstacle.down.equals(p)){
                              if(c.intersects(c2)){
                                    tank.moveBox().setValidMove('D', false);
                                    this.adjObstacle.downFlag = false;
                              }
                        }
                        if(this.adjObstacle.left.equals(p)){
                              if(c.intersects(c2)){
                                    tank.moveBox().setValidMove('L', false);
                                    this.adjObstacle.leftFlag = false;
                              }
                        }
                        if(this.adjObstacle.right.equals(p)){
                              if(c.intersects(c2)){
                                    tank.moveBox().setValidMove('R', false);
                                    this.adjObstacle.rightFlag = false;
                              }
                        }
                  }
            }
      }

      private class AdjObstacle{
            private boolean upFlag;
            private boolean downFlag;
            private boolean leftFlag;
            private boolean rightFlag;
            private Point<Integer> playerCenter;
            private Point<Integer> up;
            private Point<Integer> down;
            private Point<Integer> left;
            private Point<Integer> right;
            private Point<Integer> leftUp;
            private Point<Integer> rightUp;
            private Point<Integer> leftDown;
            private Point<Integer> rightDown;

            public void resetFlag(){
                  this.upFlag = true;
                  this.downFlag = true;
                  this.leftFlag = true;
                  this.rightFlag = true;
            }

            public void updateValidDir(Tank tank){
                  if(this.upFlag)tank.moveBox().setValidMove('U', true);
                  if(this.downFlag)tank.moveBox().setValidMove('D', true);
                  if(this.leftFlag)tank.moveBox().setValidMove('L', true);
                  if(this.rightFlag)tank.moveBox().setValidMove('R', true);
            }

            public void updatePlayerCenter(Tank tank){
                  this.playerCenter = this.calPoint(tank);
            }

            public void updateAdjWall(){
                  this.up = new Point<>(this.playerCenter.getX() - 1, this.playerCenter.getY());
                  this.down = new Point<>(this.playerCenter.getX() + 1, this.playerCenter.getY());
                  this.left = new Point<>(this.playerCenter.getX(), this.playerCenter.getY() - 1);
                  this.right = new Point<>(this.playerCenter.getX(), this.playerCenter.getY() + 1);
                  this.leftUp = new Point<>(this.playerCenter.getX() - 1, this.playerCenter.getY() - 1);
                  this.rightUp = new Point<>(this.playerCenter.getX() - 1, this.playerCenter.getY() + 1);
                  this.leftDown = new Point<>(this.playerCenter.getX() + 1, this.playerCenter.getY() - 1);
                  this.rightDown = new Point<>(this.playerCenter.getX() + 1, this.playerCenter.getY() + 1);
            }

            public Point<Integer> calPoint(Tank tank){
                  return new Point<Integer>((int)((tank.getMaxY() + tank.getMinY()) / 128), (int)((tank.getMaxX() + tank.getMinX()) / 128));
            }
      }
}