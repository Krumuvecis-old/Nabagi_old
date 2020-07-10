package localClient.grafika.grafikaModes.settings;

import localClient.ColorPalette;
import localClient.grafika.grafikaParts.DrawManager;

public class SettingsDrawManager extends DrawManager{

    public SettingsDrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette) {
        super(ekranaPlatums, ekranaAugstums, colorPalette);

        inputActions = new SettingsInput();
        //te var piedefin�t at��ir�gu layout un fona kr�su

        header = new SettingsHeader(layout, colorPalette.pair1);
        panelL = new SettingsPanelL(layout, colorPalette.pair2);
        panelR = new SettingsPanelR(layout, colorPalette.pair2);
        centerPanel = new SettingsCenterPanel(layout, colorPalette.pair3);
        //te var piedefin�t at��ir�gus pane�us
    }

}
