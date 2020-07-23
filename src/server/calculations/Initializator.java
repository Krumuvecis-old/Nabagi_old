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

        CilvekuManager.pirmieSpeletaji(CilvekuKonstantes.sakumaCilveki, CilvekuKonstantes.randomKomandas); //jauno sp�l�t�ju �ener��ana

        System.out.println("CalculationsThread: Initialization complete.");
    }

    private static void initializeLaukums() {
        MapCell.TerrainInfo.generateTerrainPresets();

        int x, y;
        for (x = 0; x < DataBase.mapChunkCountX; x++){
            for (y = 0; y < DataBase.mapChunkCountY; y++){
                List<Integer> xy = new ArrayList<>();
                xy.add(x);
                xy.add(y);
                DataBase.laukums.put(xy, new MapChunk());
            }
        }

        terrainGenerator();
    }

    private static void terrainGenerator(){
        //te var�tu �ener�t s�kuma terrain, lai neb�tu visiem ien�da v�rt�ba
    }

    private static void initializeKomandas(){
        Komanda.jaunaKomanda("Nulle"); //pati pirm� komanda

        //te var uzlikt ar� p�r�jo komandu �ener��anu, ja "randomKomandas"
    }

}
