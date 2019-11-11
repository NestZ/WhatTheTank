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

/**
 * Screen
 */
public class Screen extends BasicGameScreen {
    protected AssetManager assetManager;
    protected UiContainer uiContainer;
    protected int screenToLoad = 0;

    public void newname(GameContainer gc) {
        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());
        assetManager = new AssetManager(fileHandleResolver);
        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));
        assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);
        uiContainer = new UiContainer(gc, assetManager);
    }

    @Override
    public void initialise(GameContainer gc) {

    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
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
    public void interpolate(GameContainer gc, float alpha) {
        uiContainer.interpolate(alpha);

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        uiContainer.render(g);

    }

    @Override
    public int getId() {
        return 0;
    }

    
}