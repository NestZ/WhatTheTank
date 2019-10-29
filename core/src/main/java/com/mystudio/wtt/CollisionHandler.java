package com.mystudio.wtt;

public class CollisionHandler{
      private Wall wall;

      public void setWall(Wall wall){
            this.wall = wall;
      }

      public char isCollide(Tank tank){
            this.setTankCollisionBox(tank);
            if(tank.moveBox().R().intersects(this.wall.collisionBox()))return 'R';
            else if(tank.moveBox().L().intersects(this.wall.collisionBox()))return 'L';
            else if(tank.moveBox().U().intersects(this.wall.collisionBox()))return 'U';
            else if(tank.moveBox().D().intersects(this.wall.collisionBox()))return 'D';
            return '\0';
      }

      private void setTankCollisionBox(Tank tank){
            tank.moveBox().R().set(tank.getX() + tank.getWidth(), tank.getY() + 3, 1f, tank.getHeight() - 6);
            tank.moveBox().L().set(tank.getX() - 1, tank.getY() + 3, 1f, tank.getHeight() - 6);
            tank.moveBox().U().set(tank.getX() + 3, tank.getY() - 1, tank.getWidth() - 6, 1f);
            tank.moveBox().D().set(tank.getX() + 3, tank.getY() + tank.getHeight(), tank.getWidth() - 6, 1f);
      }
}