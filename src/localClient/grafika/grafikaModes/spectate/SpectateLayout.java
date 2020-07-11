package localClient.grafika.grafikaModes.spectate;

import localClient.grafika.grafikaParts.SampleLayout;

public class SpectateLayout extends SampleLayout {

    public SpectateLayout(int _ekranaPlatums, int _ekranaAugstums){
        super(_ekranaPlatums, _ekranaAugstums);

        panelLPlatums = 150;
        panelRPlatums = 250;

        updateCalculatedValues(_ekranaPlatums, _ekranaAugstums);
    }

}
