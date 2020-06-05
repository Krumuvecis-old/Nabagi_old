package calculations.cilveki;

import java.util.Random;

import calculations.MapChunk;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Formulas;
import calculations.komandas.KomanduApskats;
import calculations.lietas.Lieta;
import calculations.Main;

class CilvekuDarbibas {
	
	protected static boolean navKoEst;
	protected static void esanaNoInventory(int[] chunkXY, int numurs) {
		
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		if (cilveks.paika<=cilveks.paikaMin) { //çðana no inventory - tie visi valueOf ðajâ voidâ neko nedeva - var izòemt
			
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
	
	protected static void updateTradeOrders(int[] chunkXY, int numurs) {
		//tirdzniecîbas orderu pârskats
		
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		double defaultCena=Cilveku.paikaPriceDefault; //cenas apskats no atmiòas vçl nav ieviests
		//double apjomsMin=0.01;
		
		
		String nosaukums="";
		double sellLimit, buyLimit; //pârdod virs sellLimit, pârdod zem buyLimit
		for(int i=0;i<cilveks.inventory.size();i++) { //apskata katru lietu inventorijâ, pieòemot, ka dublikâtu vairs nav
			
			nosaukums = cilveks.inventory.get(i).nosaukums;
			//System.out.println("kaut ko sprieþ1: "+i+"/"+cilveks.inventory.size()+" "+nosaukums);
			if(nosaukums=="Zelts") continue; //zeltu nevar pârdot un nevar arî nopirkt
			
			if(nosaukums=="Paika") {
				sellLimit=Cilveku.sellLimitPaika;
				buyLimit=Cilveku.buyLimitPaika;
			} else { //neklasificçtiem objektiem
				sellLimit=Cilveku.sellLimitDefault;
				buyLimit=Cilveku.buyLimitDefault;
			}
			
			//System.out.println("buy/sell-Limit: "+buyLimit+"/"+sellLimit+" "+nosaukums);
			boolean tirgos=false, pirks=false;
			double apjoms=0;
			
			//System.out.println("kaut ko sprieþ2: "+i+"/"+cilveks.inventory.size());
			
			int zeltsNr=cilveks.countInventory("Zelts", false);
			double zeltsTirgum=0;
			if (zeltsNr>=0) zeltsTirgum=cilveks.inventory.get(zeltsNr).daudzums;
			
			
			
			
			//System.out.println("kaut ko sprieþ3: "+i+"/"+cilveks.inventory.size());
			if(cilveks.inventory.get(i).daudzums>sellLimit) { //pârdoðana
				apjoms=cilveks.inventory.get(i).daudzums-sellLimit;
				tirgos=true;
				pirks=false;
			} else if(cilveks.inventory.get(i).daudzums<buyLimit && zeltsTirgum>0) { //pirkðana
				apjoms=Math.min(buyLimit-cilveks.inventory.get(i).daudzums, zeltsTirgum/defaultCena);
				tirgos=true;
				pirks=true;
			}
			//System.out.println("kaut ko sprieþ4: "+i+"/"+cilveks.inventory.size()+" pirks:"+pirks);
			
			int orderNumberTemp=-1;
			for(int j=0;j<cilveks.orderi.size();j++){ //apskata visus orderus - order cleanup
				if(cilveks.orderi.get(j).prece==nosaukums) {
					if (tirgos) { orderNumberTemp=j; } else {
						if(cilveks.orderi.get(j).daudzums<=0) { //izdzçð tukðos orderus
							cilveks.orderi.remove(j);
							j--;
							//continue;
						}
					}
				}
			}
			
			if (tirgos) {
				if(orderNumberTemp<0) { //ja ordera vçl nav, uztaisa jaunu
					Orderis orderis=new Orderis();
					orderis.prece=nosaukums;
					
					orderNumberTemp=cilveks.orderi.size();
					cilveks.orderi.add(orderis);
				}
				cilveks.orderi.get(orderNumberTemp).daudzums=apjoms;
				cilveks.orderi.get(orderNumberTemp).perk=pirks;
				
				cilveks.orderi.get(orderNumberTemp).cena=defaultCena;
			}
		}
		//return(null);
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
	
	protected static void trauma(int[] chunkXY, int numurs, double stiprums, double precizitate) {
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		if (stiprums*precizitate>cilveks.brunas) {
			cilveks.hp-=stiprums*precizitate-cilveks.brunas;
		}
	}
	
	protected static void vairosanas(int[] chunkXY, int numurs) {
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		Random r=new Random();
		
		//System.out.println(Main.cilvekiList.indexOf(this)+" vairosanas cikls");
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
			if (cilveks.inventory.get(i).nosaukums=="Zelts") {
				cilveks.inventory.get(i).daudzums-=
						(Cilveku.cenaCilvekam + Cilveku.mantojumsCilvekamZelts) / cilveks.inventory.get(i).zelts;
			}
			if (cilveks.inventory.get(i).nosaukums=="Paika") {
				cilveks.inventory.get(i).daudzums-=
						Cilveku.mantojumsCilvekamPaika / cilveks.inventory.get(i).paika;
			}
		}
		
		
		String komanda;
		int[] rangs= new int[2];
		if ((r.nextDouble()<Cilveku.dzimstotDefectionChance && cilveks.rangs[1]==0 ) ||
				cilveks.komanda=="Anarhija") { //izveido savu komandu

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
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		cilveks.xyz.fi = Formulas.lenkaNoteiksana(cilveks.xyz.x, cilveks.xyz.y, XY[0], XY[1]);
		
		cilveks.xyz.v=hpKoef*vKoef*cilveks.vmax;
	}
	
	protected static void atkapties(int[] chunkXY, int numurs, double[] XY, double hpKoef, double vKoef){
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		cilveks.xyz.fi = 180 + Formulas.lenkaNoteiksana(cilveks.xyz.x,cilveks.xyz.y, XY[0], XY[1]);
		
		cilveks.xyz.v = hpKoef*vKoef*cilveks.vmax;
	}
	
	protected static void mekletZeltu(int[] chunkXY, int numurs, int i, double hpKoef, double vKoef) {
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		cilveks.darbiba="meklet zeltu";
		
		tuvoties(chunkXY, numurs, new double[] {Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(i).x, Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(i).y},hpKoef, vKoef);
	}
	
	protected static void mekletPaiku(int[] chunkXY, int numurs, int i, double hpKoef, double vKoef) {
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		cilveks.darbiba="meklet paiku";
		
		tuvoties(chunkXY, numurs, new double[] {Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(i).x, Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(i).y},hpKoef, vKoef);
	}
	
	protected static void atputa(int[] chunkXY, int numurs, double hpKoef) {
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		cilveks.darbiba="atputa";
		
		Random r = new Random();
		
		cilveks.xyz.v=0.4*hpKoef*cilveks.vmax;
		
		if (r.nextDouble()<0.1) { //periodiski nomaina kustîbas virzienu
			cilveks.xyz.fi+=cilveks.ommax*2*(-0.5+r.nextDouble());
		}
		
		
		double dDrosme=0.00001; //normalizç drosmi
		if(cilveks.drosme<=0.5) cilveks.drosme+=dDrosme;
		if(cilveks.drosme>0.5) cilveks.drosme-=dDrosme;
		
		if(cilveks.drosme>1) cilveks.drosme=1;
		if(cilveks.drosme<0) cilveks.drosme=0;
		
		double dGataviba=1;
		if(cilveks.gataviba<=Cilveku.maxGataviba-dGataviba) cilveks.gataviba+=dGataviba;
	}
	
	protected static void komanduMaina(int[] chunkXY, int numurs,
									   double paikaMaina, double zeltsMaina, double anarchyChance, double orderChance) {
		Cilveks cilveks= Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
		
		int zeltsNr=cilveks.countInventory("Zelts", true);
		double zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;
		
		int paikaNr=cilveks.countInventory("Paika", true);
		double paikaSum=0;
		if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;
		
		Random r = new Random();
		if (cilveks.rangs[1]==0 && paikaSum<paikaMaina && zeltsSum<zeltsMaina) { //tikai pirmâ lîmeòa spçlçtâji varçs mainît komandu
			if (Main.komandasList.size()>1 && (cilveks.komanda==Main.komandasList.get(0).nosaukums && r.nextDouble()<orderChance)) { //ja ir bez komandas, pievienojas citai (izvçlâs  random, bet ne 0)
				cilveks.komanda=Main.komandasList.get(r.nextInt(Main.komandasList.size()-1)+1).nosaukums; 
			} else if (r.nextDouble()<anarchyChance && cilveks.komanda!=Main.komandasList.get(0).nosaukums) { //iespçja, ka pametîs esoðo komandu (tikai, ja kâdâ ir komandâ)
				cilveks.komanda=Main.komandasList.get(0).nosaukums;
			}
		}
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

		int i = Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size();
		Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.add(cilveks);
	}
	
	
}
