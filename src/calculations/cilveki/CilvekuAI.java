package calculations.cilveki;

import calculations.Location;
import calculations.Main;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Formulas;
import calculations.lietas.Lieta;

import java.util.ArrayList;
import java.util.Random;

public class CilvekuAI {

    private static Location perkBiedrs, pardodBiedrs;

    protected  static void main(int numurs){
        Location location = Cilveks.cilvekuListPilnais.get(numurs);
        Cilveks cilveks = Cilveks.getPlayer(location);
        double hpRatio=cilveks.hp/cilveks.hpmax;

        if (!checkCloseRange()) if(!checkMediumRange()) if(!checkFarRange()) miscDarbibas(cilveks, zeltsSum);




        //errori var b�t zem�k



        lootApskatsMeklesanai(numurs);



        cilvekuSalidzinajums(numurs);

        boolean gatavsTirgot=false;
        if(perkBiedrs.i>=0||pardodBiedrs.i>=0) gatavsTirgot=true;

        //atk�rtota saskaite un papildus cleanup
        int zeltsNr=cilveks.countInventory("Zelts", true);
        double zeltsSum=0;
        if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;

        int paikaNr=cilveks.countInventory("Paika", true); //�dot no inventory jau bija viens cleanup
        double paikaSum=0;
        if (paikaNr>=0) paikaSum=cilveks.inventory.get(paikaNr).daudzums;



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

        lootApskatsMeklesanai(numurs);

        if(!cilveks.navKoEst || paikaTuvakaDist<0) { //ja ir ko est vai neredz paiku
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
            if(cilveks.darbibas.darbiba!="mukt"&&cilveks.gataviba>=Cilveku.maxGataviba/10) {
                cilveks.darbibas.darbiba="mukt";
                cilveks.gataviba=0;
            }

            if(cilveks.darbibas.darbiba=="mukt") {
                Darbibas.atkapties(cilveks,pretiniekuXYR1,chunkXY,1);

                cilveks.drosme-=cilveks.drosme*0.1; //at�em drosmi
            }

        } else if(uzbrukt) {
            if(!cilveks.darbibas.darbiba.equals("uzbrukt") && cilveks.gataviba>=Cilveku.maxGataviba) {
                cilveks.gataviba=0;
            }

            if(cilveks.darbibas.darbiba.equals("uzbrukt")) {
                Darbibas.tuvoties(cilveks,pretiniekuXYR1,chunkXY,0.9);

                cilveks.drosme+=cilveks.drosme*0.1; //pieskaita  drosmi
            }

        } else if(paikaSum<Cilveku.paikaNepiec && paikaTuvakaDist>0) {
            mekletPaiku(cilveks,paikaTuvakaXY,chunkXY,0.7);

        } else if(zeltsTuvakaisDist>0) {
            mekletZeltu(cilveks,zeltsTuvakaisXY,chunkXY,0.6);

        } else if(atkapties) {
            if(!cilveks.darbibas.darbiba.equals("atkapties") && cilveks.gataviba>=Cilveku.maxGataviba/2) {
                cilveks.darbibas.darbiba="atkapties";
                cilveks.gataviba=0;
            }

            if(cilveks.darbibas.darbiba.equals("atkapties")) {
                Darbibas.atkapties(cilveks,pretiniekuXYR2,chunkXY,0.8);

                cilveks.drosme-=cilveks.drosme*0.05;
            }


        } else if(tuvoties) {
            if(!cilveks.darbibas.darbiba.equals("tuvoties") && cilveks.gataviba>=Cilveku.maxGataviba) {
                cilveks.darbibas.darbiba="tuvoties";
                cilveks.gataviba=0;
            }

            if(cilveks.darbibas.darbiba.equals("tuvoties")) {
                Darbibas.tuvoties(cilveks,pretiniekuXYR2,chunkXY,0.6);

                cilveks.drosme+=cilveks.drosme*0.05;
            }


        } else if(gatavsTirgot){
            cilveks.darbibas.darbiba="tirgojas";
            tuvoties(cilveks,jTirgoXY,chunkXY[],0.6);

        } else if(paikaTuvakaDist>0) { //sav�kt redzamo paiku pat ja nevajag
            mekletPaiku(cilveks, XY[], chunkXY[], 0.6);

        } else {
            //misc darbibas
        }



    }

