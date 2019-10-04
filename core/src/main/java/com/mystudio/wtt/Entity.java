package com.mystudio.wtt;

import org.mini2Dx.core.graphics.Graphics;

public abstract class Entity{
      abstract void update();
      abstract void interpolate(float alpha);
      abstract void render(Graphics g);
}