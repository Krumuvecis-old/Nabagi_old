package localClient.grafika.grafikaModes.spectate;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.Header;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SpectateHeader extends Header {

    public SpectateHeader(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Main", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Lobby", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

}
