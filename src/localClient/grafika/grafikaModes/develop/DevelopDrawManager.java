package localClient.grafika.grafikaModes.develop;

import localClient.ClientThread;
import localClient.ColorPalette;
import localClient.grafika.grafikaModes.setup.SetupInput;
import localClient.grafika.grafikaParts.DrawManager;

import java.awt.*;

public class DevelopDrawManager extends DrawManager{

    public DevelopDrawManager(int ekranaPlatums, int ekranaAugstums, ColorPalette colorPalette) {
        super(ekranaPlatums, ekranaAugstums, colorPalette);

        //te var piedefinçt atðíirîgu layout un fona krâsu

        //te var piedefinçt atðíirîgus paneïus
    }

    @Override
    public void main(Graphics g, ClientThread thread) {
        super.main(g, thread);

        //te var izsaukt savas zîmçðanas metodes

    }

    //te var definçt savas zîmçðanas metodes
}
