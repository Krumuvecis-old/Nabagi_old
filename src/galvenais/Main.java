package galvenais;

import konstantes.Parametri;
import dataBase.Dati;
import cilveki.CilvekuApskats;

public class Main {
	
	public static boolean pauze=true;//, patsStarts=true;
	
	
	public static void main(String... args) {
		
		initialize(); //galvenâ inicializâcija - sagatavo pilnîgi visu tâlâkam darbam
		
		System.out.println("Main while() starting.");
		while (true){ //ðis visu laiku atkârtojas
			
			FPScalculator.start();
			
			if (!pauze) {
				System.out.println("---------- galvenâ cikla sâkums ----------");
				GalvenaisCikls.main();
			}
			
			FPScalculator.finish();
			
			try{
				Thread.sleep(FPScalculator.simulationDelay);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private static void initialize(){
		Parametri.initialize();
		FPScalculator.FPScalculationFrequency=Parametri.FPScalculationFrequency; //ðî ir FPS kalkulatora inicializâcija
		
		Dati.initialize(); //sagatavo datubâzes
		
		CilvekuApskats.setup();
		
		grafika.setup.SetupThread setupThread = new grafika.setup.SetupThread(); //palaiþ grafisko daïu
		setupThread.start();
		
	}
}
