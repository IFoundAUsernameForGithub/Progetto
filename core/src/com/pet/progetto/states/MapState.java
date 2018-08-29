package com.pet.progetto.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.pet.progetto.Progetto;
import com.pet.progetto.prefences.RuneStatic;
import com.pet.progetto.states.TransitionState.Transition;
import com.pet.progetto.ui.Character;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Graphic;
import com.pet.progetto.ui.MapPoint;
import com.pet.progetto.ui.Runes;
import com.pet.progetto.ui.State;
import com.pet.progetto.ui.Monster;
import com.pet.progetto.support.OggettoShop;

public class MapState extends State{
	
	private Graphic Top;
	private Graphic bottom;
	private Graphic map;
	private float deltaY;
	private float deltaXInventario;
	private int deltaXShop;
	private int deltaYShop;
	private float SpostamentoY;
	public float CertaY;
	
	

	private final int limitCircle = 15;
	private float mouseXStart = -1;
	private float mouseYStart = -1;
	private boolean isMouseOnCircle = true;
	private boolean hasPassedOnce = false;
	
	private boolean isMovingShop = false;
	private boolean isMovingOggettiShop = false;
	private boolean isMovingOggettiInventario = false;
	
	
	private int world;
	private Graphic worldSimbol;
	private String monsterName;
	
	private boolean bottomIsUp = false;
	private boolean bottomHasToClose = false;
	private int bottomNumber = -1;
	private float bottomTimer = 0;
	private float bottomPrecY = 0;
	private float bottomSpeed = 0;
	private float timerlimit = 0.5f;
	
	private BitmapFont font = new BitmapFont(Gdx.files.internal("Font/font.fnt"), Gdx.files.internal("Font/font.png"), false);
	private BitmapFont fontMeta20 = new BitmapFont(Gdx.files.internal("Font/Meta20.fnt"), Gdx.files.internal("Font/Meta20.png"), false);
	private BitmapFont fontMeta26 = new BitmapFont(Gdx.files.internal("Font/Meta26.fnt"), Gdx.files.internal("Font/Meta26.png"), false);
	private BitmapFont fontMeta54 = new BitmapFont(Gdx.files.internal("Font/Meta54.fnt"), Gdx.files.internal("Font/Meta54.png"), false);
	private BitmapFont font64 = new BitmapFont(Gdx.files.internal("Font/font64.fnt"), Gdx.files.internal("Font/font64.png"), false);
	
	
	public String Level = new String();
	public String Arkai = new String();
	public String Etere = new String();
	
	private String Upgrade = new String();
	private String Sell = new String();
	private String Sort = new String();
	public String Page = new String();
	
	private Graphic lato;
	private Graphic latoSotto;
	private boolean hasClickedMap=false;
	
	private int levelSelected = 0;
	private int modeSelected = 1;
	private float timerLevel = 0f;
	private float maxTimerLevel = 0.5f;
	public int maxLevelUnblocked;
	
	private boolean moveLeft = false;
	private boolean moveRight = true;
	private boolean moveLeftDone = true;
	private boolean moveRightDone = true;
	
	private Graphic IconeSpente;
	private Graphic	IconaStatus;
	private Graphic IconaEquip;
	private Graphic IconaRune;
	private Graphic IconaShop;
	private Graphic IconaOpzioni;
	
	private Graphic DescrizioneRune;
	private Graphic CorniceStatus;
	private Graphic DescrizioneEquip;
	private Graphic OggettoSelezionato;
	
	private Graphic Cappello;
	private Graphic Giacca;
	private Graphic Guanti;
	private Graphic Pantaloni;
	private Graphic Scarpe;
	private Graphic Anello1;
	private Graphic Anello2;
	private Graphic Bracciale;
	private Graphic Collana;
	private Graphic Talismano;
	
	
	private Graphic corniceShop;
	private ArrayList<OggettoShop> OggettiShop = new ArrayList<OggettoShop>();
	private ArrayList<Graphic> OggettiInventario = new ArrayList<Graphic>();
	private ArrayList<Graphic> selezioneShop = new ArrayList<Graphic>();
	public ArrayList<Runes> RuneInventario = new ArrayList<Runes>(); 
	public Runes RunaDescrizione;
	private ArrayList<Graphic> RunePlaces = new ArrayList<Graphic>();
	
	private Graphic FrecciaDestra;
	private Graphic FrecciaSinistra;
	private TextureRegion textureFrecciaSinistra;
	
	private Graphic PulsanteUpgrade;
	private Graphic PulsanteSell;
	private Graphic PulsanteSort;
	
	
	private GlyphLayout layout = new GlyphLayout();
	
	private float holdTimer = 0f;
	private boolean hasToSwap = false;
	private int swapFirstIndex = -1;
	private int swapSecondIndex = -1;
	private float holdStartPositionX = 0;
	private float holdStartPositionY = 0;
	private int holdStartPage = 0;
	
	private int sortCount=0;
	public int pageRunes=1;
	public int maxPageRunes;
	private boolean isSorted = false;
	private String Sorted;
	private boolean hasClickedSort = false;
	private boolean hasClickedRunes = false;
	private boolean hasClickedAvanti = false;
	private boolean hasClickedIndietro = false;
	
	public ArrayList<MapPoint> MapPoints = new ArrayList<MapPoint>();
	
	private float pageTimerDestra = 0f;
	private float pageTimerSinistra = 0f;
	
	private Graphic BottoneEasy;
	private Graphic BottoneNormal;
	private Graphic BottoneHard;
	private Graphic BottoneFight;
	private Graphic StatSotto;
	private Graphic HpSotto;
	
	private ShapeRenderer attack;
	private float attackNum = 1;
	private ShapeRenderer defense;
	private float defenseNum = 1;
	private ShapeRenderer difficulty;
	private float difficultyNum = 1;
	private ShapeRenderer life;
	private Monster Monster;
	
	public float timeToRefill = 0;
	private float timer = 0;
	
	private boolean isActive = true;
	public int runeSelected = -1;
	public int ExpRaiden;
	
	
	public MapState(GSM gsm, int world) {
		super(gsm);
		
		this.world = world;
		
		Top = new Graphic(Progetto.res.getAtlas("pack2").findRegion("Sopramappa"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT -96,
				1,
				1);
		
		map = new Graphic(Progetto.res.getAtlas("pack7").findRegion("OverworldTerra"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT + 80,
				1,
				1);
		
		bottom = new Graphic(Progetto.res.getAtlas("pack2").findRegion("Sottomappa"),
				Progetto.WIDTH/2,
				-406,
				1,
				1);
		
		lato = new Graphic(Progetto.res.getAtlas("pack3").findRegion("Lato"),
				Progetto.WIDTH + 160,
				Progetto.HEIGHT/2,
				1,
				1);
		
		latoSotto = new Graphic(Progetto.res.getAtlas("pack3").findRegion("LatoSotto"),
				Progetto.WIDTH + 215,
				Progetto.HEIGHT/2 + 220,
				1,
				1);
		
		IconeSpente = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IconeSpente"),
				Progetto.WIDTH/2 + 4,
				60,
				1,
				1);
		
		IconaStatus = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IconaStatus"),
				Progetto.WIDTH/10,
				60,
				1,
				1);
		
		IconaEquip = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IconaEquip"),
				Progetto.WIDTH*3/10,
				60,
				1,
				1);
		
