package grafika.main.map;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

import calculations.KonstantesUniversal;
import calculations.Main;
import calculations.MapChunk;
import calculations.komandas.Komanda;
import calculations.konstantes.Grafiskie;
import grafika.main.Grafika;
import grafika.main.SetupThread;

public class Map {

//	private static Map<String, Komanda> komandasList;
//	protected static ArrayList<ArrayList<MapChunk>> laukums;
//
//	private static SetupThread thread;
//
//	public static void main(Graphics g, SetupThread threadTemp, Grafika grafika) { //pilnîgi visa karte
//
//		komandasList = Main.komandasList;
//		laukums = Main.laukums;
//
//		thread = threadTemp;
//
//		int x0=threadTemp.dati.miniMapX, y0=threadTemp.dati.miniMapY, //zîmçðanas pamatpunkts
//				platumsMax=Math.max(0, threadTemp.dati.miniMapPlatums), //zîmçðanas maksimumi
//				augstumsMax=Math.max(0, threadTemp.dati.miniMapAugstums);
//
//		int laukumaPlatums = KonstantesUniversal.laukumaPlatumsSum, //reâlie laukuma izmçri
//				laukumaAugstums = KonstantesUniversal.laukumaAugstumsSum;
//
//		double merogs=Math.min((double)platumsMax/laukumaPlatums, (double)augstumsMax/laukumaAugstums);
//
//		int kartesPlatums = (int)(laukumaPlatums*merogs),
//				kartesAugstums = (int)(laukumaAugstums*merogs); //zîmçðanas izmçri
//
//		Terrain.main(g, x0, y0, kartesPlatums, kartesAugstums, merogs);
//		Cilveki.main(g, thread, x0, y0, merogs, laukums, komandasList);
//		Loot.main(g, x0, y0, merogs, laukums);
//
//		overPanels(g, x0, y0, kartesPlatums, kartesAugstums); //maliòas apkârt laukumam
//
//		if(threadTemp.dati.miniMapDrawInfo) { //informâcija apakðâ par paðu karti
//			drawInfo(g, x0, y0, merogs, laukumaPlatums, laukumaAugstums);
//		}
//
//
//	}
//
//	private static void overPanels(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums){
//
//
//		int mala=15;//KonstantesUniversal.mala;
//		g.setColor(Grafiskie.malasKrasa); //apkârt kartei uzzîmç maliòu
//		g.setColor(Color.darkGray);
//
//		g.fillRect(x0-mala, y0-mala, mala, kartesAugstums+mala*2);//rietumi
//		g.fillRect(x0-mala, y0-mala, kartesPlatums+mala*2, mala);//ziemeïi
//		g.fillRect(x0+kartesPlatums, y0-mala, mala, kartesAugstums+mala*2);//austrumi
//		g.fillRect(x0-mala, y0+kartesAugstums, kartesPlatums+mala*2, mala);//dienvidi
//
//
//	}
//
//	private static void drawInfo(Graphics g, int x0temp, int y0temp, double merogs, int laukumaPlatums, int laukumaAugstums){
//		Color infoPanelKrasa = Color.white;
//
//		int x0 = x0temp + 5, y0 = y0temp + (int)(laukumaAugstums*merogs), w=2, rindasPlatums=15;
//
//		g.setColor(infoPanelKrasa);
//		w = drawInfoKartei(g, x0, y0, w, rindasPlatums, merogs);
//		g.drawString("--------------------", x0, y0 + w * rindasPlatums); w++;
//		w = drawInfoGenRates(g, x0, y0, w, rindasPlatums);
//
//	}
//
//	private static int drawInfoKartei(Graphics g, int x0, int y0, int w, int rindasPlatums, double merogs){
//		g.drawString("kartes platums: " + (int)(merogs * KonstantesUniversal.laukumaPlatumsSum) +
//						" kartes augstums: " + (int)(merogs * KonstantesUniversal.laukumaAugstumsSum) +
//						" merogs: " + (new DecimalFormat("#.##").format(merogs) ),
//				x0, y0 + w * rindasPlatums); w++;
//		g.drawString("laukuma kopçjais platums (x): " + KonstantesUniversal.laukumaPlatumsSum +
//						" laukuma kopçjais augstums (y): " + KonstantesUniversal.laukumaAugstumsSum +
//						" chunk platums (x  & y): " + KonstantesUniversal.mapChunkW,
//				x0, y0 + w * rindasPlatums); w++;
//		g.drawString("chunk skaits x: " + KonstantesUniversal.mapChunkCountX +
//						" chunk skaits y: " + KonstantesUniversal.mapChunkCountY +
//						" chunk platums (cells): " + KonstantesUniversal.mapCellCount,
//				x0, y0 + w * rindasPlatums); w++;
//		g.drawString("cell skaits x: " + (KonstantesUniversal.mapCellCount * KonstantesUniversal.mapChunkCountX) +
//						" cell skaits y: " + (KonstantesUniversal.mapCellCount * KonstantesUniversal.mapChunkCountY) +
//						" cell platums (x & y): " + KonstantesUniversal.mapCellW,
//				x0, y0 + w * rindasPlatums); w++;
//
//		return w;
//
//	}
//
//	private static int drawInfoGenRates(Graphics g, int x0, int y0, int w, int rindasPlatums){
//
//		g.drawString("overallGenRate: "+(new DecimalFormat("#.##").format(KonstantesUniversal.overallGenRate)),
//				x0, y0 + w * rindasPlatums); w++;
//
//		for(int i=1; i<KonstantesUniversal.defaultLietas.size(); i++){
//			int x = x0, y = y0 + w * rindasPlatums;
//			drawInfoGenRateOnce(g, x, y, i);
//			w++;
//		}
//
//		return w;
//	}
//
//	private static void drawInfoGenRateOnce(Graphics g, int x, int y, int tips){
//		String nosaukums = KonstantesUniversal.defaultLietas.get(tips).nosaukums;
//		double genRate = KonstantesUniversal.overallGenRate * KonstantesUniversal.defaultLietas.get(tips).genKoef;
//
//		g.drawString(nosaukums + " genRate: " + (new DecimalFormat("#.##").format(genRate)) +
//						" minAmount: " + (new DecimalFormat("#.#").format(KonstantesUniversal.defaultLietas.get(tips).genMin)) +
//						" maxAmount: " + (new DecimalFormat("#.#").format(KonstantesUniversal.defaultLietas.get(tips).genMax)),
//				x, y);
//
//	}


}
