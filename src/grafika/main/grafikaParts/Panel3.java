package grafika.main.grafikaParts;

import server.userInterface.Button;
import server.userInterface.Dati;
import server.userInterface.Layout;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Panel3 {

    public SamplePanel samplePanel = new SamplePanel();

    Panel3(Layout layout){
        update(layout);
        generateButtons();
    }

    private void update(Layout layout){
        samplePanel.update(
                new int[]{layout.panel3ContentsX, layout.panel3ContentsY},
                new int[]{layout.panel3contentsWX, layout.panel3contentsWY}
        );

    }

    private void generateButtons(){
        samplePanel.buttonList = new ArrayList<>();

        String[][] buttonNames = {
                {"Zoom in", "zoomInPoga"},
                {"Zoom out", "zoomOutPoga"},
                {"Demo pictures", "zvaigznePoga"}
        };

        int buttonSpacing = 5;
        int[] buttonOffset = {buttonSpacing, buttonSpacing + 15},
                buttonSize = {100, 30};

        server.userInterface.Button.addButtonList(samplePanel, true,
                buttonOffset, true, true,
                buttonSize, buttonSpacing,
                buttonNames);
    }

    void draw(Graphics g, Dati dati){
        update(dati.layout);
        samplePanel.drawFons(g, dati.colorPalette.pair3[0], Color.black);

        if(dati.drawSampleImages) drawSampleImages(g, dati.images);
        drawSampleText(g, dati.colorPalette.pair3[1]);
        //te var likt papildus funkcijas

        Button.drawButtons(g, samplePanel);
    }


    //te var likt papildus funkcijas (jāizsauc no draw() metodes)


    private void drawSampleText(Graphics g, Color textColor){
        String text = "panelis3";
        int[] textOffset = {5, 15};

        int[] textXY = {samplePanel.XY[0] + textOffset[0], samplePanel.XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    private void drawSampleImages(Graphics g, HashMap<String, Image> images){

        int[] imageXYrelative = {300, 200},
                imageSize = {150, 150},
                imageXY = new int[]{imageXYrelative[0] + samplePanel.XY[0] - imageSize[0]/2,
                                    imageXYrelative[1] + samplePanel.XY[1] - imageSize[1]/2};
        g.drawImage(images.get("zvaigzne"), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);

        imageXYrelative = new int[]{500, 300};
        imageSize = new int[]{200, 200};
        imageXY = new int[]{imageXYrelative[0] + samplePanel.XY[0] - imageSize[0]/2,
                        imageXYrelative[1] + samplePanel.XY[1] - imageSize[1]/2};
        g.drawImage(images.get("banana"), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);

        imageXYrelative = new int[]{150, 300};
        imageSize = new int[]{30, 30};
        imageXY = new int[]{imageXYrelative[0] + samplePanel.XY[0] - imageSize[0]/2,
                imageXYrelative[1] + samplePanel.XY[1] - imageSize[1]/2};
        g.drawImage(images.get("banana"), imageXY[0], imageXY[1], imageSize[0], imageSize[1],null);
    }

}
