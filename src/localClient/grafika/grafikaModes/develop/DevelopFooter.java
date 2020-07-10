package localClient.grafika.grafikaModes.develop;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.Footer;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopFooter extends Footer {

    public DevelopFooter(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Layout grid", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Pauze", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Diagnostics panel", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

}
