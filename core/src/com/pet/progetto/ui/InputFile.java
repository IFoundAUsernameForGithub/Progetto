package com.pet.progetto.ui;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class InputFile {

	public static void save(String s, String path){
		try{
			FileHandle file = Gdx.files.local(path);
			if(saveFileExist(path)){
				file.writeString(s,true);
				return;
			}
			file.writeString(s, false);
		}
		catch(Exception e){
			e.printStackTrace();
			Gdx.app.exit();
		}
		
	}
	
	public static String load(String path){
		String s = "";
		try{
			if(saveFileExist(path)){
				return "hello";
			}
			FileHandle file = Gdx.files.internal(path);
			s = file.readString();
			
		}
		catch(Exception e){
			e.printStackTrace();
			Gdx.app.exit();
		}
		return s;
	}
	
	public static boolean saveFileExist(String path){
		File f = new File(path);
		return f.exists();
	}
	
}
