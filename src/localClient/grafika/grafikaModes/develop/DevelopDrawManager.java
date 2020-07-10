package localClient.grafika.grafikaModes.develop;

import localClient.ColorPalette;
import localClient.grafika.grafikaParts.DrawManager;

public class DevelopDrawManager extends DrawManager{

    public DevelopDrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette) {
        super(ekranaPlatums, ekranaAugstums, colorPalette);

        inputActions = new DevelopInput();
        //te var piedefinçt atðíirîgu layout un fona krâsu

        header = new DevelopHeader(layout, colorPalette.pair1);
        footer = new DevelopFooter(layout, colorPalette.pair1);
        panelL = new DevelopPanelL(layout, colorPalette.pair2);
        panelR = new DevelopPanelR(layout, colorPalette.pair2);
        centerPanel = new DevelopCenterPanel(layout, colorPalette.pair3);
        //te var piedefinçt atðíirîgus paneïus

    }
}
