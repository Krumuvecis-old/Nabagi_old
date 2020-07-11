package localClient.grafika.grafikaModes.lobby;

import localClient.grafika.grafikaParts.SampleLayout;

public class LobbyLayout extends SampleLayout {

    public LobbyLayout(int _ekranaPlatums, int _ekranaAugstums){
        super(_ekranaPlatums, _ekranaAugstums);

        panelLPlatums = 200;
        panelRPlatums = 500;

        updateCalculatedValues(_ekranaPlatums, _ekranaAugstums);
    }

}
