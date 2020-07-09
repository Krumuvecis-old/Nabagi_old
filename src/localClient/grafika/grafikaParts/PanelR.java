package localClient.grafika.grafikaParts;

import localClient.grafika.Button;
import localClient.Dati;

import java.awt.*;
import java.util.ArrayList;

public class PanelR extends SamplePanel{

    /*
     * šī klase raksturo default labo paneli
     *
     */

    PanelR(SampleLayout layout){
        super(new int[]{layout.panelRX, layout.panelY},
                calculateSize(layout),
                new Color(0,0,0,255));

        generateButtons(layout, new String[][]{
                {"Poga1", "panel2poga1"},
                {"Poga2", "panel2poga2"},
                {"panel2poga3", "panel2poga3"},
                {"poga4 yeyeye", "panel2poga4"}});
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{
                Math.min(layout.panelRPlatums, Math.max(0, layout.ekranaPlatums - layout.panelRX)),
                Math.max(0, layout.panelAugstums)};
    }

    private void generateButtons(SampleLayout layout, String[][] buttonInfo){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {layout.panelRPlatums - buttonSpacing * 2, 30},
                buttonOffset = {buttonSpacing, buttonSpacing + 15};

        Button.addButtonList(this, true,
                buttonOffset, true, true,
                buttonSize, buttonSpacing,
                buttonInfo);
    }

    public void draw(Graphics g, Dati dati, SampleLayout layout, boolean sampleText){
        super.draw(g,
                new int[]{0, layout.headerY},
                calculateSize(layout));

        //te var likt papildus funkcijas

        Button.drawButtons(g, this);

        if (sampleText) drawSampleText(g, dati.grafikasDati.colorPalette.pair1[1]);

    }

    private void drawSampleText(Graphics g, Color textColor){
        String text = "labais panelis";
        int[] textOffset = {5,15};

        int[] textXY = {XY[0] + textOffset[0], XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    //te var likt papildus funkcijas (jāizsauc no draw() metodes)

}
