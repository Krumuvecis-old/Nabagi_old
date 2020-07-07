package grafika.player;

import calculations.KonstantesUniversal;

import java.util.ArrayList;

class ButtonActions {

    protected static void main(PlayerThread thread, ArrayList<grafika.player.Button> buttonList){
        for (int i=0; i<buttonList.size();i++) {

            buttonList.get(i).actions(thread); //pârbauda katras pogas statusu

            if (buttonList.get(i).result) { //ja poga nostrâdâjusi

                buttonNotikums(i, thread.dati, thread);
                buttonList.get(i).result=false; //reseto pogas statusu
            }
        }
    }

    private static void buttonNotikums(int i, Dati dati, PlayerThread thread){
        if (i==0) { //pirmâ poga
            calculations.Main.pauze=!calculations.Main.pauze;

        } else if (i==1) { //otrâ poga
            dati.cilvekiDrawInfo=!dati.cilvekiDrawInfo;

        } else if (i==2) { //treðâ poga
            dati.cilvekiDrawR=!dati.cilvekiDrawR;

        } else if (i==3) { //ceturtâ poga
            dati.lietasDrawInfo=!dati.lietasDrawInfo;

        } else if (i==4) { //piektâ poga
            if (dati.playerDead) {
//                dati.playerInitialize(thread, false);
            }
        }

    }

}
