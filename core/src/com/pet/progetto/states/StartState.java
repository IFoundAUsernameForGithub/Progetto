package com.pet.progetto.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.pet.progetto.Progetto;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.State;

public class StartState extends State{

	private ShapeRenderer sr;
	private float r;
	private float g;
	private float b;
	private int color;
	
	private int sizewidth = 500;
	private int sizeheight = 100;
	
	private float timer = 0;
	
	public StartState(GSM gsm) {
		super(gsm);
		sr = new ShapeRenderer();
	}

	public void handleInput(){
		if(Gdx.input.justTouched()){
			gsm.set(new LogoState(gsm));
		}
	}
	public void update(float dt){
		handleInput();
		
		color++;
		if((int)(color/100)>=0 && (int)(color/100) < 1){
			r = 1;
			g = (((float)color)/100);
			b = 0;
		}
		else if((int)(color/100)>=1 && (int)(color/100) < 2){
			r = 2 - (((float)color)/100);
			g = 1;
			b = 0;
		}
		else if((int)(color/100)>=2 && (int)(color/100) < 3){
			r = 0;
			g = 1;
			b = (((float)color)/100) - 2;
		}
		else if((int)(color/100)>=3 && (int)(color/100) < 4){
			r = 0;
			g = 4 - (((float)color)/100);
			b = 1;
		}
		else if((int)(color/100)>=4 && (int)(color/100) < 5){
			r = (((float)color)/100) - 4;
			g = 0;
			b = 1;
		}
		else if((int)(color/100)>=5 && (int)(color/100) < 6){
			r = 1;
			g = 0;
			b = 6 - (((float)color)/100);
		}
		else if(color >= 600){
			color = 0;
		}
		
		timer+=dt;
		
		if(timer > 10){
			gsm.set(new LogoState(gsm));
		}
		
		
	}
	public void render(SpriteBatch sb){
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Filled);
		sr.setColor(r, g, b, 1);
		sr.rect(Progetto.WIDTH/2 - sizewidth/2, Progetto.HEIGHT/2 - sizeheight/2, sizewidth, sizeheight);
		sr.end();
	}

	
}
