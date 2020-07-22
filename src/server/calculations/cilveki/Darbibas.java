package server.calculations.cilveki;

import server.calculations.komandas.Komanda;
import server.calculations.Formulas;
import server.calculations.lietas.LietuTips;
import server.dataBase.DataBase;

import java.util.Random;

public class Darbibas {
    Random r = new Random();
    public double vKoef = 1, fi = 360 * r.nextDouble(); //vçlamais kustîbas virziens un âtrums
    public String darbiba; //animâcijai un testiem

    protected void main(Cilveks cilveks){

        //kustibasParametri(cilveks); -- tiek izsaukts pie CilvekuManager.main() -> DefaultDarbibas.main()
    }

    protected void kustibasParametri(Cilveks cilveks){
        double hpKoef = cilveks.hp / cilveks.hpmax;

        double deltaFi = fi - cilveks.xyz.fi;
        cilveks.xyz.v = cilveks.vmax * vKoef * hpKoef * Math.cos(Math.toRadians(deltaFi));

        // leòía pakâpeniska pagrieðana
        if (deltaFi > 0) {
            cilveks.xyz.fi += Math.min(cilveks.omega, deltaFi);
        } else if (deltaFi < 0) {
            cilveks.xyz.fi += Math.max(cilveks.omega * (-1), deltaFi);
        }

    }

    public static void vairosanas(String _vards) {
        Cilveks cilveks = DataBase.cilvekuList.get(_vards);

        String vards = CilvekuKonstantes.vardsDefault + Cilveks.maxCilveks + 1;

        Random r = new Random();

        Koord xyz = new Koord(
                cilveks.xyz.x, cilveks.xyz.y,
                0, 360 * r.nextDouble(),
                cilveks.xyz.chunkXY);

        double vmax = Formulas.novirzeRandom(cilveks.vmax, CilvekuKonstantes.dvMaxDzimstot),
                omega = Formulas.novirzeRandom(cilveks.omega, CilvekuKonstantes.dommaxDzimstot);

        double resnums = CilvekuKonstantes.resnumsDefault,
                R1 = Formulas.novirzeRandom(cilveks.R1, CilvekuKonstantes.dRDzimstot),
                R2 = Formulas.novirzeRandom(cilveks.R2, CilvekuKonstantes.dRDzimstot);

        double hpmax = CilvekuKonstantes.hpmax,
                paikaMax = CilvekuKonstantes.paikaMax,
                paikaMin = CilvekuKonstantes.paikaMin;

        double brunas = Formulas.novirzeRandom(cilveks.brunas, CilvekuKonstantes.dBrunasDzimstot),
                stiprums = Formulas.novirzeRandom(cilveks.stiprums, CilvekuKonstantes.dStiprumsDzimstot),
                gataviba = CilvekuKonstantes.maxGataviba,
                drosme = cilveks.drosme;

        for(int i=0; i<cilveks.inventory.size(); i++) {
            switch (cilveks.inventory.get(i).tips) {
                case "Zelts" -> cilveks.inventory.get(i).daudzums -=
                        (CilvekuKonstantes.cenaCilvekam + CilvekuKonstantes.mantojumsCilvekamZelts) /
                                LietuTips.lietuTipi.get(cilveks.inventory.get(i).tips).zelts;
                case "Paika" -> cilveks.inventory.get(i).daudzums -=
                        CilvekuKonstantes.mantojumsCilvekamPaika /
                                LietuTips.lietuTipi.get(cilveks.inventory.get(i).tips).paika;
            }
        }

        String komanda;

        if (cilveks.komanda.equals("Anarhija") ||
                (DataBase.komandasList.get(cilveks.komanda).biedri.get(_vards).rangs[1] == 0 &&
                        r.nextDouble() < CilvekuKonstantes.dzimstotDefectionChance)) { //izveido savu komandu

            komanda = Komanda.jaunaKomanda(_vards); //nosauc tçva vârdâ, tçvs bûs karalis
            cilveks.komanda = komanda;

        } else { //paliek esoðajâ komandâ
            komanda = cilveks.komanda;
        }

        DataBase.cilvekuList.put(vards,
                new Cilveks(vards,
                        xyz, vmax, omega,
                        resnums, R1, R2,
                        hpmax, paikaMax, paikaMin,
                        brunas, stiprums, gataviba, drosme,
                        komanda));

        DataBase.komandasList.get(komanda).pievienotiesKomandai(vards); //pievieno tai paðai komandai
    }

    public static void buveMaju(){
        //te varçs bûvçt mâju -- pagaidâm nav pabeigts
    }


}
