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

        for (int[] chunkXY={0,0}; chunkXY[0]<mapChunkCountX;chunkXY[0]++){ //ìenerç laukumu
            Main.laukums.add(new ArrayList<MapChunk>());
            for (chunkXY[1]=0; chunkXY[1]<mapChunkCountY;chunkXY[1]++){
                MapChunk chunk = new MapChunk();
                Main.laukums.get(chunkXY[0]).add(chunk);
                Main.laukums.get(chunkXY[0]).get(chunkXY[1]).initialize();

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
