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

        CilvekuManager.pirmieSpeletaji(Cilveku.sakumaCilveki, Cilveku.randomKomandas); //jauno spçlçtâju ìenerçðana

        System.out.println("CalculationsThread: Initialized.");
        new SetupThread(); //palaiþ grafisko daïu
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

        //te varçtu arî ìenerçt terrain utml
    }

    private static void initializeKomandas(){
        Komanda.jaunaKomanda("Nulle"); //pati pirmâ komanda

        //te var uzlikt arî pârçjo komandu ìenerçðanu, ja "randomKomandas"
    }

}
