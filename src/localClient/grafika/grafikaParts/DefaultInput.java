package localClient.grafika.grafikaParts;

import localClient.ClientThread;
import localClient.Dati;
import localClient.FileHandler;
import localClient.grafika.Button;

import javax.swing.*;
import java.awt.*;

public class DefaultInput {

    //tikai pa�as darb�bas

    public void keyboardActions(int numurs){
        switch (numurs) { //numurs - klaviat�r� nospiest�s pogas numurs
            case 32 -> System.out.println("piespiests Space");
            case 87 -> System.out.println("piespiests W");
            case 65 -> System.out.println("piespiests A");
            case 83 -> System.out.println("piespiests S");
            case 68 -> System.out.println("piespiests D");
            default -> System.out.println("piespiesta nedefin�ta keyboard poga");
        }
    }



    public void buttonActions(Button.ActionReference reference, ClientThread thread) {
        switch (reference) {

            //zem�k header pogu notikumi

            case head1 -> thread.running = false;
            case head2 -> thread.grafika.ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);
            case head3 -> thread.grafika.ekrans.setState(Frame.ICONIFIED);
            case head4 -> System.out.println("nospiesta header ceturt� poga");
            case head5 -> System.out.println("nospiesta header piekt� poga");
            case head6 -> System.out.println("nospiesta header sest� poga");
            case head7 -> System.out.println("nospiesta header sept�t� poga");
            case head8 -> System.out.println("nospiesta header astot� poga");
            case head9 -> System.out.println("nospiesta header dev�t� poga");

            //zem�k kreis� pane�a pogu notikumi

            case left1 -> {
                //newClient(); //jauns klients
                System.out.println("neweClient placeholder");
            }
            case left2 -> System.out.println("nospiesta kreis� pane�a otr� poga");
            case left3 -> System.out.println("nospiesta kreis� pane�a tre�� poga");
            case left4 -> System.out.println("nospiesta kreis� pane�a ceturt� poga");
            case left5 -> System.out.println("nospiesta kreis� pane�a piekt� poga");
            case left6 -> System.out.println("nospiesta kreis� pane�a sest� poga");
            case left7 -> System.out.println("nospiesta kreis� pane�a sept�t� poga");
            case left8 -> System.out.println("nospiesta kreis� pane�a astot� poga");
            case left9 -> System.out.println("nospiesta kreis� pane�a dev�t� poga");

            //zem�k centr�l� pane�a pogu notikumi

            case center1 -> System.out.println("zoom++ placeholder");
            case center2 -> System.out.println("zoom-- placeholder");
            case center3 -> thread.dati.grafikasDati.drawSampleImages = !thread.dati.grafikasDati.drawSampleImages;
            case center4 -> System.out.println("nospiesta centr�l� pane�a ceturt� poga");
            case center5 -> System.out.println("nospiesta centr�l� pane�a piekt� poga");
            case center6 -> System.out.println("nospiesta centr�l� pane�a sest� poga");
            case center7 -> System.out.println("nospiesta centr�l� pane�a sept�t� poga");
            case center8 -> System.out.println("nospiesta centr�l� pane�a astot� poga");
            case center9 -> System.out.println("nospiesta centr�l� pane�a dev�t� poga");

            //zem�k lab� pane�a pogu notikumi

            case right1 -> System.out.println("nospiesta lab� pane�a pirm� poga");
            case right2 -> System.out.println("nospiesta lab� pane�a otr� poga");
            case right3 -> System.out.println("nospiesta lab� pane�a tre�� poga");
            case right4 -> System.out.println("nospiesta lab� pane�a ceturt� poga");
            case right5 -> System.out.println("nospiesta lab� pane�a piekt� poga");
            case right6 -> System.out.println("nospiesta lab� pane�a sest� poga");
            case right7 -> System.out.println("nospiesta lab� pane�a sept�t� poga");
            case right8 -> System.out.println("nospiesta lab� pane�a astot� poga");
            case right9 -> System.out.println("nospiesta lab� pane�a dev�t� poga");

            //zem�k footer pogu notikumi

            case foot1 -> thread.dati.grafikasDati.drawLayoutGrid = !thread.dati.grafikasDati.drawLayoutGrid;
            case foot2 -> thread.dati.grafikasDati.drawInputDiagnosticsPanel = !thread.dati.grafikasDati.drawInputDiagnosticsPanel;
            case foot3 -> thread.dati.grafikasDati.colorPalette.pickPreset(0);
            case foot4 -> thread.dati.grafikasDati.colorPalette.pickPreset(1);
            case foot5 -> thread.dati.grafikasDati.colorPalette.pickPreset(2);
            case foot6 -> FileHandler.loadSettings(thread.dati, thread.grafika.ekrans);
            case foot7 -> FileHandler.saveSettings(thread.dati);
            case foot8 -> System.out.println("nospiesta footer astot� poga");
            case foot9 -> System.out.println("nospiesta footer dev�t� poga");

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
