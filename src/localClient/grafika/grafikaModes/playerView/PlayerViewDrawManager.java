package localClient.grafika.grafikaModes.playerView;

import localClient.ColorPalette;
import localClient.grafika.grafikaParts.DrawManager;

public class PlayerViewDrawManager extends DrawManager{

    public PlayerViewDrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette) {
        super(ekranaPlatums, ekranaAugstums, colorPalette);

        inputActions = new PlayerViewInput();
        layout = new PlayerViewLayout(ekranaPlatums, ekranaAugstums);
        //te var piedefin�t ar� at��ir�gu fona kr�su

        header = new PlayerViewHeader(layout, colorPalette.pair1);
        footer = new PlayerViewFooter(layout, colorPalette.pair1);
        panelL = new PlayerViewPanelL(layout, colorPalette.pair2);
        panelR = new PlayerViewPanelR(layout, colorPalette.pair2);
        centerPanel = new PlayerViewCenterPanel(layout, colorPalette.pair3);
        //te var piedefin�t at��ir�gus pane�us
    }
}
