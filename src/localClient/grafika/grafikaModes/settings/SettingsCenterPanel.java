package localClient.grafika.grafikaModes.settings;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;
import localClient.grafika.grafikaParts.SamplePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SettingsCenterPanel extends CenterPanel {

    SettingsCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Palette1", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Palette2", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Palette3", 0));
        buttonDetails.add(new Button.ButtonDetails(4, "Load settings", 0));
        buttonDetails.add(new Button.ButtonDetails(5, "Save settings", 0));

        //te var pievienot ppogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        drawContentPlaceHolder(g, dati.grafikasDati.colorPalette.pair3[1]);
    }

    private void drawContentPlaceHolder(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Contents placeholder", textLocation[0], textLocation[1]);
    }

}
