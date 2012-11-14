package com.fuchsiateam.fishboy;

import com.fuchsiateam.framework.Game;
import com.fuchsiateam.framework.Graphics;
import com.fuchsiateam.framework.Graphics.PixmapFormat;
import com.fuchsiateam.framework.Screen;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.background = g.newPixmap("imgs/background.png", PixmapFormat.RGB565);
		Assets.fishboy = g.newPixmap("imgs/fishboy.png", PixmapFormat.RGB565);
		Assets.fish = g.newPixmap("imgs/fish.png", PixmapFormat.RGB565);
		Assets.hit = game.getAudio().newSound("hit.wav");
		Assets.dead = game.getAudio().newSound("dead.wav");
		Settings.load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
