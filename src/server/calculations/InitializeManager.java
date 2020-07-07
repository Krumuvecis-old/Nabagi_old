package server.calculations;

import localClient.SetupThread;
import server.calculations.cilveki.CilvekuManager;
import server.calculations.komandas.Komanda;
import server.calculations.konstantes.Cilveku;
import server.calculations.lietas.LietuTips;

import java.util.ArrayList;
import java.util.List;

class InitializeManager {
    static void main(){
        LietuTips.generateLietuTipi();

        initializeLaukums();
        initializeKomandas();

        CilvekuManager.pirmieSpeletaji(Cilveku.sakumaCilveki, Cilveku.randomKomandas); //jauno sp�l�t�ju �ener��ana

        System.out.println("CalculationsThread: Initialized.");
        new SetupThread(); //palai� grafisko da�u
    }

    private static void initializeLaukums() {
        int x, y;
        for (x = 0; x < KonstantesUniversal.mapChunkCountX; x++){
            for (y = 0; y < KonstantesUniversal.mapChunkCountY; y++){
                List<Integer> xy = new ArrayList<>();
                xy.add(x);
                xy.add(y);
                Main.laukums.put(xy, new MapChunk());
            }
        }

        //te var�tu ar� �ener�t terrain utml
    }

    private static void initializeKomandas(){
        Komanda.jaunaKomanda("Nulle"); //pati pirm� komanda

        //te var uzlikt ar� p�r�jo komandu �ener��anu, ja "randomKomandas"
    }

}
