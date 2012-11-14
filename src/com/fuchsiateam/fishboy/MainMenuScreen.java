package com.fuchsiateam.fishboy;

import java.util.List;

import com.fuchsiateam.framework.Game;
import com.fuchsiateam.framework.Graphics;
import com.fuchsiateam.framework.Input.TouchEvent;
import com.fuchsiateam.framework.Screen;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
					Settings.soundEnabled = !Settings.soundEnabled;

				}
				if(inBounds(event, 64, 220, 192, 42) ) {
					game.setScreen(new GameScreen(game));
					return;
				}
				if(inBounds(event, 64, 220 + 42, 192, 42) ) {
					game.setScreen(new HelpScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.logo, 32, 20);
		g.drawPixmap(Assets.mainMenu, 64, 220);
		if(Settings.soundEnabled)
			g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
		else
			g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if(event.x > x && event.x < x + width - 1 && 
				event.y > y && event.y < y + height - 1)
			return true;
		else
			return false;
	}

}
