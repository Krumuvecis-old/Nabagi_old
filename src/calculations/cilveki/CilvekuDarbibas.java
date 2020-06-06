package calculations.cilveki;

import java.util.Random;

import calculations.MapChunk;
import calculations.komandas.Biedrs;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Formulas;
import calculations.komandas.KomanduApskats;
import calculations.lietas.Lieta;
import calculations.Main;

class CilvekuDarbibas {

	protected static void main(int numurs){

		esanaNoInventory(numurs);
		boolean navKoEst=CilvekuDarbibas.navKoEst;

		Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
		Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,  biedrs.i);

		//errori var bût zemâk

		//atkârtota saskaite un papildus cleanup
		int zeltsNr=cilveks.countInventory("Zelts", true);
		double zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;

		int paikaNr=cilveks.countInventory("Paika", true); //çdot no inventory jau bija viens cleanup
		double paikaSum=0;
		if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;

		//errori var bût augstâk

	}

	protected static boolean navKoEst;
	protected static void esanaNoInventory(int numurs) {
		Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
		Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,  biedrs.i);
		
		if (cilveks.paika<=cilveks.paikaMin) { //çðana no inventory
			
			int paikaJ=cilveks.countInventory("Paika", true);
			double paikaSum=0;
			if (paikaJ>=0) paikaSum=cilveks.inventory.get(paikaJ).daudzums;
			
			if ( paikaSum>0/* && (paikaJ>=0 && cilveks.inventory.size()>0) */) { //otro daïu ifâ pec OR var izòemt
				double apests=Math.min(Cilveku.esanasDaudzums, cilveks.inventory.get(paikaJ).daudzums);
				cilveks.inventory.get(paikaJ).daudzums-=apests;
				
				cilveks.paika+=Math.min((apests/Cilveku.esanasDaudzums)*cilveks.paikaMax, cilveks.paikaMax-cilveks.paika);
				
			} else { navKoEst = true; }
		}
		
	}
	
	protected static void trauma(int[] chunkXY, int numurs, double stiprums, double precizitate) {
		Cilveks cilveks = Cilveks.getPlayer(chunkXY, numurs);
		
		if (stiprums*precizitate>cilveks.brunas) {
			cilveks.hp-=stiprums*precizitate-cilveks.brunas;
		}
	}
	
	protected static void vairosanas(int[] chunkXY, int numurs) {
		Cilveks cilveks= Cilveks.getPlayer(chunkXY,numurs);
		
		Random r=new Random();

		Cilveks.maxCilveks++;
		String vards=Cilveku.vardsDefault+Cilveks.maxCilveks;

		Koord xyz = new Koord();
		xyz.x = cilveks.xyz.x;
		xyz.y = cilveks.xyz.y;
		xyz.v = 0;
		xyz.fi = 360*r.nextDouble();
		
		double vmax=Formulas.novirzeRandom(cilveks.vmax, Cilveku.dvMaxDzimstot),
				ommax=Formulas.novirzeRandom(cilveks.ommax, Cilveku.dommaxDzimstot);
		double hpmax=Cilveku.hpmax, hp=hpmax;
		double paika=Cilveku.paikaMax;
		double R2=Formulas.novirzeRandom(cilveks.R2, Cilveku.dRDzimstot),
				R1=Formulas.novirzeRandom(cilveks.R1, Cilveku.dRDzimstot);
		double brunas = Formulas.novirzeRandom(cilveks.brunas, Cilveku.dBrunasDzimstot);;
		double stiprums = Formulas.novirzeRandom(cilveks.stiprums, Cilveku.dStiprumsDzimstot);
		double gataviba=Cilveku.maxGataviba, drosme=cilveks.drosme;
		
		for(int i=0; i<cilveks.inventory.size(); i++) {
			if (cilveks.inventory.get(i).nosaukums.equals("Zelts")) {
				cilveks.inventory.get(i).daudzums-=
						(Cilveku.cenaCilvekam + Cilveku.mantojumsCilvekamZelts) / cilveks.inventory.get(i).zelts;
			}
			if (cilveks.inventory.get(i).nosaukums.equals("Paika")) {
				cilveks.inventory.get(i).daudzums-=
						Cilveku.mantojumsCilvekamPaika / cilveks.inventory.get(i).paika;
			}
		}
		
		
		String komanda;
		int[] rangs= new int[2];
		if ((r.nextDouble()<Cilveku.dzimstotDefectionChance && cilveks.rangs[1]==0 ) ||
				cilveks.komanda.equals("Anarhija")) { //izveido savu komandu

			KomanduApskats.jaunaKomanda(cilveks.vards);//nosauc tçva vârdâ, tçvs bûs karalis
			komanda=Main.komandasList.get(Main.komandasList.size()-1).nosaukums;
			System.out.println("izveidota komanda "+komanda);
			cilveks.komanda = komanda;
			cilveks.rangs[0]=0;
			cilveks.rangs[1]=3;
			rangs[0]=0;
    		rangs[1]=0;
    		
		} else { //paliek esoðajâ  komandâ
			komanda=cilveks.komanda;
			System.out.println(cilveks.vards+" dzemdç jaunu komandâ "+komanda);
			rangs[0]=0; //vienmçr bûs zemnieks
    		rangs[1]=0;
		}
		
		dzemdibas(vards,chunkXY,xyz,vmax,ommax,hp,hpmax,paika,
				R1,R2,brunas,stiprums,gataviba,drosme,komanda,rangs);
    }
	
	protected static void tuvoties(int[] chunkXY, int numurs, double[] XY, double hpKoef, double vKoef){
		Cilveks cilveks= Cilveks.getPlayer(chunkXY, numurs);
		
		cilveks.xyz.fi = Formulas.lenkaNoteiksana(cilveks.xyz.x, cilveks.xyz.y, XY[0], XY[1]);
		
		cilveks.xyz.v = hpKoef*vKoef*cilveks.vmax;
	}
	
	protected static void atkapties(int[] chunkXY, int numurs, double[] XY, double hpKoef, double vKoef){
		Cilveks cilveks = Cilveks.getPlayer(chunkXY, numurs);
		
		cilveks.xyz.fi = 180 + Formulas.lenkaNoteiksana(cilveks.xyz.x,cilveks.xyz.y, XY[0], XY[1]);
		
		cilveks.xyz.v = hpKoef*vKoef*cilveks.vmax;
	}

	protected static void atputa(int[] chunkXY, int numurs, double hpKoef, double vKoef) {
		Cilveks cilveks = Cilveks.getPlayer(chunkXY, numurs);
		
		cilveks.darbiba="atputa";
		
		Random r = new Random();
		
		cilveks.xyz.v=vKoef*hpKoef*cilveks.vmax;
		
		if (r.nextDouble()<Cilveku.omChangeRate) { //periodiski nomaina kustîbas virzienu
			cilveks.xyz.fi += cilveks.ommax*2*(-0.5+r.nextDouble());
		}

		double dGataviba=1;
		if(cilveks.gataviba<=Cilveku.maxGataviba-dGataviba) cilveks.gataviba+=dGataviba;
	}

	protected static void dzemdibas(String vards,
									int[] chunkXY, Koord xyz, double vmax, double ommax,
									double hp, double hpmax, double paika, double R1, double R2,
									double brunas, double stiprums, double gataviba, double drosme,
									String komanda, int[] rangs) {

		Cilveks cilveks = new Cilveks();

		cilveks.xyz=xyz;
		cilveks.vards=vards;

		cilveks.vmax=vmax;				//primârie Parametri
		cilveks.ommax=ommax;
		cilveks.hp=hp;
		cilveks.hpmax=hpmax;
		cilveks.paika=paika;
		cilveks.paikaMax=Cilveku.paikaMax;
		cilveks.paikaMin=Cilveku.paikaMin;

		cilveks.R1=R1;					//redzesloks
		cilveks.R2=R2;
		
		//default inventory
		Lieta mantojumsZelts=new Lieta();
		mantojumsZelts.x = xyz.x;
		mantojumsZelts.y = xyz.y;
		mantojumsZelts.nosaukums="Zelts";
		mantojumsZelts.daudzums=Cilveku.mantojumsCilvekamZelts;
		mantojumsZelts.zelts=1;
		mantojumsZelts.paika=0;
		mantojumsZelts.masa=1;
		mantojumsZelts.attack=0;
		mantojumsZelts.defence=0;
		mantojumsZelts.condition=1;
		cilveks.inventory.add(mantojumsZelts);
		
		Lieta mantojumsPaika=new Lieta();
		mantojumsPaika.x= cilveks.xyz.x;
		mantojumsPaika.y= cilveks.xyz.y;
		mantojumsPaika.nosaukums="Paika";
		mantojumsPaika.daudzums=Cilveku.mantojumsCilvekamPaika;
		mantojumsPaika.zelts=0;
		mantojumsPaika.paika=1;
		mantojumsPaika.masa=1;
		mantojumsPaika.attack=0;
		mantojumsPaika.defence=0;
		mantojumsPaika.condition=1;
		cilveks.inventory.add(mantojumsPaika);

		cilveks.brunas=brunas;			//cîòas parametri
		cilveks.stiprums=stiprums;
		cilveks.gataviba=gataviba;
		cilveks.drosme=drosme;

		cilveks.rangs = rangs;
		cilveks.komanda=komanda;

		Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.add(cilveks);
	}
	


}
