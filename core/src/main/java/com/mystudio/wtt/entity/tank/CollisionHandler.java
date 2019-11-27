package com.mystudio.wtt.entity.tank;

import java.util.HashMap;
import com.mystudio.wtt.entity.Wall;
import com.mystudio.wtt.utils.Point;

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

      /**
       * Set object to check collision with.
       * @param wall every wall in the map
       */
      public void setWall(HashMap<Point<Integer>, Wall> wall){
            this.wall = wall;
      }

      public void isCollide(Tank tank){
            boolean u = true;
            boolean d = true;
            boolean l = true;
            boolean r = true;
            Point<Integer> playerCenter = new Point<>((int)((tank.getMaxY() + tank.getMinY()) / 128), (int)((tank.getMaxX() + tank.getMinX()) / 128));
            Point<Integer> up = new Point<>(playerCenter.getX() - 1, playerCenter.getY());
            Point<Integer> down = new Point<>(playerCenter.getX() + 1, playerCenter.getY());
            Point<Integer> left = new Point<>(playerCenter.getX(), playerCenter.getY() - 1);
            Point<Integer> right = new Point<>(playerCenter.getX(), playerCenter.getY() + 1);
            Point<Integer> leftUp = new Point<>(playerCenter.getX() - 1, playerCenter.getY() - 1);
            Point<Integer> rightUp = new Point<>(playerCenter.getX() - 1, playerCenter.getY() + 1);
            Point<Integer> leftDown = new Point<>(playerCenter.getX() + 1, playerCenter.getY() - 1);
            Point<Integer> rightDown = new Point<>(playerCenter.getX() + 1, playerCenter.getY() + 1);
            if(this.wall.containsKey(leftUp)){
                  Wall w = this.wall.get(leftUp);
                  if(w.collisionBox().intersects(tank.moveBox().collisionBox())){
                        if(Math.abs(tank.getMinX() - w.getMaxX()) > Math.abs(tank.getMinY() - w.getMaxY()) && !this.wall.containsKey(left)){
                              tank.moveBox().setValidMove('U', false);
                              u = false;
                        }
                        else if(!this.wall.containsKey(up)){
                              tank.moveBox().setValidMove('L', false);
                              l = false;
                        }
                  }
            }
            if(this.wall.containsKey(rightUp)){
                  Wall w = this.wall.get(rightUp);
                  if(w.collisionBox().intersects(tank.moveBox().collisionBox())){
                        if(Math.abs(tank.getMaxX() - w.getMinX()) > Math.abs(tank.getMinY() - w.getMaxY()) && !this.wall.containsKey(right)){
                              tank.moveBox().setValidMove('U', false);
                              u = false;
                        }
                        else if(!this.wall.containsKey(up)){
                              tank.moveBox().setValidMove('R', false);
                              r = false;
                        }
                  }
            }
            if(this.wall.containsKey(leftDown)){
                  Wall w = this.wall.get(leftDown);
                  if(this.wall.get(leftDown).collisionBox().intersects(tank.moveBox().collisionBox())){
                        if(Math.abs(tank.getMinX() - w.getMaxX()) > Math.abs(tank.getMaxY() - w.getMinY()) && !this.wall.containsKey(left)){
                              tank.moveBox().setValidMove('D', false);
                              d = false;
                        }
                        else if(!this.wall.containsKey(down)){
                              tank.moveBox().setValidMove('L', false);
                              l = false;
                        }
                  }
            }
            if(this.wall.containsKey(rightDown)){
                  Wall w = this.wall.get(rightDown);
                  if(this.wall.get(rightDown).collisionBox().intersects(tank.moveBox().collisionBox())){
                        if(Math.abs(tank.getMaxX() - w.getMinX()) > Math.abs(tank.getMaxY() - w.getMinY()) && !this.wall.containsKey(right)){
                              tank.moveBox().setValidMove('D', false);
                              d = false;
                        }
                        else if(!this.wall.containsKey(down)){
                              tank.moveBox().setValidMove('R', false);
                              r = false;
                        }
                  }
            }
            if(this.wall.containsKey(up)){
                  System.out.println("up!!\n");
                  if(this.wall.get(up).collisionBox().intersects(tank.moveBox().collisionBox())){
                        tank.moveBox().setValidMove('U', false);
                        u = false;
                  }
            }
            if(this.wall.containsKey(down)){
                  System.out.println("down!!\n");
                  if(this.wall.get(down).collisionBox().intersects(tank.moveBox().collisionBox())){
                        tank.moveBox().setValidMove('D', false);
                        d = false;
                  }
            }
            if(this.wall.containsKey(left)){
                  System.out.println("left!!\n");
                  if(this.wall.get(left).collisionBox().intersects(tank.moveBox().collisionBox())){
                        tank.moveBox().setValidMove('L', false);
                        l = false;
                  }
            }
            if(this.wall.containsKey(right)){
                  System.out.println("right!!\n");
                  if(this.wall.get(right).collisionBox().intersects(tank.moveBox().collisionBox())){
                        tank.moveBox().setValidMove('R', false);
                        r = false;
                  }
            }
            if(u)tank.moveBox().setValidMove('U', true);
            if(d)tank.moveBox().setValidMove('D', true);
            if(l)tank.moveBox().setValidMove('L', true);
            if(r)tank.moveBox().setValidMove('R', true);
      }
}