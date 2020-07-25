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
        CilvekuManager.pirmieSpeletaji(CilvekuKonstantes.sakumaCilveki, CilvekuKonstantes.randomKomandas); //jauno sp�l�t�ju �ener��ana

        System.out.println("CalculationsThread: Initialization complete.");
    }


    private static void initializeKomandas(){
        //te, ja vajag, var ieinicializ�t kaut k�das komandu konstantes

        //�ener� s�kuma komandas
        Komanda.jaunaKomanda("Nulle"); //pati pirm� komanda

        //te var uzlikt ar� p�r�jo komandu �ener��anu, ja "randomKomandas"
    }

}
