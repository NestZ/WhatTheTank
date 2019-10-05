package com.mystudio.wtt;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class WhatTheTank extends BasicGame {
  public static final String GAME_IDENTIFIER = "com.mystudio.wtt";
  private Texture texture;
  private Field field;
	
	  @Override
    public void initialise() {
      texture = new Texture("mini2Dx.png");
      field = new Field();
    }
    
    @Override
    public void update(float delta){
      field.update(delta);
    }
    
    @Override
    public void interpolate(float alpha){
      field.interpolate(alpha);
    }
    
    @Override
    public void render(Graphics g){
      g.drawTexture(texture, 100f, 100f);
      field.render(g);
    }
}
