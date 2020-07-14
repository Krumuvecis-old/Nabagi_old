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
            if (spectateMapInfo.cellGrid) drawCellGrid(g);
            drawChunkGrid(g);
            drawLaukumaMala(g, spectateMapInfo, drawCenterXY);
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
                (int)(drawCenterXY[0] - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                (int)(drawCenterXY[1] - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs),
                (int)(DataBase.laukumaPlatumsSum * spectateMapInfo.merogs),
                (int)(DataBase.laukumaAugstumsSum * spectateMapInfo.merogs));
    }

    private void drawLaukumaMala(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo, int[] drawCenterXY){
        g.setColor(Color.red); //uzzîmç laukumu
        g.drawRect(
                (int)(drawCenterXY[0] - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                (int)(drawCenterXY[1] - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs),
                (int)(DataBase.laukumaPlatumsSum * spectateMapInfo.merogs),
                (int)(DataBase.laukumaAugstumsSum * spectateMapInfo.merogs));
    }

    private void drawChunkGrid(Graphics g){
        //horizontâlâs lînijas

        //vertikâlâs lînijas

        //zemâk no vecâ
//        int x0, y0, kartesPlatums, kartesAugstums;
//        double merogs;
//
//
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
//
//
//                }
//
//            }
//
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
//
//
//                }
//
//            }
//
//        }
    }

    private void drawCellGrid(Graphics g){
        //horizontâlâs lînijas

        //vertikâlâs lînijas
    }

    private void drawTerrain(){
        //te jâzîmç rûtiòas un terrain
    }
}
