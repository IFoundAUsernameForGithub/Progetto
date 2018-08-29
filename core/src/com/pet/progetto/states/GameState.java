package com.pet.progetto.states;

import java.awt.image.RescaleOp;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.pet.progetto.Progetto;
import com.pet.progetto.prefences.RuneStatic;
import com.pet.progetto.states.TransitionState.Transition;
import com.pet.progetto.support.GraTime;
import com.pet.progetto.support.LineBlocked;
import com.pet.progetto.support.Mouse;
import com.pet.progetto.support.ObstaTime;
import com.pet.progetto.support.TileSet;
import com.pet.progetto.ui.Character;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Graphic;
import com.pet.progetto.ui.InputFile;
import com.pet.progetto.ui.MapPoint;
import com.pet.progetto.ui.Monster;
import com.pet.progetto.ui.Obstacle;
import com.pet.progetto.ui.State;
import com.pet.progetto.ui.Runes;

public class GameState extends State{
	
	private Graphic SfondoSotto;
	private Graphic SfondoCentro;
	
	private Runes Runa0;
	private Runes Runa1;
	private Runes Runa2;
	private Runes Runa3;
	private Runes Runa4;
	
	private Color VitaColor = new Color();
	private int VitaValue = 100;
	private int StaminaValue = 100;
	
	private ShapeRenderer Stamina;
	private ShapeRenderer Vita;
	
	private Color VitaPGColor = new Color();
	private int VitaPGValue;
	private ShapeRenderer VitaPG;
	
	private Graphic Barradestrasopra;
	private Graphic Barradestrasotto;
	private Graphic Barrasinistrasopra;
	private Graphic Barrasinistrasotto;
	private Graphic BarraVitaPersonaggio;
	
	private Monster monster;
	private int world;
	
	private ArrayList<ObstaTime> ObstacleQueue = new ArrayList<ObstaTime>();
	private ArrayList<ArrayList<Obstacle>> Obstacles = new ArrayList <ArrayList<Obstacle>>();
	private ArrayList<Obstacle> Obstacles0 = new ArrayList<Obstacle>();
	private ArrayList<Obstacle> Obstacles1 = new ArrayList<Obstacle>();
	private ArrayList<Obstacle> Obstacles2 = new ArrayList<Obstacle>();
	private ArrayList<Obstacle> Obstacles3 = new ArrayList<Obstacle>();
	private ArrayList<Obstacle> Obstacles4 = new ArrayList<Obstacle>();
	
	private float timerStone = 0;
	private float timerlimit = 0.5f;
	private final float TIMERLIMIT = 0.5f;
	
	
	private float seconds = 0;
	private int count = 0;
	
	
	private BitmapFont font = new BitmapFont();
	
	private ArrayList<Runes> Runes = new ArrayList<Runes>();
	private ArrayList<Mouse> Mouses = new ArrayList<Mouse>();
	
	//private int ficata=0;
	
	private int precision;
	private ArrayList<GraTime> Precision = new ArrayList<GraTime>();
	private float timerPrecisionLimit = 1;
	
	private ArrayList<Obstacle> ObstacleAnimation = new ArrayList<Obstacle>();
	
	private ArrayList<Graphic> Aura = new ArrayList<Graphic>();
	
	private float auraTimer[] = new float[5];
	private float RuneTimer[] = new float[5];
	
	
	private boolean isStaminaEmpty = false;
	private Graphic VitaAura;
	private boolean isStaminaFull = false;
	
	private int numMonster;
	private MapState prev;
	private LineBlocked oneBlocked = new LineBlocked(-1, -1, -1);
	private LineBlocked twoBlocked = new LineBlocked(-1, -1, -1);
	private TileSet set;
	
