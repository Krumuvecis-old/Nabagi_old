package localClient.grafika.grafikaModes.setup;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SetupCenterPanel extends CenterPanel {


    private static final double[] groupSizeProportion = new double[]{0.5, 0.5},
            groupOffset = new double[]{0, 0};


    private StartingBox startingBox;

    SetupCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);
        clearButtons();

        startingBox = new StartingBox();

        //te var pievienot vçl pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        startingBox.boxResizer();
        startingBox.buttonResizer();

        startingBox.drawWelcomeSign(g, dati);
    }

    private class StartingBox {

        private static final int boxSeparation = 0;

        private int[] greetingLoc, greetingSize;
        private static final double greetingRatio = 2; // (width / height)
        private static final int greetingWidthMin = 100;

        private int[] buttonBoxLoc, buttonBoxSize;
        private int buttonCount;
        private final int[] buttonSizeMin = new int[]{50, 30};
        private static final double buttonBoxYProportion = 0.3;
        private static final int buttonSeparation = 5;

        StartingBox(){
            buttonDetails.add(new Button.ButtonDetails(1, "Lobby", 0));
            buttonDetails.add(new Button.ButtonDetails(2, "Spectate", 5));
        }

        void boxResizer(){
            int[] groupSize = new int[]{
                    (int)(size[0] * groupSizeProportion[0]),
                    (int)(size[1] * groupSizeProportion[1])};

            buttonCount = buttonList.size();
            buttonBoxSize = new int[]{
                    Math.max(
                            (buttonSizeMin[0] + buttonSeparation) * buttonCount + buttonSeparation,
                            groupSize[0]),
                    Math.max(
                            buttonSizeMin[1] + buttonSeparation * 2,
                            (int)(groupSize[1] * buttonBoxYProportion - boxSeparation / 2))};

            int greetingWidth = Math.max(
                    greetingWidthMin,
                    (int)Math.min(
                            groupSize[0],
                            (groupSize[1] - buttonBoxSize[1]) * greetingRatio));

            greetingSize = new int[]{
                    greetingWidth,
                    (int)(greetingWidth / greetingRatio)};


            greetingLoc = new int[]{
                    (int)(size[0] / 2 - greetingSize[0] / 2 + size[0] * groupOffset[0]),
                    (int)(size[1] / 2 - groupSize[1] / 2 + size[1] * groupOffset[1])};

            buttonBoxLoc = new int[]{
                    (int)(size[0] / 2 - buttonBoxSize[0] / 2 + size[0] * groupOffset[0]),
                    greetingLoc[1] + greetingSize[1] + boxSeparation};

        }

        void buttonResizer(){
            int[] buttonSize = new int[]{
                    (buttonBoxSize[0] - buttonSeparation) / buttonCount - buttonSeparation,
                    buttonBoxSize[1] - buttonSeparation * 2};

            for(int i=0; i<buttonList.size(); i++){
                buttonList.get(i).wx = buttonSize[0];
                buttonList.get(i).wy = buttonSize[1];

                buttonList.get(i).x = buttonBoxLoc[0] + buttonSeparation + (buttonSize[0] + buttonSeparation) * i;
                buttonList.get(i).y = buttonBoxLoc[1] + buttonSeparation;
            }
        }

        void drawWelcomeSign(Graphics g, Dati dati){
            int[] imageLocation = {
                    XY[0] + greetingLoc[0],
                    XY[1] + greetingLoc[1]};

            g.drawImage(dati.grafikasDati.images.get("welcomeSign"), imageLocation[0], imageLocation[1], greetingSize[0], greetingSize[1], null);
        }
    }


}
