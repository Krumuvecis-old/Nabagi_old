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

	public static ArrayList<Komanda> komandasList; //komandu datub�ze
	public static ArrayList<ArrayList<MapChunk>> laukums; //laukums-karte
	
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
		initializeKonstantes();
		komandasList = new ArrayList<Komanda>();
		initializeLaukums();
		
		CilvekuApskats.setup();
		
		grafika.main.SetupThread setupThread = new grafika.main.SetupThread(); //palai� grafisko da�u
		setupThread.start();
		
	}

	private static void initializeKonstantes() {

		KonstantesUniversal.initialize();

		Lietu.initialize();

		Komandu.initialize();
		Cilveku.initialize();

		Fizikas.initialize();
		Grafiskie.initialize();

	}

	private static void  initializeLaukums() {
		int mapChunkCountX = KonstantesUniversal.mapChunkCountX,
				mapChunkCountY = KonstantesUniversal.mapChunkCountY;
		laukums =  new  ArrayList<ArrayList<MapChunk>>();

		for (int i=0; i<mapChunkCountX;i++){
			laukums.add(new ArrayList<MapChunk>());
			for (int j=0; j<mapChunkCountY;j++){
				MapChunk chunk = new MapChunk();
				laukums.get(i).add(chunk);
				laukums.get(i).get(j).initialize();

			}
		}
	}


}
