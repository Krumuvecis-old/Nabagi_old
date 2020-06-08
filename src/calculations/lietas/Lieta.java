package calculations.lietas;

import calculations.KonstantesUniversal;
import calculations.Main;

public class Lieta {
	public double x, y;
	public String nosaukums;
	public double izmers;
	public double daudzums;
	public double zelts;
	public double paika;
	public double masa;
	public double attack;
	public double defence;
	public double condition;

	public void drop(int[] chunkXY){
		Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.add(this);
	}

	public static Lieta newLieta(int tips, double daudzums, double x, double y){

		Lieta lieta = new Lieta(); //uztaisa jaunu lietu
		lieta.daudzums = daudzums;
		lieta.x=x;
		lieta.y=y;

		LietuPreseti preset = KonstantesUniversal.defaultLietas.get(tips); //salîdzina ar presetiem
		lieta.nosaukums = preset.nosaukums;
		lieta.izmers = preset.izmers;
		lieta.masa = preset.masa;
		lieta.daudzums = preset.izmers;
		lieta.zelts = preset.zelts;
		lieta.paika = preset.paika;
		lieta.attack = preset.attack;
		lieta.defence = preset.defence;
		lieta.condition = preset.condition;

		return lieta; //padod  atpakaï jau gatavu
	}
}