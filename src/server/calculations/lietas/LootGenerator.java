package server.calculations.lietas;

import server.calculations.KonstantesUniversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootGenerator {

    protected static void main(){ //iziet cauri katram lietu tipam
        for (String i : LietuTips.lietuTipi.keySet()) {
            checkItemGeneration(i);
        }
    }

    private static void checkItemGeneration(String tips){
        //ìenerç katru tipu atseviðíi

        double genRate = LietuTips.lietuTipi.get(tips).genKoef * KonstantesUniversal.overallGenRate;
        Random r=new Random();

        int reizes = (int)Math.floor(genRate);
        if (r.nextDouble() < (genRate - reizes)) reizes++;

        for (int i=0; i<reizes; i++) {
            List<Integer> chunkXY = new ArrayList<>();
            chunkXY.add(r.nextInt(KonstantesUniversal.mapChunkCountX)); //x
            chunkXY.add(r.nextInt(KonstantesUniversal.mapChunkCountY)); //y

            createLoot(tips, chunkXY);
        }

    }

    private static void createLoot(String tips, List<Integer> chunkXY) { //uztaisa un iemet laukumâ vienu lietu
        Random r = new Random();
        double x = r.nextDouble() * KonstantesUniversal.mapChunkW,
                y = r.nextDouble() * KonstantesUniversal.mapChunkW;

        double minimums = LietuTips.lietuTipi.get(tips).genMin,
                maksimums = LietuTips.lietuTipi.get(tips).genMax,
                daudzums = minimums + (maksimums-minimums) * r.nextDouble();

        Lieta lieta = new Lieta(tips, daudzums, x, y); //uztaisa lietu
        lieta.drop(chunkXY); //iemet laukumâ
    }

}
