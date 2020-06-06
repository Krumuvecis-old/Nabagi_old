package calculations.komandas;

import calculations.Main;
import calculations.cilveki.Cilveks;

import java.awt.Color;
import java.util.ArrayList;

public class Komanda {
	
	public static int maxKomanda=0; //numerâcija nosaukuma doðanai, lai neatkârtotos nosaukumi

	public String nosaukums;
	public String galvenais;
	public Color krasa;

	public ArrayList<Biedrs> biedruList = new ArrayList<Biedrs>();
	public int karalis=-1, bagatakais=-1, rekords=0;

	void getBiedruList(int numurs){
		for(int[] chunkXY = {0, 0}; chunkXY[0]< Main.laukums.size(); chunkXY[0]++) {
			for( ; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++) {

				ArrayList<calculations.cilveki.Cilveks> cilvekiList =
						Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList;

				for (int i=0; i<cilvekiList.size(); i++){
					if(Main.komandasList.get(numurs).nosaukums == cilvekiList.get(i).komanda){
						Biedrs biedrs = new Biedrs();
						biedrs.chunkXY=chunkXY;
						biedrs.i=i;
						biedruList.add(biedrs);
					}
				}

			}
		}
	}

	protected void mekleKarali(int numurs) {
		karalis=-1;
		bagatakais = -1;
		double bagatiba=0;

		for (int i = 0; i< biedruList.size(); i++) {
			Cilveks cilveks = Main.laukums.get(biedruList.get(i).chunkXY[0]).get(biedruList.get(i).chunkXY[1]).cilvekiList.get(i);

			if(cilveks.komanda.equals(nosaukums)) {//apskata  visus komandas locekïus

				if (cilveks.vards.equals(galvenais)) { // meklç galveno un atrod
					karalis=i;
				}


				int zeltsNr = cilveks.countInventory("Zelts", false);
				double zeltsSum=0;
				if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;

				if(bagatakais<0||bagatiba<zeltsSum) {
					bagatiba=zeltsSum;
					bagatakais=i;
				}
			}
		}
	}

}
