package localClient.grafika.grafikaParts;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.GrafikasDati;
import server.calculations.CalculationsThread;

import java.awt.*;
import java.util.ArrayList;

public class Footer extends SamplePanel {

    /*
     * šī klase raksturo default footer paneli
     *
     */

    public boolean drawInfoAtTitle = true;

    public Footer(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout), calculateSize(layout), colorPair);

        //te var pievienot default pogas

        generateButtons(layout);
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.panelLX, layout.footerY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{
                Math.max(0, layout.ekranaPlatums - layout.panelLX - layout.panelROffset),
                Math.min(layout.footerAugstums, Math.max(0, layout.ekranaAugstums - layout.panelY - layout.footerOffset))};
    }

    public void generateButtons(SampleLayout layout){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {80, layout.footerAugstums - buttonSpacing * 2},
                buttonOffset = {buttonSpacing, buttonSpacing};

        Button.addButtonList(this, false,
                buttonOffset, true, false,
                buttonSize, buttonSpacing,
                buttonDetails);
    }

    public void draw(Graphics g, Dati dati, SampleLayout layout){
        super.draw(g,
                calculateLocation(layout),
                calculateSize(layout),
                dati.grafikasDati.colorPalette.pair1);

        //te var likt papildus default funkcijas

        Button.drawButtons(g, this);
        drawVersionInfo(g, dati);
    }

    private void drawVersionInfo(Graphics g, Dati dati){
        Color textColor = dati.grafikasDati.colorPalette.pair1[1];
        String currentMode = String.valueOf(dati.modeCurrent);

        String text;
        if(drawInfoAtTitle) text = dati.grafikasDati.windowTitle + " - server paused: " + CalculationsThread.pauze + " , graphics mode: " + currentMode;
        else text = GrafikasDati.programTitle;

        int[] textOffset = {5,15};

        int[] textXY = {XY[0] + textOffset[0], XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    //te var likt papildus default funkcijas (jāizsauc no draw() metodes)


}
