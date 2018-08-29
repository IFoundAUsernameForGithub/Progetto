package com.pet.progetto.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pet.progetto.Progetto;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.State;

public class TransitionState extends State {
	
	public enum Transition {
		BLACK_FADE,
		RECTANGLE, 
		LOST;
	}
	
	private State prev;
	private State next;
	private Transition type;
	
	private TextureRegion dark;
	
	private float maxTime;
	private float timer;
	private float alpha;
	private float width;
	private float height;
	private float tempX;
	private float tempY;
	private float rotation;
	

	protected TransitionState(GSM gsm, State prev, State next, Transition type) {
		super(gsm);
		
		this.prev = prev;
		this.next = next;
		this.type = type;
		
		dark = Progetto.res.getAtlas("pack4").findRegion("Bianco");
		
		if(type == Transition.BLACK_FADE){
			maxTime = 1;
			
		}
		else if(type == Transition.RECTANGLE){
			maxTime = 1;
			width = 1;
			height = 1;
			tempX = Progetto.WIDTH/2;
			tempY = Progetto.HEIGHT/2;
			
		}
		else if(type == Transition.LOST){
			maxTime = 1;
			
		}
		
		
	}

	public void handleInput() {
		
		
	}


	public void update(float dt) {
		timer += dt;
		
		if(type == Transition.BLACK_FADE) {
			if(timer >= maxTime) {
				gsm.set(next);
			}
		}
		else if (type == Transition.RECTANGLE){
			width = Progetto.WIDTH*alpha;
			height = Progetto.HEIGHT*alpha;
			tempX = Progetto.WIDTH/2 - (Progetto.WIDTH/2)*alpha;
			tempY = Progetto.HEIGHT/2 - (Progetto.HEIGHT/2)*alpha;
			rotation = 180*alpha;
			if(timer >= maxTime) {
				gsm.set(next);
			}
		}
		else if(type == Transition.LOST) {
			if(timer >= maxTime) {
				gsm.set(next);
			}
		}
		
	}


	public void render(SpriteBatch sb) {
		if(type == Transition.BLACK_FADE) {
			if(timer <= maxTime / 2) {
				alpha = timer / (maxTime / 2);
				prev.render(sb);
			}
			else {
				alpha = (1 + maxTime)-(timer / (maxTime / 2));
				//progetto.setbackground(true);
				next.render(sb);
			}

			if(alpha > 1){alpha = 1;}
			if(alpha < 0){alpha = 0;}
			
			sb.setColor(0, 0, 0, alpha);
			sb.setProjectionMatrix(cam.combined);
			sb.begin();
			sb.draw(dark, 0, 0, Progetto.WIDTH, Progetto.HEIGHT);
			sb.end();
			sb.setColor(1, 1, 1, 1);
		}
		else if(type == Transition.RECTANGLE){
			if(timer <= maxTime / 2) {
				alpha = timer / (maxTime / 2);
				prev.render(sb);
			}
			else {
				alpha = (1 + maxTime)-(timer / (maxTime / 2));
				//progetto.setbackground(false);
				next.render(sb);
			}
			
			if(alpha > 1){alpha = 1;}
			if(alpha < 0){alpha = 0;}
			
			sb.setColor(0, 0, 0, 1);
			sb.setProjectionMatrix(cam.combined);
			sb.begin();
			sb.draw(dark, tempX, tempY, Progetto.WIDTH/2, Progetto.HEIGHT/2, width, height, 1, 1, rotation);
			sb.end();
			sb.setColor(1, 1, 1, 1);
		}
		if(type == Transition.LOST) {
			if(timer <= maxTime / 2) {
				alpha = timer / (maxTime / 2);
				prev.render(sb);
			}
			else {
				alpha = (1 + maxTime)-(timer / (maxTime / 2));
				//progetto.setbackground(true);
				next.render(sb);
			}
			
			if(alpha > 1){alpha = 1;}
			if(alpha < 0){alpha = 0;}
			
			sb.setColor(0, 0, 0, alpha);
			sb.setProjectionMatrix(cam.combined);
			sb.begin();
			sb.draw(dark, 0, 0, Progetto.WIDTH, Progetto.HEIGHT);
			sb.end();
			sb.setColor(1, 1, 1, 1);
		}
		
	}

}
