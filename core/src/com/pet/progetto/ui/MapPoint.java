package com.pet.progetto.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pet.progetto.Progetto;

public class MapPoint extends Box{


	private TextureRegion[][] Sprites;
	private int size = 150;
	private int world;
	private int level;
	private int row = 0;
	private int col = 0;
	private boolean isBlocked = true;
	private TextureRegion image;
	
	public MapPoint(int Level, int World, int x, int y, boolean isBlocked){
		
		this.x = x;
		this.y = y;
		this.width = size;
		this.height = size;
		
		this.level = Level;
		this.world = World;
		if(level < 10){
			this.level = Level;
		}
		else{
			this.level = 0;
		}
		
		row = world;
		col = level;
		
		this.isBlocked = isBlocked;
		
		if(this.isBlocked){
			image = Progetto.res.getAtlas("pack2").findRegion("MostriMondoBloccato");
		}
		else{
			TextureRegion sheet =
					Progetto.res.getAtlas("pack2").findRegion("MostriMondoTerra");		
				Sprites = sheet.split(size, size);
				
			this.image = Sprites[row][col];
		}
		
	}
	
	public void render(SpriteBatch sb) {
			sb.draw(
				image,
				x - size/2,
				y - size/2,
				size,
				size
			);
	}
	
	public void setisBlocked(boolean isBlocked){
		if(isBlocked){
			image = Progetto.res.getAtlas("pack2").findRegion("MostriMondoBloccato");
		}
		else{
			TextureRegion sheet =
					Progetto.res.getAtlas("pack2").findRegion("MostriMondoTerra");		
				Sprites = sheet.split(size, size);
				
			this.image = Sprites[row][col];
		}
		this.isBlocked = isBlocked;
	}
	
	public boolean getisBlocked(){
		return isBlocked;
	}
	
	public void setImage(TextureRegion image){
		this.image = image;
	}
	
	public float getY(){
		return y;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getX(){
		return x;
	}
	public void setX(float x){
		this.x = x;
	}
	public int getWorld(){
		return world;
	}
	public int getLevel(){
		return level;
	}
	
}
