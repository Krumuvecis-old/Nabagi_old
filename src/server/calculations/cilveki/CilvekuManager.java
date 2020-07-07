package server.calculations.cilveki;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import server.calculations.KonstantesUniversal;
import server.calculations.cilveki.ai.CilvekuAI;
import server.calculations.konstantes.Cilveku;
import server.calculations.Main;
import server.calculations.konstantes.Komandu;


public class CilvekuManager {

	public static void main() {
		if(Main.cilvekuList.size() > Cilveks.rekords)
			Cilveks.rekords = Main.cilvekuList.size(); //rekorda update

		for (String vards : Main.cilvekuList.keySet()) galvenaisApskats(vards); //sadursmes un kontrolçjamâs darbîbas

		DefaultDarbibas.main(); //healing&hunger, nâvespârbaude, kustîba utml
	}
	
	private static void galvenaisApskats(String vards) {

		lootApskatsSadursmei(vards);

		if (!Main.cilvekuList.get(vards).autoPilot) {
			//spçlçtâjs kontrolç cilvçku
			System.out.println("spçlçtâja paðvadîbas funkcija vçl nav gatava");

		} else CilvekuAI.main(vards); //autopilots

	}

	private static void lootApskatsSadursmei(String vards) { //cilvçks apskata lietas, kas izmçtâtas pa karti
//		Location location = Cilveks.cilvekuListPilnais.get(numurs);
//		Cilveks cilveks = Cilveks.getPlayer(location);
//
//		double resnums=Cilveku.resnumaKoefic*cilveks.hpmax;
//
//		int chunkViewDistance=1; //0 - redz tikai savu chunk, 1 - redz 1 uz visâm pusçm
//		for(int[] dChunkXY={-chunkViewDistance,-chunkViewDistance}; dChunkXY[0]<=chunkViewDistance; dChunkXY[0]++){
//			for(dChunkXY[1]=-chunkViewDistance; dChunkXY[1]<=chunkViewDistance; dChunkXY[1]++){
//				MapChunk chunk = Main.laukums.get(location.chunkXY[0]+dChunkXY[0]).get(location.chunkXY[1]+dChunkXY[1]);
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
//					if(distance <= (resnums + resnumsLietai) / 2) { //paòem jebkâdu lietu, ja saskaras
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
			String vards = Cilveku.vardsDefault + (Cilveks.maxCilveks + 1);

			double x = KonstantesUniversal.mapChunkW * r.nextDouble(),
					y = KonstantesUniversal.mapChunkW * r.nextDouble(),
					v = 0,
					fi = 360 * r.nextDouble();

			List<Integer> chunkXY = new ArrayList<>();
			chunkXY.add(r.nextInt(KonstantesUniversal.mapChunkCountX)); //x
			chunkXY.add(r.nextInt(KonstantesUniversal.mapChunkCountY)); //y

			Koord xyz = new Koord(x, y, v, fi, chunkXY);

			double vmax = Cilveku.vmax,
					omega = Cilveku.ommax;

			double hpmax = Cilveku.hpmax,
					hp = hpmax * (0.5 + 0.5 * r.nextDouble()),
					paika = Cilveku.paikaMax;

			double R1 = Cilveku.RMax * (0.5 + 0.5 * r.nextDouble()),
					R2 = Cilveku.RMax * Cilveku.R2koefic * (0.5 + 0.5 * r.nextDouble());

			double brunas = Cilveku.brunasMin + (Cilveku.brunasMax - Cilveku.brunasMin) * r.nextDouble(),
					stiprums = Cilveku.stiprumsMin + (Cilveku.stiprumsMax - Cilveku.stiprumsMin) * r.nextDouble(),
					gataviba = 100,
					drosme = 0.5 + r.nextDouble() / 2;

			String komanda;

//			if (randomKomandas) { //ja sâkumâ sâk sadalîti pa komandâm
//
//				double randomChance=0.5;
//
//				if (Main.komandasList.size()==0 || r.nextDouble()<randomChance) { // iespçja ka jauns spçlçtâjs taisîs  jaunu  komandu
//					KomanduApskats.jaunaKomanda(vards);
//					komanda = Main.komandasList.get(Main.komandasList.size()-1).nosaukums;
//				} else { //pievienojas kâdai no jau esoðajâm
//					komanda = Main.komandasList.get(1 + r.nextInt(Main.komandasList.size()-1)).nosaukums;
//				}
//			} else { // ja visi sâk nulles komandâ
				komanda = Komandu.komandaNosaukumsFirst;
				Main.komandasList.get(komanda).pievienotiesKomandai(vards);
				//te jâbût funkcijai kur katram anarhistam komandâ izveido biedra statusu
//			}

			Main.cilvekuList.put(vards,
					new Cilveks(vards,
							xyz, vmax, omega,
							hp, hpmax, paika,
							R1, R2,
							brunas, stiprums, gataviba, drosme,
							komanda));
		}
		
	}
	
	
	
}
