package server.calculations.cilveki.ai;

public class CloseRange {

//    protected static boolean main(Cilveks cilveks, Location location){
//
//        updateTradeOrders(cilveks); //orderu apskats (ar cleanup)
//
//        boolean nolemts=checkCilveki(cilveks, location);
//
//        // te var pielikt citas funkcijas n�kotnei, piem�ram pavisam tuvo lietu apskatu
//
//
//        return nolemts;
//    }
//
//
//    protected static void updateTradeOrders(Cilveks cilveks) {
//
//        //tirdzniec�bas orderu p�rskats
//
//        double defaultCena= CilvekuKonstantes.paikaPriceDefault; //cenas apskats no atmi�as v�l nav ieviests
//        //double apjomsMin=0.01;
//
//
//        String nosaukums="";
//        double sellLimit, buyLimit; //p�rdod virs sellLimit, p�rdod zem buyLimit
//        for(int i=0;i<cilveks.inventory.size();i++) { //apskata katru lietu inventorij�, pie�emot, ka dublik�tu vairs nav
//
//            nosaukums = cilveks.inventory.get(i).nosaukums;
//            if(nosaukums.equals("Zelts")) continue; //zeltu nevar p�rdot un nevar ar� nopirkt
//
//            if(nosaukums.equals("Paika")) {
//                sellLimit=CilvekuKonstantes.sellLimitPaika;
//                buyLimit=CilvekuKonstantes.buyLimitPaika;
//            } else { //neklasific�tiem objektiem
//                sellLimit=CilvekuKonstantes.sellLimitDefault;
//                buyLimit=CilvekuKonstantes.buyLimitDefault;
//            }
//
//            boolean tirgos=false, pirks=false;
//            double apjoms=0;
//
//            int[] zeltaNumuri=cilveks.searchInventory("Zelts", false);
//            double zeltsTirgum=cilveks.countItemAmount(zeltaNumuri);
//
//
//            if(cilveks.inventory.get(i).daudzums>sellLimit) { //p�rdo�ana
//                apjoms=cilveks.inventory.get(i).daudzums-sellLimit;
//                tirgos=true;
//                pirks=false;
//            } else if(cilveks.inventory.get(i).daudzums<buyLimit && zeltsTirgum>0) { //pirk�ana
//                apjoms=Math.min(buyLimit-cilveks.inventory.get(i).daudzums, zeltsTirgum/defaultCena);
//                tirgos=true;
//                pirks=true;
//            }
//
//            int orderNumberTemp=-1;
//            for(int j=0;j<cilveks.orderi.size();j++){ //apskata visus orderus - order cleanup
//                if(cilveks.orderi.get(j).prece.equals(nosaukums)) {
//                    if (tirgos) { orderNumberTemp=j; } else {
//                        if(cilveks.orderi.get(j).daudzums<=0) { //izdz�� tuk�os orderus
//                            cilveks.orderi.remove(j);
//                            j--;
//                            //continue;
//                        }
//                    }
//                }
//            }
//
//            if (tirgos) {
//                if(orderNumberTemp<0) { //ja ordera v�l nav, uztaisa jaunu
//                    Orderis orderis=new Orderis();
//                    orderis.prece=nosaukums;
//
//                    orderNumberTemp=cilveks.orderi.size();
//                    cilveks.orderi.add(orderis);
//                }
//                cilveks.orderi.get(orderNumberTemp).daudzums=apjoms;
//                cilveks.orderi.get(orderNumberTemp).perk=pirks;
//
//                cilveks.orderi.get(orderNumberTemp).cena=defaultCena;
//            }
//        }
//    }
//
//    private static boolean checkCilveki(Cilveks cilveks, Location location){
//        boolean nolemts=false;
//
//        int chunkViewRange = 1;
//        for(int[] dChunkXY = {-chunkViewRange,-chunkViewRange}; dChunkXY[0]<=chunkViewRange; dChunkXY[0]++){
//            for(dChunkXY[1]=0 ; dChunkXY[0]<=chunkViewRange; dChunkXY[0]++){
//                int[] chunkXY2 = Location.normalizeXY(new int[]{location.chunkXY[0] + dChunkXY[0], location.chunkXY[1] + dChunkXY[1]});
//
//                ArrayList<Cilveks> cilvekiList = CalculationsThread.laukums.get(chunkXY2[0]).get(chunkXY2[1]).cilvekiList;
//
//                for(int i=0; i<cilvekiList.size(); i++){
//                    Location location2 = new Location();
//                    location2.chunkXY=chunkXY2;
//                    location2.i=i;
//                    if (location == location2) continue;
//
//                    Cilveks cilveks2 = Cilveks.getPlayer(location2);
//                    double x2=cilveks2.xyz.x + dChunkXY[0] * KonstantesUniversal.mapChunkW,
//                            y2=cilveks2.xyz.y + dChunkXY[1] * KonstantesUniversal.mapChunkW;
//
//                    double distance = Math.hypot(x2-cilveks.xyz.x, y2-cilveks.xyz.y),
//                            resnums=server.calculations.FizikasKonstantes.resnumaKoefic * cilveks.hpmax,
//                            resnumsJ=server.calculations.FizikasKonstantes.resnumaKoefic * cilveks2.hpmax;
//
//                    if(distance<(resnums+resnumsJ)/2){ //piln�ga sadursme
//                        double fiTemp= Formulas.lenkaNoteiksana(cilveks.xyz.x,cilveks.xyz.y,
//                                cilveks2.xyz.x, cilveks2.xyz.y, dChunkXY);
//                        //cilvekiList.get(i).xyz.fi=fiTemp;
//                        cilveks.xyz.x-=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
//                        cilveks.xyz.y-=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));
//                        cilveks2.xyz.x+=(resnums+resnumsJ-distance)/4*Math.cos(Math.toRadians(fiTemp));
//                        cilveks2.xyz.y+=(resnums+resnumsJ-distance)/4*Math.sin(Math.toRadians(fiTemp));
//                    }
//
//
//                    int tradeDistance = 40, attackDistance=20;
//                    if(distance < tradeDistance && cilveks.komanda.equals(cilveks2.komanda)){
//                        tradingCheck(cilveks, cilveks2);
//                    }
//
//                    if((distance < resnums + attackDistance) &&
//                            (!cilveks.komanda.equals(cilveks2.komanda) ||
//                                    (cilveks.komanda.equals("Anarhija")) ) ){
//                        attack(cilveks, cilveks2);
//                    }
//
//
//                    //te var pievienot v�l funkcijas
//
//                }
//
//            }
//        }
//
//        return nolemts;
//    }
//
//    private static void tradingCheck(Cilveks cilveks1, Cilveks cilveks2){
//        for(int i=0; i<cilveks1.orderi.size(); i++){
//            for (int j=0; j<cilveks2.orderi.size(); j++){
//                if(cilveks1.orderi.get(i).prece==cilveks2.orderi.get(j).prece &&
//                        cilveks1.orderi.get(i).perk!=cilveks2.orderi.get(j).perk){
//                    if (cilveks1.orderi.get(i).perk) trading(cilveks1, i, cilveks2, j);
//                    else trading(cilveks2, j, cilveks1, i);
//                }
//            }
//        }
//
//    }
//
//    private static void trading(Cilveks pircejs, int pircejaOrderaNr,
//                               Cilveks pardevejs, int pardevejaOrderaNr){
//
////        //j�sal�dzina tradeorders
////        double apjoms = Math.max(0,
////                Math.min(pircejs.orderi.get(pircejaOrderaNr).daudzums,
////                        pardevejs.orderi.get(pardevejaOrderaNr).daudzums));
////
////        //j�at�em nauda pirc�jam (inventory cleanup?)
////        //j�pieliek produkts pirc�jam
////        //j�pieliek nauda p�rdev�jam
////        //j�no�em produkkts p�rdev�jam (inventory cleanup?)
////
////        Cilveks pardevejs = Cilveks.getPlayer(pardodBiedrs.chunkXY, pardodBiedrs.i),
////                pircejs = Cilveks.getPlayer(perkBiedrs.chunkXY, perkBiedrs.i);
////
////        //apskata p�rdev�ju
////        int zeltsNrTemp=-1, preceNrTemp=-1;
////
////        for(int w = 0; w< pardevejs.inventory.size(); w++) {
////            if (preceNrTemp>=0 && zeltsNrTemp>=0) break;
////            if (pardevejs.inventory.get(w).nosaukums==preceTirgo) {
////                pardevejs.inventory.get(w).daudzums-=apjomsTirgo;
////                preceNrTemp=w;
////            }
////            if (pardevejs.inventory.get(w).nosaukums=="Zelts") {
////                zeltsNrTemp=w;
////            }
////        }
////
////        if (zeltsNrTemp<0) { //ja s�kum� p�rdev�jam nav naudas visp�r, uztaisa tuk�u elementu
////            Lieta samaksa = new Lieta();
////            samaksa.x= pardevejs.xyz.x;
////            samaksa.y= pardevejs.xyz.y;
////            samaksa.nosaukums="Zelts";
////            samaksa.daudzums=0;
////            samaksa.zelts=1;
////            samaksa.paika=0;
////            samaksa.masa=1;
////            samaksa.attack=0;
////            samaksa.defence=0;
////            samaksa.condition=1;
////
////            zeltsNrTemp= pardevejs.inventory.size();
////            pardevejs.inventory.add(samaksa);
////        }
////
////        //apskata pirc�ju
////        int preceNrTemp=-1;
////        for(int w = 0; w< pircejs.inventory.size(); w++) {
////            if (pircejs.inventory.get(w).nosaukums=="Zelts") {
////                pircejs.inventory.get(w).daudzums-=apjomsTirgo*cenaTirgo;
////            }
////            if (pircejs.inventory.get(w).nosaukums==preceTirgo) {
////                preceNrTemp=w;
////            }
////        }
////
////        if (preceNrTemp<0) { //ja s�kum� pirc�jam visp�r nav t�das preces, uztaisa tuk�u elementu
////
////            Lieta pirkums = new Lieta(); //j�nokop� deta�as
////
////            pirkums.x= pircejs.xyz.x;
////            pirkums.y= pircejs.xyz.y;
////            pirkums.daudzums=0;
////
////
////            pirkums.nosaukums= pardevejs.inventory.get(preceNrTemp2).nosaukums; //j�kop� manu�li, jo Java ne�auj kop�t objektu (var bet tas b�s tas pats objekts, nevis 2 da��di)
////            pirkums.zelts= pardevejs.inventory.get(preceNrTemp2).zelts;
////            pirkums.paika= pardevejs.inventory.get(preceNrTemp2).paika;
////            pirkums.masa= pardevejs.inventory.get(preceNrTemp2).masa;
////            pirkums.attack= pardevejs.inventory.get(preceNrTemp2).attack;
////            pirkums.defence= pardevejs.inventory.get(preceNrTemp2).defence;
////            pirkums.condition= pardevejs.inventory.get(preceNrTemp2).condition;
////
////
////            preceNrTemp= pircejs.inventory.size();
////            pircejs.inventory.add(pirkums);
////        }
////
////
////        pardevejs.inventory.get(zeltsNrTemp).daudzums+=apjomsTirgo*cenaTirgo; //pieliek naudu p�rdev�jam
////        //j�no�em produkts p�rdev�jam!
////        pardevejs.orderi.get(orderisPardodNr).daudzums-=apjomsTirgo; //samazina p�rdev�ja orderi
////
////        pircejs.inventory.get(preceNrTemp).daudzums+=apjomsTirgo;
////        pircejs.orderi.get(orderisPerkNr).daudzums-=apjomsTirgo; //samazina orderi
////
////        //reseto temporary lielumus, jo tirdznieciba jau notikusi
////
////        perkBiedrs.i=-1;
////        pardodBiedrs.i=-1;
////        perkBiedrs.chunkXY=new int[]{0,0};
////        pardodBiedrs.chunkXY=new int[]{0,0};
////
////        apjomsTirgo=0;
////        jTirgoXY[0]=0;
////        jTirgoXY[1]=0;
////
////        //v�rlreiz saskaita paiku un zeltu, ar� v�lreiz izdz�� tuk�os
////        pardevejs.searchInventory("Zelts", false);
////        pardevejs.orderuCleanup();
////
////        String preceName = pircejs.orderi.get(pircejaOrderaNr).prece;
////        pircejs.searchInventory(preceName, false);
////        pardevejs.orderuCleanup();
//
//    }
//
//    private static void attack(Cilveks cilveks, Cilveks cilveks2){
//
//        //melee combat
//        cilveks2.trauma(cilveks.stiprums, 1);
//    }

}
