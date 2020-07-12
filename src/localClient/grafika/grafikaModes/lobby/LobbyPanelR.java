package localClient.grafika.grafikaModes.lobby;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelR;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class LobbyPanelR extends PanelR {

    public LobbyPanelR(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);
        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Start", 0));

        //te var pievienot pogas

        generateButtons(layout);

        drawKomanduInfo = true;
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        //te var izsaukt savas metodes
    }

    private void drawContentPlaceHolder(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Detailed player info placeholder", textLocation[0], textLocation[1]);
    }

    //te var pievienot savas metodes

}
