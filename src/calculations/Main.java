package calculations;

import calculations.cilveki.Cilveks;
import calculations.cilveki.CilvekuManager;
import calculations.komandas.KomanduApskats;
import calculations.lietas.LietuApskats;
import calculations.komandas.Komanda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static Map<String, Komanda> komandasList = new HashMap<>(); //komandu datubâze
	public static Map<String, Cilveks> cilvekuList = new HashMap<>(); //spçlçtâju datubâze
	public static Map<List<Integer>, MapChunk> laukums = new HashMap<>(); //laukums-karte
	
	public static boolean pauze=false;//, patsStarts=true;
	public static CalculationTimeCalculator calculationTimeCalculator = new CalculationTimeCalculator();

	public static void main(String... args) {
		InitializeManager.main(); //galvenâ inicializâcija - sagatavo pilnîgi visu tâlâkam darbam

		System.out.println("CalculationsThread: Running.");
		while (true){ //ðis visu laiku atkârtojas -- galvenais cikls
			calculationTimeCalculator.time(true);
			
			if (!pauze) galvenaisCikls();

			calculationTimeCalculator.time(false);
			try{
				Thread.sleep(50+calculationTimeCalculator.sleepT());
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	private static void galvenaisCikls(){

		//LaukumaApskats.main(); //te jâbût arî kartes un reljefa ciklam
		LietuApskats.main(); //viss kas saistîts ar pa zemi izmçtâtajâm lietâm
		KomanduApskats.main(); //viss kas saistîts ar komandâm
		CilvekuManager.main(); //viss kas saistîts ar cilvçkiem

	}


}
