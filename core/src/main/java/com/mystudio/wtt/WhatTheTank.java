package com.mystudio.wtt;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import com.mystudio.wtt.screen.Field;

public class WhatTheTank extends BasicGameScreen{
  public static final String GAME_IDENTIFIER = "com.mystudio.wtt";
  public final static int ID = 4;
  private Texture texture;
  private Field field;
  
  @Override
  public void initialise(GameContainer gc){
    texture = new Texture("mini2Dx.png");
    //field = new Field();
  }

  @Override
  public void update(GameContainer gc, ScreenManager<?> screenManager, float delta){
    field.update(delta);
  }

  @Override
  public void interpolate(GameContainer gc, float alpha){
    field.interpolate(alpha);
  }

  @Override
  public void render(GameContainer gc, Graphics g){
    g.drawTexture(texture, 100f, 100f);
    field.render(g);
  }

  @Override
  public int getId(){
    return ID;
  }
}