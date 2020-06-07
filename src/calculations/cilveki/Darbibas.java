package calculations.cilveki;

import calculations.Location;
import calculations.Main;
import calculations.komandas.KomanduApskats;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Formulas;

import java.util.Random;

public class Darbibas {

    public double vKoef=0, fi=0; //vçlamais kustîbas virziens un âtrums
    public String darbiba; //animâcijai un testiem

    protected void main(Cilveks cilveks){



        //kustibasParametri(cilveks); -- tiek izsaukts pie CilvekuManager.main() -> DefaultDarbibas.main()
    }

    protected void kustibasParametri(Cilveks cilveks){
        double hpKoef=cilveks.hp/cilveks.hpmax;

        double deltaFi = fi - cilveks.xyz.fi;
        cilveks.xyz.v = cilveks.vmax * vKoef * hpKoef * Math.cos(Math.toRadians(deltaFi));

        // leòía pakâpeniska pagrieðana
        if (deltaFi > 0) {
            cilveks.xyz.fi += Math.min(cilveks.omega, deltaFi);
        } else if (deltaFi < 0) {
            cilveks.xyz.fi += Math.max(cilveks.omega * (-1), deltaFi);
        }

    }

    public static void vairosanas(Location location) {
        Cilveks cilveks = Cilveks.getPlayer(location);

        Random r=new Random();

        Cilveks.maxCilveks++;
        String vards=Cilveku.vardsDefault+Cilveks.maxCilveks;

        Koord xyz = new Koord();
        xyz.x = cilveks.xyz.x;
        xyz.y = cilveks.xyz.y;
        xyz.v = 0;
        xyz.fi = 360*r.nextDouble();

        double vmax=Formulas.novirzeRandom(cilveks.vmax, Cilveku.dvMaxDzimstot),
                omega=Formulas.novirzeRandom(cilveks.omega, Cilveku.dommaxDzimstot);
        double hpmax=Cilveku.hpmax, hp=hpmax;
        double paika=Cilveku.paikaMax;
        double R2=Formulas.novirzeRandom(cilveks.R2, Cilveku.dRDzimstot),
                R1=Formulas.novirzeRandom(cilveks.R1, Cilveku.dRDzimstot);
        double brunas = Formulas.novirzeRandom(cilveks.brunas, Cilveku.dBrunasDzimstot);;
        double stiprums = Formulas.novirzeRandom(cilveks.stiprums, Cilveku.dStiprumsDzimstot);
        double gataviba=Cilveku.maxGataviba, drosme=cilveks.drosme;

        for(int i=0; i<cilveks.inventory.size(); i++) {
            if (cilveks.inventory.get(i).nosaukums.equals("Zelts")) {
                cilveks.inventory.get(i).daudzums-=
                        (Cilveku.cenaCilvekam + Cilveku.mantojumsCilvekamZelts) / cilveks.inventory.get(i).zelts;
            }
            if (cilveks.inventory.get(i).nosaukums.equals("Paika")) {
                cilveks.inventory.get(i).daudzums-=
                        Cilveku.mantojumsCilvekamPaika / cilveks.inventory.get(i).paika;
            }
        }


        String komanda;
        int[] rangs= new int[2];
        if ((r.nextDouble()<Cilveku.dzimstotDefectionChance && cilveks.rangs[1]==0 ) ||
                cilveks.komanda.equals("Anarhija")) { //izveido savu komandu

            KomanduApskats.jaunaKomanda(cilveks.vards);//nosauc tçva vârdâ, tçvs bûs karalis
            komanda= Main.komandasList.get(Main.komandasList.size()-1).nosaukums;
            System.out.println("izveidota komanda "+komanda);
            cilveks.komanda = komanda;
            cilveks.rangs[0]=0;
            cilveks.rangs[1]=3;
            rangs[0]=0;
            rangs[1]=0;

        } else { //paliek esoðajâ  komandâ
            komanda=cilveks.komanda;
            System.out.println(cilveks.vards+" dzemdç jaunu komandâ "+komanda);
            rangs[0]=0; //vienmçr bûs zemnieks
            rangs[1]=0;
        }

        Cilveks.dzemdibas(vards,location.chunkXY,xyz,vmax,omega,hp,hpmax,paika,
                R1,R2,brunas,stiprums,gataviba,drosme,komanda,rangs);
    }

    public static void buveMaju(){
        //te varçs bûvçt mâju -- pagaidâm nav pabeigts
    }


}
