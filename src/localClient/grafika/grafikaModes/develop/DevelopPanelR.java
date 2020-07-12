package localClient.grafika.grafikaModes.develop;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelR;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopPanelR extends PanelR {

    public DevelopPanelR(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);
        clearButtons();

        //overPanel toggles
        buttonDetails.add(new Button.ButtonDetails(1, "Client diagnostics toggle", 18));
        buttonDetails.add(new Button.ButtonDetails(2, "Server diagnostics toggle", 16));
        buttonDetails.add(new Button.ButtonDetails(3, "Sample images toggle", 4));
        buttonDetails.add(new Button.ButtonDetails(4, "Color wheel toggle", 0));

        //te var pievienot pogas

        generateButtons(layout);

        drawKomanduInfo = true;
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout){
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
