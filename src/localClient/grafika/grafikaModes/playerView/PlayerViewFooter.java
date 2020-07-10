package localClient.grafika.grafikaModes.playerView;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.Footer;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class PlayerViewFooter extends Footer {

    public PlayerViewFooter(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Pauze", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

}
