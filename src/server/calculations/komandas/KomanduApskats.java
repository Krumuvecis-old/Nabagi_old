package server.calculations.komandas;

import java.util.Random;

import server.calculations.cilveki.Cilveks;
import server.dataBase.DataBase;


public class KomanduApskats {
	
	public static int komanduVestureMaksimums=0;
	public static String komanduVestureLielakaKomanda="0";
	
	public static void main() {
		for(String nosaukums : DataBase.komandasList.keySet()) { //apskata visas komandas

			DataBase.komandasList.get(nosaukums).playerCleanup(nosaukums); //atjauno biedru sarakstu tâlâkiem aprçíiniem

			if (nosaukums.equals(KomanduKonstantes.komandaNosaukumsFirst)) continue; //pirmo komandu tâlâk neapskata
			
			
			if (DataBase.komandasList.get(nosaukums).biedri.size()>0){ //pârbaudu, lai nav jâmaïâs cauri tukðajâm komandâm
				komanduVesture(nosaukums);
				komanduApskats(nosaukums);
			}

			if (DataBase.komandasList.get(nosaukums).biedri.size()<=0){ //ðis ir komandu cleanup
				DataBase.komandasList.remove(nosaukums);
			}
		}
	}

	private static void komanduVesture(String nosaukums) {
		Komanda komanda = DataBase.komandasList.get(nosaukums);
		int skaits = komanda.biedri.size();

		if (skaits > komanda.rekords) { //nosaka personîgo rekordu
			komanda.rekords = skaits;
		}

		if (skaits > komanduVestureMaksimums) { //meklç vçsturisko komandu-rekordisti
			komanduVestureMaksimums = skaits;
			komanduVestureLielakaKomanda = nosaukums;
		}
	}
	
	private static void komanduApskats(String nosaukums) {
		Komanda komanda= DataBase.komandasList.get(nosaukums);
		
		komanda.mekleKarali(nosaukums);
		
		if (komanda.karalis.equals("")) navKaralis(nosaukums); //ja komandâ nav atrodams galvenais

		nakotnesKomanduApskats(komanda); //nepabeigtas nâkotnes idejas

	}

	private static void navKaralis(String nosaukums) { //jâpârtaisa
		Komanda komanda = DataBase.komandasList.get(nosaukums);
		Random r = new Random();

		if(r.nextDouble() < KomanduKonstantes.komandaIzjuktChance) { //iespçja, ka mirstot karalim, izjuks komanda

			for(String vards : komanda.biedri.keySet()) { //visus komandasbiedrus pârliek 0.komandâ
				Cilveks cilveks = DataBase.cilvekuList.get(vards);

				cilveks.komanda = KomanduKonstantes.komandaNosaukumsFirst; //visus ieliek 0.komandâ
				DataBase.komandasList.get(cilveks.komanda).pievienotiesKomandai(vards);

				komanda.pamestKomandu(vards); //izòem no biedru saraksta
			}

		} else { //karaïa titula pârdalîðana

			komanda.karalis = komanda.bagatakais;

			komanda.biedri.get(komanda.karalis).rangs = new int[]{0, 3};
			komanda.galvenais = komanda.karalis;
		}

	}

	private static void nakotnesKomanduApskats(Komanda komanda){
		//komandas izaugsme un citas darbîbas nâkotnç

//		if(!(komanda.karalis<0)) {//vçlreiz apskata visus, ja jauns karalis atrasts
//
//			for (int i = 0; i< komanda.biedruList.size(); i++) { //vçlreiz apskata visus komandasbiedrus
//				//if(i==komanda.karalis) continue;
//
//				//te varçtu pârskatît rangus vai veikt jebkâdas citas darbîbas
//			}
//		}
	}

}
