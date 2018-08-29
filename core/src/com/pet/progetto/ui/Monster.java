package com.pet.progetto.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pet.progetto.Progetto;
import com.pet.progetto.animations.TunakTail;

public class Monster extends Box{
	
	private final int FRAME_COLS = 5;
	
	private Animation animation1;
	private Animation animation2;
	private TextureRegion sheet;
	private TextureRegion[] SpriteSheet1;
	private TextureRegion[] SpriteSheet2;
	private TextureRegion currentFrame;
	private float timerFrame = 0.12f;
	private float timer = 0;
	
	private int hp;
	private int maxHp;
	private int stamina;
	private int maxStamina;
	private int exp;
	private int expRune;
	private int arkai;
	private float defense;
	private int attack;
	private float lifesteal;
	private int rechargeStamina;
	
	private boolean isTunak;
	private TunakTail tail;
	
	public Monster(int monster, int world, int difficulty){
		
		if(monster == 0 && world == 0){
			
			hp = 1000 + 100*difficulty;
			maxHp = hp;
			stamina = 500 + 50*difficulty;
			maxStamina = stamina;
			exp = 1 + 1*difficulty;
			expRune = 1 + 1*difficulty;
			arkai = 1 + 1*difficulty;
			defense = 0.01f*difficulty;
			attack = 1 + 1*difficulty;
			lifesteal = 0;
			rechargeStamina = 5 + 0*difficulty;
			
			sheet = Progetto.res.getAtlas("pack6").findRegion("Golemterrasprite");
		}
		else if(monster == 1 && world == 0){
			hp = 1000 + 100*difficulty;
			maxHp = hp;
			stamina = 500 + 50*difficulty;
			maxStamina = stamina;
			exp = 1 + 1*difficulty;
			expRune = 1 + 1*difficulty;
			arkai = 1 + 1*difficulty;
			defense = 0.01f*difficulty;
			attack = 1 + 1*difficulty;
			lifesteal = 0;
			rechargeStamina = 5 + 0*difficulty;
			
			sheet = Progetto.res.getAtlas("pack6").findRegion("TunakSprite");
			isTunak = true;
			tail = new TunakTail(false);
			
		}
		else if(monster == 2 && world == 0){
			hp = 1000 + 100*difficulty;
			maxHp = hp;
			stamina = 500 + 50*difficulty;
			maxStamina = stamina;
			exp = 1 + 1*difficulty;
			expRune = 1 + 1*difficulty;
			arkai = 1 + 1*difficulty;
			defense = 0.01f*difficulty;
			attack = 1 + 1*difficulty;
			lifesteal = 0;
			rechargeStamina = 5 + 0*difficulty;
			
			sheet = Progetto.res.getAtlas("pack6").findRegion("TunakEmpSprite");
			isTunak = true;
			tail = new TunakTail(true);
			
		}
		else if(monster == 3 && world == 0){
			hp = 1000 + 100*difficulty;
			maxHp = hp;
			stamina = 500 + 50*difficulty;
			maxStamina = stamina;
			exp = 1 + 1*difficulty;
			expRune = 1 + 1*difficulty;
			arkai = 1 + 1*difficulty;
			defense = 0.01f*difficulty;
			attack = 1 + 1*difficulty;
			lifesteal = 0;
			rechargeStamina = 5 + 0*difficulty;
			
			sheet = Progetto.res.getAtlas("pack6").findRegion("Golemacquasprite");
			
		}
		else if(monster == 4 && world == 0){
			hp = 1000 + 100*difficulty;
			maxHp = hp;
			stamina = 500 + 50*difficulty;
			maxStamina = stamina;
			exp = 1 + 1*difficulty;
			expRune = 1 + 1*difficulty;
			arkai = 1 + 1*difficulty;
			defense = 0.01f*difficulty;
			attack = 1 + 1*difficulty;
			lifesteal = 0;
			rechargeStamina = 5 + 0*difficulty;
			
			sheet = Progetto.res.getAtlas("pack6").findRegion("Golemariasprite");
			
		}
		else if(monster == 5 && world == 0){
			hp = 1000 + 100*difficulty;
			maxHp = hp;
			stamina = 500 + 50*difficulty;
			maxStamina = stamina;
			exp = 1 + 1*difficulty;
			expRune = 1 + 1*difficulty;
			arkai = 1 + 1*difficulty;
			defense = 0.01f*difficulty;
			attack = 1 + 1*difficulty;
			lifesteal = 0;
			rechargeStamina = 5 + 0*difficulty;
			
			sheet = Progetto.res.getAtlas("pack6").findRegion("Golemfuocosprite");
			
		}
		else if(monster == 6 && world == 0){
			hp = 1000 + 100*difficulty;
			maxHp = hp;
			stamina = 500 + 50*difficulty;
			maxStamina = stamina;
			exp = 1 + 1*difficulty;
			expRune = 1 + 1*difficulty;
			arkai = 1 + 1*difficulty;
			defense = 0.01f*difficulty;
			attack = 1 + 1*difficulty;
			lifesteal = 0;
			rechargeStamina = 5 + 0*difficulty;
			
			sheet = Progetto.res.getAtlas("pack6").findRegion("Golemtemposprite");
			
		}
		else {
			hp = 1000 + 100*difficulty;
			maxHp = hp;
			stamina = 500 + 50*difficulty;
			maxStamina = stamina;
			exp = 1 + 1*difficulty;
			expRune = 1 + 1*difficulty;
			arkai = 1 + 1*difficulty;
			defense = 0.01f*difficulty;
			attack = 1 + 1*difficulty;
			lifesteal = 0;
			rechargeStamina = 5 + 0*difficulty;
			sheet = Progetto.res.getAtlas("pack6").findRegion("Golemterrasprite");
		}
			
			
			width = sheet.getRegionWidth()/FRAME_COLS;
			height = sheet.getRegionHeight()/2;
			
			TextureRegion[][] temp = sheet.split((int)width, (int)height);
			SpriteSheet1 = new TextureRegion[FRAME_COLS];
			SpriteSheet2 = new TextureRegion[FRAME_COLS];
			int index = 0;
			for (int i = 0; i < FRAME_COLS; i++) {
				SpriteSheet1[index++] = temp[0][i];
			}
			index = 0;
			for (int j = 0; j < FRAME_COLS; j++) {
				SpriteSheet2[index++] = temp[1][j];
	        }
			
			
	        animation1 = new Animation(timerFrame, SpriteSheet1);
	        animation2 = new Animation(timerFrame, SpriteSheet2);
	        
	        this.x = Progetto.WIDTH/2 - width/2;
	        if(monster != 1 && monster != 2){
	        	this.y = (int)(Progetto.HEIGHT - height - 10);
	        }
	        else{
	        	this.y = (int)(Progetto.HEIGHT*0.730f);
	        }
	        
	        if(isTunak){
	        	tail.setX(x + 225 - tail.width/2);
	        	tail.setY(y + 290 - tail.height/2);
	        }
	        
	}
	
