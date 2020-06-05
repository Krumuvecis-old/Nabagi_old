package calculations;

import calculations.cilveki.CilvekuApskats;
import calculations.konstantes.*;

import java.util.ArrayList;

class InitializeManager {
    protected static void main(Main main){
        initializeKonstantes();
        initializeLaukums(main);
        initializeKomandas();

        CilvekuApskats.setup();

        grafika.main.SetupThread setupThread = new grafika.main.SetupThread(); //palaiþ grafisko daïu
        setupThread.start();

    }

    private static void initializeKonstantes() {

        KonstantesUniversal.initialize();

        Lietu.initialize();

        Komandu.initialize();
        Cilveku.initialize();

        Fizikas.initialize();
        Grafiskie.initialize();

    }

    private static void  initializeLaukums(Main main) {
        int mapChunkCountX = KonstantesUniversal.mapChunkCountX,
                mapChunkCountY = KonstantesUniversal.mapChunkCountY;

        for (int i=0; i<mapChunkCountX;i++){
            main.laukums.add(new ArrayList<MapChunk>());
            for (int j=0; j<mapChunkCountY;j++){
                MapChunk chunk = new MapChunk();
                main.laukums.get(i).add(chunk);
                main.laukums.get(i).get(j).initialize();

            }
        }
    }

    private static void initializeKomandas(){

    }

    private static void newSetupWindow(){

    }

}
