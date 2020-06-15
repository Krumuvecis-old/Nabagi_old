package calculations;

import calculations.cilveki.CilvekuManager;
import calculations.komandas.KomanduApskats;
import calculations.lietas.LietuApskats;
import calculations.komandas.Komanda;

import java.util.ArrayList;

public class Main {

	public static ArrayList<Komanda> komandasList = new ArrayList<Komanda>(); //komandu datubâze
	public static ArrayList<ArrayList<MapChunk>> laukums = new  ArrayList<ArrayList<MapChunk>>(); //laukums-karte
	
	public static boolean pauze=false;//, patsStarts=true;
	public static CalculationTimeCalculator calculationTimeCalculator = new CalculationTimeCalculator();

	public static void main(String... args) {
		
		InitializeManager.main(); //galvenâ inicializâcija - sagatavo pilnîgi visu tâlâkam darbam
		
		System.out.println("Main calculations thread initialized - starting.");
		while (true){ //ðis visu laiku atkârtojas -- galvenais cikls
			calculationTimeCalculator.time(true);
			
			if (!pauze) { galvenaisCikls(); }

			calculationTimeCalculator.time(false);
			try{
				Thread.sleep(50+calculationTimeCalculator.sleepT());

			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	private static void galvenaisCikls(){
		//ðis visu laiku atkârtojas, kad nav pauze


		//LaukumaApskats.main(); //te jâbût arî kartes un reljefa ciklam
		LietuApskats.main(); //viss kas saistîts ar pa  zemi izmçtâtajâm lietâm
		KomanduApskats.main(); //viss kas saistîts ar komandâm
		CilvekuManager.main(); //viss kas saistîts ar cilvçkiem

	}


}
