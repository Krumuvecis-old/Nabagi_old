package localClient.grafika.grafikaModes.develop;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelL;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopPanelL extends PanelL {

    public DevelopPanelL(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Tablo1", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Tablo2", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Tablo3", 0));

        //genRate +/-

        //te var pievienot pogas

        generateButtons(layout);
    }

}
