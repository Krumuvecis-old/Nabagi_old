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

    public PanelR(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout),
                calculateSize(layout),
                colorPair);

        generateButtons(layout);
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.panelRX, layout.panelY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{
                Math.min(layout.panelRPlatums, Math.max(0, layout.ekranaPlatums - layout.panelRX)),
                Math.max(0, layout.panelAugstums)};
    }

    public void generateButtons(SampleLayout layout){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {layout.panelRPlatums - buttonSpacing * 2, 30},
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
                dati.grafikasDati.colorPalette.pair2);

        //te var likt papildus default funkcijas

        Button.drawButtons(g, this);

    }

    //te var likt papildus default funkcijas (jāizsauc no draw() metodes)

}
