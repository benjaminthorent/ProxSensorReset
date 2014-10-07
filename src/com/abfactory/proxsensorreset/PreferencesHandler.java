package com.abfactory.proxsensorreset;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferencesHandler {

	private SharedPreferences sharedPrefs;
	private static final String defaultLanguage = "en";

	public enum Settings {
		LANGUAGE_LOCALE;
	}
	
	public enum Language {
		ENGLISH(R.string.languageEnglish, "en"),
		FRENCH(R.string.languageFrench, "fr");
		
		private String stringFileFolder;
		private int ressourceID;
		
		Language(int ri, String sff){
			stringFileFolder = sff;
			ressourceID = ri;
		}
		
		public String getStringFileFolder() {
			return stringFileFolder;
		}
		
		public int getRessourceID() {
			return ressourceID;
		}

		public static List<Integer> getSuppportedLanguagesListAsRessources(){
			List<Integer> languagesAsRessources = new ArrayList<Integer>();
			for(Language l: Language.values()){
				languagesAsRessources.add(l.getRessourceID());
			}
			return languagesAsRessources;
		}
		
		public static String getLocaleStringFromIndex(int index){
			return Language.values()[index].getStringFileFolder();
		}
		
		public static int getDefaultItemPositionForLocale(String locale){
			int index = 0;
			for(Language l: Language.values()){
				if(l.getStringFileFolder().equals(locale)){
					return index;
				}
				index++;
			}
			return index;
		}
	
	}

	public PreferencesHandler(Context applicationContext){
		// Get app preferences
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
	}

	public void saveSettings(String locale) {
		Editor edit = sharedPrefs.edit();
		// Set changes to perform
		edit.putString(Settings.LANGUAGE_LOCALE.toString(), locale);
		// Save preferences
		edit.apply();
	}
	
	public String getLanguage(){
		return sharedPrefs.getString(Settings.LANGUAGE_LOCALE.toString(), defaultLanguage);
	}
	
	public int getDefaultLanguageIndex(){
		return Language.getDefaultItemPositionForLocale(sharedPrefs.getString(Settings.LANGUAGE_LOCALE.toString(), defaultLanguage));
	}

}
