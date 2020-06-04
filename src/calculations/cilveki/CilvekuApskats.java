package calculations.cilveki;

import java.awt.Color;
import java.util.Random;

import calculations.konstantes.Parametri;
import calculations.konstantes.Formulas;
import calculations.Main;
import calculations.komandas.Komanda;
import calculations.komandas.KomanduApskats;
import calculations.lietas.Lieta;


public class CilvekuApskats {
	
	static int mala, platums, augstums; //laukuma izmçri
	
	//static int zeltsJ=-1, paikaJ=-1;//vajadzîgi  funkciju sasaistei
	//static double zeltsSum=0, paikaSum=0; //vajadzîgi  funkciju sasaistei
	
	static double vmax, ommax;
	
	static double resnumaKoefic; //HpMax attiecîbai pret resnumu
	static int maxGataviba;
	static int RMax, R1koefic; //maksimâlais redzesloks un minimâlâ daïas koefic
	static double dRDzimstot; //redzesloka procentuâla izmaiòa vairojoties
	static double dvMaxDzimstot, dommaxDzimstot; //procentuâlâs izmaiòas dzimstot
	
	static int paikaMax, paikaMin;
	static double esanasDaudzums; //par vienu pilnu paikaMax
	static boolean navKoEstTemp; //funkcijai 
	
	
	static int paikaNepiec; //daudzums lîdz  kuram mçìinâs savâkt  paiku,  tad  skraidît apkârt
	
	static double brunasMax, brunasMin, dBrunasDzimstot, stiprumsMax, stiprumsMin, dStiprumsDzimstot;
	static String vardsDefault;
	static int cenaCilvekam, mantojumsCilvekam, mantojumsCilvekamPaika;
	
	
	static double dCenaProc;
	
	public static void main() {
		
		for (int i=0;i<Main.cilvekiList.size();i++) { galvenaisApskats(i);} //galvenais katra cilvçka apskates cikls
		
		for(int i=0;i<Main.cilvekiList.size();i++) { naavesPaarbaude(i);} //nodzçð miruðos cilvçkus
		
	}
	
