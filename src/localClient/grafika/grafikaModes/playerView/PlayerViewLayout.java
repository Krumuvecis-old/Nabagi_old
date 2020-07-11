package localClient.grafika.grafikaModes.playerView;

import localClient.grafika.grafikaParts.SampleLayout;

public class PlayerViewLayout extends SampleLayout {

    public PlayerViewLayout(int _ekranaPlatums, int _ekranaAugstums){
        super(_ekranaPlatums, _ekranaAugstums);

        panelLPlatums = 300;
        panelRPlatums = 400;

        updateCalculatedValues(_ekranaPlatums, _ekranaAugstums);
    }

}
