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
	
	public static ArrayList<Cilveks> cilvekiList; //cilvçku datubâze
	public static ArrayList<Komanda> komandasList; //komandu datubâze
	public static ArrayList<Lieta> lietas; //loot datubâze
	
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
	
	private static void initialize(){
		initializeKonstantes();
		
		cilvekiList = new ArrayList<Cilveks>();
		komandasList = new ArrayList<Komanda>();
		lietas = new ArrayList<Lieta>();
		
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
}
