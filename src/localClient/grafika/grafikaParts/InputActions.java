package localClient.grafika.grafikaParts;

import localClient.ClientThread;
import localClient.Dati;
import localClient.FileHandler;
import localClient.grafika.Button;

import javax.swing.*;
import java.awt.*;

public class InputActions {

    //tikai paðas darbîbas

    public void keyboardActions(int numurs){
        switch (numurs) { //numurs - klaviatûrâ nospiestâs pogas numurs
            case 32 -> System.out.println("piespiests Space");
            case 87 -> System.out.println("piespiests W");
            case 65 -> System.out.println("piespiests A");
            case 83 -> System.out.println("piespiests S");
            case 68 -> System.out.println("piespiests D");
            default -> System.out.println("piespiesta nedefinçta keyboard poga");
        }
    }

    //idejas ko  regulçt ar pogâm:
    //calculations pauze
    //start playerview (lobby)
    //tablodraw, colorPanel (develop)
    //mapZoom (spectate, playerView?)
    //genRate +/- (develop)


    public void headerButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> thread.running = false;
            case 2 -> thread.grafika.ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);
            case 3 -> thread.grafika.ekrans.setState(Frame.ICONIFIED);
            case 4 -> System.out.println("nospiesta header ceturtâ poga");
            case 5 -> System.out.println("nospiesta header piektâ poga");
            case 6 -> System.out.println("nospiesta header sestâ poga");
            case 7 -> System.out.println("nospiesta header septîtâ poga");
            case 8 -> System.out.println("nospiesta header astotâ poga");
            case 9 -> System.out.println("nospiesta header devîtâ poga");

            default -> System.out.println("ServerUIThread Header reìistrçts nedefinçtas pogas notikums!");
        }
    }

    public void footerButtonActions(int reference, ClientThread thread){
        switch (reference) {case 1 -> thread.dati.grafikasDati.drawLayoutGrid = !thread.dati.grafikasDati.drawLayoutGrid;
            case 2 -> thread.dati.grafikasDati.drawInputDiagnosticsPanel = !thread.dati.grafikasDati.drawInputDiagnosticsPanel;
            case 3 -> thread.dati.grafikasDati.colorPalette.pickPreset(0);
            case 4 -> thread.dati.grafikasDati.colorPalette.pickPreset(1);
            case 5 -> thread.dati.grafikasDati.colorPalette.pickPreset(2);
            case 6 -> FileHandler.loadSettings(thread.dati, thread.grafika.ekrans);
            case 7 -> FileHandler.saveSettings(thread.dati);
            case 8 -> System.out.println("nospiesta footer astotâ poga");
            case 9 -> System.out.println("nospiesta footer devîtâ poga");

            default -> System.out.println("ServerUIThread Footer reìistrçts nedefinçtas pogas notikums!");
        }
    }

    public void leftButtonActions(int reference, ClientThread thread){
        switch (reference) {
            case 1 -> {
                //newClient(); //jauns klients
                System.out.println("neweClient placeholder");
            }
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
            case 1 -> System.out.println("nospiesta labâ paneïa pirmâ poga");
            case 2 -> System.out.println("nospiesta labâ paneïa otrâ poga");
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
            case 1 -> System.out.println("zoom++ placeholder");
            case 2 -> System.out.println("zoom-- placeholder");
            case 3 -> thread.dati.grafikasDati.drawSampleImages = !thread.dati.grafikasDati.drawSampleImages;
            case 4 -> System.out.println("nospiesta centrâlâ paneïa ceturtâ poga");
            case 5 -> System.out.println("nospiesta centrâlâ paneïa piektâ poga");
            case 6 -> System.out.println("nospiesta centrâlâ paneïa sestâ poga");
            case 7 -> System.out.println("nospiesta centrâlâ paneïa septîtâ poga");
            case 8 -> System.out.println("nospiesta centrâlâ paneïa astotâ poga");
            case 9 -> System.out.println("nospiesta centrâlâ paneïa devîtâ poga");

            default -> System.out.println("ServerUIThread CenterPanel reìistrçts nedefinçtas pogas notikums!");
        }
    }

}
