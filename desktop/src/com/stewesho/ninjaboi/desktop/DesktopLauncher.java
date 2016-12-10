package com.stewesho.ninjaboi.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stewesho.ninjaboi.NinjaBoiBreakOut;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new NinjaBoiBreakOut(), "Ninja Boi: Break Out", 800, 450);
	}
}
