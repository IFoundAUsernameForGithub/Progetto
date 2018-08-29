package com.pet.progetto.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.pet.progetto.Progetto;

public class Character {
	private static float defense;
	private static int attack;
	private static float lifesteal;
	private static int fortune; 
	private static int hpIncrement;
	private static float defenseIncrement;
	private static int attackIncrement;
	private static float lifestealIncrement;
	private static int fortuneIncrement; 
	private static ArrayList<Oggetto> oggetti;
	
	
	public static int getHP(){
		return Progetto.getMaxLife();
	}
	public static float getDefense(){
		return defense + defenseIncrement;
	}
	public static void setDefense(float defense){
		Character.defense = defense;
		Progetto.setDefense(defense);
	}
	public static int getAttack(){
		return attack + attackIncrement;
	}
	public static void setAttack(int attack){
		Character.attack = attack;
		Progetto.setAttack(attack);
	}
	public static float getLifesteal() {
		return lifesteal + lifestealIncrement;
	}
	public static void setLifeSteal(float lifesteal) {
		Character.lifesteal = lifesteal;
		Progetto.setLifeSteal(lifesteal);
	}
	public static int getFortune() {
		return fortune + fortuneIncrement;
	}
	public static void setFortune(int fortune) {
		Character.fortune = fortune;
		Progetto.setFortune(fortune);
	}
	
	
	public static int getHpIncrement(){
		return hpIncrement;
	}
	public static void setHpIncrement(int hp){
		Character.hpIncrement = hp;
	}
	public static float getDefenseIncrement(){
		return defenseIncrement;
	}
	public static void setDefenseIncrement(float defense){
		Character.defenseIncrement = defense;
	}
	public static int getAttackIncrement(){
		return attackIncrement;
	}
	public static void setAttackIncrement(int attack){
		Character.attackIncrement = attack;
	}
	public static float getLifestealIncrement() {
		return lifestealIncrement;
	}
	public static void setLifestealIncrement(float lifesteal) {
		Character.lifestealIncrement = lifesteal;
	}
	public static int getFortuneIncrement() {
		return fortuneIncrement;
	}
	public static void setFortuneIncrement(int fortune) {
		Character.fortuneIncrement = fortune;
	}
	
	
	public static Oggetto getOggetto(int s){
		return oggetti.get(s);
	}
	public static void addOggetto(Oggetto s){
		oggetti.add(s);
		//TODO 
	}
	public static void deleteOggetto(int s){
		oggetti.remove(s);
		//TODO
	}
	
}
