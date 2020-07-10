package localClient.grafika.grafikaModes.playerView;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.Header;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class PlayerViewHeader extends Header {

    public PlayerViewHeader(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Spectate", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Lobby", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

}
