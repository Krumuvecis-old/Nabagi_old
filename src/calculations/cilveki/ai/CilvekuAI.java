package calculations.cilveki.ai;

import calculations.KonstantesUniversal;
import calculations.Location;
import calculations.Main;
import calculations.cilveki.Cilveks;
import calculations.cilveki.Darbibas;
import calculations.cilveki.Orderis;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Formulas;
import calculations.lietas.Lieta;

import java.util.ArrayList;
import java.util.Random;

public class CilvekuAI {

    private static Location perkBiedrs, pardodBiedrs;

    public static void main(int numurs){
        Location location = Cilveks.cilvekuListPilnais.get(numurs);
        Cilveks cilveks = Cilveks.getPlayer(location);

        /* - citu funkciju pârbaudes nolûkos AI pagaidâm ir izòemts

        //secîgi pârbauda visus attâlumus
        if (!CloseRange.main(cilveks, location))
            if(!MediumRange.main(cilveks, location))
                if(!FarRange.main(cilveks, location))
                    MiscDarbibas.main(cilveks, location);


         */

    }

    protected static void tuvoties(Cilveks cilveks, double[] XY, int[] chunkXY, double vKoef){
        cilveks.darbibas.darbiba="tuvoties";
        cilveks.darbibas.vKoef=vKoef;
        cilveks.darbibas.fi = Formulas.lenkaNoteiksana(cilveks.xyz.x, cilveks.xyz.y, XY[0], XY[1], chunkXY);
    }

    protected static void atkapties(Cilveks cilveks, double[] XY, int[] chunkXY, double vKoef){
        cilveks.darbibas.darbiba="atkapties";
        cilveks.darbibas.vKoef=vKoef;
        cilveks.darbibas.fi = 180 + Formulas.lenkaNoteiksana(cilveks.xyz.x, cilveks.xyz.y, XY[0], XY[1], chunkXY);
    }

}