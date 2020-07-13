package localClient.grafika.grafikaModes.spectate;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaModes.spectate.spectateMap.SpectateMap;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SpectateCenterPanel extends CenterPanel {

    SpectateMap spectateMap;

    SpectateCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Zoom in", 1));
        buttonDetails.add(new Button.ButtonDetails(2, "Zoom out", 2));
        buttonDetails.add(new Button.ButtonDetails(3, "Reset zoom", 0));

        //te var pievienot pogas

        generateButtons(layout);

        buttonResizer();

        spectateMap = new SpectateMap();
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

        spectateMap.draw(g, dati,
                new int[]{layout.centerPanelContentsX, layout.centerPanelContentsY},
                new int[]{layout.centerPanelContentsWX, layout.centerPanelContentsWY}); //kartes zîmçðana

        Button.drawButtons(g, this); //vçlreiz uzzîmç pogas, lai tieðâm bûtu virs kartes

        //te var izsaukt savas metodes

    }

    //te var pievienot savas metodes



}
