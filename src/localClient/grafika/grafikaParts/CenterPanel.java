package localClient.grafika.grafikaParts;

import localClient.grafika.Button;
import localClient.Dati;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CenterPanel extends SamplePanel {

    /*
     * šī klase raksturo default centrālo paneli
     *
     */

    CenterPanel(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout), calculateSize(layout), colorPair);

        generateButtons(layout, new String[][]{
                {"Zoom in", "zoomInPoga"},
                {"Zoom out", "zoomOutPoga"},
                {"Demo pictures", "zvaigznePoga"}});
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.centerPanelX, layout.panelY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{layout.centerPanelPlatums, layout.panelAugstums};
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


    void draw(Graphics g, Dati dati, SampleLayout layout, boolean sampleText, boolean sampleImages){
        super.draw(g,
                calculateLocation(layout),
                calculateSize(layout),
                dati.grafikasDati.colorPalette.pair3);

        if(sampleImages) drawSampleImages(g, dati.grafikasDati.images);
        //te var likt papildus funkcijas

        Button.drawButtons(g, this);
        if (sampleText) drawSampleText(g, dati.grafikasDati.colorPalette.pair1[1]);
    }

    private void drawSampleText(Graphics g, Color textColor){
        String text = "centrālais panelis";
        int[] textOffset = {5,15};

        int[] textXY = {XY[0] + textOffset[0], XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    //te var likt papildus funkcijas (jāizsauc no draw() metodes)



    private void drawSampleImages(Graphics g, HashMap<String, Image> images){

        int[] imageXYrelative = {300, 200},
                imageSize = {150, 150},
                imageXY = new int[]{imageXYrelative[0] + XY[0] - imageSize[0]/2,
                                    imageXYrelative[1] + XY[1] - imageSize[1]/2};
        g.drawImage(images.get("zvaigzne"), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);

        imageXYrelative = new int[]{500, 300};
        imageSize = new int[]{200, 200};
        imageXY = new int[]{imageXYrelative[0] + XY[0] - imageSize[0]/2,
                        imageXYrelative[1] + XY[1] - imageSize[1]/2};
        g.drawImage(images.get("banana"), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);

        imageXYrelative = new int[]{150, 300};
        imageSize = new int[]{30, 30};
        imageXY = new int[]{imageXYrelative[0] + XY[0] - imageSize[0]/2,
                imageXYrelative[1] + XY[1] - imageSize[1]/2};
        g.drawImage(images.get("banana"), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);
    }

}
