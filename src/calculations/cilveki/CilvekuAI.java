package calculations.cilveki;

import calculations.komandas.Biedrs;

public class CilvekuAI {

    protected  static void main(int numurs){
        Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
        Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,biedrs.i);


    }

}
