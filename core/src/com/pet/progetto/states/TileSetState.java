package com.pet.progetto.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pet.progetto.Progetto;
import com.pet.progetto.prefences.RuneStatic;
import com.pet.progetto.states.TransitionState.Transition;
import com.pet.progetto.support.Mouse;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Graphic;
import com.pet.progetto.ui.InputFile;
import com.pet.progetto.ui.Obstacle;
import com.pet.progetto.ui.State;
import com.pet.progetto.ui.Runes;

public class TileSetState extends State{

	private Graphic SfondoSotto;
	private Graphic SfondoCentro;
	
	private Runes Runa0;
	private Runes Runa1;
	private Runes Runa2;
	private Runes Runa3;
	private Runes Runa4;
	
	private Graphic Barradestrasopra;
	private Graphic Barradestrasotto;
	private Graphic Barrasinistrasopra;
	private Graphic Barrasinistrasotto;
	
	private ArrayList<ArrayList<Obstacle>> Obstacles = new ArrayList <ArrayList<Obstacle>>();
	private ArrayList<Obstacle> Obstacles0 = new ArrayList<Obstacle>();
	private ArrayList<Obstacle> Obstacles1 = new ArrayList<Obstacle>();
	private ArrayList<Obstacle> Obstacles2 = new ArrayList<Obstacle>();
	private ArrayList<Obstacle> Obstacles3 = new ArrayList<Obstacle>();
	private ArrayList<Obstacle> Obstacles4 = new ArrayList<Obstacle>();
	
	private ArrayList<Runes> Runes = new ArrayList<Runes>();
	private ArrayList<Mouse> Mouses = new ArrayList<Mouse>();
	private ArrayList<Graphic> Aura = new ArrayList<Graphic>();
	
	private float auraTimer[] = new float[5];
	private float RuneTimer[] = new float[5];
	
	private MapState prev;
	private float seconds = 0;
	
