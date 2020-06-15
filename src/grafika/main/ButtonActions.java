package grafika.main;

import calculations.KonstantesUniversal;

import java.util.ArrayList;

class ButtonActions {

    protected static void main(SetupThread thread, ArrayList<Button> buttonList){
        for (int i=0; i<buttonList.size();i++) {

            buttonList.get(i).actions(thread); //pârbauda katras pogas statusu

            if (buttonList.get(i).result) { //ja poga nostrâdâjusi

                buttonNotikums(i, thread.dati);

                buttonList.get(i).result=false; //reseto pogas statusu
            }
        }
    }

    private static void buttonNotikums(int i, Dati dati){
        if (i==0) { //pirmâ poga
            calculations.Main.pauze=!calculations.Main.pauze;

        } else if (i==1) { //otrâ poga
            dati.startPlayerView(false);

        } else if (i==2) { //treðâ poga
            dati.startPlayerView(true);

        } else if (i==3) { //ceturtâ poga
            dati.tablo1Draw=!dati.tablo1Draw;

        } else if (i==4) { //piektâ poga
            dati.tablo2Draw=!dati.tablo2Draw;

        } else if (i==5) { //sestâ poga
            dati.tablo3Draw=!dati.tablo3Draw;

        } else if (i==6) { //septîtâ poga
            dati.miniMapDraw=!dati.miniMapDraw;

        } else if (i==7) { //astotâ poga
            dati.inputPanelDraw=!dati.inputPanelDraw;

        } else if (i==8) { //devîtâ poga
            dati.colorPanelDraw=!dati.colorPanelDraw;

        } else if (i==9) { //desmitâ poga
            KonstantesUniversal.overallGenRate+=0.01;

        } else if (i==10) { //vienpadsmitâ poga
            KonstantesUniversal.overallGenRate-=0.01;

        }
    }

}
