package calculations.cilveki;

import java.util.ArrayList;

import calculations.Main;
import calculations.komandas.Biedrs;
import calculations.lietas.Lieta;

public class Cilveks {
	
	public static int maxCilveks=0; //numerâcija vârdu doðanai
	public static ArrayList<Biedrs> cilvekuListPilnais = new ArrayList<Biedrs>();
	public static int rekords=0;
	
	
	public String vards;
	public Koord xyz = new Koord(); // (x,y,v,fi) - visi double
	
	//primârie parametri
	public double vmax;
	public double ommax;
	public double hp;
	public double hpmax;
	
	public double paika;
	public double paikaMax;
	public double paikaMin;
	
	//sensorie parametri
	public double R1, R2; //redzesloks
	
	//cîòas parametri
	public ArrayList<Lieta> inventory = new ArrayList<Lieta>();
	public ArrayList<Orderis> orderi = new ArrayList<Orderis>(); // (prece,perk(boolean),cena)
	//public ArrayList<Orderis> atminas = new ArrayList<Orderis>(); //atmiòas par tirdzniecîbas cenâm
	
	public double brunas;
	public double stiprums;
	public double gataviba;
	public double drosme;
	public String komanda;
	public int[] rangs; //0[x]-(zemnieki/amatnieki)&tirgoòi, 					1[x]-valdnieki&karotâji
						
						//0,0-nabags - lasa ìenerçtos objektus / strâdâ			1,0 -apsargs - sargâ fermas un citus objektus
						//0,1-zemnieks - var uzbûvçt fermu 						1,1 -karotâjs - staigâ ap pili
						//0,2-tirgotâjs	- iepçrk resursus un pârdod kur vajag	1,2 -gvarde - staigâ pilî/sargâ karali
						//0,3-raþotâjs - iepçrk resursus un no tiem raþo  citus	1,3 -dedicated karalis - dod visiem drosmi?
	
	//animâcijai un testiem
	public String darbiba;

	protected static void getCilvekuList(){
		for(int[] chunkXY = {0, 0}; chunkXY[0]< Main.laukums.size(); chunkXY[0]++) {
			for( ; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++) {

				for (int i=0; i<cilvekuListPilnais.size(); i++){
					Biedrs biedrs = new Biedrs();
					biedrs.chunkXY=chunkXY;
					biedrs.i=i;
					cilvekuListPilnais.add(biedrs);
				}

			}
		}
	}
	
	public static Cilveks getPlayer(int[] chunkXY, int i){
		return Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(i);
	}

	public int countInventory(String nosaukums, boolean cleanup) {

		int numurs=-1;
		@SuppressWarnings("unused")
		double daudzums=0;

		for (int i=0;i<inventory.size();i++) {

			if (inventory.get(i).daudzums<=0) { //izdzçð tukðos elementus

				inventory.remove(i);

				i--;
				continue;
			}

			if(numurs<0 && inventory.get(i).nosaukums.equals(nosaukums) && inventory.get(i).daudzums>0) { //ja atrod pirmo atbilstoðo
				numurs= i;

				daudzums+=inventory.get(i).daudzums;;
				if(!cleanup) break;

				continue;
			}

			if (inventory.get(i).nosaukums.equals(nosaukums) &&
					inventory.get(i).daudzums>0  && i!=numurs && numurs>=0) { //meklç atbilstoðus elementus

				daudzums+=inventory.get(i).daudzums;

				inventory.get(numurs).daudzums += inventory.get(i).daudzums;
				inventory.get(i).daudzums=0; //sagatavo dublikâtus tâlâkai izdzçðanai

			}

		}

		return numurs;
	}

}
