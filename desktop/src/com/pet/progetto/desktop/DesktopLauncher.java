package com.pet.progetto.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pet.progetto.Progetto;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = Progetto.WIDTH/2;
		config.height = Progetto.HEIGHT/2;
		config.title = Progetto.TITLE;
		
		new LwjglApplication(new Progetto(), config);
	}
}
