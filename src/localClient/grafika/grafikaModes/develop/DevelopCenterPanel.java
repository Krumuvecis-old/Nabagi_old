package localClient.grafika.grafikaModes.develop;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopCenterPanel extends CenterPanel {

    private ButtonBox buttonBox;
    private int[] tabloLoc;

    private ContentsMethods contentsMethods;

    DevelopCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonBox = new ButtonBox(); //overPanel toggles

        //te var pievienot pogas

        generateButtons(layout);

        contentsMethods = new ContentsMethods();
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        buttonBox.update();

        updateTabloInfo();

        //drawTablo

//		if (thread.dati.tablo2Draw) ContentsMethods.drawTablo2(g); //centrâlais panelis cilvçku diagnostikai
//		if (thread.dati.tablo3Draw) ContentsMethods.drawTablo3(g); //centrâlais panelis kartes diagnostikai

        contentsMethods.drawContentPlaceHolder(g, dati.grafikasDati.colorPalette.pair3[1], XY, size);

        //te var izsaukt savas metodes
    }

    private void updateTabloInfo(){
        int[] tabloDataOffset = new int[]{10, 10};
        tabloLoc = new int[]{tabloDataOffset[0], buttonBox.boxLoc[1] + buttonBox.boxSize[1] + tabloDataOffset[1]};
    }

    private class ButtonBox {

        int[] boxLoc, boxSize;
        int[] boxSizeMin;

        private static final int buttonSeparation = 5;
        private int buttonCount;

        ButtonBox(){
            buttonDetails.add(new Button.ButtonDetails(1, "Scroll up", 7));
            buttonDetails.add(new Button.ButtonDetails(2, "Scroll down", 6));
            buttonDetails.add(new Button.ButtonDetails(3, "Select", 3));
            buttonDetails.add(new Button.ButtonDetails(4, "Deselect", 2));

            buttonCount = buttonDetails.size();

            int[] buttonSizeMin = new int[]{80, 30};
            boxSizeMin = new int[]{
                    (buttonSizeMin[0] + buttonSeparation) * buttonCount + buttonSeparation,
                    buttonSizeMin[1] + buttonSeparation * 2};

        }

        void update(){
            buttonBox.boxSize = new int[]{
                    Math.min(buttonBox.boxSizeMin[0], size[0]), //limited x
                    Math.min(buttonBox.boxSizeMin[1], size[1])}; //limited y
            buttonBox.boxLoc = new int[]{0, 0};

            buttonResizer();
        }

        private void buttonResizer(){
            int[] buttonSize = new int[]{
                    (boxSize[0] - buttonSeparation) / buttonCount - buttonSeparation,
                    boxSize[1] - buttonSeparation * 2};

            for(int i=0; i<buttonCount; i++){
                Button button = buttonList.get(i);
                button.wx = buttonSize[0];
                button.wy = buttonSize[1];

                button.x = boxLoc[0] + buttonSeparation + (buttonSize[0] + buttonSeparation) * i;
                button.y = boxLoc[1] + buttonSeparation;
            }
        }
    }

    //te var pievienot savas metodes

}
