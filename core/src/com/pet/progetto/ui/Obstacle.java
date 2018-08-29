package com.pet.progetto.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pet.progetto.Progetto;

public class Obstacle extends Box{
	
	private int Size=1;
	private int line = 0;
	private int numMouse = -1;
	private boolean isVisible = true;
	private TextureRegion Image;
	private float alpha = 1;
	private String element;
	private boolean IsDouble;
	private int StartSize = 0;
	
	public Obstacle(int Size, int lines, String Element, boolean isDouble){
		
		this.width = 103;
		this.height = 104;
		
		this.element = Element;
		this.IsDouble = isDouble;
		
		setLine(lines);
		this.y = Progetto.HEIGHT - 320;
		switch (lines) {
			case 0:
				this.x = 297; 
				break;
			case 1:
				this.x = 328;
				break;
			case 2:
				this.x = Progetto.WIDTH/2;
				break;
			case 3:
				this.x = 393;
				break;
			case 4:
				this.x = 424;
				break;
			default:
				this.x = Progetto.WIDTH/2;
				break;
				
		}
		
		if(element == "Terra"){
			if(IsDouble){
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloTerraDoppio");
			}
			else{
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloTerra");
			}
		}
		else if(element == "Acqua"){
			if(IsDouble){
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloAcquaDoppio");
			}
			else{
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloAcqua");
			}
		}
		else if(element == "Fuoco"){
			if(IsDouble){
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloFuocoDoppio");
			}
			else{
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloFuoco");
			}
		}
		else if(element == "Aria"){
			if(IsDouble){
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloAriaDoppio");
			}
			else{
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloAria");
			}
		}
		else if(element == "Tempo"){
			if(IsDouble){
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloTempoDoppio");
			}
			else{
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloTempo");
			}
		}
		else if(element == "Premuto"){
			if(IsDouble){
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloPremutoDoppio");
			}
			else{
				Image = Progetto.res.getAtlas("pack").findRegion("OstacoloPremuto");
			}
		}
		
		this.Size = Size;
		
		
	}
	
	public void SetX(float X){
		this.x = X;
	}
	
	public void SetY(float Y){
		this.y = Y;
	}
	
	public void MoveY(float Y){
		y -= Y;
	}
	public void MoveX(float X){
		if(line == 0){
			x-=X;
		}
		else if(line == 1){
			x-=X;
		}
		else if(line == 3){
			x+=X;
		}
		else if(line == 4){
			x+=X;
		}
	}
	
	public int getClicked(){
		return numMouse;
	}
	public void setClicked(int mouse){
		numMouse = mouse;
	}
	
	public int getLine(){
		return line;
	}
	
	public void setLine(int lines){
		line = lines;
	}
	
	public int getSize(){
		return Size;
	}
	
	public void setSize(int size){
		Size += size;

	}
	
	public String getElement(){
		return element;
	}
	public void setElement(String element){
		this.element = element;
	}
	
	public boolean getIsDouble(){
		return IsDouble;
	}
	public void setIsDouble(boolean IsDouble){
		this.IsDouble = IsDouble;
	}
	
	public void setisVisible(boolean visible){
		this.isVisible = visible;
	}
	
	public boolean getisVisible(){
		return isVisible;
	}
	
	public float getAlpha(){
		return alpha;
	}
	
	public void setAlpha(float alpha){
		this.alpha = alpha;
	}
	
	public int getStartSize(){
		return StartSize;
	}
	public void setStartSize(int StartSize){
		this.StartSize = StartSize;
	}
	
	public void render(SpriteBatch sb) {
		if(isVisible){
			sb.draw(Image, x - width*Size/100/2, y - height*Size/100/2, width*Size/100, height*Size/100);
			
		}
	}

}
