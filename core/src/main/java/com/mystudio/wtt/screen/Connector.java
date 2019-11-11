package com.mystudio.wtt.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.element.TextBox;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;
import org.mini2Dx.ui.style.UiTheme;
import com.mystudio.wtt.WhatTheTank;

public class Connector extends BasicGameScreen{
      private AssetManager assetManager;
      private UiContainer uiContainer;
      public final static int ID = 2;
      private Visibility v = Visibility.VISIBLE;
      private Visibility h = Visibility.HIDDEN;
      private int screenToLoad = 0;

      @Override
      public void initialise(GameContainer gc){
            FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());
            assetManager = new AssetManager(fileHandleResolver);
            assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));
            assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);
            uiContainer = new UiContainer(gc, assetManager);
            TextBox a = new TextBox(0,0,400,100);
            TextBox b = new TextBox(0,200,400,100);
            uiContainer.add(a);
            uiContainer.add(b);
            a.setVisibility(v);
            b.setVisibility(v);
            a.addActionListener(new ActionListener(){
                  @Override
                  public void onActionEnd(ActionEvent event){
                        System.out.println(a.getValue());  
                  }
                  
                  @Override
                  public void onActionBegin(ActionEvent event){
                        
                  }
            });
            b.addActionListener(new ActionListener(){
                  @Override
                  public void onActionEnd(ActionEvent event){
                        System.out.println(b.getValue());  
                        screenToLoad = WhatTheTank.ID;
                        b.setVisibility(h);
                        a.setVisibility(h);
                  }
                  
                  @Override
                  public void onActionBegin(ActionEvent event){
                        
                  }
            });
      }

      @Override
      public void update(GameContainer gc, ScreenManager<?> screenManager, float delta){
            if(!assetManager.update()){
                  return;
            }
            if(!UiContainer.isThemeApplied()){
                  UiContainer.setTheme(assetManager.get(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class));
            }
            uiContainer.update(delta);
            if (screenToLoad != 0){
                  screenManager.enterGameScreen(screenToLoad, new NullTransition(), new NullTransition());
                  screenToLoad = 0;
            }
            Gdx.input.setInputProcessor(uiContainer);
      }

      @Override
      public void interpolate(GameContainer gc, float alpha){
            uiContainer.interpolate(alpha);
      }

      @Override
      public void render(GameContainer gc, Graphics g){
            uiContainer.render(g);
      }

      @Override
      public int getId(){
            return ID;
      }
}