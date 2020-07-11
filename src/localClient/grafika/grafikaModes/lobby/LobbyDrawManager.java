package localClient.grafika.grafikaModes.lobby;

import localClient.ColorPalette;
import localClient.grafika.grafikaParts.DrawManager;

public class LobbyDrawManager extends DrawManager{

    public LobbyDrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette) {
        super(ekranaPlatums, ekranaAugstums, colorPalette);

        inputActions = new LobbyInput();
        layout = new LobbyLayout(ekranaPlatums, ekranaAugstums);
        //te var piedefin�t ar� at��ir�gu fona kr�su

        header = new LobbyHeader(layout, colorPalette.pair1);
        panelL = new LobbyPanelL(layout, colorPalette.pair2);
        panelR = new LobbyPanelR(layout, colorPalette.pair2);
        centerPanel = new LobbyCenterPanel(layout, colorPalette.pair3);
        //te var piedefin�t at��ir�gus pane�us

    }
}
