package com.pet.progetto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pet.progetto.states.StartState;
import com.pet.progetto.handler.Content;
import com.pet.progetto.prefences.OggettiStatic;
import com.pet.progetto.prefences.RuneStatic;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Character;

public class Progetto extends ApplicationAdapter implements InputProcessor{
	
	public static final String TITLE = "gioco";
	public static final int WIDTH = 720;
	public static final int HEIGHT = 1280;
	public static final int SIZE = 1;
	
	
	public static Content res;
	
	private SpriteBatch sb;
	private GSM gsm;

	public Viewport viewport;
	

	private OrthographicCamera cam;
	
	
	public static Preferences pref;
	public static Preferences prefRunes;
	public static Preferences prefOggetti;
	public static boolean backPressed;
	public static int arkai;
	public static int etere;
	public static int maxLevel;
	public static int levelRaiden;
	public static int expRaiden;
	public static int life;
	public static int lifeActual;
	private static float defense;
	private static int attack;
	private static float lifeSteal;
	private static int fortune; 
	public static int maxLife;
	public static int isFirstTime;
	public static int monstersKilled;
	
	@Override
	public void create () {
		

		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		//catch back button
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
		
		res = new Content();
		res.loadAtlas("Images/pack.atlas", "pack");
		res.loadAtlas("Images/pack2.atlas", "pack2");
		res.loadAtlas("Images/pack3.atlas", "pack3");
		res.loadAtlas("Images/pack4.atlas", "pack4");
		res.loadAtlas("Images/pack5.atlas", "pack5");
		res.loadAtlas("Images/pack6.atlas", "pack6");
		res.loadAtlas("Images/pack7.atlas", "pack7");
		res.loadAtlas("Images/pack8.atlas", "pack8");
		
		sb = new SpriteBatch();
		
		//back = new Graphic(progetto.res.getAtlas("pack").findRegion("GameScreen"), x, y, width, height);
		
		cam = new OrthographicCamera();
		
		viewport = new FitViewport(Progetto.WIDTH,Progetto.HEIGHT,cam);
		viewport.apply();
		cam.setToOrtho(false, Progetto.WIDTH, Progetto.HEIGHT);
		gsm = new GSM(viewport);
		gsm.push(new StartState(gsm));
		
		
		pref = Gdx.app.getPreferences("MyPreferences");
		//TODO MONETE E ESPERIENZA
		prefRunes = Gdx.app.getPreferences("MyPreferencesRunes");
		prefOggetti = Gdx.app.getPreferences("MyPreferencesOggetti");
		
		//clearPrefenceRune();
		
		
		if(Progetto.getPreferencesInt("maxLevel")==0){Progetto.setMaxLevel(1);}
		else{Progetto.setMaxLevel(Progetto.getPreferencesInt("maxLevel"));}
		if(Progetto.getPreferencesInt("Arkai")==0){Progetto.setArkai(0);}
		else{Progetto.setArkai(Progetto.getPreferencesInt("Arkai"));}
		if(Progetto.getPreferencesInt("Etere")==0){Progetto.setEtere(0);}
		else{Progetto.setEtere(Progetto.getPreferencesInt("Etere"));}
		if(Progetto.getPreferencesInt("Life")==0){Progetto.setLife(0);}
		else{Progetto.setLife(Progetto.getPreferencesInt("Life"));}
		if(Progetto.getPreferencesInt("Attack")==0){Character.setAttack(1);}
		else{Character.setAttack(Progetto.getPreferencesInt("Attack"));}
		if(Progetto.getPreferencesFloat("Defense")==0){Character.setDefense(0);}
		else{Character.setDefense(Progetto.getPreferencesFloat("Defense"));}
		if(Progetto.getPreferencesFloat("LifeSteal")==0){Character.setLifeSteal(0);}
		else{Character.setLifeSteal(Progetto.getPreferencesFloat("LifeSteal"));}
		if(Progetto.getPreferencesInt("Fortune")==0){Character.setFortune(0);}
		else{Character.setFortune(Progetto.getPreferencesInt("Fortune"));}
		if(Progetto.getPreferencesInt("maxLife")==0){Progetto.setMaxLife(100);}
		else{Progetto.setMaxLife(Progetto.getPreferencesInt("maxLife"));}
		if(Progetto.getPreferencesInt("levelRaiden")==0){Progetto.setLevelRaiden(1);}
		else{Progetto.setLevelRaiden(Progetto.getPreferencesInt("levelRaiden"));}
		if(Progetto.getPreferencesInt("expRaiden")==0){Progetto.setExpRaiden(0);}
		else{Progetto.setMonstersKilled(Progetto.getPreferencesInt("expRaiden"));}
		if(Progetto.getPreferencesInt("monstersKilled")==0){Progetto.setMonstersKilled(0);}
		else{Progetto.setExpRaiden(Progetto.getPreferencesInt("monstersKilled"));}
		if(Progetto.getPreferencesInt("isFirstTime")==0){Progetto.setIsFirstTime(1);Progetto.setLife(100);Progetto.setMaxLife(100);}
		else{Progetto.setIsFirstTime(Progetto.getPreferencesInt("isFirstTime"));}
		
		Object[] keysInPref;
		keysInPref = prefRunes.get().keySet().toArray();
		if(keysInPref.length >= 5){
			for(int i = 0; i < keysInPref.length; i++ ){
				RuneStatic.RuneInventario.add(Progetto.getPreferencesRuneInt("Runa" + Integer.toString(i)));
			}
		}
		else{
			for(int i = 0; i < 5; i++ ){
				RuneStatic.addRuneInventario(15);
				Progetto.setPreferencesRuneInt("Runa" + i, 15);
			}
		}
		
		Object[] keysInPref2;
		keysInPref2 = prefOggetti.get().keySet().toArray();
		if(keysInPref2.length > 0){
			for(int i = 0; i < keysInPref2.length; i++ ){
				OggettiStatic.OggettiInventario.add(Progetto.getPreferencesOggettiInt("Oggetto" + Integer.toString(i)));
			}
		}
		
		
		Character.setAttackIncrement(0);
		Character.setDefenseIncrement(0);
		Character.setFortuneIncrement(0);
		Character.setHpIncrement(0);
		Character.setLifestealIncrement(0);
		//RUNE EFFECT
		for(int i = 0; i < 5; i++){
			Character.setAttackIncrement(Character.getAttackIncrement() + RuneStatic.getAttack(i));
			Character.setDefenseIncrement(Character.getDefenseIncrement() + RuneStatic.getDefense(i));
			Character.setFortuneIncrement(Character.getFortuneIncrement() + RuneStatic.getFortune(i));
			Character.setHpIncrement(Character.getHpIncrement() + RuneStatic.getHP(i));
			Character.setLifestealIncrement(Character.getLifestealIncrement() + RuneStatic.getLifeSteal(i));
		}
		
		if(Progetto.getPreferencesInt("LifeActual")==(-Character.getHpIncrement())){Progetto.setLifeActual(-Character.getHpIncrement());}
		else{Progetto.setLifeActual(Progetto.getPreferencesInt("LifeActual"));}
		
		//TODO SCALARE CON LIVELLO
		Progetto.setMaxLife(100);
		Progetto.setLife(Progetto.getLifeActual() + Character.getHpIncrement());
		Progetto.setMaxLife(Progetto.getMaxLife() + Character.getHpIncrement());
		
		
		//OBJECT EFFECT
		for(int i = 0; i < 10; i++){
			
		}
		
		
		
	}
	
