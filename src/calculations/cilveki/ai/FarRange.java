package calculations.cilveki.ai;

import calculations.cilveki.Cilveks;

public class FarRange {

    static boolean main(String vards){
        boolean nolemts=false;

        //      cilvçku apskats
        cilvekuApskats(vards);
        //      loot apskats
        lietuApskats(vards);

        return nolemts;
    }

    private static boolean cilvekuApskats(String vards){
        boolean nolemts=false;


//        //zemâk izgriezts no vecâ koda
//
//        double distance = Math.hypot(dx, dy);
//
//        if(distance>cilveks.R2) { continue; } else { //atmet pavisam tâlos
//
//            //sâkas R2
//
//            double hpRatio2 = cilveks2.hp / cilveks2.hpmax;
//
//            if (!cilveks.komanda.equals(cilveks2.komanda) ||
//                    cilveks2.komanda.equals("Anarhija")) {
//                pretiniekuSkaitsR2++;
//                pretiniekuXR2 += cilveks2.xyz.x;
//                pretiniekuYR2 += cilveks2.xyz.y;
//                pretiniekuStiprumsR2 += cilveks2.stiprums * hpRatio2;
//                pretiniekuBrunasR2 += cilveks2.brunas;
//            }
//
//            if (cilveks.komanda.equals(cilveks2.komanda) && !cilveks.komanda.equals("Anarhija")) {
//                savejoSkaitsR2++;
//                savejoXR2 += cilveks2.xyz.x;
//                savejoYR2 += cilveks2.xyz.y;
//                savejoStiprumsR2 += cilveks2.stiprums * hpRatio2;
//                savejoBrunasR2 += cilveks2.brunas;
//
//                //te  vajadzçtu arî pârbaudît tirdzniecîbas cenas un  saglabât atmiòâ
//
//
//                if (cilveks.orderi.size() > 0) { //pârbauda tirdzniecîbas iespçjas, bet tikai starp saviem komandasbiedriem
//
//                    for (int w1 = 0; w1 < cilveks.orderi.size(); w1++) { // apskata savus orderus
//
//                        if (apjomsTirgo > 0) break;
//
//                        System.out.println(cilveks.vards + " salîdzina sevi ar " + cilveks2.vards);
//
//                        for (int w2 = 0; w2 < cilveks2.orderi.size(); w2++) { // apskata otra orderus
//
//                            if (cilveks.orderi.get(w1).prece.equals(cilveks2.orderi.get(w2).prece) &&
//                                    (cilveks.orderi.get(w1).perk != cilveks2.orderi.get(w2).perk)) { //ja abiem sakrît vçlmes un atðíiras virzieni
//
//
//                                preceTirgo = cilveks.orderi.get(w1).prece;
//                                apjomsTirgo = Math.min(cilveks.orderi.get(w1).daudzums, cilveks2.orderi.get(w2).daudzums);
//
//                                if (apjomsTirgo > 0) { //pârbauda vai nesola tukðu
//                                    jTirgoXY[0] = cilveks2.xyz.x;
//                                    jTirgoXY[1] = cilveks2.xyz.y;
//
//                                    if (cilveks.orderi.get(w1).perk) {
//                                        perkBiedrs = biedrs;
//                                        orderisPerkNr = w1;
//                                        pardodBiedrs.i = i;
//                                        pardodBiedrs.chunkXY = chunkXY;
//                                        orderisPardodNr = w2;
//                                    } else {
//                                        perkBiedrs.i = i;
//                                        perkBiedrs.chunkXY = chunkXY;
//                                        orderisPerkNr = w2;
//                                        pardodBiedrs = biedrs;
//                                        orderisPardodNr = w1;
//                                    }
//
//                                    break;
//                                }
//
//                            }
//                        }
//                    }
//                    // partneris atrasts, prece noskaidrota;
//                }
//            }
//
//        }
//
//        //augstâk izgriezts no vecâ koda

        return nolemts;
    }

    private static boolean lietuApskats(String vards){
        boolean nolemts = false;

            //ðeit paredzams lielâ  redzesloka virspusçjais redzesloks

        return nolemts;
    }

}
