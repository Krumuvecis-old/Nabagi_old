package grafika.player;

import calculations.KonstantesUniversal;

import java.util.ArrayList;

class ButtonActions {

    protected static void main(PlayerThread thread, ArrayList<grafika.player.Button> buttonList){
        for (int i=0; i<buttonList.size();i++) {

            buttonList.get(i).actions(thread); //p�rbauda katras pogas statusu

            if (buttonList.get(i).result) { //ja poga nostr�d�jusi

                buttonNotikums(i, thread.dati, thread);
                buttonList.get(i).result=false; //reseto pogas statusu
            }
        }
    }

    private static void buttonNotikums(int i, Dati dati, PlayerThread thread){
        if (i==0) { //pirm� poga
            calculations.Main.pauze=!calculations.Main.pauze;

        } else if (i==1) { //otr� poga
            dati.cilvekiDrawInfo=!dati.cilvekiDrawInfo;

        } else if (i==2) { //tre�� poga
            dati.cilvekiDrawR=!dati.cilvekiDrawR;

        } else if (i==3) { //ceturt� poga
            dati.lietasDrawInfo=!dati.lietasDrawInfo;

        } else if (i==4) { //piekt� poga
            if (dati.playerDead) {
//                dati.playerInitialize(thread, false);
            }
        }

    }

}
