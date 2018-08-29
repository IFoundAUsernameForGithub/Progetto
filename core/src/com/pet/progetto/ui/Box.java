package com.pet.progetto.ui;

public class Box {
	
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected int isRed;
	protected boolean Remove;
	
	private final int precision1 = 2;
	private int precision2 = 5;
	private int precision3 = 10;
	

	public int contains(float x, float y, float Objwidth, float Objheight) {
		if(		Math.max(this.x - width / precision1, this.x + width / precision1) >= Math.min(x + Objwidth / precision1, x - Objwidth / precision1 ) &&
				Math.min(this.x - width / precision1, this.x + width / precision1) <= Math.max(x + Objwidth / precision1, x - Objwidth / precision1 ) &&
				Math.max(this.y - height / precision1, this.y + height / precision1) >= Math.min(y + Objheight / precision1, y - Objheight / precision1 ) &&
				Math.min(this.y - height / precision1, this.y + height / precision1) <= Math.max(y + Objheight / precision1, y - Objheight / precision1 )){
			
			if(		Math.max(this.x - width / precision2, this.x + width / precision2) >= Math.min(x + Objwidth / precision2, x - Objwidth / precision2 ) &&
					Math.min(this.x - width / precision2, this.x + width / precision2) <= Math.max(x + Objwidth / precision2, x - Objwidth / precision2 ) &&
					Math.max(this.y - height / precision2, this.y + height / precision2) >= Math.min(y + Objheight / precision2, y - Objheight / precision2 ) &&
					Math.min(this.y - height / precision2, this.y + height / precision2) <= Math.max(y + Objheight / precision2, y - Objheight / precision2 )){
				
				if(		Math.max(this.x - width / precision3, this.x + width / precision3) >= Math.min(x + Objwidth / precision3, x - Objwidth / precision3 ) &&
						Math.min(this.x - width / precision3, this.x + width / precision3) <= Math.max(x + Objwidth / precision3, x - Objwidth / precision3 ) &&
						Math.max(this.y - height / precision3, this.y + height / precision3) >= Math.min(y + Objheight / precision3, y - Objheight / precision3 ) &&
						Math.min(this.y - height / precision3, this.y + height / precision3) <= Math.max(y + Objheight / precision3, y - Objheight / precision3 )){
					
					return 3;
				}
				return 2;
			}
			return 1;
		}
		return 0;
	}
	
	public float getx() { return x; }
	public float gety() { return y; }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	public boolean getRemove() { return Remove; } 
	
}
