package com.pet.progetto.support;

import com.pet.progetto.ui.Graphic;

public class GraTime {

	private Graphic graphic;
	private float timer;
	
	public GraTime(Graphic graphic, float timer){
		this.timer = timer;
		this.graphic = graphic;
	}

	public float getTimer() {
		return timer;
	}

	public void setTimer(float timer) {
		this.timer = timer;
	}

	public Graphic getGraphic() {
		return graphic;
	}

	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}
	
}
