package server.calculations;

import server.calculations.cilveki.CilvekuManager;
import server.calculations.komandas.KomanduApskats;
import server.calculations.lietas.LietuApskats;
import server.dataBase.DataBase;

import java.util.ArrayList;
import java.util.List;

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

		terrainApskats(); //LaukumaApskats.main(); //kartes un reljefa cikli - viss kas saistîts ar reljefu un laukuma izmaiòâm
		LietuApskats.main(); //viss kas saistîts ar pa zemi izmçtâtajâm lietâm
		KomanduApskats.main(); //viss kas saistîts ar komandâm
		CilvekuManager.main(); //viss kas saistîts ar cilvçkiem

		//kaut kur jâievieto arî hipotçtisku çku apskatu?

	}

	private static void terrainApskats(){
		//ðo varçtu paturpinât un pârnest uz citu klasi

		List<Integer> chunkXY = new ArrayList<>();
		chunkXY.add(0);
		chunkXY.add(0);

		for (chunkXY.set(0, 0); chunkXY.get(0) < DataBase.mapChunkCountX; chunkXY.set(0, chunkXY.get(0) + 1)){
			for (chunkXY.set(1, 0); chunkXY.get(1) < DataBase.mapChunkCountY; chunkXY.set(1, chunkXY.get(1) + 1)){

				List<Integer> cellXY = new ArrayList<>();
				cellXY.add(0);
				cellXY.add(0);

				for (cellXY.set(0, 0); cellXY.get(0) < DataBase.mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
					for (cellXY.set(1, 0); cellXY.get(1) < DataBase.mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){
						DataBase.laukums.get(chunkXY).mapCells.get(cellXY).updateValues();
					}
				}
			}
		}
	}



}