    private static boolean checkCloseRange(){
        boolean nolemts=false;

        //      updateTradeOrders (ar cleanup)
        updateTradeOrders(numurs); //orderu apskats
        //      tradingRange check for trading (varb�t v�l viens trade order cleanup?)
        //      melee combat range check for attack/defence
        //      citas opcijas n�kotnei


        return nolemts;
    }

    private static boolean checkMediumRange(){
        boolean nolemts=false;

        //      sp�l�t�ju apskats, lai nov�rt�tu sav�jos&pretiniekus
        //      vairo�an�s p�rbaude
        //      loot v�k�anas apskats
        //      tirdzniec�bas partneru mekl��ana
        //      citas opcijas n�kotnei


        return nolemts;
    }

    private static boolean checkFarRange(){
        boolean nolemts=false;

        //      cilv�ku apskats
        //      loot apskats

        return nolemts;
    }

    private static void miscDarbibas(Cilveks cilveks, double zeltsSum){
        cilveks.darbibas.darbiba=""; //reseto aili

        komanduMaina(cilveks, 1.5, 5, 0.01, 0.01);



        buvniecibasParbaude(cilveks, zeltsSum);

        double dDrosme=0.001; //normaliz� drosmi
        if(cilveks.drosme<=0.5) cilveks.drosme+=dDrosme;
        if(cilveks.drosme>0.5) cilveks.drosme-=dDrosme;

        if(cilveks.drosme>1) cilveks.drosme=1;
        if(cilveks.drosme<0) cilveks.drosme=0;

        atputa(cilveks); //ja nekas nenotiek un neko nevar izdar�t

        //gatav�bas update
        double dGataviba=1;
        if(cilveks.gataviba<=Cilveku.maxGataviba-dGataviba) cilveks.gataviba+=dGataviba;
    }


    //private static int zeltsTuvakaisNr, paikaTuvakaNr;
    //private static double zeltsTuvakaisDist, paikaTuvakaDist;

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

        perkBiedrs.i=-1;
        pardodBiedrs.i=-1;
        int orderisPerkNr=-1, orderisPardodNr=-1;
        String preceTirgo=" ";
        double apjomsTirgo=0;
        double cenaTirgo= Cilveku.paikaPriceDefault;// temporary default  value
        double[] jTirgoXY=new double[2];

