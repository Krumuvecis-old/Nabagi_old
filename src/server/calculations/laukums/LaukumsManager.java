package server.calculations.laukums;

import server.calculations.lietas.LietuApskats;
import server.dataBase.DataBase;

import java.util.ArrayList;
import java.util.List;

public class LaukumsManager {

    public static void main(){

        /*
         * �� metode iet cauri visiem chunkiem, t�p�c apskata:
         * karti un reljefu
         * loot generator
         * loot degrad��ana un izdz��ana
         */

        List<Integer> chunkXY = new ArrayList<>();
        chunkXY.add(0);
        chunkXY.add(0);

        for (chunkXY.set(0, 0); chunkXY.get(0) < Laukums.mapChunkCountX; chunkXY.set(0, chunkXY.get(0) + 1))
            for (chunkXY.set(1, 0); chunkXY.get(1) < Laukums.mapChunkCountY; chunkXY.set(1, chunkXY.get(1) + 1))
                chunkActions(chunkXY);
    }

    private static void chunkActions(List<Integer> chunkXY){

        //viss kas saist�ts ar pa zemi jau izm�t�taj�m liet�m - degrad��ana un iz�em�ana
        LietuApskats.main(DataBase.laukums.mapChunks.get(chunkXY));

        //iziet cauri r�ti��m, lai �ener�tu loot un apskat�tu terrain
        checkCells(chunkXY);
    }

    private static void checkCells(List<Integer> chunkXY){
        List<Integer> cellXY = new ArrayList<>();
        cellXY.add(0);
        cellXY.add(0);

        for (cellXY.set(0, 0); cellXY.get(0) < Laukums.mapCellCount; cellXY.set(0, cellXY.get(0) + 1)) {
            for (cellXY.set(1, 0); cellXY.get(1) < Laukums.mapCellCount; cellXY.set(1, cellXY.get(1) + 1)) {
                terrainApskats(chunkXY, cellXY);
                LootGenerator.main(chunkXY, cellXY);
            }
        }
    }

    private static void terrainApskats(List<Integer> chunkXY, List<Integer> cellXY){
        //j�sa�em koordin�tas, lai t�s var�tu izmantot apr��iniem
        MapCell cell = DataBase.laukums.mapChunks.get(chunkXY).mapCells.get(cellXY);
        cell.updateValues();
    }

}
