package com.pet.progetto.support;

import com.pet.progetto.ui.Obstacle;

public class ObstaTime extends Obstacle{
	
	private float timer;
	
	public ObstaTime(Obstacle obs, float timer) {
		super(obs.getSize(),obs.getLine(),obs.getElement(),obs.getIsDouble());
		
		this.timer = timer;
		
	}

	public float getTimer() {
		return timer;
	}

	public void setTimer(float timer) {
		this.timer = timer;
	}

}
