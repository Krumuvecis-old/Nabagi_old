package server.calculations;

import server.calculations.cilveki.CilvekuManager;
import server.calculations.komandas.Komanda;
import server.calculations.cilveki.CilvekuKonstantes;
import server.calculations.lietas.LietuTips;
import server.dataBase.DataBase;

import java.util.ArrayList;
import java.util.List;

public class Initializator {
    public static void main(String versija){
        DataBase.versija = versija;
        LietuTips.generateLietuTipi();

        initializeLaukums();
        initializeKomandas();

        CilvekuManager.pirmieSpeletaji(CilvekuKonstantes.sakumaCilveki, CilvekuKonstantes.randomKomandas); //jauno spçlçtâju ìenerçðana

        System.out.println("CalculationsThread: Initialization complete.");
    }

    private static void initializeLaukums() {
        int x, y;
        for (x = 0; x < DataBase.mapChunkCountX; x++){
            for (y = 0; y < DataBase.mapChunkCountY; y++){
                List<Integer> xy = new ArrayList<>();
                xy.add(x);
                xy.add(y);
                DataBase.laukums.put(xy, new MapChunk());
            }
        }

        //te varçtu arî ìenerçt terrain utml
    }

    private static void initializeKomandas(){
        Komanda.jaunaKomanda("Nulle"); //pati pirmâ komanda

        //te var uzlikt arî pârçjo komandu ìenerçðanu, ja "randomKomandas"
    }

}
