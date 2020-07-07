package calculations.cilveki.ai;

import calculations.Main;
import calculations.cilveki.Cilveks;
import calculations.cilveki.Darbibas;
import calculations.konstantes.Cilveku;
import calculations.konstantes.Formulas;
import calculations.lietas.Lieta;

import java.util.ArrayList;
import java.util.Random;

public class MediumRange {

//    protected static boolean main(Cilveks cilveks, Location location){
//        boolean nolemts=false;
//
//        //      spçlçtâju apskats, lai novçrtçtu savçjos&pretiniekus
//        int numurs=123;
//        cilvekuSalidzinajums(numurs);
//
//        double zeltsSum=cilveks.countItemAmount(cilveks.searchInventory("Zelts", false));
//        double paikaSum=cilveks.countItemAmount(cilveks.searchInventory("Paika", false));
//
//        komanduMaina(cilveks, zeltsSum, paikaSum,1.5, 5, 0.01, 0.01);
//        vairosanasParbaude(location, zeltsSum, paikaSum);
//
//        //      loot vâkðanas apskats
//        numurs=234;
//        lootApskatsMeklesanai(numurs);
//
//        //      tirdzniecîbas partneru meklçðana
//
//        //      citas opcijas nâkotnei
//
//
//        return nolemts;
//    }
//
//    private static void cilvekuSalidzinajums(int numurs){
//        //apskata pârçjos spçlçtâjus
//
//        Location location = Cilveks.cilvekuListPilnais.get(numurs);
//        Cilveks cilveks = Cilveks.getPlayer(location);
//        double hpRatio=cilveks.hp/cilveks.hpmax;
//
//
//
////        //double[] pretiniekuXYR1=new double[] {pretiniekuXR1/pretiniekuSkaitsR1, pretiniekuYR1/pretiniekuSkaitsR1};
////       // double[] pretiniekuXYR2=new double[] {pretiniekuXR2/pretiniekuSkaitsR2, pretiniekuYR2/pretiniekuSkaitsR2};
////
////        //double[] savejoXYR1=new double[] {savejoXR1/savejoSkaitsR1,savejoYR1/savejoSkaitsR1};
////        //double[] savejoXYR2=new double[] {savejoXR2/savejoSkaitsR2,savejoYR2/savejoSkaitsR2};
////
////        perkBiedrs.i=-1;
////        pardodBiedrs.i=-1;
////        int orderisPerkNr=-1, orderisPardodNr=-1;
////        String preceTirgo=" ";
////        double apjomsTirgo=0;
////        double cenaTirgo= Cilveku.paikaPriceDefault;// temporary default  value
////        double[] jTirgoXY=new double[2];
////
////        int chunkViewDistance=1;
////        for(int[] dChunkXY={-chunkViewDistance,-chunkViewDistance}; dChunkXY[0]<=chunkViewDistance; dChunkXY[0]++){
////            for( ; dChunkXY[1]<=chunkViewDistance; dChunkXY[1]++){
////
////                int[] chunkXY = {biedrs.chunkXY[0]+dChunkXY[0], biedrs.chunkXY[1]+dChunkXY[1]};
////                ArrayList<Cilveks> cilvekiList = Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList;
////
////                for(int i = 0; i < cilvekiList.size(); i++) {
////                    if (biedrs.chunkXY==chunkXY && biedrs.i==i) { continue; }//ar sevi nesalîdzina
////
////                    Cilveks cilveks2 = Cilveks.getPlayer(chunkXY, i);
////
////                    //pie dx un dy jâpieskaita chunk nobîdes radîtâs izmaiòas --- !!!!!!
////                    double dx=cilveks.xyz.x - cilveks2.xyz.x,
////                            dy=cilveks.xyz.y - cilveks2.xyz.y;
////
////
////
////                        if(distance>cilveks.R1) continue; //lai nesalîdzina sîkâk, ko nevajag
////                        //sâkas R1
////
////                        if (!cilveks.komanda.equals(cilveks2.komanda) ||
////                                cilveks2.komanda.equals("Anarhija")) {
////                            pretiniekuSkaitsR1++;
////                            pretiniekuXR1+= cilveks2.xyz.x;
////                            pretiniekuYR1+= cilveks2.xyz.y;
////                            pretiniekuStiprumsR1+= cilveks2.stiprums*hpRatio2;
////                            pretiniekuBrunasR1+= cilveks2.brunas;
////                        }
////
////                        if (cilveks.komanda.equals(cilveks2.komanda) &&
////                                !cilveks.komanda.equals("Anarhija")) {
////                            savejoSkaitsR1++;
////                            savejoXR1+= cilveks2.xyz.x;
////                            savejoYR1+= cilveks2.xyz.y;
////                            savejoStiprumsR1+= cilveks2.stiprums*hpRatio2;
////                            savejoBrunasR1+= cilveks2.brunas;
////                        }
////
////                        //sadursme
////
////
////                    }
////                }
////
////
////            }
////        }
//
//    }
//
//
//    protected static void komanduMaina(Cilveks cilveks, double zeltsSum, double paikaSum,
//                                       double paikaMaina, double zeltsMaina, double anarchyChance, double orderChance) {
//
//        Random r = new Random();
//        if (cilveks.rangs[1]==0 && paikaSum<paikaMaina && zeltsSum<zeltsMaina) { //tikai pirmâ lîmeòa spçlçtâji varçs mainît komandu
//            if (Main.komandasList.size()>1 && (cilveks.komanda==Main.komandasList.get(0).nosaukums && r.nextDouble()<orderChance)) { //ja ir bez komandas, pievienojas citai (izvçlâs  random, bet ne 0)
//                cilveks.komanda=Main.komandasList.get(r.nextInt(Main.komandasList.size()-1)+1).nosaukums;
//            } else if (r.nextDouble()<anarchyChance && cilveks.komanda!=Main.komandasList.get(0).nosaukums) { //iespçja, ka pametîs esoðo komandu (tikai, ja kâdâ ir komandâ)
//                cilveks.komanda=Main.komandasList.get(0).nosaukums;
//            }
//        }
//    }
//
//    private static void vairosanasParbaude(Location location, double zeltsSum, double paikaSum){
//
//        double cenaCilvekam= Cilveku.cenaCilvekam,
//                mantojumsCilvekamZelts=Cilveku.mantojumsCilvekamZelts,
//                mantojumsCilvekamPaika=Cilveku.mantojumsCilvekamPaika;
//
//        if (zeltsSum>=(cenaCilvekam+mantojumsCilvekamZelts) &&
//                paikaSum>=mantojumsCilvekamPaika*2) { //paikas  mantojums x2 lai paðam arî paliktu
//
//            Darbibas.vairosanas(location);
//        }
//    }
//
//
//    private static void lootApskatsMeklesanai(int numurs) {
////        Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
////        Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY,  biedrs.i);
////
////        //double resnums=resnumaKoefic*cilveks.hpmax;
////
////
////        zeltsTuvakaisNr = 0; //numurs J
////        paikaTuvakaNr = 0;
////
////        zeltsTuvakaisDist=-1; //distance
////        paikaTuvakaDist=-1;
////
////        for(int i = 0; i< Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.size(); i++){
////            double distance = Math.hypot(cilveks.xyz.x- Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.get(i).x,
////                    cilveks.xyz.y- Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.get(i).y);
////
////			/*double resnumsJ; //nosaka lietas resnumu
////			if (Main.lietas.get(j).nosaukums=="Zelts") { resnumsJ = zeltaResnums;
////			} else if (Main.lietas.get(j).nosaukums=="Paika") { resnumsJ = paikasResnums;
////			} else resnumsJ = lietasResnums;*/
////
////            if(Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.get(i).nosaukums=="Zelts") {
////                if (distance<zeltsTuvakaisDist || (zeltsTuvakaisDist<0 && distance<=cilveks.R2)) {
////                    zeltsTuvakaisDist = distance;
////                    zeltsTuvakaisNr=i;
////                }
////            }
////
////            if(Main.laukums.get(biedrs.chunkXY[0]).get(biedrs.chunkXY[1]).lietas.get(i).nosaukums=="Paika") {
////                if (distance<paikaTuvakaDist || (paikaTuvakaDist<0 && distance<=cilveks.R2)) {
////                    paikaTuvakaDist = distance;
////                    paikaTuvakaNr=i;
////                }
////            }
////
////        }
//    }

}
