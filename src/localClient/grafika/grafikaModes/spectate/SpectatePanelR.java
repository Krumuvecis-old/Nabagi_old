package localClient.grafika.grafikaModes.spectate;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelR;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SpectatePanelR extends PanelR {

    public SpectatePanelR(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);
        clearButtons();

        //te var pievienot pogas

        generateButtons(layout);

        drawKomanduInfo = true;
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout){
        super.draw(g, dati, layout);

        //te var izsaukt savas metodes
    }

    //te var pievienot savas metodes

}
