package calculations.konstantes;

import java.awt.Color;

public class Parametri { //visi default koeficienti un parametri vienkopus
	
	public static void initialize() {
		Galvenie.initialize();
		Grafiskie.initialize();
		Generators.initialize();
		Fizikas.initialize();
		Cilvekiem.initialize();
		Misc.initialize();
		
		System.out.println("parametri inicializ�ti");
	}
	
	// --------------------
	//zem�k galvenie parametri
	
	public static String versija;
	
	
	// --------------------
	//zem�k grafiskie parametri
	
	public static String ekranaNosaukums;
	
	public static double cilvekiKrasaSaturation, cilvekiKrasaBrightnessMin, cilvekiKrasaBrightnessMax;
	
	public static Color kronaKrasa;
	public static double kronaKoeficients;
	
	public static Color laukumaKrasa, malasKrasa;
	public static Color lietasColorZelts, lietasColorPaika, lietasColorDefault;
	
	public static Color komandasColorDefault;
	public static boolean komandasBannedColors;
	public static double[] komandasBannedColorList;
	
	
	// --------------------
	//zem�k random �eneratoru parametri
	
	public static int sakumaCilveki;
	public static boolean randomKomandas;
	
	public static double goldGenRate, goldGenMin, goldGenMax;
	public static double paikaGenRate, paikaGenMin, paikaGenMax;
	
	
	// --------------------
	//zem�k fizikas  parametri
	
	public static int platums, augstums;
	public static int mala;
	
	public static int lietasResnums; // default neklasific�tai lietai
	public static int zeltaResnums, paikasResnums;
	
	public static double resnumaKoefic; //cilv�ka resnuma koeficients (noteik�anai p�c hpMax)
	//public static double vKoefic=0.1;
	
	
	// --------------------
	//zem�k cilv�ku parametri
	
	public static double vmax, ommax;
	
	public static int maxGataviba;
	public static double hpmax;
	public static int RMax, R1koefic;
	public static double dRDzimstot;
	public static double dvMaxDzimstot, dommaxDzimstot;
	
	public static double healingRateDefault,healthReductionRate,paikaReductionDefault;
	public static int paikaMax,paikaMin;
	public static double esanasDaudzums;
	
	
	public static int paikaNepiec;
	
	public static double brunasMax, brunasMin, dBrunasDzimstot,
			stiprumsMax, stiprumsMin, dStiprumsDzimstot;
	public static String vardsDefault;
	public static int cenaCilvekam, mantojumsCilvekam, mantojumsCilvekamPaika;
	
	public static double dzimstotDefectionChance;
	
	
	public static double dCenaProc;
	public static double paikaPriceDefault;
	
	public static double sellLimitPaika, buyLimitPaika, sellLimitDefault, buyLimitDefault;
	
	
	// --------------------
	//zem�k misc parametri
	
	public static String komandaNosaukumsDefault;
	public static double komandaIzjuktChance;
}
