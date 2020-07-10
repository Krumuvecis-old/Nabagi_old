package localClient.grafika.grafikaModes.setup;

import localClient.ClientThread;
import localClient.ColorPalette;
import localClient.grafika.grafikaModes.develop.*;
import localClient.grafika.grafikaParts.*;

import java.awt.*;

public class SetupDrawManager extends DrawManager{

    public SetupDrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette) {
        super(ekranaPlatums, ekranaAugstums, colorPalette);

        inputActions = new SetupInput();
        //te var piedefinçt atðíirîgu layout un fona krâsu

        header = new SetupHeader(layout, colorPalette.pair1);
        panelL = new SetupPanelL(layout, colorPalette.pair2);
        panelR = new SetupPanelR(layout, colorPalette.pair2);
        centerPanel = new SetupCenterPanel(layout, colorPalette.pair3);
        //te var piedefinçt atðíirîgus paneïus
    }

}
