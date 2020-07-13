package localClient.grafika.grafikaModes.develop;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelL;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopPanelL extends PanelL {

    public DevelopPanelL(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Tablo1 - par cilvçkiem", 17));
        buttonDetails.add(new Button.ButtonDetails(2, "Tablo2 - par komandâm", 8));
        buttonDetails.add(new Button.ButtonDetails(3, "Tablo3 - testiem", 9));

        //genRate +/-

        //te var pievienot pogas

        generateButtons(layout);
    }

}
