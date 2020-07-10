package localClient.grafika.grafikaModes.develop;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopCenterPanel extends CenterPanel {

    DevelopCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Scroll up", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Scroll down", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Select", 0));

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
