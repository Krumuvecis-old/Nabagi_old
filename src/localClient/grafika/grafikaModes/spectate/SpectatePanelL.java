package localClient.grafika.grafikaModes.spectate;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelL;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SpectatePanelL extends PanelL {

    public SpectatePanelL(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Button1", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Button2", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Button3", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

}
