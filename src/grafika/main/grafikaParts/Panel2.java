package grafika.main.grafikaParts;

import grafika.main.Button;
import grafika.main.Dati;
import grafika.main.Layout;

import java.awt.*;
import java.util.ArrayList;

public class Panel2 {

    public SamplePanel samplePanel = new SamplePanel();

    Panel2(Layout layout){
        update(layout);
        generateButtons();
    }

    private void update(Layout layout){
        samplePanel.update(
                new int[]{layout.panel2X, layout.panelY},
                new int[]{layout.panel2platums, layout.panelAugstums}
        );
    }

    private void generateButtons(){
        samplePanel.buttonList = new ArrayList<>();

        String[][] buttonNames = {
                {"Poga1", "panel2poga1"},
                {"Poga2", "panel2poga2"},
                {"panel2poga3", "panel2poga3"},
                {"poga4 yeyeye", "panel2poga4"}
        };

        int buttonSpacing = 5;
        int[] buttonOffset = {buttonSpacing, buttonSpacing},
                buttonSize = {100, 30};

        Button.addButtonList(samplePanel, true,
                buttonOffset, false, false,
                buttonSize, buttonSpacing,
                buttonNames);
    }

    void draw(Graphics g, Dati dati){
        update(dati.layout);
        samplePanel.drawFons(g, dati.colorPalette.pair2[0], Color.black);

        drawSampleText(g, dati.colorPalette.pair2[1]);
        //te var likt papildus funkcijas

        Button.drawButtons(g, samplePanel);
    }

    private void drawSampleText(Graphics g, Color textColor){
        String text = "panelis2";
        int[] textOffset = {5, 15};

        int[] textXY = {samplePanel.XY[0] + textOffset[0], samplePanel.XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    //te var likt papildus funkcijas (jāizsauc no draw() metodes)



}
