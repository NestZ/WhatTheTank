package com.mystudio.wtt;

import java.io.IOException;
import java.util.HashMap;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import com.mystudio.wtt.entity.Map;
import com.mystudio.wtt.entity.tank.Tank;
import com.mystudio.wtt.screen.Field;

public class WhatTheTank extends BasicGameScreen{
  public final static int ID = 5;
  public static int clientID;
  public static HashMap<Integer, Tank> tanks;
  private static Field field;

  public static void initField(Map map){
    try{
      field = new Field(WhatTheTank.tanks, map);
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
  
  @Override
  public void initialise(GameContainer gc){

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
    field.render(g);
  }

  @Override
  public int getId(){
    return ID;
  }
}