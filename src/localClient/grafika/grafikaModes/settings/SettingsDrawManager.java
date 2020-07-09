package localClient.grafika.grafikaModes.settings;

import localClient.ClientThread;
import localClient.ColorPalette;
import localClient.grafika.grafikaParts.DrawManager;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SettingsDrawManager extends DrawManager{

    public SettingsDrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette) {
        super(ekranaPlatums, ekranaAugstums, colorPalette);

        centerPanel = new SettingsCenterPanel(layout, colorPalette.pair3);

        //te var piedefin�t at��ir�gu layout un fona kr�su

        //te var piedefin�t at��ir�gus pane�us
    }

    @Override
    public void main(Graphics g, ClientThread thread) {
        super.main(g, thread);

        //te var izsaukt savas z�m��anas metodes

    }

    //te var defin�t savas z�m��anas metodes
}
