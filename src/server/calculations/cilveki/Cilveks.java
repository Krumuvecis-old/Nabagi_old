package server.calculations.cilveki;

import server.calculations.Main;
import server.calculations.konstantes.Cilveku;
import server.calculations.lietas.Lieta;

public class Cilveks extends CilvekaParametri {

	public static int maxCilveks = 0, //numerâcija vârdu doðanai
			rekords = 0;

	public Cilveks(String vards, Koord _xyz, double _vmax, double _omega,
				   double _hp, double _hpmax, double _paika, double _R1, double _R2,
				   double _brunas, double _stiprums, double _gataviba, double _drosme,
				   String _komanda){
		xyz = _xyz;
		Main.laukums.get(xyz.chunkXY).cilvekiList.add(vards); //pievieno spçlçtâju arî laukumam
		vmax = _vmax;
		omega = _omega;

		hp = _hp;
		hpmax = _hpmax;
		paika = _paika;
		R1 = _R1;
		R2 = _R2;

		generateStartingInventory();

		brunas = _brunas;
		stiprums = _stiprums;
		gataviba = _gataviba;
		drosme = _drosme;

		komanda = _komanda;

		maxCilveks++;
	}

	public void trauma(double stiprums, double precizitate) {
		boolean  blockChance = brunas > (stiprums * precizitate);
		if (!blockChance) {
			double hpLoss = stiprums * precizitate - brunas;
			hp -= hpLoss;
		}
	}

	public void generateStartingInventory(){
		double x = xyz.x, y = xyz.y;

		inventory.add(new Lieta("Zelts", Cilveku.mantojumsCilvekamZelts, x, y));
		inventory.add(new Lieta("Paika", Cilveku.mantojumsCilvekamPaika, x, y));

	}

	public int[] searchInventory(String nosaukums, boolean firstOnly) {
		int[] numuri=new int[]{};

		for (int i=0; i<inventory.size(); i++) { //iet cauri visam inventory
			if (inventory.get(i).daudzums <= 0) { //izdzçð tukðos elementus
				inventory.remove(i);
				i--;
				continue;
			}
			//tâlâk tikai tie, kam daudzums>0

			if(inventory.get(i).tips.equals(nosaukums)) { //ja sakrît nosaukumi

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
