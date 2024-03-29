package server.calculations.lietas;

import server.calculations.FizikasKonstantes;
import server.dataBase.DataBase;

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
        //�ener� katru tipu atsevi��i

        double genRate = LietuTips.lietuTipi.get(tips).genKoef * FizikasKonstantes.overallGenRate;
        Random r=new Random();

        int reizes = (int)Math.floor(genRate);
        if (r.nextDouble() < (genRate - reizes)) reizes++;

        for (int i=0; i<reizes; i++) {
            List<Integer> chunkXY = new ArrayList<>();
            chunkXY.add(r.nextInt(DataBase.mapChunkCountX)); //x
            chunkXY.add(r.nextInt(DataBase.mapChunkCountY)); //y

            createLoot(tips, chunkXY);
        }

    }

    private static void createLoot(String tips, List<Integer> chunkXY) { //uztaisa un iemet laukum� vienu lietu
        Random r = new Random();
        double x = r.nextDouble() * DataBase.mapChunkW,
                y = r.nextDouble() * DataBase.mapChunkW;

        double minimums = LietuTips.lietuTipi.get(tips).genMin,
                maksimums = LietuTips.lietuTipi.get(tips).genMax,
                daudzums = minimums + (maksimums-minimums) * r.nextDouble();

        Lieta lieta = new Lieta(tips, daudzums, x, y); //uztaisa lietu
        lieta.drop(chunkXY); //iemet laukum�
    }

}
