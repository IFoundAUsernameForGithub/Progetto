package com.pet.progetto.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pet.progetto.Progetto;
import com.pet.progetto.states.TransitionState.Transition;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Graphic;
import com.pet.progetto.ui.State;

public class MedallionState extends State{

	private Graphic base;
	private Graphic earthOff;
	private Graphic waterOff;
	private Graphic fireOff;
	private Graphic airOff;
	private Graphic timeOff;
	
	private Graphic earthOn;
	private Graphic waterOn;
	private Graphic fireOn;
	private Graphic airOn;
	private Graphic timeOn;
	
	private Graphic earthTextOff;
	private Graphic waterTextOff;
	private Graphic fireTextOff;
	private Graphic airTextOff;
	private Graphic timeTextOff;
	
	private Graphic earthTextOn;
	private Graphic waterTextOn;
	private Graphic fireTextOn;
	private Graphic airTextOn;
	private Graphic timeTextOn;
	
	private ArrayList<Graphic> elementOn = new ArrayList<Graphic>();
	private ArrayList<Graphic> elementOff = new ArrayList<Graphic>();
	private ArrayList<Graphic> textOn = new ArrayList<Graphic>();
	private ArrayList<Graphic> textOff = new ArrayList<Graphic>();
	
	private float timer = 0;
	private float alpha = 0;
	
	private int levelArrived = 0;
	private final int maxLevel = 5;
	private final int increase = 8;
	private float mouseXStart;
	private float mouseYStart;
	private int times = 0;
	
	public MedallionState(GSM gsm) {
		super(gsm);
		
		base = new Graphic(Progetto.res.getAtlas("pack5").findRegion("Medaglione"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		earthOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloTerraSpento"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2 + base.getHeight()/4 + increase - 3,
				1,
				1);
		waterOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloAcquaSpento"),
				Progetto.WIDTH/2 + base.getWidth()/4 + increase + 2,
				Progetto.HEIGHT/2 - 3,
				1,
				1);
		fireOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloFuocoSpento"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2 - base.getHeight()/4 - increase - 3,
				1,
				1);
		airOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloAriaSpento"),
				Progetto.WIDTH/2 - base.getWidth()/4 - increase,
				Progetto.HEIGHT/2 - 7,
				1,
				1);
		timeOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloTempoSpento"),
				Progetto.WIDTH/2 - 8,
				Progetto.HEIGHT/2 + 7,
				1,
				1);
		
		elementOff.add(earthOff);
		elementOff.add(waterOff);
		elementOff.add(fireOff);
		elementOff.add(airOff);
		elementOff.add(timeOff);
		
		
		earthOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloTerraAcceso"),
				Progetto.WIDTH/2 - 3,
				Progetto.HEIGHT/2 + base.getHeight()/4 + increase,
				1,
				1);
		waterOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloAcquaAcceso"),
				Progetto.WIDTH/2 + base.getWidth()/4 + increase - 2,
				Progetto.HEIGHT/2 - 10,
				1,
				1);
		fireOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloFuocoAcceso"),
				Progetto.WIDTH/2 - 1,
				Progetto.HEIGHT/2 - base.getHeight()/4 - increase - 6,
				1,
				1);
		airOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloAriaAcceso"),
				Progetto.WIDTH/2 - base.getWidth()/4 - increase - 3,
				Progetto.HEIGHT/2 - 10,
				1,
				1);
		timeOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("SimboloTempoAcceso"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		
		elementOn.add(earthOn);
		elementOn.add(waterOn);
		elementOn.add(fireOn);
		elementOn.add(airOn);
		elementOn.add(timeOn);
		
		
		earthTextOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoTerraSpento"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		waterTextOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoAcquaSpento"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		fireTextOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoFuocoSpento"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		airTextOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoAriaSpento"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		timeTextOff = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoTempoSpento"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		
		textOff.add(earthTextOff);
		textOff.add(waterTextOff);
		textOff.add(fireTextOff);
		textOff.add(airTextOff);
		textOff.add(timeTextOff);
		
		earthTextOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoTerraAcceso"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		waterTextOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoAcquaAcceso"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		fireTextOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoFuocoAcceso"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		airTextOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoAriaAcceso"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		timeTextOn = new Graphic(Progetto.res.getAtlas("pack5").findRegion("TestoTempoAcceso"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		
		textOn.add(earthTextOn);
		textOn.add(waterTextOn);
		textOn.add(fireTextOn);
		textOn.add(airTextOn);
		textOn.add(timeTextOn);
		
		
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
			
			if(mouseXStart == -1 && mouseYStart == -1){
				mouseXStart = mouse.x;
				mouseYStart = mouse.y;
			}
			
			for(int i = 0; i < levelArrived + 1; i++){
				if(elementOn.get(i).contains(mouseXStart, mouseYStart, 1, 1) > 0){
					gsm.set(new TransitionState(gsm, this, new MapState(gsm,i), Transition.BLACK_FADE));
				}
			}
		}
		else{
			mouseXStart = -1;
			mouseYStart = -1;
		}
		
	}

	@Override
	public void update(float dt) {
		
		handleInput();
		
		timer+=dt;
		float maxTimer = 3.25f;
		
		if(timer >= maxTimer){
			timer = 0;
			times++;
			if(times > 2){
				times=0;
				levelArrived ++;
				if(levelArrived > 4){
					levelArrived = 0;
				}
			}
		}
		if(timer < 1f){
			alpha = timer;
		}
		else if(timer < 2){
			alpha = 1;
		}
		else if(timer < 3){
			alpha = 3 - timer;
		}
		else{
			alpha = 0;
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		base.render(sb);
		
		for(int i = 0; i < levelArrived; i++){
			textOff.get(i).render(sb);
			elementOn.get(i).render(sb);
		}
		if(levelArrived < maxLevel){
			if(timer > 1f || times > 0){
				textOff.get(levelArrived).render(sb);
			}
			sb.setColor(1, 1, 1, alpha);
			elementOn.get(levelArrived).render(sb);
			if(times < 1){
				textOn.get(levelArrived).render(sb);
			}
			sb.setColor(1, 1, 1, 1-alpha);
			elementOff.get(levelArrived).render(sb);
			sb.setColor(1, 1, 1, 1);
		}
		for(int i = levelArrived + 1; i < maxLevel; i++){
			elementOff.get(i).render(sb);
		}
		
		
		sb.end();
		sb.setColor(1, 1, 1, 1);
		
	}

}
