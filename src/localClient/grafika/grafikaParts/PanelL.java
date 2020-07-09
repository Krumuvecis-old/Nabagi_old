package localClient.grafika.grafikaParts;

import localClient.CalculationTimeCalculator;
import localClient.ClientThread;
import localClient.ColorPalette;
import localClient.Dati;
import localClient.grafika.Button;

import java.awt.*;
import java.util.ArrayList;

public class PanelL extends SamplePanel {

    /*
     * šī klase raksturo default kreiso paneli
     *
     */

    PanelL(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout), calculateSize(layout), colorPair);

        generateButtons(layout, new String[][]{
                {"New client", "panel1poga1"}});
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.panelLX, layout.panelY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{
                Math.min(layout.panelLPlatums, Math.max(0, layout.ekranaPlatums - layout.panelLX - layout.panelROffset)),
                Math.max(0, layout.panelAugstums)};
    }

    private void generateButtons(SampleLayout layout, String[][] buttonInfo){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {layout.panelLPlatums - buttonSpacing * 2, 30},
                buttonOffset = {buttonSpacing, buttonSpacing + 15};

        Button.addButtonList(this, true,
                buttonOffset, true, true,
                buttonSize, buttonSpacing,
                buttonInfo);
    }

    public void draw(Graphics g, Dati dati, SampleLayout layout, ColorPalette colorPalette, boolean sampleText, boolean diagnosticsInfo, ClientThread thread){
        super.draw(g,
                calculateLocation(layout),
                calculateSize(layout),
                dati.grafikasDati.colorPalette.pair2);

        //te var likt papildus funkcijas

        Button.drawButtons(g, this);

        if (sampleText) drawSampleText(g, colorPalette.pair1[1]);
        if (diagnosticsInfo) drawInputDiagnosticsInfo(g, thread, colorPalette.pair2[1]);

    }

    private void drawSampleText(Graphics g, Color textColor){
        String text = "kreisais panelis";
        int[] textOffset = {5,15};

        int[] textXY = {XY[0] + textOffset[0], XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    private void drawInputDiagnosticsInfo(Graphics g, ClientThread thread, Color textColor) { //ieavades pārbaude un grafiskā informācija

        int[] textOffset = {5, 190};

        int x0 = XY[0] + textOffset[0],
                y0 = XY[1] + size[1] - textOffset[1],
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

        //piespiesto pogu izvade (klaviatūrai)
        String teksts;
        for (int i=0; i<thread.input.pogas.length; i++) {
            teksts="poga "+i+" = "+thread.input.pogas[i];
            g.drawString(teksts, x0, y0+w*yw); w++;
        }

    }

    //te var likt papildus funkcijas (jāizsauc no draw() metodes)


}
