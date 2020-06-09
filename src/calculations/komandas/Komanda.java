package calculations.komandas;

import calculations.Location;
import calculations.Main;
import calculations.cilveki.Cilveks;

import java.awt.Color;
import java.util.ArrayList;

public class Komanda {
	
	public static int maxKomanda=0; //numerâcija nosaukuma doðanai, lai neatkârtotos nosaukumi

	public String nosaukums;
	public String galvenais;
	public Color krasa;

	public ArrayList<Location> biedruList = new ArrayList<Location>();
	public int karalis=-1, bagatakais=-1, rekords=0;

	void getBiedruList(int numurs){
		for(int[] chunkXY = {0, 0}; chunkXY[0]< Main.laukums.size(); chunkXY[0]++) {
			for(chunkXY[1]=0; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++) {

				ArrayList<calculations.cilveki.Cilveks> cilvekiList =
						Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList;

				for (int i=0; i<cilvekiList.size(); i++){
					if(Main.komandasList.get(numurs).nosaukums == cilvekiList.get(i).komanda){
						Location biedrs = new Location();
						biedrs.chunkXY=chunkXY;
						biedrs.i=i;
						biedruList.add(biedrs);
					}
				}

			}
		}
	}

	protected void mekleKarali() {
		karalis=-1;
		bagatakais = -1;
		double bagatiba=0;

		for (int i = 0; i< biedruList.size(); i++) {
			Cilveks cilveks = Cilveks.getPlayer(biedruList.get(i));

			if(cilveks.komanda.equals(nosaukums)) {//apskata  visus komandas locekïus

				if (cilveks.vards.equals(galvenais)) { // meklç galveno un atrod
					karalis=i;
				}

				double zeltsSum = cilveks.countItemAmount(
						cilveks.searchInventory("Zelts", true) );

				if(bagatakais<0||bagatiba<zeltsSum) {
					bagatiba=zeltsSum;
					bagatakais=i;
				}
			}
		}
	}

}
