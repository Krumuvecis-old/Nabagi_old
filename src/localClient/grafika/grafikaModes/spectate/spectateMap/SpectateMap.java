package localClient.grafika.grafikaModes.spectate.spectateMap;

import localClient.Dati;
import localClient.grafika.grafikaParts.DrawManager;
import server.dataBase.DataBase;

import java.awt.*;

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
        drawChunks(g, spectateMapInfo, contentsXY, contentsSize, drawCenterXY);

        if (spectateMapInfo.chunkGrid || spectateMapInfo.cellGrid)
            drawLaukumaMala(g, spectateMapInfo, drawCenterXY, contentsXY);

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

    private void drawLaukumaMala(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo, int[] drawCenterXY, int[] contentsXY){
        g.setColor(Color.red); //laukuma malas kontûra
        g.drawRect(
                (int)(contentsXY[0] + drawCenterXY[0] - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                (int)(contentsXY[1] + drawCenterXY[1] - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs),
                (int)(DataBase.laukumaPlatumsSum * spectateMapInfo.merogs),
                (int)(DataBase.laukumaAugstumsSum * spectateMapInfo.merogs));
    }

    private void drawChunks(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo,
                            int[] contentsXY, int[] contentsSize, int[] drawCenterXY){

        double chunkSizeGraphical = DataBase.mapChunkW * spectateMapInfo.merogs;

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
                                        / chunkSizeGraphical)),
                        (int)Math.max(0, Math.ceil(
                                (drawCenterXY[1] - visibleStartingPoint[1] - visibleChunkOffset[1] - 2)
                                        / chunkSizeGraphical))},

                visibleChunkCountAfter =  new int[]{
                        (int)Math.max(0, Math.ceil(
                                (visibleEndPoint[0] - drawCenterXY[0] - (chunkSizeGraphical - visibleChunkOffset[0]))
                                        / chunkSizeGraphical)),
                        (int)Math.max(0,Math.ceil(
                                (visibleEndPoint[1] - drawCenterXY[1] - (chunkSizeGraphical - visibleChunkOffset[1]))
                                        / chunkSizeGraphical))},

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

                drawChunk(g, spectateMapInfo,
                        contentsXY, drawCenterXY, chunkSizeGraphical,
                        chunkXY, _chunkXY, activeChunk);
            }
        }
    }

    private void drawChunk(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo,
                           int[] contentsXY, int[] drawCenterXY, double chunkSizeGraphical,
                           int[] chunkXY, int[] _chunkXY, int[] activeChunk){
        //ðeit var zîmçt attiecîgo chunk

        terrain.draw(g); //uzzîmç cell grid & terrain
        //te varçtu zîmçt komandas un teritorijas
        //te varçtu zîmçt çkas
        loot.draw(g); //uzzîmç loot
        cilveki.draw(g); //uzzîmç spçlçtâjus

        if (spectateMapInfo.chunkGrid) drawChunkInfo(g, spectateMapInfo,
                    contentsXY, drawCenterXY, chunkSizeGraphical,
                    chunkXY, _chunkXY, activeChunk);
    }

    private void drawChunkInfo(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo,
                               int[] contentsXY, int[] drawCenterXY, double chunkSizeGraphical,
                               int[] chunkXY, int[] _chunkXY, int[] activeChunk){
        if (chunkXY[0] == activeChunk[0] && chunkXY[1] == activeChunk[1])
            g.setColor(Color.yellow);
        else g.setColor(Color.darkGray);

        g.drawRect(
                (int)(contentsXY[0] + drawCenterXY[0] + _chunkXY[0] * chunkSizeGraphical - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs) - 1,
                (int)(contentsXY[1] + drawCenterXY[1] + _chunkXY[1] * chunkSizeGraphical - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs) - 1,
                (int)chunkSizeGraphical - 2,
                (int)chunkSizeGraphical - 2);

        g.drawString("x: " + chunkXY[0] + " y: " + chunkXY[1],
                (int)(contentsXY[0] + drawCenterXY[0] + _chunkXY[0] * chunkSizeGraphical - spectateMapInfo.centerXY[0] * spectateMapInfo.merogs),
                (int)(contentsXY[1] + drawCenterXY[1] + _chunkXY[1] * chunkSizeGraphical + 15 - spectateMapInfo.centerXY[1] * spectateMapInfo.merogs));

    }

    private void drawCrosshairs(Graphics g, Color krasa, int[] XY, int[] centerXY){
        int crosshairSize = 20;
        g.setColor(krasa);
        g.drawLine(XY[0] + centerXY[0] - crosshairSize / 2, XY[1] + centerXY[1],
                XY[0] + centerXY[0] + crosshairSize / 2, XY[1] + centerXY[1]); //horizontal
        g.drawLine(XY[0] + centerXY[0], XY[1] + centerXY[1] - crosshairSize / 2,
                XY[0] + centerXY[0], XY[1] + centerXY[1] + crosshairSize / 2); //vertical
    }



    //zemâk no vecâ
//	protected boolean tablo3Draw=false;
//	protected Color tablo3krasa=Color.white;
//

//    public static double cilvekiKrasaSaturation=1;
//    public static double cilvekiKrasaBrightnessMin=0.4; //pie hpRatio=0
//    public static double cilvekiKrasaBrightnessMax=1; //pie hpRatio=1
//
//    public static Color kronaKrasa = new Color(0,0,0); //kroòa krâsa - melns  punkts
//    public static double kronaKoeficients=0.5; //kroòa resnums pret kopçjo resnumu

//
//	private void drawTablo3(Graphics g){
//		//laukuma diagnostikas panelis
//		int x0 = 10 + thread.dati.miniMapX,
//				y0 = 13 + thread.dati.miniMapY,
//				platumsMax=Math.max(0, thread.dati.miniMapPlatums),
//				augstumsMax=Math.max(0, thread.dati.miniMapAugstums);
//
//		int laukumaPlatums = KonstantesUniversal.laukumaPlatumsSum,
//				laukumaAugstums=KonstantesUniversal.laukumaAugstumsSum;
//		double merogs = Math.min((double)platumsMax/laukumaPlatums,
//				(double)augstumsMax/laukumaAugstums);
//
//		int wx = (int)(KonstantesUniversal.mapChunkW * merogs),
//				wy = (int)(KonstantesUniversal.mapChunkW * merogs),
//				tekstaPlatums = 15;
//		g.setColor(thread.dati.tablo3krasa);
//		for(int[] chunkXY = {0,0}; chunkXY[0]< CalculationsThread.laukums.size(); chunkXY[0]++){
//			for(chunkXY[1]=0; chunkXY[1]<CalculationsThread.laukums.get(chunkXY[0]).size(); chunkXY[1]++){
//				int x = x0 + chunkXY[0] * wx,
//						y = y0 + chunkXY[1] * wy;
//				int w=0;
//				g.drawString("lietas: "+CalculationsThread.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.size(),
//						x,y + w * tekstaPlatums); w++;
//				g.drawString("players: "+CalculationsThread.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(),
//						x,y + w * tekstaPlatums); w++;
//				for (int i=0; i<CalculationsThread.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++){
//					g.drawString(CalculationsThread.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(i).vards,
//							x,y + w * tekstaPlatums); w++;
//				}
//			}
//		}
//	}


}
