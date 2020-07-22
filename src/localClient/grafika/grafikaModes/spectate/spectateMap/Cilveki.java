package localClient.grafika.grafikaModes.spectate.spectateMap;

import localClient.grafika.grafikaParts.DrawManager;
import server.calculations.cilveki.Cilveks;
import server.dataBase.DataBase;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

class Cilveki {

    Cilveki(){

    }

    void draw(Graphics g, DrawManager.SpectateMapInfo spectateMapInfo,
              double chunkSizeGraphical, int[] chunkLoc, int[] chunkXY){

        List<Integer> _chunkXY = new ArrayList<>();
        _chunkXY.add(chunkXY[0]);
        _chunkXY.add(chunkXY[1]);
        for(String vards : DataBase.laukums.get(_chunkXY).cilvekiList){
            drawCilveks(g, vards);
        }

    }

    private void drawCilveks(Graphics g, String vards){
        Cilveks cilveks = DataBase.cilvekuList.get(vards);

        drawMainSprite();
        drawExtras();
        drawInfo();


        //zem�k no vec�

//        double resnums=resnumaKoefic*cilveks.hpmax*merogs;
//
//        int komanda=noteiktKomandasNumuru(cilveks); //kr�sas noteik�anai
//        Color krasa = noteiktKrasuCilvekam(cilveks, komanda);
//
//        //rumpis
//
//        double x = x0 + merogs * (cilveks.xyz.x + chunkXY[0] * KonstantesUniversal.mapChunkW),
//                y = y0 + merogs * (cilveks.xyz.y + chunkXY[1] * KonstantesUniversal.mapChunkW);
//
//        g.setColor(krasa); //iek�a
//        g.fillOval((int)(x-resnums/2),
//                (int)(y-resnums/2),
//                (int)resnums, (int)resnums);
//        g.setColor(Color.black);//kont�ra
//        g.drawOval((int)(x-resnums/2),
//                (int)(y-resnums/2),
//                (int)resnums, (int)resnums);
//
//        if(cilveks.vards.equals(komandasList.get(komanda).galvenais)) { //karalis
//            drawKronis(g, x, y, resnums);
//        }
//
//        if(cilveks.vards.equals(thread.dati.playerFocusName)) { //fokus�t� sp�l�t�ja redzesloks
//            drawRedzesloks(g, x, y, merogs, cilveks, krasa);
//
//        }

    }

    private void drawMainSprite(){
        //galven�s bild�tes z�m��ana, kas pagriezta le��� fi


        //zem�k no vec�

        //double cilvekiKrasaSaturation=1;
        //double cilvekiKrasaBrightnessMin=0.4; //pie hpRatio=0
        //double cilvekiKrasaBrightnessMax=1; //pie hpRatio=1

        //private static int noteiktKomandasNumuru(Cilveks cilveks){
//        int komanda=0;
//
//        for (int i = 0; i< CalculationsThread.komandasList.size(); i++) {
//            if(cilveks.komanda.equals(CalculationsThread.komandasList.get(i).nosaukums)) {
//                komanda=i;
//                break;
//            }
//        }
//
//        return komanda;
//    }


        //    private static Color noteiktKrasuCilvekam(Cilveks cilveks, int komanda){
//        double hpRatio=cilveks.hp/cilveks.hpmax;
//
//        return new Color(Color.HSBtoRGB( (float)Formulas.getHue(CalculationsThread.komandasList.get(komanda).krasa),
//                (float)GrafikasDati.cilvekiKrasaSaturation,
//                (float)(GrafikasDati.cilvekiKrasaBrightnessMin + hpRatio *
//                        (GrafikasDati.cilvekiKrasaBrightnessMax - GrafikasDati.cilvekiKrasaBrightnessMin)) ));
//
//    }

    }

    private void drawExtras(){
        //te var ielikt kroni karalim un lietas rok�s


        //zem�k no vec�

        //Color kronaKrasa = new Color(0,0,0); //kro�a kr�sa - melns  punkts
        //double kronaKoeficients=0.5; //kro�a resnums pret kop�jo resnumu

//      private static void drawKronis(Graphics g, double x, double y, double resnums){
//        g.setColor(new Color(0,0,0)); //kro�a kr�sa - melns punkts
//        double kronaResnums=resnums/2;
//        g.fillOval((int)(x-kronaResnums/2),
//                (int)(y-kronaResnums/2),
//                (int)kronaResnums, (int)kronaResnums); //kronis
//    }

    }

    private void drawInfo(){
        //te var izvad�t inform�ciju un parametrus


        //zem�k no vec�

//    private static void drawRedzesloks(Graphics g, double x, double y, double merogs, Cilveks cilveks, Color krasa){
//        g.setColor(krasa);
//        double R2temp=cilveks.R2*merogs;
//        g.drawOval((int)(x-R2temp), (int)(y-R2temp), (int)(R2temp*2),(int)(R2temp*2)); //R2 - tuvais
//
//        double R1temp=cilveks.R1*merogs;
//        g.drawOval((int)(x-R1temp), (int)(y-R1temp), (int)(R1temp*2),(int)(R1temp*2)); //R1 - t�lais
//    }

    }



}
