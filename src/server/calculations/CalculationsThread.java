package server.calculations;

import server.calculations.cilveki.CilvekuManager;
import server.calculations.komandas.KomanduApskats;
import server.calculations.lietas.LietuApskats;

public class CalculationsThread implements Runnable {
	private static final String threadName = "CalculationsThread",
			consoleOut = threadName + ": ";

	public static boolean running = false, pauze = true;//, patsStarts=true;
	public static CalculationTimeCalculator calculationTimeCalculator = new CalculationTimeCalculator();

	public CalculationsThread(){
		running = true;
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println(consoleOut + "Running.");
		while (running){ //�is visu laiku atk�rtojas -- galvenais cikls
			calculationTimeCalculator.time(true);

			if (!pauze) galvenaisCikls();

			calculationTimeCalculator.time(false);
			try{
				Thread.sleep(calculationTimeCalculator.sleepT());
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(consoleOut + "Finished.");
	}

	private static void galvenaisCikls(){

		//LaukumaApskats.main(); //te j�b�t ar� kartes un reljefa ciklam
		LietuApskats.main(); //viss kas saist�ts ar pa zemi izm�t�taj�m liet�m
		KomanduApskats.main(); //viss kas saist�ts ar komand�m
		CilvekuManager.main(); //viss kas saist�ts ar cilv�kiem

	}



}
