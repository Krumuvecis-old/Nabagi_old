package localClient.grafika.grafikaModes.develop;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelR;
import localClient.grafika.grafikaParts.SampleLayout;
import server.calculations.cilveki.Cilveks;
import server.calculations.komandas.KomanduApskats;
import server.dataBase.DataBase;

import java.awt.*;

public class DevelopPanelR extends PanelR {

    public DevelopPanelR(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);
        clearButtons();

        //overPanel toggles
        buttonDetails.add(new Button.ButtonDetails(1, "Client diagnostics toggle", 18));
        buttonDetails.add(new Button.ButtonDetails(2, "Server diagnostics toggle", 16));
        buttonDetails.add(new Button.ButtonDetails(3, "Sample images toggle", 4));
        buttonDetails.add(new Button.ButtonDetails(4, "Color wheel toggle", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout){
        super.draw(g, dati, layout);

        drawKomanduInfo(g, dati.grafikasDati.colorPalette.pair2[1]);
    }

    private void drawKomanduInfo(Graphics g, Color textColorDefault) {

        g.setColor(textColorDefault);

		int nobideX = 10, nobideY = 150;
		int wy = 15, w=1; //rindas platums un uzrakst�to rindu skaits
        int x0 = XY[0] + nobideX, y0 = XY[1] + nobideY;

		//teko�� inform�cija par komand�m
		g.drawString("visliel�k� komanda: " + KomanduApskats.komanduVestureLielakaKomanda + " (" + KomanduApskats.komanduVestureMaksimums + ")",
                x0, y0 + wy * w); w++;
		g.drawString("tagad kop� sp�l�t�ji: " + DataBase.cilvekuList.size(),
                x0, y0 + wy * w); w++;
		g.drawString("---------------",
                x0, y0 + wy * w); w++;

		for (String komanda : DataBase.komandasList.keySet()) {
			g.setColor(DataBase.komandasList.get(komanda).krasa);
			g.drawString(komanda + " - " + DataBase.komandasList.get(komanda).biedri.size() + " speletaji",
                    x0, y0 + wy * w); w++;
			g.drawString("karalis: " + DataBase.komandasList.get(komanda).galvenais,
                    x0, y0 + wy * w); w++;
		}

		//visp�r�ja sp�l�t�ju statistika drusku zem�k
		g.setColor(textColorDefault);
		g.drawString("---------------",
                x0, y0 + wy * w); w++;

        //List<String> cilvekuVardiTemp = new ArrayList<>(DataBase.cilvekuList.keySet());
		//g.drawString("�obr�d vec�kais: " + (cilvekuVardiTemp.sort(null)).get(0)),
        //        x0, y0 + wy * w); w++;
		//g.drawString("jaun�kais: "+cilvekiList.get(cilvekiList.size()-1).vards,
        //        x0, y0 + wy * w); w++;
		g.drawString("kop� biju�i sp�l�t�ji: " + Cilveks.maxCilveks,
                x0, y0 + wy * w); w++;

    }


}
