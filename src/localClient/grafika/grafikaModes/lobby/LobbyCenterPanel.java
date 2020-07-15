package localClient.grafika.grafikaModes.lobby;

import localClient.Dati;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class LobbyCenterPanel extends CenterPanel {

    LobbyCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        //te var pievienot pogas

        generateButtons();
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        drawDetailedPlayerInfo(g, dati.grafikasDati.colorPalette.pair3[1]);

        //te var izsaukt savas metodes
    }

    private void drawDetailedPlayerInfo(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Player list placeholder", textLocation[0], textLocation[1]);
    }

    //te var pievienot savas metodes
}
