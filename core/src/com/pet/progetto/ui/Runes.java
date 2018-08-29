package com.pet.progetto.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pet.progetto.Progetto;

public class Runes extends Box{
	
	
	private TextureRegion[][] Sprites;
	private int size = 130;
	private String type;
	private String element;
	private int level;
	private int row = 0;
	private int col = 0;
	private boolean isVisible = true;
	private TextureRegion image;
	private int typeNum=0;
	private int elementNum=0;
	
	public int hp = 0;
	public float defense = 0;
	public int attack = 0;
	public float lifesteal = 0;
	public int fortune = 0; 
	
	public Runes(String Type, int x, int y, String element, int Level){
		
		this.x = x;
		this.y = y;
		this.width = size;
		this.height = size;
		
		this.type = Type;
		this.element = element;
		if(Level < 3){
			this.level = Level;
		}
		else{
			this.level = 0;
		}
		
		if(type == "Normal"){
			hp += 5*(level+1);
			attack += 5*(level+1);
			col = level;
			typeNum = 0;
		}
		else if(type == "Lifesteal"){
			hp += 2*(level+1);
			attack += 6*(level+1);
			lifesteal += 0.01*(level+1);
			col = 3 + level;
			typeNum = 1;
		}
		else if(type == "Luck"){
			hp += 1*(level+1);
			attack += 1*(level+1);
			fortune += 1*(level+1);
			col = 6 + level;
			typeNum = 2;
		}
		else if(type == "Attack"){
			attack += 10*(level+1);
			hp += -5*(level+1);
			col = 9 + level;
			typeNum = 3;
		}
		else if(type == "Defense"){
			hp += 10*(level+1);
			attack += 1*(level+1);
			defense += 0.03f*(level+1);
			col = 12 + level;
			typeNum = 4;
		}
		else{
			hp += 5*(level+1);
			attack += 5*(level+1);
			col = level;
			typeNum = 0;
		}
		
		if(element == "Off"){		
			row = 0;
			elementNum = 0;
		}
		else if(element == "Neutral"){
			row = 1;
			elementNum = 1;
		}
		else if(element == "Earth"){
			row = 2;
			elementNum = 2;
		}
		else if(element == "Water"){
			row = 3;
			elementNum = 3;
		}
		else if(element == "Fire"){
			row = 4;
			elementNum = 4;
		}
		else if(element == "Air"){
			row = 5;
			elementNum = 5;
		}
		else if(element == "Time"){
			row = 6;
			elementNum = 6;
		}
		else if(element == "Missed"){
			row = 7;
			elementNum = 7;
		}
		else {		
			row = 0;
			elementNum = 0;
		}
		
		TextureRegion sheet =
				Progetto.res.getAtlas("pack").findRegion("RuneSprite");		
			Sprites = sheet.split(size, size);
			
		image = Sprites[row][col];
	}
	
