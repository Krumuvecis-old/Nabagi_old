package grafika.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import calculations.CalculationTimeCalculator;
import calculations.Main;
import calculations.MapChunk;
import calculations.cilveki.Cilveks;
import calculations.komandas.Komanda;
import calculations.komandas.KomanduApskats;
import calculations.konstantes.Cilveku;
import calculations.lietas.Lieta;

@SuppressWarnings("serial")
class Grafika extends JPanel {
	protected JFrame ekrans;
	private Grafika grafika;
	
	private static SetupThread thread; //temporary lielums zîmçðanas funkcijâm

	protected ArrayList<Komanda> komandasList = calculations.Main.komandasList;

	
	//te nelikt mainîgos!!! (var likt pie thread.dati)
	
	protected void initialize(SetupThread threadTemp) {
		thread=threadTemp;
		ekrans = new JFrame(thread.dati.windowTitle);
		ekrans.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		grafika = new Grafika();
		
		ekrans.getContentPane().add(BorderLayout.CENTER, grafika);
		ekrans.setResizable(true);
		ekrans.setSize(thread.dati.ekranaPlatums, thread.dati.ekranaAugstums);
		//ekrans.setLocationByPlatform(true);
		ekrans.setLocation(10,10);
		
		ekrans.setVisible(true);
		
	}
	
	void main (SetupThread threadTemp) {
		thread=threadTemp; //temporary update zîmçðanas cikliem, jo repaint() nepieòem argumentus
		
		ekrans.repaint();
	}
	
	protected void paintComponent(Graphics g) {
		
		drawFons(g);
		drawButtons(g);
		if (thread.dati.miniMapDraw) MiniMap.drawMiniMap(g, thread, this); //karte
		
		if (thread.dati.tablo1Draw) drawTablo1(g); //galvenais komandu panelis sânâ
		if (thread.dati.tablo2Draw) drawTablo2(g); //centrâlais panelis diagnostikai
		
		if (thread.dati.colorPanelDraw) drawColorPanel(g); //komandu krâsu diagnostika
		if (thread.dati.inputPanelDraw) drawInputTest(g); //ievades diagnostikas panelis
		
		
	}
	
	private void drawFons(Graphics g) {
		g.setColor(thread.dati.fonaKrasa); // fons
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(thread.dati.nosaukumaKrasa);
		g.drawString(thread.dati.windowTitle, thread.dati.nosaukumsX, thread.dati.nosaukumsY);
	}
	
	private void drawTablo1(Graphics g) { //galvenais tablo pa labi no pogâm
		
		g.setColor(thread.dati.tablo1krasa);
		
		int nobideX=thread.dati.tablo1x0, nobideY=thread.dati.tablo1y0;
		int tekstaPlatums=thread.dati.tablo1tekstaPlatums;
		
		int w=0; //uzrakstîto rindu skaits
		
		CalculationTimeCalculator calculationTimeCalculatorTemp=calculations.Main.calculationTimeCalculator; //informâcija par galvenâ thread statusu
		if (thread.dati.drawCalculationTime) {
			g.drawString("Par aprçíinu Thread:", nobideX,w*tekstaPlatums+nobideY); w++;
			g.drawString("CalcTimeMax("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
					calculationTimeCalculatorTemp.calculationTimeMax, nobideX,w*tekstaPlatums+nobideY); w++;
			g.drawString("TotalFrameTime("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
					calculationTimeCalculatorTemp.totalFrameTimeMax, nobideX,w*tekstaPlatums+nobideY); w++;
		}
		g.drawString("Pauze: "+calculations.Main.pauze,nobideX,w*tekstaPlatums+nobideY);w++;
		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;
		
		//tekoðâ informâcija par komandâm
		g.drawString("vislielâkâ komanda: "+KomanduApskats.komanduVestureLielakaKomanda+" ("+KomanduApskats.komanduVestureMaksimums+")",nobideX,w*tekstaPlatums+nobideY);w++;
		g.drawString("tagad kopâ spçlçtâji: "+thread.dati.cilvekuPilnaisList.size(),nobideX,w*tekstaPlatums+nobideY);w++;
		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;
		
		for (int i=0;i<komandasList.size();i++) {
			g.setColor(komandasList.get(i).krasa);
			g.drawString(komandasList.get(i).nosaukums+" - "+komandasList.get(i).biedruList.size()+" speletaji",nobideX,w*tekstaPlatums+nobideY);w++;
			g.drawString("karalis: "+komandasList.get(i).galvenais,nobideX,w*tekstaPlatums+nobideY);w++;
		}
		
		//vispârçja spçlçtâju statistika drusku zemâk
		g.setColor(thread.dati.tablo1krasa);
		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;
		//g.drawString("vecâkais: "+cilvekiList.get(0).vards,nobideX,w*tekstaPlatums+nobideY);w++;
		//g.drawString("jaunâkais: "+cilvekiList.get(cilvekiList.size()-1).vards,nobideX,w*tekstaPlatums+nobideY);w++;
		g.drawString("kopâ bijuði spçlçtâji: "+Cilveks.maxCilveks,nobideX,w*tekstaPlatums+nobideY);w++;

	}
	
