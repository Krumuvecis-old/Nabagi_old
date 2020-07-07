package grafika.main.grafikaParts;

import server.userInterface.Dati;
import server.userInterface.Layout;

import java.awt.*;
import java.util.ArrayList;

public class Footer {

    public SamplePanel samplePanel = new SamplePanel();

    Footer(Layout layout){
        update(layout);
        generateButtons(layout);
    }

    private void update(Layout layout){
        samplePanel.update(
                new int[]{0, layout.footerY},
                new int[]{layout.ekranaPlatums, layout.footerAugstums}
        );
    }

    private void generateButtons(Layout layout){
        samplePanel.buttonList = new ArrayList<>();

        String[][] buttonNames = {
                {"Layout grid", "foot1"},
                {"Diagnostics", "foot2"},
                {"Palette1", "palette0poga"},
                {"Palette2", "palette1poga"},
                {"Palette3", "palette2poga"},
                {"Load Settings", "loadSettingsPoga"},
                {"Save Settings", "saveSettingsPoga"}
        };

        int buttonSpacing=5;
        int[] buttonSize = {80, layout.footerAugstums - buttonSpacing * 2},
                buttonOffset = {buttonSpacing, buttonSpacing};

        Button.addButtonList(samplePanel, false,
                buttonOffset,  true, false,
                buttonSize, buttonSpacing,
                buttonNames);
    }

    void draw(Graphics g, Dati dati){
        update(dati.layout);
        samplePanel.drawFons(g, dati.colorPalette.pair1[0], Color.black);

        drawSampleText(g, dati.colorPalette.pair1[1]);
        //te var likt papildus funkcijas

        Button.drawButtons(g, samplePanel);
    }

    private void drawSampleText(Graphics g, Color textColor){
        String text = "footer";
        int[] textOffset = {5,15};

        int[] textXY = {samplePanel.XY[0] + textOffset[0], samplePanel.XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    //te var likt papildus funkcijas (jāizsauc no draw() metodes)


}
