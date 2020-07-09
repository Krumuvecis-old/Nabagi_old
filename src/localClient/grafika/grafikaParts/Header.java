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

    Header(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout), calculateSize(layout), colorPair);

        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.head1, "Exit1", 0));
        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.head2, "Maximize", 0));
        buttonDetails.add(new Button.ButtonDetails(Button.ActionReference.head3, "Minimize", 0));

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

    private void generateButtons(SampleLayout layout){
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

        Button.drawButtons(g, this);
        drawTitle(g, dati.grafikasDati.windowTitle, dati.grafikasDati.colorPalette.pair1[1], String.valueOf(dati.modeCurrent));
    }

    private void drawTitle(Graphics g, String windowTitle, Color textColor, String currentMode){
        String text = windowTitle + " - mode: " + currentMode;
        int[] textOffset = {5,15};

        int[] textXY = {XY[0] + textOffset[0], XY[1] + textOffset[1]};
        g.setColor(textColor);
        g.drawString(text, textXY[0], textXY[1]);
    }

    //te var likt papildus funkcijas (jāizsauc no draw() metodes)

}
