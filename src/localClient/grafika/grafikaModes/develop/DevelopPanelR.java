package localClient.grafika.grafikaModes.develop;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelR;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopPanelR extends PanelR {

    ButtonBox buttonBox;

    public DevelopPanelR(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);
        clearButtons();

        buttonBox = new ButtonBox(); //overPanel toggles

        //te var pievienot pogas

        generateButtons(layout);

    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout){
        super.draw(g, dati, layout);

        buttonBox.update();

        if(layout.playerSelected) drawDetailedPlayerInfo(g, dati.grafikasDati.colorPalette.pair3[1]);
        else updateKomanduInfo();

        //te var izsaukt savas metodes
    }

    private void updateKomanduInfo(){
        int[] komanduInfoOffset = new int[]{10, 10};
        drawKomanduInfo = true;
        komanduInfoOffsetXY = new int[]{komanduInfoOffset[0], buttonBox.boxLoc[1] + buttonBox.boxSize[1] + komanduInfoOffset[1]};
    }

    private class ButtonBox {

        int[] boxLoc, boxSize;
        int[] boxSizeMin;

        private static final int buttonRowLenght = 2, buttonSeparation = 5;
        private int buttonCount, buttonRowCount;

        ButtonBox(){
            buttonDetails.add(new Button.ButtonDetails(1, "Client diagnostics toggle", 18));
            buttonDetails.add(new Button.ButtonDetails(2, "Server diagnostics toggle", 16));
            buttonDetails.add(new Button.ButtonDetails(3, "Sample images toggle", 4));
            buttonDetails.add(new Button.ButtonDetails(4, "Color wheel toggle", 0));

            buttonCount = buttonDetails.size();
            buttonRowCount = (int)Math.ceil((1.0 * buttonCount) / buttonRowLenght); //roundup division

            int[] buttonSizeMin = new int[]{0, 30};
            boxSizeMin = new int[]{
                    (buttonSizeMin[0] + buttonSeparation) * buttonRowLenght + buttonSeparation,
                    (buttonSizeMin[1] + buttonSeparation) * buttonRowCount + buttonSeparation};

        }

        void update(){
            buttonBox.boxSize = new int[]{
                    Math.max(buttonBox.boxSizeMin[0], size[0]), //stretch x
                    Math.min(buttonBox.boxSizeMin[1], size[1])}; //limited y
            buttonBox.boxLoc = new int[]{0, 0};

            buttonResizer();
        }

        private void buttonResizer(){
            int[] buttonSize = new int[]{
                    (boxSize[0] - buttonSeparation) / buttonRowLenght - buttonSeparation,
                    (boxSize[1] - buttonSeparation) / buttonRowCount - buttonSeparation};

            for(int i=0, j=0; j<buttonRowCount; i++){
                Button button = buttonList.get(i + j * buttonRowLenght);
                button.wx = buttonSize[0];
                button.wy = buttonSize[1];

                button.x = boxLoc[0] + buttonSeparation + (buttonSize[0] + buttonSeparation) * i;
                button.y = boxLoc[1] + buttonSeparation + (buttonSize[1] + buttonSeparation) * j;

                if(i >= buttonRowLenght - 1){
                    i = -1;
                    j++;
                }
            }
        }
    }

    private void drawDetailedPlayerInfo(Graphics g, Color textColor){
        drawKomanduInfo = false;
        g.setColor(textColor);
        int[] textLocation = new int[]{
                XY[0] + size[0] / 2,
                XY[1] + (buttonBox.boxSize[1] + size[1]) / 2};
        g.drawString("Detailed player info placeholder", textLocation[0] - 85, textLocation[1]);
    }

    //te var pievienot savas metodes
}
