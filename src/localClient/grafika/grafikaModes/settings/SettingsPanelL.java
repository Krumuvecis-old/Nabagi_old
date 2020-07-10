package localClient.grafika.grafikaModes.settings;

import localClient.ClientThread;
import localClient.grafika.Button;
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
    public void draw(Graphics g, ClientThread thread, SampleLayout layout, boolean diagnosticsInfo) {
        super.draw(g, thread, layout, diagnosticsInfo);

        drawContentPlaceHolder(g, thread.dati.grafikasDati.colorPalette.pair2[1]);
    }

    private void drawContentPlaceHolder(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2, XY[1] + size[1] / 2};
        g.drawString("Contents placeholder", textLocation[0], textLocation[1]);
    }
}