	public void setX(float x){
		this.x = x;
		if(isTunak){
			tail.setX(this.x + 225 - tail.width/2);
		}
	}
	
	public void setY(float y){
		this.y = y;
		if(isTunak){
			tail.setY(this.y + 290 - tail.height/2);
		}
	}
	
	public void render(SpriteBatch sb) {
		
		if(isTunak){
			tail.render(sb);
		}
		float stay = 0.23f;
		
		timer += Gdx.graphics.getDeltaTime();
		if(timer >= stay){
			if(!animation1.isAnimationFinished(timer - stay)){
				currentFrame = animation1.getKeyFrame(timer - stay,true);
			}
			else if(timer > (float)(timerFrame*FRAME_COLS) + stay){
				if(!animation2.isAnimationFinished(timer - (float)(timerFrame*FRAME_COLS) - stay)){
						currentFrame = animation2.getKeyFrame(timer - (float)(timerFrame*FRAME_COLS) - stay,true);
				}
				else{
					timer = 0;
				}
			}
		}
		else{
			currentFrame = animation1.getKeyFrame(0,true);
		}
		sb.draw(currentFrame, x, y, width, height);
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpRune() {
		return expRune;
	}

	public void setExpRune(int expRune) {
		this.expRune = expRune;
	}

	public int getArkai() {
		return arkai;
	}

	public void setArkai(int arkai) {
		this.arkai = arkai;
	}

	public float getDefense() {
		return defense;
	}

	public void setDefense(float defense) {
		this.defense = defense;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public float getLifesteal() {
		return lifesteal;
	}

	public void setLifesteal(float lifesteal) {
		this.lifesteal = lifesteal;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}

	public int getRechargeStamina() {
		return rechargeStamina;
	}

	public void setRechargeStamina(int rechargeStamina) {
		this.rechargeStamina = rechargeStamina;
	}

}
