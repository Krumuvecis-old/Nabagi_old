package calculations.lietas;

import calculations.KonstantesUniversal;
import calculations.Main;

import java.util.Random;

public class LootGenerator {

    protected static void main(){
        for(int i=0; i<KonstantesUniversal.defaultLietas.size(); i++){
            checkItemGeneration(i);
        }
    }

    private static void checkItemGeneration(int tips){

        double genRate = KonstantesUniversal.defaultLietas.get(tips).genKoef * KonstantesUniversal.overallGenRate;
        Random r=new Random();

        for (int[] chunkXY = {0,0}; chunkXY[0]< Main.laukums.size(); chunkXY[0]++){
            for( ; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++){

                int reizes = (int)Math.floor(genRate);
                if ((r.nextDouble() * (genRate - reizes)) < 1) reizes++;

                for (int i=0; i<reizes; i++) {
                    createLoot(tips, chunkXY);
                }

            }
        }
    }

    private static void createLoot(int tips, int[] chunkXY) {

        Random r=new Random();
        double x = r.nextDouble() * KonstantesUniversal.mapChunkW,
                y = r.nextDouble() * KonstantesUniversal.mapChunkW;

        double minimums = KonstantesUniversal.defaultLietas.get(tips).genMin,
                maksimums = KonstantesUniversal.defaultLietas.get(tips).genMax,
                daudzums = minimums + (maksimums-minimums)*r.nextDouble();

        Lieta lieta = Lieta.newLieta(tips, daudzums, x, y);
        lieta.drop(chunkXY);
    }

}
