package com.mystudio.wtt.screen;

import org.mini2Dx.core.game.ScreenBasedGame;
import com.mystudio.wtt.WhatTheTank;

public class GameScreenManager extends ScreenBasedGame{
    @Override
    public void initialise(){
        addScreen(new MainMenu());
        addScreen(new EnterName());
        addScreen(new SelectRole());
        addScreen(new Connector());
        addScreen(new Lobby());
        addScreen(new WhatTheTank());
    }

    @Override
    public int getInitialScreenId(){
        return MainMenu.ID;
    }
}