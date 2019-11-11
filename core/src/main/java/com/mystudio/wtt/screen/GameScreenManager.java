package com.mystudio.wtt.screen;

import org.mini2Dx.core.game.ScreenBasedGame;
import com.mystudio.wtt.WhatTheTank;

public class GameScreenManager extends ScreenBasedGame {
    @Override
    public void initialise(){
        addScreen(new MainMenu());
        addScreen(new Lobby());
        addScreen(new WhatTheTank());
        addScreen(new Lobby1());
    }

    @Override
    public int getInitialScreenId(){
        return MainMenu.ID ;
    }
}