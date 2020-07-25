package server.calculations.laukums;

import server.dataBase.DataBase;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Laukums {

    public Map<List<Integer>, MapChunk> mapChunks;

    public static int mapChunkCountX = 21, mapChunkCountY = 13,
            mapCellCount = 15, mapCellW = 20,
            mapChunkW = mapCellW * mapCellCount,
            laukumaAugstumsSum = mapChunkW * mapChunkCountY,
            laukumaPlatumsSum = mapChunkW * mapChunkCountX;

    public static Color laukumaKrasa = new Color(70,120,70), //zaïpelçks
            malasKrasa = Color.darkGray;

    public Laukums(){
        mapChunks = new HashMap<>();

        int x, y;
        for (x = 0; x < mapChunkCountX; x++){
            for (y = 0; y < mapChunkCountY; y++){
                List<Integer> xy = new ArrayList<>();
                xy.add(x);
                xy.add(y);
                mapChunks.put(xy, new MapChunk());
            }
        }

        //te varçtu ìenerçt laukumam specifisku terrain
    }

    public static class LaukumsInitializator{

        public static void initializeLaukums() {
            MapCell.TerrainInfo.generateTerrainPresets();

            DataBase.laukums = new Laukums();

            terrainGenerator();

            System.out.println("CalculationsThread: Terrain generated - laukums initialized.");
        }

        private static void terrainGenerator(){
            generateMountains();
            generateDeserts();
            generateRemainingTerrain();
        }

        private static Random r = new Random();

        private static void generateMountains(){
            int mountainCount = (int)(0.2 * mapChunkCountX * mapChunkCountY);

            for(int i = 0; i < mountainCount; i++){

                List<Integer> chunkXY = new ArrayList<>();
                chunkXY.add(r.nextInt(mapChunkCountX));
                chunkXY.add(r.nextInt(mapChunkCountY));


                List<Integer> cellXY = new ArrayList<>();
                cellXY.add(0);
                cellXY.add(0);

                for (cellXY.set(0, 0); cellXY.get(0) < mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
                    for (cellXY.set(1, 0); cellXY.get(1) < mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){
                        MapCell cell = DataBase.laukums.mapChunks.get(chunkXY).mapCells.get(cellXY);

                        if (r.nextDouble() < 0.8) cell.terrainType = 3; //akmeòi

                    }
                }
            }

        }

        private static void generateDeserts(){
            int desertCount = (int)(0.4 * mapChunkCountX * mapChunkCountY);

            for(int i = 0; i < desertCount; i++){
                List<Integer> chunkXY = new ArrayList<>();
                chunkXY.add(r.nextInt(mapChunkCountX));
                chunkXY.add(r.nextInt(mapChunkCountY));


                List<Integer> cellXY = new ArrayList<>();
                cellXY.add(0);
                cellXY.add(0);

                for (cellXY.set(0, 0); cellXY.get(0) < mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
                    for (cellXY.set(1, 0); cellXY.get(1) < mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){
                        MapCell cell = DataBase.laukums.mapChunks.get(chunkXY).mapCells.get(cellXY);

                        if (cell.terrainType != 3) cell.terrainType = 2; //smiltis

                    }
                }
            }
        }

        private static void generateRemainingTerrain(){
            List<Integer> chunkXY = new ArrayList<>();
            chunkXY.add(0);
            chunkXY.add(0);

            for (chunkXY.set(0, 0); chunkXY.get(0) < mapChunkCountX; chunkXY.set(0, chunkXY.get(0) + 1)){
                for (chunkXY.set(1, 0); chunkXY.get(1) < mapChunkCountY; chunkXY.set(1, chunkXY.get(1) + 1)){

                    List<Integer> cellXY = new ArrayList<>();
                    cellXY.add(0);
                    cellXY.add(0);

                    for (cellXY.set(0, 0); cellXY.get(0) < mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
                        for (cellXY.set(1, 0); cellXY.get(1) < mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){
                            MapCell cell = DataBase.laukums.mapChunks.get(chunkXY).mapCells.get(cellXY);

                            if(cell.terrainType == 0){ //tikai neizmainîtâs rûtiòas
                                if (r.nextDouble() < 0.1) cell.terrainType = 1; //zâle
                            }

                        }
                    }
                }
            }
        }
    }
}
