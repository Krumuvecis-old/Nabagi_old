package localClient.grafika.grafikaModes.setup;

import localClient.Dati;
import localClient.grafika.grafikaParts.PanelR;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SetupPanelR extends PanelR {

    public SetupPanelR(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);
        clearButtons();

        //te var pievienot pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout){
        super.draw(g, dati, layout);

        drawContentPlaceHolder(g, dati.grafikasDati.colorPalette.pair2[1]);
    }

    private void drawContentPlaceHolder(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Contents placeholder", textLocation[0], textLocation[1]);
    }

}
