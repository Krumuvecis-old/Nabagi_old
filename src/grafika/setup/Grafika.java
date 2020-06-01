package grafika.setup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import komandas.KomanduApskats;
import konstantes.Formulas;
import konstantes.Parametri;

@SuppressWarnings("serial")
class Grafika extends JPanel {
	
	protected JFrame ekrans;
	private Grafika grafika;
	
	private static SetupThread threadTemp; //temporary lielums zîmçðanas funkcijâm
	
	private ArrayList<dataBase.Cilveks> cilvekiList = dataBase.Dati.cilvekiList;
	private ArrayList<dataBase.Komanda> komandasList = dataBase.Dati.komandasList;
	private ArrayList<dataBase.Lieta> lietasList = dataBase.Dati.lietas;
	
	//te nelikt mainîgos!!! (var likt pie thread.dati)
	
	protected void initialize(SetupThread thread) {
		threadTemp=thread;
		ekrans = new JFrame(thread.dati.windowTitle);
		ekrans.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		grafika = new Grafika();
		
		ekrans.getContentPane().add(BorderLayout.CENTER, grafika);
		ekrans.setResizable(true);
		ekrans.setSize(thread.dati.ekranaPlatums, thread.dati.ekranaAugstums);
		ekrans.setLocationByPlatform(true);
		
		ekrans.setVisible(true);
		
	}
	
	void main (SetupThread thread) {
		threadTemp=thread; //temporary update zîmçðanas cikliem, jo repaint() nepieòem argumentus
		
		ekrans.repaint();
	}
	
	protected void paintComponent(Graphics g) {
		
		drawFons(g);
		drawButtons(g);
		if (threadTemp.dati.miniMapDraw) drawMiniMap(g); //karte
		
		if (threadTemp.dati.tablo1Draw) drawTablo1(g); //galvenais komandu panelis sânâ
		if (threadTemp.dati.tablo2Draw) drawTablo2(g); //centrâlais panelis diagnostikai
		
		if (threadTemp.dati.colorPanelDraw) drawColorPanel(g); //komandu krâsu diagnostika
		if (threadTemp.dati.inputPanelDraw) drawInputTest(g); //ievades diagnostikas panelis
		
		
	}
	
	private void drawFons(Graphics g) {
		g.setColor(threadTemp.dati.fonaKrasa); // fons
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(threadTemp.dati.nosaukumaKrasa);
		g.drawString(threadTemp.dati.windowTitle,threadTemp.dati.nosaukumsX,threadTemp.dati.nosaukumsY);
	}
	
	private void drawTablo1(Graphics g) { //galvenais tablo zem pogâm
		
		g.setColor(threadTemp.dati.tablo1krasa);
		
		int nobideX=threadTemp.dati.tablo1x0, nobideY=threadTemp.dati.tablo1y0;
		int tekstaPlatums=threadTemp.dati.tablo1tekstaPlatums;
		
		int w=0; //uzrakstîto rindu skaits
		
		if (threadTemp.dati.drawFPS) {
			//g.drawString("FPSmin("+FPScalculator.FPScalculationFrequency+"): "+FPScalculator.FPSmin,0,w*tekstaPlatums+nobideY);w++;
			//g.drawString("calcTimeMax("+FPScalculator.FPScalculationFrequency+"): "+FPScalculator.calculationTimeMax,0,w*tekstaPlatums+nobideY);w++;
		}
		g.drawString("Pauze: "+galvenais.Main.pauze,nobideX,w*tekstaPlatums+nobideY);w++;
		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;
		
		//tekoðâ informâcija par komandâm
		g.drawString("vislielâkâ komanda: "+KomanduApskats.komanduVestureLielakaKomanda+" ("+KomanduApskats.komanduVestureMaksimums+")",nobideX,w*tekstaPlatums+nobideY);w++;
		g.drawString("tagad kopâ spçlçtâji: "+cilvekiList.size(),nobideX,w*tekstaPlatums+nobideY);w++;
		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;
		
		for (int i=0;i<komandasList.size();i++) {
			g.setColor(komandasList.get(i).krasa);
			g.drawString(komandasList.get(i).nosaukums+" - "+komandasList.get(i).skaits+" speletaji",nobideX,w*tekstaPlatums+nobideY);w++;
			g.drawString("karalis: "+komandasList.get(i).galvenais,nobideX,w*tekstaPlatums+nobideY);w++;
		}
		
		//vispârçja spçlçtâju statistika drusku zemâk
		g.setColor(threadTemp.dati.tablo1krasa);
		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;
		g.drawString("vecâkais: "+cilvekiList.get(0).vards,nobideX,w*tekstaPlatums+nobideY);w++;
		g.drawString("jaunâkais: "+cilvekiList.get(cilvekiList.size()-1).vards,nobideX,w*tekstaPlatums+nobideY);w++;
		
	}
	
