package localClient.grafika.grafikaParts;

import localClient.ClientThread;
import localClient.Dati;
import localClient.FileHandler;
import localClient.grafika.Button;

import javax.swing.*;
import java.awt.*;

public class DefaultInput {

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



    public void buttonActions(Button.ActionReference reference, ClientThread thread) {
        switch (reference) {

            //zemâk header pogu notikumi

            case head1 -> thread.running = false;
            case head2 -> thread.grafika.ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);
            case head3 -> thread.grafika.ekrans.setState(Frame.ICONIFIED);
            case head4 -> System.out.println("nospiesta header ceturtâ poga");
            case head5 -> System.out.println("nospiesta header piektâ poga");
            case head6 -> System.out.println("nospiesta header sestâ poga");
            case head7 -> System.out.println("nospiesta header septîtâ poga");
            case head8 -> System.out.println("nospiesta header astotâ poga");
            case head9 -> System.out.println("nospiesta header devîtâ poga");

            //zemâk kreisâ paneïa pogu notikumi

            case left1 -> {
                //newClient(); //jauns klients
                System.out.println("neweClient placeholder");
            }
            case left2 -> System.out.println("nospiesta kreisâ paneïa otrâ poga");
            case left3 -> System.out.println("nospiesta kreisâ paneïa treðâ poga");
            case left4 -> System.out.println("nospiesta kreisâ paneïa ceturtâ poga");
            case left5 -> System.out.println("nospiesta kreisâ paneïa piektâ poga");
            case left6 -> System.out.println("nospiesta kreisâ paneïa sestâ poga");
            case left7 -> System.out.println("nospiesta kreisâ paneïa septîtâ poga");
            case left8 -> System.out.println("nospiesta kreisâ paneïa astotâ poga");
            case left9 -> System.out.println("nospiesta kreisâ paneïa devîtâ poga");

            //zemâk centrâlâ paneïa pogu notikumi

            case center1 -> System.out.println("zoom++ placeholder");
            case center2 -> System.out.println("zoom-- placeholder");
            case center3 -> thread.dati.grafikasDati.drawSampleImages = !thread.dati.grafikasDati.drawSampleImages;
            case center4 -> System.out.println("nospiesta centrâlâ paneïa ceturtâ poga");
            case center5 -> System.out.println("nospiesta centrâlâ paneïa piektâ poga");
            case center6 -> System.out.println("nospiesta centrâlâ paneïa sestâ poga");
            case center7 -> System.out.println("nospiesta centrâlâ paneïa septîtâ poga");
            case center8 -> System.out.println("nospiesta centrâlâ paneïa astotâ poga");
            case center9 -> System.out.println("nospiesta centrâlâ paneïa devîtâ poga");

            //zemâk labâ paneïa pogu notikumi

            case right1 -> System.out.println("nospiesta labâ paneïa pirmâ poga");
            case right2 -> System.out.println("nospiesta labâ paneïa otrâ poga");
            case right3 -> System.out.println("nospiesta labâ paneïa treðâ poga");
            case right4 -> System.out.println("nospiesta labâ paneïa ceturtâ poga");
            case right5 -> System.out.println("nospiesta labâ paneïa piektâ poga");
            case right6 -> System.out.println("nospiesta labâ paneïa sestâ poga");
            case right7 -> System.out.println("nospiesta labâ paneïa septîtâ poga");
            case right8 -> System.out.println("nospiesta labâ paneïa astotâ poga");
            case right9 -> System.out.println("nospiesta labâ paneïa devîtâ poga");

            //zemâk footer pogu notikumi

            case foot1 -> thread.dati.grafikasDati.drawLayoutGrid = !thread.dati.grafikasDati.drawLayoutGrid;
            case foot2 -> thread.dati.grafikasDati.drawInputDiagnosticsPanel = !thread.dati.grafikasDati.drawInputDiagnosticsPanel;
            case foot3 -> thread.dati.grafikasDati.colorPalette.pickPreset(0);
            case foot4 -> thread.dati.grafikasDati.colorPalette.pickPreset(1);
            case foot5 -> thread.dati.grafikasDati.colorPalette.pickPreset(2);
            case foot6 -> FileHandler.loadSettings(thread.dati, thread.grafika.ekrans);
            case foot7 -> FileHandler.saveSettings(thread.dati);
            case foot8 -> System.out.println("nospiesta footer astotâ poga");
            case foot9 -> System.out.println("nospiesta footer devîtâ poga");

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
