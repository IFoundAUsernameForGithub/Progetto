package com.pet.progetto.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pet.progetto.Progetto;
import com.pet.progetto.states.TransitionState.Transition;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Graphic;
import com.pet.progetto.ui.State;

public class LogoState extends State {

	private Graphic Logo;
	private float timer = 0;
	private float maxTime = 2;
	private float alpha = 0;
	
	public LogoState(GSM gsm) {
		super(gsm);
		
		Logo = new Graphic(Progetto.res.getAtlas("pack4").findRegion("Intro"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		
		
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void update(float dt) {
		timer += dt;
		
		if(timer > maxTime){
			timer = maxTime;
			gsm.set(new TransitionState(gsm, this, new MedallionState(gsm), Transition.BLACK_FADE));
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		
		if(timer <= maxTime / 2) {
			alpha = timer / (maxTime / 2);
		}
		else if(timer > maxTime / 2){
			alpha = 1;
		}
		
		if(alpha > 1){alpha = 1;}
		if(alpha < 0){alpha = 0;}
		
		
		sb.setColor(1, 1, 1, alpha);
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		Logo.render(sb);
		sb.end();
		sb.setColor(1, 1, 1, 1);
	}

}
