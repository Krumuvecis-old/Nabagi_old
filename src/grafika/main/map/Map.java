package grafika.main.map;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

import calculations.KonstantesUniversal;
import calculations.Main;
import calculations.MapChunk;
import calculations.konstantes.Fizikas;
import calculations.konstantes.Formulas;
import calculations.cilveki.Cilveks;
import calculations.komandas.Komanda;
import calculations.konstantes.Grafiskie;
import calculations.lietas.LietuPreseti;
import calculations.lietas.Lieta;
import grafika.KonstantesGrafikai;
import grafika.main.Grafika;
import grafika.main.SetupThread;

public class Map {

	private static ArrayList<Komanda> komandasList;
	protected static ArrayList<ArrayList<MapChunk>> laukums;
	
	private static SetupThread thread;
	
	public static void main(Graphics g, SetupThread threadTemp, Grafika grafika) { //pilnîgi visa karte

		komandasList = grafika.komandasList;
		laukums = Main.laukums;
		
		thread = threadTemp;
		
		int x0=threadTemp.dati.miniMapX, y0=threadTemp.dati.miniMapY,
				platumsMax=Math.max(0, threadTemp.dati.miniMapPlatums),
				augstumsMax=Math.max(0, threadTemp.dati.miniMapAugstums);
		
		int laukumaPlatums = KonstantesUniversal.laukumaPlatumsSum,
				laukumaAugstums=KonstantesUniversal.laukumaAugstumsSum;
		double merogs=Math.min((double)platumsMax/laukumaPlatums, (double)augstumsMax/laukumaAugstums);

		int kartesPlatums = (int)(laukumaPlatums*merogs),
				kartesAugstums = (int)(laukumaAugstums*merogs);

		Terrain.main(g, x0, y0, kartesPlatums, kartesAugstums, merogs);
		Cilveki.main(g, thread, x0, y0, merogs, laukums, komandasList);
		Loot.main(g, x0, y0, merogs, laukums);

		overPanels(g, x0, y0, kartesPlatums, kartesAugstums); //maliòas apkârt laukumam

		if(threadTemp.dati.miniMapDrawInfo) { //informâcija apakðâ par paðu karti
			drawInfo(g, x0, y0, merogs, laukumaPlatums, laukumaAugstums);
		}

		//laukuma diagnostikas panelis
		int x1=350, y1=200, wx=150, wy=50, tekstaPlatums=15;
		g.setColor(Color.black);
		for(int[] chunkXY = {0,0}; chunkXY[0]<Main.laukums.size(); chunkXY[0]++){
			for(chunkXY[1]=0; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++){
				int x = x1 + chunkXY[0] * wx,
						y = y1 + chunkXY[1] * wy;
				int w=0;
				g.drawString("lietas: "+Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.size(),
						x,y + w * tekstaPlatums); w++;
				g.drawString("players: "+Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(),
						x,y + w * tekstaPlatums); w++;
			}
		}


		
	}

	private static void overPanels(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums){


		int mala=15;//KonstantesUniversal.mala;
		g.setColor(Grafiskie.malasKrasa); //apkârt kartei uzzîmç maliòu
		g.setColor(Color.darkGray);

		g.fillRect(x0-mala, y0-mala, mala, kartesAugstums+mala*2);//rietumi
		g.fillRect(x0-mala, y0-mala, kartesPlatums+mala*2, mala);//ziemeïi
		g.fillRect(x0+kartesPlatums, y0-mala, mala, kartesAugstums+mala*2);//austrumi
		g.fillRect(x0-mala, y0+kartesAugstums, kartesPlatums+mala*2, mala);//dienvidi


	}

	private static void drawInfo(Graphics g, int x0temp, int y0temp, double merogs, int laukumaPlatums, int laukumaAugstums){
		Color infoPanelKrasa = Color.white;

		int x0 = x0temp + 5, y0 = y0temp + (int)(laukumaAugstums*merogs), w=2, rindasPlatums=15;

		g.setColor(infoPanelKrasa);
		w = drawInfoKartei(g, x0, y0, w, rindasPlatums, merogs);
		g.drawString("--------------------", x0, y0 + w * rindasPlatums); w++;
		w = drawInfoGenRates(g, x0, y0, w, rindasPlatums);

	}

	private static int drawInfoKartei(Graphics g, int x0, int y0, int w, int rindasPlatums, double merogs){
		g.drawString("kartes platums: " + (int)(merogs * KonstantesUniversal.laukumaPlatumsSum) +
						" kartes augstums: " + (int)(merogs * KonstantesUniversal.laukumaAugstumsSum) +
						" merogs: " + (new DecimalFormat("#.##").format(merogs) ),
				x0, y0 + w * rindasPlatums); w++;
		g.drawString("laukuma kopçjais platums (x): " + KonstantesUniversal.laukumaPlatumsSum +
						" laukuma kopçjais augstums (y): " + KonstantesUniversal.laukumaAugstumsSum +
						" chunk platums (x  & y): " + KonstantesUniversal.mapChunkW,
				x0, y0 + w * rindasPlatums); w++;
		g.drawString("chunk skaits x: " + KonstantesUniversal.mapChunkCountX +
						" chunk skaits y: " + KonstantesUniversal.mapChunkCountY +
						" chunk platums (cells): " + KonstantesUniversal.mapCellCount,
				x0, y0 + w * rindasPlatums); w++;
		g.drawString("cell skaits x: " + (KonstantesUniversal.mapCellCount * KonstantesUniversal.mapChunkCountX) +
						" cell skaits y: " + (KonstantesUniversal.mapCellCount * KonstantesUniversal.mapChunkCountY) +
						" cell platums (x & y): " + KonstantesUniversal.mapCellW,
				x0, y0 + w * rindasPlatums); w++;

		return w;

	}

	private static int drawInfoGenRates(Graphics g, int x0, int y0, int w, int rindasPlatums){

		g.drawString("overallGenRate: "+(new DecimalFormat("#.##").format(KonstantesUniversal.overallGenRate)),
				x0, y0 + w * rindasPlatums); w++;

		for(int i=1; i<KonstantesUniversal.defaultLietas.size(); i++){
			int x = x0, y = y0 + w * rindasPlatums;
			drawInfoGenRateOnce(g, x, y, i);
			w++;
		}

		return w;
	}

	private static void drawInfoGenRateOnce(Graphics g, int x, int y, int tips){
		String nosaukums = KonstantesUniversal.defaultLietas.get(tips).nosaukums;
		double genRate = KonstantesUniversal.overallGenRate * KonstantesUniversal.defaultLietas.get(tips).genKoef;

		g.drawString(nosaukums + " genRate: " + (new DecimalFormat("#.##").format(genRate)) +
						" minAmount: " + (new DecimalFormat("#.#").format(KonstantesUniversal.defaultLietas.get(tips).genMin)) +
						" maxAmount: " + (new DecimalFormat("#.#").format(KonstantesUniversal.defaultLietas.get(tips).genMax)),
				x, y);

	}


}
