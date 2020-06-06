package calculations.cilveki;

import java.awt.Color;
import java.util.Random;

import calculations.KonstantesUniversal;
import calculations.MapChunk;
import calculations.komandas.Biedrs;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Fizikas;
import calculations.konstantes.Formulas;
import calculations.Main;
import calculations.komandas.Komanda;
import calculations.komandas.KomanduApskats;
import calculations.lietas.Lieta;


public class CilvekuApskats {
	
	static double vmax, ommax;
	
	static double resnumaKoefic; //HpMax attiecîbai pret resnumu
	static int maxGataviba;
	static double RMax;
	static double R2koefic; //maksimâlais redzesloks un minimâlâ daïas koefic
	static double dRDzimstot; //redzesloka procentuâla izmaiòa vairojoties
	static double dvMaxDzimstot, dommaxDzimstot; //procentuâlâs izmaiòas dzimstot
	
	static int paikaMax, paikaMin;
	static double esanasDaudzums; //par vienu pilnu paikaMax
	static boolean navKoEstTemp; //funkcijai 
	
	
	static double paikaNepiec; //daudzums lîdz  kuram mçìinâs savâkt  paiku,  tad  skraidît apkârt
	
	static double brunasMax, brunasMin, dBrunasDzimstot, stiprumsMax, stiprumsMin, dStiprumsDzimstot;
	static String vardsDefault;
	static double cenaCilvekam;
	static double mantojumsCilvekamZelts;
	static double mantojumsCilvekamPaika;
	
	
	static double dCenaProc;
	
	public static void main() {
		Cilveks.getCilvekuList();

		for (int i = 0; i< Cilveks.cilvekuListPilnais.size(); i++) {
			galvenaisApskats(i);
		}

		for (int i = 0; i< Cilveks.cilvekuListPilnais.size(); i++) {
			calculations.komandas.Biedrs biedrs=Cilveks.cilvekuListPilnais.get(i);
			naavesPaarbaude(biedrs.chunkXY, biedrs.i); //nodzçð miruðos cilvçkus
		}
		
	}
	
	private static void galvenaisApskats(int numurs) {
		int[] chunkXY = Cilveks.cilvekuListPilnais.get(numurs).chunkXY;
		int i = Cilveks.cilvekuListPilnais.get(numurs).i;
		Cilveks cilveks= Cilveks.getPlayer(chunkXY, i);
		Random r=new Random();


		lootApskatsSadursmei(numurs);



		if (cilveks.autoPilot) {
			//spçlçtâjs kontrolç cilvçku

			// errori var bût zemâk
		} else CilvekuAI.main(numurs); //autopilots

		CilvekuDarbibas.main(numurs); //default nekontrolçjamas darbîbas visiem

		//errori var bût augstâk

		
		//ideâlai kustîbai pietrûkst:
			//leòía pagrieðana
			//ieskrieðanâs (paâtrinâjums)
		
		Kustiba.main(cilveks, numurs);

		healingAndHunger(chunkXY, i);
	}
	

	
	private static int zeltsTuvakaisNr, paikaTuvakaNr;
	private static double zeltsTuvakaisDist, paikaTuvakaDist;

	private static void lootApskatsSadursmei(int numurs) { //cilvçks apskata lietas, kas izmçtâtas pa karti
		Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
		Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,biedrs.i);

		double resnums=resnumaKoefic*cilveks.hpmax;
		//Random r=new Random();

