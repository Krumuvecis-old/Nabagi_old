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
		
	}

	private static void overPanels(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums){


		int mala=KonstantesUniversal.mala;
		g.setColor(Grafiskie.malasKrasa); //apkârt kartei uzzîmç maliòu

		g.fillRect(x0-mala, y0-mala, mala, kartesAugstums+mala*2);//rietumi
		g.fillRect(x0-mala, y0-mala, kartesPlatums+mala*2, mala);//ziemeïi
		g.fillRect(x0+kartesPlatums, y0-mala, mala, kartesAugstums+mala*2);//austrumi
		g.fillRect(x0-mala, y0+kartesAugstums, kartesPlatums+mala*2, mala);//dienvidi


	}

	private static void drawInfo(Graphics g, int x0, int y0, double merogs, int laukumaPlatums, int laukumaAugstums){

		g.setColor(Color.white);
		g.drawString("laukuma platums (x): "+laukumaPlatums+" laukuma augstums (y): "+laukumaAugstums,
				x0+5, y0+15+(int)(laukumaAugstums*merogs));
		g.drawString("kartes platums: "+(int)(laukumaPlatums*merogs)+" kartes augstums: "+(int)(laukumaAugstums*merogs)+" merogs: "+(new DecimalFormat("#.##").format(merogs)),
				x0+5, y0+30+(int)(laukumaAugstums*merogs));

		double goldGenRate=0;
		for(int i=0; i<KonstantesUniversal.defaultLietas.size(); i++){
			if(KonstantesUniversal.defaultLietas.get(i).equals("Zelts"))
				goldGenRate = KonstantesUniversal.defaultLietas.get(i).genKoef * KonstantesUniversal.overallGenRate;
		}

		double paikaGenRate=0;
		for(int i=0; i<KonstantesUniversal.defaultLietas.size(); i++){
			if(KonstantesUniversal.defaultLietas.get(i).equals("Paika"))
				paikaGenRate = KonstantesUniversal.defaultLietas.get(i).genKoef * KonstantesUniversal.overallGenRate;
		}

		g.drawString("overallGenRate: "+(new DecimalFormat("#.###").format(goldGenRate))+
						"  goldGenRate: "+(new DecimalFormat("#.###").format(goldGenRate))+
						"  paikaGenRate: "+(new DecimalFormat("#.###").format(paikaGenRate)),
				x0+5, y0+45+(int)(laukumaAugstums*merogs));

	}


}
