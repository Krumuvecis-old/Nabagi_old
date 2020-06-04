package grafika.player;

import java.awt.Color;
import java.awt.Graphics;

import konstantes.CalculationTimeCalculator;

class DrawSidePanels {
	
	private static PlayerThread thread;
	
	protected static void main(Graphics g, PlayerThread threadTemp) {
		thread=threadTemp;
		
		//te vajadz�tu grafisku noform�jumu
		
		//te vajadz�tu z�m�t inventory un status
		
		drawButtons(g);
		
		//te varb�t kaut k�da piel�dzin��ana
		
		if (thread.dati.diagnosticsPanelDraw) drawDiagnosticsPanel(g); //ievades diagnostikas panelis
	}
	
	
	private static void drawButtons(Graphics g) {
		
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
			g.fillRect(x, y, wx, wy); //z�m� pa�u pogu
			
			if (active) g.setColor(contourColorActive); else g.setColor(contourColorDefault);
			g.drawRect(x, y, wx, wy); //z�m� kont�ru
			
			String teksts=thread.dati.buttonList.get(i).title;
			int tekstaGarums=thread.dati.buttonList.get(i).title.length();
			int x0=x+wx/2-tekstaGarums*burtaPlatums/2, y0=y+wy/2+burtaAugstums/2, //teksta s�kumpunkts, lai teksts b�tu pogai pa vidu
					correction=thread.dati.buttonList.get(i).correction;
			
			g.setColor(textColorDefault);
			g.drawString(teksts, x0+correction, y0); //pogas nosaukums
			//g.drawRect(x0, y0-burtaAugstums, tekstaGarums*burtaPlatums, burtaAugstums); //r�m�tis ap tekstu
			
		}
		
	}
	
	private static void drawDiagnosticsPanel(Graphics g) {
		int panelPlatums=Dati.diagnosticPanelPlatums, panelAugstums=Dati.diagnosticPanelAugstums,
				x0=Math.min(thread.dati.ekranaPlatums-panelPlatums, thread.dati.kartePlatums+2*Dati.karteNobideX),
				y0=Math.max(0, thread.dati.ekranaAugstums-Dati.karteAtstatumsY-panelAugstums),
				yw=15, w=0;
		
		g.setColor(Dati.diagnosticsPanelColor);
		
		
		CalculationTimeCalculator calculationTimeCalculatorTemp=thread.dati.calculationTimeCalculator; //setup loga tehniskie parametri
		g.drawString("Grafikas parametri:", x0, y0+w*yw); w++; 
		System.out.println("CalcTimeMax("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
				calculationTimeCalculatorTemp.calculationTimeMax);
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
}
