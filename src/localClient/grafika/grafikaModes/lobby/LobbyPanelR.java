package localClient.grafika.grafikaModes.lobby;

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

}
