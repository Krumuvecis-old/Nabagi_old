package dataBase;

import java.util.ArrayList;


public class Dati {
	
	
	public static ArrayList<Cilveks> cilvekiList; //cilv�ku datub�ze
	public static ArrayList<Komanda> komandasList; //komandu datub�ze
	public static ArrayList<Lieta> lietas; //loot datub�ze
	
	public static void initialize() {
		cilvekiList = new ArrayList<Cilveks>();
		komandasList = new ArrayList<Komanda>();
		lietas = new ArrayList<Lieta>();
	}
	
}
