package localClient.grafika.grafikaModes.spectate;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SpectateCenterPanel extends CenterPanel {

    SpectateCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Zoom in", 1));
        buttonDetails.add(new Button.ButtonDetails(2, "Zoom out", 2));

        //te var pievienot pogas

        generateButtons(layout);

        buttonResizer();
    }

    private void buttonResizer(){
        int[] buttonOffset = new int[]{0, 0},
                buttonSize = new int[]{80, 30};
        int buttonSeparation = 5;
        for (int i = 0; i<buttonList.size(); i++){
            Button button = buttonList.get(i);
            button.wx = buttonSize[0];
            button.wy = buttonSize[1];

            button.x = buttonOffset[0] + buttonSeparation;
            button.y = buttonOffset[1] + buttonSeparation + (buttonSize[1] + buttonSeparation) * i;
        }

    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);



        //te var izsaukt savas metodes

        //no vecâ:
        //if (thread.dati.miniMapDraw) SpectateMap.main(g, thread, this); //karte

        drawContentPlaceHolder(g, dati.grafikasDati.colorPalette.pair3[1]);

    }

    private void drawContentPlaceHolder(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Contents placeholder", textLocation[0], textLocation[1]);
    }

    //te var pievienot savas metodes



}
