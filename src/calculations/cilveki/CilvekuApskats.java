package calculations.cilveki;

import java.awt.Color;
import java.util.Random;

import calculations.KonstantesUniversal;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Fizikas;
import calculations.konstantes.Formulas;
import calculations.Main;
import calculations.komandas.Komanda;
import calculations.komandas.KomanduApskats;
import calculations.lietas.Lieta;


public class CilvekuApskats {
	
	static double vmax, ommax;
	
	static double resnumaKoefic; //HpMax attiec�bai pret resnumu
	static int maxGataviba;
	static double RMax;
	static double R2koefic; //maksim�lais redzesloks un minim�l� da�as koefic
	static double dRDzimstot; //redzesloka procentu�la izmai�a vairojoties
	static double dvMaxDzimstot, dommaxDzimstot; //procentu�l�s izmai�as dzimstot
	
	static int paikaMax, paikaMin;
	static double esanasDaudzums; //par vienu pilnu paikaMax
	static boolean navKoEstTemp; //funkcijai 
	
	
	static double paikaNepiec; //daudzums l�dz  kuram m��in�s sav�kt  paiku,  tad  skraid�t apk�rt
	
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
			naavesPaarbaude(biedrs.chunkXY, biedrs.i); //nodz�� miru�os cilv�kus
		}
		
	}
	
	private static void galvenaisApskats(int numurs) {
		int[] chunkXY = Cilveks.cilvekuListPilnais.get(numurs).chunkXY;
		int i = Cilveks.cilvekuListPilnais.get(numurs).i;
		Cilveks cilveks= Cilveks.getPlayer(chunkXY, i);
		Random r=new Random();
		
		double resnums=resnumaKoefic*cilveks.hpmax;
		
		lootApskatsSadursmei(chunkXY, i);

		if (cilveks.autoPilot) {
			//sp�l�t�js kontrol� cilv�ku
		} else CilvekuAI.main(numurs); //autopilots

		CilvekuDarbibas.main(numurs);



		
		//ide�lai kust�bai pietr�kst:
			//le��a pagrie�ana
			//ieskrie�an�s (pa�trin�jums)
		
		Kustiba.main(cilveks, numurs);
		
		CilvekuDarbibas.healingAndHunger(chunkXY, i);
	}
	
	private static void playerAI(int numurs){

	}
	
	private static void lootApskatsSadursmei(int[] chunkXY, int i) { //cilv�ks apskata lietas, kas izm�t�tas pa karti
		Cilveks cilveks = Cilveks.getPlayer(chunkXY, i);
		double resnums=resnumaKoefic*cilveks.hpmax;
		//Random r=new Random();
		
		for(int j = 0; j< Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.size(); j++){
			double distance = Math.hypot(cilveks.xyz.x- Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(j).x,
					cilveks.xyz.y- Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(j).y);
			
			double resnumsJ; //nosaka lietas resnumu
			
			if (Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(j).nosaukums=="Zelts") { resnumsJ = Fizikas.zeltaResnums;
			} else if (Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(j).nosaukums=="Paika") { resnumsJ = Fizikas.paikasResnums;
			} else resnumsJ = Fizikas.lietasResnums; // default neklasific�tai lietai
			
			if(distance<=(resnums+resnumsJ)/2) { //pa�em jebk�du lietu, ja  saskaras
				cilveks.inventory.add(Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(j));
				Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.remove(j);
				j--;
				continue;
			}
		}
		
	}
	
	private static int zeltsTuvakaisNr, paikaTuvakaNr;
	private static double zeltsTuvakaisDist, paikaTuvakaDist;
	
	private static void lootApskatsMeklesanai(int[] chunkXY, int i) {
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(i);
		//double resnums=resnumaKoefic*cilveks.hpmax;
		//Random r=new Random();
		
		
		zeltsTuvakaisNr = 0; //numurs J
		paikaTuvakaNr = 0;
		
		zeltsTuvakaisDist=-1; //distance
		paikaTuvakaDist=-1;
		
		for(int j = 0; j< Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.size(); j++){
			double distance = Math.hypot(cilveks.xyz.x- Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(j).x,
					cilveks.xyz.y- Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(j).y);
			
			/*double resnumsJ; //nosaka lietas resnumu
			if (Main.lietas.get(j).nosaukums=="Zelts") { resnumsJ = zeltaResnums;
			} else if (Main.lietas.get(j).nosaukums=="Paika") { resnumsJ = paikasResnums;
			} else resnumsJ = lietasResnums;*/
			
			if(Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(j).nosaukums=="Zelts") {
				if (distance<zeltsTuvakaisDist || (zeltsTuvakaisDist<0 && distance<=cilveks.R2)) {
					zeltsTuvakaisDist = distance;
					zeltsTuvakaisNr=j;
				}
			}
			
			if(Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(j).nosaukums=="Paika") {
				if (distance<paikaTuvakaDist || (paikaTuvakaDist<0 && distance<=cilveks.R2)) {
					paikaTuvakaDist = distance;
					paikaTuvakaNr=j;
				}
			}
				
		}
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
		resnumaKoefic=Fizikas.resnumaKoefic; //HpMax attiec�bai pret resnumu
		maxGataviba=Cilveku.maxGataviba;
		RMax=Cilveku.RMax; //maksim�lais redzesloks
		R2koefic=Cilveku.R2koefic; //minim�l� redzesloka da�as koefic
		dRDzimstot=Cilveku.dRDzimstot; //redzesloka procentu�la izmai�a vairojoties
		dvMaxDzimstot=Cilveku.dvMaxDzimstot; //procentu�l�s izmai�as dzimstot
		dommaxDzimstot=Cilveku.dommaxDzimstot; //procentu�l�s izmai�as dzimstot
		
		paikaMax=Cilveku.paikaMax;
		paikaMin=Cilveku.paikaMin;
		esanasDaudzums=Cilveku.esanasDaudzums; //par vienu pilnu paikaMax
		navKoEstTemp = false; //funkcijai 
		
		
		paikaNepiec = Cilveku.paikaNepiec; //daudzums l�dz  kuram m��in�s sav�kt  paiku,  tad  skraid�t apk�rt
		
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
		
		if(Main.komandasList.size()==0) { //pati pirm� komanda
			
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
				if (Main.komandasList.size()==0||r.nextDouble()<0.5) { // iesp�ja ka jauns sp�l�t�js tais�s  jaunu  komandu
					KomanduApskats.jaunaKomanda(vards);
					komanda = Main.komandasList.get(Main.komandasList.size()-1).nosaukums;
					rangs[0]=0;
					rangs[1]=0;
				} else { //pievienojas k�dai no jau eso�aj�m
					komanda = Main.komandasList.get(r.nextInt(Main.komandasList.size()-1)+1).nosaukums;
					rangs[0]=0;
					rangs[1]=0;
				}
			} else { // ja  visi s�k nulles komand�
				komanda = Main.komandasList.get(0).nosaukums;
				rangs[0]=0;
				rangs[1]=0;
			}

			CilvekuDarbibas.dzemdibas(vards,chunkXY,xyz,vmax,ommax,hp,hpmax,paika,R1,R2,brunas,stiprums,gataviba,drosme,komanda,rangs);
		}
		
	}
	
	
	
}
