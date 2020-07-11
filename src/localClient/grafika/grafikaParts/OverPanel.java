package localClient.grafika.grafikaParts;

import localClient.CalculationTimeCalculator;
import localClient.ClientThread;
import localClient.grafika.GrafikasDati;
import server.calculations.komandas.Komanda;

import java.awt.*;
import java.util.HashMap;

public class OverPanel {

    public void draw(Graphics g, ClientThread thread, SampleLayout layout){

        //varb�t uz �eieni j�p�rnes drawLayoutGrid no drawManager

        if(thread.dati.grafikasDati.drawSampleImages)
            drawSampleImages(g, thread.dati.grafikasDati.images);
        if(thread.dati.grafikasDati.drawColorWheel)
            drawColorWheel(g, layout);
        if(thread.dati.grafikasDati.drawClientDiagnosticsInfo)
            drawClientDiagnosticsInfo(g, thread, thread.dati.grafikasDati.colorPalette.pair2[1], layout);
        if(thread.dati.grafikasDati.drawCalculationTime)
            drawCalculationTimes(g);
    }

    private void drawClientDiagnosticsInfo(Graphics g, ClientThread thread, Color textColor, SampleLayout layout) { //ieavades p�rbaude un grafisk� inform�cija

        int[] textOffset = {5, 200};

        int x0 = layout.panelLX + textOffset[0],
                y0 = layout.footerY - textOffset[1],
                yw = 15, w = 0;

        g.setColor(textColor);

        CalculationTimeCalculator calculationTimeCalculatorTemp = thread.calculationTimeCalculator; //setup loga tehniskie parametri
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

        //piespiesto pogu izvade (klaviat�rai)
        String teksts;
        for (int i=0; i<thread.input.pogas.length; i++) {
            teksts="poga "+i+" = "+thread.input.pogas[i];
            g.drawString(teksts, x0, y0+w*yw); w++;
        }

    }

    private void drawCalculationTimes(Graphics g){
        //Calculation times display not implemented yed

        //zem�k kop�ts no vec�
//      g.setColor(thread.dati.tablo1krasa);
//
//		int nobideX=thread.dati.tablo1x0, nobideY=thread.dati.tablo1y0;
//		int tekstaPlatums=thread.dati.tablo1tekstaPlatums;
//
//		int w=0; //uzrakst�to rindu skaits
//
//		CalculationTimeCalculator calculationTimeCalculatorTemp=server.calculations.CalculationsThread.calculationTimeCalculator; //inform�cija par galven� thread statusu
//		if (thread.dati.drawCalculationTime) {
//			g.drawString("Par apr��inu Thread:", nobideX,w*tekstaPlatums+nobideY); w++;
//			g.drawString("CalcTimeMax("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
//					calculationTimeCalculatorTemp.calculationTimeMax, nobideX,w*tekstaPlatums+nobideY); w++;
//			g.drawString("TotalFrameTime("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
//					calculationTimeCalculatorTemp.totalFrameTimeMax, nobideX,w*tekstaPlatums+nobideY); w++;
//		}
//		g.drawString("Pauze: "+server.calculations.CalculationsThread.pauze,nobideX,w*tekstaPlatums+nobideY);w++;
//		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;

    }

    private void drawColorWheel(Graphics g, SampleLayout layout) {

        int[] location = new int[]{10, 10}; // xy from bottom-right
		int radiuss = 50,
				centrsX = layout.centerPanelContentsX + layout.centerPanelContentsWX - radiuss - location[0] ,
				centrsY = layout.centerPanelContentsY + layout.centerPanelContentsWY - radiuss - location[1];

		g.setColor(Color.lightGray); // ap�a kont�ra

		g.drawOval(
		        centrsX - radiuss,
                centrsY - radiuss,
                2 * radiuss,
                2 * radiuss);

		double[] colorList = Komanda.komandasTakenColors();

		for(int i=0; i<colorList.length;i++) {
			g.setColor(new Color(Color.HSBtoRGB((float)colorList[i], 1, 1)));
			g.drawLine(centrsX, centrsY,
					(int)(centrsX+radiuss*Math.sin(2*Math.PI*colorList[i])),
					(int)(centrsY-radiuss*Math.cos(2*Math.PI*colorList[i])));
		}

	}

    private void drawSampleImages(Graphics g, HashMap<String, Image> images){

        int[] imageXYrelative = {300, 200},
                imageSize = {150, 150},
                imageXY = new int[]{imageXYrelative[0] - imageSize[0]/2,
                        imageXYrelative[1] - imageSize[1]/2};
        g.drawImage(images.get("zvaigzne"), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);

        imageXYrelative = new int[]{500, 300};
        imageSize = new int[]{200, 200};
        imageXY = new int[]{imageXYrelative[0] - imageSize[0]/2,
                imageXYrelative[1] - imageSize[1]/2};
        g.drawImage(images.get("banana"), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);

        imageXYrelative = new int[]{150, 300};
        imageSize = new int[]{30, 30};
        imageXY = new int[]{imageXYrelative[0] - imageSize[0]/2,
                imageXYrelative[1] - imageSize[1]/2};
        g.drawImage(images.get("banana"), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);

        imageXYrelative = new int[]{300, 400};
        imageSize = new int[]{100, 100};
        imageXY = new int[]{imageXYrelative[0] - imageSize[0]/2,
                imageXYrelative[1] - imageSize[1]/2};
        g.drawImage(GrafikasDati.rotateImage(images.get("banana"), 90), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);
    }
}
