package localClient.grafika.grafikaParts;

import localClient.Dati;
import localClient.grafika.Button;

import java.awt.*;
import java.util.ArrayList;

public class Header extends SamplePanel {

    /*
     * šī klase raksturo default header paneli
     *
     */

    public Header(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout), calculateSize(layout), colorPair);

        //te var pievienot default pogas

        generateButtons(layout);
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.panelLX, layout.headerY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{
                Math.max(0, layout.ekranaPlatums - layout.panelLX - layout.panelROffset),
                Math.min(layout.headerAugstums, Math.max(0, layout.ekranaAugstums - layout.headerY - layout.footerOffset))};
    }

    public void generateButtons(SampleLayout layout){
        buttonList = new ArrayList<>();

        int buttonSpacing = 5;
        int[] buttonSize = {150, layout.headerAugstums - buttonSpacing * 2},
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
        drawTitleLogo(g, dati);
    }

    private void drawTitleLogo(Graphics g, Dati dati){
        int[] imageOffset = {0,0};

        double aspectRatio = 8;
        int[] imageXY = {XY[0] + imageOffset[0], XY[1] + imageOffset[1]},
        imageSize = {(int)(aspectRatio * size[1]), size[1]};
        g.drawImage(dati.grafikasDati.images.get("nabagiLogo"), imageXY[0], imageXY[1], imageSize[0], imageSize[1], null);
    }

    //te var likt papildus default funkcijas (jāizsauc no draw() metodes)

}
