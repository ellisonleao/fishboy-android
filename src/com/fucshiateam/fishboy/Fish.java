package com.fucshiateam.fishboy;

import java.util.Random;

import android.graphics.Rect;

import com.fucshiateam.framework.Game;
import com.fucshiateam.framework.math.Vector2;

public class Fish {
	public Vector2 position;
	public boolean isDead;
	private Rect bounds;
	private Random rand;
	private float fishVel = -20.0f;
	public Fish(Vector2 position, Game game) {
		this.position = position;
		this.isDead = false;
		this.rand = new Random();
		this.bounds = new Rect();
	}

	public void update(float deltaTime){
		this.position.add(new Vector2(0, this.fishVel * deltaTime));
		this.bounds =  new Rect((int) this.position.x , (int) this.position.y,
				(int) this.position.x + Assets.fish.getWidth(), 
				(int) this.position.y + Assets.fish.getHeight());
		
		if (this.position.y < 190){
			this.isDead = true;
		}
		
		if (isDead){
			Assets.dead.play(1);
		}	
	}

	public void reboot(Game game) {
		int max = game.getGraphics().getWidth() - Assets.fish.getWidth();
		int min = Assets.fish.getWidth() + 5;
		this.position = new Vector2( 
				rand.nextInt( max - min + 1) + min,
	            game.getGraphics().getHeight()
	    );
		this.isDead = false;
	}

	public boolean hit(Rect playerBounds) {
		if (this.bounds.intersect(playerBounds)){
			return true;
		}
		return false;
	}
	
	public Rect getBounds(){
		return this.bounds;
	}
	
	
}
