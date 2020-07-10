package localClient.grafika.grafikaModes.playerView;

import localClient.ClientThread;
import localClient.ColorPalette;
import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelL;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class PlayerViewPanelL extends PanelL {

    public PlayerViewPanelL(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Button1", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Button2", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Button3", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, ClientThread thread, SampleLayout layout) {
        super.draw(g, thread, layout);

        drawContentPlaceHolder(g, thread.dati.grafikasDati.colorPalette.pair2[1]);
    }

    private void drawContentPlaceHolder(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Contents placeholder", textLocation[0], textLocation[1]);
    }
}
