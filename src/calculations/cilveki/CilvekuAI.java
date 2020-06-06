package calculations.cilveki;

import calculations.Main;
import calculations.komandas.Biedrs;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Formulas;
import calculations.lietas.Lieta;

import java.util.Random;

public class CilvekuAI {

    protected  static void main(int numurs){
        Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
        Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,biedrs.i);

        updateTradeOrders(numurs); //orderu apskats

        lootApskatsMeklesanai(numurs);

        cilvekuSalidzinajums(numurs);

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

                int vKoef=;
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

                int vKoef=;
                CilvekuDarbibas.tuvoties(chunkXY,i,pretiniekuXYR1,hpKoef,0.9);

                cilveks.drosme+=cilveks.drosme*0.1; //pieskaita  drosmi
            }

        } else if(paikaSum<paikaNepiec && paikaTuvakaDist>0) {
            int vKoef=;
            CilvekuDarbibas.mekletPaiku(chunkXY, i,paikaTuvakaNr,hpKoef,0.7);

        } else if(zeltsTuvakaisDist>0) {
            int vKoef=;
            CilvekuDarbibas.mekletZeltu(chunkXY, i,zeltsTuvakaisNr,hpKoef,0.6);

        } else if(atkapties==true) {
            if(cilveks.darbiba!="atkapties"&&cilveks.gataviba>=maxGataviba/2) {
                cilveks.darbiba="atkapties";
                cilveks.gataviba=0;
            }

            if(cilveks.darbiba=="atkapties") {
                cilveks.darbiba="atkapties";

                int vKoef=;
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

                int vKoef=;
                CilvekuDarbibas.tuvoties(chunkXY,i,pretiniekuXYR2,hpKoef,0.8);

                cilveks.drosme+=cilveks.drosme*0.05;
            }


        } else if(gatavsTirgot){
            cilveks.darbiba="tirgojas";

            int vKoef=;
            CilvekuDarbibas.tuvoties(chunkXY,i,jTirgoXY,hpKoef,0.6);

        } else if(paikaTuvakaDist>0) { //sav�kt redzamo paiku pat ja nevajag

            int vKoef=;
            CilvekuDarbibas.mekletPaiku(chunkXY,i,paikaTuvakaNr,hpKoef,0.6);

        } else {
            cilveks.darbiba=""; //reseto aili

            CilvekuDarbibas.komanduMaina(chunkXY, i, 1.5, 5, 0.01, 0.01); //paikaMaina (minimums), zeltsMaina (minimums), anarchyChance, orderChance;





            Random r=new Random();

            if (r.nextDouble()<0.1) { //iesp�ja, ka kaut ko tais�s
                int majasCena = 30;
                if(zeltsSum>=majasCena) {
                    //buveMaju();

                }

            }

            double dDrosme=0.001; //normaliz� drosmi
            if(cilveks.drosme<=0.5) cilveks.drosme+=dDrosme;
            if(cilveks.drosme>0.5) cilveks.drosme-=dDrosme;

            if(cilveks.drosme>1) cilveks.drosme=1;
            if(cilveks.drosme<0) cilveks.drosme=0;

            double vKoef=0.4;
            CilvekuDarbibas.atputa(chunkXY, i, hpKoef, vKoef); //ja nekas nenotiek un neko nevar izdar�t
        }



    }

    private static void lootApskatsMeklesanai(int numurs) {
        Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
        Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,  biedrs.i);

        //double resnums=resnumaKoefic*cilveks.hpmax;


        zeltsTuvakaisNr = 0; //numurs J
        paikaTuvakaNr = 0;

        zeltsTuvakaisDist=-1; //distance
        paikaTuvakaDist=-1;

        for(int i = 0; i< Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.size(); i++){
            double distance = Math.hypot(cilveks.xyz.x- Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.get(i).x,
                    cilveks.xyz.y- Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.get(i).y);

			/*double resnumsJ; //nosaka lietas resnumu
			if (Main.lietas.get(j).nosaukums=="Zelts") { resnumsJ = zeltaResnums;
			} else if (Main.lietas.get(j).nosaukums=="Paika") { resnumsJ = paikasResnums;
			} else resnumsJ = lietasResnums;*/

            if(Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.get(i).nosaukums=="Zelts") {
                if (distance<zeltsTuvakaisDist || (zeltsTuvakaisDist<0 && distance<=cilveks.R2)) {
                    zeltsTuvakaisDist = distance;
                    zeltsTuvakaisNr=i;
                }
            }

            if(Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.get(i).nosaukums=="Paika") {
                if (distance<paikaTuvakaDist || (paikaTuvakaDist<0 && distance<=cilveks.R2)) {
                    paikaTuvakaDist = distance;
                    paikaTuvakaNr=i;
                }
            }

        }
    }

    private static void cilvekuSalidzinajums(int numurs){
        //apskata p�r�jos sp�l�t�jus

        Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
        Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY, biedrs.i);

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

        for(int i = 0; i< Cilveks.cilvekuListPilnais.size(); i++) {
            if (i==biedrs.i) { continue; }//ar sevi nesal�dzina


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

                    double fiTemp= Formulas.lenkaNoteiksana(cilveks.xyz.x,cilveks.xyz.y,
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

                            //v�rlreiz saskaita paiku un zeltu, ar� v�lreiz izdz�� tuk�os
                            zeltsNr=cilveks.countInventory("Zelts", true);
                            zeltsSum=0;
                            if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;

                            paikaNr=cilveks.countInventory("Paika", true);
                            paikaSum=0;
                            if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;

                        }
                    }
                }
            }
        }

        if (savejoSkaitsR1<=2 && savejoSkaitsR2<=5){ //vairo�an�s, bet priv�ti
            vairosanasParbaude();
        }


    }

    //errori var b�t augst�k

    private static void vairosanasParbaude(int[] chunkXY, int i, double zeltsSum, double paikaSum){

        double cenaCilvekam=Cilveku.cenaCilvekam,
                mantojumsCilvekamZelts=Cilveku.mantojumsCilvekamZelts,
                mantojumsCilvekamPaika=Cilveku.mantojumsCilvekamPaika;

        if (zeltsSum>=(cenaCilvekam+mantojumsCilvekamZelts) &&
                paikaSum>=mantojumsCilvekamPaika*2) { //paikas  mantojums x2 lai pa�am ar� paliktu

            CilvekuDarbibas.vairosanas(chunkXY, i);
        }
    }

    //errori var b�t zem�k

    protected static void updateTradeOrders(int numurs) {
        //tirdzniec�bas orderu p�rskats

        Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
        Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,  biedrs.i);

        double defaultCena=Cilveku.paikaPriceDefault; //cenas apskats no atmi�as v�l nav ieviests
        //double apjomsMin=0.01;


        String nosaukums="";
        double sellLimit, buyLimit; //p�rdod virs sellLimit, p�rdod zem buyLimit
        for(int i=0;i<cilveks.inventory.size();i++) { //apskata katru lietu inventorij�, pie�emot, ka dublik�tu vairs nav

            nosaukums = cilveks.inventory.get(i).nosaukums;
            //System.out.println("kaut ko sprie�1: "+i+"/"+cilveks.inventory.size()+" "+nosaukums);
            if(nosaukums=="Zelts") continue; //zeltu nevar p�rdot un nevar ar� nopirkt

            if(nosaukums=="Paika") {
                sellLimit=Cilveku.sellLimitPaika;
                buyLimit=Cilveku.buyLimitPaika;
            } else { //neklasific�tiem objektiem
                sellLimit=Cilveku.sellLimitDefault;
                buyLimit=Cilveku.buyLimitDefault;
            }

            //System.out.println("buy/sell-Limit: "+buyLimit+"/"+sellLimit+" "+nosaukums);
            boolean tirgos=false, pirks=false;
            double apjoms=0;

            //System.out.println("kaut ko sprie�2: "+i+"/"+cilveks.inventory.size());

            int zeltsNr=cilveks.countInventory("Zelts", false);
            double zeltsTirgum=0;
            if (zeltsNr>=0) zeltsTirgum=cilveks.inventory.get(zeltsNr).daudzums;




            //System.out.println("kaut ko sprie�3: "+i+"/"+cilveks.inventory.size());
            if(cilveks.inventory.get(i).daudzums>sellLimit) { //p�rdo�ana
                apjoms=cilveks.inventory.get(i).daudzums-sellLimit;
                tirgos=true;
                pirks=false;
            } else if(cilveks.inventory.get(i).daudzums<buyLimit && zeltsTirgum>0) { //pirk�ana
                apjoms=Math.min(buyLimit-cilveks.inventory.get(i).daudzums, zeltsTirgum/defaultCena);
                tirgos=true;
                pirks=true;
            }
            //System.out.println("kaut ko sprie�4: "+i+"/"+cilveks.inventory.size()+" pirks:"+pirks);

            int orderNumberTemp=-1;
            for(int j=0;j<cilveks.orderi.size();j++){ //apskata visus orderus - order cleanup
                if(cilveks.orderi.get(j).prece==nosaukums) {
                    if (tirgos) { orderNumberTemp=j; } else {
                        if(cilveks.orderi.get(j).daudzums<=0) { //izdz�� tuk�os orderus
                            cilveks.orderi.remove(j);
                            j--;
                            //continue;
                        }
                    }
                }
            }

            if (tirgos) {
                if(orderNumberTemp<0) { //ja ordera v�l nav, uztaisa jaunu
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

    protected static void komanduMaina(int[] chunkXY, int numurs,
                                       double paikaMaina, double zeltsMaina, double anarchyChance, double orderChance) {
        Cilveks cilveks= Cilveks.getPlayer(chunkXY,numurs);

        int zeltsNr=cilveks.countInventory("Zelts", false);
        double zeltsSum=0;
        if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;

        int paikaNr=cilveks.countInventory("Paika", false);
        double paikaSum=0;
        if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;

        Random r = new Random();
        if (cilveks.rangs[1]==0 && paikaSum<paikaMaina && zeltsSum<zeltsMaina) { //tikai pirm� l�me�a sp�l�t�ji var�s main�t komandu
            if (Main.komandasList.size()>1 && (cilveks.komanda==Main.komandasList.get(0).nosaukums && r.nextDouble()<orderChance)) { //ja ir bez komandas, pievienojas citai (izv�l�s  random, bet ne 0)
                cilveks.komanda=Main.komandasList.get(r.nextInt(Main.komandasList.size()-1)+1).nosaukums;
            } else if (r.nextDouble()<anarchyChance && cilveks.komanda!=Main.komandasList.get(0).nosaukums) { //iesp�ja, ka pamet�s eso�o komandu (tikai, ja k�d� ir komand�)
                cilveks.komanda=Main.komandasList.get(0).nosaukums;
            }
        }
    }

    protected static void mekletZeltu(int[] chunkXY, int numurs, int i, double hpKoef, double vKoef) {
        Cilveks cilveks = Cilveks.getPlayer(chunkXY, numurs);

        cilveks.darbiba="meklet zeltu";

        CilvekuDarbibas.tuvoties(chunkXY, numurs,
                new double[] {Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(i).x,
                        Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(i).y},
                hpKoef, vKoef);
    }

    protected static void mekletPaiku(int[] chunkXY, int numurs, int i, double hpKoef, double vKoef) {
        Cilveks cilveks = Cilveks.getPlayer(chunkXY, numurs);

        cilveks.darbiba="meklet paiku";

        CilvekuDarbibas.tuvoties(chunkXY, numurs,
                new double[] {Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(i).x,
                        Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.get(i).y},
                hpKoef, vKoef);
    }

}