	public static int getPreferencesInt(String key){
		return pref.getInteger(key);
	}
	
	public static void setPreferencesInt(String key, int value){
		pref.remove(key);
		pref.putInteger(key, value);
		pref.flush();
	}
	public static float getPreferencesFloat(String key){
		return pref.getFloat(key);
	}
	
	public static void setPreferencesFloat(String key, float value){
		pref.remove(key);
		pref.putFloat(key, value);
		pref.flush();
	}
	
	public static void clearPrefence(){
		Object[] keysInPref;
		keysInPref = pref.get().keySet().toArray();
		if(keysInPref.length >= 5){
			for(int i = 0; i < keysInPref.length; i++ ){
				pref.remove(keysInPref[i].toString());
			}
		}
		pref.flush();
	}
	
	public static int getPreferencesRuneInt(String key){
		return prefRunes.getInteger(key);
	}
	
	public static void setPreferencesRuneInt(String key, int value){
		prefRunes.remove(key);
		prefRunes.putInteger(key, value);
		prefRunes.flush();
	}
	
	public static void clearPrefenceRune(){
		Object[] keysInPref;
		keysInPref = prefRunes.get().keySet().toArray();
		if(keysInPref.length >= 5){
			for(int i = 0; i < keysInPref.length; i++ ){
				prefRunes.remove("Runa" + i);
			}
		}
		prefRunes.flush();
	}
	