	public Runes(int value, int x, int y){
		
		row = value/15;
		col = value%15;
		
		this.x = x;
		this.y = y;
		this.width = size;
		this.height = size;
		
		switch (row){
			case 0:
				element = "Off";
				break;
			case 1:
				element = "Neutral";
				break;
			case 2:
				element = "Earth";
				break;
			case 3:
				element = "Water";
				break;
			case 4:
				element = "Fire";
				break;
			case 5:
				element = "Air";
				break;
			case 6:
				element = "Time";
				break;
			case 7:
				element = "Missed";
				break;
			default:
				element = "Neutral";
				break;
			
		}
		
		elementNum = row;
		typeNum = col/3;
		level = col%3;
		
		switch (typeNum){
			case 0:
				type = "Normal";
				hp += 5*(level+1);
				attack += 5*(level+1);
				break;
			case 1:
				type = "Lifesteal";
				hp += 2*(level+1);
				attack += 6*(level+1);
				lifesteal += 0.01*(level+1);
				break;
			case 2:
				type = "Luck";
				hp += 1*(level+1);
				attack += 1*(level+1);
				fortune += 1*(level+1);
				break;
			case 3:
				type = "Attack";
				attack += 10*(level+1);
				hp += -5*(level+1);
				break;
			case 4:
				type = "Defense";
				hp += 10*(level+1);
				attack += 1*(level+1);
				defense += 0.03f*(level+1);
				break;
			default:
				type = "Normal";
				hp += 5*(level+1);
				attack += 5*(level+1);
				break;
				
		}
		
		
		
		TextureRegion sheet =
				Progetto.res.getAtlas("pack").findRegion("RuneSprite");		
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
	
	public void setisVisible(boolean visible){
		this.isVisible = visible;
	}
	
	public boolean getisVisible(){
		return isVisible;
	}
	
	public void setImage(TextureRegion image){
		this.image = image;
	}
	
	public void TurnOn(int value){
		if(value >= 0){
			if(element == "Neutral"){
				row = 1;
				elementNum = 1;
			}
			else if(element == "Earth"){
				row = 2;
				elementNum = 2;
			}
			else if(element == "Water"){
				row = 3;
				elementNum = 3;
			}
			else if(element == "Fire"){
				row = 4;
				elementNum = 4;
			}
			else if(element == "Air"){
				row = 5;
				elementNum = 5;
			}
			else if(element == "Time"){
				row = 6;
				elementNum = 6;
			}
			else if(element == "Void"){
				row = 7;
				elementNum = 7;
			}
			else {		
				row = 0;
				elementNum = 0;
			}
		}
		else{
			//Off
			row = 0;
			elementNum = 0;
		}
		
		TextureRegion sheet =
				Progetto.res.getAtlas("pack").findRegion("RuneSprite");		
			Sprites = sheet.split(size, size);
			
		this.image = Sprites[row][col];
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
	public int getTypeNum(){
		return typeNum;
	}
	public String getType(){
		return type;
	}
	public int getElementNum(){
		return elementNum;
	}
	public String getElement(){
		return element;
	}
	public int getLevel(){
		return level;
	}
	
	public int getHP(){
		return hp;
	}
	public void setHP(int value){
		hp = value;
	}
	public int getAttack(){
		return attack;
	}
	public void setAttack(int value){
		attack = value;
	}
	public float getDefense(){
		return defense;
	}
	public void setDefense(float value){
		defense = value;
	}
	public float getLifeSteal(){
		return lifesteal;
	}
	public void setFortune(float value){
		lifesteal = value;
	}
	public int getFortune(){
		return fortune;
	}
	public void setFortune(int value){
		fortune = value;
	}
	public int getNumber(){
		return elementNum*15 + typeNum*3 + level;
	}
	
	
	
	
	public int compareLevelTo(Runes obj){
		if(level < obj.level) return -1;
		else if (level > obj.level) return 1;
		else{
			if(typeNum > obj.typeNum) return -1;
			else if(typeNum < obj.typeNum) return 1;
			else{
				if(elementNum > obj.elementNum) return -1;
				else if (elementNum < obj.elementNum) return 1;
				else return 0;
			}
		}
	}
	public int compareElementTo(Runes obj){
		if(elementNum > obj.elementNum) return -1;
		else if (elementNum < obj.elementNum) return 1;
		else{
			if(level < obj.level) return -1;
			else if (level > obj.level) return 1;
			else{
				if(typeNum > obj.typeNum) return -1;
				else if(typeNum < obj.typeNum) return 1;
				else return 0;
			}
		}
	}
	public int compareTypeTo(Runes obj){
		if(typeNum > obj.typeNum) return -1;
		else if (typeNum < obj.typeNum) return 1;
		else{
			if(level < obj.level) return -1;
			else if (level > obj.level) return 1;
			else{
				if(elementNum > obj.elementNum) return -1;
				else if(elementNum < obj.elementNum) return 1;
				else return 0;
			}
		}
	}
	
	

	
	
	
}
