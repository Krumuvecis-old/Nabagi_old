package calculations.konstantes;

import java.awt.Color;
import java.util.Random;

public class Formulas {
	
	public static double lenkaNoteiksana(double x1, double y1, double x2, double y2) {
		double alfa, distance=Math.hypot(x1-x2, y1-y2);
		
		if(y2>y1) {
			alfa=Math.toDegrees(Math.acos((x2-x1)/distance));
		} else if(y2==y1) {
			if(x2>=x1) { alfa=0;} else { alfa=180;}
		} else {
			alfa=180+Math.toDegrees(Math.acos((x1-x2)/distance));
		}
		
		return alfa;
	}
	
	public static double getHue(Color krasa) {
		float[] HSBList=new float[3];
		Color.RGBtoHSB(krasa.getRed(), krasa.getGreen(), krasa.getBlue(), HSBList);
		return HSBList[0];
	}
	
	public static double novirzeRandom(double x0, double procenti) {
		Random r = new Random();
		return x0*( 1 + procenti*( 1 - 2*r.nextDouble() ) );
	}
	
}