	public static int getPreferencesOggettiInt(String key){
		return prefOggetti.getInteger(key);
	}
	
	public static void setPreferencesOggettiInt(String key, int value){
		prefOggetti.remove(key);
		prefOggetti.putInteger(key, value);
		prefOggetti.flush();
	}
	
	public static void clearPrefenceOggetti(){
		Object[] keysInPref;
		keysInPref = prefOggetti.get().keySet().toArray();
		if(keysInPref.length >= 5){
			for(int i = 0; i < keysInPref.length; i++ ){
				prefOggetti.remove("Oggetto" + i);
			}
		}
		prefOggetti.flush();
	}
	
	public static boolean getBackPressed(){return backPressed;}
	public static void setBackPressed(boolean value){backPressed = value;}
	
	public static int getArkai(){return Progetto.arkai;}
	public static void setArkai(int value){Progetto.arkai = value; Progetto.setPreferencesInt("Arkai", value);}
	public static int getEtere(){return Progetto.etere;}
	public static void setEtere(int value){Progetto.etere = value; Progetto.setPreferencesInt("Etere", value);}
	public static int getMaxLevel(){return Progetto.maxLevel;}
	public static void setMaxLevel(int value){Progetto.maxLevel = value; Progetto.setPreferencesInt("maxLevel", value);}
	public static int getLife(){return Progetto.life;}
	public static void setLife(int value){Progetto.life = value; Progetto.setPreferencesInt("Life", value);}
	public static int getLifeActual(){return Progetto.lifeActual;}
	public static void setLifeActual(int value){Progetto.lifeActual = value; Progetto.setPreferencesInt("LifeActual", value);}
	public static int getMaxLife(){return Progetto.maxLife;}
	public static void setMaxLife(int value){Progetto.maxLife = value; Progetto.setPreferencesInt("maxLife", value);}
	public static int getLevelRaiden(){return Progetto.levelRaiden;}
	public static void setLevelRaiden(int value){Progetto.levelRaiden = value; Progetto.setPreferencesInt("levelRaiden", value);}
	public static int getExpRaiden(){return Progetto.expRaiden;}
	public static void setExpRaiden(int value){Progetto.expRaiden = value; Progetto.setPreferencesInt("expRaiden", value);}
	public static int getIsFirstTime(){return Progetto.isFirstTime;}
	public static void setIsFirstTime(int value){Progetto.isFirstTime = value; Progetto.setPreferencesInt("isFirstTime", value);}
	public static int getAttack(){return Progetto.attack;}
	public static void setAttack(int value){Progetto.attack = value; Progetto.setPreferencesInt("Attack", value);}
	public static float getDefense(){return Progetto.defense;}
	public static void setDefense(float value){Progetto.defense = value; Progetto.setPreferencesFloat("Defense", value);}
	public static float getLifeSteal(){return Progetto.lifeSteal;}
	public static void setLifeSteal(float value){Progetto.lifeSteal = value; Progetto.setPreferencesFloat("LifeSteal", value);}
	public static int getFortune(){return Progetto.fortune;}
	public static void setFortune(int value){Progetto.fortune = value; Progetto.setPreferencesInt("Fortune", value);}
	public static int getMonstersKilled(){return Progetto.monstersKilled;}
	public static void setMonstersKilled(int value){Progetto.monstersKilled = value; Progetto.setPreferencesInt("monstersKilled", value);}
	
	

	public void update(float dt){
		
	}
	
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		//back.render(sb);
		update(Gdx.graphics.getDeltaTime());
		sb.end();
	
	
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
	
	@Override
	public boolean keyDown(int keycode) {
			if(keycode == Keys.BACK) {
	            Progetto.setBackPressed(true);
	        }
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.BACK) {
			Progetto.setBackPressed(false);
        }
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
		cam.setToOrtho(false, Progetto.WIDTH, Progetto.HEIGHT);
		
	}
	
	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
	
}
	
