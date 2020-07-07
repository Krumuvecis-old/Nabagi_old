package localClient.grafikaParts;

import localClient.CalculationTimeCalculator;
import localClient.ClientThread;
import localClient.Layout;
import localClient.Button;

import java.awt.*;
import java.util.ArrayList;

public class Panel1 {

    public SamplePanel samplePanel = new SamplePanel();

    Panel1(Layout layout){
        update(layout);
        generateButtons();
    }

    private void update(Layout layout){
        samplePanel.update(
                new int[]{layout.panel1X, layout.panelY},
                new int[]{layout.panel1platums, layout.panelAugstums}
        );
    }

    private void generateButtons(){
        samplePanel.buttonList = new ArrayList<>();

        String[][] buttonNames = {
                {"New client", "panel1poga1"}
        };

        int buttonSpacing = 5;
        int[] buttonOffset = {buttonSpacing, buttonSpacing + 15},
                buttonSize = {100, 30};

        Button.addButtonList(samplePanel, true,
                buttonOffset,  true, true,
                buttonSize, buttonSpacing,
                buttonNames);
    }

    void draw(Graphics g, ClientThread thread){
        update(thread.dati.layout);
        samplePanel.drawFons(g, thread.dati.colorPalette.pair2[0], Color.black);

        drawSampleText(g, thread.dati.colorPalette.pair2[1]);
        //te var likt papildus funkcijas

        if (thread.dati.drawInputDiagnosticsPanel) drawInputDiagnosticsInfo(g, thread, thread.dati.colorPalette.pair2[1]);
        Button.drawButtons(g, samplePanel);
    }

    private void drawSampleText(Graphics g, Color textColor){
        String text = "panelis1";
        int[] textOffset = {5, 15};

        int[] textXY = {samplePanel.XY[0] + textOffset[0], samplePanel.XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    private void drawInputDiagnosticsInfo(Graphics g, ClientThread thread, Color textColor) { //ieavades pārbaude un grafiskā informācija

        int[] textOffset = {5, 190};

        int x0=samplePanel.XY[0] + textOffset[0],
                y0=samplePanel.XY[1] + samplePanel.size[1] - textOffset[1],
                yw=15, w=0;

        g.setColor(textColor);

        CalculationTimeCalculator calculationTimeCalculatorTemp = thread.dati.calculationTimeCalculator; //setup loga tehniskie parametri
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
