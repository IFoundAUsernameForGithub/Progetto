package com.pet.progetto.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pet.progetto.Progetto;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Graphic;
import com.pet.progetto.ui.State;

public class LifeState extends State{
	
	
	private boolean hasLifeToFight = true;
	private float timerHasToFight = 0;
	
	private Graphic sfondoNero;
	private Graphic sfondoHasToFight;
	private float alphaHasToFight = 1;
	
	private Graphic ButtonNo;
	private Graphic ButtonYes;

	private String text1 = new String("Your HP are too low.");
	private String text2 = new String("Do you want to rest");
	private String text3 = new String("at the Inn for 20 Arkai?");
	private String text4 = new String("You don't have enough Arkai");
	private boolean enoughArkai = true;
	private float timerEnoughArkai = 0;
	private float alphaEnoughArkai = 0;
	
	private BitmapFont font = new BitmapFont(Gdx.files.internal("Meta54.fnt"), Gdx.files.internal("Meta54.png"), false);
	private BitmapFont fontMeta32 = new BitmapFont(Gdx.files.internal("Meta32.fnt"), Gdx.files.internal("Meta32.png"), false);
	
	private MapState prev;
	private GlyphLayout layout = new GlyphLayout();
	private float mouseXStart;
	private float mouseYStart;
	
	protected LifeState(GSM gsm, MapState prev) {
		super(gsm);
		this.prev = prev;
		
		sfondoNero = new Graphic(Progetto.res.getAtlas("pack4").findRegion("Bianco"),
				Progetto.WIDTH/2,
				Progetto.HEIGHT/2,
				10,
				10);
		
		sfondoHasToFight = new Graphic(Progetto.res.getAtlas("pack3").findRegion("SfondoVita"),
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
		
		
	}

	@Override
	public void handleInput() {
		
		if(Progetto.getBackPressed()){
			hasLifeToFight = false;
		}
		
		if(Gdx.input.isTouched() && hasLifeToFight){
			
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
			
			if(ButtonNo.contains(mouse.x, mouse.y, 1, 1) > 0){
				hasLifeToFight = false;
			}
			if(ButtonYes.contains(mouse.x, mouse.y, 1, 1) > 0){
				if(Progetto.getArkai() - 20 >= 0){
					Progetto.setArkai(Progetto.getArkai() - 20);
					hasLifeToFight = false;
				}
				else{
					enoughArkai = false;
				}
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
		
		if(!enoughArkai){
			timerEnoughArkai += dt;
			if(timerEnoughArkai < 1f){
				alphaEnoughArkai = timerEnoughArkai;
			}
			else if(timerEnoughArkai < 2f){
				alphaEnoughArkai = 1;
			}
			else{
				alphaEnoughArkai = 3 - timerEnoughArkai;
			}
			if(timerEnoughArkai > 3f){
				timerEnoughArkai = 0;
				enoughArkai = true;
			}
		}
		
		if(hasLifeToFight){
			timerHasToFight+=dt;
			if(timerHasToFight > 0.5f){
				timerHasToFight = 0.5f;
			}
		}
		else{
			timerHasToFight-=dt;
			if(timerHasToFight < 0){
				timerHasToFight = 0;
				prev.setIsActive(true);
				gsm.pop();
			}
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		if(timerHasToFight <= 0.5f){
			alphaHasToFight = timerHasToFight;
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
		sfondoHasToFight.render(sb);
		
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
		if(!enoughArkai){
			scale = 0.5f;
			font.setColor(46f/255,25f/255,0f/255,alphaEnoughArkai);
			font.getData().setScale(scale);
			layout.setText(font, text4);
			font.draw(sb, text4, Progetto.WIDTH/2 - layout.width/2, ButtonYes.gety() - 50  - layout.height/2);

		}
		
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
