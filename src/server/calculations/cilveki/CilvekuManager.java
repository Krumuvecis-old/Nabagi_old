package server.calculations.cilveki;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import server.calculations.cilveki.ai.CilvekuAI;
import server.calculations.komandas.KomanduKonstantes;
import server.calculations.laukums.Laukums;
import server.dataBase.DataBase;


public class CilvekuManager {

	public static void main() {
		if(DataBase.cilvekuList.size() > Cilveks.rekords)
			Cilveks.rekords = DataBase.cilvekuList.size(); //rekorda update

		for (String vards : DataBase.cilvekuList.keySet()) galvenaisApskats(vards); //sadursmes un kontrol�jam�s darb�bas

		DefaultDarbibas.main(); //healing&hunger, n�vesp�rbaude, kust�ba utml
	}
	
	private static void galvenaisApskats(String vards) {

		lootApskatsSadursmei(vards);

		if (!DataBase.cilvekuList.get(vards).autoPilot) {
			//sp�l�t�js kontrol� cilv�ku
			System.out.println("sp�l�t�ja pa�vad�bas funkcija v�l nav gatava");

		} else CilvekuAI.main(vards); //autopilots

	}

	private static void lootApskatsSadursmei(String vards) { //cilv�ks apskata lietas, kas izm�t�tas pa karti
//		Location location = Cilveks.cilvekuListPilnais.get(numurs);
//		Cilveks cilveks = Cilveks.getPlayer(location);
//
//		double resnums=CilvekuKonstantes.resnumaKoefic*cilveks.hpmax;
//
//		int chunkViewDistance=1; //0 - redz tikai savu chunk, 1 - redz 1 uz vis�m pus�m
//		for(int[] dChunkXY={-chunkViewDistance,-chunkViewDistance}; dChunkXY[0]<=chunkViewDistance; dChunkXY[0]++){
//			for(dChunkXY[1]=-chunkViewDistance; dChunkXY[1]<=chunkViewDistance; dChunkXY[1]++){
//				MapChunk chunk = CalculationsThread.laukums.get(location.chunkXY[0]+dChunkXY[0]).get(location.chunkXY[1]+dChunkXY[1]);
//
//				for(int i = 0; i < chunk.lietas.size(); i++){
//					Lieta lieta = chunk.lietas.get(i);
//
//					double dx = dChunkXY[0] * KonstantesUniversal.mapChunkW +
//									lieta.x - cilveks.xyz.x,
//
//							dy = dChunkXY[1] * KonstantesUniversal.mapChunkW +
//									lieta.y - cilveks.xyz.y;
//
//					double distance = Math.hypot(dx, dy);
//
//					double resnumsLietai=KonstantesUniversal.defaultLietas.get(0).izmers; //nosaka lietas resnumu
//					for (int j=1; j<(KonstantesUniversal.defaultLietas.size()); j++) {
//						if (lieta.nosaukums.equals(KonstantesUniversal.defaultLietas.get(j).nosaukums)) {
//							resnumsLietai = KonstantesUniversal.defaultLietas.get(j).izmers;
//							break;
//						}
//					}
//
//
//					if(distance <= (resnums + resnumsLietai) / 2) { //pa�em jebk�du lietu, ja saskaras
//						cilveks.inventory.add(chunk.lietas.get(i));
//						chunk.lietas.remove(i);
//						i--;
//						continue;
//					}
//				}
//
//			}
//		}

	}
	
	public static void pirmieSpeletaji(int skaits, boolean randomKomandas) {
		Random r = new Random();

		for(int i=0; i<skaits; i++) {
			String vards = CilvekuKonstantes.vardsDefault + (Cilveks.maxCilveks + 1);

			double x = Laukums.mapChunkW * r.nextDouble(),
					y = Laukums.mapChunkW * r.nextDouble(),
					v = 0,
					fi = 360 * r.nextDouble();

			List<Integer> chunkXY = new ArrayList<>();
			chunkXY.add(r.nextInt(Laukums.mapChunkCountX)); //x
			chunkXY.add(r.nextInt(Laukums.mapChunkCountY)); //y

			Koord xyz = new Koord(x, y, v, fi, chunkXY);

			double vmax = CilvekuKonstantes.vmax,
					omega = CilvekuKonstantes.ommax;

			double resnums = CilvekuKonstantes.resnumsDefault,
					R1 = CilvekuKonstantes.RMax * (0.5 + 0.5 * r.nextDouble()),
					R2 = CilvekuKonstantes.RMax * CilvekuKonstantes.R2koefic * (0.5 + 0.5 * r.nextDouble());

			double hpmax = CilvekuKonstantes.hpmax,
					paikaMax = CilvekuKonstantes.paikaMax,
					paikaMin = CilvekuKonstantes.paikaMin;

			double brunas = CilvekuKonstantes.brunasMin + (CilvekuKonstantes.brunasMax - CilvekuKonstantes.brunasMin) * r.nextDouble(),
					stiprums = CilvekuKonstantes.stiprumsMin + (CilvekuKonstantes.stiprumsMax - CilvekuKonstantes.stiprumsMin) * r.nextDouble(),
					gataviba = 100,
					drosme = 0.5 + r.nextDouble() / 2;

			String komanda;

//			if (randomKomandas) { //ja s�kum� s�k sadal�ti pa komand�m
//
//				double randomChance=0.5;
//
//				if (CalculationsThread.komandasList.size()==0 || r.nextDouble()<randomChance) { // iesp�ja ka jauns sp�l�t�js tais�s  jaunu  komandu
//					KomanduApskats.jaunaKomanda(vards);
//					komanda = CalculationsThread.komandasList.get(CalculationsThread.komandasList.size()-1).nosaukums;
//				} else { //pievienojas k�dai no jau eso�aj�m
//					komanda = CalculationsThread.komandasList.get(1 + r.nextInt(CalculationsThread.komandasList.size()-1)).nosaukums;
//				}
//			} else { // ja visi s�k nulles komand�
				komanda = KomanduKonstantes.komandaNosaukumsFirst;
				DataBase.komandasList.get(komanda).pievienotiesKomandai(vards);
				//te j�b�t funkcijai kur katram anarhistam komand� izveido biedra statusu
//			}

			DataBase.cilvekuList.put(vards,
					new Cilveks(vards,
							xyz, vmax, omega,
							resnums, R1, R2,
							hpmax, paikaMax, paikaMin,
							brunas, stiprums, gataviba, drosme,
							komanda));

			//sarandomiz� izsalkuma l�meni s�kuma sp�l�t�jiem
			DataBase.cilvekuList.get(vards).paika = paikaMin + (paikaMax - paikaMin) * r.nextDouble();

		}
	}
	
	
	
}
