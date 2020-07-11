package localClient.grafika.grafikaModes.spectate;

import localClient.grafika.grafikaParts.SampleLayout;

public class SpectateLayout extends SampleLayout {

    public SpectateLayout(int _ekranaPlatums, int _ekranaAugstums){
        super(_ekranaPlatums, _ekranaAugstums);

        panelLPlatums = 200;
        panelRPlatums = 500;

        updateCalculatedValues(_ekranaPlatums, _ekranaAugstums);
    }

}
