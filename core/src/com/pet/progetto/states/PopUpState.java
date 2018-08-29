package com.pet.progetto.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pet.progetto.Progetto;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Graphic;
import com.pet.progetto.ui.State;

public class PopUpState extends State{
	
	
	private boolean stayInState = true;
	private float timerInState = 0;
	
	private Graphic blackBackground;
	private float alphaInState = 1;
	
	
	State prev;
	
	
	protected PopUpState(GSM gsm, State prev) {
		super(gsm);
		this.prev = prev;
		
		blackBackground = new Graphic(Progetto.res.getAtlas("pack4").findRegion("Bianco"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				10,
				10);
		
		
		
	}

	@Override
	public void handleInput() {
			if(Gdx.input.isTouched()){
				
				mouse.x = Gdx.input.getX();
				mouse.y = Gdx.input.getY();
				
				gsm.getVp().unproject(mouse);
				
				if(mouse.x > Progetto.WIDTH){mouse.x = Progetto.WIDTH + 1;}
				if(mouse.x < 0){mouse.x = -1;}
				if(mouse.y > Progetto.HEIGHT){mouse.y = Progetto.HEIGHT + 1;}
				if(mouse.y < 0){mouse.y = -1;}
				
				if(mouse.x < 100 && mouse.y < 100){
					stayInState = false;
				}
				
				
			}
		
	}

	@Override
	public void update(float dt) {
		
		handleInput();
		
		if(stayInState){
			timerInState+=dt;
			if(timerInState > 0.5f){
				timerInState = 0.5f;
			}
		}
		else{
			timerInState-=dt;
			if(timerInState < 0){
				timerInState = 0;
				gsm.pop();
			}
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		if(timerInState <= 0.5f){
			alphaInState = timerInState;
		}
		else{
			alphaInState = 0.5f;
		}
		prev.render(sb);
		sb.setColor(0, 0, 0, alphaInState);
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		blackBackground.render(sb);
		sb.end();
		sb.setColor(1, 1, 1, 1);
		
	}

}
