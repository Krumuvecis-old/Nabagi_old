package server.calculations.laukums;

import server.calculations.FizikasKonstantes;
import server.calculations.lietas.Lieta;
import server.dataBase.DataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LootGenerator {

    static void main(List<Integer> chunkXY, List<Integer> cellXY){

        HashMap<String, MapCell.TerrainInfo.ItemGenInfo> genInfo =
                MapCell.terrainPresets.get(DataBase.laukums.mapChunks.get(chunkXY).mapCells.get(cellXY).terrainType).lootGeneratorInfo;

        for(String tips : genInfo.keySet()){ //pârbauda katru lietu tipu
            checkItemGeneration(
                    chunkXY, new int[]{
                            cellXY.get(0) * Laukums.mapCellW,
                            cellXY.get(1) * Laukums.mapCellW},
                    genInfo.get(tips), tips);
        }
    }

    private static void checkItemGeneration(List<Integer> chunkXY, int[] cellLoc,
                                            MapCell.TerrainInfo.ItemGenInfo genRates, String tips){

        double genRate = genRates.genKoef * FizikasKonstantes.overallGenRate;
        Random r = new Random();

        if (r.nextDouble() < genRate) {
            double[] xy = new double[]{
                    cellLoc[0] + r.nextDouble() * Laukums.mapCellW,
                    cellLoc[1] + r.nextDouble() * Laukums.mapCellW};

            double minimums = genRates.genMin, maksimums = genRates.genMax,
                    daudzums = minimums + (maksimums - minimums) * r.nextDouble();

            createLoot(chunkXY, xy, tips, daudzums);
        }
    }

    private static void createLoot(List<Integer> chunkXY, double[] xy, String tips, double daudzums) {
        Lieta lieta = new Lieta(tips, daudzums, xy[0], xy[1]); //uztaisa lietu
        lieta.drop(chunkXY); //iemet laukumâ
    }

}
