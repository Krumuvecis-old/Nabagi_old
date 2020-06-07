package grafika.main;

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

class MiniMap {

	private static ArrayList<Komanda> komandasList;
	protected static ArrayList<ArrayList<MapChunk>> laukums;
	
	private static SetupThread thread;
	
	protected static void drawMiniMap(Graphics g, SetupThread threadTemp, Grafika grafika) { //pilnîgi visa karte

		komandasList = grafika.komandasList;
		laukums = Main.laukums;
		
		thread = threadTemp;
		
		int x0=threadTemp.dati.miniMapX, y0=threadTemp.dati.miniMapY,
				platumsMax=Math.max(0, threadTemp.dati.miniMapPlatums),
				augstumsMax=Math.max(0, threadTemp.dati.miniMapAugstums);
		
		int laukumaPlatums = KonstantesUniversal.laukumaPlatumsSum,
				laukumaAugstums=KonstantesUniversal.laukumaAugstumsSum,
				malaDefault=KonstantesUniversal.mala;
		double merogs=Math.min((double)platumsMax/laukumaPlatums, (double)augstumsMax/laukumaAugstums);
		
		g.setColor(Grafiskie.malasKrasa);
		g.fillRect(x0, y0, (int)(laukumaPlatums*merogs), (int)(laukumaAugstums*merogs));
		g.setColor(Grafiskie.laukumaKrasa);
		g.fillRect((int)(x0+malaDefault*merogs), (int)(y0+malaDefault*merogs), (int)((laukumaPlatums-malaDefault*2)*merogs), (int)((laukumaAugstums-malaDefault*2)*merogs));
		
		if(threadTemp.dati.miniMapDrawInfo) { //informâcija apakðâ par paðu karti
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
		
		drawCilveki(g, x0, y0, merogs, laukums, komandasList);
		drawLoot(g, x0, y0, merogs, laukums);
		
	}
	
	private static void drawCilveki(Graphics g, int x0, int y0, double merogs, ArrayList<ArrayList<MapChunk>> laukums, ArrayList<Komanda> komandasList) { //papildinâjums kartei

		
		double resnumaKoefic = Fizikas.resnumaKoefic;
		for(int i=0;i<thread.dati.cilvekuPilnaisList.size();i++) {
			
			Cilveks cilveks = Cilveks.getPlayer(thread.dati.cilvekuPilnaisList.get(i)); //pats apskatâmais spçlçtâjs
			
			double resnums=resnumaKoefic*cilveks.hpmax*merogs;
			
			
			int komanda=0; //pçc  komandas nosaka krâsu
			for (int j=0;j<komandasList.size();j++) {
				if(cilveks.komanda==komandasList.get(j).nosaukums) {
					komanda=j;
					break;
				}
			}
			
			double hpRatio=cilveks.hp/cilveks.hpmax;
			
			Color krasa = new Color(Color.HSBtoRGB((float)Formulas.getHue(komandasList.get(komanda).krasa),
					(float) KonstantesGrafikai.cilvekiKrasaSaturation,
					(float)(KonstantesGrafikai.cilvekiKrasaBrightnessMin+(KonstantesGrafikai.cilvekiKrasaBrightnessMax-KonstantesGrafikai.cilvekiKrasaBrightnessMin)*hpRatio)));
			
			//rumpis
			
			double x=x0+cilveks.xyz.x*merogs, y=y0+cilveks.xyz.y*merogs;
			
			g.setColor(krasa); //iekða
			g.fillOval((int)(x-resnums/2),
					(int)(y-resnums/2),
					(int)resnums, (int)resnums); 
			g.setColor(Color.black);//kontûra
			g.drawOval((int)(x-resnums/2),
					(int)(y-resnums/2),
					(int)resnums, (int)resnums);
			
			if(cilveks.vards==komandasList.get(komanda).galvenais) { //karalis
				g.setColor(new Color(0,0,0)); //kroòa krâsa - melns punkts
				double kronaResnums=resnums/2;
				g.fillOval((int)(x-kronaResnums/2),
						(int)(y-kronaResnums/2),
						(int)kronaResnums, (int)kronaResnums); //kronis
			}
			
			
			if(i==thread.dati.playerFocusNumber) { //fokusçtâ spçlçtâja redzesloks
				g.setColor(krasa);
				double R2temp=cilveks.R2*merogs;
				g.drawOval((int)(x-R2temp), (int)(y-R2temp), (int)(R2temp*2),(int)(R2temp*2)); //R2 - tâlais
			}
			
		}
		
	}
	
	private static void drawLoot(Graphics g, int x0, int y0, double merogs, ArrayList<ArrayList<MapChunk>> laukums) {
		for(int[] chunkXY={0,0}; chunkXY[0]<laukums.size(); chunkXY[0]++){
			for( ; chunkXY[1]<laukums.get(chunkXY[0]).size(); chunkXY[1]++){

				ArrayList<Lieta> lietasList = laukums.get(chunkXY[0]).get(chunkXY[1]).lietas;

				for(int i=0;i<lietasList.size();i++) { //zîmç lietas, kas izmçtâtas pa karti

					double x=x0+lietasList.get(i).x*merogs, y=y0+lietasList.get(i).y*merogs,
							resnums=KonstantesUniversal.defaultLietas.get(0).izmers;
					Color krasa1=KonstantesUniversal.defaultLietas.get(0).krasa, krasa2=Color.black; //iekðai un kontûrai

					for(int j=1; j<KonstantesUniversal.defaultLietas.size(); j++){
						if(lietasList.get(i).nosaukums.equals(KonstantesUniversal.defaultLietas.get(j).nosaukums)) {
							krasa1 = KonstantesUniversal.defaultLietas.get(j).krasa;
							resnums = KonstantesUniversal.defaultLietas.get(j).izmers * merogs;
							break;
						}
					}
					g.setColor(krasa1); //iekða
					g.fillOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
					g.setColor(krasa2); //kontûra
					g.drawOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);

				}

			}
		}


		
	}
	
	
}
