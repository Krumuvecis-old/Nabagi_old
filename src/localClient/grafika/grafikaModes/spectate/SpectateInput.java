package localClient.grafika.grafikaModes.spectate;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.grafikaParts.InputActions;
import server.calculations.CalculationsThread;

public class SpectateInput extends InputActions {

    @Override
    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.setup;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.lobby;

            default -> System.out.println("ServerUIThread SpectateHeader reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void footerButtonActions(int reference, ClientThread thread){

        switch (reference) {
            case 1 -> CalculationsThread.pauze = !CalculationsThread.pauze;

            default -> System.out.println("ServerUIThread SpectateFooter reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void leftButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("Spectate mode left panel button 1");
            case 2 -> System.out.println("Spectate mode left panel button 2");
            case 3 -> System.out.println("Spectate mode left panel button 3");

            default -> System.out.println("ServerUIThread SpectateLeftPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void rightButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("Spectate mode right panel button 1");
            case 2 -> System.out.println("Spectate mode right panel button 2");
            case 3 -> System.out.println("Spectate mode right panel button 3");

            default -> System.out.println("ServerUIThread SpectateRightPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

    @Override
    public void centerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("Spectate mode zoom in placeholder");
            case 2 -> System.out.println("Spectate mode zoom out placeholder");

            default -> System.out.println("ServerUIThread SpectateCenterPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }
}
