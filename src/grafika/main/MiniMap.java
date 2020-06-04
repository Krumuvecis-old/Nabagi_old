package grafika.main;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

import konstantes.Formulas;
import konstantes.Parametri;
import calculations.cilveki.Cilveks;
import calculations.komandas.Komanda;
import calculations.lietas.Lieta;
class MiniMap {
	
	private static ArrayList<Cilveks> cilvekiList;
	private static ArrayList<Komanda> komandasList;
	private static ArrayList<Lieta> lietasList;
	
	private static SetupThread thread;
	
	protected static void drawMiniMap(Graphics g, SetupThread threadTemp, Grafika grafika) { //pilnîgi visa karte'
		
		cilvekiList = grafika.cilvekiList;
		komandasList = grafika.komandasList;
		lietasList = grafika.lietasList;
		
		thread = threadTemp;
		
		int x0=threadTemp.dati.miniMapX, y0=threadTemp.dati.miniMapY,
				platumsMax=Math.max(0, threadTemp.dati.miniMapPlatums),
				augstumsMax=Math.max(0, threadTemp.dati.miniMapAugstums);
		
		int laukumaPlatums=Parametri.platums, laukumaAugstums=Parametri.augstums, malaDefault=Parametri.mala;
		double merogs=Math.min((double)platumsMax/laukumaPlatums, (double)augstumsMax/laukumaAugstums);
		
		g.setColor(Parametri.malasKrasa);
		g.fillRect(x0, y0, (int)(laukumaPlatums*merogs), (int)(laukumaAugstums*merogs));
		g.setColor(Parametri.laukumaKrasa);
		g.fillRect((int)(x0+malaDefault*merogs), (int)(y0+malaDefault*merogs), (int)((laukumaPlatums-malaDefault*2)*merogs), (int)((laukumaAugstums-malaDefault*2)*merogs));
		
		if(threadTemp.dati.miniMapDrawInfo) {
			g.setColor(Color.white);
			g.drawString("xDef: "+laukumaPlatums+" yDef: "+laukumaAugstums,
					x0+5, y0+15+(int)(laukumaAugstums*merogs));
			g.drawString("wx: "+(int)(laukumaPlatums*merogs)+" wy: "+(int)(laukumaAugstums*merogs)+" merogs: "+(new DecimalFormat("#.##").format(merogs)),
					x0+5, y0+30+(int)(laukumaAugstums*merogs));
			g.drawString("genRate: "+(new DecimalFormat("#.###").format(Parametri.goldGenRate/2))+
					"  goldGen: "+(new DecimalFormat("#.###").format(Parametri.goldGenRate))+
					"  paikaGen: "+(new DecimalFormat("#.###").format(Parametri.paikaGenRate)),
					x0+5, y0+45+(int)(laukumaAugstums*merogs));
		}
		
		drawCilveki(g, x0, y0, merogs, cilvekiList, komandasList);
		drawLoot(g, x0, y0, merogs, lietasList);
		
	}
	
	private static void drawCilveki(Graphics g, int x0, int y0, double merogs, ArrayList<Cilveks> cilvekiList, ArrayList<Komanda> komandasList) { //papildinâjums kartei
		
		double resnumaKoefic=Parametri.resnumaKoefic;
		for(int i=0;i<cilvekiList.size();i++) {
			
			Cilveks cilveks = cilvekiList.get(i); //pats apskatâmais spçlçtâjs
			
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
					(float)Parametri.cilvekiKrasaSaturation,
					(float)(Parametri.cilvekiKrasaBrightnessMin+(Parametri.cilvekiKrasaBrightnessMax-Parametri.cilvekiKrasaBrightnessMin)*hpRatio)));
			
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
	
	private static void drawLoot(Graphics g, int x0, int y0, double merogs, ArrayList<Lieta> lietasList) {
		
		for(int i=0;i<lietasList.size();i++) { //zîmç lietas, kas izmçtâtas pa karti
			
			double x=x0+lietasList.get(i).x*merogs, y=y0+lietasList.get(i).y*merogs;
			
			if(lietasList.get(i).nosaukums=="Zelts") {
				double resnums=Parametri.zeltaResnums*merogs;
				
				g.setColor(Parametri.lietasColorZelts); //iekða
				g.fillOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
				g.setColor(Color.black); //kontûra
				g.drawOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
				
			} else if(lietasList.get(i).nosaukums=="Paika") {
				double resnums=Parametri.paikasResnums*merogs;
				
				g.setColor(Parametri.lietasColorPaika); //iekða
				g.fillOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
				g.setColor(Color.black); //kontûra
				g.drawOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
				
			} else { //neklasificçti objekti
				double resnums=Parametri.lietasResnums*merogs;
				
				g.setColor(Parametri.lietasColorDefault); //iekða
				g.fillOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
				g.setColor(Color.black); //kontûra
				g.drawOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
				
			}
			
		}
		
	}
	
	
}
