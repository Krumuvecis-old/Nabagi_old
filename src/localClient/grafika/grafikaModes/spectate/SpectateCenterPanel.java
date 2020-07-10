package localClient.grafika.grafikaModes.spectate;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SpectateCenterPanel extends CenterPanel {

    SpectateCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Zoom in", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Zoom out", 0));

        //te var pievienot ppogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout, boolean sampleImages) {
        super.draw(g, dati, layout, sampleImages);

        drawContentPlaceHolder(g, dati.grafikasDati.colorPalette.pair3[1]);
    }

    private void drawContentPlaceHolder(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2, XY[1] + size[1] / 2};
        g.drawString("Contents placeholder", textLocation[0], textLocation[1]);
    }

}
