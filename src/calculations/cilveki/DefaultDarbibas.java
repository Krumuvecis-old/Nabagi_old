package calculations.cilveki;

import calculations.Main;
import calculations.konstantes.Cilveku;

public class DefaultDarbibas {

    protected static void main(){
        for(String vards : Main.cilvekuList.keySet()){
            Cilveks cilveks = Main.cilvekuList.get(vards);

            //esanaNoInventory(cilveks);

            //te var ielikt arî citas darbîbas

            //healingAndHunger(cilveks);

            if (Naave.naavesPaarbaude(vards)) continue; //pârbaudç izdzçð, ja vajag

            //ideâlai kustîbai pietrûkst paâtrinâjums
            cilveks.darbibas.kustibasParametri(cilveks);
            Kustiba.main(vards);
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
