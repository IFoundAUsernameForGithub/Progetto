package com.pet.progetto.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pet.progetto.Progetto;
import com.pet.progetto.ui.GSM;
import com.pet.progetto.ui.Graphic;
import com.pet.progetto.ui.State;
import com.pet.progetto.ui.Obstacle;

public class ProvaState extends State{

	private Graphic Runa1;
	private Obstacle Runa2;
	private Obstacle Runa3;
	
	private int color = 0;
	private int precision = 0;
	
	public ProvaState(GSM gsm) {
		super(gsm);

		Runa1 = new Graphic(Progetto.res.getAtlas("pack").findRegion("Runa"),
				Progetto.WIDTH/10,
				Progetto.HEIGHT/4,
				1,
				1);
		
		Runa2 = new Obstacle(10,4,"Terra",false);
		
		Runa3 = new Obstacle(10,4,"Acqua",false);
		
	}

	
	public void handleInput() {
		if(Gdx.input.isTouched()){
			mouse.x = Gdx.input.getX();
			mouse.y = Gdx.input.getY();
			
			gsm.getVp().unproject(mouse);
			
			if(mouse.x > Progetto.WIDTH){mouse.x = Progetto.WIDTH + 1;}
			if(mouse.x < 0){mouse.x = -1;}
			if(mouse.y > Progetto.HEIGHT){mouse.y = Progetto.HEIGHT + 1;}
			if(mouse.y < 0){mouse.y = -1;}
			
			if(Runa1.contains(mouse.x,mouse.y,1,1)>=1){
				Runa1.setX(mouse.x);
				Runa1.setY(mouse.y);
			}
			
			precision = Runa1.contains(Runa2.getx(), Runa2.gety(), 103, 104);
			
			if(precision>=1){
				color = precision;
			}
			else{
				color = 0;
			}
		}
		
	}

	
	public void update(float dt) {
		handleInput();
		
		Runa2.setSize(+2);
	}

	
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();

		if(color == 0){
			sb.setColor(1,1,1,1);
		}
		else if(color == 1){
			sb.setColor(1, 0, 0, 1);
		}
		else if(color == 2){
			sb.setColor(0,1,0,1);
		}
		else{
			sb.setColor(0, 0, 1, 1);
		}
		Runa1.render(sb);
		Runa2.render(sb);
		Runa3.render(sb);
		
		sb.end();
	}

}
