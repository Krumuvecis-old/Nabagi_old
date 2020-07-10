package localClient.grafika.grafikaParts;

import localClient.CalculationTimeCalculator;
import localClient.ClientThread;
import localClient.ColorPalette;

import java.awt.*;
import java.util.HashMap;

public abstract class DrawManager {

    public SampleLayout layout;
    public InputActions inputActions;

    public Header header;
    public Footer footer;
    public PanelL panelL;
    public PanelR panelR;
    public CenterPanel centerPanel;

    public boolean drawHeader = true, drawFooter = true,
            drawPanelL = true,  drawPanelR = true,
            drawCenterPanel = true,
            drawOverPanel = true;

    public DrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette){
        layout = new SampleLayout(ekranaPlatums, ekranaAugstums);
        inputActions = new InputActions();

        header = new Header(layout, colorPalette.pair1);
        footer = new Footer(layout, colorPalette.pair1);
        panelL = new PanelL(layout, colorPalette.pair2);
        panelR = new PanelR(layout, colorPalette.pair2);
        centerPanel = new CenterPanel(layout, colorPalette.pair3);
    }

    public void main(Graphics g, ClientThread thread){
        drawFons(g, thread);

        if(drawCenterPanel) centerPanel.draw(g, thread.dati, layout);

        if(drawPanelL) panelL.draw(g, thread, layout);
        if(drawPanelR) panelR.draw(g, thread.dati, layout);

        if(drawHeader) header.draw(g, thread.dati, layout);
        if(drawFooter) footer.draw(g, thread.dati, layout);

        if(thread.dati.grafikasDati.drawLayoutGrid) drawLayoutGrid(g, thread.dati.grafikasDati.layoutGridColor);

        if(drawOverPanel) drawOverPanel(g, thread, layout);

    }

    private void drawFons(Graphics g, ClientThread thread) {
        g.setColor(thread.dati.grafikasDati.backgroundColor);
        g.fillRect(0, 0, thread.grafika.grafika.getWidth(), thread.grafika.grafika.getHeight());
    }

    private void drawLayoutGrid(Graphics g, Color gridColor){
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0, layout.ekranaPlatums, layout.ekranaAugstums);


        g.setColor(gridColor);
        int[] textOffset = {5,15}, textXY;

        g.drawRect(header.XY[0], header.XY[1], header.size[0], header.size[1]); //header box
        textXY = new int[]{header.XY[0] + textOffset[0], header.XY[1] + textOffset[1]};
        g.drawString("LobbyHeader", textXY[0], textXY[1]);

        g.drawRect(panelL.XY[0], panelL.XY[1], panelL.size[0], panelL.size[1]); //panelL box
        textXY = new int[]{panelL.XY[0] + textOffset[0], panelL.XY[1] + textOffset[1]};
        g.drawString("Left panel", textXY[0], textXY[1]);

        g.drawRect(panelR.XY[0], panelR.XY[1], panelR.size[0], panelR.size[1]); //panelR box
        textXY = new int[]{panelR.XY[0] + textOffset[0], panelR.XY[1] + textOffset[1]};
        g.drawString("Right panel", textXY[0], textXY[1]);

        g.drawRect(centerPanel.XY[0], centerPanel.XY[1], centerPanel.size[0], centerPanel.size[1]); //centerPanel box
        textXY = new int[]{centerPanel.XY[0] + textOffset[0], centerPanel.XY[1] + textOffset[1]};
        g.drawString("Center panel", textXY[0], textXY[1]);
        g.drawRect(layout.centerPanelContentsX, layout.centerPanelContentsY,
                layout.centerPanelContentsWX, layout.centerPanelContentsWY); //centerPanelContents box

        g.drawRect(footer.XY[0], footer.XY[1], footer.size[0], footer.size[1]); //footer box
        textXY = new int[]{footer.XY[0] + textOffset[0], footer.XY[1] + textOffset[1]};
        g.drawString("PlayerViewFooter", textXY[0], textXY[1]);

    }

    private void drawOverPanel(Graphics g, ClientThread thread, SampleLayout layout){

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
