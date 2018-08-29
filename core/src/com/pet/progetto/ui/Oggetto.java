package com.pet.progetto.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pet.progetto.Progetto;

public class Oggetto extends Box{
	
	private TextureRegion[][] Sprites;
	private int size = 130;
	private int row = 0;
	private int col = 0;
	private boolean isVisible = true;
	private TextureRegion image;
	private boolean comprato;
	
	public int arkai = 0;
	public int etere = 0;
	public int soldi = 0; 
	public String description = new String();
	
	public Oggetto(int index){
		
		row = index/15;
		col = index%15;
		
		this.width = size;
		this.height = size;
		
		TextureRegion sheet =
				Progetto.res.getAtlas("pack8").findRegion("OggettiSprite");		
			Sprites = sheet.split(size, size);
			
		image = Sprites[row][col];
		
		
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

	public String getDescription() {
		return description;
	}

	public boolean getIsVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
