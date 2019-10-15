package com.mystudio.wtt;

import org.mini2Dx.core.engine.geom.CollisionBox;

public class CollisionHandler{
      private Tank tank;
      private CollisionBox tankR;
      private CollisionBox tankL;
      private CollisionBox tankU;
      private CollisionBox tankD;
      private Wall wall;

      public void setTank(Tank tank){
            this.tank = tank;
            this.tankR = new CollisionBox();
            this.tankL = new CollisionBox();
            this.tankU = new CollisionBox();
            this.tankD = new CollisionBox();
      }

      public void setWall(Wall wall){
            this.wall = wall;
      }

      public char isCollide(){
            this.setTankCollisionBox();
            if(this.tankR.intersects(this.wall.collisionBox()))return 'R';
            else if(this.tankL.intersects(this.wall.collisionBox()))return 'L';
            else if(this.tankU.intersects(this.wall.collisionBox()))return 'U';
            else if(this.tankD.intersects(this.wall.collisionBox()))return 'D';
            return '\0';
      }

      private void setTankCollisionBox(){
            this.tankR.set(this.tank.getX() + this.tank.getWidth(), this.tank.getY() + 3, 1f, this.tank.getHeight() - 6);
            this.tankL.set(this.tank.getX() - 1,this.tank.getY() + 3, 1f,this.tank.getHeight() - 6);
            this.tankU.set(this.tank.getX() + 3, this.tank.getY() - 1, this.tank.getWidth() - 6, 1f);
            this.tankD.set(this.tank.getX() + 3, this.tank.getY() + this.tank.getHeight(), this.tank.getWidth() - 6, 1f);
      }
}