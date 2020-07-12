package localClient.grafika.grafikaModes.develop;

import localClient.Dati;

import java.awt.*;

public class Tablo2 extends DevelopCenterPanel.Tablo {

    Tablo2() {
        super();

        tabloName = "tablo2";
    }

    @Override
    public void draw(Graphics g, Dati dati, int[] panelXY, int boxY, int boxH){
        super.draw(g, dati, panelXY, boxY, boxH);

        drawTabloName(g, panelXY, dati.grafikasDati.colorPalette.pair3[1]);
    }

}
