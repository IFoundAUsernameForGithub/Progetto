package com.pet.progetto.support;

public class LineBlocked {
	
	private int line;
	private float start;
	private float end;
	
	public LineBlocked(int line, float start, float end){
		this.line = line;
		this.start = start;
		this.end = end;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public float getStart() {
		return start;
	}

	public void setStart(float start) {
		this.start = start;
	}
	
	public void setTimer(float dt){
		this.start -= dt;
		this.end -= dt;
	}

	public float getEnd() {
		return end;
	}

	public void setEnd(float end) {
		this.end = end;
	}
	
}
