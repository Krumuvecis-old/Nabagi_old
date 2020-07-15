package localClient.grafika.grafikaModes.spectate.spectateMap;

import localClient.Dati;
import localClient.grafika.grafikaParts.DrawManager;
import server.dataBase.DataBase;

import java.awt.*;

public class Terrain {

    Terrain(){

    }

    void draw(Graphics g, Dati dati, int[] contentsXY, int[] contentsSize, int[] drawCenterXY){
        DrawManager.SpectateMapInfo spectateMapInfo = dati.drawManagerList.get(dati.modeCurrent).spectateMapInfo;

        drawLaukumaFons(g, spectateMapInfo, drawCenterXY, contentsXY, contentsSize);

        drawTerrain();

        if (spectateMapInfo.chunkGrid || spectateMapInfo.cellGrid) {
            drawGrid(g, spectateMapInfo, drawCenterXY, contentsXY, contentsSize);
            drawLaukumaMala(g, spectateMapInfo, drawCenterXY, contentsXY);
        }
    }

    private void drawLaukumaFons(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo,
                                 int[] drawCenterXY, int[] contentsXY, int[] contentsSize){
        //uzzîmç laukumu
        g.setColor(DataBase.laukumaKrasa);

        if(spectateMapInfo.mapWrap) g.fillRect( //viss contents
                contentsXY[0], contentsXY[1],
                contentsSize[0], contentsSize[1]);
        else g.fillRect( //tikai 1 laukums
                (int)(contentsXY[0] + drawCenterXY[0] - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                (int)(contentsXY[1] + drawCenterXY[1] - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs),
                (int)(DataBase.laukumaPlatumsSum * spectateMapInfo.merogs),
                (int)(DataBase.laukumaAugstumsSum * spectateMapInfo.merogs));
    }

    private void drawGrid(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo,
                          int[] drawCenterXY, int[] contentsXY, int[] contentsSize){

        //spectateMapInfo.cellGrid

        double chunkSizeGraphical = DataBase.mapChunkW * spectateMapInfo.merogs;

        int[] visibleStartingPoint = new int[]{
                        (int)Math.max(0, drawCenterXY[0] - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                        (int)Math.max(0, drawCenterXY[1] - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs)},

                visibleEndPoint = new int[]{
                        (int)Math.min(
                                contentsSize[0],
                                drawCenterXY[0] + (DataBase.laukumaPlatumsSum - spectateMapInfo.centerXY[0]) * spectateMapInfo.merogs),
                        (int)Math.min(
                                contentsSize[1],
                                drawCenterXY[1] + (DataBase.laukumaAugstumsSum - spectateMapInfo.centerXY[1]) * spectateMapInfo.merogs)},

                visibleChunkCount = new int[]{
                        (int)(Math.ceil((visibleEndPoint[0] - visibleStartingPoint[0]) / chunkSizeGraphical)),
                        (int)(Math.ceil((visibleEndPoint[1] - visibleStartingPoint[1]) / chunkSizeGraphical))},

                visibleCellCount = new int[]{
                        (int)(contentsSize[0] / chunkSizeGraphical),
                        (int)(contentsSize[1] / chunkSizeGraphical)},

                activeChunk = new int[]{
                        (int)Math.floor(1.0 * spectateMapInfo.centerXY[0] / DataBase.mapChunkW),
                        (int)Math.floor(1.0 * spectateMapInfo.centerXY[1] / DataBase.mapChunkW)},

                activeCell = new int[]{
                        (int)Math.floor(1.0 * spectateMapInfo.centerXY[0] / DataBase.mapCellW),
                        (int)Math.floor(1.0 * spectateMapInfo.centerXY[1] / DataBase.mapCellW)},

                startingChunk = new int[]{
                        activeChunk[0] - (int)Math.floor((drawCenterXY[0] - visibleStartingPoint[0]) / chunkSizeGraphical),
                        activeChunk[1] - (int)Math.floor((drawCenterXY[1] - visibleStartingPoint[1]) / chunkSizeGraphical)};




        for(int i = startingChunk[1]; i < visibleChunkCount[1] + startingChunk[1]; i++){



            //te horizontâlâs lînijas
        }

        g.setColor(Color.magenta);
        g.drawRect(contentsXY[0] + visibleStartingPoint[0] +1, contentsXY[1] + visibleStartingPoint[1] +1,
                visibleEndPoint[0] - visibleStartingPoint[0] -2, visibleEndPoint[1] - visibleStartingPoint[1] -2);



        for(int[] _chunkXY = new int[]{startingChunk[0], startingChunk[1]}; _chunkXY[0] < visibleChunkCount[0] + startingChunk[0]; _chunkXY[0]++){

            //te vajag vertikâlâs lînijas

            for(_chunkXY[1] = startingChunk[1]; _chunkXY[1] < visibleChunkCount[1] + startingChunk[1]; _chunkXY[1]++){

                int[] chunkXY = new int[]{_chunkXY[0], _chunkXY[1]};

                while (chunkXY[0] < 0) chunkXY[0] += DataBase.mapChunkCountX;
                while (chunkXY[0] >= DataBase.mapChunkCountX) chunkXY[0] -= DataBase.mapChunkCountX;

                while (chunkXY[1] < 0) chunkXY[1] += DataBase.mapChunkCountY;
                while (chunkXY[1] >= DataBase.mapChunkCountY) chunkXY[1] -= DataBase.mapChunkCountY;

                if (chunkXY[0] == activeChunk[0] && chunkXY[1] == activeChunk[1])
                    g.setColor(Color.yellow);
                else g.setColor(Color.darkGray);

                //ðo taisnstûri nevajag
                g.drawRect(
                        (int)(contentsXY[0] + drawCenterXY[0] + _chunkXY[0] * chunkSizeGraphical - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs) - 1,
                        (int)(contentsXY[1] + drawCenterXY[1] + _chunkXY[1] * chunkSizeGraphical - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs) - 1,
                        (int)chunkSizeGraphical - 2,
                        (int)chunkSizeGraphical - 2);

                g.drawString("x: " + _chunkXY[0] + " y: " + _chunkXY[1],
                        (int)(contentsXY[0] + drawCenterXY[0] + _chunkXY[0] * chunkSizeGraphical - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                        (int)(contentsXY[1] + drawCenterXY[1] + _chunkXY[1] * chunkSizeGraphical + 15 - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs));

            }

        }





        //zemâk no vecâ
//        int x0, y0, kartesPlatums, kartesAugstums;
//
//        Color chunkColor=Color.gray, cellColor=Color.darkGray;
//
//        int chunkCountX= DataBase.mapChunkCountX,
//                chunkCountY= DataBase.mapChunkCountY;
//
//        for(int i=0; i <= chunkCountY; i++){ //horizontâlâs lînijas
//
//            g.setColor(chunkColor);
//            int dyChunk=(int)(i * DataBase.mapChunkW * merogs);
//            g.drawLine(x0, y0+dyChunk, x0+kartesPlatums, y0+dyChunk);
//
//            if(drawCells && i<chunkCountY ){
//                g.setColor(cellColor);
//                //uzzîmç cell rûtiòas
//                for(int j = 1; j < DataBase.mapCellCount; j++){
//
//                    int dyCell=dyChunk + (int)(j * DataBase.mapCellW * merogs);
//                    g.drawLine(x0, y0+dyCell, x0+kartesPlatums, y0+dyCell);
//                }
//            }
//        }
//
//        for(int i=0; i <= chunkCountX; i++){ //vertikâlâs lînijas
//
//            g.setColor(chunkColor);
//            int dxChunk=(int)(i * DataBase.mapChunkW * merogs);
//            g.drawLine(x0 + dxChunk, y0, x0 + dxChunk, y0 + kartesAugstums);
//
//            if(drawCells && i< chunkCountX){
//                g.setColor(cellColor);
//                //uzzîmç cell rûtiòas
//                for(int j = 1; j < DataBase.mapCellCount; j++){
//
//                    int dxCell=dxChunk + (int)(j * DataBase.mapCellW * merogs);
//                    g.drawLine(x0 + dxCell, y0, x0 + dxCell, y0 + kartesAugstums);
//                }
//            }
//        }
    }

    private void drawCellGrid(Graphics g){
        //horizontâlâs lînijas

        //vertikâlâs lînijas
    }

    private void drawLaukumaMala(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo, int[] drawCenterXY, int[] contentsXY){
        g.setColor(Color.red); //laukuma malas kontûra
        g.drawRect(
                (int)(contentsXY[0] + drawCenterXY[0] - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                (int)(contentsXY[1] + drawCenterXY[1] - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs),
                (int)(DataBase.laukumaPlatumsSum * spectateMapInfo.merogs),
                (int)(DataBase.laukumaAugstumsSum * spectateMapInfo.merogs));
    }

    private void drawTerrain(){
        //te jâzîmç rûtiòas un terrain
    }
}
