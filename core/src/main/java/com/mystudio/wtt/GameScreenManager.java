package com.mystudio.wtt;

import org.mini2Dx.core.game.ScreenBasedGame;

public class GameScreenManager extends ScreenBasedGame {
    @Override
    public void initialise(){
        addScreen(new MainMenu());
        addScreen(new Lobby());
        addScreen(new WhatTheTank());
    }

    @Override
    public int getInitialScreenId(){
        return MainMenu.ID;
    }
}