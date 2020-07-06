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

        CilvekuManager.pirmieSpeletaji(Cilveku.sakumaCilveki, Cilveku.randomKomandas); //jauno sp�l�t�ju �ener��ana

        newSetupWindow();
    }

    private static void initializeLaukums() {
        for (int[] chunkXY={0,0}; chunkXY[0]<KonstantesUniversal.mapChunkCountX; chunkXY[0]++){
            for (chunkXY[1]=0; chunkXY[1]<KonstantesUniversal.mapChunkCountY;chunkXY[1]++){
                MapChunk chunk = new MapChunk();
                Main.laukums.put(chunkXY, chunk);
            }
        }

        //te var�tu ar� �ener�t terrain utml
    }

    private static void initializeKomandas(){
        Komanda.jaunaKomanda("Nulle"); //pati pirm� komanda

        //te var uzlikt ar� p�r�jo komandu �ener��anu, ja "randomKomandas"
    }

    private static void newSetupWindow(){
        grafika.main.SetupThread setupThread = new grafika.main.SetupThread(); //palai� grafisko da�u
        setupThread.start();
    }

}
