package cilveki;

import java.util.Random;

import konstantes.Parametri;
import konstantes.Formulas;
import dataBase.*;
import komandas.KomanduApskats;

class CilvekuDarbibas {
	
	protected static boolean navKoEst;
	protected static void esanaNoInventory(int numurs) {
		
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		if (cilveks.paika<=cilveks.paikaMin) { //çðana no inventory - tie visi valueOf ðajâ voidâ neko nedeva - var izòemt
			
			int paikaJ=CilvekuApskats.countInventory(numurs, "Paika", true);
			double paikaSum=0;
			if (paikaJ>=0) paikaSum=Double.valueOf(cilveks.inventory.get(paikaJ).daudzums);
			
			if ( paikaSum>0 && (paikaJ>=0 && cilveks.inventory.size()>0) ) { //otro daïu ifâ pec OR var izòemt
				if(paikaJ>=cilveks.inventory.size()||paikaJ<0) System.out.println(cilveks.vards+"Error5! "+paikaJ+"/"+cilveks.inventory.size());
				double apests=Double.valueOf(Math.min(Parametri.esanasDaudzums, cilveks.inventory.get(paikaJ).daudzums));
				cilveks.inventory.get(paikaJ).daudzums-=Double.valueOf(apests);
				
				cilveks.paika+=Double.valueOf(Math.min((apests/Parametri.esanasDaudzums)*cilveks.paikaMax, cilveks.paikaMax-cilveks.paika));
				
			} else { navKoEst = true; }
		}
		
	}
	
