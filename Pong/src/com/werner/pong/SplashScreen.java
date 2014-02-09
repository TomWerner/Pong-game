package com.werner.pong;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

/**
 * Shows a splash image and moves on to the next screen.
 */
public class SplashScreen extends AbstractScreen
{
	private Image splashImage;
	private BackgroundMusic music;

	public SplashScreen(Pong game)
	{
		super(game);
		music = new BackgroundMusic("music/OutThere_0.ogg", "Menu Music");
	}

	@Override
	public void show()
	{
		super.show();

		// start playing the menu music
		game.getMusicManager().play(music);

		// // retrieve the splash image's region from the atlas
		// AtlasRegion splashRegion = getAtlas().findRegion(
		// "splash-screen/splash-image");
		// Drawable splashDrawable = new TextureRegionDrawable(splashRegion);
		TextureRegion t = new TextureRegion(new Texture(
				Gdx.files.internal("data/splashscreen.png")));
		Drawable splashDrawable = new TextureRegionDrawable(t);

		// here we create the splash image actor; its size is set when the
		// resize() method gets called
		splashImage = new Image(splashDrawable, Scaling.stretch);
		splashImage.setFillParent(true);

		// this is needed for the fade-in effect to work correctly; we're just
		// making the image completely transparent
		splashImage.getColor().a = 0f;

		// configure the fade-in/out effect on the splash image
		splashImage.addAction(sequence(fadeIn(1.5f), delay(2.75f),
				fadeOut(1.75f), new Action()
				{
					@Override
					public boolean act(float delta)
					{
						// the last action will move to the next screen
						 game.setScreen(new MenuScreen(game));
						return true;
					}
				}));

		// and finally we add the actor to the stage
		stage.addActor(splashImage);
	}
}