	private static void galvenaisApskats(int i) {
		Cilveks cilveks=Main.cilvekiList.get(i);
		Random r=new Random();
		
		double resnums=resnumaKoefic*cilveks.hpmax;
		
		lootApskatsSadursmei(i);
		
		//System.out.println(cilveks.vards+" tulît çdîs");
		CilvekuDarbibas.esanaNoInventory(i);
		boolean navKoEst=CilvekuDarbibas.navKoEst;
		
		/*if (navKoEst) {System.out.println(cilveks.vards+" nav ko çst");
		} else {System.out.println(cilveks.vards+" ir ko çst");}*/
		
		//atkârtota saskaite un papildus cleanup
		int zeltsNr=countInventory(i,"Zelts", true);
		double zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;
		
		int paikaNr=countInventory(i,"Paika", true); //çdot no inventory jau bija viens cleanup
		double paikaSum=0;
		if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;
		
		CilvekuDarbibas.updateTradeOrders(i); //orderu apskats
		
		
		//apskata pârçjos spçlçtâjus
		
		double hpRatio=cilveks.hp/cilveks.hpmax;
		double hpKoef=0.5+0.5*hpRatio;
		
		int savejoSkaitsR1=1, pretiniekuSkaitsR1=0, savejoSkaitsR2=1, pretiniekuSkaitsR2=0;
		double pretiniekuXR1=0,pretiniekuYR1=0,pretiniekuXR2=0,pretiniekuYR2=0;
		@SuppressWarnings("unused")
		double pretiniekuStiprumsR1=0, pretiniekuStiprumsR2=0;
		@SuppressWarnings("unused")
		double pretiniekuBrunasR1=0, pretiniekuBrunasR2=0;
		
		@SuppressWarnings("unused")
		double savejoXR1=cilveks.xyz.x,savejoYR1=cilveks.xyz.y,savejoXR2=savejoXR1,savejoYR2=savejoYR1;
		@SuppressWarnings("unused")
		double savejoStiprumsR1=cilveks.stiprums, savejoStiprumsR2=savejoStiprumsR1;
		@SuppressWarnings("unused")
		double savejoBrunasR1=cilveks.brunas, savejoBrunasR2=savejoBrunasR1;
		
		int perkNr=-1, orderisPerkNr=-1;
		int pardodNr=-1, orderisPardodNr=-1;
		String preceTirgo=" ";
		double apjomsTirgo=0;
		double cenaTirgo=Parametri.paikaPriceDefault;// temporary default  value
		double[] jTirgoXY=new double[2];
		
		for(int j=0;j<Main.cilvekiList.size();j++) {
			if (j==i) { continue; }//ar sevi nesalîdzina
			
			
			double distance = Math.hypot(cilveks.xyz.x-Main.cilvekiList.get(j).xyz.x,
					cilveks.xyz.y-Main.cilvekiList.get(j).xyz.y);
			
			if(distance>cilveks.R2) { continue; } else { //atmet pavisam tâlos
				
				//sâkas R2
				
				double hpRatioJ=Main.cilvekiList.get(j).hp/Main.cilvekiList.get(j).hpmax;
				
				if (cilveks.komanda!=Main.cilvekiList.get(j).komanda ||
						Main.cilvekiList.get(j).komanda == "Anarhija") {
					pretiniekuSkaitsR2++;
					pretiniekuXR2+=Main.cilvekiList.get(j).xyz.x;
					pretiniekuYR2+=Main.cilvekiList.get(j).xyz.y;
					pretiniekuStiprumsR2+=Main.cilvekiList.get(j).stiprums*hpRatioJ;
					pretiniekuBrunasR2+=Main.cilvekiList.get(j).brunas;
				}
				
				if (cilveks.komanda==Main.cilvekiList.get(j).komanda &&
						cilveks.komanda != "Anarhija") {
					savejoSkaitsR2++;
					savejoXR2+=Main.cilvekiList.get(j).xyz.x;
					savejoYR2+=Main.cilvekiList.get(j).xyz.y;
					savejoStiprumsR2+=Main.cilvekiList.get(j).stiprums*hpRatioJ;
					savejoBrunasR2+=Main.cilvekiList.get(j).brunas;
					
					//te  vajadzçtu arî pârbaudît tirdzniecîbas cenas un  saglabât atmiòâ
					
					
					
					if(cilveks.orderi.size()>0) { //pârbauda tirdzniecîbas iespçjas, bet tikai starp saviem komandasbiedriem
						
						for(int w1=0;w1<cilveks.orderi.size();w1++) { // apskata savus orderus
							
							if(apjomsTirgo>0) break;
							
							System.out.println(cilveks.vards+" salîdzina sevi ar "+Main.cilvekiList.get(j).vards);
							
							for(int w2=0;w2<Main.cilvekiList.get(j).orderi.size();w2++) { // apskata otra orderus
								
								if(cilveks.orderi.get(w1).prece==Main.cilvekiList.get(j).orderi.get(w2).prece &&
										(cilveks.orderi.get(w1).perk!=Main.cilvekiList.get(j).orderi.get(w2).perk)) { //ja abiem sakrît vçlmes un atðíiras virzieni
									
									
									preceTirgo=cilveks.orderi.get(w1).prece;
									apjomsTirgo=Math.min(cilveks.orderi.get(w1).daudzums, Main.cilvekiList.get(j).orderi.get(w2).daudzums);
									
									if (apjomsTirgo>0) { //pârbauda vai nesola tukðu
										jTirgoXY[0]=Main.cilvekiList.get(j).xyz.x;
										jTirgoXY[1]=Main.cilvekiList.get(j).xyz.y;
										
										if(cilveks.orderi.get(w1).perk==true) {
											perkNr=i;
											orderisPerkNr=w1;
											pardodNr=j;
											orderisPardodNr=w2;
										} else {
											perkNr=j;
											orderisPerkNr=w2;
											pardodNr=i;
											orderisPardodNr=w1;
										}
										
										break;
									}
									
								}
							}
						}
						// partneris atrasts, prece noskaidrota;
					}
				}
				
				
				if(distance>cilveks.R1) continue; //lai nesalîdzina sîkâk, ko nevajag
				//sâkas R1
				
				if (cilveks.komanda!=Main.cilvekiList.get(j).komanda ||
						Main.cilvekiList.get(j).komanda == "Anarhija") {
					pretiniekuSkaitsR1++;
					pretiniekuXR1+=Main.cilvekiList.get(j).xyz.x;
					pretiniekuYR1+=Main.cilvekiList.get(j).xyz.y;
					pretiniekuStiprumsR1+=Main.cilvekiList.get(j).stiprums*hpRatioJ;
					pretiniekuBrunasR1+=Main.cilvekiList.get(j).brunas;
				}
				
				if (cilveks.komanda==Main.cilvekiList.get(j).komanda &&
						cilveks.komanda != "Anarhija") {
					savejoSkaitsR1++;
					savejoXR1+=Main.cilvekiList.get(j).xyz.x;
					savejoYR1+=Main.cilvekiList.get(j).xyz.y;
					savejoStiprumsR1+=Main.cilvekiList.get(j).stiprums*hpRatioJ;
					savejoBrunasR1+=Main.cilvekiList.get(j).brunas;
				}
				
				//sadursme
				
				double resnumsJ=resnumaKoefic*Main.cilvekiList.get(j).hpmax;
				
				if(distance>(resnums+resnumsJ)/2||i==j) {continue;} else { //zemâk tikai kas saskarâs
					
					double fiTemp=Formulas.lenkaNoteiksana(cilveks.xyz.x,cilveks.xyz.y,
							Main.cilvekiList.get(j).xyz.x,Main.cilvekiList.get(j).xyz.y);
					//cilvekiList.get(i).xyz.fi=fiTemp;
					cilveks.xyz.x-=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
					cilveks.xyz.y-=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));
					Main.cilvekiList.get(j).xyz.x+=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
					Main.cilvekiList.get(j).xyz.y+=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));
					
