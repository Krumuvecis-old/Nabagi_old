package calculations.cilveki;

import java.awt.Color;
import java.util.Random;

import calculations.KonstantesUniversal;
import calculations.Location;
import calculations.MapChunk;
import calculations.cilveki.ai.CilvekuAI;
import calculations.konstantes.Cilveku;
import calculations.Main;
import calculations.komandas.Komanda;
import calculations.komandas.KomanduApskats;
import calculations.lietas.Lieta;


public class CilvekuManager {

	
	public static void main() {

		Cilveks.getCilvekuList(); //vajadzîgs cilvçku apskatam, bet pçc tam vairs nav lietojams, jo netiek updeitots (neizmantot grafikai!!!)
		for (int i = 0; i< Cilveks.cilvekuListPilnais.size(); i++) galvenaisApskats(i);

		DefaultDarbibas.main();

	}
	
	private static void galvenaisApskats(int numurs) {
		Cilveks cilveks = Cilveks.getPlayer( Cilveks.cilvekuListPilnais.get(numurs) );
		Random r=new Random();


		//lootApskatsSadursmei(numurs);

		if (!cilveks.autoPilot) {
			//spçlçtâjs kontrolç cilvçku
			System.out.println("spçlçtâja paðvadîbas funkcija vçl nav gatava");

			// errori var bût zemâk
		} else CilvekuAI.main(numurs); //autopilots


	}

	private static void lootApskatsSadursmei(int numurs) { //cilvçks apskata lietas, kas izmçtâtas pa karti
		Location location = Cilveks.cilvekuListPilnais.get(numurs);
		Cilveks cilveks = Cilveks.getPlayer(location);

		double resnums=Cilveku.resnumaKoefic*cilveks.hpmax;

		int chunkViewDistance=1; //0 - redz tikai savu chunk, 1 - redz 1 uz visâm pusçm
		for(int[] dChunkXY={-chunkViewDistance,-chunkViewDistance}; dChunkXY[0]<=chunkViewDistance; dChunkXY[0]++){
			for(dChunkXY[1]=-chunkViewDistance; dChunkXY[1]<=chunkViewDistance; dChunkXY[1]++){
				MapChunk chunk = Main.laukums.get(location.chunkXY[0]+dChunkXY[0]).get(location.chunkXY[1]+dChunkXY[1]);

				for(int i = 0; i < chunk.lietas.size(); i++){
					Lieta lieta = chunk.lietas.get(i);

					double dx = dChunkXY[0] * KonstantesUniversal.mapChunkW +
									lieta.x - cilveks.xyz.x,

							dy = dChunkXY[1] * KonstantesUniversal.mapChunkW +
									lieta.y - cilveks.xyz.y;

					double distance = Math.hypot(dx, dy);

					double resnumsLietai=KonstantesUniversal.defaultLietas.get(0).izmers; //nosaka lietas resnumu
					for (int j=1; j<(KonstantesUniversal.defaultLietas.size()); j++) {
						if (lieta.nosaukums.equals(KonstantesUniversal.defaultLietas.get(j).nosaukums)) {
							resnumsLietai = KonstantesUniversal.defaultLietas.get(j).izmers;
							break;
						}
					}


					if(distance<=(resnums+resnumsLietai)/2) { //paòem jebkâdu lietu, ja saskaras
						cilveks.inventory.add(chunk.lietas.get(i));
						chunk.lietas.remove(i);
						i--;
						continue;
					}
				}

			}
		}

	}
	
	public static void initialize() {
		pirmieSpeletaji();
	}
	
	private static void pirmieSpeletaji() {
		boolean randomKomandas=Cilveku.randomKomandas;

		Random r = new Random();
		int skaits=Cilveku.sakumaCilveki;

		if(Main.komandasList.size()==0) { //pati pirmâ komanda

			Komanda pirmaKomanda=new Komanda();
			pirmaKomanda.nosaukums="Anarhija";
			pirmaKomanda.galvenais="Nulle";
			pirmaKomanda.krasa=new Color(255, 0, 0);

			Komanda.maxKomanda++;
			Main.komandasList.add(pirmaKomanda);

		}

		for(int i=0;i<skaits;i++) {
			Cilveks.maxCilveks++;
			String vards=Cilveku.vardsDefault+Cilveks.maxCilveks;
			int[] chunkXY = new int[]{r.nextInt(KonstantesUniversal.mapChunkCountX),
					r.nextInt(KonstantesUniversal.mapChunkCountY)};
			Koord xyz = new Koord();
			xyz.x = KonstantesUniversal.mapChunkW * r.nextDouble();
			xyz.y = KonstantesUniversal.mapChunkW * r.nextDouble();
			xyz.v = 0;
			xyz.fi = 360*r.nextDouble();

			double vmax=Cilveku.vmax,
					ommax=Cilveku.ommax;
			double hpmax=Cilveku.hpmax,
					hp=hpmax*(0.5+0.5*r.nextDouble());
			double paika=Cilveku.paikaMax;
			double R1=Cilveku.RMax*(0.5+0.5*r.nextDouble()),
					R2=Cilveku.RMax/Cilveku.R2koefic*(0.5+0.5*r.nextDouble());
			double brunas=Cilveku.brunasMin+(Cilveku.brunasMax-Cilveku.brunasMin)*r.nextDouble(),
					stiprums=Cilveku.stiprumsMin+(Cilveku.stiprumsMax-Cilveku.stiprumsMin)*r.nextDouble(),
					gataviba=100,
					drosme=0.5+r.nextDouble()/2;

			String komanda;
			int[] rangs = new int[] {0,0};

			if (randomKomandas) { //ja sâkumâ sâk sadalîti pa komandâm
				double randomChance=0.5;

				if (Main.komandasList.size()==0 || r.nextDouble()<randomChance) { // iespçja ka jauns spçlçtâjs taisîs  jaunu  komandu
					KomanduApskats.jaunaKomanda(vards);
					komanda = Main.komandasList.get(Main.komandasList.size()-1).nosaukums;
					rangs[0]=0;
					rangs[1]=0;
				} else { //pievienojas kâdai no jau esoðajâm
					komanda = Main.komandasList.get(1 + r.nextInt(Main.komandasList.size()-1)).nosaukums;
					rangs[0]=0;
					rangs[1]=0;
				}
			} else { // ja visi sâk nulles komandâ
				komanda = Main.komandasList.get(0).nosaukums;
				rangs[0]=0;
				rangs[1]=0;
			}

			Cilveks.dzemdibas(vards,chunkXY,xyz,vmax,ommax,hp,hpmax,paika,R1,R2,brunas,stiprums,gataviba,drosme,komanda,rangs);
		}
		
	}
	
	
	
}
