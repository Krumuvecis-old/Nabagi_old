package galvenais;

import java.util.Date;

import konstantes.Parametri;

public class FPScalculator {
	
	protected static long simulationDelay; //Main while delay komponentei
	private static long calculationTimeStart; //starta un finiða salîdzinâðanai
	
	public static int FPScalculationFrequency; //FPS aprçíina resetoðanas bieþums (inicializç Main klasç), jâbût ðeit, lai varçtu izvadît grafiski
	private static int calculationStep=0; //solis FPS aprçíina ciklam, jâbût te, lai netiktu resetots
	
	public static int FPSmin=1; //ðiem mainîgajiem jâbût ðeit, lai varçtu izvadît grafiski
	public static long calculationTimeMax=1;
	
	
	protected static void start() {
		
		Date moments = new Date();
		calculationTimeStart = moments.getTime(); //te jânolasa sâkuma laiks kadra ilguma aprçíinam
		
	}
	
	protected static void finish() {
		Date moments = new Date(); //te vçlreiz jânolasa laiks, lai noteiktu calculationTimeTemp
		long calculationTimeEnd = moments.getTime();
		long calculationTime=Math.max(1, calculationTimeEnd-calculationTimeStart);
		
		simulationDelay=Math.max(0, Parametri.simulationMaxDelay-calculationTime);
		int FPS=(int)(Math.max(1, 1000/(calculationTime+simulationDelay)));
		
		
		
		if(calculationStep>=FPScalculationFrequency) {
			calculationStep=0;
			FPSmin=FPS;
			calculationTimeMax=calculationTime;
		} else {
			FPSmin=Math.min(FPSmin, FPS);
			calculationTimeMax=Math.max(calculationTimeMax, calculationTime);
			calculationStep++;
		}
		
		
	}
	
	
}
