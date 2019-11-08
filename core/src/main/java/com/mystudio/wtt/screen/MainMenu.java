package com.mystudio.wtt.screen;

import java.util.Objects;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;
import org.mini2Dx.core.serialization.SerializationException;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.element.Container;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;
import org.mini2Dx.ui.style.UiTheme;

public class MainMenu extends BasicGameScreen {
      private AssetManager assetManager;
      private UiContainer uiContainer;
      private int screenToLoad = 0;
      public final static int ID = 1;
      public static final String UI_MAINMENU_LAYOUT_XML = "MainMenu.xml";
      private ScreenManager<?> screenManager;

      @Override
      public void initialise(GameContainer gc){
            FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());
            assetManager = new AssetManager(fileHandleResolver);
            assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));
            assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);
            uiContainer = new UiContainer(gc, assetManager);
            Container mainMenuContainer = null;
            try{
                  mainMenuContainer = Mdx.xml.fromXml(Gdx.files.internal(UI_MAINMENU_LAYOUT_XML).reader(), Container.class);
            }
            catch(SerializationException e){  
                  e.printStackTrace();
            }
            Objects.requireNonNull(mainMenuContainer); 
            TextButton newGameButton = (TextButton) mainMenuContainer.getElementById("newGameButton");
            TextButton quitButton = (TextButton) mainMenuContainer.getElementById("quitButton");
            mainMenuContainer.shrinkToContents(true); 
            mainMenuContainer.setXY((mainMenuContainer.getWidth()) / 2, (mainMenuContainer.getHeight()) / 2);
            uiContainer.add(mainMenuContainer);
            newGameButton.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        screenToLoad = Connector.ID;
                  }
            });
            quitButton.addActionListener(new ActionListener(){
                  @Override
                  public void onActionBegin(ActionEvent event){

                  }

                  @Override
                  public void onActionEnd(ActionEvent event){
                        System.exit(0);
                  }
            });
      }

      @Override
      public void update(GameContainer gc, ScreenManager<?> screenManager, float delta){
            this.screenManager = screenManager;
            if(!assetManager.update()){
                  return;
            }
            if(!UiContainer.isThemeApplied()){
                  UiContainer.setTheme(assetManager.get(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class));
            }
            uiContainer.update(delta);
            if(screenToLoad != 0){
                  this.screenManager.enterGameScreen(screenToLoad, new NullTransition(), new NullTransition());
                  screenToLoad = 0;
            }
            Gdx.input.setInputProcessor(uiContainer);
      }
    
      @Override
      public void render(GameContainer gc, Graphics g){
            uiContainer.render(g);
      }

      @Override
      public void interpolate(GameContainer gc, float alpha){

      }

      @Override
      public int getId(){
            return ID;
      }
}