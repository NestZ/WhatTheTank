package com.mystudio.wtt.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;
import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;
import com.mystudio.wtt.screen.GameScreenManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig("com.mystudio.wtt");
		config.vSyncEnabled = true;
		config.height = 1080;
		config.width = 1920;
		config.fullscreen = true;
		//config.pauseWhenBackground = false;
		//config.pauseWhenMinimized = false;
		//config.resizable = false;
		new DesktopMini2DxGame(new GameScreenManager(), config);
	}
}