	public GameState(GSM gsm,MapState prev,int numMonster,int world,int difficulty){
		super(gsm);
		this.prev = prev;
		
		this.world = world;
		this.numMonster = numMonster;
		
		for(int j = 0; j < 5; j++){
			RuneTimer[j]=0;
			auraTimer[j]=0;
		}
		
		
		monster = new Monster(numMonster,world,difficulty);
		
		VitaPGValue = Progetto.getLife();
		VitaValue = monster.getHp();
		StaminaValue = monster.getStamina();

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
		
		BarraVitaPersonaggio = new Graphic(Progetto.res.getAtlas("pack").findRegion("Barrahpterra"),
				Progetto.WIDTH/2,
				19,
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
		
		Stamina = new ShapeRenderer();
		Vita = new ShapeRenderer();
		VitaPG = new ShapeRenderer();
		
		
		VitaAura = new Graphic(Progetto.res.getAtlas("pack").findRegion("Aura"),
				monster.getx() + monster.getWidth()/2,
				monster.gety() + monster.getHeight()/2,
				1,
				1);
		
		System.out.println(InputFile.load("Tilesets/Level1").toString());
		set = new TileSet(InputFile.load("Tilesets/Level1"));
		
	}
	
	public void MoveObject(){
		
		for(int i = 0; i < Obstacles0.size(); i++){
			Obstacles0.get(i).MoveY(6);
			Obstacles0.get(i).MoveX(2);
			Obstacles0.get(i).setSize(1);
		}
		for(int i = 0; i < Obstacles1.size(); i++){
			Obstacles1.get(i).MoveY(7);
			Obstacles1.get(i).MoveX(1);
			Obstacles1.get(i).setSize(1);
		}
		for(int i = 0; i < Obstacles2.size(); i++){
			Obstacles2.get(i).MoveY(8);
			Obstacles2.get(i).MoveX(0);
			Obstacles2.get(i).setSize(1);
		}
		for(int i = 0; i < Obstacles3.size(); i++){
			Obstacles3.get(i).MoveY(7);
			Obstacles3.get(i).MoveX(1);
			Obstacles3.get(i).setSize(1);
		}
		for(int i = 0; i < Obstacles4.size(); i++){
			Obstacles4.get(i).MoveY(6);
			Obstacles4.get(i).MoveX(2);
			Obstacles4.get(i).setSize(1);
		}
		
	}
	
	public void RemoveObstacles(){
		for(int i = 0; i < Obstacles.size(); i++){
			int num = -2;
			if(Obstacles.get(i).size()!=0){
				num = Obstacles.get(i).get(0).getClicked();
				if(num < 0){
					if(Obstacles.get(i).get(0).gety() < Runes.get(i).gety() - Runes.get(i).getHeight()/2 - Obstacles.get(i).get(0).getSize()/2){
						Precision.add(new GraTime(
								new Graphic(	Progetto.res.getAtlas("pack").findRegion("Miss"),
								Runes.get(i).getx(),
								Runes.get(i).gety()+125,
								1,
								1)
								,0));
						Runes.get(i).setisVisible(false);
						Runes.get(i).TurnOn(-1);
						
						Obstacles.get(i).remove(0);
						
						if(VitaPGValue > 0){
							VitaPGValue -= attackedRaiden();
							Progetto.setLife(VitaPGValue);
							Progetto.setLifeActual(VitaPGValue - Character.getHpIncrement());
						}
						else{
							VitaPGValue = 0;
							Progetto.setLife(VitaPGValue);
							Progetto.setLifeActual(VitaPGValue - Character.getHpIncrement());
						}
					}
				}
				else{
					
					if(Runes.get(i).contains(Obstacles.get(i).get(0).getx(), Obstacles.get(i).get(0).gety(), Obstacles.get(i).get(0).getWidth(), Obstacles.get(i).get(0).getHeight())==0){
						Obstacles.get(i).remove(0);
					}
					
				}
			}
		}
	}
	
	public void RemovePrecision(float dt){
		if(Precision.size()!=0){
			for(int j = 0; j < Precision.size(); j++){
				Precision.get(j).setTimer(Precision.get(j).getTimer()+dt);
				if(Precision.get(j).getTimer() >= timerPrecisionLimit){
					Precision.remove(j);
				}
			}
		}
	}
	
	public void UpdatePrecision(){
		for(int n = 0; n < Precision.size(); n++){
			Precision.get(n).getGraphic().setY(Precision.get(n).getGraphic().gety()+1);
		}
	}
	
	public void RemoveAura(float dt){

		for(int i = 0; i < Aura.size(); i++){
			if(Aura.get(i).getVisible()){
				auraTimer[i] +=dt;
				if(auraTimer[i] > 0.3f){
					auraTimer[i] = 0;
					Aura.get(i).setvisible(false);
				}
			}
		}
	}
	
	public void StartObstacles(float dt){
		for(int i = 0; i < ObstacleQueue.size(); i++){
			ObstacleQueue.get(i).setTimer(ObstacleQueue.get(i).getTimer()-dt);
			if(ObstacleQueue.get(i).getTimer() < 0){
				switch(ObstacleQueue.get(i).getLine()){
					case 0:{
						Obstacles0.add(new Obstacle(ObstacleQueue.get(i).getSize(),ObstacleQueue.get(i).getLine(),ObstacleQueue.get(i).getElement(),ObstacleQueue.get(i).getIsDouble()));
						break;
					}
					case 1:{
						Obstacles1.add(new Obstacle(ObstacleQueue.get(i).getSize(),ObstacleQueue.get(i).getLine(),ObstacleQueue.get(i).getElement(),ObstacleQueue.get(i).getIsDouble()));
						break;
					}
					case 2:{
						Obstacles2.add(new Obstacle(ObstacleQueue.get(i).getSize(),ObstacleQueue.get(i).getLine(),ObstacleQueue.get(i).getElement(),ObstacleQueue.get(i).getIsDouble()));
						break;
					}
					case 3:{
						Obstacles3.add(new Obstacle(ObstacleQueue.get(i).getSize(),ObstacleQueue.get(i).getLine(),ObstacleQueue.get(i).getElement(),ObstacleQueue.get(i).getIsDouble()));
						break;
					}
					case 4:{
						Obstacles4.add(new Obstacle(ObstacleQueue.get(i).getSize(),ObstacleQueue.get(i).getLine(),ObstacleQueue.get(i).getElement(),ObstacleQueue.get(i).getIsDouble()));
						break;
					}
					default:{
						Obstacles0.add(new Obstacle(ObstacleQueue.get(i).getSize(),ObstacleQueue.get(i).getLine(),ObstacleQueue.get(i).getElement(),ObstacleQueue.get(i).getIsDouble()));
						break;
					}
				}
				ObstacleQueue.remove(i);
			}
		}
	}
	
	public void GenerateObstacles(float dt){
		seconds += dt;
		float diff = seconds - set.getSeconds(count);
		if(Math.abs(diff) < 0.1){
			float time;
			if(diff >= 0){
				time = diff;
			}
			else{
				time = 0;
			}
			ObstacleQueue.add(new ObstaTime(new Obstacle(10,set.getRuna(count),"Terra",true), time));
			count++;
		}
		// TODO
		if(count >= set.getMax()){
			gsm.set(new TransitionState(gsm, this, prev, Transition.BLACK_FADE));
		}
	}
	
	public void removeQueue(){
		ObstacleQueue.clear();
		System.out.println(ObstacleQueue.size());
	}
	
	
	public void CheckVitaStamina(){
		
		if(StaminaValue < 0){
			removeQueue();
			oneBlocked = new LineBlocked(-1, -1, -1);
			twoBlocked = new LineBlocked(-1, -1, -1);
			StaminaValue = 0;
			isStaminaEmpty = true;
			isStaminaFull = false;
		}
		
		if(VitaValue > monster.getMaxHp()/2){
			VitaColor = Color.GREEN;
		}
		else if(VitaValue > monster.getMaxHp()/4  && VitaValue <= monster.getMaxHp()/2){
			VitaColor = Color.YELLOW;
		}
		else if(VitaValue < monster.getMaxHp()/4 && VitaValue >= 0){
			VitaColor = Color.RED;
		}
		else if(VitaValue < 0){
			VitaValue = 0;
			int random = MathUtils.random(15, 104);
			RuneStatic.addRuneInventario(random);
			int runaX=0;
			int runaY=0;
			int size = prev.RuneInventario.size();
			int page = prev.pageRunes;
			runaX = 300 + 145*(((size-5)%9)/3);
			runaY = -375 - 145*((size-5)%3);
			if(size < 15 + 9*(page-2)){
				runaX-=1000;
			}
			else if(size >= 14+9*(page-1)){
				runaX+=1000;
			}
			prev.RuneInventario.add(new Runes(random,runaX,runaY));
			if((size-5) >= 9*prev.maxPageRunes){
				prev.maxPageRunes++;
				prev.Page = prev.pageRunes + " / " + prev.maxPageRunes;
			}
			if(MathUtils.random(0, 9) == 9){
				Progetto.setEtere(Progetto.getEtere()+ 1);
				prev.Etere = Integer.toString(Progetto.getEtere());
			}
			Progetto.setArkai(Progetto.getArkai() + monster.getArkai());
			prev.Arkai = Integer.toString(Progetto.getArkai());
			Progetto.setExpRaiden(Progetto.getExpRaiden() + monster.getExp());
			prev.ExpRaiden = Progetto.getExpRaiden();
			
			if(Progetto.getExpRaiden()>5*Progetto.getLevelRaiden()){
				int resto = Progetto.getExpRaiden() - 5*Progetto.getLevelRaiden();
				Progetto.setExpRaiden(resto);
				prev.ExpRaiden = Progetto.getExpRaiden();
				Progetto.setLevelRaiden(Progetto.getLevelRaiden() + 1);
				prev.Level = Integer.toString(Progetto.getLevelRaiden());
			}
			if(Progetto.getMaxLevel() < numMonster + 2 && numMonster < 9){
				Progetto.setMaxLevel(numMonster + 2);
			}
			
			prev.maxLevelUnblocked = Progetto.getMaxLevel();
			
			for(int i = 0; i < 10; i++){
				int x;
				int y;
				
				boolean value;
				if(i < prev.maxLevelUnblocked){
					value = false;
				}
				else{
					value = true;
				}
				if(i == 9){
					x = (int) prev.MapPoints.get(i).getX();
					y = (int) prev.MapPoints.get(i).getY();
					prev.MapPoints.remove(i);
					prev.MapPoints.add(i,new MapPoint(i, 0, x, y, value));
				}
				else{
					x = (int) prev.MapPoints.get(i).getX();
					y = (int) prev.MapPoints.get(i).getY();
					prev.MapPoints.remove(i);
					prev.MapPoints.add(i,new MapPoint(i, 0, x, y, value));
				}
			}
			
			prev.timeToRefill = Progetto.getMaxLife() - Progetto.getLife()*1;
			
			gsm.set(new TransitionState(gsm, this, prev, Transition.BLACK_FADE));
			
		}
	}
	
	public void CheckVitaPG(){
		
		if(VitaPGValue > Progetto.getMaxLife()/2){
			VitaPGColor = Color.GREEN;
		}
		else if(VitaPGValue > Progetto.getMaxLife()/4  && VitaPGValue <= Progetto.getMaxLife()/2){
			VitaPGColor = Color.YELLOW;
		}
		else if(VitaPGValue < Progetto.getMaxLife()/4 && VitaPGValue > 0){
			VitaPGColor = Color.RED;
		}
		else if(VitaPGValue <= 0){
			VitaPGValue = 0;
			Progetto.setLife(VitaPGValue);
			Progetto.setLifeActual(VitaPGValue - Character.getHpIncrement());
			prev.timeToRefill = Progetto.getMaxLife() - Progetto.getLife()*1;
			gsm.set(new TransitionState(gsm, this, prev, Transition.LOST));
			
		}
	}
	
	@Override
	public void handleInput() {
		if(!isStaminaEmpty){
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
			
			int count = 0;
			int mouse1 = 0;
			
			for(int j = 0; j < Runes.size(); j++){
				if(Runes.get(j).getisVisible()){
					for(int k = 0; k < Mouses.size(); k++){
						if(Runes.get(j).contains(Mouses.get(k).getMouse().x, Mouses.get(k).getMouse().y,1,1) >= 1 && Mouses.get(k).getIsClicked()){
							Mouses.get(k).setIsClicked(false);
							mouse1++;
							if(Obstacles.get(j).size()!=0){
								for(int l = 0; l < Obstacles.get(j).size(); l++){
									if(Obstacles.get(j).get(l).getisVisible()){
										precision = Runes.get(j).contains(Obstacles.get(j).get(l).getx(),Obstacles.get(j).get(l).gety(),Obstacles.get(j).get(l).getSize(),Obstacles.get(j).get(l).getSize());
										if(precision >= 1){
											
		
											if(Obstacles.get(j).get(l).getisVisible()){
											
												ObstacleAnimation.add(new Obstacle( 
														Obstacles.get(j).get(l).getSize(),
														Obstacles.get(j).get(l).getLine(),
														Obstacles.get(j).get(l).getElement(),
														Obstacles.get(j).get(l).getIsDouble())
												);
												
												ObstacleAnimation.get(ObstacleAnimation.size()-1).SetX(Obstacles.get(j).get(l).getx());
												ObstacleAnimation.get(ObstacleAnimation.size()-1).SetY(Obstacles.get(j).get(l).gety());
												ObstacleAnimation.get(ObstacleAnimation.size()-1).setStartSize(ObstacleAnimation.get(ObstacleAnimation.size()-1).getSize());
												
											}
											
											
											count++;
											if(precision == 3 && Obstacles.get(j).get(l).getClicked() < 0){
												StaminaValue-=attackMonster();
												Obstacles.get(j).get(l).setClicked(k);
												Obstacles.get(j).get(l).setisVisible(false);
												Precision.add(new GraTime(
														new Graphic(	Progetto.res.getAtlas("pack").findRegion("Nice"),
														Runes.get(j).getx(),
														Runes.get(j).gety()+125,
														1,
														1)
														,0));
												Aura.get(j).setvisible(true);
											}
											else if(precision == 2 && Obstacles.get(j).get(l).getClicked() < 0){
												StaminaValue-=attackMonster()/2;
												Obstacles.get(j).get(l).setClicked(k);
												Obstacles.get(j).get(l).setisVisible(false);
												Precision.add(new GraTime(
														new Graphic(	Progetto.res.getAtlas("pack").findRegion("Ok"),
														Runes.get(j).getx(),
														Runes.get(j).gety()+125,
														1,
														1)
														,0));
												Aura.get(j).setvisible(true);
											}
											else if(precision == 1 && Obstacles.get(j).get(l).getClicked() < 0){
												StaminaValue-=attackMonster()/4;
												Obstacles.get(j).get(l).setClicked(k);
												Obstacles.get(j).get(l).setisVisible(false);
												Precision.add(new GraTime(
														new Graphic(	Progetto.res.getAtlas("pack").findRegion("Bad"),
														Runes.get(j).getx(),
														Runes.get(j).gety()+125,
														1,
														1)
														,0));
												Aura.get(j).setvisible(true);
											}
										}
										l = Obstacles.get(j).size();
									}
								}
							}
							if(count == 0 && Runes.get(j).getisVisible()){
								Precision.add(new GraTime(new Graphic(	Progetto.res.getAtlas("pack").findRegion("Miss"),
										Runes.get(j).getx(),
										Runes.get(j).gety()+125,
										1,
										1)
										,0));
								Runes.get(j).setisVisible(false);
								Runes.get(j).TurnOn(-1);
							}
							else if(Runes.get(j).getisVisible()){
								Runes.get(j).TurnOn(1);
							}
							count = 0;
						}
						
						else if(mouse1 == 0){
							Runes.get(j).TurnOn(0);
						}
					}
					mouse1=0;
				}
			}
		}
		else{
			if(StaminaValue > 0 && StaminaValue < monster.getMaxStamina()){
				if(Gdx.input.justTouched()){
					VitaValue -= attackMonster();
				}
			}
			
		}
	}
	
	public int attackMonster(){
		int value = (int)(Character.getAttack()*(1 - monster.getDefense()));
		if(Progetto.getLife() + value*Character.getLifesteal() < Progetto.getMaxLife()){
			Progetto.setLife((int)(Progetto.getLife() + value*Character.getLifesteal()));
			Progetto.setLifeActual((int)(Progetto.getLife() - Character.getHpIncrement()));
		}
		else{
			Progetto.setLife(Progetto.getMaxLife());
			Progetto.setLifeActual(Progetto.getMaxLife() - Character.getHpIncrement());
		}
		VitaPGValue = Progetto.getLife();
		return value;
	}
	
	public int attackedRaiden(){
		int value = (int)(monster.getAttack()*(1 - Character.getDefense()));
		monster.setHp((int)(monster.getHp() + value*monster.getLifesteal()));
		return value;
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

	private void RemoveEverything(){
		
		for(int i = 0; i < Obstacles.size(); i++){
			for(int j = 0; j < Obstacles.get(i).size(); j++){
				Obstacles.get(i).remove(0);
			}
		}
		
		for(int i = 0; i < Aura.size(); i++){
			Aura.get(i).setvisible(false);
			auraTimer[i]=0;
		}
		
		for(int i = 0; i < Runes.size(); i++){
			Runes.get(i).TurnOn(0);
			RuneTimer[i]=0;
		}
		
		for(int i = 0; i < ObstacleAnimation.size(); i++){
			ObstacleAnimation.remove(0);
		}
		
		for(int i = 0; i < Precision.size(); i++){
			Precision.remove(0);
		}
		
	}
	
	public void MoveToCenter(){
		
		if(monster.gety() - 1 > Progetto.HEIGHT/2 - monster.getHeight()/2){
			monster.setY(monster.gety() - 2);
			VitaAura.setY(VitaAura.gety() - 2);
			VitaAura.setWidth(VitaAura.getWidth()*1.007f);
			VitaAura.setHeight(VitaAura.getHeight()*1.007f);
		}
		else{
			monster.setY(Progetto.HEIGHT/2 - monster.getHeight()/2);
			VitaAura.setY(Progetto.HEIGHT/2);
			
			if(StaminaValue <= monster.getMaxStamina()){
				StaminaValue += monster.getRechargeStamina();
			}
			else{
				StaminaValue = monster.getMaxStamina();
				isStaminaFull = true;
			}
			
		}
		
	}
	
	public void MoveBack(){
		if(monster.gety() + 1 < Progetto.HEIGHT - monster.getHeight() - 10){
			
			monster.setY(monster.gety() + 2);
			VitaAura.setY(VitaAura.gety() + 2);
			VitaAura.setWidth(VitaAura.getWidth()/1.007f);
			VitaAura.setHeight(VitaAura.getHeight()/1.007f);
			
		}
		else{
			
			monster.setY(Progetto.HEIGHT - monster.getHeight() - 10);
			VitaAura.setY(monster.gety() + monster.getHeight()/2);
			isStaminaEmpty = false;
			isStaminaFull = false;
		
		}
		
	}
	
	@Override
	public void update(float dt) {
		
		handleInput();
		
		CheckVitaStamina();
		
		CheckVitaPG();
		
		if(!isStaminaEmpty){
			MoveObject();
			
			RemoveObstacles();
			
			RemovePrecision(dt);
			
			UpdatePrecision();
			
			RemoveAura(dt);
			
			GenerateObstacles(dt);
			
			StartObstacles(dt);
			
			UpdateRunes(dt);
			
			AnimationObstacle(dt);
		}
		else{
			if(!isStaminaFull){
				RemoveEverything();
				MoveToCenter();
			}
			else{
				MoveBack();
			}
		}
		
	}
	
	private void AnimationObstacle(float dt) {
		
		for(int i = 0; i < ObstacleAnimation.size(); i++){
			ObstacleAnimation.get(i).setSize(1);
			if(ObstacleAnimation.get(i).getSize()>ObstacleAnimation.get(i).getStartSize()+30){
				ObstacleAnimation.remove(i);
			}
		}
	}
	
	private float RenderObstacleAnimation(int p){
		return 1 - ((float)(ObstacleAnimation.get(p).getSize()-ObstacleAnimation.get(p).getStartSize())/30);
	}

	private float RenderPrecision(float dt){
		if(dt < (timerPrecisionLimit/3)){
			return dt/(timerPrecisionLimit/3);
		}
		else if(dt <(timerPrecisionLimit*2f/3)){
			return 1;
		}
		else{
			return 1-dt/timerPrecisionLimit;
		}
	}
	
	private float RenderAura(float dt){
		if(dt < 0.15f){
			return dt/0.15f;
		}
		else{
			return 1-(dt-0.15f)/0.15f;
		}
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();

		SfondoSotto.render(sb);
		
		if(!isStaminaEmpty){
			monster.render(sb);
		
		SfondoCentro.render(sb);
		
		Barradestrasotto.render(sb);
		Barrasinistrasotto.render(sb);
		
		sb.end();
		Stamina.setProjectionMatrix(cam.combined);
		Stamina.begin(ShapeType.Filled);
		Stamina.setColor(Color.LIGHT_GRAY);
		Stamina.rect(10, Progetto.HEIGHT/2 - 150, 20, StaminaValue*445/monster.getMaxStamina());
		Stamina.end();
		
		Vita.setProjectionMatrix(cam.combined);
		Vita.begin(ShapeType.Filled);
		Vita.setColor(VitaColor);
		Vita.rect(Progetto.WIDTH - 30, Progetto.HEIGHT/2 - 150, 20, VitaValue*445/monster.getMaxHp());
		Vita.end();
		sb.begin();
		
		Barradestrasopra.render(sb);
		Barrasinistrasopra.render(sb);

			
			for(int m = 0; m < Aura.size(); m++){
				if(Aura.get(m).getVisible()){
					sb.setColor(1, 1, 1, RenderAura(auraTimer[m]));
					Aura.get(m).render(sb);
					sb.setColor(1, 1, 1, 1);
				}
			}
			
			for(int i = 0; i < Runes.size(); i++){
				Runes.get(i).render(sb);
			}
			
			for(int p = 0; p < ObstacleAnimation.size(); p++){
				sb.setColor(1, 1, 1, RenderObstacleAnimation(p));
				ObstacleAnimation.get(p).render(sb);
				sb.setColor(1, 1, 1, 1);
			}
			
			for(int u = 0; u < Precision.size(); u++){
				sb.setColor(1, 1, 1, RenderPrecision(Precision.get(u).getTimer()));
				Precision.get(u).getGraphic().render(sb);
				sb.setColor(1, 1, 1, 1);
			}
			
			
			
			for(int j = 0; j < Obstacles.size(); j++){
				for(int i = Obstacles.get(j).size(); i > 0; i--){
					//if(i > 7){
					//	sb.setColor(0.7f, 0.7f, 0.7f, 1);
					//}
					if(Obstacles.get(j).get(i-1).getisVisible()){
						Obstacles.get(j).get(i-1).render(sb);
					}
					//sb.setColor(1, 1, 1, 1);
				}
			}
			
		}
		else{
			
			SfondoCentro.render(sb);
			
			for(int i = 0; i < Runes.size(); i++){
				Runes.get(i).render(sb);
			}
			
			sb.setColor(1, 1, 1, 1);
			VitaAura.render(sb);
			sb.setColor(1, 1, 1, 1);
			
			
			
			Barradestrasotto.render(sb);
			Barrasinistrasotto.render(sb);
			
			sb.end();
			Stamina.setProjectionMatrix(cam.combined);
			Stamina.begin(ShapeType.Filled);
			Stamina.setColor(Color.LIGHT_GRAY);
			Stamina.rect(10, Progetto.HEIGHT/2 - 150, 20, StaminaValue*445/monster.getMaxStamina());
			Stamina.end();
			
			Vita.setProjectionMatrix(cam.combined);
			Vita.begin(ShapeType.Filled);
			Vita.setColor(VitaColor);
			Vita.rect(Progetto.WIDTH - 30, Progetto.HEIGHT/2 - 150, 20, VitaValue*445/monster.getMaxHp());
			Vita.end();
			sb.begin();
			
			Barradestrasopra.render(sb);
			Barrasinistrasopra.render(sb);
			
			monster.render(sb);
		}
		
		
		font.draw(sb, "n of stones: " + ObstacleAnimation.size(), Progetto.WIDTH/2, Progetto.HEIGHT/2);
		sb.end();
		
		VitaPG.setProjectionMatrix(cam.combined);
		VitaPG.begin(ShapeType.Filled);
		VitaPG.setColor(VitaPGColor);
		VitaPG.rect(Progetto.WIDTH/2 - VitaPGValue*(575f/Progetto.getMaxLife())/2, 0, (VitaPGValue*575f)/Progetto.getMaxLife(), 19);
		VitaPG.end();
		sb.begin();
		
		BarraVitaPersonaggio.render(sb);
		
		sb.end();
	}
	
}
