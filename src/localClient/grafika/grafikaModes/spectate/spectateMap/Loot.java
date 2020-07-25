package localClient.grafika.grafikaModes.spectate.spectateMap;

import localClient.Dati;
import localClient.grafika.grafikaParts.DrawManager;
import server.calculations.laukums.Laukums;
import server.calculations.lietas.Lieta;
import server.calculations.lietas.LietuTips;
import server.dataBase.DataBase;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

public class Loot {

    Loot(){}

    void draw(Graphics g, Dati dati, DrawManager.SpectateMapInfo spectateMapInfo, int[] chunkLoc, int[] chunkXY){

        List<Integer> chunkXYlist = new ArrayList<>();
        chunkXYlist.add(chunkXY[0]);
        chunkXYlist.add(chunkXY[1]);
        for(int i = 0; i < DataBase.laukums.mapChunks.get(chunkXYlist).lietas.size(); i++){
            Lieta lieta = DataBase.laukums.mapChunks.get(chunkXYlist).lietas.get(i);

            double[] xy = new double[]{
                    chunkLoc[0] + lieta.x * spectateMapInfo.merogs,
                    chunkLoc[1] + lieta.y * spectateMapInfo.merogs};
            double resnums = LietuTips.lietuTipi.get(lieta.tips).izmers * spectateMapInfo.merogs;

            drawItem(g, dati, lieta, xy, resnums, spectateMapInfo.merogs, spectateMapInfo.drawLootInfo); //zîmç atseviðíu lietu
        }
    }

    private void drawItem(Graphics g, Dati dati, Lieta lieta, double[] xy, double resnums, double merogs, boolean drawLootInfo){
        resnums = Math.max(resnums, 10);
        if(merogs >= 0.3) g.drawImage(
                dati.grafikasDati.images.get(LietuTips.lietuTipi.get(lieta.tips).spriteName),
                (int)(xy[0] - resnums/2), (int)(xy[1] - resnums/2),
                (int)resnums, (int)resnums,null);

        if(drawLootInfo && merogs >= 1.5)
            drawItemInfo(g, lieta, xy, resnums, dati.grafikasDati.colorPalette.pair3[1]);

    }

    private void drawItemInfo(Graphics g, Lieta lieta, double[] xy, double resnums, Color textColor){
        g.setColor(textColor);

        int[] textOffset = new int[]{-10, (int)(5 + resnums/2)};
        int textHeight = 15, w = 1;

        g.drawString("" + (new DecimalFormat("#.##")).format(lieta.daudzums),
                (int)(xy[0] + textOffset[0]),
                (int)(xy[1] + textOffset[1] + textHeight * w));
        //w++;
    }

}
