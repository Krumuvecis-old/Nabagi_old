package localClient.grafika.grafikaModes.develop;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopCenterPanel extends CenterPanel {

    ContentsMethods contentsMethods;

    DevelopCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Scroll up", 5));
        buttonDetails.add(new Button.ButtonDetails(2, "Scroll down", 6));
        buttonDetails.add(new Button.ButtonDetails(3, "Select", 2));

        generateButtons(layout);

        contentsMethods = new ContentsMethods();
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        contentsMethods.drawContentPlaceHolder(g, dati.grafikasDati.colorPalette.pair3[1], XY, size);

        //zem�k kop�ts no vec� (ar� zem�k 3 metodes defin�tas no vec�)
// 		if (thread.dati.tablo1Draw) ContentsMethods.drawTablo1(g); //galvenais komandu panelis s�n�
//		if (thread.dati.tablo2Draw) ContentsMethods.drawTablo2(g); //centr�lais panelis cilv�ku diagnostikai
//		if (thread.dati.tablo3Draw) drawTablo3(g); //centr�lais panelis kartes diagnostikai

    }


}
