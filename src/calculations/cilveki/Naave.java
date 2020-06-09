package calculations.cilveki;

import calculations.KonstantesUniversal;
import calculations.Location;
import calculations.Main;
import calculations.lietas.Lieta;

import java.util.Random;

class Naave {

    protected static boolean naavesPaarbaude(Location location) {
        if(Cilveks.getPlayer(location).hp > 0) return false;
        else {
            naave(location);
            return true;
        }
    }

    private static void naave(Location location){
        dropLoot(location);
        Main.laukums.get(location.chunkXY[0]).get(location.chunkXY[1]).cilvekiList.remove(location.i);
    }

    private static void dropLoot(Location location){
        Cilveks cilveks = Cilveks.getPlayer(location);
        double lootDropDistance=10;
        for(int i=0; i<cilveks.inventory.size(); i++) {
            Lieta lieta = cilveks.inventory.get(i);

            Random r = new Random();

            //pagaidâm lietu nomet tikai savâ chunkâ ----- !!!!!
            lieta.x = Math.max(0, Math.min(KonstantesUniversal.mapChunkW,
                    cilveks.xyz.x + lootDropDistance*(r.nextDouble()-0.5)*2 ));
            lieta.y = Math.max(0, Math.min(KonstantesUniversal.mapChunkW,
                    cilveks.xyz.y + lootDropDistance*(r.nextDouble()-0.5)*2 ));

            lieta.drop(location.chunkXY);

            cilveks.inventory.remove(i);
            i--;
        }

    }


}