	protected static void updateTradeOrders(int numurs) {
		//tirdzniecîbas orderu pârskats
		
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		double defaultCena=Parametri.paikaPriceDefault; //cenas apskats no atmiòas vçl nav ieviests
		//double apjomsMin=0.01;
		
		
		String nosaukums="";
		double sellLimit, buyLimit; //pârdod virs sellLimit, pârdod zem buyLimit
		for(int i=0;i<cilveks.inventory.size();i++) { //apskata katru lietu inventorijâ, pieòemot, ka dublikâtu vairs nav
			
			nosaukums = cilveks.inventory.get(i).nosaukums;
			//System.out.println("kaut ko sprieþ1: "+i+"/"+cilveks.inventory.size()+" "+nosaukums);
			if(nosaukums=="Zelts") continue; //zeltu nevar pârdot un nevar arî nopirkt
			
			if(nosaukums=="Paika") {
				sellLimit=Parametri.sellLimitPaika;
				buyLimit=Parametri.buyLimitPaika;
			} else { //neklasificçtiem objektiem
				sellLimit=Parametri.sellLimitDefault;
				buyLimit=Parametri.buyLimitDefault;
			}
			
			//System.out.println("buy/sell-Limit: "+buyLimit+"/"+sellLimit+" "+nosaukums);
			boolean tirgos=false, pirks=false;
			double apjoms=0;
			
			//System.out.println("kaut ko sprieþ2: "+i+"/"+cilveks.inventory.size());
			
			int zeltsNr=CilvekuApskats.countInventory(numurs, "Zelts", false);
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
							continue;
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
	
	protected static void healingAndHunger(int numurs) {
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		double dHpRegen=Parametri.healingRateDefault,
				dHpHungry=Parametri.healthReductionRate,
				paikaD=Parametri.paikaReductionDefault;
		
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
	
	protected static void trauma(int numurs, double stiprums, double precizitate) {
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		if (stiprums*precizitate>cilveks.brunas) {
			cilveks.hp-=stiprums*precizitate-cilveks.brunas;
		}
	}
	
	protected static void vairosanas(int numurs) {
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		Random r=new Random();
		
		//System.out.println(Dati.cilvekiList.indexOf(this)+" vairosanas cikls");
		Cilveks.maxCilveks++;
		String vardsTemp=Parametri.vardsDefault+Cilveks.maxCilveks;
		double x=cilveks.xyz.x, y=cilveks.xyz.y; //x un y
		double v=1, fi=360*r.nextDouble();
		
		double vmaxTemp=cilveks.vmax*(1+Parametri.dvMaxDzimstot*(-0.5+r.nextDouble())),
				ommaxTemp=cilveks.ommax*(1+Parametri.dommaxDzimstot*(-0.5+r.nextDouble()));
		double hpmaxTemp=Parametri.hpmax, hpTemp=hpmaxTemp;
		double paikaTemp=Parametri.paikaMax;
		double R2Temp=cilveks.R2*(1+Parametri.dRDzimstot*(-0.5+r.nextDouble())),
				R1Temp=cilveks.R1*(1+Parametri.dRDzimstot*(-0.5+r.nextDouble()));
		double brunasTemp = cilveks.brunas+Parametri.dBrunasDzimstot*(-0.5+r.nextDouble());
		double stiprumsTemp=cilveks.stiprums+Parametri.dStiprumsDzimstot*(-0.5+r.nextDouble());
		double gatavibaTemp=Parametri.maxGataviba, drosmeTemp=cilveks.drosme;
		
		for(int i=0;i<cilveks.inventory.size();i++) {
			if (cilveks.inventory.get(i).nosaukums=="Zelts") {
				cilveks.inventory.get(i).daudzums-=(Parametri.cenaCilvekam+Parametri.mantojumsCilvekam)/cilveks.inventory.get(i).zelts;
			}
			if (cilveks.inventory.get(i).nosaukums=="Paika") {
				cilveks.inventory.get(i).daudzums-=Parametri.mantojumsCilvekamPaika/cilveks.inventory.get(i).paika;
			}
		}
		
		
		String komandaTemp;
		int[] rangsTemp= new int[2];
		if ((r.nextDouble()<0.02 && cilveks.rangs[1]==0 )|| cilveks.komanda=="Anarhija") { //izveido savu komandu
			KomanduApskats.jaunaKomanda(cilveks.vards);//nosauks tçva vârdâ, tçvs bûs karalis
			komandaTemp=Dati.komandasList.get(Dati.komandasList.size()-1).nosaukums;
			System.out.println("izveidota komanda "+komandaTemp);
			cilveks.komanda = komandaTemp;
			cilveks.rangs[0]=0;
			cilveks.rangs[1]=3;
			rangsTemp[0]=0;
    		rangsTemp[1]=0;
    		
		} else { //paliek esoðajâ  komandâ
			komandaTemp=cilveks.komanda;
			System.out.println(cilveks.vards+" dzemdç jaunu komandâ "+komandaTemp);
			rangsTemp[0]=0;
    		rangsTemp[1]=0;
		}
		
		dzemdibas(vardsTemp,new double[]{x,y,v,fi},vmaxTemp,ommaxTemp,hpTemp,hpmaxTemp,paikaTemp,
				R1Temp,R2Temp,brunasTemp,stiprumsTemp,gatavibaTemp,drosmeTemp,komandaTemp,rangsTemp);
    }
	
	protected static void tuvoties(int numurs, double[] XY, double hpKoef, double vKoef){
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		cilveks.xyz.fi = Formulas.lenkaNoteiksana(cilveks.xyz.x, cilveks.xyz.y, XY[0], XY[1]);
		
		cilveks.xyz.v=hpKoef*vKoef*cilveks.vmax;
	}
	
	protected static void atkapties(int numurs, double[] XY, double hpKoef, double vKoef){
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		cilveks.xyz.fi = 180 + Formulas.lenkaNoteiksana(cilveks.xyz.x,cilveks.xyz.y, XY[0], XY[1]);
		
		cilveks.xyz.v = hpKoef*vKoef*cilveks.vmax;
	}
	
	protected static void mekletZeltu(int numurs, int i, double hpKoef, double vKoef) {
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		cilveks.darbiba="meklet zeltu";
		
		tuvoties(numurs, new double[] {Dati.lietas.get(i).x,Dati.lietas.get(i).y},hpKoef, vKoef);
	}
	
	protected static void mekletPaiku(int numurs, int i, double hpKoef, double vKoef) {
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		cilveks.darbiba="meklet paiku";
		
		tuvoties(numurs, new double[] {Dati.lietas.get(i).x,Dati.lietas.get(i).y},hpKoef, vKoef);
	}
	
	protected static void atputa(int numurs, double hpKoef) {
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
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
		if(cilveks.gataviba<=Parametri.maxGataviba-dGataviba) cilveks.gataviba+=dGataviba;
	}
	
	protected static void komanduMaina(int numurs, double paikaMaina, double zeltsMaina, double anarchyChance, double orderChance) {
		Cilveks cilveks=Dati.cilvekiList.get(numurs);
		
		int zeltsNr=CilvekuApskats.countInventory(numurs,"Zelts", true);
		double zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;
		
		int paikaNr=CilvekuApskats.countInventory(numurs,"Paika", true);
		double paikaSum=0;
		if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;
		
		Random r = new Random();
		if (cilveks.rangs[1]==0 && paikaSum<paikaMaina && zeltsSum<zeltsMaina) { //tikai pirmâ lîmeòa spçlçtâji varçs mainît komandu
			if (Dati.komandasList.size()>1 && (cilveks.komanda==Dati.komandasList.get(0).nosaukums && r.nextDouble()<orderChance)) { //ja ir bez komandas, pievienojas citai (izvçlâs  random, bet ne 0)
				cilveks.komanda=Dati.komandasList.get(r.nextInt(Dati.komandasList.size()-1)+1).nosaukums; 
			} else if (r.nextDouble()<anarchyChance && cilveks.komanda!=Dati.komandasList.get(0).nosaukums) { //iespçja, ka pametîs esoðo komandu (tikai, ja kâdâ ir komandâ)
				cilveks.komanda=Dati.komandasList.get(0).nosaukums;
			}
		}
	}
	
	protected static void dzemdibas(String vardsTemp,
			double[] koordTemp, double vmaxTemp, double ommaxTemp,
			double hpTemp, double hpmaxTemp, double paikaTemp, double R1Temp, double R2Temp,
			double brunasTemp, double stiprumsTemp, double gatavibaTemp, double drosmeTemp, String komandaTemp, int[] rangsTemp) {
		
		int i=Dati.cilvekiList.size();
		Dati.cilvekiList.add(new Cilveks());
		
		
		Koord xyzTemp = new Koord();
		xyzTemp.x=koordTemp[0];
		xyzTemp.y=koordTemp[1];
		xyzTemp.v=koordTemp[2];
		xyzTemp.fi=koordTemp[3];
		
		
		Dati.cilvekiList.get(i).xyz=xyzTemp;
		Dati.cilvekiList.get(i).vards=vardsTemp;
		
		Dati.cilvekiList.get(i).vmax=vmaxTemp;				//primârie Parametri
		Dati.cilvekiList.get(i).ommax=ommaxTemp;
		Dati.cilvekiList.get(i).hp=hpTemp;
		Dati.cilvekiList.get(i).hpmax=hpmaxTemp;
		Dati.cilvekiList.get(i).paika=paikaTemp;
		Dati.cilvekiList.get(i).paikaMax=Parametri.paikaMax;
		Dati.cilvekiList.get(i).paikaMin=Parametri.paikaMin;
		
		Dati.cilvekiList.get(i).R1=R1Temp;					//redzesloks
		Dati.cilvekiList.get(i).R2=R2Temp;
		
		//default inventory
		Lieta mantojumsZelts=new Lieta();
		mantojumsZelts.x=Dati.cilvekiList.get(i).xyz.x;
		mantojumsZelts.y=Dati.cilvekiList.get(i).xyz.y;
		mantojumsZelts.nosaukums="Zelts";
		mantojumsZelts.daudzums=Parametri.mantojumsCilvekam;
		mantojumsZelts.zelts=1;
		mantojumsZelts.paika=0;
		mantojumsZelts.masa=1;
		mantojumsZelts.attack=0;
		mantojumsZelts.defence=0;
		mantojumsZelts.condition=1;
		Dati.cilvekiList.get(i).inventory.add(mantojumsZelts);
		
		Lieta mantojumsPaika=new Lieta();
		mantojumsPaika.x=Dati.cilvekiList.get(i).xyz.x;
		mantojumsPaika.y=Dati.cilvekiList.get(i).xyz.y;
		mantojumsPaika.nosaukums="Paika";
		mantojumsPaika.daudzums=Parametri.mantojumsCilvekamPaika;
		mantojumsPaika.zelts=0;
		mantojumsPaika.paika=1;
		mantojumsPaika.masa=1;
		mantojumsPaika.attack=0;
		mantojumsPaika.defence=0;
		mantojumsPaika.condition=1;
		Dati.cilvekiList.get(i).inventory.add(mantojumsPaika);
		
		Dati.cilvekiList.get(i).brunas=brunasTemp;			//cîòas parametri
		Dati.cilvekiList.get(i).stiprums=stiprumsTemp;
		Dati.cilvekiList.get(i).gataviba=gatavibaTemp;
		Dati.cilvekiList.get(i).drosme=drosmeTemp;
		
		Dati.cilvekiList.get(i).rangs = rangsTemp;
		Dati.cilvekiList.get(i).komanda=komandaTemp;
	}
	
	
}
