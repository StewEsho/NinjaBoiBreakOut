package com.stewesho.ninjaboi.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stewesho.ninjaboi.NinjaBoiBreakOut;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Ninja Boi: Break Out";
		config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
        config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.fullscreen = true;
		config.resizable = false;
		config.forceExit = true;
		new LwjglApplication(new NinjaBoiBreakOut(), config);
	}
}
