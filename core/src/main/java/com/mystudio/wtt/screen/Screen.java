package com.mystudio.wtt.screen;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.screen.transition.NullTransition;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.style.UiTheme;

public class Screen extends BasicGameScreen{
    protected AssetManager assetManager;
    protected UiContainer uiContainer;
    protected int screenToLoad = 0;

    public void assetLoad(GameContainer gc){
        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());
        this.assetManager = new AssetManager(fileHandleResolver);
        this.assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));
        this.assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);
        this.uiContainer = new UiContainer(gc, this.assetManager);
    }

    @Override
    public void initialise(GameContainer gc){

    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta){
        if(!this.assetManager.update()){
            return;
      }
      if(!UiContainer.isThemeApplied()){
            UiContainer.setTheme(this.assetManager.get(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class));
      }
      this.uiContainer.update(delta);
      if (this.screenToLoad != 0){
            screenManager.enterGameScreen(this.screenToLoad, new NullTransition(), new NullTransition());
            this.screenToLoad = 0;
      }
      Gdx.input.setInputProcessor(this.uiContainer);
    }

    @Override
    public void interpolate(GameContainer gc, float alpha){
        this.uiContainer.interpolate(alpha);
    }

    @Override
    public void render(GameContainer gc, Graphics g){
        this.uiContainer.render(g);
    }

    @Override
    public int getId(){
        return 0;
    }
}