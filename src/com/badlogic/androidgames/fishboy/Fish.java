package com.badlogic.androidgames.fishboy;

import java.util.Random;

import android.graphics.Rect;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.math.Vector2;

public class Fish {
	public Vector2 position;
	public boolean isDead;
	public boolean isCaptured;
	public Rect bounds;
	private Random rand;
	
	public Fish(Vector2 position) {
		this.position = position;
		this.isDead = false;
		this.isCaptured = false;
		this.rand = new Random();
		this.bounds = new Rect();
	}

	public void update(float deltaTime){
		this.position.add(new Vector2(0, -8.0f * deltaTime));
		
		if (this.position.y == 90){
			this.isDead = true;
		}
		
		if (isDead){
			Assets.dead.play(1);
		}	
	}

	public void reboot(Game game) {
		// TODO Auto-generated method stub
		this.position = new Vector2( 
				rand.nextInt(game.getGraphics().getWidth() - Assets.fish.getWidth() + 1) + Assets.fish.getWidth(),
	            game.getGraphics().getHeight()
	    );
	}

	public boolean hit(Rect playerBounds) {
		if (this.bounds.intersect(playerBounds)){
			this.isCaptured = true;
			return true;
		}
		return false;
	}
	
	
	
}
