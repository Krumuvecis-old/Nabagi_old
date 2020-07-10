package localClient.grafika.grafikaModes.setup;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.Header;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SetupHeader extends Header {

    public SetupHeader(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Settings", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Develop", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

}
