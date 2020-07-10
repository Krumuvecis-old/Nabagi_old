package localClient.grafika.grafikaModes.spectate;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.Footer;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SpectateFooter extends Footer {

    public SpectateFooter(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Pauze", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

}