					if (cilveks.komanda!=Main.cilvekiList.get(j).komanda || cilveks.komanda=="Anarhija") {//cîòa
						double stiprums=cilveks.stiprums;
						double stiprumsJ=Main.cilvekiList.get(j).stiprums;
						
						if (i>j) {
							CilvekuDarbibas.trauma(i,stiprumsJ*hpRatioJ, 1);
							CilvekuDarbibas.trauma(j,stiprums*hpRatioJ, 1);
						} else {
							CilvekuDarbibas.trauma(j,stiprums*hpRatioJ, 1);
							CilvekuDarbibas.trauma(i,stiprumsJ*hpRatioJ, 1);
						}
					} else { //ja ir vienâ komandâ - pârbauda tirdzniecîbu
						
						if(apjomsTirgo>0) {
							System.out.println(i+" - "+cilveks.vards+" gatavs tirgot"+preceTirgo+", pârbauda daudzumu, jâbût:"+apjomsTirgo);
							
							//apskata pârdevçju
							int zeltsNrTemp=-1,preceNrTemp2=-1;
							
							System.out.println(pardodNr+" lietas ir "+Main.cilvekiList.get(pardodNr).inventory.size());
							for(int w=0;w<Main.cilvekiList.get(pardodNr).inventory.size();w++) {
								if (preceNrTemp2>=0 && zeltsNrTemp>=0) break;
								if (Main.cilvekiList.get(pardodNr).inventory.get(w).nosaukums==preceTirgo) {
									Main.cilvekiList.get(pardodNr).inventory.get(w).daudzums-=apjomsTirgo;
									preceNrTemp2=w;
								}
								if (Main.cilvekiList.get(pardodNr).inventory.get(w).nosaukums=="Zelts") {
									zeltsNrTemp=w;
								}
							}
							System.out.println("pardodNr:"+pardodNr+" preceTemp2 "+preceNrTemp2);
							
							if (zeltsNrTemp<0) { //ja sâkumâ pârdevçjam nav naudas vispâr, uztaisa tukðu elementu
								System.out.println(pardodNr+" pardod, bet nav naudas, tapec taisa jaunu (pardod "+perkNr+")");
								Lieta samaksa = new Lieta();
								samaksa.x=Main.cilvekiList.get(pardodNr).xyz.x;
								samaksa.y=Main.cilvekiList.get(pardodNr).xyz.y;
								samaksa.nosaukums="Zelts";
								samaksa.daudzums=0;
								samaksa.zelts=1;
								samaksa.paika=0;
								samaksa.masa=1;
								samaksa.attack=0;
								samaksa.defence=0;
								samaksa.condition=1;
								
								zeltsNrTemp=Main.cilvekiList.get(pardodNr).inventory.size();
								Main.cilvekiList.get(pardodNr).inventory.add(samaksa);
							}
							
							Main.cilvekiList.get(pardodNr).inventory.get(zeltsNrTemp).daudzums+=apjomsTirgo*cenaTirgo; //pieliek naudu pârdevçjam
							Main.cilvekiList.get(pardodNr).orderi.get(orderisPardodNr).daudzums-=apjomsTirgo; //samazina orderi
							
							//apskata pircçju
							int preceNrTemp=-1;
							for(int w=0;w<Main.cilvekiList.get(perkNr).inventory.size();w++) {
								if (Main.cilvekiList.get(perkNr).inventory.get(w).nosaukums=="Zelts") {
									Main.cilvekiList.get(perkNr).inventory.get(w).daudzums-=apjomsTirgo*cenaTirgo;
								}
								if (Main.cilvekiList.get(perkNr).inventory.get(w).nosaukums==preceTirgo) {
									preceNrTemp=w;
								}
							}
							
							//System.out.println("pârbauda preceNrTemp:"+preceNrTemp+" preceNrTemp2:"+preceNrTemp2+"  apjomsTirgo:"+apjomsTirgo+" preceTirgo:"+preceTirgo);
							if (preceNrTemp<0) { //ja sâkumâ pircçjam vispâr nav tâdas preces, uztaisa tukðu elementu
								//System.out.println(perkNr+" perk, bet tas ir pirmais elements, tapec taisa jaunu (perk no "+pardodNr+") preceNrTemp2="+preceNrTemp2);
								//System.out.println("pardevejam "+pardodNr+" - "+Main.cilvekiList.get(pardodNr).vards+" ir "+Main.cilvekiList.get(pardodNr).inventory.size()+" lietas");
								Lieta pirkums = new Lieta(); //jânokopç detaïas
								
								pirkums.x=Main.cilvekiList.get(perkNr).xyz.x;
								pirkums.y=Main.cilvekiList.get(perkNr).xyz.y;
								pirkums.daudzums=0;
								
								
								pirkums.nosaukums=Main.cilvekiList.get(pardodNr).inventory.get(preceNrTemp2).nosaukums; //jâkopç manuâli, jo Java neïauj kopçt objektu (var bet tas bûs tas pats objekts, nevis 2 daþâdi)
								pirkums.zelts=Main.cilvekiList.get(pardodNr).inventory.get(preceNrTemp2).zelts;
								pirkums.paika=Main.cilvekiList.get(pardodNr).inventory.get(preceNrTemp2).paika;
								pirkums.masa=Main.cilvekiList.get(pardodNr).inventory.get(preceNrTemp2).masa;
								pirkums.attack=Main.cilvekiList.get(pardodNr).inventory.get(preceNrTemp2).attack;
								pirkums.defence=Main.cilvekiList.get(pardodNr).inventory.get(preceNrTemp2).defence;
								pirkums.condition=Main.cilvekiList.get(pardodNr).inventory.get(preceNrTemp2).condition;
								
								
								preceNrTemp=Main.cilvekiList.get(perkNr).inventory.size();
								Main.cilvekiList.get(perkNr).inventory.add(pirkums);
							}
							
							//System.out.println("preceNrTemp:"+preceNrTemp+" preceNrTemp2:"+preceNrTemp2+" apjomsTirgo:"+apjomsTirgo);
							Main.cilvekiList.get(perkNr).inventory.get(preceNrTemp).daudzums+=apjomsTirgo;
							Main.cilvekiList.get(perkNr).orderi.get(orderisPerkNr).daudzums-=apjomsTirgo; //samazina orderi
							
							//System.out.println(pardodNr+" pardeva "+perkNr+" - "+(new DecimalFormat("#.##").format(apjomsTirgo))+" "+preceTirgo+" par "+cenaTirgo+"/gab");
							
							perkNr=-1; //reseto temporary lielumus, jo tirdznieciba jau notikusi
							pardodNr=-1;
							apjomsTirgo=0;
							jTirgoXY[0]=0;
							jTirgoXY[1]=0;
							
						}
					}
				}
			}
		}
		
		
		
		
		//vçrlreiz saskaita paiku un zeltu, arî vçlreiz izdzçð tukðos
		zeltsNr=countInventory(i,"Zelts", true);
		zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;
		
		paikaNr=countInventory(i,"Paika", true);
		paikaSum=0;
		if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;
		
		
		if (zeltsSum>=(cenaCilvekam+mantojumsCilvekam) &&
				paikaSum>=mantojumsCilvekamPaika*2 &&
				savejoSkaitsR1<=2 && savejoSkaitsR2<=5) { //vairoðanâs, bet privâti
			
			CilvekuDarbibas.vairosanas(i);
		}
		
		
		boolean gatavsTirgot=false;
		if(perkNr>=0||pardodNr>=0) gatavsTirgot=true;
		//System.out.println(i+" gatavsTirgot="+gatavsTirgot+" perkNr:"+perkNr+" pardodNr:"+pardodNr);
		
		
		
		double[] pretiniekuXYR1=new double[] {pretiniekuXR1/pretiniekuSkaitsR1, pretiniekuYR1/pretiniekuSkaitsR1};
		double[] pretiniekuXYR2=new double[] {pretiniekuXR2/pretiniekuSkaitsR2, pretiniekuYR2/pretiniekuSkaitsR2};
		
		//double[] savejoXYR1=new double[] {savejoXR1/savejoSkaitsR1,savejoYR1/savejoSkaitsR1};
		//double[] savejoXYR2=new double[] {savejoXR2/savejoSkaitsR2,savejoYR2/savejoSkaitsR2};
		
		boolean mukt=false, atkapties=false, uzbrukt=false, tuvoties=false;
		double minDrosme=0.1, maxDrosme=0.9;
		cilveks.drosme=Math.max(cilveks.drosme, minDrosme);
		cilveks.drosme=Math.min(cilveks.drosme, maxDrosme);
		
		
		double  drosmesKoefic=2;
		
		if(pretiniekuSkaitsR1>0 && savejoSkaitsR1>0) {
			if( ( (pretiniekuBrunasR1/pretiniekuSkaitsR1)*cilveks.hp / (cilveks.stiprums) /
					((savejoBrunasR1/savejoSkaitsR1)*cilveks.hp / (pretiniekuStiprumsR1/pretiniekuSkaitsR1)) >
					drosmesKoefic/cilveks.drosme+drosmesKoefic*cilveks.drosme)) {
				mukt=true;
			}		
		}
		
		lootApskatsMeklesanai(i);
		
		if(navKoEst==false||paikaTuvakaDist<0) { //ja ir ko est vai neredz paiku
			if( pretiniekuSkaitsR1>0 && savejoSkaitsR1>0) {
				if((pretiniekuBrunasR1/pretiniekuSkaitsR1)*cilveks.hp / (cilveks.stiprums) /
						((savejoBrunasR1/savejoSkaitsR1)*cilveks.hp / (pretiniekuStiprumsR1/pretiniekuSkaitsR1)) <
						drosmesKoefic/cilveks.drosme+drosmesKoefic*cilveks.drosme) {
					uzbrukt=true;
				}
			}
			
			if( pretiniekuSkaitsR2>0 && savejoSkaitsR2>0) {
				if(pretiniekuSkaitsR2/savejoSkaitsR2 >
						drosmesKoefic/cilveks.drosme+drosmesKoefic*cilveks.drosme) {
					atkapties=true;
				}
				if(pretiniekuSkaitsR2/savejoSkaitsR2 <
						drosmesKoefic/cilveks.drosme+drosmesKoefic*cilveks.drosme &&
						pretiniekuSkaitsR2>0) {
					tuvoties=true;
				}
			}
		}
		
		
		
		
		if(mukt==true) {
			if(cilveks.darbiba!="mukt"&&cilveks.gataviba>=maxGataviba/10) {
				cilveks.darbiba="mukt";
				cilveks.gataviba=0;
			}
			
			if(cilveks.darbiba=="mukt") {
				cilveks.darbiba="mukt";
				
				CilvekuDarbibas.atkapties(i,pretiniekuXYR1,hpKoef,1);
				
				cilveks.drosme-=cilveks.drosme*0.1; //atòem drosmi
			}
			
		} else if(uzbrukt==true) {
			if(cilveks.darbiba!="uzbrukt"&&cilveks.gataviba>=maxGataviba) {
				cilveks.darbiba="uzbrukt";
				cilveks.gataviba=0;
			}
			
			if(cilveks.darbiba=="uzbrukt") {
				cilveks.darbiba="uzbrukt";
				
				CilvekuDarbibas.tuvoties(i,pretiniekuXYR1,hpKoef,0.9);
				
				cilveks.drosme+=cilveks.drosme*0.1; //pieskaita  drosmi
			}
			
		} else if(paikaSum<paikaNepiec && paikaTuvakaDist>0) {
			CilvekuDarbibas.mekletPaiku(i,paikaTuvakaNr,hpKoef,0.7); 
			
		} else if(zeltsTuvakaisDist>0) {
			CilvekuDarbibas.mekletZeltu(i,zeltsTuvakaisNr,hpKoef,0.6);
			
		} else if(atkapties==true) {
			if(cilveks.darbiba!="atkapties"&&cilveks.gataviba>=maxGataviba/2) {
				cilveks.darbiba="atkapties";
				cilveks.gataviba=0;
			}
			
			if(cilveks.darbiba=="atkapties") {
				cilveks.darbiba="atkapties";
				
				CilvekuDarbibas.atkapties(i,pretiniekuXYR2,hpKoef,0.8);
				
				cilveks.drosme-=cilveks.drosme*0.05;
			}
			
			
		} else if(tuvoties==true) {
			if(cilveks.darbiba!="tuvoties"&&cilveks.gataviba>=maxGataviba) {
				cilveks.darbiba="tuvoties";
				cilveks.gataviba=0;
			}
			
			if(cilveks.darbiba=="tuvoties") {
				cilveks.darbiba="tuvoties";
				
				CilvekuDarbibas.tuvoties(i,pretiniekuXYR2,hpKoef,0.8);
				
				cilveks.drosme+=cilveks.drosme*0.05;
			}
			
			
		} else if(gatavsTirgot){
			cilveks.darbiba="tirgojas";
			//System.out.println(i+" tirgojas");
			CilvekuDarbibas.tuvoties(i,jTirgoXY,hpKoef,0.6);
			
		} else if(paikaTuvakaDist>0) { //savâkt redzamo paiku pat ja nevajag
			CilvekuDarbibas.mekletPaiku(i,paikaTuvakaNr,hpKoef,0.6); 
			
		} else {
			cilveks.darbiba=""; //reseto aili
			
			CilvekuDarbibas.komanduMaina(i, 1.5, 5, 0.01, 0.01); //paikaMaina (minimums), zeltsMaina (minimums), anarchyChance, orderChance;
			 
			
			
			
			
			
			
			if (r.nextDouble()<0.1) { //iespçja, ka kaut ko taisîs
				int majasCena = 30;
				if(zeltsSum>=majasCena) {
					//buveMaju();
				
				}
			
			}
			
			CilvekuDarbibas.atputa(i, hpKoef); //ja nekas nenotiek un neko nevar izdarît
		}
		
		
		
		
		//ideâlai kustîbai pietrûkst:
			//leòía pagrieðana
			//ieskrieðanâs (paâtrinâjums)
		
		Kustiba.main(i);
		
		CilvekuDarbibas.healingAndHunger(i);
		
		System.out.println(cilveks.vards+" apskates cikls beidzies");
	}
	
	public static int countInventory(int numursCilvekam, String nosaukums, boolean cleanup) {
		Cilveks cilveks=Main.cilvekiList.get(numursCilvekam);
		
		int numurs=-1;
		@SuppressWarnings("unused")
		double daudzums=0;
		
		for (int i=0;i<cilveks.inventory.size();i++) {
			
			if(numurs<0 && cilveks.inventory.get(i).nosaukums==nosaukums && cilveks.inventory.get(i).daudzums>0) { //ja atrod pirmo atbilstoðo
				numurs=Integer.valueOf(i);
				
				daudzums+=cilveks.inventory.get(i).daudzums;;
				if(cleanup==false) break;
				
				continue;
			} 
			
			if (cilveks.inventory.get(i).nosaukums==nosaukums && cilveks.inventory.get(i).daudzums>0  && i!=numurs && numurs>=0) { //meklç atbilstoðus elementus
				
				daudzums+=cilveks.inventory.get(i).daudzums;
				
				cilveks.inventory.get(numurs).daudzums += Double.valueOf(cilveks.inventory.get(i).daudzums);
				cilveks.inventory.get(i).daudzums=0; //sagatavo dublikâtus tâlâkai izdzçðanai
				
			}
			
			if (cilveks.inventory.get(i).daudzums<=0) { //izdzçð tukðos elementus
				
				cilveks.inventory.remove(i);
				
				i--;
				continue;
			}
		}
		
		//System.out.println(cilveks.vards+"countInventory beidzies ("+nosaukums+" "+numurs+" sk:"+daudzums+") -------");
		return numurs;
	}
	
	private static void lootApskatsSadursmei(int i) { //cilvçks apskata lietas, kas izmçtâtas pa karti
		Cilveks cilveks=Main.cilvekiList.get(i);
		double resnums=resnumaKoefic*cilveks.hpmax;
		//Random r=new Random();
		
		for(int j=0;j<Main.lietas.size();j++){ 
			double distance = Math.hypot(cilveks.xyz.x-Main.lietas.get(j).x,
					cilveks.xyz.y-Main.lietas.get(j).y);
			
			double resnumsJ; //nosaka lietas resnumu
			
			if (Main.lietas.get(j).nosaukums=="Zelts") { resnumsJ = Parametri.zeltaResnums;
			} else if (Main.lietas.get(j).nosaukums=="Paika") { resnumsJ = Parametri.paikasResnums;
			} else resnumsJ = Parametri.lietasResnums; // default neklasificçtai lietai
			
			if(distance<=(resnums+resnumsJ)/2) { //paòem jebkâdu lietu, ja  saskaras
				cilveks.inventory.add(Main.lietas.get(j));
				Main.lietas.remove(j);
				j--;
				continue;
			}
		}
		
	}
	
	private static int zeltsTuvakaisNr, paikaTuvakaNr;
	private static double zeltsTuvakaisDist, paikaTuvakaDist;
	
	private static void lootApskatsMeklesanai(int i) {
		Cilveks cilveks=Main.cilvekiList.get(i);
		//double resnums=resnumaKoefic*cilveks.hpmax;
		//Random r=new Random();
		
		
		zeltsTuvakaisNr = 0; //numurs J
		paikaTuvakaNr = 0;
		
		zeltsTuvakaisDist=-1; //distance
		paikaTuvakaDist=-1;
		
		for(int j=0;j<Main.lietas.size();j++){ 
			double distance = Math.hypot(cilveks.xyz.x-Main.lietas.get(j).x,
					cilveks.xyz.y-Main.lietas.get(j).y);
			
			/*double resnumsJ; //nosaka lietas resnumu
			if (Main.lietas.get(j).nosaukums=="Zelts") { resnumsJ = zeltaResnums;
			} else if (Main.lietas.get(j).nosaukums=="Paika") { resnumsJ = paikasResnums;
			} else resnumsJ = lietasResnums;*/
			
			if(Main.lietas.get(j).nosaukums=="Zelts") {
				if (distance<zeltsTuvakaisDist || (zeltsTuvakaisDist<0 && distance<=cilveks.R2)) {
					zeltsTuvakaisDist = distance;
					zeltsTuvakaisNr=j;
				}
			}
			
			if(Main.lietas.get(j).nosaukums=="Paika") {
				if (distance<paikaTuvakaDist || (paikaTuvakaDist<0 && distance<=cilveks.R2)) {
					paikaTuvakaDist = distance;
					paikaTuvakaNr=j;
				}
			}
				
		}
	}
	
	private static void naavesPaarbaude(int numurs) {
		Cilveks cilveks = Main.cilvekiList.get(numurs);
		if(cilveks.hp<=0) {
			for(int i=0;i<cilveks.inventory.size();i++) {
				Lieta lieta = cilveks.inventory.get(i);
				lieta.x=cilveks.xyz.x;
				lieta.y=cilveks.xyz.y;
				Main.lietas.add(lieta);
				cilveks.inventory.remove(i);
				i--;
			}
			
			if (cilveks.paika<=0) { System.out.println(cilveks.vards + " nomira no bada, paliek "+(Main.cilvekiList.size()-1)+" cilvçki");
			} else { System.out.println(cilveks.vards + ". nosists, paliek " +(Main.cilvekiList.size()-1)+" cilvçki"); }
			
			Main.cilvekiList.remove(numurs);
		}
	}
	
	public static void setup() {
		
		initialize();
		pirmieSpeletaji();
		
	}
	
	private static void initialize() {
		
		mala = Parametri.mala; //laukuma izmçriem
		platums=Parametri.platums;
		augstums=Parametri.augstums;
		System.out.println("platums: "+platums+" augstums: "+augstums);
		
		vmax=Parametri.vmax;
		ommax=Parametri.ommax;
		resnumaKoefic=Parametri.resnumaKoefic; //HpMax attiecîbai pret resnumu
		maxGataviba=Parametri.maxGataviba;
		RMax=Parametri.RMax; //maksimâlais redzesloks
		R1koefic=Parametri.R1koefic; //minimâlâ redzesloka daïas koefic
		dRDzimstot=Parametri.dRDzimstot; //redzesloka procentuâla izmaiòa vairojoties
		dvMaxDzimstot=Parametri.dvMaxDzimstot; //procentuâlâs izmaiòas dzimstot
		dommaxDzimstot=Parametri.dommaxDzimstot; //procentuâlâs izmaiòas dzimstot
		
		paikaMax=Parametri.paikaMax;
		paikaMin=Parametri.paikaMin;
		esanasDaudzums=Parametri.esanasDaudzums; //par vienu pilnu paikaMax
		navKoEstTemp = false; //funkcijai 
		
		
		paikaNepiec = Parametri.paikaNepiec; //daudzums lîdz  kuram mçìinâs savâkt  paiku,  tad  skraidît apkârt
		
		brunasMax=Parametri.brunasMax;
		brunasMin=Parametri.brunasMin;
		dBrunasDzimstot=Parametri.dBrunasDzimstot;
		stiprumsMax=Parametri.stiprumsMax;
		stiprumsMin=Parametri.stiprumsMin;
		dStiprumsDzimstot=Parametri.dStiprumsDzimstot;
		
		vardsDefault=Parametri.vardsDefault;
		cenaCilvekam=Parametri.cenaCilvekam;
		mantojumsCilvekam=Parametri.mantojumsCilvekam;
		mantojumsCilvekamPaika=Parametri.mantojumsCilvekamPaika;
		
		
		dCenaProc=Parametri.dCenaProc;
		
		
	}
	
	private static void pirmieSpeletaji() {
		boolean randomKomandas=Parametri.randomKomandas;
		
		Random r = new Random();
		int skaits=Parametri.sakumaCilveki;
		
		if(Main.komandasList.size()==0) { //pati pirmâ komanda
			
			Komanda pirmaKomanda=new Komanda();
			pirmaKomanda.nosaukums="Anarhija";
			pirmaKomanda.galvenais="Nulle";
			pirmaKomanda.krasa=new Color(255,0,0);
			
			pirmaKomanda.skaits=skaits;
			
			Komanda.maxKomanda++;
			Main.komandasList.add(pirmaKomanda);
			
		}
		
		for(int i=0;i<skaits;i++) {
			Cilveks.maxCilveks++;
			String vards=vardsDefault+Cilveks.maxCilveks;
			int atstatums=30; //atstatums no laukuma paðas malas
			double x=atstatums+(platums-atstatums*2)*r.nextDouble(), y=atstatums+(augstums-atstatums*2)*r.nextDouble(); //x un y
			double v=1, fi=360*r.nextDouble();
			double vmax=Parametri.vmax, ommax=Parametri.ommax;
			double hpmax=Parametri.hpmax, hp=hpmax*(0.5+0.5*r.nextDouble());
			double paika=paikaMax;
			double R1=RMax/R1koefic*(0.5+0.5*r.nextDouble()), R2=RMax*(0.5+0.5*r.nextDouble());
			double brunas=brunasMin+(brunasMax-brunasMin)*r.nextDouble(), stiprums=stiprumsMin+(stiprumsMax-stiprumsMin)*r.nextDouble(), gataviba=100;
			double drosme=0.5+r.nextDouble()/2;
			
			String komanda;
			int[] rangs= new int[] {0,0};
			
			
			if (randomKomandas==true) {
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
			
			
			CilvekuDarbibas.dzemdibas(vards,new double[]{x,y,v,fi},vmax,ommax,hp,hpmax,paika,R1,R2,brunas,stiprums,gataviba,drosme,komanda,rangs);
		}
		
		System.out.println(skaits + " speletaji uzgenereti!");
		
	}
	
	
	
}
