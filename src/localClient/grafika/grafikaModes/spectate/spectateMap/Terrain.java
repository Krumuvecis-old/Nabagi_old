package localClient.grafika.grafikaModes.spectate.spectateMap;

import localClient.Dati;
import localClient.grafika.grafikaParts.DrawManager;
import server.calculations.MapCell;
import server.dataBase.DataBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Terrain {

    Terrain(){}

    void draw(Graphics g, Dati dati, DrawManager.SpectateMapInfo spectateMapInfo, int[] chunkLoc, int[] chunkXY){

        //te cikliski jâiziet cauri visâm chunka rûtiòâm

        List<Integer> _chunkXY = new ArrayList<>();
        _chunkXY.add(chunkXY[0]);
        _chunkXY.add(chunkXY[1]);

        List<Integer> cellXY = new ArrayList<>();
        cellXY.add(0);
        cellXY.add(0);

        boolean detailedCells = false;
        int[] colorComponents = new int[]{0,0,0};
        if(spectateMapInfo.merogs >= 0.3) detailedCells = true;

        for(cellXY.set(0, 0); cellXY.get(0)<DataBase.mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
            for(cellXY.set(1, 0); cellXY.get(1)<DataBase.mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){

                MapCell cell = DataBase.laukums.get(_chunkXY).mapCells.get(cellXY);

                if(detailedCells) {
                    double size = DataBase.mapCellW * spectateMapInfo.merogs;
                    double[] loc = new double[]{
                            chunkLoc[0] + cellXY.get(0) * size,
                            chunkLoc[1] + cellXY.get(1) * size};


                    drawTerrain(g, dati, cell, loc, size, spectateMapInfo.merogs);
                    if(spectateMapInfo.cellGrid) drawCellInfo(g, cell, loc, size, new Color(70,80,70), spectateMapInfo.merogs);
                }
                else {
                    Color cellColor = MapCell.terrainPresets.get(cell.terrainType).defaultColor;
                    colorComponents[0] += cellColor.getRed();
                    colorComponents[1] += cellColor.getGreen();
                    colorComponents[2] += cellColor.getBlue();
                }

            }
        }

        if(!detailedCells) drawCombinedTerrain(g, chunkLoc, (int)Math.ceil(spectateMapInfo.chunkSizeGraphical), colorComponents);
    }

    private void drawCombinedTerrain(Graphics g, int[] chunkLoc, int chunkSize, int[] colorComponents){
        //te iekrâso visu chunk, ja zoom neatïauj katru rûtiòu

        int cellCount = (int)Math.pow(DataBase.mapCellCount, 2);
        g.setColor(new Color(
                colorComponents[0]/cellCount,
                colorComponents[1]/cellCount,
                colorComponents[2]/cellCount));

        g.fillRect(chunkLoc[0], chunkLoc[1], chunkSize, chunkSize);
    }

    private void drawTerrain(Graphics g, Dati dati, MapCell cell, double[] cellLoc, double size, double merogs){

        if(merogs >= 2){ //sprite
            g.drawImage(dati.grafikasDati.images.get(MapCell.terrainPresets.get(cell.terrainType).spriteName),
                    (int)cellLoc[0], (int)cellLoc[1], (int)size, (int)size,
                    null);

        } else { //colored rectangle
            g.setColor(MapCell.terrainPresets.get(cell.terrainType).defaultColor);
            g.fillRect((int)cellLoc[0], (int)cellLoc[1], (int)Math.ceil(size), (int)Math.ceil(size));
        }

    }

    private void drawCellInfo(Graphics g, MapCell cell, double[] cellLoc, double size, Color krasa, double merogs){
        //te zîmç cell râmi un info (analoìiski kâ chunkiem)
        g.setColor(krasa);

        if(merogs >= 0.3){ //grid
            g.drawRect((int)cellLoc[0] + 1, (int)cellLoc[1] + 1,
                    (int)size - 2, (int)size - 2);
        }

        if(merogs >= 0.9){ //info
            int[] textOffset = new int[]{3, 0};
            int textHeight = 15, w = 1;

            g.drawString("" + cell.terrainType,
                    (int)(cellLoc[0] + textOffset[0]),
                    (int)(cellLoc[1] + textOffset[1] + textHeight * w));
            //w++;
        }

    }

}
