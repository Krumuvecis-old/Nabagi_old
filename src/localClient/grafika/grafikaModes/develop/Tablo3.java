package localClient.grafika.grafikaModes.develop;

import localClient.Dati;

import java.awt.*;

public class Tablo3 extends DevelopCenterPanel.Tablo {

    Tablo3() {
        super();

        tabloName = "tablo3";
    }

    @Override
    public void draw(Graphics g, Dati dati, int[] panelXY, int boxY, int boxH){
        super.draw(g, dati, panelXY, boxY, boxH);

        drawTabloName(g, panelXY, dati.grafikasDati.colorPalette.pair3[1]);
    }


}
