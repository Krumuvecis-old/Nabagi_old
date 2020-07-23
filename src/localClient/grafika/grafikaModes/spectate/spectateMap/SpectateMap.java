package localClient.grafika.grafikaModes.spectate.spectateMap;

import localClient.Dati;
import localClient.grafika.grafikaParts.DrawManager;
import server.calculations.MapChunk;
import server.dataBase.DataBase;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class SpectateMap {

    Terrain terrain;
    Loot loot;
    Cilveki cilveki;

    public SpectateMap(){
        terrain = new Terrain();
        loot = new Loot();
        cilveki = new Cilveki();

    }

    public void draw(Graphics g, Dati dati, int[] contentsXY, int[] contentsSize) {
        update(dati, contentsSize);

        int[] drawCenterXY = new int[]{
                contentsSize[0] / 2,
                contentsSize[1] / 2};

        DrawManager.SpectateMapInfo spectateMapInfo = dati.drawManagerList.get(dati.modeCurrent).spectateMapInfo;

        drawLaukumaFons(g, spectateMapInfo, drawCenterXY, contentsXY, contentsSize);
        drawChunks(g, dati, spectateMapInfo, contentsXY, contentsSize, drawCenterXY);

        if (spectateMapInfo.chunkGrid || spectateMapInfo.cellGrid)
            drawLaukumaMala(g, spectateMapInfo, drawCenterXY, contentsXY, Color.red); //laukuma malas kontûra

        drawCrosshairs(g, dati.grafikasDati.colorPalette.pair3[1], contentsXY, drawCenterXY);
    }

    private void update(Dati dati, int[] contentsSize){

        //pârrçíina mçrogu, zoom utml
        dati.drawManagerList.get(Dati.ModeOption.spectate).spectateMapInfo.update(contentsSize);

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



    private void drawChunks(Graphics g, Dati dati, DrawManager.SpectateMapInfo spectateMapInfo,
                            int[] contentsXY, int[] contentsSize, int[] drawCenterXY){

        int[] visibleStartingPoint, visibleEndPoint;

        if(spectateMapInfo.mapWrap){
            visibleStartingPoint = new int[]{0, 0};
            visibleEndPoint = new int[]{contentsSize[0], contentsSize[1]};
        } else {
            visibleStartingPoint = new int[]{ //redzamîbas top-left stûris ekrânâ
                    (int)Math.max(0, drawCenterXY[0] - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                    (int)Math.max(0, drawCenterXY[1] - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs)};

            visibleEndPoint = new int[]{ //redzamîbas bottom-right stûris ekrânâ
                    (int)Math.min(
                            contentsSize[0],
                            drawCenterXY[0] + (DataBase.laukumaPlatumsSum - spectateMapInfo.centerXY[0]) * spectateMapInfo.merogs),
                    (int)Math.min(
                            contentsSize[1],
                            drawCenterXY[1] + (DataBase.laukumaAugstumsSum - spectateMapInfo.centerXY[1]) * spectateMapInfo.merogs)};
        }

        int[] activeChunk = new int[]{ //centrâlais aktîvais chunk - numurs
                        (int)Math.floor(1.0 * spectateMapInfo.centerXY[0] / DataBase.mapChunkW),
                        (int)Math.floor(1.0 * spectateMapInfo.centerXY[1] / DataBase.mapChunkW)},

                visibleChunkOffset =  new int[]{ //graphical offset inside chunk
                        (int)((spectateMapInfo.centerXY[0] - Math.floor(1.0 * spectateMapInfo.centerXY[0] / DataBase.mapChunkW) * DataBase.mapChunkW)
                                * spectateMapInfo.merogs),
                        (int)((spectateMapInfo.centerXY[1] - Math.floor(1.0 * spectateMapInfo.centerXY[1] / DataBase.mapChunkW) * DataBase.mapChunkW)
                                * spectateMapInfo.merogs)},

                visibleChunkCountBefore =  new int[]{
                        (int)Math.max(0, Math.ceil(
                                (drawCenterXY[0] - visibleStartingPoint[0] - visibleChunkOffset[0] - 2)
                                        / spectateMapInfo.chunkSizeGraphical)),
                        (int)Math.max(0, Math.ceil(
                                (drawCenterXY[1] - visibleStartingPoint[1] - visibleChunkOffset[1] - 2)
                                        / spectateMapInfo.chunkSizeGraphical))},

                visibleChunkCountAfter =  new int[]{
                        (int)Math.max(0, Math.ceil(
                                (visibleEndPoint[0] - drawCenterXY[0] - (spectateMapInfo.chunkSizeGraphical - visibleChunkOffset[0]))
                                        / spectateMapInfo.chunkSizeGraphical)),
                        (int)Math.max(0,Math.ceil(
                                (visibleEndPoint[1] - drawCenterXY[1] - (spectateMapInfo.chunkSizeGraphical - visibleChunkOffset[1]))
                                        / spectateMapInfo.chunkSizeGraphical))},

                visibleChunkCount = new int[]{ //kopçjais redzamo chunk skaits
                        1 + visibleChunkCountBefore[0] + visibleChunkCountAfter[0],
                        1 + visibleChunkCountBefore[1] + visibleChunkCountAfter[1]},

                startingChunk = new int[]{ //pirmais ekrânâ zîmçjamais chunk - relatîvs numurs (var bût negatîvs)
                        activeChunk[0] - visibleChunkCountBefore[0],
                        activeChunk[1] - visibleChunkCountBefore[1]};


        for(int[] _chunkXY = new int[]{startingChunk[0], startingChunk[1]};
            _chunkXY[0] < visibleChunkCount[0] + startingChunk[0];
            _chunkXY[0]++){

            for(_chunkXY[1] = startingChunk[1];
                _chunkXY[1] < visibleChunkCount[1] + startingChunk[1];
                _chunkXY[1]++){

                int[] chunkXY = new int[]{_chunkXY[0], _chunkXY[1]};

                while (chunkXY[0] < 0) chunkXY[0] += DataBase.mapChunkCountX;
                while (chunkXY[0] >= DataBase.mapChunkCountX) chunkXY[0] -= DataBase.mapChunkCountX;

                while (chunkXY[1] < 0) chunkXY[1] += DataBase.mapChunkCountY;
                while (chunkXY[1] >= DataBase.mapChunkCountY) chunkXY[1] -= DataBase.mapChunkCountY;

                int[] chunkLoc = new int[]{
                        (int)(contentsXY[0] + drawCenterXY[0] + (_chunkXY[0] - activeChunk[0]) * spectateMapInfo.chunkSizeGraphical - visibleChunkOffset[0] + 1),
                        (int)(contentsXY[1] + drawCenterXY[1] + (_chunkXY[1] - activeChunk[1]) * spectateMapInfo.chunkSizeGraphical - visibleChunkOffset[1] + 1)};

                drawChunk(g, dati, spectateMapInfo, chunkLoc, chunkXY, activeChunk);
            }
        }
    }

    private void drawChunk(Graphics g, Dati dati, DrawManager.SpectateMapInfo spectateMapInfo,
                           int[] chunkLoc, int[] chunkXY, int[] activeChunk){
        //ðeit zîmç attiecîgo chunk

        terrain.draw(g, dati, spectateMapInfo, chunkLoc, chunkXY); //uzzîmç cell grid & terrain
        //te varçtu zîmçt komandas un teritorijas
        //te varçtu zîmçt çkas
        loot.draw(g, dati, spectateMapInfo, chunkLoc, chunkXY); //uzzîmç loot
        cilveki.draw(g, dati, spectateMapInfo, chunkLoc, chunkXY); //uzzîmç spçlçtâjus

        if (spectateMapInfo.chunkGrid) drawChunkInfo(g, spectateMapInfo, chunkLoc, chunkXY, activeChunk);
    }

    private void drawChunkInfo(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo,
                               int[] chunkLoc, int[] chunkXY, int[] activeChunk){

        if (chunkXY[0] == activeChunk[0] && chunkXY[1] == activeChunk[1])
            g.setColor(Color.yellow);
        else g.setColor(new Color(40,40,40));

        g.drawRect(chunkLoc[0] - 1, chunkLoc[1] - 1,
                (int)spectateMapInfo.chunkSizeGraphical - 2, (int)spectateMapInfo.chunkSizeGraphical - 2);

        int[] textOffset = new int[]{3, 0};
        int textHeight = 15, w = 1;

        if(spectateMapInfo.chunkSizeGraphical >= 45){
            g.drawString(chunkXY[0] + ", " + chunkXY[1],
                    chunkLoc[0] + textOffset[0],
                    chunkLoc[1] + textOffset[1] + textHeight * w);
            w++;
        }

        if(spectateMapInfo.chunkSizeGraphical >= 75){
            List<Integer> chunkXYlist = new ArrayList<>();
            chunkXYlist.add(chunkXY[0]);
            chunkXYlist.add(chunkXY[1]);
            MapChunk chunk = DataBase.laukums.get(chunkXYlist);

            g.drawString("players: " + chunk.cilvekiList.size(),
                    chunkLoc[0] + textOffset[0],
                    chunkLoc[1] + textOffset[1] + textHeight * w);
            w++;

            g.drawString("loot: " + chunk.lietas.size(),
                    chunkLoc[0] + textOffset[0],
                    chunkLoc[1] + textOffset[1] + textHeight * w);
            //w++;
        }

    }

    private void drawLaukumaMala(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo,
                                 int[] drawCenterXY, int[] contentsXY, Color borderColor){
        g.setColor(borderColor);
        g.drawRect(
                (int)(contentsXY[0] + drawCenterXY[0] - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                (int)(contentsXY[1] + drawCenterXY[1] - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs),
                (int)(DataBase.laukumaPlatumsSum * spectateMapInfo.merogs),
                (int)(DataBase.laukumaAugstumsSum * spectateMapInfo.merogs));
    }

    private void drawCrosshairs(Graphics g, Color krasa, int[] XY, int[] centerXY){
        int crosshairSize = 20;
        g.setColor(krasa);
        g.drawLine(XY[0] + centerXY[0] - crosshairSize / 2, XY[1] + centerXY[1],
                XY[0] + centerXY[0] + crosshairSize / 2, XY[1] + centerXY[1]); //horizontal
        g.drawLine(XY[0] + centerXY[0], XY[1] + centerXY[1] - crosshairSize / 2,
                XY[0] + centerXY[0], XY[1] + centerXY[1] + crosshairSize / 2); //vertical
    }

}
