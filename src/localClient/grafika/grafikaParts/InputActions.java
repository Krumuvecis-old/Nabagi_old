package localClient.grafika.grafikaParts;

import localClient.ClientThread;
import localClient.Dati;
import localClient.FileHandler;

public class InputActions {

    //tikai paðas darbîbas

    public void keyboardActions(int numurs, ClientThread thread){
        switch (numurs) { //numurs - klaviatûrâ nospiestâs pogas numurs
            case 32 -> System.out.println("piespiests Space");
            case 87 -> System.out.println("piespiests W");
            case 65 -> System.out.println("piespiests A");
            case 83 -> System.out.println("piespiests S");
            case 68 -> System.out.println("piespiests D");
            default -> System.out.println("piespiesta nedefinçta keyboard poga");
        }
    }

    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("nospiesta header pirmâ poga");
            case 2 -> System.out.println("nospiesta header otrâ poga");
            case 3 -> System.out.println("nospiesta header treðâ poga");
            case 4 -> System.out.println("nospiesta header ceturtâ poga");
            case 5 -> System.out.println("nospiesta header piektâ poga");
            case 6 -> System.out.println("nospiesta header sestâ poga");
            case 7 -> System.out.println("nospiesta header septîtâ poga");
            case 8 -> System.out.println("nospiesta header astotâ poga");
            case 9 -> System.out.println("nospiesta header devîtâ poga");

            default -> System.out.println("ServerUIThread LobbyHeader reìistrçts nedefinçtas pogas notikums!");
        }
    }

    public void footerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("nospiesta footer pirmâ poga");
            case 2 -> System.out.println("nospiesta footer otrâ poga");
            case 3 -> System.out.println("nospiesta footer treðâ poga");
            case 4 -> System.out.println("nospiesta footer ceturtâ poga");
            case 5 -> System.out.println("nospiesta footer piektâ poga");
            case 6 -> System.out.println("nospiesta footer sestâ poga");
            case 7 -> System.out.println("nospiesta footer septîtâ poga");
            case 8 -> System.out.println("nospiesta footer astotâ poga");
            case 9 -> System.out.println("nospiesta footer devîtâ poga");

            default -> System.out.println("ServerUIThread PlayerViewFooter reìistrçts nedefinçtas pogas notikums!");
        }
    }

    public void leftButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("nospiesta kreisâ paneïa pirmâ poga");
            case 2 -> System.out.println("nospiesta kreisâ paneïa otrâ poga");
            case 3 -> System.out.println("nospiesta kreisâ paneïa treðâ poga");
            case 4 -> System.out.println("nospiesta kreisâ paneïa ceturtâ poga");
            case 5 -> System.out.println("nospiesta kreisâ paneïa piektâ poga");
            case 6 -> System.out.println("nospiesta kreisâ paneïa sestâ poga");
            case 7 -> System.out.println("nospiesta kreisâ paneïa septîtâ poga");
            case 8 -> System.out.println("nospiesta kreisâ paneïa astotâ poga");
            case 9 -> System.out.println("nospiesta kreisâ paneïa devîtâ poga");

            default -> System.out.println("ServerUIThread PanelLeft reìistrçts nedefinçtas pogas notikums!");
        }
    }

    public void rightButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.dati.modeCurrent = Dati.ModeOption.setup;
            case 2 -> thread.dati.modeCurrent = Dati.ModeOption.settings;
            case 3 -> System.out.println("nospiesta labâ paneïa treðâ poga");
            case 4 -> System.out.println("nospiesta labâ paneïa ceturtâ poga");
            case 5 -> System.out.println("nospiesta labâ paneïa piektâ poga");
            case 6 -> System.out.println("nospiesta labâ paneïa sestâ poga");
            case 7 -> System.out.println("nospiesta labâ paneïa septîtâ poga");
            case 8 -> System.out.println("nospiesta labâ paneïa astotâ poga");
            case 9 -> System.out.println("nospiesta labâ paneïa devîtâ poga");

            default -> System.out.println("ServerUIThread PanelRight reìistrçts nedefinçtas pogas notikums!");
        }
    }

    public void centerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> System.out.println("nospiesta centrâlâ paneïa pirmâ poga");
            case 2 -> System.out.println("nospiesta centrâlâ paneïa otrâ poga");
            case 3 -> System.out.println("nospiesta centrâlâ paneïa trçðâ poga");
            case 4 -> System.out.println("nospiesta centrâlâ paneïa ceturtâ poga");
            case 5 -> System.out.println("nospiesta centrâlâ paneïa piektâ poga");
            case 6 -> System.out.println("nospiesta centrâlâ paneïa sestâ poga");
            case 7 -> System.out.println("nospiesta centrâlâ paneïa septîtâ poga");
            case 8 -> System.out.println("nospiesta centrâlâ paneïa astotâ poga");
            case 9 -> System.out.println("nospiesta centrâlâ paneïa devîtâ poga");

            default -> System.out.println("ServerUIThread LobbyCenterPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

}
