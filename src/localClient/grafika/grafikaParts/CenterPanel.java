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

    public CenterPanel(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout), calculateSize(layout), colorPair);

        buttonDetails.add(new Button.ButtonDetails(1, "Zoom in", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Zoom out", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Demo pictures", 0));

        generateButtons(layout);
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.centerPanelX, layout.panelY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{layout.centerPanelPlatums, layout.panelAugstums};
    }

    public void generateButtons(SampleLayout layout){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {100, 30},
                buttonOffset = {buttonSpacing, buttonSpacing};

        Button.addButtonList(this, true,
                buttonOffset, true, true,
                buttonSize, buttonSpacing,
                buttonDetails);
    }


    public void draw(Graphics g, Dati dati, SampleLayout layout){
        super.draw(g,
                calculateLocation(layout),
                calculateSize(layout),
                dati.grafikasDati.colorPalette.pair3);

        //te var likt papildus default funkcijas

        Button.drawButtons(g, this);
    }

    //te var likt papildus default funkcijas (jāizsauc no draw() metodes)




}
