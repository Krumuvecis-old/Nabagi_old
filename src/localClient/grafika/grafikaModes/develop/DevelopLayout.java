package localClient.grafika.grafikaModes.develop;

import localClient.grafika.grafikaParts.SampleLayout;

public class DevelopLayout extends SampleLayout {

    public DevelopLayout(int _ekranaPlatums, int _ekranaAugstums){
        super(_ekranaPlatums, _ekranaAugstums);

        panelRPlatums = 500;

        updateCalculatedValues(_ekranaPlatums, _ekranaAugstums);
    }

}
