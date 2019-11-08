package com.mystudio.wtt.entity;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.engine.geom.CollisionBox;

public abstract class Wall{
      public Sprite sprite;
      public CollisionBox collisionBox;

      public abstract CollisionBox collisionBox();
      public abstract void update(float delta);
      public abstract void interpolate(float alpha);
      public abstract void render(Graphics g);
}