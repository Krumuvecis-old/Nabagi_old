package server.calculations.lietas;

import server.calculations.FizikasKonstantes;
import server.calculations.MapCell;
import server.dataBase.DataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LootGenerator {

    static void main(){

        //iziet cauri visiem chunkiem

        List<Integer> chunkXY = new ArrayList<>();
        chunkXY.add(0);
        chunkXY.add(0);

        for (chunkXY.set(0, 0); chunkXY.get(0) < DataBase.mapChunkCountX; chunkXY.set(0, chunkXY.get(0) + 1)){
            for (chunkXY.set(1, 0); chunkXY.get(1) < DataBase.mapChunkCountY; chunkXY.set(1, chunkXY.get(1) + 1)){

                //iziet cauri visâm rûtiòâm

                List<Integer> cellXY = new ArrayList<>();
                cellXY.add(0);
                cellXY.add(0);

                for (cellXY.set(0, 0); cellXY.get(0) < DataBase.mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
                    for (cellXY.set(1, 0); cellXY.get(1) < DataBase.mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){

                        MapCell cell = DataBase.laukums.get(chunkXY).mapCells.get(cellXY);

                        checkItemTypes(
                                chunkXY,
                                new int[]{
                                        cellXY.get(0) * DataBase.mapCellW,
                                        cellXY.get(1) * DataBase.mapCellW},
                                cell);
                    }
                }
            }
        }
    }

    private static void checkItemTypes(List<Integer> chunkXY, int[] cellLoc, MapCell cell){
        HashMap<String, MapCell.TerrainInfo.ItemGenInfo> genInfo = MapCell.terrainPresets.get(cell.terrainType).lootGeneratorInfo;
        for(String tips : genInfo.keySet()){
            checkItemGeneration(chunkXY, cellLoc, genInfo.get(tips), tips);
        }
    }

    private static void checkItemGeneration(List<Integer> chunkXY, int[] cellLoc,
                                            MapCell.TerrainInfo.ItemGenInfo genRates, String tips){
        //ìenerç katru tipu atseviðíi

        double genRate = genRates.genKoef * FizikasKonstantes.overallGenRate;
        Random r = new Random();

        if (r.nextDouble() < genRate) {
            double[] xy = new double[]{
                    cellLoc[0] + r.nextDouble() * DataBase.mapCellW,
                    cellLoc[1] + r.nextDouble() * DataBase.mapCellW};

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
