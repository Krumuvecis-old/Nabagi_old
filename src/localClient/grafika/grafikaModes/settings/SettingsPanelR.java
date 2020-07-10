package localClient.grafika.grafikaModes.settings;

import localClient.Dati;
import localClient.grafika.grafikaParts.PanelR;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SettingsPanelR extends PanelR {

    public SettingsPanelR(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);
        clearButtons();

        //te var pievienot pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout){
        super.draw(g, dati, layout);

        drawBackgroundImage(g, dati);

        //te var izsaukt savas  metodes
    }

    //te var pievienot savas metodes

}