		IconaRune = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IconaRune"),
				Progetto.WIDTH/2,
				60,
				1,
				1);
		
		IconaShop = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IconaShop"),
				Progetto.WIDTH*7/10,
				60,
				1,
				1);
		
		IconaOpzioni = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IconaOpzioni"),
				Progetto.WIDTH*9/10,
				60,
				1,
				1);
		
		DescrizioneRune = new Graphic(Progetto.res.getAtlas("pack2").findRegion("DescrizioneRune"),
				550,
				-129,
				1,
				1);
		
		FrecciaDestra = new Graphic(Progetto.res.getAtlas("pack2").findRegion("Freccia"),
				590,
				-795,
				1,
				1);
		
		BottoneEasy = new Graphic(Progetto.res.getAtlas("pack3").findRegion("BottoneEasyPremuto"),
				Progetto.WIDTH + 215,
				Progetto.HEIGHT/2 + 23,
				1,
				1);
		
		BottoneNormal = new Graphic(Progetto.res.getAtlas("pack3").findRegion("BottoneNormal"),
				Progetto.WIDTH + 216,
				Progetto.HEIGHT/2 - 71,
				1,
				1);
		
		BottoneHard = new Graphic(Progetto.res.getAtlas("pack3").findRegion("BottoneHard"),
				Progetto.WIDTH + 216,
				Progetto.HEIGHT/2 -158,
				1,
				1);

		BottoneFight = new Graphic(Progetto.res.getAtlas("pack3").findRegion("BottoneFight"),
				Progetto.WIDTH + 216,
				Progetto.HEIGHT/2 - 440,
				1,
				1);
		
		StatSotto = new Graphic(Progetto.res.getAtlas("pack3").findRegion("StatSotto"),
				Progetto.WIDTH + 216,
				Progetto.HEIGHT/2 - 285,
				1,
				1);
		
		HpSotto = new Graphic(Progetto.res.getAtlas("pack2").findRegion("HpSotto"),
				Progetto.WIDTH - 116,
				Progetto.HEIGHT - 55,
				1,
				1);
		
		CorniceStatus = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreStatus"),
				Progetto.WIDTH/2 + 130,
				-475,
				1,
				1);
		
		DescrizioneEquip = new Graphic(Progetto.res.getAtlas("pack2").findRegion("DescrizioneEquip"),
				Progetto.WIDTH/2 + 65,
				-730,
				1,
				1);
		OggettoSelezionato = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),
				Progetto.WIDTH/2 - 270,
				-730,
				1,
				1);
		
		corniceShop = new Graphic(Progetto.res.getAtlas("pack2").findRegion("ShopSopra"),
				Progetto.WIDTH/2,
				-108,
				1,
				1);
		
		for(int i = 0; i < 11; i++){
			selezioneShop.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("ShopIcona"), Progetto.WIDTH/2 - 288 + 144*i, -108, 1, 1));
		}
		
		for(int i = 0; i < 10; i++){
			OggettiShop.add(new OggettoShop(1,Progetto.WIDTH/2,-280-172*i,1,1));
			i++;
			OggettiShop.add(new OggettoShop(1,Progetto.WIDTH/2,-280-172*i,-1,-1));
		}
		
		TextureRegion image = Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip");
		int size = 8;
		for(int i = 0; i < size; i++){
			if(size < 7){
				OggettiInventario.add(new Graphic(image,Progetto.WIDTH/2 - ((float)size/2 - i -0.5f)*116,-865,1,1));
			}
			else{
				OggettiInventario.add(new Graphic(image,70 + i*116,-865,1,1));
			}
		}
		
		/*
		 *  
		   	for(int i = 0; i < OggettiStatic.size(); i++){
				OggettiShop.add(new Graphic(progetto.res.getAtlas("pack2").findRegion("OggettoShopDoppio"), progetto.WIDTH/2, -280 - 172*i, 1, 1));
			}
		 * 
		 */
		
		
		
		int index = 0;
		int increase = 120;
		Cappello = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 -250,-100 - increase*index++,1,1);
		Giacca = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 -250,-100 - increase*index++,1,1);
		Guanti = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 -250,-100 - increase*index++,1,1);
		Pantaloni= new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 -250,-100 - increase*index++,1,1);
		Scarpe = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 -250,-100 - increase*index++,1,1);
		
		index = 0;
		Anello1 = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 + 250,-100 - increase*index++,1,1);
		Anello2 = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 + 250,-100 - increase*index++,1,1);
		Bracciale = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 + 250,-100 - increase*index++,1,1);
		Collana = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 + 250,-100 - increase*index++,1,1);
		Talismano = new Graphic(Progetto.res.getAtlas("pack2").findRegion("IndicatoreEquip"),Progetto.WIDTH/2 + 250,-100 - increase*index++,1,1);
		
		
		
		
		if(world == 0){
			worldSimbol = new Graphic(Progetto.res.getAtlas("pack3").findRegion("LatoTerra"),Progetto.WIDTH + 216,Progetto.HEIGHT - 170,1,1);
		}
		else if(world == 1){
			worldSimbol = new Graphic(Progetto.res.getAtlas("pack3").findRegion("LatoAcqua"),Progetto.WIDTH + 216,Progetto.HEIGHT - 170,1,1);
		}
		else if(world == 2){
			worldSimbol = new Graphic(Progetto.res.getAtlas("pack3").findRegion("LatoFuoco"),Progetto.WIDTH + 216,Progetto.HEIGHT - 170,1,1);
		}
		else if(world == 3){
			worldSimbol = new Graphic(Progetto.res.getAtlas("pack3").findRegion("LatoAria"),Progetto.WIDTH + 216,Progetto.HEIGHT - 170,1,1);
		}
		else if(world == 4){
			worldSimbol = new Graphic(Progetto.res.getAtlas("pack3").findRegion("LatoTempo"),Progetto.WIDTH + 216,Progetto.HEIGHT - 170,1,1);
		}
		else{
			worldSimbol = new Graphic(Progetto.res.getAtlas("pack3").findRegion("LatoTerra"),Progetto.WIDTH + 216,Progetto.HEIGHT - 170,1,1);
		}
		
		
		Monster = new Monster(1,world,1);
		Monster.setX(Progetto.WIDTH + 60);
		Monster.setY(Progetto.HEIGHT/2 + 210);
		
		monsterName = new String("Golem");
		
		
		
		textureFrecciaSinistra = new TextureRegion(Progetto.res.getAtlas("pack2").findRegion("Freccia"));
		textureFrecciaSinistra.flip(true, false);
		
		
		FrecciaSinistra = new Graphic(textureFrecciaSinistra,
				300,
				FrecciaDestra.gety(),
				1,
				1);
		
		int j = 0;
		int count = 0;
		for(int i = 0; i < RuneStatic.RuneInventario.size(); i++){
			int x=0;
			int y=0;
			if(i < 5){
				x = 110;
				y = -155 - i*160;
			}
			else{
				
				if(i < 14){
					x = 300 + 145*((i-5)/3);
					y = -375 - 145*((i-5)%3);
				}
				else{
					x = 1300 + 145*((i-5 -9*j)/3);
					y = -375 - 145*((i-5 -9*j)%3);
				}
				count++;
				if(count == 9){
					j++;
					count = 0;
				}
			}
			RuneInventario.add(new Runes(RuneStatic.getRuneInventario(i),x,y));
		}
		
		
		//runa descrizione
		RunaDescrizione = new Runes("Normal", 300, -155, "Off", 0);
		//sottoruna
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 300, -665, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 300, -520, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 300, -375, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 445, -665, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 445, -520, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 445, -375, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 590, -665, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 590, -520, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 590, -375, 1, 1));
		//runa descrizione
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 300, -155, 1, 1));
		
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 110, -795, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 110, -635, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 110, -475, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 110, -315, 1, 1));
		RunePlaces.add(new Graphic(Progetto.res.getAtlas("pack2").findRegion("Indicatore"), 110, -155, 1, 1));
		
		PulsanteSell = new Graphic(Progetto.res.getAtlas("pack2").findRegion("PulsanteRune"), 626, -245, 1, 1);
		PulsanteSort = new Graphic(Progetto.res.getAtlas("pack2").findRegion("PulsanteRune"), 450, -885, 1, 1);
		PulsanteUpgrade = new Graphic(Progetto.res.getAtlas("pack2").findRegion("PulsanteRune"), 470, -245, 1, 1);
		
		
		maxLevelUnblocked = Progetto.getMaxLevel();
		
		for(int i = 0; i < 10; i++){
			boolean value;
			if(i < maxLevelUnblocked){
				value = false;
			}
			else{
				value = true;
			}
			if(i == 9){
				MapPoints.add(new MapPoint(i, 0, Progetto.WIDTH/2, 210 + i*219 + 219, value));
			}
			else{
				MapPoints.add(new MapPoint(i, 0, Progetto.WIDTH/2, 277 + i*219, value));
			}
		}
		
		
		
		
		Level = Integer.toString(Progetto.getLevelRaiden());
		Etere = Integer.toString(Progetto.getEtere());
		Arkai = Integer.toString(Progetto.getArkai());
		Upgrade = "Upgrade";
		Sell = "Sell";
		Sort = "Sort";
		Sorted = "";
		maxPageRunes = (int)Math.ceil((double)((RuneInventario.size()-5))/9);
		if(maxPageRunes == 0){
			maxPageRunes = 1;
		}
		Page = pageRunes + " / " + maxPageRunes;
		
		
		attack = new ShapeRenderer();
		defense = new ShapeRenderer();
		difficulty = new ShapeRenderer();
		life = new ShapeRenderer();
		
		timeToRefill = Progetto.getMaxLife() - Progetto.getLife()*1;
		
	}
	
	public void handleInput() {
		
	
			if(!bottomIsUp){
				
				if(Progetto.getBackPressed()){
					if(!moveRight && moveRightDone && moveLeft && moveLeftDone){
						moveRight = true;
						moveLeft = false;
						moveRightDone = false;
					}
					else{
						gsm.set(new TransitionState(gsm, this, new LogoState(gsm), Transition.BLACK_FADE));
					}
				}
				
				if(Gdx.input.isTouched()){
					
					mouse.x = Gdx.input.getX();
					mouse.y = Gdx.input.getY();
					
					gsm.getVp().unproject(mouse);
					
					if(mouse.x > Progetto.WIDTH){mouse.x = Progetto.WIDTH + 1;}
					if(mouse.x < 0){mouse.x = -1;}
					if(mouse.y > Progetto.HEIGHT){mouse.y = Progetto.HEIGHT + 1;}
					if(mouse.y < 0){mouse.y = -1;}
					
					System.out.println(mouse);
					
					
					
					
					if(mouseXStart==-1&&mouseYStart==-1){
						mouseXStart = mouse.x;
						mouseYStart = mouse.y;
						hasPassedOnce = false;
						isMouseOnCircle=true;
					}
					else if(isMouseOnCircle){
						if(mouse.x > mouseXStart - limitCircle && mouse.x < mouseXStart + limitCircle
								&& mouse.y > mouseYStart - limitCircle && mouse.y < mouseYStart + limitCircle){
							isMouseOnCircle = true;
						}
						else{
							isMouseOnCircle = false;
						}
					}
					
					if(!isMouseOnCircle){

						if(moveLeft && moveLeftDone){
							if(mouseXStart <= Progetto.WIDTH/2 - 65){
								deltaY = Gdx.input.getDeltaY()*Progetto.HEIGHT/Gdx.graphics.getHeight();
							}
						}
						else{
							deltaY = Gdx.input.getDeltaY()*Progetto.HEIGHT/Gdx.graphics.getHeight();
						}
					}
					else{
						
						if(mouseXStart > Progetto.WIDTH - 100 && mouseYStart >= Progetto.HEIGHT/2 - 100 && mouseYStart <= Progetto.HEIGHT/2 + 100){
							setLevelStats();
							moveLeft = true;
							moveRight = false;
							moveLeftDone = false;
							checkLevelSelected();
						}
						
						if(!moveRight && moveRightDone && moveLeft && moveLeftDone){
							if(mouseYStart >= Progetto.HEIGHT/2 - 100 && mouseYStart <= Progetto.HEIGHT/2 + 100 &&
									mouseXStart >= Progetto.WIDTH/2 - 135 && mouseXStart <= Progetto.WIDTH/2 - 35){
								moveRight = true;
								moveLeft = false;
								moveRightDone = false;
							}
						}
						
						if(mouseXStart >= 0 && mouseXStart <= 144 && mouseYStart <= 117 && mouseYStart >= 0 && moveLeftDone && moveRightDone ){
							bottomIsUp = true;
							mouseXStart = -1;
							mouseYStart = -1;
							bottomNumber = 0;
							deltaY = 0;
						}
						else if(mouseXStart >= 145 && mouseXStart <= 288 && mouseYStart <= 117 && mouseYStart >= 0 && moveLeftDone && moveRightDone){
							bottomIsUp = true;
							mouseXStart = -1;
							mouseYStart = -1;
							bottomNumber = 1;
							deltaY = 0;
						}
						else if(mouseXStart >= 289 && mouseXStart <= 432 && mouseYStart <= 117 && mouseYStart >= 0 && moveLeftDone && moveRightDone){
							bottomIsUp = true;
							mouseXStart = -1;
							mouseYStart = -1;
							bottomNumber = 2;
							deltaY = 0;
						}
						else if(mouseXStart >= 433 && mouseXStart <= 576 && mouseYStart <= 117 && mouseYStart >= 0 && moveLeftDone && moveRightDone){
							bottomIsUp = true;
							mouseXStart = -1;
							mouseYStart = -1;
							bottomNumber = 3;
							deltaY = 0;
						}
						else if(mouseXStart >= 577 && mouseXStart <= 720 && mouseYStart <= 117 && mouseYStart >= 0 && moveLeftDone && moveRightDone){
							bottomIsUp = true;
							mouseXStart = -1;
							mouseYStart = -1;
							bottomNumber = 4;
							deltaY = 0;
						}
						
						
						if(moveLeft && moveLeftDone){
							if(BottoneEasy.contains(mouseXStart, mouseYStart, 1, 1) > 0 && modeSelected != 1){
								modeSelected = 1;
								setLevelStats();
								BottoneEasy.setImage(Progetto.res.getAtlas("pack3").findRegion("BottoneEasyPremuto"));
								BottoneNormal.setImage(Progetto.res.getAtlas("pack3").findRegion("BottoneNormal"));
								BottoneHard.setImage(Progetto.res.getAtlas("pack3").findRegion("BottoneHard"));
							}
							else if(BottoneNormal.contains(mouseXStart, mouseYStart, 1, 1) > 0 && modeSelected != 2){
								modeSelected = 2;
								setLevelStats();
								BottoneEasy.setImage(Progetto.res.getAtlas("pack3").findRegion("BottoneEasy"));
								BottoneNormal.setImage(Progetto.res.getAtlas("pack3").findRegion("BottoneNormalPremuto"));
								BottoneHard.setImage(Progetto.res.getAtlas("pack3").findRegion("BottoneHard"));
							}
							else if(BottoneHard.contains(mouseXStart, mouseYStart, 1, 1) > 0 && modeSelected != 3){
								modeSelected = 3;
								setLevelStats();
								BottoneEasy.setImage(Progetto.res.getAtlas("pack3").findRegion("BottoneEasy"));
								BottoneNormal.setImage(Progetto.res.getAtlas("pack3").findRegion("BottoneNormal"));
								BottoneHard.setImage(Progetto.res.getAtlas("pack3").findRegion("BottoneHardPremuto"));
							}
							else if(BottoneFight.contains(mouseXStart, mouseYStart, 1, 1) > 0 ){
								if(Progetto.getLife() > Progetto.getMaxLife()/10f){
									mouseXStart = -1;
									mouseYStart = -1;
									gsm.set(new TransitionState(gsm, this, new GameState(gsm,this,levelSelected,0,modeSelected), Transition.BLACK_FADE));
								}
								else{
									mouseXStart = -1;
									mouseYStart = -1;
									isActive = false;
									gsm.push(new LifeState(gsm, this));
								}
							}
						}
					}
				}
				else {
					
					if(deltaY!=0){
						hasPassedOnce = true;
					}
					if(isMouseOnCircle && !hasPassedOnce){
						if(!moveLeft && moveLeftDone && moveRight && moveRightDone){
							for(int i = 0; i < maxLevelUnblocked; i++){
								if(MapPoints.get(i).contains(mouseXStart, mouseYStart, 1, 1) > 0 || (mouseXStart > Progetto.WIDTH - 100 && mouseYStart >= Progetto.HEIGHT/2 - 100 && mouseYStart <= Progetto.HEIGHT/2 + 100)){
									levelSelected = i;
									setLevelStats();
									moveLeft = true;
									moveRight = false;
									moveLeftDone = false;
									checkLevelSelected();
								}
							}
						}
						
						if(moveLeft && moveLeftDone){
							
							if(mouseXStart <= Progetto.WIDTH/2 - 65){
								
								for(int i = 0; i < maxLevelUnblocked; i++){
									if(MapPoints.get(i).contains(mouse.x, mouse.y, 1, 1) > 0 && !hasClickedMap){
										levelSelected = i;
										hasClickedMap=true;
										setLevelStats();
										checkLevelSelected();
										
									}
								}
								
							}
							
						}
						hasPassedOnce = true;
					}
					
					mouseXStart = -1;
					mouseYStart = -1;
					hasClickedMap=false;
				}
			}
			else {
				
				if(!bottomHasToClose){
					
					if(Progetto.getBackPressed() && bottom.gety() == 539 ){
						bottomHasToClose = true;
						bottomTimer = timerlimit*2;
					}
					
					if(Gdx.input.isTouched()){
						mouse.x = Gdx.input.getX();
						mouse.y = Gdx.input.getY();
						
						gsm.getVp().unproject(mouse);
						
						if(mouse.x > Progetto.WIDTH){mouse.x = Progetto.WIDTH + 1;}
						if(mouse.x < 0){mouse.x = -1;}
						if(mouse.y > Progetto.HEIGHT){mouse.y = Progetto.HEIGHT + 1;}
						if(mouse.y < 0){mouse.y = -1;}
						
						if(bottom.gety() == 539 ){
							
							if(mouseXStart==-1&&mouseYStart==-1){
								mouseXStart = mouse.x;
								mouseYStart = mouse.y;
								hasPassedOnce = false;
								isMouseOnCircle=true;
							}
							else if(isMouseOnCircle){
								if(mouse.x > mouseXStart - limitCircle && mouse.x < mouseXStart + limitCircle
										&& mouse.y > mouseYStart - limitCircle && mouse.y < mouseYStart + limitCircle){
									isMouseOnCircle = true;
								}
								else{
									isMouseOnCircle = false;
								}
							}
							
							
							if((mouseYStart >= 1063 && mouseYStart <= Progetto.HEIGHT)){
								bottomHasToClose = true;
								bottomTimer = timerlimit*2;
							}
							
							if(mouseXStart >= 0 && mouseXStart <= 144 && mouseYStart <= 1062 && mouseYStart >= 945){
								bottomNumber = 0;
							}
							else if(mouseXStart >= 145 && mouseXStart <= 288 && mouseYStart <= 1062 && mouseYStart >= 945){
								bottomNumber = 1;
							}
							else if(mouseXStart >= 289 && mouseXStart <= 432 && mouseYStart <= 1062 && mouseYStart >= 945){
								bottomNumber = 2;
							}
							else if(mouseXStart >= 433 && mouseXStart <= 576 && mouseYStart <= 1062 && mouseYStart >= 945){
								bottomNumber = 3;
							}
							else if(mouseXStart >= 577 && mouseXStart <= 720 && mouseYStart <= 1062 && mouseYStart >= 945){
								bottomNumber = 4;
							}
							
							
							
							if(bottomNumber == 1){
								//INSERIRE IL RESTO
								
								if(!isMouseOnCircle){
									for(int i = 0; i < OggettiInventario.size() && !isMovingOggettiInventario; i++){
										if(OggettiInventario.get(i).contains(mouseXStart, mouseYStart, 1, 1) > 0){
											isMovingOggettiInventario = true;
										}
									}
									
									if(isMovingOggettiInventario == true){
										deltaXInventario = Gdx.input.getDeltaX()*Progetto.WIDTH/Gdx.graphics.getWidth();
										moveOggettiInventario();
									}
								}
								else{
									//INSERIRE COSA SUCCEDE SE SI PREME IL BOTTONE
									
								}
							}
							else if(bottomNumber == 3){
								//INSERIRE IL RESTO
								
								if(!isMouseOnCircle){
									for(int i = 0; i < OggettiShop.size() && !isMovingOggettiShop; i++){
										if(mouseYStart < 750){
											if(OggettiShop.get(i).contains(mouseXStart, mouseYStart, 1, 1) > 0){
												isMovingOggettiShop = true;
											}
										}
									}
									
									
									if(isMovingOggettiShop == true){
										deltaYShop = Gdx.input.getDeltaY()*Progetto.HEIGHT/Gdx.graphics.getHeight();
										moveOggettiShop();
										deltaXShop = 0;
									}
									
								
									for(int i = 0; i < selezioneShop.size() && !isMovingShop; i++){
										if(selezioneShop.get(i).contains(mouseXStart, mouseYStart, 1, 1) > 0){
											isMovingShop = true;
										}
									}
									
									if(isMovingShop == true){
										deltaXShop = Gdx.input.getDeltaX()*Progetto.WIDTH/Gdx.graphics.getWidth();
										moveSelezioneShop();
										deltaYShop = 0;
									}
								}
								else{
									//INSERIRE COSA SUCCEDE SE SI PREME IL BOTTONE
								}
							}
							else if(bottomNumber == 4){
								Progetto.setLife(Progetto.getMaxLife());
								Progetto.setLifeActual(Progetto.getMaxLife() - Character.getHpIncrement());
								timeToRefill = Progetto.getMaxLife() - Progetto.getLife()*1;
							}
							else if(bottomNumber == 2){
								
								if(PulsanteSell.contains(mouseXStart, mouseYStart, 1, 1) > 0 && runeSelected > 4){
									mouseXStart = -1;
									mouseYStart = -1;
									isActive = false;
									gsm.push(new SellState(gsm, this, runeSelected));
								}
								
								if(!hasClickedAvanti && pageRunes < maxPageRunes){
									if(FrecciaDestra.contains(mouseXStart, mouseYStart, 1, 1)>=1){
										SpostaPagina(1);
									}
								}
								
								if(!hasClickedIndietro && pageRunes > 1){
									if(FrecciaSinistra.contains(mouseXStart, mouseYStart, 1, 1)>=1){
										SpostaPagina(-1);
									}
								}
								
								if(!hasClickedSort){
									if(PulsanteSort.contains(mouseXStart, mouseYStart, 1, 1)>=1){
										SortRunes2(sortCount);
										sortCount++;
										if(sortCount > 2){
											sortCount = 0;
										}
										hasClickedSort = true;
									}
								}
								
								for(int i = 0; i < RuneInventario.size() && !hasToSwap; i++){
									
									
									if(RuneInventario.get(i).contains(mouseXStart, mouseYStart, 1, 1) >= 1){
										
										if(!hasClickedRunes){
											runeSelected  = i;
											RunaDescrizione = new Runes(RuneInventario.get(i).getType(),
																		300,
																		790,
																		RuneInventario.get(i).getElement(),
																		RuneInventario.get(i).getLevel());
											hasClickedRunes = true;
										}
										
										if(RuneInventario.get(i).contains(mouse.x, mouse.y, 1, 1) >= 1){
											holdTimer+=Gdx.graphics.getDeltaTime();
										}
										else{
											holdTimer = 0;
										}
									}
									
								}							
								
								if(holdTimer >= 0.25f){
									for(int i = 0; i < RuneInventario.size() && !hasToSwap; i++){
										
										if(RuneInventario.get(i).contains(mouse.x, mouse.y, 1, 1) >= 1){
											holdStartPositionX = RuneInventario.get(i).getx();
											holdStartPositionY = RuneInventario.get(i).gety();
											swapFirstIndex = i;
											holdStartPage = pageRunes;
											hasToSwap = true;
										}
									}
									
									if(hasToSwap){
										RuneInventario.get(swapFirstIndex).setX(mouse.x);
										RuneInventario.get(swapFirstIndex).setY(mouse.y);
									}
									
								}
								
								if(hasToSwap){
									if(FrecciaDestra.contains(mouse.x, mouse.y, 1, 1) > 0){
										pageTimerDestra += Gdx.graphics.getDeltaTime();
										if(pageTimerDestra > 1 && pageRunes < maxPageRunes){
											SpostaPagina(1);
											pageTimerDestra = 0f;
										}
									}
									else{
										pageTimerDestra = 0f;
									}
									
									if(FrecciaSinistra.contains(mouse.x, mouse.y, 1, 1) > 0){
										pageTimerSinistra += Gdx.graphics.getDeltaTime();
										if(pageTimerSinistra > 1 && pageRunes > 1){
											SpostaPagina(-1);
											pageTimerSinistra=0f;
										}
									}
									else{
										pageTimerSinistra = 0f;
									}
								}
								
							}
							
							
							
							
						}
						
					}
					else {
						
						
						if(bottomNumber==0){
							if(deltaXInventario!=0){
								hasPassedOnce = true;
							}
							if(isMouseOnCircle && !hasPassedOnce){
								
								//TODO PREMI OGGETTO INVENTARIO
								gsm.set(new TransitionState(gsm, this, new TileSetState(gsm,this), Transition.LOST));
								hasPassedOnce = true;
							}
						}
						else if(bottomNumber == 4){
							if(deltaXShop!=0 || deltaYShop!=0){
								hasPassedOnce = true;
							}
							if(isMouseOnCircle && !hasPassedOnce){
								
								//TODO PREMI OGGETTO SHOP
								
								hasPassedOnce = true;
							}
						}
						else if(bottomNumber == 2){
							if(hasToSwap){
								int count = 0;
								for(int i = 0; i < RuneInventario.size(); i++){
									if(i != swapFirstIndex){
										if(RuneInventario.get(i).contains(mouse.x, mouse.y, 1, 1) >= 1){
											count++;
											swapSecondIndex = i;
											if(holdStartPage == pageRunes || swapFirstIndex < 5){
												SwapRunes(RuneInventario.get(swapFirstIndex), holdStartPositionX, holdStartPositionY, RuneInventario.get(swapSecondIndex));
												getEffectFromRunes();
											}
											else if(holdStartPage > pageRunes && swapFirstIndex >= 5){
												SwapRunes(RuneInventario.get(swapFirstIndex), holdStartPositionX + 1000, holdStartPositionY, RuneInventario.get(swapSecondIndex));
											}
											else if(holdStartPage < pageRunes && swapFirstIndex >= 5){
												SwapRunes(RuneInventario.get(swapFirstIndex), holdStartPositionX - 1000, holdStartPositionY, RuneInventario.get(swapSecondIndex));
											}
											isSorted = false;
											runeSelected = swapSecondIndex;
											
										}
									}
								}
								if(count == 0){
									if(holdStartPage > pageRunes && swapFirstIndex >= 5){
										holdStartPositionX += 1000;
									}
									else if(holdStartPage < pageRunes && swapFirstIndex >= 5){
										holdStartPositionX -= 1000;
									}
									RuneInventario.get(swapFirstIndex).setX(holdStartPositionX);
									RuneInventario.get(swapFirstIndex).setY(holdStartPositionY);
								}
								swapFirstIndex = -1;
								swapSecondIndex = -1;
								holdTimer = 0;
								hasToSwap = false;
								holdStartPositionX = -1;
								holdStartPositionY = -1;
								
							}
							hasClickedSort = false;
							hasClickedRunes = false;
							hasClickedAvanti =  false;
							hasClickedIndietro =  false;
						}
						isMovingShop = false;
						isMovingOggettiShop = false;
						isMovingOggettiInventario = false;
						mouseXStart = -1;
						mouseYStart = -1;
						
						
						moveOggettiInventario();
						moveOggettiShop();
						moveSelezioneShop();
						
						if(deltaXInventario > 0){
							deltaXInventario -= 0.5f;
						}
						if(deltaXInventario < 0){
							deltaXInventario += 0.5f;
						}
						if(deltaXShop > 0){
							deltaXShop -= 0.5f;
						}
						if(deltaXShop < 0){
							deltaXShop += 0.5f;
						}
						if(deltaYShop > 0){
							deltaYShop -= 0.5f;
						}
						if(deltaYShop < 0){
							deltaYShop += 0.5f;
						}
					
					}
					
					
				}
				
				if(bottomHasToClose){
					deltaXInventario = 0;
					deltaXShop = 0;
					deltaYShop = 0;
					
					mouseXStart = -1;
					mouseYStart = -1;
				}
				
			}
		
		
	}
	
	public void moveOggettiInventario(){
		boolean hasToSet = false;
		for(int i = 0; i < OggettiInventario.size(); i++){
			OggettiInventario.get(i).setX(OggettiInventario.get(i).getx() + deltaXInventario);
			if(OggettiInventario.get(i).getx() > 70 + i*116){
				OggettiInventario.get(i).setX( 70 + i*116);
				hasToSet = true;
			}
			if(OggettiInventario.get(i).getx() < 70 - 116*(OggettiInventario.size() - 6 - i)){
				OggettiInventario.get(i).setX(70 - 116*(OggettiInventario.size() - 6 - i));
				hasToSet = true;
			}
		}
		if(hasToSet){
			deltaXInventario=0;
		}
	}
	
	public void moveOggettiShop(){
		for(int i = 0; i < OggettiShop.size(); i++){
			OggettiShop.get(i).setY(OggettiShop.get(i).gety() - deltaYShop);
			if(OggettiShop.get(i).gety() > -259 + 172*(OggettiShop.size()+1 - i)){
				OggettiShop.get(i).setY( -259 + 172*(OggettiShop.size()+1 - i));
			}
			if(OggettiShop.get(i).gety() < -280 - 172*i + SpostamentoY){
				OggettiShop.get(i).setY( -280 -172*i + SpostamentoY);
			}
			
			if(OggettiShop.get(i).gety() > -280 + 172 + SpostamentoY){
				OggettiShop.get(i).setIsVisible(false);
			}
			else{
				OggettiShop.get(i).setIsVisible(true);
			}
		}
	}
	
	public void moveSelezioneShop(){
		for(int i = 0; i < selezioneShop.size(); i++){
			selezioneShop.get(i).setX(selezioneShop.get(i).getx() + deltaXShop);
			if(selezioneShop.get(i).getx() > Progetto.WIDTH/2 -288 + 144*i){
				selezioneShop.get(i).setX(Progetto.WIDTH/2 -288 + 144*i);
			}
			if(selezioneShop.get(i).getx() < Progetto.WIDTH/2 -288 - 144*(selezioneShop.size() - 5 -i)){
				selezioneShop.get(i).setX(Progetto.WIDTH/2 -288 - 144*(selezioneShop.size()- 5 - i));
			}
		}
	}
	
	public void MoveLeft(float value) {
		float levelSpeed = 0;
		maxTimerLevel = 1;
		if(value < 0){
			timerLevel += Gdx.graphics.getDeltaTime();
			if(timerLevel < maxTimerLevel/2){
				float precY = lato.getx();
				lato.setX( -( 210 / ( (maxTimerLevel/2) * (maxTimerLevel/2) ) ) * (timerLevel*timerLevel) + 880);
				levelSpeed = lato.getx() - precY;
			}
			else if( timerLevel < maxTimerLevel ){
				float precY = lato.getx();
				lato.setX( ( (840) / (maxTimerLevel*maxTimerLevel) ) * (timerLevel*timerLevel) - (1680/(maxTimerLevel)) * (timerLevel) + 1300 );
				levelSpeed = lato.getx() - precY;
			}
			else{
				if(timerLevel >= maxTimerLevel){
					float precY = lato.getx();
					lato.setX(Progetto.WIDTH/2 + 100);
					levelSpeed = lato.getx() - precY;
					moveLeftDone = true;
					timerLevel = maxTimerLevel;
				}
			}
			
		}
		else if (value > 0){
			timerLevel -= Gdx.graphics.getDeltaTime();
			if(timerLevel < maxTimerLevel/2 && timerLevel > 0){
				float precY = lato.getx();
				lato.setX( -( 210 / ( (maxTimerLevel/2) * (maxTimerLevel/2) ) ) * (timerLevel*timerLevel) + 880);
				levelSpeed = lato.getx() - precY;
			}
			else if( timerLevel < maxTimerLevel && timerLevel > 0){
				float precY = lato.getx();
				lato.setX( ( (840) / (maxTimerLevel*maxTimerLevel) ) * (timerLevel*timerLevel) - (1680/(maxTimerLevel)) * (timerLevel) + 1300 );
				levelSpeed = lato.getx() - precY;
			}
			else{
				if(timerLevel <= 0){
					float precY = lato.getx();
					lato.setX(Progetto.WIDTH + 160);
					levelSpeed = lato.getx() - precY;
					moveRightDone = true;
					timerLevel = 0;
				}
			}
		}
		
		
		
		
		latoSotto.setX(latoSotto.getx() + levelSpeed);
		BottoneEasy.setX(BottoneEasy.getx() + levelSpeed);
		BottoneNormal.setX(BottoneNormal.getx() + levelSpeed);
		BottoneHard.setX(BottoneHard.getx() + levelSpeed);
		BottoneFight.setX(BottoneFight.getx() + levelSpeed);
		StatSotto.setX(StatSotto.getx() + levelSpeed);
		Monster.setX(Monster.getx() + levelSpeed);
		worldSimbol.setX(worldSimbol.getx() + levelSpeed);
		
		
		
		
		
		for(int i = 0; i < MapPoints.size(); i++){
			MapPoints.get(i).setX(MapPoints.get(i).getx() + levelSpeed*7/12);
		}
		map.setX(map.getx() + levelSpeed*7/12);
		
		if(timerLevel == maxTimerLevel){
			for(int i = 0; i < MapPoints.size(); i++){
				MapPoints.get(i).setX(115);
			}
			map.setX(115);
		}
		
		if(timerLevel == 0){
			for(int i = 0; i < MapPoints.size(); i++){
				MapPoints.get(i).setX(360);
			}
			map.setX(360);
		}
		
	}
	public void SpostaPagina(int value) {
		if(value > 0){
			pageRunes++;
			for(int i = 5 + 9*(pageRunes-1); i < 5 + 9*pageRunes && i - 9 < RuneInventario.size(); i++){
				RuneInventario.get(i - 9).setX(RuneInventario.get(i-9).getx()-1000);
			}
			for(int i = 5 + 9*(pageRunes-1); i < 5 + 9*pageRunes && i < RuneInventario.size(); i++){
				RuneInventario.get(i).setX(RuneInventario.get(i).getx()-1000);
			}
			Page = pageRunes + " / " + maxPageRunes;
			hasClickedAvanti =  true;
		}
		else{
			pageRunes--;
			for(int i = 5 + 9*(pageRunes-1); i < 5 + 9*pageRunes && i + 9 < RuneInventario.size(); i++){
				RuneInventario.get(i + 9).setX(RuneInventario.get(i+9).getx()+1000);
			}
			for(int i = 5 + 9*(pageRunes-1); i < 5 + 9*pageRunes && i < RuneInventario.size(); i++){
				RuneInventario.get(i).setX(RuneInventario.get(i).getx()+1000);
			}
			Page = pageRunes + " / " + maxPageRunes;
			hasClickedIndietro =  true;
		}
		
		
	}
	
	public void SwapRunes(Runes first, float x, float y, Runes second){
		int primo = RuneInventario.indexOf(first);
		int secondo = RuneInventario.indexOf(second);
		float secondX = RuneInventario.get(secondo).getx();
		float secondY = RuneInventario.get(secondo).gety();
		
		RuneInventario.set(primo,second);
		RuneInventario.set(secondo, first);
		RuneInventario.get(primo).setX(x);
		RuneInventario.get(primo).setY(y);
		RuneInventario.get(secondo).setX(secondX);
		RuneInventario.get(secondo).setY(secondY);
		
		RuneStatic.getRuneInventario(primo);
		
		int Primo = RuneStatic.getRuneInventario(primo);
		int Secondo = RuneStatic.getRuneInventario(secondo);
		
		RuneStatic.setRuneInventario(Primo, secondo);
		Progetto.setPreferencesRuneInt("Runa" + Integer.toString(secondo), Primo);
		RuneStatic.setRuneInventario(Secondo, primo);
		Progetto.setPreferencesRuneInt("Runa" + Integer.toString(primo), Secondo);
	}
	
	public void SortRunes2(int value){
		if(value == 0){
			Sorted = "Sorted\nby Level";
		}
		else if(value == 1){
			Sorted = "Sorted\nby Type";
		}
		else {
			Sorted = "Sorted\nby Element";
		}
		QuickSort(5, RuneInventario.size()-1, value);
		isSorted = true;
	}
	
	public void QuickSort(int left, int right, int value){
		int begin = left;
		int end = right;
		Runes pivot;
		
		    // get middle element pivot
		    //= arr[(left + right) / 2]
		
		

		    //improved pivot
		
		int middle = (left + right) / 2;
		int LM;
		int MR;
		int LR;
	    if(value == 0){
		    LM = RuneInventario.get(left).compareLevelTo(RuneInventario.get(middle));
		    MR = RuneInventario.get(middle).compareLevelTo(RuneInventario.get(right));
		    LR = RuneInventario.get(left).compareLevelTo(RuneInventario.get(right));
	    }
	    else if(value == 1){
	    	LM = RuneInventario.get(left).compareTypeTo(RuneInventario.get(middle));
		    MR = RuneInventario.get(middle).compareTypeTo(RuneInventario.get(right));
		    LR = RuneInventario.get(left).compareTypeTo(RuneInventario.get(right));
	    }
	    else{
	    	LM = RuneInventario.get(left).compareElementTo(RuneInventario.get(middle));
		    MR = RuneInventario.get(middle).compareElementTo(RuneInventario.get(right));
		    LR = RuneInventario.get(left).compareElementTo(RuneInventario.get(right));
	    }
	    if (-1 * LM == LR)
	    	pivot = RuneInventario.get(left);
	    else
	        if (MR == -1 * LR)
	            pivot = RuneInventario.get(right);
	        else
	            pivot = RuneInventario.get(middle);
	    do{	
	    	if(value == 0){
	    		while (RuneInventario.get(left).compareLevelTo(pivot) > 0) left++;
	    		while (RuneInventario.get(right).compareLevelTo(pivot) < 0) right--;
	    	}
	    	else if(value == 1){
	    		while (RuneInventario.get(left).compareTypeTo(pivot) > 0) left++;
	    		while (RuneInventario.get(right).compareTypeTo(pivot) < 0) right--;
	    	}
	    	else {
	    		while (RuneInventario.get(left).compareElementTo(pivot) > 0) left++;
	    		while (RuneInventario.get(right).compareElementTo(pivot) < 0) right--;
	    	}
	        

	        if(left <= right){
	        	
	            SwapRunes(RuneInventario.get(right), RuneInventario.get(right).getx(), RuneInventario.get(right).gety(), RuneInventario.get(left));

	            left++;
	            right--;
	        }
	    } while (left <= right);

	    if (left < end) QuickSort(left, end, value);
	    if (begin < right) QuickSort(begin, right, value);
	}


	public void updateMap(float dt){
		if(!bottomIsUp){
			if(map.gety() - deltaY > -80 && map.gety() - deltaY < Progetto.HEIGHT + 80){
				map.setY(map.gety() - deltaY);
				for(int i = 0; i < 10; i++){
					MapPoints.get(i).setY(MapPoints.get(i).gety() - deltaY);
				}
			}
			else if (map.gety() - deltaY > -80){
				map.setY(Progetto.HEIGHT + 80);
				for(int i = 0; i < 10; i++){
					if(i == 9){
						MapPoints.get(i).setY(429 + i*219);
					}
					else{
						MapPoints.get(i).setY(277 + i*219);
					}
				}
				deltaY = 0;
			}
			else{
				map.setY(-80);
				for(int i = 0; i < 10; i++){
					if(i == 9){
						MapPoints.get(i).setY(-1011 + i*219);
					}
					else{
						MapPoints.get(i).setY(-1163 + i*219);
					}
				}
				deltaY = 0;
			}
			if(deltaY > 0){
				deltaY -=0.5f;
			}
			
			if(deltaY < 0){
				deltaY +=0.5f;
			}
			
			if(moveLeft && !moveLeftDone && !moveRight && moveRightDone){
				MoveLeft(-1);
			}
			
			if(moveRight && !moveRightDone && !moveLeft && moveLeftDone){
				MoveLeft(+1);
			}
			
		}
		else {
			if(!bottomHasToClose){
				
				float bottomY = bottom.gety();
				float bottomlimit = 539;
				
				bottomPrecY = bottomY;
				
				if(bottomY < bottomlimit){
					bottomTimer += dt;
					if (bottomY < 66){
						bottomY = (bottomTimer*bottomTimer)*(472)/(timerlimit*timerlimit) -406;
					}  
					else {
						bottomY = ((-473)/(timerlimit*timerlimit))*(bottomTimer*bottomTimer) + (1892/(timerlimit))*(bottomTimer) + (-1353) ;
					}
					
					if ( bottomTimer >= timerlimit*2 || bottomY >= bottomlimit){
						bottomY = bottomlimit;
						bottomTimer = timerlimit*2;
					}
					bottomSpeed = bottomY - bottomPrecY;
					
					bottom.setY(bottomY);
					
					moveBottom(bottomSpeed);
					
					if(bottomY >= bottomlimit){
						bottomSpeed = 0;
						bottomPrecY = bottomY;
					}
					
				}
				
			}
			else{
				
				float bottomY = bottom.gety();
				float bottomlimit = -406;
				
				bottomPrecY = bottomY;
				
				if(bottomY > bottomlimit){
					
					bottomTimer -= dt;
					
					if (bottomY < 66){
						bottomY = (bottomTimer*bottomTimer)*(472)/(timerlimit*timerlimit) -406;
					}
					else {
						bottomY = ((-473)/(timerlimit*timerlimit))*(bottomTimer*bottomTimer) + (1892/(timerlimit))*(bottomTimer) + (-1353) ;
					}
					
					if ( bottomTimer < 0  || bottomY < bottomlimit){
						bottomY = bottomlimit;
						bottomIsUp = false;
						bottomTimer = 0;
					}
					
					bottomSpeed = bottomY - bottomPrecY;
					
					bottom.setY(bottomY);
					
					moveBottom(bottomSpeed);
					
					if(bottomY <= bottomlimit){
						bottomSpeed = 0;
						bottomPrecY = bottomY;
					}
					
				}
				
				if(bottomY == bottomlimit){
						bottomIsUp = false;
						bottomTimer = 0;
						bottomSpeed = 0;
						bottomPrecY = bottomY;
						bottomNumber = -1;
						bottomHasToClose = false;
				}
				
			}
		}
	}
	
	public void checkLevelSelected(){
		
		int incrementHeight = 20;
		float tempX = latoSotto.getx();
		if(levelSelected == 0){
			monsterName = new String("Golem");
		}
		else if(levelSelected == 1){
			incrementHeight = 40;
			monsterName = new String("Tunak");
		}
		else if(levelSelected == 2){
			incrementHeight = 40;
			monsterName = new String("TunakEmp");
		}
		else if(levelSelected == 3){
			monsterName = new String("Golem");
		}
		else if(levelSelected == 4){
		}
		else if(levelSelected == 5){
		}
		else if(levelSelected == 6){
		}
		else{
		}
		
		
		Monster = new Monster(levelSelected,world,1);
		Monster.setX(tempX - Monster.getWidth()/2);
		Monster.setY(Progetto.HEIGHT/2 + incrementHeight);
		
	}
	
	public void setLevelStats(){
		if(levelSelected == 0){
			attackNum = 25 + 3*modeSelected;
			defenseNum = 35 + 3*modeSelected;
			difficultyNum = 15 + 3*modeSelected;
		}
		else{
			attackNum = 25 + 3*modeSelected;
			defenseNum = 35 + 3*modeSelected;
			difficultyNum = 15 + 3*modeSelected;
		}
	}
	
	public void moveBottom(float deltaY){
		
		IconeSpente.setY(IconeSpente.gety() + deltaY);
		IconaStatus.setY(IconaStatus.gety() + deltaY);
		IconaEquip.setY(IconaEquip.gety() + deltaY);
		IconaRune.setY(IconaRune.gety() + deltaY);
		IconaShop.setY(IconaShop.gety() + deltaY);
		IconaOpzioni.setY(IconaOpzioni.gety() + deltaY);
		FrecciaDestra.setY(FrecciaDestra.gety() + deltaY);
		FrecciaSinistra.setY(FrecciaSinistra.gety() + deltaY);
		
		for(int i = 0; i < RunePlaces.size(); i++){
			RunePlaces.get(i).setY(RunePlaces.get(i).gety() + deltaY);
		}
		for(int i = 0; i < RuneInventario.size(); i++){
			RuneInventario.get(i).setY(RuneInventario.get(i).gety() + deltaY);
		}
		RunaDescrizione.setY(RunaDescrizione.gety() + deltaY);
		
		PulsanteSell.setY(PulsanteSell.gety() + deltaY);
		PulsanteSort.setY(PulsanteSort.gety() + deltaY);
		PulsanteUpgrade.setY(PulsanteUpgrade.gety() + deltaY);
		DescrizioneRune.setY(DescrizioneRune.gety() + deltaY);
		OggettoSelezionato.setY(OggettoSelezionato.gety() + deltaY);
		CorniceStatus.setY(CorniceStatus.gety() + deltaY);
		DescrizioneEquip.setY(DescrizioneEquip.gety() + deltaY);
		Cappello.setY(Cappello.gety() + deltaY);
		Giacca.setY(Giacca.gety() + deltaY);
		Guanti.setY(Guanti.gety() + deltaY);
		Pantaloni.setY(Pantaloni.gety() + deltaY);
		Scarpe.setY(Scarpe.gety() + deltaY);
		Anello1.setY(Anello1.gety() + deltaY);
		Anello2.setY(Anello2.gety() + deltaY);
		Bracciale.setY(Bracciale.gety() + deltaY);
		Collana.setY(Collana.gety() + deltaY);
		Talismano.setY(Talismano.gety() + deltaY);
		
		corniceShop.setY(corniceShop.gety() + deltaY);
		
		for(int i = 0; i < 11; i++){
			selezioneShop.get(i).setY(selezioneShop.get(i).gety() + deltaY);
		}
		for(int i = 0; i < 10; i++){
			OggettiShop.get(i).setY(OggettiShop.get(i).gety() + deltaY);
			
		}
		for(int i = 0; i < OggettiInventario.size(); i++){
			OggettiInventario.get(i).setY(OggettiInventario.get(i).gety() + deltaY);
		}
		
		
		
		
		CertaY += deltaY;
		SpostamentoY += deltaY;
			
		
	}
	
	public String getTimeString(float value){
		int hour = (int)(value/3600);
		int minute = (int)((value/60)%60);
		int	second = (int)(value%60);
		String Hour = new String();
		String Minute = new String();
		String Second = new String();
		
		if(hour < 10){
			if(hour == 0){
				Hour = "00:";
			}
			else{
				Hour = "0" + hour + ":";
			}
		}
		else{
			Hour = Integer.toString(hour) + ":";
		}
		if(minute < 10){
			if(minute == 0){
				Minute = "00:";
			}
			else{
				Minute = "0" + minute + ":";
			}
		}
		else{
			Minute = Integer.toString(minute) + ":";
		}
		if(second < 10){
			if(second == 0){
				if(minute == 0){
					if(hour == 0){
						Second = "";
						Minute = "";
						Hour = "";
					}
					else{
						Second = "00";
					}
				}
				else{
					Second = "00";
				}
			}
			else{
				Second = "0" + second;
			}
			
		}
		else{
			Second = Integer.toString(second);
		}
		
		
		return Hour + Minute + Second;
	}
	
	public void getEffectFromRunes(){
		if(swapFirstIndex < 5 || swapSecondIndex < 5){
			Character.setAttackIncrement(0);
			Character.setDefenseIncrement(0);
			Character.setFortuneIncrement(0);
			Character.setHpIncrement(0);
			Character.setLifestealIncrement(0);
			for(int z = 0; z < 5; z++){
				Character.setAttackIncrement(Character.getAttackIncrement() + RuneStatic.getAttack(z));
				Character.setDefenseIncrement(Character.getDefenseIncrement() + RuneStatic.getDefense(z));
				Character.setFortuneIncrement(Character.getFortuneIncrement() + RuneStatic.getFortune(z));
				Character.setHpIncrement(Character.getHpIncrement() + RuneStatic.getHP(z));
				Character.setLifestealIncrement(Character.getLifestealIncrement() + RuneStatic.getLifeSteal(z));
				Progetto.setMaxLife(100);
				Progetto.setLife(Progetto.getLifeActual() + Character.getHpIncrement());
				Progetto.setMaxLife(Progetto.getMaxLife() + Character.getHpIncrement());
			}
			timeToRefill = Progetto.getMaxLife() - Progetto.getLife()*1;
		}
	}
	
	public String getString(int runeSelected){
		String description = "";
		int type = RuneInventario.get(runeSelected).getTypeNum();
		int level = RuneInventario.get(runeSelected).getLevel();
		switch (type){
			case 0: description = "Hp: + " + 5*(level+1) + "\nAttack: +" + 5*(level+1); break;
			case 1: description = "Hp: + " + 2*(level+1) + "\nAttack: +" + 6*(level+1) + "\nLifeSteal: +" + (level+1) + "%" ; break;
			case 2: description = "Hp: + " + 1*(level+1) + "\nAttack: +" + 1*(level+1) + "\nFortune: +" + 1*(level+1); break;
			case 3: description = "Hp: - " + 5*(level+1) + "\nAttack: +" + 10*(level+1); break;
			case 4: description = "Hp: + " + 10*(level+1) + "\nAttack: +" + 1*(level+1) + "\nDefense: +" + 3*(level+1) + "%" ; break;
			default: description = "Hp: + " + 5*(level+1) + "\nAttack: +" + 5*(level+1); break;
			
		}
		return description;
	}


	public void update(float dt) {
		
		if(isActive){
			
			handleInput();
		
			updateMap(dt);
			
		}
		else{
			Arkai = Integer.toString(Progetto.getArkai());
		}
		
		if(Progetto.getLife() != Progetto.getMaxLife()){
			timer+=dt;
			if(timer >= 1){
				int life = Progetto.getLife() + 1;
				if(life < Progetto.getMaxLife()){
					Progetto.setLife(Progetto.getLife()+1);
					Progetto.setLifeActual(Progetto.getLife() - Character.getHpIncrement());
					timer-=1;
				}
				else{
					Progetto.setLife(Progetto.getMaxLife());
					Progetto.setLifeActual(Progetto.getMaxLife() - Character.getHpIncrement());
				}
				
			}
		}
		
		if(timeToRefill > 0){
			timeToRefill-=dt;
			if(timeToRefill < 0){
				timeToRefill = 0;
			}
		}
		
		
		
		
		if(bottom.gety() == 539){
			
			
			
		}
		
	}


	public void render(SpriteBatch sb) {
		
		
		
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		map.render(sb);
		for(int i = 0; i < 10; i++){
			MapPoints.get(i).render(sb);
		}
		latoSotto.render(sb);
		StatSotto.render(sb);
		Monster.render(sb);
		
		
		
		sb.end();
		
		attack.setProjectionMatrix(cam.combined);
		attack.begin(ShapeType.Filled);
		attack.setColor(new Color(46f/255, 25f/255, 0, 1));
		attack.rect(StatSotto.getx() - 80, StatSotto.gety() + 26, attackNum*445/100, 45);
		attack.end();
		
		defense.setProjectionMatrix(cam.combined);
		defense.begin(ShapeType.Filled);
		defense.setColor(new Color(46f/255, 25f/255, 0, 1));
		defense.rect(StatSotto.getx() - 80, StatSotto.gety() - 23, defenseNum*445/100, 45);
		defense.end();
		
		difficulty.setProjectionMatrix(cam.combined);
		difficulty.begin(ShapeType.Filled);
		difficulty.setColor(new Color(46f/255, 25f/255, 0, 1));
		difficulty.rect(StatSotto.getx() - 80, StatSotto.gety() - 72, difficultyNum*445/100, 45);
		difficulty.end();
		
		
		sb.begin();
		
		
		lato.render(sb);
		BottoneFight.render(sb);
		BottoneEasy.render(sb);
		BottoneNormal.render(sb);
		BottoneHard.render(sb);
		worldSimbol.render(sb);
		
		float scale = 1f;
		fontMeta20.setColor(46f/255,25f/255,0f/255,1);
		fontMeta20.getData().setScale(scale);
		layout.setText(fontMeta20, monsterName);
		fontMeta20.draw(sb, monsterName, worldSimbol.getx() - (int)(layout.width/2), worldSimbol.gety() - 40 - (int)(layout.height/2 + 1));
		
		
		
		HpSotto.render(sb);
		
		
		
		sb.end();
		
		life.setProjectionMatrix(cam.combined);
		life.begin(ShapeType.Filled);
		life.setColor(new Color(Color.GREEN));
		if(((Progetto.getLife()*103f/Progetto.getMaxLife())/2f) >= 0){
			life.rect(HpSotto.getx() - (Progetto.getLife()*103f/Progetto.getMaxLife())/2f, HpSotto.gety() - 30/2, Progetto.getLife()*103f/Progetto.getMaxLife(), 30);
		}
		else{
			life.rect(HpSotto.getx() - 0, HpSotto.gety() - 30/2, 0, 30);
		}
		life.end();
		
		sb.begin();
		
		
		Top.render(sb);
			
		
		bottom.render(sb);
		
		
		
		
		IconeSpente.render(sb);
		
		if(bottomNumber == 0){
			CorniceStatus.render(sb);
			IconaStatus.render(sb);
		}
		else if(bottomNumber == 1){
			
			Cappello.render(sb);
			Giacca.render(sb);
			Guanti.render(sb);
			Pantaloni.render(sb);
			Scarpe.render(sb);
			Anello1.render(sb);
			Anello2.render(sb);
			Bracciale.render(sb);
			Collana.render(sb);
			Talismano.render(sb);
			
			for(int i = 0; i < OggettiInventario.size(); i++){
				OggettiInventario.get(i).render(sb);
			}
			OggettoSelezionato.render(sb);
			DescrizioneEquip.render(sb);
			IconaEquip.render(sb);
		}
		else if(bottomNumber == 2){
			IconaRune.render(sb);
			DescrizioneRune.render(sb);
			
			if(pageRunes < maxPageRunes){
				FrecciaDestra.render(sb);
			}
			
			
			for(int i = 0; i < RunePlaces.size(); i++){
				RunePlaces.get(i).render(sb);
			}
			for(int i = 0; i < 5; i++){
				RuneInventario.get(i).render(sb);
			}
			for(int i = 5 + 9*(pageRunes-1); i < RuneInventario.size() && i < 5 + 9*pageRunes; i++){
				RuneInventario.get(i).render(sb);
			}
			RunaDescrizione.render(sb);
			
			if(runeSelected >= 0){
				scale = 1f;
				fontMeta20.setColor(46f/255,25f/255,0f/255,1);
				fontMeta20.getData().setScale(scale);
				layout.setText(fontMeta20, getString(runeSelected));
				fontMeta20.draw(sb, getString(runeSelected), DescrizioneRune.getx() - 70, DescrizioneRune.gety() + 5 + (int)(layout.height/2));
			}
			
			
			if(runeSelected > 4){
				PulsanteSell.render(sb);
				PulsanteUpgrade.render(sb);
				scale = 1f;
				fontMeta20.setColor(46f/255,25f/255,0f/255,1);
				fontMeta20.getData().setScale(scale);
				layout.setText(fontMeta20, Sell);
				fontMeta20.draw(sb, Sell, 626 - (int)(layout.width/2), -225 + CertaY - (int)(layout.height/2 + 1));
				layout.setText(fontMeta20, Upgrade);
				fontMeta20.draw(sb, Upgrade, 470 - (int)(layout.width/2), -225 + CertaY - (int)(layout.height/2 + 1));
			}
			PulsanteSort.render(sb);
			
			scale = 1f;
			fontMeta20.setColor(46f/255,25f/255,0f/255,1);
			fontMeta20.getData().setScale(scale);
			layout.setText(fontMeta20, Sort);
			fontMeta20.draw(sb, Sort, 450 - (int)(layout.width/2), -865 + CertaY - (int)(layout.height/2 + 1));
			if(isSorted){
				layout.setText(fontMeta20, Sorted);
				fontMeta20.draw(sb, Sorted, 300 - (int)(layout.width/2), -835 + CertaY - (int)(layout.height/2 + 1));
			}
			scale = 1f;
			fontMeta54.setColor(46f/255,25f/255,0f/255,1);
			fontMeta54.getData().setScale(scale);
			layout.setText(fontMeta54, Page);
			fontMeta54.draw(sb, Page, 450 - (int)(layout.width/2), -745 + CertaY - (int)(layout.height/2 + 1));
			
			if(pageRunes > 1){
				FrecciaSinistra.render(sb);
			}
			
		}
		else if(bottomNumber == 3){
			
			
			
			for(int i = 0; i < 10; i++){
				if(OggettiShop.get(i).getIsVisible()){
					OggettiShop.get(i).render(sb);
				}
			}
			
			
			
			for(int i = 0; i < 11; i++){
				selezioneShop.get(i).render(sb);
			}
			corniceShop.render(sb);
			
			IconaShop.render(sb);
		}
		else if(bottomNumber == 4){
			IconaOpzioni.render(sb);
		}
		
		
		//OggettoShop.render(sb);
		
		scale = 1f;
		font64.setColor(251f/255,227f/255,177f/255,1);
		font64.getData().setScale(scale);
		layout.setText(font64, Level);
		font64.draw(sb, Level, Progetto.WIDTH/2 - layout.width/2 , Progetto.HEIGHT - 18 - layout.height/2);
		scale = 1f;
		font.setColor(251f/255,227f/255,177f/255,1);
		font.getData().setScale(scale);
		layout.setText(font, Etere);
		font.draw(sb, Etere, Progetto.WIDTH/6 + 43 - layout.width, Progetto.HEIGHT - 39 - layout.height/2);
		scale = 1f;
		font.getData().setScale(scale);
		layout.setText(font, Arkai);
		font.draw(sb, Arkai, Progetto.WIDTH/6 + 90 - layout.width, Progetto.HEIGHT - 5 - layout.height/2);
		scale = 1f;
		font.setColor(46f/255,25f/255,0f/255,1);
		font.getData().setScale(scale);
		layout.setText(font, getTimeString(timeToRefill));
		font.draw(sb, getTimeString(timeToRefill), Progetto.WIDTH - 20 - layout.width, Progetto.HEIGHT - 61 - layout.height/2);
		
		if(bottomNumber == 0){
			int offset1 = -43;
			int offset2 = 0;
			int distance = 86;
			
			scale = 1f;
			fontMeta26.setColor(46f/255,25f/255,0f/255,1);
			fontMeta26.getData().setScale(scale);
			layout.setText(fontMeta26, Integer.toString(Progetto.getLevelRaiden()));
			fontMeta26.draw(sb, Integer.toString(Progetto.getLevelRaiden()), Progetto.WIDTH - 50 - layout.width, offset1 - distance - layout.height/2 + CertaY);
			layout.setText(fontMeta26, Integer.toString(Progetto.getExpRaiden() + (Progetto.getLevelRaiden()-1)*5));
			fontMeta26.draw(sb, Integer.toString(Progetto.getExpRaiden() + (Progetto.getLevelRaiden()-1)*5), Progetto.WIDTH - 50 - layout.width, offset1 - distance*2 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, Progetto.getExpRaiden() + "/" + Progetto.getLevelRaiden()*5);
			fontMeta26.draw(sb, Progetto.getExpRaiden() + "/" + Progetto.getLevelRaiden()*5, Progetto.WIDTH - 50 - layout.width, offset1 - distance*3 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, Integer.toString(Character.getAttack()));
			fontMeta26.draw(sb, Integer.toString(Character.getAttack()), Progetto.WIDTH - 50 - layout.width, offset1 - distance*4 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, Integer.toString(Progetto.getLife()) + "/" + Integer.toString(Character.getHP()));
			fontMeta26.draw(sb, Integer.toString(Progetto.getLife()) + "/" + Integer.toString(Character.getHP()), Progetto.WIDTH - 50 - layout.width, offset1 - distance*5 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, Integer.toString((int)(Character.getLifesteal()*100))+"%");
			fontMeta26.draw(sb, Integer.toString((int)(Character.getLifesteal()*100))+"%", Progetto.WIDTH - 50 - layout.width, offset1 - distance*6 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, Integer.toString(Character.getFortune()));
			fontMeta26.draw(sb, Integer.toString(Character.getFortune()), Progetto.WIDTH - 50 - layout.width, offset1 - distance*7 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, Integer.toString((int)(Character.getDefense()*100))+"%");
			fontMeta26.draw(sb, Integer.toString((int)(Character.getDefense()*100))+"%", Progetto.WIDTH - 50 - layout.width, offset1 - distance*8 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, Integer.toString(Progetto.getMonstersKilled()));
			fontMeta26.draw(sb, Integer.toString(Progetto.getMonstersKilled()), Progetto.WIDTH - 50 - layout.width, offset1 - distance*9 - layout.height/2 + CertaY);
			
			
			layout.setText(fontMeta26, "Level:");
			fontMeta26.draw(sb, "Level:", Progetto.WIDTH/2 , offset2 - distance - layout.height/2 + CertaY);
			layout.setText(fontMeta26, "Experience:");
			fontMeta26.draw(sb, "Experience:", Progetto.WIDTH/2 , offset2 - distance*2 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, "To Next Level:");
			fontMeta26.draw(sb, "To Next Level:", Progetto.WIDTH/2 , offset2 - distance*3 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, "Attack:");
			fontMeta26.draw(sb, "Attack:", Progetto.WIDTH/2 , offset2 - distance*4 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, "Health Points:");
			fontMeta26.draw(sb, "Health Points:", Progetto.WIDTH/2 , offset2 - distance*5 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, "LifeSteal:");
			fontMeta26.draw(sb, "LifeSteal:", Progetto.WIDTH/2, offset2 - distance*6 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, "Luck:");
			fontMeta26.draw(sb, "Luck:", Progetto.WIDTH/2 , offset2 - distance*7 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, "Defense:");
			fontMeta26.draw(sb, "Defense:", Progetto.WIDTH/2 , offset2 - distance*8 - layout.height/2 + CertaY);
			layout.setText(fontMeta26, "Monsters Killed:");
			fontMeta26.draw(sb, "Monsters Killed:", Progetto.WIDTH/2 , offset2 - distance*9 - layout.height/2 + CertaY);
			
		}
		
		if(swapFirstIndex!=-1 && RuneInventario.size() > swapFirstIndex){
			RuneInventario.get(swapFirstIndex).render(sb);
		}
		
		sb.end();
	}
	
	public boolean getIsActive(){
		return isActive;
	}
	public void setIsActive(boolean active){
		isActive = active;
	}

}
