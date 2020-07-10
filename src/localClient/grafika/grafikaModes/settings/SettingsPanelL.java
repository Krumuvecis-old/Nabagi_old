package localClient.grafika.grafikaModes.settings;

import localClient.ClientThread;
import localClient.grafika.grafikaParts.PanelL;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SettingsPanelL extends PanelL {

    public SettingsPanelL(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        //te var pievienot pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, ClientThread thread, SampleLayout layout) {
        super.draw(g, thread, layout);

        drawBackgroundImage(g, thread.dati);

        //te var izsaukt savas  metodes
    }

    //te var pievienot savas metodes
}
