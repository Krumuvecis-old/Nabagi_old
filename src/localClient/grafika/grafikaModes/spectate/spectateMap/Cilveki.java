package localClient.grafika.grafikaModes.spectate.spectateMap;

import localClient.Dati;
import localClient.grafika.GrafikasDati;
import localClient.grafika.grafikaParts.DrawManager;
import server.calculations.Formulas;
import server.calculations.cilveki.Cilveks;
import server.dataBase.DataBase;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

class Cilveki {

    Cilveki(){}

    void draw(Graphics g, Dati dati, DrawManager.SpectateMapInfo spectateMapInfo,
              int[] chunkLoc, int[] chunkXY){
        //iziet cauri visiem cilv�kiem

        List<Integer> _chunkXY = new ArrayList<>();
        _chunkXY.add(chunkXY[0]);
        _chunkXY.add(chunkXY[1]);
        for(String vards : DataBase.laukums.get(_chunkXY).cilvekiList)
            drawCilveks(g, dati, spectateMapInfo, vards, chunkLoc);
    }

    private void drawCilveks(Graphics g, Dati dati, DrawManager.SpectateMapInfo spectateMapInfo,
                             String vards, int[] chunkLoc){
        //katra cilv�ka individu�la z�m��ana

        Cilveks cilveks = DataBase.cilvekuList.get(vards);
        double[] cilveksLoc = new double[]{
                chunkLoc[0] + cilveks.xyz.x * spectateMapInfo.merogs,
                chunkLoc[1] + cilveks.xyz.y * spectateMapInfo.merogs};
        Color playerColor = noteiktKrasuCilvekam(cilveks);

        double resnums = cilveks.resnums * spectateMapInfo.merogs;
        int detailedDrawingLimit = 8; //pret resnumu
        if(resnums >= detailedDrawingLimit){
            drawBodyBackground(g, cilveksLoc, resnums, playerColor, false);
            drawMainSprite(g, dati, cilveks, cilveksLoc, resnums);
            drawExtras();
            drawInfo();
        } else {
            drawBodyBackground(g, cilveksLoc, detailedDrawingLimit, playerColor, true);
        }


    }

    private Color noteiktKrasuCilvekam(Cilveks cilveks){
        double hpRatio = cilveks.hp / cilveks.hpmax;

        double cilvekiKrasaSaturation = 1;
        double cilvekiKrasaBrightnessMin = 0.4; //pie hpRatio=0
        double cilvekiKrasaBrightnessMax = 1; //pie hpRatio=1

        return new Color(Color.HSBtoRGB(
                (float)Formulas.getHue(DataBase.komandasList.get(cilveks.komanda).krasa),
                (float)cilvekiKrasaSaturation,
                (float)(cilvekiKrasaBrightnessMin + hpRatio * (cilvekiKrasaBrightnessMax - cilvekiKrasaBrightnessMin))));
    }

    private void drawBodyBackground(Graphics g, double[] cilveksLoc, double resnums, Color playerColor, boolean contour){
        int x = (int)(cilveksLoc[0] - resnums / 2),
                y = (int)(cilveksLoc[1] - resnums / 2);

        g.setColor(playerColor); //iekr�so rumpi cilv�ka kr�s�
        g.fillOval(x, y, (int)resnums, (int)resnums);

        if(contour){
            g.setColor(Color.black); //kont�ra ap rumpi
            g.drawOval(x, y, (int)resnums, (int)resnums);
        }

    }

    private void drawMainSprite(Graphics g, Dati dati, Cilveks cilveks, double[] cilveksLoc, double resnums){
        //galven�s bild�tes z�m��ana, kas pagriezta le��� fi

        GrafikasDati.drawRotatedImage(g, dati.grafikasDati, "cilveks",
                new int[]{(int)cilveksLoc[0], (int)cilveksLoc[1]},
                new int[]{(int)resnums, (int)resnums},
                cilveks.xyz.fi - 90);
    }

    private void drawExtras(){
        //no vec� par kro�a z�m��anu karalim

        //if(cilveks.vards.equals(komandasList.get(komanda).galvenais)) { //karalis
//            drawKronis(g, x, y, resnums);
//        }

        //Color kronaKrasa = new Color(0,0,0); //kro�a kr�sa - melns  punkts
        //double kronaKoeficients=0.5; //kro�a resnums pret kop�jo resnumu

//      private static void drawKronis(Graphics g, double x, double y, double resnums){
//        g.setColor(new Color(0,0,0)); //kro�a kr�sa - melns punkts
//        double kronaResnums=resnums/2;
//        g.fillOval((int)(x-kronaResnums/2),
//                (int)(y-kronaResnums/2),
//                (int)kronaResnums, (int)kronaResnums); //kronis
//    }


        //te var ielikt, ka z�m� lietas, kas rok�s
    }

    private void drawInfo(){
        //te var izvad�t inform�ciju un parametrus


        //no vec� par redzesloka z�m��anu

        //if(cilveks.vards.equals(thread.dati.playerFocusName)) { //fokus�t� sp�l�t�ja redzesloks
//            drawRedzesloks(g, x, y, merogs, cilveks, krasa);
//
//        }

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
