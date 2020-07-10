package localClient.grafika.grafikaModes.spectate;

import localClient.ClientThread;
import localClient.ColorPalette;
import localClient.grafika.grafikaModes.develop.*;
import localClient.grafika.grafikaParts.DrawManager;

import java.awt.*;

public class SpectateDrawManager extends DrawManager{

    public SpectateDrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette) {
        super(ekranaPlatums, ekranaAugstums, colorPalette);

        inputActions = new SpectateInput();
        //te var piedefinçt atðíirîgu layout un fona krâsu

        header = new SpectateHeader(layout, colorPalette.pair1);
        footer = new SpectateFooter(layout, colorPalette.pair1);
        panelL = new SpectatePanelL(layout, colorPalette.pair2);
        panelR = new SpectatePanelR(layout, colorPalette.pair2);
        centerPanel = new SpectateCenterPanel(layout, colorPalette.pair3);
        //te var piedefinçt atðíirîgus paneïus
    }

}