        int chunkViewDistance=1;
        for(int[] dChunkXY={-chunkViewDistance,-chunkViewDistance}; dChunkXY[0]<=chunkViewDistance; dChunkXY[0]++){
            for( ; dChunkXY[1]<=chunkViewDistance; dChunkXY[1]++){

                int[] chunkXY = {biedrs.chunkXY[0]+dChunkXY[0], biedrs.chunkXY[1]+dChunkXY[1]};
                ArrayList<Cilveks> cilvekiList = Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList;

                for(int i = 0; i < cilvekiList.size(); i++) {
                    if (biedrs.chunkXY==chunkXY && biedrs.i==i) { continue; }//ar sevi nesal�dzina

                    Cilveks cilveks2 = Cilveks.getPlayer(chunkXY, i);

                    //pie dx un dy j�pieskaita chunk nob�des rad�t�s izmai�as --- !!!!!!
                    double dx=cilveks.xyz.x - cilveks2.xyz.x,
                            dy=cilveks.xyz.y - cilveks2.xyz.y;

                    double distance = Math.hypot(dx, dy);

                    if(distance>cilveks.R2) { continue; } else { //atmet pavisam t�los

                        //s�kas R2

                        double hpRatio2 = cilveks2.hp / cilveks2.hpmax;

                        if (!cilveks.komanda.equals(cilveks2.komanda) ||
                                cilveks2.komanda.equals("Anarhija")) {
                            pretiniekuSkaitsR2++;
                            pretiniekuXR2+= cilveks2.xyz.x;
                            pretiniekuYR2+= cilveks2.xyz.y;
                            pretiniekuStiprumsR2+= cilveks2.stiprums*hpRatio2;
                            pretiniekuBrunasR2+= cilveks2.brunas;
                        }

                        if (cilveks.komanda.equals(cilveks2.komanda) && !cilveks.komanda.equals("Anarhija")) {
                            savejoSkaitsR2++;
                            savejoXR2+= cilveks2.xyz.x;
                            savejoYR2+= cilveks2.xyz.y;
                            savejoStiprumsR2+= cilveks2.stiprums*hpRatio2;
                            savejoBrunasR2+= cilveks2.brunas;

                            //te  vajadz�tu ar� p�rbaud�t tirdzniec�bas cenas un  saglab�t atmi��



                            if(cilveks.orderi.size()>0) { //p�rbauda tirdzniec�bas iesp�jas, bet tikai starp saviem komandasbiedriem

                                for(int w1=0;w1<cilveks.orderi.size();w1++) { // apskata savus orderus

                                    if(apjomsTirgo>0) break;

                                    System.out.println(cilveks.vards+" sal�dzina sevi ar "+ cilveks2.vards);

                                    for(int w2 = 0; w2< cilveks2.orderi.size(); w2++) { // apskata otra orderus

                                        if(cilveks.orderi.get(w1).prece.equals(cilveks2.orderi.get(w2).prece) &&
                                                (cilveks.orderi.get(w1).perk != cilveks2.orderi.get(w2).perk)) { //ja abiem sakr�t v�lmes un at��iras virzieni


                                            preceTirgo=cilveks.orderi.get(w1).prece;
                                            apjomsTirgo=Math.min(cilveks.orderi.get(w1).daudzums, cilveks2.orderi.get(w2).daudzums);

                                            if (apjomsTirgo>0) { //p�rbauda vai nesola tuk�u
                                                jTirgoXY[0] = cilveks2.xyz.x;
                                                jTirgoXY[1] = cilveks2.xyz.y;

                                                if(cilveks.orderi.get(w1).perk) {
                                                    perkBiedrs=biedrs;
                                                    orderisPerkNr=w1;
                                                    pardodBiedrs.i=i;
                                                    pardodBiedrs.chunkXY=chunkXY;
                                                    orderisPardodNr=w2;
                                                } else {
                                                    perkBiedrs.i=i;
                                                    perkBiedrs.chunkXY=chunkXY;
                                                    orderisPerkNr=w2;
                                                    pardodBiedrs=biedrs;
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

                        if (!cilveks.komanda.equals(cilveks2.komanda) ||
                                cilveks2.komanda.equals("Anarhija")) {
                            pretiniekuSkaitsR1++;
                            pretiniekuXR1+= cilveks2.xyz.x;
                            pretiniekuYR1+= cilveks2.xyz.y;
                            pretiniekuStiprumsR1+= cilveks2.stiprums*hpRatio2;
                            pretiniekuBrunasR1+= cilveks2.brunas;
                        }

                        if (cilveks.komanda.equals(cilveks2.komanda) &&
                                !cilveks.komanda.equals("Anarhija")) {
                            savejoSkaitsR1++;
                            savejoXR1+= cilveks2.xyz.x;
                            savejoYR1+= cilveks2.xyz.y;
                            savejoStiprumsR1+= cilveks2.stiprums*hpRatio2;
                            savejoBrunasR1+= cilveks2.brunas;
                        }

                        //sadursme

                        double resnums=calculations.konstantes.Fizikas.resnumaKoefic*cilveks.hpmax;
                        double resnumsJ=calculations.konstantes.Fizikas.resnumaKoefic* cilveks2.hpmax;

                        if(distance>(resnums+resnumsJ)/2) {continue;} else { //zem�k tikai kas saskar�s

                            double fiTemp= Formulas.lenkaNoteiksana(cilveks.xyz.x,cilveks.xyz.y,
                                    cilveks2.xyz.x, cilveks2.xyz.y);
                            //cilvekiList.get(i).xyz.fi=fiTemp;
                            cilveks.xyz.x-=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
                            cilveks.xyz.y-=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));
                            cilveks2.xyz.x+=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
                            cilveks2.xyz.y+=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));

                            if (!cilveks.komanda.equals(cilveks2.komanda) || cilveks.komanda.equals("Anarhija")) {//c��a
                                double stiprums = cilveks.stiprums, stiprumsJ = cilveks2.stiprums;

                                Cilveks.trauma(cilveks2,stiprums*hpRatio2, 1); //sadod pretiniekam
                                Cilveks.trauma(cilveks,stiprumsJ*hpRatio2, 1); //sa�em pret�

                            } else { //ja ir vien� komand� - p�rbauda tirdzniec�bu

                                if(apjomsTirgo>0) {

                                    Cilveks pardevejs = Cilveks.getPlayer(pardodBiedrs.chunkXY, pardodBiedrs.i),
                                            pircejs = Cilveks.getPlayer(perkBiedrs.chunkXY, perkBiedrs.i);

                                    //apskata p�rdev�ju
                                    int zeltsNrTemp=-1, preceNrTemp=-1;

                                    for(int w = 0; w< pardevejs.inventory.size(); w++) {
                                        if (preceNrTemp>=0 && zeltsNrTemp>=0) break;
                                        if (pardevejs.inventory.get(w).nosaukums==preceTirgo) {
                                            pardevejs.inventory.get(w).daudzums-=apjomsTirgo;
                                            preceNrTemp=w;
                                        }
                                        if (pardevejs.inventory.get(w).nosaukums=="Zelts") {
                                            zeltsNrTemp=w;
                                        }
                                    }

                                    if (zeltsNrTemp<0) { //ja s�kum� p�rdev�jam nav naudas visp�r, uztaisa tuk�u elementu
                                        Lieta samaksa = new Lieta();
                                        samaksa.x= pardevejs.xyz.x;
                                        samaksa.y= pardevejs.xyz.y;
                                        samaksa.nosaukums="Zelts";
                                        samaksa.daudzums=0;
                                        samaksa.zelts=1;
                                        samaksa.paika=0;
                                        samaksa.masa=1;
                                        samaksa.attack=0;
                                        samaksa.defence=0;
                                        samaksa.condition=1;

                                        zeltsNrTemp= pardevejs.inventory.size();
                                        pardevejs.inventory.add(samaksa);
                                    }

                                    //apskata pirc�ju
                                    int preceNrTemp=-1;
                                    for(int w = 0; w< pircejs.inventory.size(); w++) {
                                        if (pircejs.inventory.get(w).nosaukums=="Zelts") {
                                            pircejs.inventory.get(w).daudzums-=apjomsTirgo*cenaTirgo;
                                        }
                                        if (pircejs.inventory.get(w).nosaukums==preceTirgo) {
                                            preceNrTemp=w;
                                        }
                                    }

                                    if (preceNrTemp<0) { //ja s�kum� pirc�jam visp�r nav t�das preces, uztaisa tuk�u elementu

                                        Lieta pirkums = new Lieta(); //j�nokop� deta�as

                                        pirkums.x= pircejs.xyz.x;
                                        pirkums.y= pircejs.xyz.y;
                                        pirkums.daudzums=0;


                                        pirkums.nosaukums= pardevejs.inventory.get(preceNrTemp2).nosaukums; //j�kop� manu�li, jo Java ne�auj kop�t objektu (var bet tas b�s tas pats objekts, nevis 2 da��di)
                                        pirkums.zelts= pardevejs.inventory.get(preceNrTemp2).zelts;
                                        pirkums.paika= pardevejs.inventory.get(preceNrTemp2).paika;
                                        pirkums.masa= pardevejs.inventory.get(preceNrTemp2).masa;
                                        pirkums.attack= pardevejs.inventory.get(preceNrTemp2).attack;
                                        pirkums.defence= pardevejs.inventory.get(preceNrTemp2).defence;
                                        pirkums.condition= pardevejs.inventory.get(preceNrTemp2).condition;


                                        preceNrTemp= pircejs.inventory.size();
                                        pircejs.inventory.add(pirkums);
                                    }

                                    AsortiDarbibas.trading(pircejs, zeltsNrPerk,
                                    pardevejs, preceNr, kurss, apjoms);

                                }
                            }
                        }
                    }
                }


            }
        }

        if (savejoSkaitsR1<=2 && savejoSkaitsR2<=5){ //vairo�an�s, bet priv�ti
            vairosanasParbaude(biedrs.chunkXY, biedrs.i,  zeltsSum, paikaSum);
        }

    }

    //errori var b�t augst�k

    private static void vairosanasParbaude(Location location, double zeltsSum, double paikaSum){

        double cenaCilvekam=Cilveku.cenaCilvekam,
                mantojumsCilvekamZelts=Cilveku.mantojumsCilvekamZelts,
                mantojumsCilvekamPaika=Cilveku.mantojumsCilvekamPaika;

        if (zeltsSum>=(cenaCilvekam+mantojumsCilvekamZelts) &&
                paikaSum>=mantojumsCilvekamPaika*2) { //paikas  mantojums x2 lai pa�am ar� paliktu

            Darbibas.vairosanas(location);
        }
    }

    //errori var b�t zem�k

    protected static void updateTradeOrders(Cilveks cilveks) {

        //tirdzniec�bas orderu p�rskats

        double defaultCena=Cilveku.paikaPriceDefault; //cenas apskats no atmi�as v�l nav ieviests
        //double apjomsMin=0.01;


        String nosaukums="";
        double sellLimit, buyLimit; //p�rdod virs sellLimit, p�rdod zem buyLimit
        for(int i=0;i<cilveks.inventory.size();i++) { //apskata katru lietu inventorij�, pie�emot, ka dublik�tu vairs nav

            nosaukums = cilveks.inventory.get(i).nosaukums;
            if(nosaukums.equals("Zelts")) continue; //zeltu nevar p�rdot un nevar ar� nopirkt

            if(nosaukums.equals("Paika")) {
                sellLimit=Cilveku.sellLimitPaika;
                buyLimit=Cilveku.buyLimitPaika;
            } else { //neklasific�tiem objektiem
                sellLimit=Cilveku.sellLimitDefault;
                buyLimit=Cilveku.buyLimitDefault;
            }

            boolean tirgos=false, pirks=false;
            double apjoms=0;

            int[] zeltaNumuri=cilveks.searchInventory("Zelts", false);
            double zeltsTirgum=cilveks.countItemAmount(zeltaNumuri);


            if(cilveks.inventory.get(i).daudzums>sellLimit) { //p�rdo�ana
                apjoms=cilveks.inventory.get(i).daudzums-sellLimit;
                tirgos=true;
                pirks=false;
            } else if(cilveks.inventory.get(i).daudzums<buyLimit && zeltsTirgum>0) { //pirk�ana
                apjoms=Math.min(buyLimit-cilveks.inventory.get(i).daudzums, zeltsTirgum/defaultCena);
                tirgos=true;
                pirks=true;
            }

            int orderNumberTemp=-1;
            for(int j=0;j<cilveks.orderi.size();j++){ //apskata visus orderus - order cleanup
                if(cilveks.orderi.get(j).prece.equals(nosaukums)) {
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

    protected static void komanduMaina(Cilveks cilveks,
                                       double paikaMaina, double zeltsMaina, double anarchyChance, double orderChance) {

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

    protected static void mekletZeltu(Cilveks cilveks, double[] XY, int[] chunkXY, double vKoef) {
        cilveks.darbibas.darbiba="meklet zeltu";
        cilveks.darbibas.vKoef=vKoef;
        cilveks.darbibas.fi = Formulas.lenkaNoteiksana(cilveks.xyz.x, cilveks.xyz.y, XY[0], XY[1], chunkXY);
    }

    protected static void mekletPaiku(Cilveks cilveks, double[] XY, int[] chunkXY, double vKoef) {
        cilveks.darbibas.darbiba="meklet paiku";
        cilveks.darbibas.vKoef=vKoef;
        cilveks.darbibas.fi = Formulas.lenkaNoteiksana(cilveks.xyz.x, cilveks.xyz.y, XY[0], XY[1], chunkXY);
    }

    protected static void atkapties(Cilveks cilveks, double[] XY, int[] chunkXY, double vKoef){
        cilveks.darbibas.darbiba="atkapties";
        cilveks.darbibas.vKoef=vKoef;
        cilveks.darbibas.fi = 180 + Formulas.lenkaNoteiksana(cilveks.xyz.x, cilveks.xyz.y, XY[0], XY[1], chunkXY);
    }

    public static void atputa(Cilveks cilveks) {
        cilveks.darbibas.darbiba="atputa";
        cilveks.darbibas.vKoef=0.4;
        Random r = new Random();
        if (r.nextDouble()<Cilveku.omChangeRate) { //periodiski nomaina kust�bas virzienu
            cilveks.darbibas.fi = cilveks.xyz.fi + cilveks.omega*2*(-0.5+r.nextDouble());
        }
    }

    private static void buvniecibasParbaude(Cilveks cilveks, double zeltsSum){
        double buildingChance=0.1, majasCena=30;

        Random r=new Random();
        if (r.nextDouble()<buildingChance) { //iesp�ja, ka kaut ko tais�s
            if(zeltsSum>=majasCena) {
                System.out.println(cilveks.vards+" zelts: "+(int)zeltsSum+" - gatavs b�v�t m�ju!!!");
                Darbibas.buveMaju();

            }

        }
    }

}