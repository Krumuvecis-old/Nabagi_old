package localClient;

import javax.swing.*;
import java.awt.*;

public class InputActions {

    //tikai pa�as darb�bas

    private static final String modeDefault = "default"; //idejas k� satais�t re��mus, v�l j�p�rnes uz Dati vai clientThread vai citur

    public static void keyboardActions(int numurs, String mode){
        if (mode.equals(modeDefault)){
            switch (numurs) { //numurs - klaviat�r� nospiest�s pogas numurs
                case 32 -> System.out.println("piespiests Space");
                case 87 -> System.out.println("piespiests W");
                case 65 -> System.out.println("piespiests A");
                case 83 -> System.out.println("piespiests S");
                case 68 -> System.out.println("piespiests D");
                default -> System.out.println("piespiesta nedefin�ta keyboard poga");
            }
        } else {
            System.out.println("�aj� re��m� poga nedarbojas");
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

            //zem�k header pogu notikumi

            case "head1" -> thread.running = false;
            case "head2" -> thread.grafika.ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);
            case "head3" -> thread.grafika.ekrans.setState(Frame.ICONIFIED);
            case "head4" -> System.out.println("nospiesta header ceturt� poga");
            case "head5" -> System.out.println("nospiesta header piekt� poga");
            case "head6" -> System.out.println("nospiesta header sest� poga");
            case "head7" -> System.out.println("nospiesta header sept�t� poga");

            //zem�k footer pogu notikumi

            case "foot1" -> thread.dati.drawLayoutGrid = !thread.dati.drawLayoutGrid;
            case "foot2" -> thread.dati.drawInputDiagnosticsPanel = !thread.dati.drawInputDiagnosticsPanel;
            case "palette0poga" -> thread.dati.colorPalette.pickPreset(0);
            case "palette1poga" -> thread.dati.colorPalette.pickPreset(1);
            case "palette2poga" -> thread.dati.colorPalette.pickPreset(2);
            case "loadSettingsPoga" -> FileHandler.loadSettings(thread.dati, thread.grafika.ekrans);
            case "saveSettingsPoga" -> FileHandler.saveSettings(thread.dati);

            //zem�k pirm� pane�a pogu notikumi

            case "panel1poga1" -> {
                //newClient(); //jauns klients
                System.out.println("neweClient placeholder");
            }
            case "panel1poga2" -> System.out.println("nospiesta panel1 otr� poga");
            case "panel1poga3" -> System.out.println("nospiesta panel1 tre�� poga");
            case "panel1poga4" -> System.out.println("nospiesta panel1 ceturt� poga");
            case "panel1poga5" -> System.out.println("nospiesta panel1 piekt� poga");

            //zem�k otr� pane�a pogu notikumi

            case "panel2poga1" -> System.out.println("nospiesta panel2 pirm� poga");
            case "panel2poga2" -> System.out.println("nospiesta panel2 otr� poga");
            case "panel2poga3" -> System.out.println("nospiesta panel2 tre�� poga");
            case "panel2poga4" -> System.out.println("nospiesta panel2 ceturt� poga");
            case "panel2poga5" -> System.out.println("nospiesta panel2 piekt� poga");

            //zem�k tre�� pane�a pogu notikumi

            case "zoomInPoga" -> System.out.println("zoom++ placeholder");
            case "zoomOutPoga" -> System.out.println("zoom-- placeholder");
            case "zvaigznePoga" -> thread.dati.drawSampleImages = !thread.dati.drawSampleImages;
            case "panel3poga4" -> System.out.println("nospiesta panel3 ceturt� poga");
            case "panel3poga5" -> System.out.println("nospiesta panel3 piekt� poga");
            default -> System.out.println("ServerUIThread re�istr�ts nedefin�tas pogas notikums!");
        }

        //zem�k vec�s darb�bas

//		if (i==0) { //pirm� poga
//			server.calculations.CalculationsThread.pauze=!server.calculations.CalculationsThread.pauze;
//
//		} else if (i==1) { //otr� poga
//			dati.startPlayerView(false);
//
//		} else if (i==2) { //tre�� poga
//			dati.startPlayerView(true);
//
//		} else if (i==3) { //ceturt� poga
//			dati.tablo1Draw=!dati.tablo1Draw;
//
//		} else if (i==4) { //piekt� poga
//			dati.tablo2Draw=!dati.tablo2Draw;
//
//		} else if (i==5) { //sest� poga
//			dati.tablo3Draw=!dati.tablo3Draw;
//
//		} else if (i==6) { //sept�t� poga
//			dati.miniMapDraw=!dati.miniMapDraw;
//
//		} else if (i==7) { //astot� poga
//			dati.inputPanelDraw=!dati.inputPanelDraw;
//
//		} else if (i==8) { //dev�t� poga
//			dati.colorPanelDraw=!dati.colorPanelDraw;
//
//		} else if (i==9) { //desmit� poga
//			KonstantesUniversal.overallGenRate+=0.01;
//
//		} else if (i==10) { //vienpadsmit� poga
//			KonstantesUniversal.overallGenRate-=0.01;
//
//		}


    }

}