	public TileSetState(GSM gsm,MapState prev){
		super(gsm);
		this.prev = prev;
		
		for(int j = 0; j < 5; j++){
			RuneTimer[j]=0;
			auraTimer[j]=0;
		}
		
		SfondoSotto = new Graphic(Progetto.res.getAtlas("pack").findRegion("Backgroundterra"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT - 200,
				1,
				1);
		
		SfondoCentro = new Graphic(Progetto.res.getAtlas("pack").findRegion("Sfondoterra"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		
		Barradestrasopra = new Graphic(Progetto.res.getAtlas("pack").findRegion("Barravitasopraterra"),
				Progetto.WIDTH - 28,
				Progetto.HEIGHT/2 + 105,
				1,
				1);
		
		Barradestrasotto = new Graphic(Progetto.res.getAtlas("pack").findRegion("Barravitasottoterra"),
				Progetto.WIDTH - 15,
				Progetto.HEIGHT/2 + 67,
				1,
				1);
		
		Barrasinistrasopra = new Graphic(Progetto.res.getAtlas("pack").findRegion("Barrastaminasopraterra"),
				28,
				Progetto.HEIGHT/2 + 105,
				1,
				1);
		
		Barrasinistrasotto = new Graphic(Progetto.res.getAtlas("pack").findRegion("Barrastaminasottoterra"),
				25,
				Progetto.HEIGHT/2 + 95,
				1,
				1);
		
		//rune
		
		Runa0 = new Runes(RuneStatic.RuneInventario.get(0).intValue(), Progetto.WIDTH/10 + 10, Progetto.HEIGHT/4);
		Runa1 = new Runes(RuneStatic.RuneInventario.get(1).intValue(), Progetto.WIDTH*3/10 + 5, Progetto.HEIGHT/6);
		Runa2 = new Runes(RuneStatic.RuneInventario.get(2).intValue(), Progetto.WIDTH/2, Progetto.HEIGHT/12);
		Runa3 = new Runes(RuneStatic.RuneInventario.get(3).intValue(), Progetto.WIDTH*7/10 - 5, Progetto.HEIGHT/6);
		Runa4 = new Runes(RuneStatic.RuneInventario.get(4).intValue(), Progetto.WIDTH*9/10 - 10, Progetto.HEIGHT/4);
		
		Runes.add(0, Runa0);
		Runes.add(1, Runa1);
		Runes.add(2, Runa2);
		Runes.add(3, Runa3);
		Runes.add(4, Runa4);
		
		Obstacles.add(Obstacles0);
		Obstacles.add(Obstacles1);
		Obstacles.add(Obstacles2);
		Obstacles.add(Obstacles3);
		Obstacles.add(Obstacles4);
		
		for(int i = 0; i < Runes.size(); i++){
			Runes.get(i).TurnOn(1);
			if(Runes.get(i).getElement() == "Neutral"){
				Aura.add(new Graphic(Progetto.res.getAtlas("pack").findRegion("Aura"),
						Runes.get(i).getx(),
						Runes.get(i).gety(),
						1,
						1));
			}
			else if(Runes.get(i).getElement() == "Earth"){
				Aura.add(new Graphic(Progetto.res.getAtlas("pack").findRegion("AuraTerra"),
						Runes.get(i).getx(),
						Runes.get(i).gety(),
						1,
						1));
			}
			else if(Runes.get(i).getElement() == "Water"){
				Aura.add(new Graphic(Progetto.res.getAtlas("pack").findRegion("AuraAcqua"),
						Runes.get(i).getx(),
						Runes.get(i).gety(),
						1,
						1));
			}
			else if(Runes.get(i).getElement() == "Fire"){
				Aura.add(new Graphic(Progetto.res.getAtlas("pack").findRegion("AuraFuoco"),
						Runes.get(i).getx(),
						Runes.get(i).gety(),
						1,
						1));
			}
			else if(Runes.get(i).getElement() == "Air"){
				Aura.add(new Graphic(Progetto.res.getAtlas("pack").findRegion("AuraAria"),
						Runes.get(i).getx(),
						Runes.get(i).gety(),
						1,
						1));
			}
			else if(Runes.get(i).getElement() == "Time"){
				Aura.add(new Graphic(Progetto.res.getAtlas("pack").findRegion("AuraTempo"),
						Runes.get(i).getx(),
						Runes.get(i).gety(),
						1,
						1));
			}
			else{
				Aura.add(new Graphic(Progetto.res.getAtlas("pack").findRegion("Aura"),
						Runes.get(i).getx(),
						Runes.get(i).gety(),
						1,
						1));
			}
			Aura.get(i).setvisible(false);
		}
	}
	
	@Override
	public void handleInput() {
		for(int i = 0; i < 3; i++) {
			if(Gdx.input.isTouched(i)){
				mouse.x = Gdx.input.getX(i);
				mouse.y = Gdx.input.getY(i);
				
				gsm.getVp().unproject(mouse);
				
				if(mouse.x > Progetto.WIDTH){mouse.x = Progetto.WIDTH + 1;}
				if(mouse.x < 0){mouse.x = -1;}
				if(mouse.y > Progetto.HEIGHT){mouse.y = Progetto.HEIGHT + 1;}
				if(mouse.y < 0){mouse.y = -1;}
				
				if(Mouses.size() <= i){
					Mouse b = new Mouse(mouse,true);
					Mouses.add(i, b);
				}
				else {
					if(mouse.x < 0 || mouse.y < 0 || mouse.x > Progetto.WIDTH || mouse.y > Progetto.HEIGHT){
						Mouse b = new Mouse(new Vector3(-2,-2,0),true);
						Mouses.set(i, b);
					}
					else{
						Mouse b = new Mouse(new Vector3(mouse.x,mouse.y,0),Mouses.get(i).getIsClicked());
						Mouses.set(i, b);
					}
				}
			}
			else {
				if(Mouses.size() > i){
					Mouse b = new Mouse(new Vector3(-1,-1,0),true);
					Mouses.set(i, b);
				}
			}
		}
		for(int j = 0; j < Runes.size(); j++){
			for(int k = 0; k < Mouses.size(); k++){
				if(Runes.get(j).contains(Mouses.get(k).getMouse().x, Mouses.get(k).getMouse().y,1,1) >= 1 && Mouses.get(k).getIsClicked()){
					Mouses.get(k).setIsClicked(false);
					System.out.println(Gdx.files.getLocalStoragePath());
					InputFile.save("Runa:" + j + ";Secondi:" + Math.floor(seconds*10)/10, "Tilesets/Level2" );
				}
			}
		}
	}
	
	public void UpdateRunes(float dt){
		for(int i = 0; i < Runes.size(); i++){
			if(!Runes.get(i).getisVisible()){
				RuneTimer[i] +=dt;
				if(RuneTimer[i] > 1.5f){
					RuneTimer[i] = 0;
					Runes.get(i).setisVisible(true);
					Runes.get(i).TurnOn(1);
				}
			}
		}
	}
	
	@Override
	public void update(float dt) {
		seconds+=dt;
		handleInput();
		
		if(seconds > 150){
			gsm.set(new TransitionState(gsm, this, prev, Transition.LOST));
		}
		
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		SfondoSotto.render(sb);
		
		SfondoCentro.render(sb);
		
		Barradestrasotto.render(sb);
		Barrasinistrasotto.render(sb);
		Barradestrasopra.render(sb);
		Barrasinistrasopra.render(sb);
		
		for(int i = 0; i < Runes.size(); i++){
			Runes.get(i).render(sb);
		}
		
		sb.end();
	}
	
}
