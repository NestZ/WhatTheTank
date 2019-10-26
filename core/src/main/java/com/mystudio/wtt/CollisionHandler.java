package com.mystudio.wtt;

public class CollisionHandler{
      private Wall wall;

      public void setWall(Wall wall){
            this.wall = wall;
      }

      public char isCollide(Tank tank){
            this.setTankCollisionBox(tank);
            if(tank.tankR.intersects(this.wall.collisionBox()))return 'R';
            else if(tank.tankL.intersects(this.wall.collisionBox()))return 'L';
            else if(tank.tankU.intersects(this.wall.collisionBox()))return 'U';
            else if(tank.tankD.intersects(this.wall.collisionBox()))return 'D';
            return '\0';
      }

      private void setTankCollisionBox(Tank tank){
            tank.tankR.set(tank.getX() + tank.getWidth(), tank.getY() + 3, 1f, tank.getHeight() - 6);
            tank.tankL.set(tank.getX() - 1, tank.getY() + 3, 1f, tank.getHeight() - 6);
            tank.tankU.set(tank.getX() + 3, tank.getY() - 1, tank.getWidth() - 6, 1f);
            tank.tankD.set(tank.getX() + 3, tank.getY() + tank.getHeight(), tank.getWidth() - 6, 1f);
      }
}