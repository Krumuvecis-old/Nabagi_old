package localClient.grafika.grafikaParts;

import localClient.CalculationTimeCalculator;
import localClient.ClientThread;

import java.awt.*;
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
    }

}
