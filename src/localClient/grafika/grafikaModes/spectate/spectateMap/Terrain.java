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

        for(cellXY.set(0, 0); cellXY.get(0)<DataBase.mapCellCount; cellXY.set(0, cellXY.get(0) + 1)){
            for(cellXY.set(1, 0); cellXY.get(1)<DataBase.mapCellCount; cellXY.set(1, cellXY.get(1) + 1)){

                double size = DataBase.mapCellW * spectateMapInfo.merogs;
                double[] loc = new double[]{
                        chunkLoc[0] + cellXY.get(0) * size,
                        chunkLoc[1] + cellXY.get(1) * size};

                MapCell cell = DataBase.laukums.get(_chunkXY).mapCells.get(cellXY);
                drawTerrain(g);
                if(spectateMapInfo.cellGrid) drawCellGrid(g, loc, size, new Color(70,80,70), spectateMapInfo.merogs);
            }
        }
    }

    private void drawTerrain(Graphics g){
        //te jâzîmç terrain sprite vai krâsa atkarîbâ no mçroga
    }

    private void drawCellGrid(Graphics g, double[] cellLoc, double size, Color krasa, double merogs){
        //te jâzîmç râmis un info analoìiski kâ chunkiem

        if(merogs >= 0.3){
            g.setColor(krasa);
            g.drawRect((int)cellLoc[0] + 1, (int)cellLoc[1] + 1,
                    (int)size - 2, (int)size - 2);
        }

//        activeCell = new int[]{ //centrâlâ aktîvâ ðûna - numurs (pagaidâm netiek zîmçta)
//                (int)Math.floor(1.0 * spectateMapInfo.centerXY[0] / DataBase.mapCellW),
//                (int)Math.floor(1.0 * spectateMapInfo.centerXY[1] / DataBase.mapCellW)};
//
//        visibleCellCount = new int[]{ //netiek lietots - nepareiza formula
//                (int)(contentsSize[0] / chunkSizeGraphical),
//                (int)(contentsSize[1] / chunkSizeGraphical)};


//        if (cellXY[0] == activeCell[0] && cellXY[1] == activeCell[1])
//            g.setColor(Color.yellow);
//        else g.setColor(Color.darkGray);
//
//        g.drawRect(chunkLoc[0] - 1, chunkLoc[1] - 1,
//                (int)spectateMapInfo.chunkSizeGraphical - 2, (int)spectateMapInfo.chunkSizeGraphical - 2);
//
//        int[] textOffset = new int[]{3, 0};
//        int textHeight = 15, w = 1;
//
//        if(spectateMapInfo.chunkSizeGraphical >= 45){
//            g.drawString(chunkXY[0] + ", " + chunkXY[1],
//                    chunkLoc[0] + textOffset[0],
//                    chunkLoc[1] + textOffset[1] + textHeight * w);
//            w++;
//        }
//
//        if(spectateMapInfo.chunkSizeGraphical >= 75){
//            List<Integer> chunkXYlist = new ArrayList<>();
//            chunkXYlist.add(chunkXY[0]);
//            chunkXYlist.add(chunkXY[1]);
//            MapChunk chunk = DataBase.laukums.get(chunkXYlist);
//
//            g.drawString("players: " + chunk.cilvekiList.size(),
//                    chunkLoc[0] + textOffset[0],
//                    chunkLoc[1] + textOffset[1] + textHeight * w);
//            w++;
//
//            g.drawString("loot: " + chunk.lietas.size(),
//                    chunkLoc[0] + textOffset[0],
//                    chunkLoc[1] + textOffset[1] + textHeight * w);
//            //w++;
//        }

    }

}
