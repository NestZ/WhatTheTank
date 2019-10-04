package com.mystudio.wtt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class WhatTheTank extends BasicGame {
  public static final String GAME_IDENTIFIER = "com.mystudio.wtt";
  InputHandler inputHandler;

  private Texture texture;
  private Tank t;
  private Wall b;
	
	  @Override
    public void initialise() {
      texture = new Texture("mini2Dx.png");
      t = new Tank("c", 0f, 0f, 1);
      b = new Brick();
      inputHandler = new InputHandler(t);
      Gdx.input.setInputProcessor(inputHandler);
    }
    
    @Override
    public void update(float delta){
      t.update();
      b.update();
    }
    
    @Override
    public void interpolate(float alpha){
      t.interpolate(alpha);
      b.interpolate(alpha);
    }
    
    @Override
    public void render(Graphics g){
      g.drawTexture(texture, 100f, 100f);
      t.render(g);
      b.render(g);
    }
}
