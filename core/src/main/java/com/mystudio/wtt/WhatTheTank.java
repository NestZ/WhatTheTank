package com.mystudio.wtt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class WhatTheTank extends BasicGame {
  public static final String GAME_IDENTIFIER = "com.mystudio.wtt";

  private Texture texture;
  private Tank t;
	
	  @Override
    public void initialise() {
      texture = new Texture("mini2Dx.png");
      t = new Tank("c", 0f, 0f, 1);
    }
    
    @Override
    public void update(float delta){
      t.setPos(t.getX() + 4, t.getY() + 4);
      t.update();
    }
    
    @Override
    public void interpolate(float alpha){
      t.interpolate(alpha);
    }
    
    @Override
    public void render(Graphics g){
      g.drawTexture(texture, 100f, 100f);
      t.render(g);
    }
}
