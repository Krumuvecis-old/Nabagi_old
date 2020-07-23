package server.calculations;

import server.calculations.cilveki.CilvekuManager;
import server.calculations.komandas.Komanda;
import server.calculations.cilveki.CilvekuKonstantes;
import server.calculations.lietas.LietuTips;
import server.dataBase.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Initializator {
    public static void main(String versija){
        DataBase.versija = versija;
        LietuTips.generateLietuTipi();

        initializeLaukums();
        initializeKomandas();

        CilvekuManager.pirmieSpeletaji(CilvekuKonstantes.sakumaCilveki, CilvekuKonstantes.randomKomandas); //jauno spçlçtâju ìenerçðana

        System.out.println("CalculationsThread: Initialization complete.");
    }

    private static void initializeLaukums() {
        MapCell.TerrainInfo.generateTerrainPresets();

        int x, y;
        for (x = 0; x < DataBase.mapChunkCountX; x++){
            for (y = 0; y < DataBase.mapChunkCountY; y++){
                List<Integer> xy = new ArrayList<>();
                xy.add(x);
                xy.add(y);
                DataBase.laukums.put(xy, new MapChunk());
            }
        }

        terrainGenerator();
    }

    private static void terrainGenerator(){
        //te ìenerç sâkuma terrain, lai nebûtu visiem vienâda vçrtîba

        List<Integer> chunkXY = new ArrayList<>();
        chunkXY.add(0);
        chunkXY.add(0);

        for (chunkXY.set(0, 0); chunkXY.get(0) < DataBase.mapChunkCountX; chunkXY.set(0, chunkXY.get(0) + 1)){
            for (chunkXY.set(1, 0); chunkXY.get(1) < DataBase.mapChunkCountY; chunkXY.set(1, chunkXY.get(1) + 1)){

                List<Integer> cellXY = new ArrayList<>();
                cellXY.add(0);
                cellXY.add(0);

                for (cellXY.set(0, 0); cellXY.get(0) < DataBase.mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
                    for (cellXY.set(1, 0); cellXY.get(1) < DataBase.mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){
                        MapCell cell = DataBase.laukums.get(chunkXY).mapCells.get(cellXY);

                        Random r = new Random();
                        if (r.nextDouble() < 0.1) cell.terrainType = 3; //akmeòi
                        else if(r.nextDouble() < 0.3) cell.terrainType = 2; //smiltis

                    }
                }
            }
        }
    }

    private static void initializeKomandas(){
        Komanda.jaunaKomanda("Nulle"); //pati pirmâ komanda

        //te var uzlikt arî pârçjo komandu ìenerçðanu, ja "randomKomandas"
    }

}
