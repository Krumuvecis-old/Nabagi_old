package localClient.grafika.grafikaModes.settings;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.Header;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SettingsHeader extends Header {

    public SettingsHeader(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Main", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

}
