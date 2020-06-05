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

	public static ArrayList<Komanda> komandasList; //komandu datubâze
	public static ArrayList<ArrayList<MapChunk>> laukums; //laukums-karte
	
	public static boolean pauze=true;//, patsStarts=true;
	public static CalculationTimeCalculator calculationTimeCalculator = new CalculationTimeCalculator();
	
	
	
	public static void main(String... args) {
		
		initialize(); //galvenâ inicializâcija - sagatavo pilnîgi visu tâlâkam darbam
		
		System.out.println("Main while() starting.");
		while (true){ //ðis visu laiku atkârtojas
			calculationTimeCalculator.time(true);
			
			if (!pauze) {
				
				//te jâbût arî kartes un reljefa ciklam
				LietuApskats.main(); //viss kas saistîts ar pa  zemi izmçtâtajâm lietâm
				KomanduApskats.main(); //viss kas saistîts ar komandâm
				CilvekuApskats.main(); //viss kas saistîts ar cilvçkiem
				
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
		
		grafika.main.SetupThread setupThread = new grafika.main.SetupThread(); //palaiþ grafisko daïu
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
