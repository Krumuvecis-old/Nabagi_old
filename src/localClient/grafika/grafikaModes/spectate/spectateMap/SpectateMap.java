package localClient.grafika.grafikaModes.spectate.spectateMap;

import localClient.Dati;

import java.awt.*;

public class SpectateMap {

    public SpectateMap(){

    }

    public void draw(Graphics g, Dati dati, int[] contentsXY, int[] contentsSize) {

        //if (dati.drawManagerList.get(dati.modeCurrent).spectateMapInfo.)


        //zem�k no vec�

//      int x0=threadTemp.dati.miniMapX, y0=threadTemp.dati.miniMapY, //z�m��anas pamatpunkts
//				platumsMax=Math.max(0, threadTemp.dati.miniMapPlatums), //z�m��anas maksimumi
//				augstumsMax=Math.max(0, threadTemp.dati.miniMapAugstums);


//      int laukumaPlatums = KonstantesUniversal.laukumaPlatumsSum, //re�lie laukuma izm�ri
//				laukumaAugstums = KonstantesUniversal.laukumaAugstumsSum;
//
//		double merogs=Math.min((double)platumsMax/laukumaPlatums, (double)augstumsMax/laukumaAugstums);
//
//		int kartesPlatums = (int)(laukumaPlatums*merogs),
//				kartesAugstums = (int)(laukumaAugstums*merogs); //z�m��anas izm�ri
//
//		Terrain.main(g, x0, y0, kartesPlatums, kartesAugstums, merogs);
//		Cilveki.main(g, thread, x0, y0, merogs, laukums, komandasList);
//		Loot.main(g, x0, y0, merogs, laukums);
//
//		overPanels(g, x0, y0, kartesPlatums, kartesAugstums); //mali�as apk�rt laukumam
//
//		if(threadTemp.dati.miniMapDrawInfo) { //inform�cija apak�� par pa�u karti
//			drawInfo(g, x0, y0, merogs, laukumaPlatums, laukumaAugstums);
//		}

        drawContentPlaceHolder(g, contentsXY, contentsSize, dati.grafikasDati.colorPalette.pair3[1]);

    }

    private void drawContentPlaceHolder(Graphics g, int[]XY, int[] size, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Spectator map placeholder", textLocation[0], textLocation[1]);
    }


//		komandasList = CalculationsThread.komandasList;
//		laukums = CalculationsThread.laukums;
//
//		thread = threadTemp;
//

    //zem�k kop�ts no vec�

    //	// --------------------
//	//zem�k par kartes z�m��anu
//
//	public boolean miniMapDraw=true, miniMapDrawInfo=true; //kartes z�m��ana visp�r un inform�cija tai apak��
//	public int miniMapX=tablo2x0, miniMapY=tablo2y0-15,
//			miniMapPlatums=ekranaPlatums-miniMapX-50,
//			miniMapAugstums=ekranaAugstums-miniMapY-50;
//
//
//	// --------------------
//	//zem�k par centr�l� (kartes diagnostikas) tablo Parametriem
//
//	protected boolean tablo3Draw=false;
//	protected Color tablo3krasa=Color.white;
//
//	//augst�k vecie parametri

    // --------------------
    //zem�k jaun�s funkcijas pa�emtas no TimeScheduler


//    public static double cilvekiKrasaSaturation=1;
//    public static double cilvekiKrasaBrightnessMin=0.4; //pie hpRatio=0
//    public static double cilvekiKrasaBrightnessMax=1; //pie hpRatio=1
//
//    public static Color kronaKrasa = new Color(0,0,0); //kro�a kr�sa - melns  punkts
//    public static double kronaKoeficients=0.5; //kro�a resnums pret kop�jo resnumu

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


//v�rt�ga funkcija bet j�pievieno pie centerPanel sample klases
//
//	private static void overPanels(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums){
//
//
//		int mala=15;//KonstantesUniversal.mala;
//		g.setColor(Grafiskie.malasKrasa); //apk�rt kartei uzz�m� mali�u
//		g.setColor(Color.darkGray);
//
//		g.fillRect(x0-mala, y0-mala, mala, kartesAugstums+mala*2);//rietumi
//		g.fillRect(x0-mala, y0-mala, kartesPlatums+mala*2, mala);//zieme�i
//		g.fillRect(x0+kartesPlatums, y0-mala, mala, kartesAugstums+mala*2);//austrumi
//		g.fillRect(x0-mala, y0+kartesAugstums, kartesPlatums+mala*2, mala);//dienvidi
//
//
//	}


}
