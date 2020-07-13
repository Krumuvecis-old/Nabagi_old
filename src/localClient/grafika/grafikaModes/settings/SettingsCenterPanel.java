package localClient.grafika.grafikaModes.settings;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SettingsCenterPanel extends CenterPanel {

    private int[] box1Loc, box2Loc;

    private static final int[]
            box1Size = new int[]{300, 100, 50}, //x, y, buttonY
            box1LabelOffset = new int[]{
                    box1Size[0] / 2 - 50, //x
                    (box1Size[1] - box1Size[2]) / 2}, //y
            box2Size = new int[]{box1Size[0], box1Size[1], box1Size[2]}, //x, y, buttonY
            box2LabelOffset = new int[]{
                    box2Size[0] / 2 - 51, //x
                    (box2Size[1] - box2Size[2]) / 2}; //y
    private static final String
            box1Label = "Select color palette:",
            box2Label = "Load/save settings:";
    private static final int
            box1ButtonCount = 3, box2ButtonCount = 2,
            buttonSeparation = 5;

    SettingsCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Brown/green", 4));
        buttonDetails.add(new Button.ButtonDetails(2, "Blue", 1));
        buttonDetails.add(new Button.ButtonDetails(3, "Red", -2));
        buttonDetails.add(new Button.ButtonDetails(4, "Load settings", 10));
        buttonDetails.add(new Button.ButtonDetails(5, "Save settings", 8));

        //te var pievienot pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        drawSettingsOptions(g, dati.grafikasDati.colorPalette.pair3[1]);
    }

    private void drawSettingsOptions(Graphics g, Color textColor){
        updateBoxLocations();
        buttonRepositioner();

        g.setColor(textColor);
        int[] textLocation = new int[]{
                XY[0] + box1Loc[0] + box1LabelOffset[0],
                XY[1] + box1Loc[1] + box1LabelOffset[1]};
        g.drawString(box1Label, textLocation[0], textLocation[1]);

        textLocation = new int[]{
                XY[0] + box2Loc[0] + box2LabelOffset[0],
                XY[1] + box2Loc[1] + box2LabelOffset[1]};
        g.drawString(box2Label, textLocation[0], textLocation[1]);
    }

    private void updateBoxLocations(){
        int boxSeparation = 10;
        box1Loc = new int[]{
                size[0] / 2 - box1Size[0] / 2,
                size[1] / 2 - (box1Size[1] + box2Size[1] + boxSeparation) / 2};
        box2Loc = new int[]{
                box1Loc[0],
                box1Loc[1] + box1Size[1] + boxSeparation};
    }

    private void buttonRepositioner(){
        int[] box1ButtonSize = new int[]{
                        (box1Size[0] - buttonSeparation) / 3 - buttonSeparation,
                        box1Size[2] - buttonSeparation * 2},
                box2ButtonSize = new int[]{
                        (box2Size[0] - buttonSeparation) / 2 - buttonSeparation,
                        box2Size[2] - buttonSeparation * 2};

        buttonRepositionerSupplement(box1ButtonCount, 0, box1Loc, box1Size, box1ButtonSize); //box1
        buttonRepositionerSupplement(box2ButtonCount, box1ButtonCount, box2Loc, box2Size, box2ButtonSize); //box2
    }

    private void buttonRepositionerSupplement(int buttonCount, int countOffset, int[] boxLoc, int[] boxSize, int[] buttonSize){
        for (int i = countOffset; i < buttonCount + countOffset; i++){
            buttonList.get(i).x = boxLoc[0] + buttonSeparation + (buttonSize[0] + buttonSeparation) * (i - countOffset);
            buttonList.get(i).y = boxLoc[1] + boxSize[2] + buttonSeparation;

            buttonList.get(i).wx = buttonSize[0];
            buttonList.get(i).wy = buttonSize[1];
        }
    }

}
