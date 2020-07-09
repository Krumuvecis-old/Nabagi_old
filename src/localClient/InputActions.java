package localClient;

import javax.swing.*;
import java.awt.*;

public class InputActions {

    //tikai paðas darbîbas

    private static final String modeDefault = "default"; //idejas kâ sataisît reþîmus, vçl jâpârnes uz Dati vai clientThread vai citur

    public static void keyboardActions(int numurs, String mode){
        if (mode.equals(modeDefault)){
            switch (numurs) { //numurs - klaviatûrâ nospiestâs pogas numurs
                case 32 -> System.out.println("piespiests Space");
                case 87 -> System.out.println("piespiests W");
                case 65 -> System.out.println("piespiests A");
                case 83 -> System.out.println("piespiests S");
                case 68 -> System.out.println("piespiests D");
                default -> System.out.println("piespiesta nedefinçta keyboard poga");
            }
        } else {
            System.out.println("ðajâ reþîmâ poga nedarbojas");
        }

    }

    public static void buttonActions(String reference, ClientThread thread, String mode) {
        switch (mode) {
            case modeDefault -> defaultButtons(reference, thread);
            default -> System.out.println("Buttons for this mode have undefined actions!");
        }
    }

    private static void defaultButtons(String reference, ClientThread thread) {
        switch (reference) {

            //zemâk header pogu notikumi

            case "head1" -> thread.running = false;
            case "head2" -> thread.grafika.ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);
            case "head3" -> thread.grafika.ekrans.setState(Frame.ICONIFIED);
            case "head4" -> System.out.println("nospiesta header ceturtâ poga");
            case "head5" -> System.out.println("nospiesta header piektâ poga");
            case "head6" -> System.out.println("nospiesta header sestâ poga");
            case "head7" -> System.out.println("nospiesta header septîtâ poga");

            //zemâk footer pogu notikumi

            case "foot1" -> thread.dati.drawLayoutGrid = !thread.dati.drawLayoutGrid;
            case "foot2" -> thread.dati.drawInputDiagnosticsPanel = !thread.dati.drawInputDiagnosticsPanel;
            case "palette0poga" -> thread.dati.colorPalette.pickPreset(0);
            case "palette1poga" -> thread.dati.colorPalette.pickPreset(1);
            case "palette2poga" -> thread.dati.colorPalette.pickPreset(2);
            case "loadSettingsPoga" -> FileHandler.loadSettings(thread.dati, thread.grafika.ekrans);
            case "saveSettingsPoga" -> FileHandler.saveSettings(thread.dati);

            //zemâk pirmâ paneïa pogu notikumi

            case "panel1poga1" -> {
                //newClient(); //jauns klients
                System.out.println("neweClient placeholder");
            }
            case "panel1poga2" -> System.out.println("nospiesta panel1 otrâ poga");
            case "panel1poga3" -> System.out.println("nospiesta panel1 treðâ poga");
            case "panel1poga4" -> System.out.println("nospiesta panel1 ceturtâ poga");
            case "panel1poga5" -> System.out.println("nospiesta panel1 piektâ poga");

            //zemâk otrâ paneïa pogu notikumi

            case "panel2poga1" -> System.out.println("nospiesta panel2 pirmâ poga");
            case "panel2poga2" -> System.out.println("nospiesta panel2 otrâ poga");
            case "panel2poga3" -> System.out.println("nospiesta panel2 treðâ poga");
            case "panel2poga4" -> System.out.println("nospiesta panel2 ceturtâ poga");
            case "panel2poga5" -> System.out.println("nospiesta panel2 piektâ poga");

            //zemâk treðâ paneïa pogu notikumi

            case "zoomInPoga" -> System.out.println("zoom++ placeholder");
            case "zoomOutPoga" -> System.out.println("zoom-- placeholder");
            case "zvaigznePoga" -> thread.dati.drawSampleImages = !thread.dati.drawSampleImages;
            case "panel3poga4" -> System.out.println("nospiesta panel3 ceturtâ poga");
            case "panel3poga5" -> System.out.println("nospiesta panel3 piektâ poga");
            default -> System.out.println("ServerUIThread reìistrçts nedefinçtas pogas notikums!");
        }

        //zemâk vecâs darbîbas

//		if (i==0) { //pirmâ poga
//			server.calculations.CalculationsThread.pauze=!server.calculations.CalculationsThread.pauze;
//
//		} else if (i==1) { //otrâ poga
//			dati.startPlayerView(false);
//
//		} else if (i==2) { //treðâ poga
//			dati.startPlayerView(true);
//
//		} else if (i==3) { //ceturtâ poga
//			dati.tablo1Draw=!dati.tablo1Draw;
//
//		} else if (i==4) { //piektâ poga
//			dati.tablo2Draw=!dati.tablo2Draw;
//
//		} else if (i==5) { //sestâ poga
//			dati.tablo3Draw=!dati.tablo3Draw;
//
//		} else if (i==6) { //septîtâ poga
//			dati.miniMapDraw=!dati.miniMapDraw;
//
//		} else if (i==7) { //astotâ poga
//			dati.inputPanelDraw=!dati.inputPanelDraw;
//
//		} else if (i==8) { //devîtâ poga
//			dati.colorPanelDraw=!dati.colorPanelDraw;
//
//		} else if (i==9) { //desmitâ poga
//			KonstantesUniversal.overallGenRate+=0.01;
//
//		} else if (i==10) { //vienpadsmitâ poga
//			KonstantesUniversal.overallGenRate-=0.01;
//
//		}


    }

}
