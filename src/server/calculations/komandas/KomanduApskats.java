package server.calculations.komandas;

import java.util.Random;

import server.calculations.Main;
import server.calculations.cilveki.Cilveks;
import server.calculations.konstantes.Komandu;


public class KomanduApskats {
	
	public static int komanduVestureMaksimums=0;
	public static String komanduVestureLielakaKomanda="0";
	
	public static void main() {
		for(String nosaukums : Main.komandasList.keySet()) { //apskata visas komandas

			Main.komandasList.get(nosaukums).playerCleanup(nosaukums); //atjauno biedru sarakstu t�l�kiem apr��iniem

			if (nosaukums.equals(Komandu.komandaNosaukumsFirst)) continue; //pirmo komandu t�l�k neapskata
			
			
			if (Main.komandasList.get(nosaukums).biedri.size()>0){ //p�rbaudu, lai nav j�ma��s cauri tuk�aj�m komand�m
				komanduVesture(nosaukums);
				komanduApskats(nosaukums);
			}

			if (Main.komandasList.get(nosaukums).biedri.size()<=0){ //�is ir komandu cleanup
				Main.komandasList.remove(nosaukums);
			}
		}
	}

	private static void komanduVesture(String nosaukums) {
		Komanda komanda = Main.komandasList.get(nosaukums);
		int skaits = komanda.biedri.size();

		if (skaits > komanda.rekords) { //nosaka person�go rekordu
			komanda.rekords = skaits;
		}

		if (skaits > komanduVestureMaksimums) { //mekl� v�sturisko komandu-rekordisti
			komanduVestureMaksimums = skaits;
			komanduVestureLielakaKomanda = nosaukums;
		}
	}
	
	private static void komanduApskats(String nosaukums) {
		Komanda komanda=Main.komandasList.get(nosaukums);
		
		komanda.mekleKarali(nosaukums);
		
		if (komanda.karalis.equals("")) navKaralis(nosaukums); //ja komand� nav atrodams galvenais

		nakotnesKomanduApskats(komanda); //nepabeigtas n�kotnes idejas

	}

	private static void navKaralis(String nosaukums) { //j�p�rtaisa
		Komanda komanda = Main.komandasList.get(nosaukums);
		Random r = new Random();

		if(r.nextDouble() < Komandu.komandaIzjuktChance) { //iesp�ja, ka mirstot karalim, izjuks komanda

			for(String vards : komanda.biedri.keySet()) { //visus komandasbiedrus p�rliek 0.komand�
				Cilveks cilveks = Main.cilvekuList.get(vards);

				cilveks.komanda = Komandu.komandaNosaukumsFirst; //visus ieliek 0.komand�
				Main.komandasList.get(cilveks.komanda).pievienotiesKomandai(vards);

				komanda.pamestKomandu(vards); //iz�em no biedru saraksta
			}

		} else { //kara�a titula p�rdal��ana

			komanda.karalis = komanda.bagatakais;

			komanda.biedri.get(komanda.karalis).rangs = new int[]{0, 3};
			komanda.galvenais = komanda.karalis;
		}

	}

	private static void nakotnesKomanduApskats(Komanda komanda){
		//komandas izaugsme un citas darb�bas n�kotn�

//		if(!(komanda.karalis<0)) {//v�lreiz apskata visus, ja jauns karalis atrasts
//
//			for (int i = 0; i< komanda.biedruList.size(); i++) { //v�lreiz apskata visus komandasbiedrus
//				//if(i==komanda.karalis) continue;
//
//				//te var�tu p�rskat�t rangus vai veikt jebk�das citas darb�bas
//			}
//		}
	}

}
