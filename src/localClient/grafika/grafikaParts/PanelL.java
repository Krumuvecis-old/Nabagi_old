package localClient.grafika.grafikaParts;

import localClient.CalculationTimeCalculator;
import localClient.ClientThread;
import localClient.ColorPalette;
import localClient.Dati;
import localClient.grafika.Button;

import java.awt.*;
import java.util.ArrayList;

public class PanelL extends SamplePanel {

    /*
     * šī klase raksturo default kreiso paneli
     *
     */

    public PanelL(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout), calculateSize(layout), colorPair);

        buttonDetails.add(new Button.ButtonDetails(1, "New client", 0));

        generateButtons(layout);
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.panelLX, layout.panelY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{
                Math.min(layout.panelLPlatums, Math.max(0, layout.ekranaPlatums - layout.panelLX - layout.panelROffset)),
                Math.max(0, layout.panelAugstums)};
    }

    public void generateButtons(SampleLayout layout){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {layout.panelLPlatums - buttonSpacing * 2, 30},
                buttonOffset = {buttonSpacing, buttonSpacing};

        Button.addButtonList(this, true,
                buttonOffset, true, true,
                buttonSize, buttonSpacing,
                buttonDetails);
    }

    public void draw(Graphics g, ClientThread thread, SampleLayout layout){
        super.draw(g,
                calculateLocation(layout),
                calculateSize(layout),
                thread.dati.grafikasDati.colorPalette.pair2);

        //te var likt papildus default funkcijas

        Button.drawButtons(g, this);
    }


    //te var likt papildus default funkcijas (jāizsauc no draw() metodes)


}