	private void drawTablo2(Graphics g) { //lielais diagnostikas logs
		
		//tekoðâ informâcija par cilvçkiem
		
		int nobideY=thread.dati.tablo2y0, rindasPlatums=thread.dati.tablo2rindasPlatums,
				kolonna1x=thread.dati.tablo2x0, kolonna2x=kolonna1x+thread.dati.tablo2platums1,
				kolonnaNx=kolonna2x+thread.dati.tablo2platums2, kolonnaNplatums=thread.dati.tablo2platumsN;
		
		int w=1; //virsraksta rindu skaits
		
		String teksts1="Nosaukums (komanda), ...";
		String teksts2="invertory + orderi";
		String teksts3="Inventory";
		
		for(int i=(-1)*w;i<thread.dati.cilvekuPilnaisList.size();i++) { //cikls, lai iziet caur cilvçkiem
			g.setColor(thread.dati.tablo2krasaDefault);
			Cilveks cilveks = Cilveks.getPlayer(thread.dati.cilvekuPilnaisList.get(i).chunkXY,thread.dati.cilvekuPilnaisList.get(i).i);

			int invSize=0;
			
			if(!(i<0)) {
				teksts1=cilveks.vards + " (" + cilveks.komanda+") "+
						//"HP: "+(new DecimalFormat("#.##").format(cilveks.hp/cilveks.hpmax)+" ; "+
						"HP: " + (int)(cilveks.hp/cilveks.hpmax*100) + "% "+
						"paika: " + (int)(cilveks.paika/cilveks.paikaMax*100) + "% ";
				
				teksts2="invSize:"+cilveks.inventory.size()+" ; "+
						"orderi:"+cilveks.orderi.size();
				
				invSize=cilveks.inventory.size();
				
				
				if (cilveks.paika<cilveks.paikaMin- Cilveku.paikaReductionDefault) {
					g.setColor(thread.dati.tablo2krasaCritical);
				}
			}
			
			g.drawString(teksts1, kolonna1x, nobideY+rindasPlatums*(i+w));
			g.drawString(teksts2, kolonna2x, nobideY+rindasPlatums*(i+w));
			
			for(int j=0;j<Math.max(1,invSize);j++) { //iziet caur visu inventory
				
				
				if (!(i<0)) {
					if (invSize>0) {
						teksts3=cilveks.inventory.get(j).nosaukums+" "+(new DecimalFormat("#.##").format(cilveks.inventory.get(j).daudzums) );
					} else { teksts3=""; }
				}
				g.drawString(teksts3,kolonnaNx+kolonnaNplatums*j,nobideY+rindasPlatums*(i+w));
			}
		}
	}
	
