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

		//atkârtota saskaite un papildus cleanup
		int zeltsNr=cilveks.countInventory("Zelts", true);
		double zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;

		int paikaNr=cilveks.countInventory("Paika", true); //çdot no inventory jau bija viens cleanup
		double paikaSum=0;
		if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;

		CilvekuDarbibas.updateTradeOrders(chunkXY, i); //orderu apskats


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

		double[] pretiniekuXYR1=new double[] {pretiniekuXR1/pretiniekuSkaitsR1, pretiniekuYR1/pretiniekuSkaitsR1};
		double[] pretiniekuXYR2=new double[] {pretiniekuXR2/pretiniekuSkaitsR2, pretiniekuYR2/pretiniekuSkaitsR2};

		//double[] savejoXYR1=new double[] {savejoXR1/savejoSkaitsR1,savejoYR1/savejoSkaitsR1};
		//double[] savejoXYR2=new double[] {savejoXR2/savejoSkaitsR2,savejoYR2/savejoSkaitsR2};

		int perkNr=-1, orderisPerkNr=-1;
		int pardodNr=-1, orderisPardodNr=-1;
		String preceTirgo=" ";
		double apjomsTirgo=0;
		double cenaTirgo= Cilveku.paikaPriceDefault;// temporary default  value
		double[] jTirgoXY=new double[2];

		for(int j = 0; j< Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); j++) {
			if (j==i) { continue; }//ar sevi nesalîdzina


			double distance = Math.hypot(cilveks.xyz.x- Cilveks.getPlayer(chunkXY, j).xyz.x,
					cilveks.xyz.y- Cilveks.getPlayer(chunkXY, j).xyz.y);

			if(distance>cilveks.R2) { continue; } else { //atmet pavisam tâlos

				//sâkas R2

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

					//te  vajadzçtu arî pârbaudît tirdzniecîbas cenas un  saglabât atmiòâ



					if(cilveks.orderi.size()>0) { //pârbauda tirdzniecîbas iespçjas, bet tikai starp saviem komandasbiedriem

						for(int w1=0;w1<cilveks.orderi.size();w1++) { // apskata savus orderus

							if(apjomsTirgo>0) break;

							System.out.println(cilveks.vards+" salîdzina sevi ar "+ Cilveks.getPlayer(chunkXY, j).vards);

							for(int w2 = 0; w2< Cilveks.getPlayer(chunkXY, j).orderi.size(); w2++) { // apskata otra orderus

								if(cilveks.orderi.get(w1).prece== Cilveks.getPlayer(chunkXY, j).orderi.get(w2).prece &&
										(cilveks.orderi.get(w1).perk!= Cilveks.getPlayer(chunkXY, j).orderi.get(w2).perk)) { //ja abiem sakrît vçlmes un atðíiras virzieni


									preceTirgo=cilveks.orderi.get(w1).prece;
									apjomsTirgo=Math.min(cilveks.orderi.get(w1).daudzums, Cilveks.getPlayer(chunkXY, j).orderi.get(w2).daudzums);

									if (apjomsTirgo>0) { //pârbauda vai nesola tukðu
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


				if(distance>cilveks.R1) continue; //lai nesalîdzina sîkâk, ko nevajag
				//sâkas R1

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

				if(distance>(resnums+resnumsJ)/2||i==j) {continue;} else { //zemâk tikai kas saskarâs

					double fiTemp=Formulas.lenkaNoteiksana(cilveks.xyz.x,cilveks.xyz.y,
							Cilveks.getPlayer(chunkXY, j).xyz.x, Cilveks.getPlayer(chunkXY, j).xyz.y);
					//cilvekiList.get(i).xyz.fi=fiTemp;
					cilveks.xyz.x-=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
					cilveks.xyz.y-=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));
					Cilveks.getPlayer(chunkXY, j).xyz.x+=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
					Cilveks.getPlayer(chunkXY, j).xyz.y+=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));

					if (cilveks.komanda!= Cilveks.getPlayer(chunkXY, j).komanda || cilveks.komanda=="Anarhija") {//cîòa
						double stiprums = cilveks.stiprums, stiprumsJ = Cilveks.getPlayer(chunkXY, j).stiprums;

						if (i>j) {
							CilvekuDarbibas.trauma(chunkXY, i,stiprumsJ*hpRatioJ, 1);
							CilvekuDarbibas.trauma(chunkXY, j,stiprums*hpRatioJ, 1);
						} else {
							CilvekuDarbibas.trauma(chunkXY, j,stiprums*hpRatioJ, 1);
							CilvekuDarbibas.trauma(chunkXY, i,stiprumsJ*hpRatioJ, 1);
						}
					} else { //ja ir vienâ komandâ - pârbauda tirdzniecîbu

						if(apjomsTirgo>0) {
							System.out.println(i+" - "+cilveks.vards+" gatavs tirgot"+preceTirgo+", pârbauda daudzumu, jâbût:"+apjomsTirgo);

							//apskata pârdevçju
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

							if (zeltsNrTemp<0) { //ja sâkumâ pârdevçjam nav naudas vispâr, uztaisa tukðu elementu
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

							Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(zeltsNrTemp).daudzums+=apjomsTirgo*cenaTirgo; //pieliek naudu pârdevçjam
							Cilveks.getPlayer(chunkXY, pardodNr).orderi.get(orderisPardodNr).daudzums-=apjomsTirgo; //samazina orderi

							//apskata pircçju
							int preceNrTemp=-1;
							for(int w = 0; w< Cilveks.getPlayer(chunkXY, perkNr).inventory.size(); w++) {
								if (Cilveks.getPlayer(chunkXY, perkNr).inventory.get(w).nosaukums=="Zelts") {
									Cilveks.getPlayer(chunkXY, perkNr).inventory.get(w).daudzums-=apjomsTirgo*cenaTirgo;
								}
								if (Cilveks.getPlayer(chunkXY, perkNr).inventory.get(w).nosaukums==preceTirgo) {
									preceNrTemp=w;
								}
							}

							//System.out.println("pârbauda preceNrTemp:"+preceNrTemp+" preceNrTemp2:"+preceNrTemp2+"  apjomsTirgo:"+apjomsTirgo+" preceTirgo:"+preceTirgo);
							if (preceNrTemp<0) { //ja sâkumâ pircçjam vispâr nav tâdas preces, uztaisa tukðu elementu

								Lieta pirkums = new Lieta(); //jânokopç detaïas

								pirkums.x= Cilveks.getPlayer(chunkXY, perkNr).xyz.x;
								pirkums.y= Cilveks.getPlayer(chunkXY, perkNr).xyz.y;
								pirkums.daudzums=0;


								pirkums.nosaukums= Cilveks.getPlayer(chunkXY, pardodNr).inventory.get(preceNrTemp2).nosaukums; //jâkopç manuâli, jo Java neïauj kopçt objektu (var bet tas bûs tas pats objekts, nevis 2 daþâdi)
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




		//vçrlreiz saskaita paiku un zeltu, arî vçlreiz izdzçð tukðos
		zeltsNr=cilveks.countInventory("Zelts", true);
		zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;

		paikaNr=cilveks.countInventory("Paika", true);
		paikaSum=0;
		if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;


		if (zeltsSum>=(cenaCilvekam+mantojumsCilvekamZelts) &&
				paikaSum>=mantojumsCilvekamPaika*2 &&
				savejoSkaitsR1<=2 && savejoSkaitsR2<=5) { //vairoðanâs, bet privâti

			CilvekuDarbibas.vairosanas(chunkXY, i);
		}


		boolean gatavsTirgot=false;
		if(perkNr>=0||pardodNr>=0) gatavsTirgot=true;




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

				cilveks.drosme-=cilveks.drosme*0.1; //atòem drosmi
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

		} else if(paikaTuvakaDist>0) { //savâkt redzamo paiku pat ja nevajag
			CilvekuDarbibas.mekletPaiku(chunkXY,i,paikaTuvakaNr,hpKoef,0.6);

		} else {
			cilveks.darbiba=""; //reseto aili

			CilvekuDarbibas.komanduMaina(chunkXY, i, 1.5, 5, 0.01, 0.01); //paikaMaina (minimums), zeltsMaina (minimums), anarchyChance, orderChance;







			if (r.nextDouble()<0.1) { //iespçja, ka kaut ko taisîs
				int majasCena = 30;
				if(zeltsSum>=majasCena) {
					//buveMaju();

				}

			}

			CilvekuDarbibas.atputa(chunkXY, i, hpKoef); //ja nekas nenotiek un neko nevar izdarît
		}



	}

	protected static boolean navKoEst;
	protected static void esanaNoInventory(int numurs) {
		Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
		Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,  biedrs.i);
		
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
	
	protected static void updateTradeOrders(int numurs) {
		//tirdzniecîbas orderu pârskats

		Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
		Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,  biedrs.i);
		
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
