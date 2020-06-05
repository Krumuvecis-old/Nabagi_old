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
	
	static int mala, platums, augstums; //laukuma izm�ri
	
	//static int zeltsJ=-1, paikaJ=-1;//vajadz�gi  funkciju sasaistei
	//static double zeltsSum=0, paikaSum=0; //vajadz�gi  funkciju sasaistei
	
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

		int[] chunkXY = new int[2];

		for (chunkXY=new int[]{0,0}; chunkXY[0]<Main.laukums.size(); chunkXY[0]++){
			for ( ; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++){

				for (int i = 0; i< Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++) {
					galvenaisApskats(chunkXY, i);
				}

			}
		}

		for (chunkXY=new int[]{0,0}; chunkXY[0]<Main.laukums.size(); chunkXY[0]++){
			for ( ; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++){

				for (int i = 0; i< Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++) {
					naavesPaarbaude(chunkXY, i); //nodz�� miru�os cilv�kus
				}

			}
		}
		
	}
	
	private static void galvenaisApskats(int[] chunkXY, int i) {
		Cilveks cilveks= Cilveks.getPlayer(chunkXY, i);
		Random r=new Random();
		
		double resnums=resnumaKoefic*cilveks.hpmax;
		
		lootApskatsSadursmei(chunkXY, i);
		
		//System.out.println(cilveks.vards+" tul�t �d�s");
		CilvekuDarbibas.esanaNoInventory(chunkXY, i);
		boolean navKoEst=CilvekuDarbibas.navKoEst;
		
		//atk�rtota saskaite un papildus cleanup
		int zeltsNr=countInventory(chunkXY, i,"Zelts", true);
		double zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;
		
		int paikaNr=countInventory(chunkXY, i,"Paika", true); //�dot no inventory jau bija viens cleanup
		double paikaSum=0;
		if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;
		
		CilvekuDarbibas.updateTradeOrders(chunkXY, i); //orderu apskats
		
		
		//apskata p�r�jos sp�l�t�jus
		
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
		double cenaTirgo= Cilveku.paikaPriceDefault;// temporary default  value
		double[] jTirgoXY=new double[2];
		
		for(int j = 0; j< Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); j++) {
			if (j==i) { continue; }//ar sevi nesal�dzina
			
			
			double distance = Math.hypot(cilveks.xyz.x- Cilveks.getPlayer(chunkXY, j).xyz.x,
					cilveks.xyz.y- Cilveks.getPlayer(chunkXY, j).xyz.y);
			
			if(distance>cilveks.R2) { continue; } else { //atmet pavisam t�los
				
				//s�kas R2
				
				double hpRatioJ = Cilveks.getPlayer(chunkXY, j).hp/ Cilveks.getPlayer(chunkXY, j).hpmax;
				
				if (cilveks.komanda!= Cilveks.getPlayer(chunkXY, j).komanda ||
						Cilveks.getPlayer(chunkXY, j).komanda == "Anarhija") {
					pretiniekuSkaitsR2++;
					pretiniekuXR2+= Cilveks.getPlayer(chunkXY, j).xyz.x;
					pretiniekuYR2+= Cilveks.getPlayer(chunkXY, j).xyz.y;
					pretiniekuStiprumsR2+= Cilveks.getPlayer(chunkXY, j).stiprums*hpRatioJ;
					pretiniekuBrunasR2+= Cilveks.getPlayer(chunkXY, j).brunas;
				}
				
				if (cilveks.komanda== Cilveks.getPlayer(chunkXY, j).komanda &&
						cilveks.komanda != "Anarhija") {
					savejoSkaitsR2++;
					savejoXR2+= Cilveks.getPlayer(chunkXY, j).xyz.x;
					savejoYR2+= Cilveks.getPlayer(chunkXY, j).xyz.y;
					savejoStiprumsR2+= Cilveks.getPlayer(chunkXY, j).stiprums*hpRatioJ;
					savejoBrunasR2+= Cilveks.getPlayer(chunkXY, j).brunas;
					
					//te  vajadz�tu ar� p�rbaud�t tirdzniec�bas cenas un  saglab�t atmi��
					
					
					
					if(cilveks.orderi.size()>0) { //p�rbauda tirdzniec�bas iesp�jas, bet tikai starp saviem komandasbiedriem
						
						for(int w1=0;w1<cilveks.orderi.size();w1++) { // apskata savus orderus
							
							if(apjomsTirgo>0) break;
							
							System.out.println(cilveks.vards+" sal�dzina sevi ar "+ Cilveks.getPlayer(chunkXY, j).vards);
							
							for(int w2 = 0; w2< Cilveks.getPlayer(chunkXY, j).orderi.size(); w2++) { // apskata otra orderus
								
								if(cilveks.orderi.get(w1).prece== Cilveks.getPlayer(chunkXY, j).orderi.get(w2).prece &&
										(cilveks.orderi.get(w1).perk!= Cilveks.getPlayer(chunkXY, j).orderi.get(w2).perk)) { //ja abiem sakr�t v�lmes un at��iras virzieni
									
									
									preceTirgo=cilveks.orderi.get(w1).prece;
									apjomsTirgo=Math.min(cilveks.orderi.get(w1).daudzums, Cilveks.getPlayer(chunkXY, j).orderi.get(w2).daudzums);
									
									if (apjomsTirgo>0) { //p�rbauda vai nesola tuk�u
										jTirgoXY[0]= Cilveks.getPlayer(chunkXY, j).xyz.x;
										jTirgoXY[1]= Cilveks.getPlayer(chunkXY, j).xyz.y;
										
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
				
				
				if(distance>cilveks.R1) continue; //lai nesal�dzina s�k�k, ko nevajag
				//s�kas R1
				
				if (cilveks.komanda!= Cilveks.getPlayer(chunkXY, j).komanda ||
						Cilveks.getPlayer(chunkXY, j).komanda == "Anarhija") {
					pretiniekuSkaitsR1++;
					pretiniekuXR1+= Cilveks.getPlayer(chunkXY, j).xyz.x;
					pretiniekuYR1+= Cilveks.getPlayer(chunkXY, j).xyz.y;
					pretiniekuStiprumsR1+= Cilveks.getPlayer(chunkXY, j).stiprums*hpRatioJ;
					pretiniekuBrunasR1+= Cilveks.getPlayer(chunkXY, j).brunas;
				}
				
				if (cilveks.komanda== Cilveks.getPlayer(chunkXY, j).komanda &&
						cilveks.komanda != "Anarhija") {
					savejoSkaitsR1++;
					savejoXR1+= Cilveks.getPlayer(chunkXY, j).xyz.x;
					savejoYR1+= Cilveks.getPlayer(chunkXY, j).xyz.y;
					savejoStiprumsR1+= Cilveks.getPlayer(chunkXY, j).stiprums*hpRatioJ;
					savejoBrunasR1+= Cilveks.getPlayer(chunkXY, j).brunas;
				}
				
				//sadursme
				
				double resnumsJ=resnumaKoefic* Cilveks.getPlayer(chunkXY, j).hpmax;
				
				if(distance>(resnums+resnumsJ)/2||i==j) {continue;} else { //zem�k tikai kas saskar�s
					
					double fiTemp=Formulas.lenkaNoteiksana(cilveks.xyz.x,cilveks.xyz.y,
							Cilveks.getPlayer(chunkXY, j).xyz.x, Cilveks.getPlayer(chunkXY, j).xyz.y);
					//cilvekiList.get(i).xyz.fi=fiTemp;
					cilveks.xyz.x-=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
					cilveks.xyz.y-=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));
					Cilveks.getPlayer(chunkXY, j).xyz.x+=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
					Cilveks.getPlayer(chunkXY, j).xyz.y+=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));
					
					if (cilveks.komanda!= Cilveks.getPlayer(chunkXY, j).komanda || cilveks.komanda=="Anarhija") {//c��a
						double stiprums = cilveks.stiprums, stiprumsJ = Cilveks.getPlayer(chunkXY, j).stiprums;
						
						if (i>j) {
							CilvekuDarbibas.trauma(chunkXY, i,stiprumsJ*hpRatioJ, 1);
							CilvekuDarbibas.trauma(chunkXY, j,stiprums*hpRatioJ, 1);
						} else {
							CilvekuDarbibas.trauma(chunkXY, j,stiprums*hpRatioJ, 1);
							CilvekuDarbibas.trauma(chunkXY, i,stiprumsJ*hpRatioJ, 1);
						}
					} else { //ja ir vien� komand� - p�rbauda tirdzniec�bu
						
						if(apjomsTirgo>0) {
							System.out.println(i+" - "+cilveks.vards+" gatavs tirgot"+preceTirgo+", p�rbauda daudzumu, j�b�t:"+apjomsTirgo);
							
							//apskata p�rdev�ju
							int zeltsNrTemp=-1,preceNrTemp2=-1;
							
							System.out.println(pardodNr+" lietas ir "+ Cilveks.getPlayer(chunkXY, pardodNr).inventory.size());
							for(int w = 0; w< Cilveks.getPlayer(chunkXY, pardodNr).inventory.size(); w++) {
								if (preceNrTemp2>=0 && zeltsNrTemp>=0) break;
								if (Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(w).nosaukums==preceTirgo) {
									Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(w).daudzums-=apjomsTirgo;
									preceNrTemp2=w;
								}
								if (Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(w).nosaukums=="Zelts") {
									zeltsNrTemp=w;
								}
							}
							System.out.println("pardodNr:"+pardodNr+" preceTemp2 "+preceNrTemp2);
							
							if (zeltsNrTemp<0) { //ja s�kum� p�rdev�jam nav naudas visp�r, uztaisa tuk�u elementu
								Lieta samaksa = new Lieta();
								samaksa.x= Cilveks.getPlayer(chunkXY, pardodNr).xyz.x;
								samaksa.y= Cilveks.getPlayer(chunkXY, pardodNr).xyz.y;
								samaksa.nosaukums="Zelts";
								samaksa.daudzums=0;
								samaksa.zelts=1;
								samaksa.paika=0;
								samaksa.masa=1;
								samaksa.attack=0;
								samaksa.defence=0;
								samaksa.condition=1;
								
								zeltsNrTemp= Cilveks.getPlayer(chunkXY, pardodNr).inventory.size();
								Cilveks.getPlayer(chunkXY, pardodNr).inventory.add(samaksa);
							}

							Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(zeltsNrTemp).daudzums+=apjomsTirgo*cenaTirgo; //pieliek naudu p�rdev�jam
							Cilveks.getPlayer(chunkXY, pardodNr).orderi.get(orderisPardodNr).daudzums-=apjomsTirgo; //samazina orderi
							
							//apskata pirc�ju
							int preceNrTemp=-1;
							for(int w = 0; w< Cilveks.getPlayer(chunkXY, perkNr).inventory.size(); w++) {
								if (Cilveks.getPlayer(chunkXY, perkNr).inventory.get(w).nosaukums=="Zelts") {
									Cilveks.getPlayer(chunkXY, perkNr).inventory.get(w).daudzums-=apjomsTirgo*cenaTirgo;
								}
								if (Cilveks.getPlayer(chunkXY, perkNr).inventory.get(w).nosaukums==preceTirgo) {
									preceNrTemp=w;
								}
							}

							//System.out.println("p�rbauda preceNrTemp:"+preceNrTemp+" preceNrTemp2:"+preceNrTemp2+"  apjomsTirgo:"+apjomsTirgo+" preceTirgo:"+preceTirgo);
							if (preceNrTemp<0) { //ja s�kum� pirc�jam visp�r nav t�das preces, uztaisa tuk�u elementu

								Lieta pirkums = new Lieta(); //j�nokop� deta�as
								
								pirkums.x= Cilveks.getPlayer(chunkXY, perkNr).xyz.x;
								pirkums.y= Cilveks.getPlayer(chunkXY, perkNr).xyz.y;
								pirkums.daudzums=0;


								pirkums.nosaukums= Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(preceNrTemp2).nosaukums; //j�kop� manu�li, jo Java ne�auj kop�t objektu (var bet tas b�s tas pats objekts, nevis 2 da��di)
								pirkums.zelts= Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(preceNrTemp2).zelts;
								pirkums.paika= Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(preceNrTemp2).paika;
								pirkums.masa= Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(preceNrTemp2).masa;
								pirkums.attack= Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(preceNrTemp2).attack;
								pirkums.defence= Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(preceNrTemp2).defence;
								pirkums.condition= Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(preceNrTemp2).condition;
								
								
								preceNrTemp= Cilveks.getPlayer(chunkXY, perkNr).inventory.size();
								Cilveks.getPlayer(chunkXY, perkNr).inventory.add(pirkums);
							}
							

							Cilveks.getPlayer(chunkXY, perkNr).inventory.get(preceNrTemp).daudzums+=apjomsTirgo;
							Cilveks.getPlayer(chunkXY, perkNr).orderi.get(orderisPerkNr).daudzums-=apjomsTirgo; //samazina orderi
							

							
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
		
		
		
		
		//v�rlreiz saskaita paiku un zeltu, ar� v�lreiz izdz�� tuk�os
		zeltsNr=countInventory(chunkXY, i,"Zelts", true);
		zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;
		
		paikaNr=countInventory(chunkXY, i,"Paika", true);
		paikaSum=0;
		if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;
		
		
		if (zeltsSum>=(cenaCilvekam+mantojumsCilvekamZelts) &&
				paikaSum>=mantojumsCilvekamPaika*2 &&
				savejoSkaitsR1<=2 && savejoSkaitsR2<=5) { //vairo�an�s, bet priv�ti
			
			CilvekuDarbibas.vairosanas(chunkXY, i);
		}
		
		
		boolean gatavsTirgot=false;
		if(perkNr>=0||pardodNr>=0) gatavsTirgot=true;
		

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
		
		lootApskatsMeklesanai(chunkXY, i);
		
		if(navKoEst==false || paikaTuvakaDist<0) { //ja ir ko est vai neredz paiku
			if( pretiniekuSkaitsR1>0 && savejoSkaitsR1>0) {
				if((pretiniekuBrunasR1/pretiniekuSkaitsR1)*cilveks.hp / (cilveks.stiprums) /
						((savejoBrunasR1/savejoSkaitsR1)*cilveks.hp / (pretiniekuStiprumsR1/pretiniekuSkaitsR1)) <
						drosmesKoefic/cilveks.drosme+drosmesKoefic*cilveks.drosme) {
					uzbrukt=true;
				}
			} else if( pretiniekuSkaitsR2>0 && savejoSkaitsR2>0) {
				if(pretiniekuSkaitsR2/savejoSkaitsR2 >
						drosmesKoefic/cilveks.drosme+drosmesKoefic*cilveks.drosme) {
					atkapties=true;
				} else tuvoties=true;
			}
		}
		
		
		
		
		if(mukt) {
			if(cilveks.darbiba!="mukt"&&cilveks.gataviba>=maxGataviba/10) {
				cilveks.darbiba="mukt";
				cilveks.gataviba=0;
			}
			
			if(cilveks.darbiba=="mukt") {
				cilveks.darbiba="mukt";
				
				CilvekuDarbibas.atkapties(chunkXY,i,pretiniekuXYR1,hpKoef,1);
				
				cilveks.drosme-=cilveks.drosme*0.1; //at�em drosmi
			}
			
		} else if(uzbrukt) {
			if(cilveks.darbiba!="uzbrukt" && cilveks.gataviba>=maxGataviba) {
				cilveks.darbiba="uzbrukt";
				cilveks.gataviba=0;
			}
			
			if(cilveks.darbiba=="uzbrukt") {
				cilveks.darbiba="uzbrukt";
				
				CilvekuDarbibas.tuvoties(chunkXY,i,pretiniekuXYR1,hpKoef,0.9);
				
				cilveks.drosme+=cilveks.drosme*0.1; //pieskaita  drosmi
			}
			
		} else if(paikaSum<paikaNepiec && paikaTuvakaDist>0) {
			CilvekuDarbibas.mekletPaiku(chunkXY, i,paikaTuvakaNr,hpKoef,0.7);
			
		} else if(zeltsTuvakaisDist>0) {
			CilvekuDarbibas.mekletZeltu(chunkXY, i,zeltsTuvakaisNr,hpKoef,0.6);
			
		} else if(atkapties==true) {
			if(cilveks.darbiba!="atkapties"&&cilveks.gataviba>=maxGataviba/2) {
				cilveks.darbiba="atkapties";
				cilveks.gataviba=0;
			}
			
			if(cilveks.darbiba=="atkapties") {
				cilveks.darbiba="atkapties";
				
				CilvekuDarbibas.atkapties(chunkXY, i,pretiniekuXYR2,hpKoef,0.8);
				
				cilveks.drosme-=cilveks.drosme*0.05;
			}
			
			
		} else if(tuvoties==true) {
			if(cilveks.darbiba!="tuvoties"&&cilveks.gataviba>=maxGataviba) {
				cilveks.darbiba="tuvoties";
				cilveks.gataviba=0;
			}
			
			if(cilveks.darbiba=="tuvoties") {
				cilveks.darbiba="tuvoties";
				
				CilvekuDarbibas.tuvoties(chunkXY,i,pretiniekuXYR2,hpKoef,0.8);
				
				cilveks.drosme+=cilveks.drosme*0.05;
			}
			
			
		} else if(gatavsTirgot){
			cilveks.darbiba="tirgojas";
			//System.out.println(i+" tirgojas");
			CilvekuDarbibas.tuvoties(chunkXY,i,jTirgoXY,hpKoef,0.6);
			
		} else if(paikaTuvakaDist>0) { //sav�kt redzamo paiku pat ja nevajag
			CilvekuDarbibas.mekletPaiku(chunkXY,i,paikaTuvakaNr,hpKoef,0.6);
			
		} else {
			cilveks.darbiba=""; //reseto aili
			
			CilvekuDarbibas.komanduMaina(chunkXY, i, 1.5, 5, 0.01, 0.01); //paikaMaina (minimums), zeltsMaina (minimums), anarchyChance, orderChance;
			 
			
			
			
			
			
			
			if (r.nextDouble()<0.1) { //iesp�ja, ka kaut ko tais�s
				int majasCena = 30;
				if(zeltsSum>=majasCena) {
					//buveMaju();
				
				}
			
			}
			
			CilvekuDarbibas.atputa(chunkXY, i, hpKoef); //ja nekas nenotiek un neko nevar izdar�t
		}
		
		
		
		
		//ide�lai kust�bai pietr�kst:
			//le��a pagrie�ana
			//ieskrie�an�s (pa�trin�jums)
		
		Kustiba.main(cilveks);
		
		CilvekuDarbibas.healingAndHunger(chunkXY, i);
	}
	
	public static int countInventory(int[] chunkXY, int numursCilvekam, String nosaukums, boolean cleanup) {
		Cilveks cilveks = Cilveks.getPlayer(chunkXY, numursCilvekam);
		
		int numurs=-1;
		@SuppressWarnings("unused")
		double daudzums=0;
		
		for (int i=0;i<cilveks.inventory.size();i++) {
			
			if(numurs<0 && cilveks.inventory.get(i).nosaukums==nosaukums && cilveks.inventory.get(i).daudzums>0) { //ja atrod pirmo atbilsto�o
				numurs=Integer.valueOf(i);
				
				daudzums+=cilveks.inventory.get(i).daudzums;;
				if(cleanup==false) break;
				
				continue;
			} 
			
			if (cilveks.inventory.get(i).nosaukums==nosaukums && cilveks.inventory.get(i).daudzums>0  && i!=numurs && numurs>=0) { //mekl� atbilsto�us elementus
				
				daudzums+=cilveks.inventory.get(i).daudzums;
				
				cilveks.inventory.get(numurs).daudzums += Double.valueOf(cilveks.inventory.get(i).daudzums);
				cilveks.inventory.get(i).daudzums=0; //sagatavo dublik�tus t�l�kai izdz��anai
				
			}
			
			if (cilveks.inventory.get(i).daudzums<=0) { //izdz�� tuk�os elementus
				
				cilveks.inventory.remove(i);
				
				i--;
				continue;
			}
		}
		
		//System.out.println(cilveks.vards+"countInventory beidzies ("+nosaukums+" "+numurs+" sk:"+daudzums+") -------");
		return numurs;
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
		Cilveks cilveks = Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(numurs);
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
		
		mala = KonstantesUniversal.mala; //laukuma izm�riem
		platums = KonstantesUniversal.laukumaPlatumsSum;
		augstums = KonstantesUniversal.laukumaAugstumsSum;
		
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
			Koord xyz = new Koord();
			xyz.x = KonstantesUniversal.mapChunkW * r.nextDouble();
			xyz.y = KonstantesUniversal.mapChunkW * r.nextDouble();
			xyz.v = 0;
			xyz.fi = 360*r.nextDouble();
			xyz.xChunk = r.nextInt(KonstantesUniversal.mapChunkCountX);
			xyz.yChunk = r.nextInt(KonstantesUniversal.mapChunkCountY);

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
			


			CilvekuDarbibas.dzemdibas(vards,xyz,vmax,ommax,hp,hpmax,paika,R1,R2,brunas,stiprums,gataviba,drosme,komanda,rangs);
		}
		
	}
	
	
	
}
