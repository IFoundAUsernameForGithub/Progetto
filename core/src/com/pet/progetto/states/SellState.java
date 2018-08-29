package com.pet.progetto.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pet.progetto.Progetto;
import com.pet.progetto.prefences.RuneStatic;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Graphic;
import com.pet.progetto.ui.Runes;
import com.pet.progetto.ui.State;

public class SellState extends State{

	
	private boolean hasNotSell = true;
	private float timerSell = 0;
	
	private Graphic sfondoNero;
	private Graphic sfondoSell;
	private float alphaHasToFight = 1;
	
	private Graphic ButtonNo;
	private Graphic ButtonYes;

	private String text1 = new String("Do you want");
	private String text2 = new String("to sell this");
	private String text3 = new String("rune for 10 Arkai?");
	
	private BitmapFont font = new BitmapFont(Gdx.files.internal("Meta54.fnt"), Gdx.files.internal("Meta54.png"), false);
	private BitmapFont fontMeta32 = new BitmapFont(Gdx.files.internal("Meta32.fnt"), Gdx.files.internal("Meta32.png"), false);
	
	private MapState prev;
	private GlyphLayout layout = new GlyphLayout();
	private float mouseXStart;
	private float mouseYStart;
	private int runeSelected = 0;
	
	public SellState(GSM gsm, MapState prev, int runeSelected) {
		super(gsm);
		this.prev = prev;
		
		sfondoNero = new Graphic(Progetto.res.getAtlas("pack4").findRegion("Bianco"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				10,
				10);
		
		sfondoSell = new Graphic(Progetto.res.getAtlas("pack3").findRegion("SfondoVita"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				1,
				1);
		
		ButtonNo = new Graphic(Progetto.res.getAtlas("pack2").findRegion("PulsanteRune"),
				Progetto.WIDTH/2 + 100,
				Progetto.HEIGHT/2 - 75,
				1,
				1);
		ButtonYes = new Graphic(Progetto.res.getAtlas("pack2").findRegion("PulsanteRune"),
				Progetto.WIDTH/2 - 100,
				Progetto.HEIGHT/2 - 75,
				1,
				1);
		
		this.runeSelected = runeSelected;
		
		
	}

	@Override
	public void handleInput() {
		
		if(Progetto.getBackPressed()){
			hasNotSell = false;
		}
		
		if(Gdx.input.isTouched() && hasNotSell){
			
			mouse.x = Gdx.input.getX();
			mouse.y = Gdx.input.getY();
			
			gsm.getVp().unproject(mouse);
			
			if(mouse.x > Progetto.WIDTH){mouse.x = Progetto.WIDTH + 1;}
			if(mouse.x < 0){mouse.x = -1;}
			if(mouse.y > Progetto.HEIGHT){mouse.y = Progetto.HEIGHT + 1;}
			if(mouse.y < 0){mouse.y = -1;}
			
			if(mouseXStart == -1 && mouseYStart == -1){
				mouseXStart = mouse.x;
				mouseYStart = mouse.y;
			}
			
			if(ButtonNo.contains(mouseXStart, mouseYStart, 1, 1) > 0){
				hasNotSell = false;
			}
			if(ButtonYes.contains(mouseXStart, mouseYStart, 1, 1) > 0){
				mouseXStart = -1;
				mouseYStart = -1;
				Progetto.setArkai(Progetto.getArkai() + 10);
				
				RuneStatic.RuneInventario.remove(runeSelected);
				
				prev.RuneInventario.clear();
				Progetto.clearPrefenceRune();
				
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
					
					prev.RuneInventario.add(new Runes(RuneStatic.getRuneInventario(i),x,(int)(y + 945)));
					
					Progetto.setPreferencesRuneInt("Runa" + Integer.toString(i), RuneStatic.getRuneInventario(i));
					
				}
				prev.pageRunes = 1;
				prev.maxPageRunes = (int)Math.ceil((double)((prev.RuneInventario.size()-5))/9);
				if(prev.maxPageRunes == 0){
					prev.maxPageRunes = 1;
				}
				prev.Page = prev.pageRunes + " / " + prev.maxPageRunes;
				prev.runeSelected = -1;
				prev.RunaDescrizione = new Runes(Integer.toString(0),
						300,
						790,
						Integer.toString(0),
						0);
				hasNotSell = false;
			}
		}
		else{
			mouseXStart = -1;
			mouseYStart = -1;
		}
	
	}

	@Override
	public void update(float dt) {
			
		
		
		handleInput();
		prev.update(dt);
		
		
		if(hasNotSell){
			timerSell+=dt;
			if(timerSell > 0.5f){
				timerSell = 0.5f;
			}
		}
		else{
			timerSell-=dt;
			if(timerSell < 0){
				timerSell = 0;
				prev.setIsActive(true);
				gsm.pop();
			}
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		if(timerSell <= 0.5f){
			alphaHasToFight = timerSell;
		}
		else{
			alphaHasToFight = 0.5f;
		}
		prev.render(sb);
		sb.setColor(0, 0, 0, alphaHasToFight*1.2f);
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		sfondoNero.render(sb);
		
		sb.setColor(1, 1, 1, alphaHasToFight*2);
		sfondoSell.render(sb);
		
		sb.setColor(1,1,1,1);
		float scale = 1;
		scale = 0.75f;
		font.setColor(46f/255,25f/255,0f/255,alphaHasToFight*2);
		font.getData().setScale(scale);
		layout.setText(font, text1);
		font.draw(sb, text1, Progetto.WIDTH/2 - layout.width/2 , Progetto.HEIGHT/2 + 150 - layout.height/2);
		scale = 0.75f;
		font.setColor(46f/255,25f/255,0f/255,alphaHasToFight*2);
		font.getData().setScale(scale);
		layout.setText(font, text2);
		font.draw(sb, text2, Progetto.WIDTH/2 - layout.width/2 , Progetto.HEIGHT/2 + 150 - 50 - layout.height/2);
		scale = 0.75f;
		font.setColor(46f/255,25f/255,0f/255,alphaHasToFight*2);
		font.getData().setScale(scale);
		layout.setText(font, text3);
		font.draw(sb, text3, Progetto.WIDTH/2 - layout.width/2 , Progetto.HEIGHT/2 + 150 - 100 - layout.height/2);
		
		sb.setColor(1, 1, 1, alphaHasToFight*2);
		ButtonNo.render(sb);
		ButtonYes.render(sb);
		sb.setColor(1, 1, 1, 1);
		
		
		scale = 1f;
		fontMeta32.setColor(46f/255,25f/255,0f/255,alphaHasToFight*2);
		fontMeta32.getData().setScale(scale);
		String yes = "Yes";
		layout.setText(fontMeta32, yes);
		fontMeta32.draw(sb, yes, ButtonYes.getx() - layout.width/2 , ButtonYes.gety() + layout.height/2);
		
		scale = 1f;
		fontMeta32.setColor(46f/255,25f/255,0f/255,alphaHasToFight*2);
		fontMeta32.getData().setScale(scale);
		String no = "No";
		layout.setText(fontMeta32, no);
		fontMeta32.draw(sb, no, ButtonNo.getx() - layout.width/2 , ButtonNo.gety() + layout.height/2);
		
		
		
		sb.end();
		sb.setColor(1, 1, 1, 1);
		
	}
}
