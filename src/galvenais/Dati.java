package galvenais;

import java.util.ArrayList;

import calculations.cilveki.Cilveks;
import calculations.komandas.Komanda;
import calculations.lietas.Lieta;


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
