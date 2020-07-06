package calculations.cilveki;

import calculations.Main;
import calculations.konstantes.Cilveku;

public class DefaultDarbibas {

    protected static void main(){
        for(String vards : Main.cilvekuList.keySet()){
            Cilveks cilveks = Main.cilvekuList.get(vards);

            //esanaNoInventory(cilveks);

            //te var ielikt ar� citas darb�bas

            //healingAndHunger(cilveks);

            if (Naave.naavesPaarbaude(vards)) continue; //p�rbaud� izdz��, ja vajag

            //ide�lai kust�bai pietr�kst pa�trin�jums
            cilveks.darbibas.kustibasParametri(cilveks);
            Kustiba.main(vards);
        }
    }

    private static void esanaNoInventory(Cilveks cilveks) {

        if (cilveks.paika<=cilveks.paikaMin) { //��ana no inventory

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

        if (cilveks.paika>=cilveks.paikaMin) { //ja pietiek p�rtika, vese�ojas
            if(cilveks.hp<cilveks.hpmax) cilveks.hp+=dHpRegen;
        } else if(cilveks.paika<=0) { //ja p�rtika nav - zaud� Hp
            if(cilveks.hp>0) cilveks.hp-=dHpHungry;
        }

        if(cilveks.hp<0) cilveks.hp=0; //nol�dzina pie 0
        if(cilveks.hp>cilveks.hpmax) cilveks.hp=cilveks.hpmax; //nol�dzina pie hpmax

        //konstanti at�em�s paika
        if(cilveks.paika>0) cilveks.paika-=paikaD;
        if(cilveks.paika<0) cilveks.paika=0;
    }
}
