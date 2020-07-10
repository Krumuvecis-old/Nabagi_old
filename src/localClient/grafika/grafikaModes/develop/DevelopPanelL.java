package localClient.grafika.grafikaModes.develop;

import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelL;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopPanelL extends PanelL {

    public DevelopPanelL(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Button1", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Button2", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Button3", 0));

        //buttonDetails.add(new Button.ButtonDetails(2, "Diagnostics", 0));
        //tablodraw, colorPanel
        //genRate +/-
        //thread.dati.grafikasDati.drawSampleImages = !thread.dati.grafikasDati.drawSampleImages

        //te var pievienot pogas

        generateButtons(layout);
    }

}
