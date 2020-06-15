package grafika.main;

import calculations.KonstantesUniversal;

import java.util.ArrayList;

class ButtonActions {

    protected static void main(SetupThread thread, ArrayList<Button> buttonList){
        for (int i=0; i<buttonList.size();i++) {

            buttonList.get(i).actions(thread); //p�rbauda katras pogas statusu

            if (buttonList.get(i).result) { //ja poga nostr�d�jusi

                buttonNotikums(i, thread.dati);

                buttonList.get(i).result=false; //reseto pogas statusu
            }
        }
    }

    private static void buttonNotikums(int i, Dati dati){
        if (i==0) { //pirm� poga
            calculations.Main.pauze=!calculations.Main.pauze;

        } else if (i==1) { //otr� poga
            dati.startPlayerView(false);

        } else if (i==2) { //tre�� poga
            dati.startPlayerView(true);

        } else if (i==3) { //ceturt� poga
            dati.tablo1Draw=!dati.tablo1Draw;

        } else if (i==4) { //piekt� poga
            dati.tablo2Draw=!dati.tablo2Draw;

        } else if (i==5) { //sest� poga
            dati.tablo3Draw=!dati.tablo3Draw;

        } else if (i==6) { //sept�t� poga
            dati.miniMapDraw=!dati.miniMapDraw;

        } else if (i==7) { //astot� poga
            dati.inputPanelDraw=!dati.inputPanelDraw;

        } else if (i==8) { //dev�t� poga
            dati.colorPanelDraw=!dati.colorPanelDraw;

        } else if (i==9) { //desmit� poga
            KonstantesUniversal.overallGenRate+=0.01;

        } else if (i==10) { //vienpadsmit� poga
            KonstantesUniversal.overallGenRate-=0.01;

        }
    }

}
