package server.calculations;

import server.calculations.cilveki.CilvekuManager;
import server.calculations.komandas.Komanda;
import server.calculations.cilveki.CilvekuKonstantes;
import server.calculations.laukums.Laukums;
import server.calculations.lietas.LietuTips;
import server.dataBase.DataBase;

public class Initializator {
    public static void main(String versija){
        DataBase.versija = versija;

        LietuTips.generateLietuTipi();
        Laukums.LaukumsInitializator.initializeLaukums();
        initializeKomandas();
        CilvekuManager.pirmieSpeletaji(CilvekuKonstantes.sakumaCilveki, CilvekuKonstantes.randomKomandas); //jauno spçlçtâju ìenerçðana

        System.out.println("CalculationsThread: Initialization complete.");
    }


    private static void initializeKomandas(){
        //te, ja vajag, var ieinicializçt kaut kâdas komandu konstantes

        //ìenerç sâkuma komandas
        Komanda.jaunaKomanda("Nulle"); //pati pirmâ komanda

        //te var uzlikt arî pârçjo komandu ìenerçðanu, ja "randomKomandas"
    }

}
