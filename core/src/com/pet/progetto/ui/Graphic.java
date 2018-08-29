package com.pet.progetto.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Graphic extends Box{
	
	private TextureRegion image;
	private boolean visible = true;
	
	public Graphic(TextureRegion image, float x, float y, float Width, float Height) {
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.getRegionWidth()*Width;
		height = image.getRegionHeight()*Height;
	}
	
	public void setX (float x){
		this.x = x;
		
	}
	
	public void setY (float y) {
		this.y = y;
	}
	
	public void setWidth(float Width){
		this.width = Width;
	}
	
	public void setHeight(float Height) {
		this.height = Height;
	}
	
	public void setImage(TextureRegion ima){
		this.image = ima;
	}
	
	public void setvisible(boolean visible){
		this.visible = visible;
	}
	
	public boolean getVisible(){
		return this.visible;
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(image, x - width / 2, y - height / 2, width, height);
	}

}
