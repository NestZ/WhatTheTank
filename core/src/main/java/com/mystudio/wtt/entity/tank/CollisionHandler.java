package com.mystudio.wtt.entity.tank;

import java.util.HashMap;
import java.util.Iterator;
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
            Iterator<Point<Integer>> it = this.wall.keySet().iterator();
            while(it.hasNext()){
                  Wall w = wall.get(it.next());
                  if(tank.moveBox().collisionBox().intersects(w.collisionBox())){
                        Point<Integer> playerCenter = new Point<>((int)((tank.getMaxX() + tank.getMinX()) / 2), (int)((tank.getMaxY() + tank.getMinY()) / 2));
                  }
            }
      }
}