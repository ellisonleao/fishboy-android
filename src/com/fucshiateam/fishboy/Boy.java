package com.fucshiateam.fishboy;

import android.graphics.Rect;

import com.fucshiateam.framework.math.Vector2;

public class Boy {
	private float vel = 60.0f;
	public Vector2 position;
	public boolean moveLeft;
	public boolean stop;
	
	public Boy(Vector2 position) {
		this.position = position;
		this.stop = true;
	}
	
	public void update(float deltaTime){
		if (!this.stop){
			if (this.moveLeft){
				this.position.add(-this.vel * deltaTime, 0);
			}else{
				this.position.add(this.vel * deltaTime, 0);
			}
		}
	}
	
	public Rect getBounds(){
		return new Rect((int) this.position.x , (int) this.position.y,
				(int) this.position.x + Assets.fishBoy.getWidth(), 
				(int) this.position.y + Assets.fishBoy.getHeight());
	}

	public void stop() {
		// TODO Auto-generated method stub
		this.position.add(0,0);
	}
}
