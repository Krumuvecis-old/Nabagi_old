package localClient.grafika.grafikaModes.spectate.spectateMap;

import localClient.Dati;

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
                contentsXY[0] + contentsSize[0] / 2,
                contentsXY[1] + contentsSize[1] / 2};

        //te varçtu izdalît grid un fona zîmçðanu
        terrain.draw(g, dati, contentsXY , contentsSize, drawCenterXY); //uzzîmç grid & terrain
        //te varçtu zîmçt komandas un teritorijas
        //te varçtu zîmçt çkas
        loot.draw(g); //uzzîmç loot
        cilveki.draw(g); //uzzîmç spçlçtâjus

        drawContentPlaceHolder(g, contentsXY, contentsSize, dati.grafikasDati.colorPalette.pair3[1]);
    }

    private void update(Dati dati, int[] contentsSize){
        dati.drawManagerList.get(Dati.ModeOption.spectate).spectateMapInfo.update(contentsSize); //pârrçíina mçrogu, zoom utml


    }

    private void drawContentPlaceHolder(Graphics g, int[]XY, int[] size, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Spectator map placeholder", textLocation[0], textLocation[1]);
    }

    //zemâk kopçts no vecâ

//	public int miniMapX=tablo2x0, miniMapY=tablo2y0-15,
//			miniMapPlatums=ekranaPlatums-miniMapX-50,
//			miniMapAugstums=ekranaAugstums-miniMapY-50;
//
//
//	// --------------------
//	//zemâk par centrâlâ (kartes diagnostikas) tablo Parametriem
//
//	protected boolean tablo3Draw=false;
//	protected Color tablo3krasa=Color.white;
//
//	//augstâk vecie parametri

    // --------------------
    //zemâk jaunâs funkcijas paòemtas no TimeScheduler


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
