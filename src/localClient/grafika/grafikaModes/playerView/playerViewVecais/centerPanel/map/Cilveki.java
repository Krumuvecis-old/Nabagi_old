package localClient.grafika.grafikaModes.playerView.playerViewVecais.centerPanel.map;

class Cilveki {

//    protected void main(Graphics g) {
//
//        //zem�k viss kop�ts no vec� drawMap
//
////        double resnumaKoefic= FizikasKonstantes.resnumaKoefic;
////
////        for(int[] chunkXY={0,0}; chunkXY[0]<laukums.size(); chunkXY[0]++) {
////            for (; chunkXY[1] < laukums.get(chunkXY[0]).size(); chunkXY[1]++) {
////
////                ArrayList<Lieta> lietasList = laukums.get(chunkXY[0]).get(chunkXY[1]).lietas;
////                ArrayList<Cilveks> cilvekiList = laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList;
////
////                for(int i=0;i<cilvekiList.size();i++) {
////
////                    Cilveks player = cilvekiList.get(i);
////
////                    double dx = x0-player.xyz.x,
////                            dy = y0-player.xyz.y;
////
////                    if (Math.hypot(dx, dy) > R2) continue; //lai atmet tos kas par t�lu (true - par t�lu)
////
////                    double resnums=merogs*resnumaKoefic*player.hpmax;
////
////                    int komanda = playerGetKomanda(player);
////                    Color krasa = playerGetColor(player, komanda); //p�c komandas nosaka kr�su
////
////                    double[] koord;
////                    if (player==thread.dati.player) { //z�m� galveno sp�l�t�ju pa�� centr�
////                        koord=getAbsoluteCoordinates(true, dx, dy);
////
////                    } else { //z�m� p�r�jos sp�l�t�jus
////                        koord=getAbsoluteCoordinates(false, dx, dy);
////
////                    }
////
////                    drawPlayer(g, i, player, komanda, koord, resnums, krasa);
////
////
////                }
////
////
////            }
////        }
//
//        //augst�k viss kop�ts no vec� drawMap
//
//    }
//
//
//    private int playerGetKomanda(Cilveks player) {
//        int komanda=0;
//        for (int i=0; i<CalculationsThread.komandasList.size(); i++) {
//            if(player.komanda.equals(CalculationsThread.komandasList.get(i).nosaukums)) {
//                komanda=i;
//                break;
//            }
//        }
//        return komanda;
//    }
//
//    private Color playerGetColor(Cilveks player, int komanda) {
//
//        double hpRatio=player.hp/player.hpmax;
//
//        return new Color(Color.HSBtoRGB(
//                (float) Formulas.getHue(CalculationsThread.komandasList.get(komanda).krasa),
//                (float) GrafikasDati.cilvekiKrasaSaturation,
//                (float)(GrafikasDati.cilvekiKrasaBrightnessMin +
//                        (GrafikasDati.cilvekiKrasaBrightnessMax -
//                                GrafikasDati.cilvekiKrasaBrightnessMin) * hpRatio)));
//
//    }
//
//    //zem�k viss kop�ts no vec� drawMap
////
////    private void drawPlayer(Graphics g, int number, Cilveks player, int komanda, double[] koord, double resnums, Color krasa) {
////        //rumpis
////        g.setColor(krasa); //iek�a
////        g.fillOval((int)(koord[0] - resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
////
////        g.setColor(Color.black);//kont�ra
////        g.drawOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
////
////        if(player.vards.equals( CalculationsThread.komandasList.get(komanda).galvenais )) { //karalis
////
////            Color kronaKrasa = GrafikasDati.kronaKrasa;
////            double kronaKoeficients=GrafikasDati.kronaKoeficients;
////
////            g.setColor(kronaKrasa);
////            double kronaResnums=resnums*kronaKoeficients;
////            g.fillOval((int)(koord[0]-kronaResnums/2), (int)(koord[1]-kronaResnums/2),
////                    (int)kronaResnums, (int)kronaResnums); //kronis
////        }
////
////
////        if(thread.dati.cilvekiDrawR) { //redzesloks
////            double R1temp=player.R1*merogs, R2temp=player.R2*merogs;
////            g.setColor(krasa);
////            g.drawOval((int)(koord[0]-R1temp), (int)(koord[1]-R1temp), (int)(R1temp*2),(int)(R1temp*2)); //R1 - tuvais
////            g.drawOval((int)(koord[0]-R2temp), (int)(koord[1]-R2temp), (int)(R2temp*2),(int)(R2temp*2)); //R2 - t�lais
////        }
////
////        if (thread.dati.cilvekiDrawInfo) { //nosaukumi un papildinform�cija
////            g.setColor(krasa);
////            playerDrawInfo(g, number, player, koord, resnums);
////        }
////
////
////    }
////
////    private void playerDrawInfo(Graphics g, int i, Cilveks player, double[] koord, double resnums) {
////
////        //saskaita cik kuram paika un zelts, lai var�tu izvad�t
////        double zeltsSum=player.countItemAmount(player.searchInventory("Zelts", false));
////        double paikaSum=player.countItemAmount(player.searchInventory("Paika", false));
////
////        //g.setColor(new Color(255,100,200)); //default kr�sa?
////        int textSize=15;
////        String virsraksts = player.komanda+" - "+player.vards;//+" - ["+player.rangs[0]+"-"+player.rangs[1]+"] - "+(int)(player.hp/player.hpmax*100)+"%";
////
////        int w0=1; //pirm� rinda (1 zem sp�l�t�ja)
////        String[] tekstListe = new String[]{
////                "ATK:"+(int)(player.stiprums)+" DEF:"+(int)(player.brunas),
////                "drosme: "+(int)(player.drosme*1000),
////                //"gataviba: "+  player.gataviba,
////                //"lietas: "+player.inventory.size(),
////                "Z"+(int)(zeltsSum)+" P"+(int)(paikaSum),
////                //player.darbiba,
////                //"paika: "+(int)(player.paika/player.paikaMax*100)+"%" //paikas proporcija
////
////        };
////
////
////        String[] tekstListe2;
////
////        if (player.orderi.size()>0) {
////            if(player.orderi.get(0).perk) {
////                tekstListe2 = new String[]{
////                        player.orderi.get(0).prece+" "+(int)player.orderi.get(0).daudzums+" - perk",
////                };
////            } else {
////                tekstListe2 = new String[]{
////                        player.orderi.get(0).prece+" "+(int)player.orderi.get(0).daudzums+" - pardod",
////                };
////            }
////
////        } else {
////            tekstListe2 = new String[] {
////                    ""
////            };
////        }
////
////
////        g.drawString(virsraksts, (int)(koord[0]-30), (int)(koord[1]-resnums/2-5));
////
////        for (int w=0; w<tekstListe.length;w++) {
////            g.drawString(tekstListe[w], (int)(koord[0]-30), (int)(koord[1]+resnums/2+textSize*(w+w0)));
////        }
////
////		/*g.drawString("orderi "+player.orderi.size(),
////				(int)(koord[0]-30), (int)(koord[1]+resnums/2+textSize*(w0+tekstListe.length)));
////		*/
////        for (int w=0;w<tekstListe2.length;w++) {
////            g.drawString(tekstListe2[w], (int)(koord[0]-30), (int)(koord[1]+resnums/2+textSize*(w+w0+tekstListe.length)));
////        }
////    }
////
////
////    //augst�k viss kop�ts no vec� drawMap
////
////    //zem�k viss kop�ts no setupView
////
////    protected static void main(Graphics g, ClientThread thread, int x0, int y0, double merogs, ArrayList<ArrayList<MapChunk>> laukums, ArrayList<Komanda> komandasList) { //papildin�jums kartei
////
////        double resnumaKoefic = FizikasKonstantes.resnumaKoefic;
////
////        //iziet cauri visiem chunkiem
////        for(int[] chunkXY={0,0}; chunkXY[0]<laukums.size(); chunkXY[0]++){
////            for(chunkXY[1]=0; chunkXY[1]<laukums.get(chunkXY[0]).size(); chunkXY[1]++){
////
////                //p�rbauda visus sp�l�t�jus konkr�taj� chunk�
////                for(int i = 0; i<laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++){
////
////                    Cilveks cilveks = laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(i); //pats apskat�mais sp�l�t�js
////
////                    double resnums=resnumaKoefic*cilveks.hpmax*merogs;
////
////                    int komanda=noteiktKomandasNumuru(cilveks); //kr�sas noteik�anai
////                    Color krasa = noteiktKrasuCilvekam(cilveks, komanda);
////
////                    //rumpis
////
////                    double x = x0 + merogs * (cilveks.xyz.x + chunkXY[0] * KonstantesUniversal.mapChunkW),
////                            y = y0 + merogs * (cilveks.xyz.y + chunkXY[1] * KonstantesUniversal.mapChunkW);
////
////                    g.setColor(krasa); //iek�a
////                    g.fillOval((int)(x-resnums/2),
////                            (int)(y-resnums/2),
////                            (int)resnums, (int)resnums);
////                    g.setColor(Color.black);//kont�ra
////                    g.drawOval((int)(x-resnums/2),
////                            (int)(y-resnums/2),
////                            (int)resnums, (int)resnums);
////
////                    if(cilveks.vards.equals(komandasList.get(komanda).galvenais)) { //karalis
////                        drawKronis(g, x, y, resnums);
////                    }
////
////                    if(cilveks.vards.equals(thread.dati.playerFocusName)) { //fokus�t� sp�l�t�ja redzesloks
////                        drawRedzesloks(g, x, y, merogs, cilveks, krasa);
////
////                    }
////
////                }
////            }
////        }
////
////    }
////
////    private static int noteiktKomandasNumuru(Cilveks cilveks){
////        int komanda=0;
////
////        for (int i = 0; i< CalculationsThread.komandasList.size(); i++) {
////            if(cilveks.komanda.equals(CalculationsThread.komandasList.get(i).nosaukums)) {
////                komanda=i;
////                break;
////            }
////        }
////
////        return komanda;
////    }
////
////    private static Color noteiktKrasuCilvekam(Cilveks cilveks, int komanda){
////        double hpRatio=cilveks.hp/cilveks.hpmax;
////
////        return new Color(Color.HSBtoRGB( (float)Formulas.getHue(CalculationsThread.komandasList.get(komanda).krasa),
////                (float)GrafikasDati.cilvekiKrasaSaturation,
////                (float)(GrafikasDati.cilvekiKrasaBrightnessMin + hpRatio *
////                        (GrafikasDati.cilvekiKrasaBrightnessMax - GrafikasDati.cilvekiKrasaBrightnessMin)) ));
////
////    }
////
////    private static void drawKronis(Graphics g, double x, double y, double resnums){
////        g.setColor(new Color(0,0,0)); //kro�a kr�sa - melns punkts
////        double kronaResnums=resnums/2;
////        g.fillOval((int)(x-kronaResnums/2),
////                (int)(y-kronaResnums/2),
////                (int)kronaResnums, (int)kronaResnums); //kronis
////    }
////
////    private static void drawRedzesloks(Graphics g, double x, double y, double merogs, Cilveks cilveks, Color krasa){
////        g.setColor(krasa);
////        double R2temp=cilveks.R2*merogs;
////        g.drawOval((int)(x-R2temp), (int)(y-R2temp), (int)(R2temp*2),(int)(R2temp*2)); //R2 - tuvais
////
////        double R1temp=cilveks.R1*merogs;
////        g.drawOval((int)(x-R1temp), (int)(y-R1temp), (int)(R1temp*2),(int)(R1temp*2)); //R1 - t�lais
////    }

    //augst�k viss kop�ts no setupView
}
