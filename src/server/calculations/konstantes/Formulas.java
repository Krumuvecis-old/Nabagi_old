package server.calculations.konstantes;

import server.calculations.KonstantesUniversal;

import java.awt.Color;
import java.util.Random;

public class Formulas {
	
	public static double lenkaNoteiksana(double x1, double y1, double x2, double y2, int[] dChunkXY) {
		int chunkPlatums = KonstantesUniversal.mapChunkW;
		double x2Temp = x2 + dChunkXY[0] * chunkPlatums,
				y2Temp = y2 + dChunkXY[1] * chunkPlatums;

		return tangensi(x1, y1, x2Temp, y2Temp);
	}

	private static double tangensi(double x1, double y1, double x2, double y2){
		double dx = x2 - x1,
				dy = y2 - y1,
				distance=Math.hypot(dx, dy),
				alfa;

		if(y2 > y1) alfa = Math.toDegrees(Math.acos((x2 - x1) / distance));
		else if(y2 == y1) {
			if (x2 >= x1) alfa = 0;
			else alfa = 180;
		} else alfa = 180 + Math.toDegrees(Math.acos((x1 - x2) / distance));

		return alfa;
	}
	
	public static double getHue(Color krasa) {
		float[] HSBList = new float[3];
		Color.RGBtoHSB(krasa.getRed(), krasa.getGreen(), krasa.getBlue(), HSBList);
		return HSBList[0];
	}
	
	public static double novirzeRandom(double skaitlis, double procenti) {
		return skaitlis * (1 + procenti * (1 - 2 * (new Random()).nextDouble()));
	}
	
}
