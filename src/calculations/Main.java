package calculations;

import calculations.konstantes.*;
import calculations.lietas.Lieta;
import calculations.lietas.LietuApskats;
import calculations.komandas.Komanda;
import calculations.komandas.KomanduApskats;

import java.util.ArrayList;

import calculations.cilveki.Cilveks;
import calculations.cilveki.CilvekuApskats;

public class Main {

	public static ArrayList<Komanda> komandasList = new ArrayList<Komanda>(); //komandu datub�ze
	public static ArrayList<ArrayList<MapChunk>> laukums = new  ArrayList<ArrayList<MapChunk>>(); //laukums-karte
	
	public static boolean pauze=true;//, patsStarts=true;
	public static CalculationTimeCalculator calculationTimeCalculator = new CalculationTimeCalculator();
	
	
	
	public static void main(String... args) {
		
		initialize(); //galven� inicializ�cija - sagatavo piln�gi visu t�l�kam darbam
		
		System.out.println("Main while() starting.");
		while (true){ //�is visu laiku atk�rtojas
			calculationTimeCalculator.time(true);
			
			if (!pauze) {
				
				//te j�b�t ar� kartes un reljefa ciklam
				LietuApskats.main(); //viss kas saist�ts ar pa  zemi izm�t�taj�m liet�m
				KomanduApskats.main(); //viss kas saist�ts ar komand�m
				CilvekuApskats.main(); //viss kas saist�ts ar cilv�kiem
				
			}
			
			calculationTimeCalculator.time(false);
			try{
				Thread.sleep(calculationTimeCalculator.sleepT());
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private static void initialize() {



}
