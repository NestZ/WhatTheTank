package com.mystudio.wtt.entity;

import org.mini2Dx.core.graphics.Graphics;
import com.mystudio.wtt.utils.Point;
import org.mini2Dx.core.engine.geom.CollisionBox;

public interface Tiles{
      public static Tiles getInstance(char c, Point<Integer> p){
            switch(c){
                  case '.' :
                        return new NonCollisionTile(p.getX(), p.getY(), "GrassLand.png");
                  case '0' :
                        return new CollisionTile(p.getX(), p.getY(), "Iron.png", false);
                  case '*' :
                        return new NonCollisionTile(p.getX(), p.getY(), "Tree.png");
                  case '2' :
                        return new CollisionTile(p.getX(), p.getY(), "Wall_3.png", true);
                  case '1' :
                        return new NonCollisionTile(p.getX(), p.getY(), "Dd.png");
                  case '3' :
                        return new NonCollisionTile(p.getX(), p.getY(), "DL.png");
                  case '4' :
                        return new NonCollisionTile(p.getX(), p.getY(), "DOWN.png");
                  case '5' :
                        return new NonCollisionTile(p.getX(), p.getY(), "DR.png");
                  case '6' :
                        return new NonCollisionTile(p.getX(), p.getY(), "G.png");
                  case '7' :
                        return new NonCollisionTile(p.getX(), p.getY(), "G3L.png");
                  case '8' :
                        return new NonCollisionTile(p.getX(), p.getY(), "G3R.png");
                  case '9' :
                        return new NonCollisionTile(p.getX(), p.getY(), "G4.png");
                  case '/' :
                        return new NonCollisionTile(p.getX(), p.getY(), "GDKL.png");
                  case '\\' :
                        return new NonCollisionTile(p.getX(), p.getY(), "GDKR.png");
                  case '_' :
                        return new NonCollisionTile(p.getX(), p.getY(), "GS.png");
                  case '#' :
                        return new NonCollisionTile(p.getX(), p.getY(), "GSS.png");
                  case '$' :
                        return new NonCollisionTile(p.getX(), p.getY(), "GSUS.png");
                  case '@' :
                        return new NonCollisionTile(p.getX(), p.getY(), "GUKL.png");
                  case '+' :
                        return new NonCollisionTile(p.getX(), p.getY(), "GUKR.png");
                  case '%' :
                        return new NonCollisionTile(p.getX(), p.getY(), "gul.png");
                  case 'a' :
                        return new NonCollisionTile(p.getX(), p.getY(), "GUS.png");
                  case 'b' :
                        return new NonCollisionTile(p.getX(), p.getY(), "L.png");
                  case 'c' :
                        return new NonCollisionTile(p.getX(), p.getY(), "R.png");
                  case 'd' :
                        return new NonCollisionTile(p.getX(), p.getY(), "S.png");
                  case 'e' :
                        return new NonCollisionTile(p.getX(), p.getY(), "S3R.png");
                  case 'f' :
                        return new NonCollisionTile(p.getX(), p.getY(), "S3U.png");
                  case 'g' :
                        return new NonCollisionTile(p.getX(), p.getY(), "SB.png");
                  case 'h' :
                        return new NonCollisionTile(p.getX(), p.getY(), "SBB.png");
                  case 'i' :
                        return new NonCollisionTile(p.getX(), p.getY(), "SDKR.png");
                  case 'j' :
                        return new NonCollisionTile(p.getX(), p.getY(), "SGS.png");
                  case 'k' :
                        return new NonCollisionTile(p.getX(), p.getY(), "SGUS.png");
                  case 'l' :
                        return new NonCollisionTile(p.getX(), p.getY(), "SS.png");
                  case 'm' :
                        return new NonCollisionTile(p.getX(), p.getY(), "SUKR.png");
                  case 'n' :
                        return new NonCollisionTile(p.getX(), p.getY(), "SUS.png");
                  case 'o' :
                        return new NonCollisionTile(p.getX(), p.getY(), "TOP.png");
                  case 'p' :
                        return new NonCollisionTile(p.getX(), p.getY(), "UR.png");
                  case 'q' :
                        return new NonCollisionTile(p.getX(), p.getY(), "q.png");
                  case 'r' :
                        return new NonCollisionTile(p.getX(), p.getY(), "y.png");
                  case 's' :
                        return new NonCollisionTile(p.getX(), p.getY(), "z.png");
                  default :
                        return null;
            }
      }
      public abstract CollisionBox collisionBox();
      public abstract float getMaxX();
      public abstract float getMinX();
      public abstract float getMaxY();
      public abstract float getMinY();
      public abstract void shot();
      public abstract boolean isVisible();
      public abstract void update(float delta);
      public abstract void interpolate(float alpha);
      public abstract void render(Graphics g);
}