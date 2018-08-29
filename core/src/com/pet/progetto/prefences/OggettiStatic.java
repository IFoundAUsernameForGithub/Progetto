package com.pet.progetto.prefences;

import java.util.ArrayList;
import com.pet.progetto.Progetto;
import com.pet.progetto.ui.Oggetto;

public class OggettiStatic {
	
public static ArrayList<Integer> OggettiInventario = new ArrayList<Integer>();		
	
	public static void addOggettiInventario(int value){
		OggettiInventario.add(value);
		Object[] keysInPref;
		keysInPref = Progetto.prefOggetti.get().keySet().toArray();
		int i = keysInPref.length;
		Progetto.setPreferencesRuneInt("Oggetto" + Integer.toString(i), value);
	}
	public static void setOggettiInventario(int value,int index){
		if(index < OggettiInventario.size()){
			OggettiInventario.set(index, value);
		}
	}
	public static void removeOggettiInventario(int index){
		if(index < OggettiInventario.size()){
			OggettiInventario.remove(index);
		}
	}
	public static int getOggettiInventario(int index){
		if(index < OggettiInventario.size()){
			return OggettiInventario.get(index);
		}
		else{
			return -1;
		}
	}
	
	public static String getDescription(int index){
		return new Oggetto(OggettiStatic.getOggettiInventario(index)).getDescription();
	}
	
}
