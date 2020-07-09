package localClient.grafika.grafikaModes.settings;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;
import localClient.grafika.grafikaParts.SamplePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SettingsCenterPanel extends CenterPanel {

    SettingsCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Zoom in", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Zoom out", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Demo pictures", 0));
        buttonDetails.add(new Button.ButtonDetails(4, "ExtraButton", 0));

        generateButtons(layout);
    }

}
