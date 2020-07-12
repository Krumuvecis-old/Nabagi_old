package localClient.grafika.grafikaModes.develop;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.DrawManager;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopCenterPanel extends CenterPanel {

    private ButtonBox buttonBox;

    private Tablo tablo1, tablo2, tablo3;

    DevelopCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonBox = new ButtonBox(); //overPanel toggles

        //te var pievienot pogas

        generateButtons(layout);

        tablo1 = new Tablo1();
        tablo2 = new Tablo2();
        tablo3 = new Tablo3();
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        buttonBox.update();

        DrawManager.DevelopTabloInfo.TabloMode tabloCurrent =
                dati.drawManagerList.get(Dati.ModeOption.develop).developTabloInfo.tabloCurrent;

        switch (tabloCurrent){
            case tablo1 -> tablo1.draw(g, dati, XY, buttonBox.boxLoc[1], buttonBox.boxSize[1]); //cilvçku info
            case tablo2 -> tablo2.draw(g, dati, XY, buttonBox.boxLoc[1], buttonBox.boxSize[1]); //komandu info
            case tablo3 -> tablo3.draw(g, dati, XY, buttonBox.boxLoc[1], buttonBox.boxSize[1]); //tablo3

            default -> {}
        }

        //te var izsaukt savas metodes
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

    public static class Tablo {

        public String tabloName = "default";
        public int[] tabloLoc, tabloSize;

        private static final int lineHeight = 15;
        private static final int[] textOffset = new int[]{0, 0};

        public Tablo(){

        }

        public void draw(Graphics g, Dati dati, int[] panelXY, int boxY, int boxH){
            reposition(boxY, boxH);

            //te var izsaukt default tablo metodes

        }

        private void reposition(int boxY, int boxH){
            int[] tabloDataOffset = new int[]{10, 10};
            tabloLoc = new int[]{tabloDataOffset[0],
                    boxY + boxH + tabloDataOffset[1]};
        }

        public void drawTabloName(Graphics g, int[] XY, Color textColor){
            printLine(g, XY, 0,
                    "Contents placeholder, tablo name: " + tabloName
                    , textColor);
        }

        public void printLine(Graphics g, int[] panelXY, int w, String text, Color textColor){
            g.setColor(textColor);
            int x = panelXY[0] + tabloLoc[0] + textOffset[0],
                    y =  tabloLoc[1] + tabloLoc[1] + textOffset[1] + lineHeight * w;

            g.drawString(text, x, y);
        }

        //te var definçt default tablo metodes

    }

    //te var pievienot savas metodes

}
