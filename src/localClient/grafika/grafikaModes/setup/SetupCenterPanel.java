package localClient.grafika.grafikaModes.setup;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SetupCenterPanel extends CenterPanel {


    private static final double[] startingBoxSizeProportion = new double[]{0.75, 0.5},
            startingBoxOffset = new double[]{0, 0};

    private StartingBox startingBox;

    SetupCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);
        clearButtons();

        startingBox = new StartingBox();
        //te var pievienot citus satura boxus

        //te var pievienot vçl pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        //te var  izsaukt savu satura boxu  metodes

        startingBox.draw(g, dati);

        //te var izsaukt savas metodes
    }

    private class StartingBox {

        private static final int boxSeparation = 0;
        private static final double buttonBoxYProportion = 0.25;

        GreetingBox greetingBox;
        ButtonBox buttonBox;

        StartingBox(){
            greetingBox = new GreetingBox();
            buttonBox = new ButtonBox();
        }

        void draw(Graphics g, Dati dati){
            boxResizer();

            buttonBox.update();
            greetingBox.draw(g, dati);
        }

        private void boxResizer(){
            int[] boxSize = new int[]{ //calculate minimum boxSize
                    (int)(size[0] * startingBoxSizeProportion[0]),
                    (int)(size[1] * startingBoxSizeProportion[1])};

            buttonBox.boxSize = new int[]{
                    Math.max(buttonBox.boxSizeMin[0], boxSize[0]),
                    (int)Math.max(buttonBox.boxSizeMin[1], boxSize[1] * buttonBoxYProportion)};

            greetingBox.boxSize = new int[]{
                    Math.max(greetingBox.boxSizeMin[0], boxSize[0]),
                    Math.max(greetingBox.boxSizeMin[1], boxSize[1] - buttonBox.boxSize[1] - boxSeparation)};

            boxSize = new int[]{ //recalculate actual boxSize
                    Math.max(greetingBox.boxSize[0], buttonBox.boxSize[0]),
                    greetingBox.boxSize[1] + boxSeparation + buttonBox.boxSize[1]};

            int[] boxLoc = new int[]{
                    (int)(size[0] / 2 - boxSize[0] / 2 + size[0] * startingBoxOffset[0]),
                    (int)(size[1] / 2 - boxSize[1] / 2 + size[1] * startingBoxOffset[1])};

            greetingBox.boxLoc = new int[]{boxLoc[0], boxLoc[1]};
            buttonBox.boxLoc = new int[]{
                    boxLoc[0],
                    greetingBox.boxLoc[1] + greetingBox.boxSize[1] + boxSeparation};

        }
    }

    private class GreetingBox {

        private static final double greetingRatio = 2; // (width / height)
        private static final int greetingWidthMin = 100;

        final int[] boxSizeMin = new int[]{
                greetingWidthMin,
                (int)(greetingWidthMin / greetingRatio)};

        int[] boxLoc, boxSize;
        private int[] greetingLoc, greetingSize;

        GreetingBox(){};

        void draw(Graphics g, Dati dati){
            resizeGreeting();
            drawWelcomeSign(g, dati);
        }

        private void resizeGreeting(){
            int greetingWidth = Math.max(
                    greetingWidthMin,
                    (int)Math.min(
                            boxSize[0],
                            boxSize[1] * greetingRatio));

            greetingSize = new int[]{
                    greetingWidth,
                    (int)(greetingWidth / greetingRatio)};
            greetingLoc = new int[]{
                    boxSize[0] / 2 - greetingSize[0] / 2,
                    boxSize[1] / 2 - greetingSize[1] / 2};
        }

        private void drawWelcomeSign(Graphics g, Dati dati){
            int[] imageLocation = new int[]{
                    XY[0] + boxLoc[0] + greetingLoc[0],
                    XY[1] + boxLoc[1] + greetingLoc[1]};

            g.drawImage(dati.grafikasDati.images.get("welcomeSign"),
                    imageLocation[0], imageLocation[1], greetingSize[0], greetingSize[1],
                    null);
        }
    }

    private class ButtonBox {

        int[] boxLoc, boxSize;
        int[] boxSizeMin;

        private static final int buttonCount = 2, buttonSeparation = 10;

        ButtonBox(){
            buttonDetails.add(new Button.ButtonDetails(1, "Lobby", 0));
            buttonDetails.add(new Button.ButtonDetails(2, "Spectate", 5));

            int[] buttonSizeMin = new int[]{60, 30};
            boxSizeMin = new int[]{
                   (buttonSizeMin[0] + buttonSeparation) * buttonCount + buttonSeparation,
                   buttonSizeMin[1] + buttonSeparation * 2};

        }

        void update(){
            buttonResizer();
        }

        private void buttonResizer(){
            int[] buttonSize = new int[]{
                    (boxSize[0] - buttonSeparation) / buttonCount - buttonSeparation,
                    boxSize[1] - buttonSeparation * 2};

            for(int i=0; i<buttonList.size(); i++){
                buttonList.get(i).wx = buttonSize[0];
                buttonList.get(i).wy = buttonSize[1];

                buttonList.get(i).x = boxLoc[0] + buttonSeparation + (buttonSize[0] + buttonSeparation) * i;
                buttonList.get(i).y = boxLoc[1] + buttonSeparation;
            }
        }
    }

    //te var pievienot savas metodes

}
