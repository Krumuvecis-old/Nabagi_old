package localClient.grafika.grafikaModes.lobby;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.grafikaParts.InputActions;

public class LobbyInput extends InputActions {

    @Override
    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.setup;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.spectate;

            default -> super.headerButtonActions(reference, thread);
        }
    }

    @Override
    public void leftButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("Lobby mode left panel button 1");
            case 2 -> System.out.println("Lobby mode left panel button 2");
            case 3 -> System.out.println("Lobby mode left panel button 3");

            default -> super.leftButtonActions(reference, thread);
        }
    }

    @Override
    public void rightButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.playerView;

            //newClient(); //jauns klients
            //System.out.println("neweClient placeholder");

            default -> super.rightButtonActions(reference, thread);
        }
    }

}
