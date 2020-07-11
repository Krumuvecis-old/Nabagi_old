package localClient.grafika.grafikaModes.develop;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelL;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopPanelL extends PanelL {

    public DevelopPanelL(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Client diagnostics", 9));
        buttonDetails.add(new Button.ButtonDetails(2, "Server diagnostics", 9));
        buttonDetails.add(new Button.ButtonDetails(3, "Sample images", -1));

        //buttonDetails.add(new Button.ButtonDetails(2, "Diagnostics", 0));
        //tablodraw, colorPanel
        //genRate +/-

        //te var pievienot pogas

        generateButtons(layout);
    }

}
