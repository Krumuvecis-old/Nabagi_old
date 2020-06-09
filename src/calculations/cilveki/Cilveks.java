package calculations.cilveki;

import calculations.KonstantesUniversal;
import calculations.Location;
import calculations.Main;
import calculations.konstantes.Cilveku;
import calculations.lietas.Lieta;

import java.util.ArrayList;
import java.util.Random;

public class Cilveks extends CilvekaParametri {

	public static int maxCilveks=0; //numerâcija vârdu doðanai
	public static ArrayList<Location> cilvekuListPilnais;
	public static int rekords=0;

	protected static void getCilvekuList(){
		cilvekuListPilnais = new ArrayList<Location>(); //nodzçð veco sarakstu
		for(int[] chunkXY = {0, 0}; chunkXY[0]< Main.laukums.size(); chunkXY[0]++) {
			for(chunkXY[1]=0; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++) {
				for (int i=0; i<Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++){
					Location location = new Location();
					location.chunkXY=chunkXY;
					location.i=i;
					cilvekuListPilnais.add(location);
				}
			}
		}
		if(cilvekuListPilnais.size() > rekords) rekords = cilvekuListPilnais.size(); //rekorda update
	}
	
	public static Cilveks getPlayer(Location location){
		return Main.laukums.get(location.chunkXY[0]).get(location.chunkXY[1]).cilvekiList.get(location.i);
	}

	public void trauma(double stiprums, double precizitate) {
		boolean  blockChance = brunas > ( stiprums * precizitate );
		if (!blockChance) {
			double hpLoss = stiprums * precizitate - brunas;
			hp -= hpLoss;
		}
	}

	public static void dzemdibas(String vards,
								 int[] chunkXY, Koord xyz, double vmax, double omega,
								 double hp, double hpmax, double paika, double R1, double R2,
								 double brunas, double stiprums, double gataviba, double drosme,
								 String komanda, int[] rangs) {

		Cilveks cilveks = new Cilveks();

		cilveks.xyz=xyz;
		cilveks.vards=vards;

		cilveks.vmax=vmax;				//primârie Parametri
		cilveks.omega=omega;
		cilveks.hp=hp;
		cilveks.hpmax=hpmax;
		cilveks.paika=paika;
		cilveks.paikaMax=Cilveku.paikaMax;
		cilveks.paikaMin=Cilveku.paikaMin;

		cilveks.R1=R1;					//redzesloks
		cilveks.R2=R2;

		cilveks.generateStartingInventory();

		cilveks.brunas=brunas;			//cîòas parametri
		cilveks.stiprums=stiprums;
		cilveks.gataviba=gataviba;
		cilveks.drosme=drosme;

		cilveks.rangs = rangs;
		cilveks.komanda=komanda;

		Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.add(cilveks);
	}

	public void generateStartingInventory(){

		Random r=new Random();
		double x = xyz.x, y = xyz.y;

		// zemâk sâkuma zelts
		String nosaukums = "Zelts";
		double daudzums = Cilveku.mantojumsCilvekamZelts;
		int tips=0;
		for (int i=1; i< KonstantesUniversal.defaultLietas.size(); i++){
			if (KonstantesUniversal.defaultLietas.get(i).nosaukums.equals(nosaukums)){
				tips=i;
			}
		}
		inventory.add(Lieta.newLieta(tips, daudzums, x, y));

		// zemâk sâkuma paika
		nosaukums = "Paika";
		daudzums = Cilveku.mantojumsCilvekamZelts;
		tips=0;
		for (int i=1; i< KonstantesUniversal.defaultLietas.size(); i++){
			if (KonstantesUniversal.defaultLietas.get(i).nosaukums.equals(nosaukums)){
				tips=i;
			}
		}
		inventory.add(Lieta.newLieta(tips, daudzums, x, y));

	}

	public int[] searchInventory(String nosaukums, boolean firstOnly) {
		int[] numuri=new int[]{};

		for (int i=0;i<inventory.size();i++) { //iet cauri visam inventory
			if (inventory.get(i).daudzums <= 0) { //izdzçð tukðos elementus
				inventory.remove(i);
				i--;
				continue;
			}
			//tâlâk tikai tie, kam daudzums>0

			if(inventory.get(i).nosaukums.equals(nosaukums)) { //ja sakrît nosaukumi

				if (numuri.length==0){ //ja tâds ir pirmais
					numuri=new int[]{i};
					if(firstOnly) break;
					continue;
				}
				//tâlak tikai ja sakrît nosaukumi, bet tâds jau iepriekð atrasts

				//pârvieto pilnîgi visu saturu uz pirmo elementu
				inventory.get(numuri[0]).daudzums += inventory.get(i).daudzums;
				inventory.get(i).daudzums=0;

				//vçlreiz pârbauda daudzumu pçc satura pârvietoðanas
				if(inventory.get(i).daudzums>0){
					//pieskaita sarakstam, ja nav tukðs
					int[] numuriTemp = new int[numuri.length + 1];
					for (int j=0; j<numuri.length; j++) { numuriTemp[j] = numuri[j]; } //nokopç esoðos elementus
					numuriTemp[numuriTemp.length - 1] = i; //kâ pçdçjo pieskaita jauniegûto numuru
					numuri  = numuriTemp;
				} else {
					//jâizdzçð lieta no inventory, jo ir tukðs dublikâts
					inventory.remove(i);
					i--;
					continue;
				}

			}
		}
		return numuri;
	}

	public double countItemAmount(int[] numuri){
		double summa=0;

		for (int i=0; i<numuri.length; i++){
			summa += inventory.get(numuri[i]).daudzums;
		}

		return summa;
	}

	public void orderuCleanup(){
		for (int i=0; i<orderi.size();i++){
			if (!orderi.get(i).checkStatus()){
				orderi.remove(i);
				i--;
			}
		}
	}

}
