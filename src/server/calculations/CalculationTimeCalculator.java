package server.calculations;

import java.util.Date;

public class CalculationTimeCalculator {
	public long delayMin=20;
	
	private long startTime=1;
	public long calculationTime=1, totalFrameTime=delayMin;
	
	public void time(boolean start) {
		Date moments = new Date();
		if (start) {
			startTime = moments.getTime(); //te jânolasa sâkuma laiks kadra ilguma aprçíinam
		} else {
			calculationTime=Math.max(0, moments.getTime()-startTime); //te jânolasa laiks, lai noteiktu calculationTime
			
		}
	}
	
	public long sleepT() { //kadra aizkaves ilgums
		long sleep = Math.max(0, delayMin-calculationTime);
		totalFrameTime=calculationTime+sleep;
		
		checkMaxTime(sleep);
		
		return sleep;
	}
	
	
	public int timeCalculationFrequency=30; //maksimâlâ ilguma aprçíina resetoðanas bieþums, jâbût ðeit, lai varçtu izvadît grafiski
	private int calculationStep=0; //solis FPS aprçíina ciklam, jâbût te, lai netiktu resetots
	public long calculationTimeMax=1, totalFrameTimeMax=1;
	
	private void checkMaxTime(long sleep) {
		
		if(calculationStep>=timeCalculationFrequency) {
			calculationStep=0;
			calculationTimeMax=calculationTime;
			totalFrameTimeMax=totalFrameTime;
		} else {
			calculationTimeMax=Math.max(calculationTimeMax, calculationTime);
			totalFrameTimeMax=Math.max(totalFrameTimeMax, totalFrameTime);
			calculationStep++;
		}
	}
	
}
