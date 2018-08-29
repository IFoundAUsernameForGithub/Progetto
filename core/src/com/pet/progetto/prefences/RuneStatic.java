package com.pet.progetto.prefences;

import java.util.ArrayList;

import com.pet.progetto.Progetto;
import com.pet.progetto.ui.Runes;

public class RuneStatic {
	
	public static ArrayList<Integer> RuneInventario = new ArrayList<Integer>();		
	
	public static void addRuneInventario(int value){
		if(value < 15*7){
			RuneInventario.add(value);
			Object[] keysInPref;
			keysInPref = Progetto.prefRunes.get().keySet().toArray();
			int i = keysInPref.length;
			Progetto.setPreferencesRuneInt("Runa" + Integer.toString(i), value);
		}
	}
	public static void setRuneInventario(int value,int index){
		if(value < 15*7 && index < RuneInventario.size()){
			RuneInventario.set(index, value);
		}
	}
	public static void removeRuneInventario(int index){
		if(index < RuneInventario.size()){
			RuneInventario.remove(index);
		}
	}
	public static int getRuneInventario(int index){
		if(index < RuneInventario.size()){
			return RuneInventario.get(index);
		}
		else{
			return -1;
		}
	}
	
	public static int getHP(int index){
		return new Runes(RuneStatic.getRuneInventario(index),0,0).getHP();
	}
	public static int getAttack(int index){
		return new Runes(RuneStatic.getRuneInventario(index),0,0).getAttack();
	}
	public static float getDefense(int index){
		return new Runes(RuneStatic.getRuneInventario(index),0,0).getDefense();
	}
	public static float getLifeSteal(int index){
		return new Runes(RuneStatic.getRuneInventario(index),0,0).getLifeSteal();
	}
	public static int getFortune(int index){
		return new Runes(RuneStatic.getRuneInventario(index),0,0).getFortune();
	}
	
}
