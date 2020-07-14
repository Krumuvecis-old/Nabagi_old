package localClient.grafika.grafikaParts;

import localClient.ClientThread;
import localClient.Dati;
import localClient.FileHandler;

public class InputActions {

    //tikai pa�as darb�bas

    public void keyboardActions(int numurs, ClientThread thread){
        switch (numurs) { //numurs - klaviat�r� nospiest�s pogas numurs
            case 32 -> System.out.println("piespiests Space");
            case 87 -> System.out.println("piespiests W");
            case 65 -> System.out.println("piespiests A");
            case 83 -> System.out.println("piespiests S");
            case 68 -> System.out.println("piespiests D");
            default -> System.out.println("piespiesta nedefin�ta keyboard poga");
        }
    }

    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("nospiesta header pirm� poga");
            case 2 -> System.out.println("nospiesta header otr� poga");
            case 3 -> System.out.println("nospiesta header tre�� poga");
            case 4 -> System.out.println("nospiesta header ceturt� poga");
            case 5 -> System.out.println("nospiesta header piekt� poga");
            case 6 -> System.out.println("nospiesta header sest� poga");
            case 7 -> System.out.println("nospiesta header sept�t� poga");
            case 8 -> System.out.println("nospiesta header astot� poga");
            case 9 -> System.out.println("nospiesta header dev�t� poga");

            default -> System.out.println("ServerUIThread LobbyHeader re�istr�ts nedefin�tas pogas notikums!");
        }
    }

    public void footerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("nospiesta footer pirm� poga");
            case 2 -> System.out.println("nospiesta footer otr� poga");
            case 3 -> System.out.println("nospiesta footer tre�� poga");
            case 4 -> System.out.println("nospiesta footer ceturt� poga");
            case 5 -> System.out.println("nospiesta footer piekt� poga");
            case 6 -> System.out.println("nospiesta footer sest� poga");
            case 7 -> System.out.println("nospiesta footer sept�t� poga");
            case 8 -> System.out.println("nospiesta footer astot� poga");
            case 9 -> System.out.println("nospiesta footer dev�t� poga");

            default -> System.out.println("ServerUIThread PlayerViewFooter re�istr�ts nedefin�tas pogas notikums!");
        }
    }

    public void leftButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("nospiesta kreis� pane�a pirm� poga");
            case 2 -> System.out.println("nospiesta kreis� pane�a otr� poga");
            case 3 -> System.out.println("nospiesta kreis� pane�a tre�� poga");
            case 4 -> System.out.println("nospiesta kreis� pane�a ceturt� poga");
            case 5 -> System.out.println("nospiesta kreis� pane�a piekt� poga");
            case 6 -> System.out.println("nospiesta kreis� pane�a sest� poga");
            case 7 -> System.out.println("nospiesta kreis� pane�a sept�t� poga");
            case 8 -> System.out.println("nospiesta kreis� pane�a astot� poga");
            case 9 -> System.out.println("nospiesta kreis� pane�a dev�t� poga");

            default -> System.out.println("ServerUIThread PanelLeft re�istr�ts nedefin�tas pogas notikums!");
        }
    }

    public void rightButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.setup;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.settings;
            case 3 -> System.out.println("nospiesta lab� pane�a tre�� poga");
            case 4 -> System.out.println("nospiesta lab� pane�a ceturt� poga");
            case 5 -> System.out.println("nospiesta lab� pane�a piekt� poga");
            case 6 -> System.out.println("nospiesta lab� pane�a sest� poga");
            case 7 -> System.out.println("nospiesta lab� pane�a sept�t� poga");
            case 8 -> System.out.println("nospiesta lab� pane�a astot� poga");
            case 9 -> System.out.println("nospiesta lab� pane�a dev�t� poga");

            default -> System.out.println("ServerUIThread PanelRight re�istr�ts nedefin�tas pogas notikums!");
        }
    }

    public void centerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("nospiesta centr�l� pane�a pirm� poga");
            case 2 -> System.out.println("nospiesta centr�l� pane�a otr� poga");
            case 3 -> System.out.println("nospiesta centr�l� pane�a tr��� poga");
            case 4 -> System.out.println("nospiesta centr�l� pane�a ceturt� poga");
            case 5 -> System.out.println("nospiesta centr�l� pane�a piekt� poga");
            case 6 -> System.out.println("nospiesta centr�l� pane�a sest� poga");
            case 7 -> System.out.println("nospiesta centr�l� pane�a sept�t� poga");
            case 8 -> System.out.println("nospiesta centr�l� pane�a astot� poga");
            case 9 -> System.out.println("nospiesta centr�l� pane�a dev�t� poga");

            default -> System.out.println("ServerUIThread LobbyCenterPanel re�istr�ts nedefin�tas pogas notikums!");
        }
    }

}
