package localClient.grafika.grafikaModes.lobby;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.grafikaParts.InputActions;

public class LobbyInput extends InputActions {

    @Override
    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.setup;

            default -> System.out.println("ServerUIThread LobbyHeader reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void leftButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("Lobby mode left panel button 1");
            case 2 -> System.out.println("Lobby mode left panel button 2");
            case 3 -> System.out.println("Lobby mode left panel button 3");

            default -> System.out.println("ServerUIThread LobbyLeftPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void rightButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.playerView;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.spectate;

            default -> System.out.println("ServerUIThread LobbyRightPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

}
