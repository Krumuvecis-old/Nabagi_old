package localClient.map;

class Cilveki {

//    protected static void main(Graphics g, SetupThread thread, int x0, int y0, double merogs, ArrayList<ArrayList<MapChunk>> laukums, ArrayList<Komanda> komandasList) { //papildin�jums kartei
//
//        double resnumaKoefic = Fizikas.resnumaKoefic;
//
//        //iziet cauri visiem chunkiem
//        for(int[] chunkXY={0,0}; chunkXY[0]<laukums.size(); chunkXY[0]++){
//            for(chunkXY[1]=0; chunkXY[1]<laukums.get(chunkXY[0]).size(); chunkXY[1]++){
//
//                //p�rbauda visus sp�l�t�jus konkr�taj� chunk�
//                for(int i = 0; i<laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++){
//
//                    Cilveks cilveks = laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(i); //pats apskat�mais sp�l�t�js
//
//                    double resnums=resnumaKoefic*cilveks.hpmax*merogs;
//
//                    int komanda=noteiktKomandasNumuru(cilveks); //kr�sas noteik�anai
//                    Color krasa = noteiktKrasuCilvekam(cilveks, komanda);
//
//                    //rumpis
//
//                    double x = x0 + merogs * (cilveks.xyz.x + chunkXY[0] * KonstantesUniversal.mapChunkW),
//                            y = y0 + merogs * (cilveks.xyz.y + chunkXY[1] * KonstantesUniversal.mapChunkW);
//
//                    g.setColor(krasa); //iek�a
//                    g.fillOval((int)(x-resnums/2),
//                            (int)(y-resnums/2),
//                            (int)resnums, (int)resnums);
//                    g.setColor(Color.black);//kont�ra
//                    g.drawOval((int)(x-resnums/2),
//                            (int)(y-resnums/2),
//                            (int)resnums, (int)resnums);
//
//                    if(cilveks.vards.equals(komandasList.get(komanda).galvenais)) { //karalis
//                        drawKronis(g, x, y, resnums);
//                    }
//
//                    if(cilveks.vards.equals(thread.dati.playerFocusName)) { //fokus�t� sp�l�t�ja redzesloks
//                        drawRedzesloks(g, x, y, merogs, cilveks, krasa);
//
//                    }
//
//                }
//            }
//        }
//
//    }
//
//    private static int noteiktKomandasNumuru(Cilveks cilveks){
//        int komanda=0;
//
//        for (int i = 0; i< Main.komandasList.size(); i++) {
//            if(cilveks.komanda.equals(Main.komandasList.get(i).nosaukums)) {
//                komanda=i;
//                break;
//            }
//        }
//
//        return komanda;
//    }
//
//    private static Color noteiktKrasuCilvekam(Cilveks cilveks, int komanda){
//        double hpRatio=cilveks.hp/cilveks.hpmax;
//
//        return new Color(Color.HSBtoRGB( (float)Formulas.getHue(Main.komandasList.get(komanda).krasa),
//                (float)KonstantesGrafikai.cilvekiKrasaSaturation,
//                (float)(KonstantesGrafikai.cilvekiKrasaBrightnessMin + hpRatio *
//                        (KonstantesGrafikai.cilvekiKrasaBrightnessMax - KonstantesGrafikai.cilvekiKrasaBrightnessMin)) ));
//
//    }
//
//    private static void drawKronis(Graphics g, double x, double y, double resnums){
//        g.setColor(new Color(0,0,0)); //kro�a kr�sa - melns punkts
//        double kronaResnums=resnums/2;
//        g.fillOval((int)(x-kronaResnums/2),
//                (int)(y-kronaResnums/2),
//                (int)kronaResnums, (int)kronaResnums); //kronis
//    }
//
//    private static void drawRedzesloks(Graphics g, double x, double y, double merogs, Cilveks cilveks, Color krasa){
//        g.setColor(krasa);
//        double R2temp=cilveks.R2*merogs;
//        g.drawOval((int)(x-R2temp), (int)(y-R2temp), (int)(R2temp*2),(int)(R2temp*2)); //R2 - tuvais
//
//        double R1temp=cilveks.R1*merogs;
//        g.drawOval((int)(x-R1temp), (int)(y-R1temp), (int)(R1temp*2),(int)(R1temp*2)); //R1 - t�lais
//    }
}
