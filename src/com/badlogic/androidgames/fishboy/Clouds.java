package com.badlogic.androidgames.fishboy;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.math.Vector2;

public class Clouds {
    private Vector2 cloud1Pos;
    private Vector2 cloud2Pos;
    private float cloud1Vel = 5.0f;
    private float cloud2Vel = 10.0f;
    private Game game;
    
    public Clouds(Game game) {
        this.cloud1Pos = new Vector2(game.getGraphics().getWidth(), 20);
        this.cloud2Pos = new Vector2(game.getGraphics().getWidth(), 30);
        this.game = game;
    }
    
    public void update(float deltaTime){
        Vector2 vel1 = new Vector2(-deltaTime * cloud1Vel, 0);
        Vector2 vel2 = new Vector2(-deltaTime * cloud2Vel, 0);
        cloud1Pos.add(vel1);
        cloud1Pos.add(vel2);
        
        if (cloud1Pos.x < -Assets.cloud1.getWidth()){
        	cloud1Pos.x = (float) this.game.getGraphics().getWidth();
        }
        
        if (cloud2Pos.x < -Assets.cloud2.getWidth()){
        	cloud2Pos.x = (float) this.game.getGraphics().getWidth();
        }
    }

	public void drawClouds() {
        this.game.getGraphics().drawPixmap(Assets.cloud1, (int) cloud1Pos.x, (int) cloud1Pos.y);
        this.game.getGraphics().drawPixmap(Assets.cloud2, (int) cloud1Pos.x, (int) cloud1Pos.y);
	}
}
