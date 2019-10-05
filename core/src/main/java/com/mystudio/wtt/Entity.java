package com.mystudio.wtt;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.engine.geom.CollisionBox;

public abstract class Entity{
      public Sprite sprite;
      public CollisionBox collisionBox;

      abstract void update(float delta);
      abstract void interpolate(float alpha);
      abstract void render(Graphics g);
}