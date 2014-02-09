package com.werner.pong;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Main
{
	public static void main(String[] args)
	{
		new LwjglApplication(new Pong(), "Pong", 800, 600, true);
	}
}