	private void drawTablo2(Graphics g) {
		
		//tekoðâ informâcija par cilvçkiem
		
		int nobideY=threadTemp.dati.tablo2y0, rindasPlatums=threadTemp.dati.tablo2rindasPlatums,
				kolonna1x=threadTemp.dati.tablo2x0, kolonna2x=kolonna1x+threadTemp.dati.tablo2platums1,
				kolonnaNx=kolonna2x+threadTemp.dati.tablo2platums2, kolonnaNplatums=threadTemp.dati.tablo2platumsN;
		
		int w=1; //virsraksta rindu skaits
		
		String teksts1="Nosaukums (komanda), ...";
		String teksts2="invertory + orderi";
		String teksts3="Inventory";
		
		for(int i=(-1)*w;i<cilvekiList.size();i++) { //cikls, lai iziet caur cilvçkiem
			g.setColor(threadTemp.dati.tablo2krasaDefault);
			
			int invSize=0;
			
			if(!(i<0)) {
				teksts1=cilvekiList.get(i).vards + " (" + cilvekiList.get(i).komanda+") "+
						//"HP: "+(new DecimalFormat("#.##").format(cilvekiList.get(i).hp/cilvekiList.get(i).hpmax)+" ; "+
						"HP: " + (int)(cilvekiList.get(i).hp/cilvekiList.get(i).hpmax*100) + "% "+
						"paika: " + (int)(cilvekiList.get(i).paika/cilvekiList.get(i).paikaMax*100) + "% ";
				
				teksts2="invSize:"+cilvekiList.get(i).inventory.size()+" ; "+
						"orderi:"+cilvekiList.get(i).orderi.size();
				
				invSize=cilvekiList.get(i).inventory.size();
				
				
				if (cilvekiList.get(i).paika<cilvekiList.get(i).paikaMin-Parametri.paikaReductionDefault) {
					g.setColor(threadTemp.dati.tablo2krasaCritical);
				}
			}
			
			g.drawString(teksts1, kolonna1x, nobideY+rindasPlatums*(i+w));
			g.drawString(teksts2, kolonna2x, nobideY+rindasPlatums*(i+w));
			
			for(int j=0;j<Math.max(1,invSize);j++) { //iziet caur visu inventory
				
				
				if (!(i<0)) {
					if (invSize>0) {
						teksts3=cilvekiList.get(i).inventory.get(j).nosaukums+" "+(new DecimalFormat("#.##").format(cilvekiList.get(i).inventory.get(j).daudzums) );
					} else { teksts3=""; }
				}
				g.drawString(teksts3,kolonnaNx+kolonnaNplatums*j,nobideY+rindasPlatums*(i+w));
			}
		}
	}
	
	private void drawInputTest(Graphics g) {
		int x0=threadTemp.dati.inputPanelX, y0=threadTemp.dati.inputPanelY, yw=15, w=0;
		
		g.setColor(threadTemp.dati.inputPanelColor);
		g.drawString("x: "+threadTemp.input.xPele+" y: "+threadTemp.input.yPele, x0, y0+w*yw); w++;
		g.drawString("windowActive: " + threadTemp.windowActive, x0, y0+w*yw); w++;
		
		
		//piespiesto pogu izvade
		String teksts;
		for (int i=0; i<threadTemp.input.pogas.length; i++) {
			teksts="poga "+i+" = "+threadTemp.input.pogas[i];
			g.drawString(teksts, x0, y0+w*yw); w++;
		}
		
	}
	
	private void drawColorPanel(Graphics g) {
		
		int radiuss=threadTemp.dati.colorPanelRadiuss,
				centrsX=threadTemp.dati.colorPanelX0+radiuss,
				centrsY=threadTemp.dati.colorPanelY0+radiuss;
		
		g.setColor(threadTemp.dati.colorPanelColor);
		g.drawOval(centrsX-radiuss, centrsY-radiuss, 2*radiuss, 2*radiuss); //pelçks krâsu aplis
		
		double[] colorList=KomanduApskats.komandasTakenColors();
		
		for(int i=0; i<colorList.length;i++) {
			g.setColor(new Color(Color.HSBtoRGB((float)colorList[i], 1, 1)));
			g.drawLine(centrsX, centrsY,
					(int)(centrsX+radiuss*Math.sin(2*Math.PI*colorList[i])), 
					(int)(centrsY-radiuss*Math.cos(2*Math.PI*colorList[i])));
		}
		
	}
	
