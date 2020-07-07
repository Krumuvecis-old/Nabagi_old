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

	public static Map<String, Komanda> komandasList = new HashMap<>(); //komandu datub�ze
	public static Map<String, Cilveks> cilvekuList = new HashMap<>(); //sp�l�t�ju datub�ze
	public static Map<List<Integer>, MapChunk> laukums = new HashMap<>(); //laukums-karte
	
	public static boolean pauze=false;//, patsStarts=true;
	public static CalculationTimeCalculator calculationTimeCalculator = new CalculationTimeCalculator();

	public static void main(String... args) {
		InitializeManager.main(); //galven� inicializ�cija - sagatavo piln�gi visu t�l�kam darbam

		System.out.println("CalculationsThread: Running.");
		while (true){ //�is visu laiku atk�rtojas -- galvenais cikls
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

		//LaukumaApskats.main(); //te j�b�t ar� kartes un reljefa ciklam
		LietuApskats.main(); //viss kas saist�ts ar pa zemi izm�t�taj�m liet�m
		KomanduApskats.main(); //viss kas saist�ts ar komand�m
		CilvekuManager.main(); //viss kas saist�ts ar cilv�kiem

	}


}
