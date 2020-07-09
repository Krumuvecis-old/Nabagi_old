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

    Footer(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout), calculateSize(layout), colorPair);

        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.foot1, "Layout grid", 0));
        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.foot2, "Diagnostics", 0));
        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.foot3, "Palette1", 0));
        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.foot4, "Palette2", 0));
        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.foot5, "Palette3", 0));
        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.foot6, "Load Settings", 0));
        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.foot7, "Save Settings", 0));

        generateButtons(layout);
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.panelLX, layout.footerY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{
                Math.max(0, layout.ekranaPlatums - layout.panelLX - layout.panelROffset),
                Math.min(layout.footerAugstums, Math.max(0, layout.ekranaAugstums - layout.panelY - layout.footerOffset))};
    }

    private void generateButtons(SampleLayout layout){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {80, layout.footerAugstums - buttonSpacing * 2},
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

        //te var likt papildus funkcijas

        Button.drawButtons(g, this);
    }

    //te var likt papildus funkcijas (jāizsauc no draw() metodes)


}