	private void drawButtons(Graphics g) {
		
		Color
			bodyColorDefault=Button.bodyColorDefault, bodyColorPressed=Button.bodyColorPressed, bodyColorActive=Button.bodyColorActive, 
			contourColorDefault=Button.contourColorDefault, contourColorPressed=Button.contourColorPressed, contourColorActive=Button.contourColorActive,
			textColorDefault=Button.textColorDefault, textColorPressed=Button.textColorPressed, textColorActive=Button.textColorActive;
		
		int burtaPlatums=Button.burtaPlatums, burtaAugstums=Button.burtaAugstums;
		
		for (int i=0;i<threadTemp.dati.buttonList.size();i++) {
			
			int x=threadTemp.dati.buttonList.get(i).x, y=threadTemp.dati.buttonList.get(i).y,
					wx=threadTemp.dati.buttonList.get(i).wx, wy=threadTemp.dati.buttonList.get(i).wy;
			boolean active=threadTemp.dati.buttonList.get(i).active, pressed=threadTemp.dati.buttonList.get(i).pressed;
			
			if (pressed) g.setColor(bodyColorPressed); else g.setColor(bodyColorDefault);
			g.fillRect(x, y, wx, wy); //zîmç paðu pogu
			
			if (active) g.setColor(contourColorActive); else g.setColor(contourColorDefault);
			g.drawRect(x, y, wx, wy); //zîmç kontûru
			
			String teksts=threadTemp.dati.buttonList.get(i).title;
			int tekstaGarums=threadTemp.dati.buttonList.get(i).title.length();
			int x0=x+wx/2-tekstaGarums*burtaPlatums/2, y0=y+wy/2+burtaAugstums/2, //teksta sâkumpunkts, lai teksts bûtu pogai pa vidu
					correction=threadTemp.dati.buttonList.get(i).correction;
			
			g.setColor(textColorDefault);
			g.drawString(teksts, x0+correction, y0); //pogas nosaukums
			//g.drawRect(x0, y0-burtaAugstums, tekstaGarums*burtaPlatums, burtaAugstums); //râmîtis ap tekstu
			
		}
		
	}
	
	private void drawMiniMap(Graphics g) {
		
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
		
		drawCilveki(g, x0, y0, merogs);
		drawLoot(g, x0, y0, merogs);
		
	}
	
	private void drawCilveki(Graphics g, int x0, int y0, double merogs) {
		
		double resnumaKoefic=Parametri.resnumaKoefic;
		for(int i=0;i<cilvekiList.size();i++) {
			
			//pats apskatâmais spçlçtâjs
			
			double resnums=resnumaKoefic*cilvekiList.get(i).hpmax*merogs;
			
			
			int komanda=0; //pçc  komandas nosaka krâsu
			for (int j=0;j<komandasList.size();j++) {
				if(cilvekiList.get(i).komanda==komandasList.get(j).nosaukums) {
					komanda=j;
					break;
				}
			}
			
			double hpRatio=cilvekiList.get(i).hp/cilvekiList.get(i).hpmax;
			
			Color krasa = new Color(Color.HSBtoRGB((float)Formulas.getHue(komandasList.get(komanda).krasa),
					(float)Parametri.cilvekiKrasaSaturation,
					(float)(Parametri.cilvekiKrasaBrightnessMin+(Parametri.cilvekiKrasaBrightnessMax-Parametri.cilvekiKrasaBrightnessMin)*hpRatio)));
			
			//rumpis
			
			double x=x0+cilvekiList.get(i).xyz.x*merogs, y=y0+cilvekiList.get(i).xyz.y*merogs;
			
			g.setColor(krasa); //iekða
			g.fillOval((int)(x-resnums/2),
					(int)(y-resnums/2),
					(int)resnums, (int)resnums); 
			g.setColor(Color.black);//kontûra
			g.drawOval((int)(x-resnums/2),
					(int)(y-resnums/2),
					(int)resnums, (int)resnums);
			
			if(cilvekiList.get(i).vards==komandasList.get(komanda).galvenais) { //karalis
				g.setColor(new Color(0,0,0)); //kroòa krâsa - melns punkts
				double kronaResnums=resnums/2;
				g.fillOval((int)(x-kronaResnums/2),
						(int)(y-kronaResnums/2),
						(int)kronaResnums, (int)kronaResnums); //kronis
			}
			
		}
		
	}
	
	private void drawLoot(Graphics g, int x0, int y0, double merogs) {
		
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
