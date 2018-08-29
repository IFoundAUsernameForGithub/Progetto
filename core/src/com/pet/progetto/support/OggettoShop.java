package com.pet.progetto.support;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pet.progetto.Progetto;
import com.pet.progetto.ui.Box;
import com.pet.progetto.ui.Graphic;

public class OggettoShop extends Box{
	
	private TextureRegion sfondo;
	private TextureRegion pulsante1;
	private TextureRegion pulsante2;
	private boolean isSingle = false;
	private boolean isVisible = true;
	private int arkai;
	private int etere;
	private float soldi;
	
	private BitmapFont font = new BitmapFont(Gdx.files.internal("Font/font.fnt"), Gdx.files.internal("Font/font.png"), false);
	private BitmapFont fontMeta20 = new BitmapFont(Gdx.files.internal("Font/Meta20.fnt"), Gdx.files.internal("Font/Meta20.png"), false);
	private GlyphLayout layout = new GlyphLayout();
	
	private String description = "Lardo Incrociato";
	
	
	public OggettoShop(int oggetto, float x, float y, int arkai, int etere){
		this.x = x;
		this.y = y;
		
		if(arkai >= 1 && etere >= 1){
			sfondo = Progetto.res.getAtlas("pack2").findRegion("OggettoShopDoppio");
			pulsante1 = Progetto.res.getAtlas("pack2").findRegion("PulsanteArkai");
			pulsante2 = Progetto.res.getAtlas("pack2").findRegion("PulsanteEtere");
			this.arkai = arkai;
			this.etere = etere;
			isSingle = false;
		}
		else if(arkai <= 0 && etere <=0){
			sfondo = Progetto.res.getAtlas("pack2").findRegion("OggettoShopSingolo");
			pulsante1 = Progetto.res.getAtlas("pack2").findRegion("PulsanteSoldi");
			soldi = 0.99f;
			isSingle = true;
		}
		width = sfondo.getRegionWidth();
		height = sfondo.getRegionHeight();
		
		
		
	
	}
	public void render(SpriteBatch sb){
		float scale = 1;
		sb.draw(sfondo, x - width/2, y - height/2);
		if(!isSingle){
			sb.draw(pulsante1, x + 256 - pulsante1.getRegionWidth()/2, y + 40 - pulsante1.getRegionHeight()/2);
			sb.draw(pulsante2, x + 256 - pulsante2.getRegionWidth()/2, y - 40 - pulsante2.getRegionHeight()/2);
			
			font.setColor(46f/255,25f/255,0f/255,1);
			layout.setText(font, Integer.toString(etere));
			font.draw(sb, Integer.toString(etere), x + 276 - layout.width/2, y + 40 + layout.height/2);
			layout.setText(font, Integer.toString(arkai));
			font.draw(sb, Integer.toString(arkai), x + 276 - layout.width/2, y - 40 + layout.height/2);
			
			scale = 1f;
			fontMeta20.setColor(46f/255,25f/255,0f/255,1);
			fontMeta20.getData().setScale(scale);
			layout.setText(fontMeta20, description);
			fontMeta20.draw(sb, description, x - 20 - layout.width/2, y + layout.height/2);
			
		}
		else{
			sb.draw(pulsante1, x + 256 - pulsante1.getRegionWidth()/2, y - pulsante1.getRegionHeight()/2);
			font.setColor(46f/255,25f/255,0f/255,1);
			layout.setText(font, Float.toString(soldi));
			font.draw(sb, Float.toString(soldi), x + 276 - layout.width/2, y + layout.height/2);
			
			scale = 1f;
			fontMeta20.setColor(46f/255,25f/255,0f/255,1);
			fontMeta20.getData().setScale(scale);
			layout.setText(fontMeta20, description);
			fontMeta20.draw(sb, description, x - 20 - layout.width/2, y + layout.height/2);
			
		}
		
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public boolean getIsVisible(){
		return isVisible;
	}
	
	public void setIsVisible(boolean value){
		isVisible = value;
	}
	
	public boolean isPressingButton1(float x, float y){
		float incrementY = 0;
		if(!isSingle){
			incrementY = 40;
		}
		return new Graphic(pulsante1, this.x + 256, this.y + incrementY, 1, 1).contains(x, y, 1, 1) > 0;
	}
	
	public boolean isPressingButton2(float x, float y){
		if(!isSingle){
			return new Graphic(pulsante2, this.x + 256, this.y - 40, 1, 1).contains(x, y, 1, 1) > 0;
		}
		else {
			return false;
		}
	}
	
	
}
