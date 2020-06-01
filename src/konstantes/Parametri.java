package konstantes;

import java.awt.Color;

//import java.util.ArrayList;

public class Parametri { //visi default koeficienti un parametri vienkopus
	
	public static void initialize() {
		Galvenie.initialize();
		Grafiskie.initialize();
		Generators.initialize();
		Fizikas.initialize();
		Cilvekiem.initialize();
		Misc.initialize();
		
		System.out.println("parametri inicializçti");
	}
	
	// --------------------
	//zemâk galvenie parametri
	
	public static String versija;
	public static long simulationMaxDelay;
	
	
	// --------------------
	//zemâk grafiskie parametri
	
	public static String ekranaNosaukums;
	
	public static int FPScalculationFrequency;
	
	public static double cilvekiKrasaSaturation, cilvekiKrasaBrightnessMin, cilvekiKrasaBrightnessMax;
	
	public static Color laukumaKrasa, malasKrasa;
	public static Color lietasColorZelts, lietasColorPaika, lietasColorDefault;
	
	public static Color komandasColorDefault;
	public static boolean komandasBannedColors;
	public static double[] komandasBannedColorList;
	
	
	// --------------------
	//zemâk random ìeneratoru parametri
	
	public static int sakumaCilveki;
	public static boolean randomKomandas;
	
	public static double goldGenRate, goldGenMin, goldGenMax;
	public static double paikaGenRate, paikaGenMin, paikaGenMax;
	
	
	// --------------------
	//zemâk fizikas  parametri
	
	public static int platums, augstums;
	public static int mala;
	
	public static int lietasResnums; // default neklasificçtai lietai
	public static int zeltaResnums, paikasResnums;
	
	public static double resnumaKoefic; //cilvçka resnuma koeficients (noteikðanai pçc hpMax)
	//public static double vKoefic=0.1;
	
	
	// --------------------
	//zemâk cilvçku parametri
	
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
	
	
	public static double dCenaProc;
	public static double paikaPriceDefault;
	
	public static double sellLimitPaika, buyLimitPaika, sellLimitDefault, buyLimitDefault;
	
	
	// --------------------
	//zemâk misc parametri
	
	public static String komandaNosaukumsDefault;
	public static double komandaIzjuktChance;
}
