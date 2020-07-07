package calculations.cilveki;

import calculations.KonstantesUniversal;
import calculations.Main;
import calculations.lietas.Lieta;

import java.util.Random;

class Naave {

    protected static boolean naavesPaarbaude(String vards) {
        if(Main.cilvekuList.get(vards).hp > 0) return false;
        else {
            naave(vards);
            return true;
        }
    }

    private static void naave(String vards){
        dropLoot(vards);
        Main.laukums.get(Main.cilvekuList.get(vards).xyz.chunkXY).cilvekiList.remove(vards);
        Main.cilvekuList.remove(vards);
    }

    private static void dropLoot(String vards){
        Cilveks cilveks = Main.cilvekuList.get(vards);
        double lootDropDistance = 15;

        for(int i=0; i<cilveks.inventory.size(); i++) {
            Lieta lieta = cilveks.inventory.get(i);

            Random r = new Random();

            //
            //pagaidâm lietu nomet tikai savâ chunkâ ----- !!!!!
            //

            lieta.x = Math.max(0, Math.min(KonstantesUniversal.mapChunkW,
                    cilveks.xyz.x + lootDropDistance*(r.nextDouble()-0.5)*2 ));
            lieta.y = Math.max(0, Math.min(KonstantesUniversal.mapChunkW,
                    cilveks.xyz.y + lootDropDistance*(r.nextDouble()-0.5)*2 ));

            lieta.drop(cilveks.xyz.chunkXY);

            cilveks.inventory.remove(i);
            i--;
        }

    }


}
