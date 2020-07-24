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

        LaukumsInitializator.initializeLaukums();
        initializeKomandas();

        CilvekuManager.pirmieSpeletaji(CilvekuKonstantes.sakumaCilveki, CilvekuKonstantes.randomKomandas); //jauno spçlçtâju ìenerçðana

        System.out.println("CalculationsThread: Initialization complete.");
    }

    private static class LaukumsInitializator{

        public static void initializeLaukums() {
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
            generateMountains();
            generateDeserts();
            generateRemainingTerrain();
        }

        private static Random r = new Random();

        private static void generateMountains(){
            int mountainCount = (int)(0.2 * DataBase.mapChunkCountX * DataBase.mapChunkCountY);

            for(int i = 0; i < mountainCount; i++){

                List<Integer> chunkXY = new ArrayList<>();
                chunkXY.add(r.nextInt(DataBase.mapChunkCountX));
                chunkXY.add(r.nextInt(DataBase.mapChunkCountY));


                List<Integer> cellXY = new ArrayList<>();
                cellXY.add(0);
                cellXY.add(0);

                for (cellXY.set(0, 0); cellXY.get(0) < DataBase.mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
                    for (cellXY.set(1, 0); cellXY.get(1) < DataBase.mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){
                        MapCell cell = DataBase.laukums.get(chunkXY).mapCells.get(cellXY);

                        if (r.nextDouble() < 0.8) cell.terrainType = 3; //akmeòi

                    }
                }
            }

        }

        private static void generateDeserts(){
            int desertCount = (int)(0.4 * DataBase.mapChunkCountX * DataBase.mapChunkCountY);

            for(int i = 0; i < desertCount; i++){
                List<Integer> chunkXY = new ArrayList<>();
                chunkXY.add(r.nextInt(DataBase.mapChunkCountX));
                chunkXY.add(r.nextInt(DataBase.mapChunkCountY));


                List<Integer> cellXY = new ArrayList<>();
                cellXY.add(0);
                cellXY.add(0);

                for (cellXY.set(0, 0); cellXY.get(0) < DataBase.mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
                    for (cellXY.set(1, 0); cellXY.get(1) < DataBase.mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){
                        MapCell cell = DataBase.laukums.get(chunkXY).mapCells.get(cellXY);

                        if (cell.terrainType != 3) cell.terrainType = 2; //smiltis

                    }
                }
            }
        }

        private static void generateRemainingTerrain(){
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

                            if(cell.terrainType == 0){ //tikai neizmainîtâs rûtiòas
                                if (r.nextDouble() < 0.1) cell.terrainType = 1; //zâle
                            }

                        }
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
