package localClient.grafika.grafikaParts;

import localClient.Dati;
import localClient.grafika.Button;

import java.awt.*;
import java.util.ArrayList;

public class Footer extends SamplePanel {

    /*
     * šī klase raksturo default footer paneli
     *
     */

    Footer(SampleLayout layout){
        super(new int[]{0, layout.footerY},
                calculateSize(layout),
                new Color(0,0,0,255));


        generateButtons(layout, new String[][]{
                {"SampleLayout grid", "foot1"},
                {"Diagnostics", "foot2"},
                {"Palette1", "palette0poga"},
                {"Palette2", "palette1poga"},
                {"Palette3", "palette2poga"},
                {"Load Settings", "loadSettingsPoga"},
                {"Save Settings", "saveSettingsPoga"}});
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{
                Math.max(0, layout.ekranaPlatums - layout.panelLX - layout.panelROffset),
                Math.min(layout.footerAugstums, Math.max(0, layout.ekranaAugstums - layout.panelY - layout.footerOffset))};
    }

    private void generateButtons(SampleLayout layout, String[][] buttonInfo){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {80, layout.footerAugstums - buttonSpacing * 2},
                buttonOffset = {buttonSpacing, buttonSpacing};

        Button.addButtonList(this, false,
                buttonOffset, true, false,
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
        String text = "footer";
        int[] textOffset = {5,15};

        int[] textXY = {XY[0] + textOffset[0], XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    //te var likt papildus funkcijas (jāizsauc no draw() metodes)


}
