package calculations.cilveki.ai;

import calculations.KonstantesUniversal;
import calculations.Location;
import calculations.Main;
import calculations.cilveki.Cilveks;
import calculations.cilveki.Orderis;
import calculations.konstantes.Cilveku;

import java.util.ArrayList;

public class CloseRange {

    protected static boolean main(Cilveks cilveks, Location location){

        updateTradeOrders(cilveks); //orderu apskats (ar cleanup)

        boolean nolemts=checkCilveki(cilveks, location);

        // te var pielikt citas funkcijas nâkotnei, piemçram pavisam tuvo lietu apskatu


        return nolemts;
    }


    protected static void updateTradeOrders(Cilveks cilveks) {

        //tirdzniecîbas orderu pârskats

        double defaultCena= Cilveku.paikaPriceDefault; //cenas apskats no atmiòas vçl nav ieviests
        //double apjomsMin=0.01;


        String nosaukums="";
        double sellLimit, buyLimit; //pârdod virs sellLimit, pârdod zem buyLimit
        for(int i=0;i<cilveks.inventory.size();i++) { //apskata katru lietu inventorijâ, pieòemot, ka dublikâtu vairs nav

            nosaukums = cilveks.inventory.get(i).nosaukums;
            if(nosaukums.equals("Zelts")) continue; //zeltu nevar pârdot un nevar arî nopirkt

            if(nosaukums.equals("Paika")) {
                sellLimit=Cilveku.sellLimitPaika;
                buyLimit=Cilveku.buyLimitPaika;
            } else { //neklasificçtiem objektiem
                sellLimit=Cilveku.sellLimitDefault;
                buyLimit=Cilveku.buyLimitDefault;
            }

            boolean tirgos=false, pirks=false;
            double apjoms=0;

            int[] zeltaNumuri=cilveks.searchInventory("Zelts", false);
            double zeltsTirgum=cilveks.countItemAmount(zeltaNumuri);


            if(cilveks.inventory.get(i).daudzums>sellLimit) { //pârdoðana
                apjoms=cilveks.inventory.get(i).daudzums-sellLimit;
                tirgos=true;
                pirks=false;
            } else if(cilveks.inventory.get(i).daudzums<buyLimit && zeltsTirgum>0) { //pirkðana
                apjoms=Math.min(buyLimit-cilveks.inventory.get(i).daudzums, zeltsTirgum/defaultCena);
                tirgos=true;
                pirks=true;
            }

            int orderNumberTemp=-1;
            for(int j=0;j<cilveks.orderi.size();j++){ //apskata visus orderus - order cleanup
                if(cilveks.orderi.get(j).prece.equals(nosaukums)) {
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

    private static boolean checkCilveki(Cilveks cilveks, Location location){
        boolean nolemts=false;

        int chunkViewRange = 1;
        for(int[] dChunkXY = {-chunkViewRange,-chunkViewRange}; dChunkXY[0]<=chunkViewRange; dChunkXY[0]++){
            for( ; dChunkXY[0]<=chunkViewRange; dChunkXY[0]++){
                int[] chunkXY2 = Location.normalizeXY(new int[]{location.chunkXY[0] + dChunkXY[0], location.chunkXY[1] + dChunkXY[1]});

                ArrayList<Cilveks> cilvekiList = Main.laukums.get(chunkXY2[0]).get(chunkXY2[1]).cilvekiList;

                for(int i=0; i<cilvekiList.size(); i++){
                    Location location2 = new Location();
                    location2.chunkXY=chunkXY2;
                    location2.i=i;
                    if (location == location2) continue;

                    Cilveks cilveks2 = Cilveks.getPlayer(location2);
                    double x2=cilveks2.xyz.x + dChunkXY[0] * KonstantesUniversal.mapChunkW,
                            y2=cilveks2.xyz.y + dChunkXY[1] * KonstantesUniversal.mapChunkW;

                    double distance = Math.hypot(x2-cilveks.xyz.x, y2-cilveks.xyz.y);


                    int tradeDistance = 40, attackDistance=40;
                    if(distance < tradeDistance && cilveks.komanda.equals(cilveks2.komanda)){
                        tradingCheck(cilveks, cilveks2);
                    }

                    if(distance < attackDistance && !cilveks.komanda.equals(cilveks2.komanda)){
                        attack(cilveks, cilveks2);
                    }

                    //te var pievienot vçl funkcijas

                }

            }
        }

        return nolemts;
    }

    private static void tradingCheck(Cilveks cilveks1, Cilveks cilveks2){
        for(int i=0; i<cilveks1.orderi.size(); i++){
            for (int j=0; j<cilveks2.orderi.size(); j++){
                if(cilveks1.orderi.get(i).prece==cilveks2.orderi.get(j).prece &&
                        cilveks1.orderi.get(i).perk!=cilveks2.orderi.get(j).perk){
                    if (cilveks1.orderi.get(i).perk) trading(cilveks1, i, cilveks2, j);
                    else trading(cilveks2, j, cilveks1, i);
                }
            }
        }

    }



    private static void trading(Cilveks pircejs, int pircejaOrderaNr,
                               Cilveks pardevejs, int pardevejaOrderaNr){

        //jâsalîdzina tradeorders
        //jâatòem nauda pircçjam (inventory cleanup?)
        //jâpieliek produkts pircçjam
        //jâpieliek nauda pârdevçjam
        //jânoòem produkkts pârdevçjam (inventory cleanup?)

        //updatetradeorders?


        pardevejs.inventory.get(zeltsNrTemp).daudzums+=apjomsTirgo*cenaTirgo; //pieliek naudu pârdevçjam
        //jânoòem produkts pârdevçjam!
        pardevejs.orderi.get(orderisPardodNr).daudzums-=apjomsTirgo; //samazina pârdevçja orderi

        pircejs.inventory.get(preceNrTemp).daudzums+=apjomsTirgo;
        pircejs.orderi.get(orderisPerkNr).daudzums-=apjomsTirgo; //samazina orderi

        //reseto temporary lielumus, jo tirdznieciba jau notikusi

        perkBiedrs.i=-1;
        pardodBiedrs.i=-1;
        perkBiedrs.chunkXY=new int[]{0,0};
        pardodBiedrs.chunkXY=new int[]{0,0};

        apjomsTirgo=0;
        jTirgoXY[0]=0;
        jTirgoXY[1]=0;

        //vçrlreiz saskaita paiku un zeltu, arî vçlreiz izdzçð tukðos
        zeltsNr=pardevejs.countInventory("Zelts", true);
        zeltsSum=0;
        if (zeltsNr>=0) zeltsSum=pardevejs.inventory.get(zeltsNr).daudzums;

        String preceName = pircejs.inventory.get(paikaNr).nosaukums;
        paikaNr=pircejs.countInventory(preceName, true);
        paikaSum=0;
        if (paikaNr>=0) paikaSum=pircejs.inventory.get(paikaNr).daudzums;

    }

    private static void attack(Cilveks cilveks, Cilveks cilveks2){

        //melee combat
        cilveks2.trauma(cilveks.stiprums, 1);
    }

}
