package calculations;

import calculations.cilveki.CilvekuManager;
import calculations.konstantes.*;
import calculations.lietas.LietuPreseti;

import java.util.ArrayList;

class InitializeManager {
    protected static void main(){
        initializeKonstantes();
        initializeLaukums();
        initializeKomandas();

        CilvekuManager.initialize(); //initialize + jaunie spçlçtâji

        newSetupWindow();

    }

    private static void initializeKonstantes() {

        KonstantesUniversal.initialize();

        LietuPreseti.initialize();

        Komandu.initialize();
        Cilveku.initialize();

        Fizikas.initialize();
        Grafiskie.initialize();

    }

    private static void  initializeLaukums() {
        int mapChunkCountX = KonstantesUniversal.mapChunkCountX,
                mapChunkCountY = KonstantesUniversal.mapChunkCountY;

        for (int i=0; i<mapChunkCountX;i++){ //ìenerç laukumu
            Main.laukums.add(new ArrayList<MapChunk>());
            for (int j=0; j<mapChunkCountY;j++){
                MapChunk chunk = new MapChunk();
                Main.laukums.get(i).add(chunk);
                Main.laukums.get(i).get(j).initialize();

            }
        }
    }

    private static void initializeKomandas(){

        //kas te notiek?


    }

    private static void newSetupWindow(){
        grafika.main.SetupThread setupThread = new grafika.main.SetupThread(); //palaiþ grafisko daïu
        setupThread.start();
    }

}
