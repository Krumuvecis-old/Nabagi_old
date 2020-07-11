package localClient.grafika.grafikaModes.lobby;

import localClient.grafika.grafikaParts.SampleLayout;

public class LobbyLayout extends SampleLayout {

    public LobbyLayout(int _ekranaPlatums, int _ekranaAugstums){
        super(_ekranaPlatums, _ekranaAugstums);

        panelLPlatums = 150;
        panelRPlatums = 300;

        updateCalculatedValues(_ekranaPlatums, _ekranaAugstums);
    }

}
