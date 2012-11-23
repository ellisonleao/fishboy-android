package com.badlogic.androidgames.fishboy;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        Assets.mainMenuBg = g.newPixmap("menu.png", PixmapFormat.ARGB4444);
        Assets.fishBoy = g.newPixmap("fishboy.png", PixmapFormat.ARGB4444);
        Assets.fish = g.newPixmap("fish.png", PixmapFormat.ARGB4444);
        Assets.heart = g.newPixmap("heart.png", PixmapFormat.ARGB4444);
        Assets.cloud1 = g.newPixmap("cloud1.png", PixmapFormat.ARGB4444);
        Assets.cloud2 = g.newPixmap("cloud2.png", PixmapFormat.ARGB4444);
        Assets.help = g.newPixmap("help.png", PixmapFormat.ARGB4444);
        
        Assets.theme = game.getAudio().newMusic("theme.wav");
        Assets.menu = game.getAudio().newMusic("menu.wav");
        Assets.dead = game.getAudio().newSound("dead.wav");
        
        
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        Assets.gameOverBg = g.newPixmap("bggameover.png", PixmapFormat.ARGB4444);
        Assets.click = game.getAudio().newSound("click.ogg");
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}