package galvenais;

import java.util.Date;

import konstantes.Parametri;

public class FPScalculator {
	
	protected static long simulationDelay; //Main while delay komponentei
	private static long calculationTimeStart; //starta un fini�a sal�dzin��anai
	
	public static int FPScalculationFrequency; //FPS apr��ina reseto�anas bie�ums (inicializ� Main klas�), j�b�t �eit, lai var�tu izvad�t grafiski
	private static int calculationStep=0; //solis FPS apr��ina ciklam, j�b�t te, lai netiktu resetots
	
	public static int FPSmin=1; //�iem main�gajiem j�b�t �eit, lai var�tu izvad�t grafiski
	public static long calculationTimeMax=1;
	
	
	protected static void start() {
		
		Date moments = new Date();
		calculationTimeStart = moments.getTime(); //te j�nolasa s�kuma laiks kadra ilguma apr��inam
		
	}
	
	protected static void finish() {
		Date moments = new Date(); //te v�lreiz j�nolasa laiks, lai noteiktu calculationTimeTemp
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
