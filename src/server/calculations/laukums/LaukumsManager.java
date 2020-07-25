package server.calculations.laukums;

import server.dataBase.DataBase;

import java.util.ArrayList;
import java.util.List;

public class LaukumsManager {

    public static void main(){

        //iziet cauri visiem chunkiem
        List<Integer> chunkXY = new ArrayList<>();
        chunkXY.add(0);
        chunkXY.add(0);

        for (chunkXY.set(0, 0); chunkXY.get(0) < Laukums.mapChunkCountX; chunkXY.set(0, chunkXY.get(0) + 1))
            for (chunkXY.set(1, 0); chunkXY.get(1) < Laukums.mapChunkCountY; chunkXY.set(1, chunkXY.get(1) + 1))
                chunkActions(chunkXY);
    }

    private static void chunkActions(List<Integer> chunkXY){

        //te, ja vajag, var ielikt chunk actions

        //iziet cauri visâm rûtiòâm
        List<Integer> cellXY = new ArrayList<>();
        cellXY.add(0);
        cellXY.add(0);

        for (cellXY.set(0, 0); cellXY.get(0) < Laukums.mapCellCount; cellXY.set(0, cellXY.get(0) + 1))
            for (cellXY.set(1, 0); cellXY.get(1) < Laukums.mapCellCount; cellXY.set(1, cellXY.get(1) + 1))
                cellActions(chunkXY, cellXY);
    }

    private static void cellActions(List<Integer> chunkXY, List<Integer> cellXY){
        terrainApskats(chunkXY, cellXY);
        LootGenerator.main(chunkXY, cellXY);
    }

    private static void terrainApskats(List<Integer> chunkXY, List<Integer> cellXY){
        //jâsaòem koordinâtas, lai tâs varçtu izmantot aprçíiniem
        MapCell cell = DataBase.laukums.mapChunks.get(chunkXY).mapCells.get(cellXY);
        cell.updateValues();
    }

}
