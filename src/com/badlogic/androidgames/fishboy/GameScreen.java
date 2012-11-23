package com.badlogic.androidgames.fishboy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.math.Vector2;

public class GameScreen extends Screen {
    enum GameState {
        Running,
        GameOver
    }
    public static int TOTAL_FISHES = 100;
    Random rand;
    GameState state = GameState.Running;
    //World world;
    int oldScore = 0;
    public int lifes = 4;
    String score = "0";
    Boy boy;
    Vector2 cloud1Pos;
    Vector2 cloud2Pos;
    float cloud1Vel = 5.0f;
    float cloud2Vel = 10.0f;
    List<Fish> fishes;
    
    
    public GameScreen(Game game) {
        super(game);
        rand = new Random();
        boy = new Boy(new Vector2(130, 90));
        //createFishes();
        if (Settings.soundEnabled){
        	Assets.menu.stop();
        	Assets.theme.play();
        	Assets.theme.setLooping(true);
        }
        
        cloud1Pos = new Vector2(game.getGraphics().getWidth(), 20);
        cloud2Pos = new Vector2(game.getGraphics().getWidth(), 30);
        fishes = new ArrayList<Fish>();
        createFishes();
    }
    
    
    private void createFishes() {
        //cria peixes
        for (int i = 0; i <= TOTAL_FISHES; ++i) {
            Vector2 pos = new Vector2( 
            		rand.nextInt(game.getGraphics().getWidth() - Assets.fish.getWidth() + 1) + Assets.fish.getWidth(),
            		game.getGraphics().getHeight()
            	);
            this.fishes.add(new Fish(pos));
        }
	}
	

	@Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.GameOver)
        	game.setScreen(new GameOverScreen(game));
    }
    
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_DOWN) {
            	boy.stop = false;
            	if(event.x <= game.getGraphics().getWidth() / 2 ) {
                	boy.moveLeft = true;
                }
                if(event.x > game.getGraphics().getWidth() / 2) {
                    boy.moveLeft = false;
                }
            }
            
            if(event.type == TouchEvent.TOUCH_UP) {
            	boy.stop = true;
            }
        }
        boy.update(deltaTime);
        
        for (int i = 0; i < 5; i++)
        {
            fishes.get(i).update(deltaTime);
            if (fishes.get(i).isDead)
            {
                lifes--;
                fishes.get(i).reboot(game);
            }else if (fishes.get(i).hit(boy.getBounds()))
            {
                score += 10;
                fishes.get(i).reboot(game);
            }
        }
        
        //world.update(deltaTime);
        if(lifes == 0) {
            state = GameState.GameOver;
        }
        
        //atualiza nuvens
        Vector2 vel1 = new Vector2(-deltaTime * cloud1Vel, 0);
        Vector2 vel2 = new Vector2(-deltaTime * cloud2Vel, 0);
        cloud1Pos.add(vel1);
        cloud1Pos.add(vel2);
        
        if (cloud1Pos.x < -Assets.cloud1.getWidth()){
        	cloud1Pos.x = (float) game.getGraphics().getWidth();
        }
        
        if (cloud2Pos.x < -Assets.cloud2.getWidth()){
        	cloud2Pos.x = (float) game.getGraphics().getWidth();
        }
    }
    

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        drawWorld();
        if(state == GameState.Running){
        	drawRunningUI();
        	drawFishes();
        }
            
        drawText(g, score, 20 - score.length()*20 / 2, g.getHeight() - 42);  
    }
    
    private void drawFishes() {
    	Graphics g = game.getGraphics();
		for (int i = 0; i < fishes.size() ; i++){
			g.drawPixmap(Assets.fish, (int) fishes.get(i).position.x, (int) fishes.get(i).position.y);
		}
	}


	private void drawWorld() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.fishBoy,  (int) boy.position.x, (int) boy.position.y);
        g.drawPixmap(Assets.fish, 270, (int) 270);
        
        //clouds
        g.drawPixmap(Assets.cloud1, (int) cloud1Pos.x, (int) cloud1Pos.y);
        g.drawPixmap(Assets.cloud2, (int) cloud1Pos.x, (int) cloud1Pos.y);
        
        //debug
      	Paint paint = new Paint();
        paint.setColor(Color.rgb(0, 0,255));
        paint.setStyle(Style.STROKE);
        game.getGraphics().drawRect(boy.getBounds(), paint);
    }
   
    
    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        Vector2 lifePos = new Vector2(g.getWidth()/2 - 50,20);
        for (int i=0 ; i < lifes; i++ ){
        	g.drawPixmap(Assets.heart,(int) lifePos.x, (int) lifePos.y);
        	lifePos.x += (float) Assets.heart.getWidth();
        }
    }

    
    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }
    
    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
    	Assets.theme.stop();
    	
    }
}