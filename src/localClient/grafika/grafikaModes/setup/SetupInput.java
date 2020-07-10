package localClient.grafika.grafikaModes.setup;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.grafikaParts.InputActions;

public class SetupInput extends InputActions {

    @Override
    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.settings;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.develop;

            default -> System.out.println("ServerUIThread SetupHeader reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void centerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.lobby;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.spectate;

            default -> System.out.println("ServerUIThread SetupCenterPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

}
