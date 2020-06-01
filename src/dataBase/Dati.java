package dataBase;

import java.util.ArrayList;


public class Dati {
	
	
	public static ArrayList<Cilveks> cilvekiList; //cilvçku datubâze
	public static ArrayList<Komanda> komandasList; //komandu datubâze
	public static ArrayList<Lieta> lietas; //loot datubâze
	
	public static void initialize() {
		cilvekiList = new ArrayList<Cilveks>();
		komandasList = new ArrayList<Komanda>();
		lietas = new ArrayList<Lieta>();
	}
	
}
