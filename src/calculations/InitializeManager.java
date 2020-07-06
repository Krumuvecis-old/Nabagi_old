package calculations;

import calculations.cilveki.CilvekuManager;
import calculations.komandas.Komanda;
import calculations.konstantes.Cilveku;
import calculations.lietas.LietuTips;

class InitializeManager {
    static void main(){
        LietuTips.generateLietuTipi();

        initializeLaukums();
        initializeKomandas();

        CilvekuManager.pirmieSpeletaji(Cilveku.sakumaCilveki, Cilveku.randomKomandas); //jauno spçlçtâju ìenerçðana

        newSetupWindow();
    }

    private static void initializeLaukums() {
        for (int[] chunkXY={0,0}; chunkXY[0]<KonstantesUniversal.mapChunkCountX; chunkXY[0]++){
            for (chunkXY[1]=0; chunkXY[1]<KonstantesUniversal.mapChunkCountY;chunkXY[1]++){
                MapChunk chunk = new MapChunk();
                Main.laukums.put(chunkXY, chunk);
            }
        }

        //te varçtu arî ìenerçt terrain utml
    }

    private static void initializeKomandas(){
        Komanda.jaunaKomanda("Nulle"); //pati pirmâ komanda

        //te var uzlikt arî pârçjo komandu ìenerçðanu, ja "randomKomandas"
    }

    private static void newSetupWindow(){
        grafika.main.SetupThread setupThread = new grafika.main.SetupThread(); //palaiþ grafisko daïu
        setupThread.start();
    }

}
