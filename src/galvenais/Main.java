package galvenais;

import konstantes.CalculationTimeCalculator;
import konstantes.Parametri;
import calculations.lietas.LietuApskats;
import calculations.komandas.KomanduApskats;
import calculations.cilveki.CilvekuApskats;

public class Main {
	
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
	
	private static void initialize(){
		Parametri.initialize();
		
		Dati.initialize(); //sagatavo datub�zes
		
		CilvekuApskats.setup();
		
		grafika.main.SetupThread setupThread = new grafika.main.SetupThread(); //palai� grafisko da�u
		setupThread.start();
		
	}
}
