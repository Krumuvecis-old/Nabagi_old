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
		while (running){ //ðis visu laiku atkârtojas -- galvenais cikls
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

		//LaukumaApskats.main(); //te jâbût arî kartes un reljefa ciklam
		LietuApskats.main(); //viss kas saistîts ar pa zemi izmçtâtajâm lietâm
		KomanduApskats.main(); //viss kas saistîts ar komandâm
		CilvekuManager.main(); //viss kas saistîts ar cilvçkiem

	}



}
