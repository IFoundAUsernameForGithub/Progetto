package com.pet.progetto.support;

public class TileSet {
	
	private int runa;
	private float seconds;
	private String[] lines;
	
	public TileSet(String s){
		lines = s.split("\n");
		runa = Integer.parseInt(lines[0].split(";")[0].split(":")[1]);
		seconds = Float.parseFloat(lines[0].split(";")[1].split(":")[1]);
	}
	
	public int getMax(){
		return lines.length;
	}
	
	public int getRuna(int count){
		runa = Integer.parseInt(lines[count].split(";")[0].split(":")[1]);
		return runa;
	}
	
	public float getSeconds(int count){
		seconds = Float.parseFloat(lines[count].split(";")[1].split(":")[1]);
		return seconds;
	}
	
}
