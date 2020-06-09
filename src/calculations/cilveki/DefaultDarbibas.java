package calculations.cilveki;

import calculations.Location;
import calculations.Main;
import calculations.konstantes.Cilveku;

import java.util.ArrayList;

public class DefaultDarbibas {

    protected static void main(){

        for(int[] chunkXY = {0,0}; chunkXY[0]< Main.laukums.size(); chunkXY[0]++) {
            for (chunkXY[1]=0; chunkXY[1] < Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++) {
                ArrayList<Cilveks> cilvekiList = Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList;
                for (int i = 0; i < cilvekiList.size(); i++) {

                    Location location = new Location();
                    location.chunkXY = chunkXY;
                    location.i = i;

                    Cilveks cilveks = Cilveks.getPlayer(location);
                    /*esanaNoInventory(cilveks);

                    //te var ielikt arî citas darbîbas

                    healingAndHunger(cilveks);

                    if (Naave.naavesPaarbaude(location)) {
                        //pârbaudç izdzçð, ja vajag
                        i--;
                        continue;
                    }*/

                    //ideâlai kustîbai pietrûkst paâtrinâjums
                    cilveks.darbibas.kustibasParametri(cilveks);
                    Kustiba.main(cilveks, location);

                }
            }
        }

    }

    private static void esanaNoInventory(Cilveks cilveks) {

        if (cilveks.paika<=cilveks.paikaMin) { //çðana no inventory

            int[] paikaNumuri = cilveks.searchInventory("Paika", false);

            if (paikaNumuri.length>0) {

                double apests=Math.min(Cilveku.esanasDaudzums, cilveks.inventory.get(paikaNumuri[0]).daudzums);
                cilveks.inventory.get(paikaNumuri[0]).daudzums-=apests;

                cilveks.paika = Math.min(cilveks.paikaMax,
                        cilveks.paika + apests * (cilveks.paikaMax / Cilveku.esanasDaudzums));

            } else cilveks.navKoEst = true;
        }
    }



    private static void healingAndHunger(Cilveks cilveks) {
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
}
