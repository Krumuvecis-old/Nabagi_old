package localClient.grafika.grafikaParts;

import localClient.Dati;
import localClient.grafika.Layout;
import localClient.grafika.Button;

import java.awt.*;
import java.util.ArrayList;

public class Header {

    public SamplePanel samplePanel = new SamplePanel();

    Header(Layout layout){
        update(layout);
        generateButtons(layout);
    }

    private void update(Layout layout){
        samplePanel.update(
                new int[]{0, layout.headerY},
                new int[]{layout.ekranaPlatums, layout.headerAugstums}
                );
    }

    private void generateButtons(Layout layout){
        samplePanel.buttonList = new ArrayList<>();

        String[][] buttonNames = {
                {"Exit", "head1"},
                {"Maximize", "head2"},
                {"Minimize", "head3"}
        };

        int buttonSpacing=5;
        int[] buttonSize = {150, layout.headerAugstums - buttonSpacing * 2},
                buttonOffset = {buttonSpacing, buttonSpacing};

        Button.addButtonList(samplePanel, false,
                buttonOffset, true, false,
                buttonSize, buttonSpacing,
                buttonNames);
    }

    void draw(Graphics g, Dati dati){
        update(dati.layout);
        samplePanel.drawFons(g, dati.colorPalette.pair1[0], Color.black);

        //te var likt papildus funkcijas

        Button.drawButtons(g, samplePanel);
        drawTitle(g, dati.windowTitle, dati.colorPalette.pair1[1]);
    }

    private void drawTitle(Graphics g, String windowTitle, Color textColor){
        String text = windowTitle + " header";
        int[] textOffset = {5,15};

        int[] textXY = {samplePanel.XY[0] + textOffset[0], samplePanel.XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    //te var likt papildus funkcijas (jāizsauc no draw() metodes)

}
