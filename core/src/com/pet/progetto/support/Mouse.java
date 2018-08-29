package com.pet.progetto.support;

import com.badlogic.gdx.math.Vector3;

public class Mouse{
	
	private Vector3 mouse;
	private boolean isClicked;
	
	public Mouse(Vector3 mouse, boolean isClicked){
		this.mouse = mouse;
		this.isClicked = isClicked;
	}

	public Vector3 getMouse() {
		return mouse;
	}

	public void setMouse(Vector3 mouse) {
		this.mouse = mouse;
	}

	public boolean getIsClicked() {
		return isClicked;
	}

	public void setIsClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
	
}
