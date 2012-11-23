package com.badlogic.androidgames.fishboy;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class MainMenuScreen extends Screen {
	private Clouds clouds;
	
    public MainMenuScreen(Game game) {
        super(game);
        clouds = new Clouds(game);
        if (Settings.soundEnabled){
        	Assets.menu.play();
            Assets.menu.setLooping(true);	
        }
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
                    if(Settings.soundEnabled){
                        Assets.click.play(1);
                    	Assets.menu.play();
                    	Assets.menu.setLooping(true);	
                	}else{
                    	Assets.menu.stop();
                    }
                }
                if(inBounds(event, 64, 135, 192, 42) ) {
                    game.setScreen(new GameScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                if(inBounds(event, 64, 135 + 42, 192, 42) ) {
                    game.setScreen(new HelpScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
        clouds.update(deltaTime);
    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        clouds.drawClouds();
        g.drawPixmap(Assets.logo, 140, 10);
        g.drawPixmap(Assets.mainMenuBg, 142, 74);
        g.drawPixmap(Assets.mainMenu, 145, 120);
        if(Settings.soundEnabled)
            g.drawPixmap(Assets.buttons, 0, 320 - 64, 0, 0, 64, 64);
        else
            g.drawPixmap(Assets.buttons, 0, 320 -64 , 64, 0, 64, 64);
        
    }

    @Override
    public void pause() {        

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    	Assets.menu.stop();
    }
}
