package localClient.grafika.grafikaParts;

import localClient.CalculationTimeCalculator;
import localClient.ClientThread;
import localClient.grafika.GrafikasDati;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class OverPanel {

    public void draw(Graphics g, ClientThread thread, SampleLayout layout){

        //varbût uz ðeieni jâpârnes drawLayoutGrid no drawManager

        if(thread.dati.grafikasDati.drawSampleImages)
            drawSampleImages(g, thread.dati.grafikasDati.images);
        if(thread.dati.grafikasDati.drawInputDiagnosticsPanel)
            drawInputDiagnosticsInfo(g, thread, thread.dati.grafikasDati.colorPalette.pair2[1], layout);
        if(thread.dati.grafikasDati.drawInputDiagnosticsPanel)
            drawCalculationTimes(g);
    }

    private void drawInputDiagnosticsInfo(Graphics g, ClientThread thread, Color textColor, SampleLayout layout) { //ieavades pârbaude un grafiskâ informâcija

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

        //piespiesto pogu izvade (klaviatûrai)
        String teksts;
        for (int i=0; i<thread.input.pogas.length; i++) {
            teksts="poga "+i+" = "+thread.input.pogas[i];
            g.drawString(teksts, x0, y0+w*yw); w++;
        }

    }

    private void drawCalculationTimes(Graphics g){
        //Calculation times display not implemented yed

        //zemâk kopçts no vecâ
//      g.setColor(thread.dati.tablo1krasa);
//
//		int nobideX=thread.dati.tablo1x0, nobideY=thread.dati.tablo1y0;
//		int tekstaPlatums=thread.dati.tablo1tekstaPlatums;
//
//		int w=0; //uzrakstîto rindu skaits
//
//		CalculationTimeCalculator calculationTimeCalculatorTemp=server.calculations.CalculationsThread.calculationTimeCalculator; //informâcija par galvenâ thread statusu
//		if (thread.dati.drawCalculationTime) {
//			g.drawString("Par aprçíinu Thread:", nobideX,w*tekstaPlatums+nobideY); w++;
//			g.drawString("CalcTimeMax("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
//					calculationTimeCalculatorTemp.calculationTimeMax, nobideX,w*tekstaPlatums+nobideY); w++;
//			g.drawString("TotalFrameTime("+calculationTimeCalculatorTemp.timeCalculationFrequency+"): "+
//					calculationTimeCalculatorTemp.totalFrameTimeMax, nobideX,w*tekstaPlatums+nobideY); w++;
//		}
//		g.drawString("Pauze: "+server.calculations.CalculationsThread.pauze,nobideX,w*tekstaPlatums+nobideY);w++;
//		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;

    }

//    private void drawColorPanel(Graphics g) {
//
//		int radiuss=thread.dati.colorPanelRadiuss,
//				centrsX=thread.dati.colorPanelX0+radiuss,
//				centrsY=thread.dati.colorPanelY0+radiuss;
//
//		g.setColor(thread.dati.colorPanelColor);
//		g.drawOval(centrsX-radiuss, centrsY-radiuss, 2*radiuss, 2*radiuss); //pelçks krâsu aplis
//
//		double[] colorList=KomanduApskats.komandasTakenColors();
//
//		for(int i=0; i<colorList.length;i++) {
//			g.setColor(new Color(Color.HSBtoRGB((float)colorList[i], 1, 1)));
//			g.drawLine(centrsX, centrsY,
//					(int)(centrsX+radiuss*Math.sin(2*Math.PI*colorList[i])),
//					(int)(centrsY-radiuss*Math.cos(2*Math.PI*colorList[i])));
//		}
//
//	}

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
