package calculations.cilveki.ai;

import calculations.cilveki.Cilveks;

public class FarRange {

    static boolean main(String vards){
        boolean nolemts=false;

        //      cilv�ku apskats
        cilvekuApskats(vards);
        //      loot apskats
        lietuApskats(vards);

        return nolemts;
    }

    private static boolean cilvekuApskats(String vards){
        boolean nolemts=false;


//        //zem�k izgriezts no vec� koda
//
//        double distance = Math.hypot(dx, dy);
//
//        if(distance>cilveks.R2) { continue; } else { //atmet pavisam t�los
//
//            //s�kas R2
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
//                //te  vajadz�tu ar� p�rbaud�t tirdzniec�bas cenas un  saglab�t atmi��
//
//
//                if (cilveks.orderi.size() > 0) { //p�rbauda tirdzniec�bas iesp�jas, bet tikai starp saviem komandasbiedriem
//
//                    for (int w1 = 0; w1 < cilveks.orderi.size(); w1++) { // apskata savus orderus
//
//                        if (apjomsTirgo > 0) break;
//
//                        System.out.println(cilveks.vards + " sal�dzina sevi ar " + cilveks2.vards);
//
//                        for (int w2 = 0; w2 < cilveks2.orderi.size(); w2++) { // apskata otra orderus
//
//                            if (cilveks.orderi.get(w1).prece.equals(cilveks2.orderi.get(w2).prece) &&
//                                    (cilveks.orderi.get(w1).perk != cilveks2.orderi.get(w2).perk)) { //ja abiem sakr�t v�lmes un at��iras virzieni
//
//
//                                preceTirgo = cilveks.orderi.get(w1).prece;
//                                apjomsTirgo = Math.min(cilveks.orderi.get(w1).daudzums, cilveks2.orderi.get(w2).daudzums);
//
//                                if (apjomsTirgo > 0) { //p�rbauda vai nesola tuk�u
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
//        //augst�k izgriezts no vec� koda

        return nolemts;
    }

    private static boolean lietuApskats(String vards){
        boolean nolemts = false;

            //�eit paredzams liel�  redzesloka virspus�jais redzesloks

        return nolemts;
    }

}
