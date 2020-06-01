package galvenais;

import konstantes.Parametri;
import dataBase.Dati;
import cilveki.CilvekuApskats;

public class Main {
	
	public static boolean pauze=true;//, patsStarts=true;
	
	
	public static void main(String... args) {
		
		initialize(); //galven� inicializ�cija - sagatavo piln�gi visu t�l�kam darbam
		
		System.out.println("Main while() starting.");
		while (true){ //�is visu laiku atk�rtojas
			
			FPScalculator.start();
			
			if (!pauze) {
				System.out.println("---------- galven� cikla s�kums ----------");
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
		FPScalculator.FPScalculationFrequency=Parametri.FPScalculationFrequency; //�� ir FPS kalkulatora inicializ�cija
		
		Dati.initialize(); //sagatavo datub�zes
		
		CilvekuApskats.setup();
		
		grafika.setup.SetupThread setupThread = new grafika.setup.SetupThread(); //palai� grafisko da�u
		setupThread.start();
		
	}
}
