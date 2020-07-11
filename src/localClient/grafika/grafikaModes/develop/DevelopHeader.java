package localClient.grafika.grafikaModes.develop;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.Header;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopHeader extends Header {

    public DevelopHeader(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Main", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Spectate", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

}
