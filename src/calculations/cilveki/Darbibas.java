package calculations.cilveki;

import calculations.Location;
import calculations.Main;
import calculations.komandas.Komanda;
import calculations.komandas.KomanduApskats;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Formulas;
import calculations.lietas.LietuTips;

import java.util.Random;

public class Darbibas {
    Random r = new Random();
    public double vKoef = 1, fi = 360 * r.nextDouble(); //v�lamais kust�bas virziens un �trums
    public String darbiba; //anim�cijai un testiem

    protected void main(Cilveks cilveks){

        //kustibasParametri(cilveks); -- tiek izsaukts pie CilvekuManager.main() -> DefaultDarbibas.main()
    }

    protected void kustibasParametri(Cilveks cilveks){
        double hpKoef = cilveks.hp / cilveks.hpmax;

        double deltaFi = fi - cilveks.xyz.fi;
        cilveks.xyz.v = cilveks.vmax * vKoef * hpKoef * Math.cos(Math.toRadians(deltaFi));

        // le��a pak�peniska pagrie�ana
        if (deltaFi > 0) {
            cilveks.xyz.fi += Math.min(cilveks.omega, deltaFi);
        } else if (deltaFi < 0) {
            cilveks.xyz.fi += Math.max(cilveks.omega * (-1), deltaFi);
        }

    }

    public static void vairosanas(String _vards) {
        Cilveks cilveks = Main.cilvekuList.get(_vards);

        String vards = Cilveku.vardsDefault + Cilveks.maxCilveks + 1;

        Random r = new Random();

        Koord xyz = new Koord(
                cilveks.xyz.x, cilveks.xyz.y,
                0, 360*r.nextDouble(),
                cilveks.xyz.chunkXY);

        double vmax=Formulas.novirzeRandom(cilveks.vmax, Cilveku.dvMaxDzimstot),
                omega=Formulas.novirzeRandom(cilveks.omega, Cilveku.dommaxDzimstot);

        double hpmax=Cilveku.hpmax, hp=hpmax,
                paika=Cilveku.paikaMax;

        double R2=Formulas.novirzeRandom(cilveks.R2, Cilveku.dRDzimstot),
                R1=Formulas.novirzeRandom(cilveks.R1, Cilveku.dRDzimstot);

        double brunas = Formulas.novirzeRandom(cilveks.brunas, Cilveku.dBrunasDzimstot),
                stiprums = Formulas.novirzeRandom(cilveks.stiprums, Cilveku.dStiprumsDzimstot),
                gataviba = Cilveku.maxGataviba,
                drosme = cilveks.drosme;

        for(int i=0; i<cilveks.inventory.size(); i++) {
            switch (cilveks.inventory.get(i).tips) {
                case "Zelts" -> cilveks.inventory.get(i).daudzums -=
                        (Cilveku.cenaCilvekam + Cilveku.mantojumsCilvekamZelts) /
                                LietuTips.lietuTipi.get(cilveks.inventory.get(i).tips).zelts;
                case "Paika" -> cilveks.inventory.get(i).daudzums -=
                        Cilveku.mantojumsCilvekamPaika /
                                LietuTips.lietuTipi.get(cilveks.inventory.get(i).tips).paika;
            }
        }

        String komanda;

        if (cilveks.komanda.equals("Anarhija") ||
                (Main.komandasList.get(cilveks.komanda).biedri.get(_vards).rangs[1] == 0 &&
                        r.nextDouble() < Cilveku.dzimstotDefectionChance)) { //izveido savu komandu

            komanda = Komanda.jaunaKomanda(_vards); //nosauc t�va v�rd�, t�vs b�s karalis
            cilveks.komanda = komanda;

        } else { //paliek eso�aj� komand�
            komanda = cilveks.komanda;
        }

        Main.cilvekuList.put(vards,
                new Cilveks(vards,
                        xyz, vmax, omega,
                        hp, hpmax, paika,
                        R1, R2,
                        brunas, stiprums, gataviba, drosme,
                        komanda));

        Main.komandasList.get(komanda).pievienotiesKomandai(vards); //pievieno tai pa�ai komandai
    }

    public static void buveMaju(){
        //te var�s b�v�t m�ju -- pagaid�m nav pabeigts
    }


}
