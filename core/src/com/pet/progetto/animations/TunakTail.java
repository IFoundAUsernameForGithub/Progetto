package com.pet.progetto.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pet.progetto.Progetto;
import com.pet.progetto.ui.Box;

public class TunakTail extends Box{
private final int FRAME_COLS = 8;
	
	private Animation animation1;
	private TextureRegion sheet;
	private TextureRegion[] SpriteSheet;
	private TextureRegion currentFrame;
	private float timerFrame = 0.10f;
	private float timer = 0;
	
	public TunakTail(boolean emp){
		if(emp){
			sheet = Progetto.res.getAtlas("pack6").findRegion("TunakEmpCodaSprite");
		}
		else{
			sheet = Progetto.res.getAtlas("pack6").findRegion("TunakCodaSprite");
		}
		
		width = sheet.getRegionWidth()/FRAME_COLS;
		height = sheet.getRegionHeight();
		
		SpriteSheet = new TextureRegion[FRAME_COLS];
		int index = 0;
		for (int i = 0; i < FRAME_COLS; i++) {
			SpriteSheet[index++] = sheet.split((int)width,(int)height)[0][i];
		}
		
		
        animation1 = new Animation(timerFrame, SpriteSheet);
		
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void render(SpriteBatch sb) {
		
		timer += Gdx.graphics.getDeltaTime();
		if(!animation1.isAnimationFinished(timer)){
			currentFrame = animation1.getKeyFrame(timer,true);
		}
		else if(timer > (float)(timerFrame*FRAME_COLS)){
			timer = 0;
		}
		sb.draw(currentFrame, x, y, width, height);
	}

}
