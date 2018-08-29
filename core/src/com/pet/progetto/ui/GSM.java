package com.pet.progetto.ui;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GSM {
	
	private Stack<State> states;
	private Viewport vp;
	
	public GSM(Viewport vp) {
		states = new Stack<State>();
		this.vp = vp;
	}
	
	public void push(State s) {
		states.push(s);
	}
	
	public void pop() {
		states.pop();
	}
	
	public void set(State s) {
		states.pop();
		states.push(s);
	}
	
	public void update(float dt) {
		states.peek().update(dt);
	}
	
	public void render(SpriteBatch sb) {
		states.peek().render(sb);
	}

	public Viewport getVp() {
		return vp;
	}
	
}