	private void drawInputTest(Graphics g) { //ieavades pârbaude un grafiskâ informâcija
		
		int x0=thread.dati.inputPanelX, y0=thread.dati.inputPanelY, yw=15, w=0;
		
		g.setColor(thread.dati.inputPanelColor);
		
		
		CalculationTimeCalculator calculationTimeCalculatorTemp=thread.dati.calculationTimeCalculator; //setup loga tehniskie parametri
		g.drawString("Grafikas parametri:", x0, y0+w*yw); w++; 
		g.drawString("CalcTimeMax("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
				calculationTimeCalculatorTemp.calculationTimeMax, x0, y0+w*yw); w++;
		g.drawString("TotalFrameTime("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
				calculationTimeCalculatorTemp.totalFrameTimeMax, x0, y0+w*yw); w++;
		
		g.drawString("MinFPS("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
				(1000/calculationTimeCalculatorTemp.totalFrameTimeMax), x0, y0+w*yw);w++;
		g.drawString("---------------",x0, y0+w*yw);w++;
		
		
		g.drawString("x: "+thread.input.xPele+" y: "+thread.input.yPele, x0, y0+w*yw); w++; //peles x un y
		g.drawString("windowActive: " + thread.windowActive, x0, y0+w*yw); w++;
		
		
		//piespiesto pogu izvade
		String teksts;
		for (int i=0; i<thread.input.pogas.length; i++) {
			teksts="poga "+i+" = "+thread.input.pogas[i];
			g.drawString(teksts, x0, y0+w*yw); w++;
		}
		
	}
	
	private void drawColorPanel(Graphics g) {
		
		int radiuss=thread.dati.colorPanelRadiuss,
				centrsX=thread.dati.colorPanelX0+radiuss,
				centrsY=thread.dati.colorPanelY0+radiuss;
		
		g.setColor(thread.dati.colorPanelColor);
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
		
		@SuppressWarnings("unused")
		Color
			bodyColorDefault=Button.bodyColorDefault, bodyColorPressed=Button.bodyColorPressed, bodyColorActive=Button.bodyColorActive, 
			contourColorDefault=Button.contourColorDefault, contourColorPressed=Button.contourColorPressed, contourColorActive=Button.contourColorActive,
			textColorDefault=Button.textColorDefault, textColorPressed=Button.textColorPressed, textColorActive=Button.textColorActive;
		
		int burtaPlatums=Button.burtaPlatums, burtaAugstums=Button.burtaAugstums;
		
		for (int i=0;i<thread.dati.buttonList.size();i++) {
			
			int x=thread.dati.buttonList.get(i).x, y=thread.dati.buttonList.get(i).y,
					wx=thread.dati.buttonList.get(i).wx, wy=thread.dati.buttonList.get(i).wy;
			boolean active=thread.dati.buttonList.get(i).active, pressed=thread.dati.buttonList.get(i).pressed;
			
			if (pressed) g.setColor(bodyColorPressed); else g.setColor(bodyColorDefault);
			g.fillRect(x, y, wx, wy); //zîmç paðu pogu
			
			if (active) g.setColor(contourColorActive); else g.setColor(contourColorDefault);
			g.drawRect(x, y, wx, wy); //zîmç kontûru
			
			String teksts=thread.dati.buttonList.get(i).title;
			int tekstaGarums=thread.dati.buttonList.get(i).title.length();
			int x0=x+wx/2-tekstaGarums*burtaPlatums/2, y0=y+wy/2+burtaAugstums/2, //teksta sâkumpunkts, lai teksts bûtu pogai pa vidu
					correction=thread.dati.buttonList.get(i).correction;
			
			g.setColor(textColorDefault);
			g.drawString(teksts, x0+correction, y0); //pogas nosaukums
			//g.drawRect(x0, y0-burtaAugstums, tekstaGarums*burtaPlatums, burtaAugstums); //râmîtis ap tekstu
			
		}
		
	}
	
	
}
