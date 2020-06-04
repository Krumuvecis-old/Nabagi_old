package calculations.cilveki;

import java.util.ArrayList;

import calculations.lietas.Lieta;

public class Cilveks {
	
	public static int maxCilveks=0; //numerâcija vârdu doðanai
	
	
	public String vards;
	public Koord xyz = new Koord(); // (x,y,v,fi) - visi double
	
	//primârie parametri
	public double vmax;
	public double ommax;
	public double hp;
	public double hpmax;
	
	public double paika;
	public double paikaMax;
	public double paikaMin;
	
	//sensorie parametri
	public double R1, R2; //redzesloks
	
	//cîòas parametri
	public ArrayList<Lieta> inventory = new ArrayList<Lieta>();
	public ArrayList<Orderis> orderi = new ArrayList<Orderis>(); // (prece,perk(boolean),cena)
	//public ArrayList<Orderis> atminas = new ArrayList<Orderis>(); //atmiòas par tirdzniecîbas cenâm
	
	public double brunas;
	public double stiprums;
	public double gataviba;
	public double drosme;
	public String komanda;
	public int[] rangs; //0[x]-(zemnieki/amatnieki)&tirgoòi, 					1[x]-valdnieki&karotâji
						
						//0,0-nabags - lasa ìenerçtos objektus / strâdâ			1,0 -apsargs - sargâ fermas un citus objektus
						//0,1-zemnieks - var uzbûvçt fermu 						1,1 -karotâjs - staigâ ap pili
						//0,2-tirgotâjs	- iepçrk resursus un pârdod kur vajag	1,2 -gvarde - staigâ pilî/sargâ karali
						//0,3-raþotâjs - iepçrk resursus un no tiem raþo  citus	1,3 -dedicated karalis - dod visiem drosmi?
	
	//animâcijai un testiem
	public String darbiba;
	
	
	
	
}