		int chunkViewDistance=1; //0 - redz tikai savu chunk, 1 - redz 1 uz visâm pusçm
		for(int[] dChunkXY={-chunkViewDistance,-chunkViewDistance}; dChunkXY[0]<=chunkViewDistance; dChunkXY[0]++){
			for( ; dChunkXY[1]<=chunkViewDistance; dChunkXY[1]++){
				MapChunk chunk = Main.laukums.get(biedrs.chunkXY[0]+dChunkXY[0]).get(biedrs.chunkXY[1]+dChunkXY[1]);

				for(int i = 0; i < chunk.lietas.size(); i++){

					double dx = dChunkXY[0] * KonstantesUniversal.mapChunkW +
									chunk.lietas.get(i).x - cilveks.xyz.x,

							dy = dChunkXY[1] * KonstantesUniversal.mapChunkW +
									chunk.lietas.get(i).y - cilveks.xyz.y;

					double distance = Math.hypot(dx, dy);

					double resnumsJ; //nosaka lietas resnumu

					if (chunk.lietas.get(i).nosaukums.equals("Zelts")) { resnumsJ = Fizikas.zeltaResnums;
					} else if (chunk.lietas.get(i).nosaukums.equals("Paika")) { resnumsJ = Fizikas.paikasResnums;
					} else resnumsJ = Fizikas.lietasResnums; // default neklasificçtai lietai

					if(distance<=(resnums+resnumsJ)/2) { //paòem jebkâdu lietu, ja saskaras
						cilveks.inventory.add(chunk.lietas.get(i));
						chunk.lietas.remove(i);
						i--;
						continue;
					}
				}

			}
		}




	}

	protected static void healingAndHunger(int[] chunkXY, int numurs) {
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);

		double dHpRegen=Cilveku.healingRateDefault,
				dHpHungry=Cilveku.healthReductionRate,
				paikaD=Cilveku.paikaReductionDefault;

		if (cilveks.paika>=cilveks.paikaMin) { //ja pietiek pârtika, veseïojas
			if(cilveks.hp<cilveks.hpmax) cilveks.hp+=dHpRegen;
		} else if(cilveks.paika<=0) { //ja pârtika nav - zaudç Hp
			if(cilveks.hp>0) cilveks.hp-=dHpHungry;
		}

		if(cilveks.hp<0) cilveks.hp=0; //nolîdzina pie 0
		if(cilveks.hp>cilveks.hpmax) cilveks.hp=cilveks.hpmax; //nolîdzina pie hpmax

		//konstanti atòemâs paika
		if(cilveks.paika>0) cilveks.paika-=paikaD;
		if(cilveks.paika<0) cilveks.paika=0;
	}

	private static void naavesPaarbaude(int[] chunkXY, int numurs) {
		double lootDropDistance=10;
		Cilveks cilveks = Cilveks.getPlayer(chunkXY, numurs);
		if(cilveks.hp<=0) {
			for(int i=0; i<cilveks.inventory.size(); i++) {
				Lieta lieta = cilveks.inventory.get(i);

				Random r = new Random();
				lieta.x = Math.max(0, Math.min(KonstantesUniversal.mapChunkW,
						cilveks.xyz.x + lootDropDistance*(r.nextDouble()-0.5)*2 ));
				lieta.y = Math.max(0, Math.min(KonstantesUniversal.mapChunkW,
						cilveks.xyz.y + lootDropDistance*(r.nextDouble()-0.5)*2 ));
				Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.add(lieta);
				cilveks.inventory.remove(i);
				i--;
			}


			Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.remove(numurs);
		}
	}
	
	public static void setup() {
		
		initialize();
		pirmieSpeletaji();
		
	}
	
	private static void initialize() {
		
		vmax=Cilveku.vmax;
		ommax=Cilveku.ommax;
		resnumaKoefic=Fizikas.resnumaKoefic; //HpMax attiecîbai pret resnumu
		maxGataviba=Cilveku.maxGataviba;
		RMax=Cilveku.RMax; //maksimâlais redzesloks
		R2koefic=Cilveku.R2koefic; //minimâlâ redzesloka daïas koefic
		dRDzimstot=Cilveku.dRDzimstot; //redzesloka procentuâla izmaiòa vairojoties
		dvMaxDzimstot=Cilveku.dvMaxDzimstot; //procentuâlâs izmaiòas dzimstot
		dommaxDzimstot=Cilveku.dommaxDzimstot; //procentuâlâs izmaiòas dzimstot
		
		paikaMax=Cilveku.paikaMax;
		paikaMin=Cilveku.paikaMin;
		esanasDaudzums=Cilveku.esanasDaudzums; //par vienu pilnu paikaMax
		navKoEstTemp = false; //funkcijai 
		
		
		paikaNepiec = Cilveku.paikaNepiec; //daudzums lîdz  kuram mçìinâs savâkt  paiku,  tad  skraidît apkârt
		
		brunasMax=Cilveku.brunasMax;
		brunasMin=Cilveku.brunasMin;
		dBrunasDzimstot=Cilveku.dBrunasDzimstot;
		stiprumsMax=Cilveku.stiprumsMax;
		stiprumsMin=Cilveku.stiprumsMin;
		dStiprumsDzimstot=Cilveku.dStiprumsDzimstot;
		
		vardsDefault=Cilveku.vardsDefault;
		cenaCilvekam=Cilveku.cenaCilvekam;
		mantojumsCilvekamZelts=Cilveku.mantojumsCilvekamZelts;
		mantojumsCilvekamPaika=Cilveku.mantojumsCilvekamPaika;
		
		
		dCenaProc=Cilveku.dCenaProc;
		
		
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
			String vards=vardsDefault+Cilveks.maxCilveks;
			int[] chunkXY = {r.nextInt(KonstantesUniversal.mapChunkCountX),
					r.nextInt(KonstantesUniversal.mapChunkCountY)};
			Koord xyz = new Koord();
			xyz.x = KonstantesUniversal.mapChunkW * r.nextDouble();
			xyz.y = KonstantesUniversal.mapChunkW * r.nextDouble();
			xyz.v = 0;
			xyz.fi = 360*r.nextDouble();

			double vmax=Cilveku.vmax, ommax=Cilveku.ommax;
			double hpmax=Cilveku.hpmax, hp=hpmax*(0.5+0.5*r.nextDouble());
			double paika=paikaMax;
			double R1=RMax/R2koefic*(0.5+0.5*r.nextDouble()), R2=RMax*(0.5+0.5*r.nextDouble());
			double brunas=brunasMin+(brunasMax-brunasMin)*r.nextDouble(), stiprums=stiprumsMin+(stiprumsMax-stiprumsMin)*r.nextDouble(), gataviba=100;
			double drosme=0.5+r.nextDouble()/2;
			
			String komanda;
			int[] rangs= new int[] {0,0};
			
			
			if (randomKomandas) {
				if (Main.komandasList.size()==0||r.nextDouble()<0.5) { // iespçja ka jauns spçlçtâjs taisîs  jaunu  komandu
					KomanduApskats.jaunaKomanda(vards);
					komanda = Main.komandasList.get(Main.komandasList.size()-1).nosaukums;
					rangs[0]=0;
					rangs[1]=0;
				} else { //pievienojas kâdai no jau esoðajâm
					komanda = Main.komandasList.get(r.nextInt(Main.komandasList.size()-1)+1).nosaukums;
					rangs[0]=0;
					rangs[1]=0;
				}
			} else { // ja  visi sâk nulles komandâ
				komanda = Main.komandasList.get(0).nosaukums;
				rangs[0]=0;
				rangs[1]=0;
			}

			CilvekuDarbibas.dzemdibas(vards,chunkXY,xyz,vmax,ommax,hp,hpmax,paika,R1,R2,brunas,stiprums,gataviba,drosme,komanda,rangs);
		}
		
	}
	
	
	
}
