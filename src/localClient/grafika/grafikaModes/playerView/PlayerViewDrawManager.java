package localClient.grafika.grafikaModes.playerView;

import localClient.ClientThread;
import localClient.ColorPalette;
import localClient.grafika.grafikaParts.DrawManager;

import java.awt.*;

public class PlayerViewDrawManager extends DrawManager{

    public PlayerViewDrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette) {
        super(ekranaPlatums, ekranaAugstums, colorPalette);

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
