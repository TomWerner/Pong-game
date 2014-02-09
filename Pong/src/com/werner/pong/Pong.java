package com.werner.pong;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;

/**
 * The game's main class, called as application events are fired.
 */
public class Pong extends Game
{
	// constant useful for logging
	public static final String LOG = Pong.class.getSimpleName();

	// whether we are in development mode
	public static final boolean DEV_MODE = false;

	// a libgdx helper class that logs the current FPS each second
	private FPSLogger fpsLogger;

	// services
	private PreferencesManager preferencesManager;
	private LevelManager levelManager;
	private MusicManager musicManager;

	private int width;

	private int height;

	public Pong()
	{
	}

	// Services' getters

	public PreferencesManager getPreferencesManager()
	{
		return preferencesManager;
	}

	public LevelManager getLevelManager()
	{
		return levelManager;
	}

	public MusicManager getMusicManager()
	{
		return musicManager;
	}

	// Game-related methods

	@Override
	public void create()
	{
		Gdx.app.log(Pong.LOG, "Creating game on " + Gdx.app.getType());

		// create the preferences manager
		preferencesManager = new PreferencesManager();

		// create the music manager
		musicManager = new MusicManager();
		musicManager.setVolume(preferencesManager.getVolume());
		musicManager.setEnabled(preferencesManager.isMusicEnabled());

		// create the level manager
		levelManager = new LevelManager();

		// create the helper objects
		fpsLogger = new FPSLogger();
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		this.width = width;
		this.height = height;
		Gdx.app.log(Pong.LOG, "Resizing game to: " + width + " x " + height);

		// show the splash screen when the game is resized for the first time;
		// this approach avoids calling the screen's resize method repeatedly
		if (getScreen() == null)
		{
			if (DEV_MODE)
			{
				setScreen(new GameScreen(this, 1));
			}
			else
			{
				setScreen(new SplashScreen(this));
			}
		}
	}

	@Override
	public void render()
	{
		super.render();

		// output the current FPS
		if (DEV_MODE)
			fpsLogger.log();
	}

	@Override
	public void pause()
	{
		super.pause();
		Gdx.app.log(Pong.LOG, "Pausing game");
	}

	@Override
	public void resume()
	{
		super.resume();
		Gdx.app.log(Pong.LOG, "Resuming game");
	}

	@Override
	public void setScreen(Screen screen)
	{
		super.setScreen(screen);
		Gdx.app.log(Pong.LOG, "Setting screen: "
				+ screen.getClass().getSimpleName());
	}

	@Override
	public void dispose()
	{
		super.dispose();
		Gdx.app.log(Pong.LOG, "Disposing game");

		// dipose some services
		musicManager.dispose();
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
}
