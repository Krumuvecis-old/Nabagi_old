package localClient.grafika.grafikaModes.setup;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SetupCenterPanel extends CenterPanel {

    SetupCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Lobby", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Spectate", 0));

        //te var pievienot ppogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        buttonRepositioner(layout);

        drawWelcomeSign(g, dati.grafikasDati.colorPalette.pair3[1]);
    }

    private void buttonRepositioner(SampleLayout layout){
        buttonList.get(0).x = size[0] / 2 - buttonList.get(0).wx /2;
        buttonList.get(0).y = size[1] / 2 - 50;
        buttonList.get(1).x = buttonList.get(0).x;
        buttonList.get(1).y = buttonList.get(0).y + buttonList.get(0).wy + 5;
    }

    private void drawWelcomeSign(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 55, XY[1] + size[1] / 2 - 55};
        g.drawString("Welcome to Nabagi!", textLocation[0], textLocation[1]);
    }

}
