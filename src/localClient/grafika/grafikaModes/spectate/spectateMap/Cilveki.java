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
        //iziet cauri visiem cilvçkiem

        List<Integer> _chunkXY = new ArrayList<>();
        _chunkXY.add(chunkXY[0]);
        _chunkXY.add(chunkXY[1]);
        for(String vards : DataBase.laukums.get(_chunkXY).cilvekiList)
            drawCilveks(g, dati, spectateMapInfo, vards, chunkLoc);
    }

    private void drawCilveks(Graphics g, Dati dati, DrawManager.SpectateMapInfo spectateMapInfo,
                             String vards, int[] chunkLoc){
        //katra cilvçka individuâla zîmçðana

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

        } else {
            resnums = detailedDrawingLimit;
            drawBodyBackground(g, cilveksLoc, resnums, playerColor, true);
        }

        if(spectateMapInfo.drawRedzesloki){
            drawRedzesloks(g, cilveks, cilveksLoc, spectateMapInfo.merogs, playerColor);
        }

        //vârds un informâcija
        drawInfo(g, cilveks, vards, cilveksLoc, resnums,
                dati.grafikasDati.colorPalette.pair3[1], spectateMapInfo.merogs, spectateMapInfo.drawPlayerInfo);
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

        g.setColor(playerColor); //iekrâso rumpi cilvçka krâsâ
        g.fillOval(x, y, (int)resnums, (int)resnums);

        if(contour){
            g.setColor(Color.black); //kontûra ap rumpi
            g.drawOval(x, y, (int)resnums, (int)resnums);
        }

    }

    private void drawMainSprite(Graphics g, Dati dati, Cilveks cilveks, double[] cilveksLoc, double resnums){
        //galvenâs bildîtes zîmçðana, kas pagriezta leòíî fi

        GrafikasDati.drawRotatedImage(g, dati.grafikasDati, "cilveks",
                new int[]{(int)cilveksLoc[0], (int)cilveksLoc[1]},
                new int[]{(int)resnums, (int)resnums},
                cilveks.xyz.fi - 90);
    }

    private void drawExtras(){
        //no vecâ par kroòa zîmçðanu karalim

        //if(cilveks.vards.equals(komandasList.get(komanda).galvenais)) { //karalis
//            drawKronis(g, x, y, resnums);
//        }

        //Color kronaKrasa = new Color(0,0,0); //kroòa krâsa - melns  punkts
        //double kronaKoeficients=0.5; //kroòa resnums pret kopçjo resnumu

//      private static void drawKronis(Graphics g, double x, double y, double resnums){
//        g.setColor(new Color(0,0,0)); //kroòa krâsa - melns punkts
//        double kronaResnums=resnums/2;
//        g.fillOval((int)(x-kronaResnums/2),
//                (int)(y-kronaResnums/2),
//                (int)kronaResnums, (int)kronaResnums); //kronis
//    }


        //te var ielikt, ka zîmç lietas, kas rokâs
    }

    private void drawInfo(Graphics g, Cilveks cilveks, String vards, double[] cilveksLoc, double resnums, Color textColor, double merogs, boolean drawPlayerInfo){
        g.setColor(textColor);

        if(merogs >= 0.3){ //vârds un pamatinformâcija
            int[] nameOffset = new int[]{-30, (int)(-5 - resnums/2)};
            g.drawString(vards + " - " + cilveks.komanda,
                    (int)(cilveksLoc[0] + nameOffset[0]),
                    (int)(cilveksLoc[1] + nameOffset[1]));
        }

        if(merogs >= 0.8 && drawPlayerInfo){ //detalizçta informâcija
            int[] textOffset = new int[]{-30, (int)(5 + resnums/2)};
            int textHeight = 15, w = 1;

            g.drawString("xy: " + (int)cilveks.xyz.x + ", " + (int)cilveks.xyz.y,
                    (int)(cilveksLoc[0] + textOffset[0]),
                    (int)(cilveksLoc[1] + textOffset[1] + textHeight * w));
            w++;
            g.drawString("fi: " + (int)cilveks.xyz.fi,
                    (int)(cilveksLoc[0] + textOffset[0]),
                    (int)(cilveksLoc[1] + textOffset[1] + textHeight * w));
            w++;
            g.drawString("hp: " + (int)cilveks.hp + "/" + (int)cilveks.hpmax,
                    (int)(cilveksLoc[0] + textOffset[0]),
                    (int)(cilveksLoc[1] + textOffset[1] + textHeight * w));
            w++;
            g.drawString("paika: " + (int)cilveks.paika + "/" + (int)cilveks.paikaMax + " min: " + (int)cilveks.paikaMin,
                    (int)(cilveksLoc[0] + textOffset[0]),
                    (int)(cilveksLoc[1] + textOffset[1] + textHeight * w));
            w++;
            g.drawString("R1: " + (int)cilveks.R1 + " R2: " + (int)cilveks.R2,
                    (int)(cilveksLoc[0] + textOffset[0]),
                    (int)(cilveksLoc[1] + textOffset[1] + textHeight * w));
            //w++;
        }
    }

    private void drawRedzesloks(Graphics g, Cilveks cilveks, double[] cilveksLoc, double merogs, Color krasa){
        g.setColor(krasa);
        int drawingSizeLimit = 10;

        double R1 = cilveks.R1 * merogs; //R1 - tâlais
        if(R1 >= drawingSizeLimit) g.drawOval(
                (int)(cilveksLoc[0] - R1), (int)(cilveksLoc[1] - R1),
                (int)(R1 * 2), (int)(R1 * 2));

        double R2 = cilveks.R2 * merogs; //R2 - tuvais
        if(R2 >= drawingSizeLimit) g.drawOval(
                (int)(cilveksLoc[0] - R2), (int)(cilveksLoc[1] - R2),
                (int)(R2 * 2), (int)(R2 * 2));
    }

}
