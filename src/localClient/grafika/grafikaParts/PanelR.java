package localClient.grafika.grafikaParts;

import localClient.grafika.Button;
import localClient.Dati;
import server.calculations.cilveki.Cilveks;
import server.calculations.komandas.KomanduApskats;
import server.dataBase.DataBase;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class PanelR extends SamplePanel{

    /*
     * ðî klase raksturo default labo paneli
     *
     */

    public boolean drawKomanduInfo = false;
    public int[] komanduInfoOffsetXY = new int[]{10, 10}; //default offset relative to panel

    public PanelR(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout),
                calculateSize(layout),
                colorPair);

        generateButtons(layout);
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.panelRX, layout.panelY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{
                Math.min(layout.panelRPlatums, Math.max(0, layout.ekranaPlatums - layout.panelRX)),
                Math.max(0, layout.panelAugstums)};
    }

    public void generateButtons(SampleLayout layout){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {layout.panelRPlatums - buttonSpacing * 2, 30},
                buttonOffset = {buttonSpacing, buttonSpacing};

        Button.addButtonList(this, true,
                buttonOffset, true, true,
                buttonSize, buttonSpacing,
                buttonDetails);
    }

    public void draw(Graphics g, Dati dati, SampleLayout layout){
        super.draw(g,
                calculateLocation(layout),
                calculateSize(layout),
                dati.grafikasDati.colorPalette.pair2);

        //te var likt papildus default funkcijas

        Button.drawButtons(g, this);

        if(drawKomanduInfo) drawKomanduInfo(g, komanduInfoOffsetXY, dati.grafikasDati.colorPalette.pair2[1]);

    }

    private void drawKomanduInfo(Graphics g, int[] offsetXY, Color textColorDefault) {

        g.setColor(textColorDefault);

        int wy = 15, w=1; //rindas platums un uzrakstîto rindu skaits
        int x0 = XY[0] + offsetXY[0], y0 = XY[1] + offsetXY[1];

        //tekoðâ informâcija par komandâm
        g.drawString("vislielâkâ komanda: " + KomanduApskats.komanduVestureLielakaKomanda + " (" + KomanduApskats.komanduVestureMaksimums + ")",
                x0, y0 + wy * w); w++;
        g.drawString("tagad kopâ spçlçtâji: " + DataBase.cilvekuList.size(),
                x0, y0 + wy * w); w++;
        g.drawString("---------------",
                x0, y0 + wy * w); w++;

        for (String komanda : DataBase.komandasList.keySet()) {
            g.setColor(DataBase.komandasList.get(komanda).krasa);
            g.drawString(komanda + " - " + DataBase.komandasList.get(komanda).biedri.size() + " speletaji",
                    x0, y0 + wy * w);
            g.drawString("karalis: " + DataBase.komandasList.get(komanda).galvenais,
                    x0 + 150, y0 + wy * w);
            w++;
        }

        //vispârçja spçlçtâju statistika drusku zemâk
        g.setColor(textColorDefault);
        g.drawString("---------------",
                x0, y0 + wy * w); w++;

        //List<String> cilvekuVardiTemp = new ArrayList<>(DataBase.cilvekuList.keySet());
        //g.drawString("ðobrîd vecâkais: " + (cilvekuVardiTemp.sort(null)).get(0)),
        //        x0, y0 + wy * w); w++;
        //g.drawString("jaunâkais: "+cilvekiList.get(cilvekiList.size()-1).vards,
        //        x0, y0 + wy * w); w++;
        g.drawString("kopâ bijuði spçlçtâji: " + Cilveks.maxCilveks,
                x0, y0 + wy * w); w++;

    }

    //te var likt papildus default funkcijas (jâizsauc no draw() metodes)

}
