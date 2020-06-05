package calculations.cilveki;

import java.util.ArrayList;

import calculations.Main;
import calculations.komandas.Biedrs;
import calculations.lietas.Lieta;

public class Cilveks {
	
	public static int maxCilveks=0; //numer�cija v�rdu do�anai
	public static ArrayList<Biedrs> cilvekuListPilnais = new ArrayList<Biedrs>();
	public static int rekords=0;
	
	
	public String vards;
	public Koord xyz = new Koord(); // (x,y,v,fi) - visi double
	
	//prim�rie parametri
	public double vmax;
	public double ommax;
	public double hp;
	public double hpmax;
	
	public double paika;
	public double paikaMax;
	public double paikaMin;
	
	//sensorie parametri
	public double R1, R2; //redzesloks
	
	//c��as parametri
	public ArrayList<Lieta> inventory = new ArrayList<Lieta>();
	public ArrayList<Orderis> orderi = new ArrayList<Orderis>(); // (prece,perk(boolean),cena)
	//public ArrayList<Orderis> atminas = new ArrayList<Orderis>(); //atmi�as par tirdzniec�bas cen�m
	
	public double brunas;
	public double stiprums;
	public double gataviba;
	public double drosme;
	public String komanda;
	public int[] rangs; //0[x]-(zemnieki/amatnieki)&tirgo�i, 					1[x]-valdnieki&karot�ji
						
						//0,0-nabags - lasa �ener�tos objektus / str�d�			1,0 -apsargs - sarg� fermas un citus objektus
						//0,1-zemnieks - var uzb�v�t fermu 						1,1 -karot�js - staig� ap pili
						//0,2-tirgot�js	- iep�rk resursus un p�rdod kur vajag	1,2 -gvarde - staig� pil�/sarg� karali
						//0,3-ra�ot�js - iep�rk resursus un no tiem ra�o  citus	1,3 -dedicated karalis - dod visiem drosmi?
	
	//anim�cijai un testiem
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

			if (inventory.get(i).daudzums<=0) { //izdz�� tuk�os elementus

				inventory.remove(i);

				i--;
				continue;
			}

			if(numurs<0 && inventory.get(i).nosaukums.equals(nosaukums) && inventory.get(i).daudzums>0) { //ja atrod pirmo atbilsto�o
				numurs= i;

				daudzums+=inventory.get(i).daudzums;;
				if(!cleanup) break;

				continue;
			}

			if (inventory.get(i).nosaukums.equals(nosaukums) &&
					inventory.get(i).daudzums>0  && i!=numurs && numurs>=0) { //mekl� atbilsto�us elementus

				daudzums+=inventory.get(i).daudzums;

				inventory.get(numurs).daudzums += inventory.get(i).daudzums;
				inventory.get(i).daudzums=0; //sagatavo dublik�tus t�l�kai izdz��anai

			}

		}

		return numurs;
	}

}
