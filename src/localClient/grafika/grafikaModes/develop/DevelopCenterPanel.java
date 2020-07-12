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
        buttonDetails.add(new Button.ButtonDetails(4, "Deselect", 0));

        generateButtons(layout);

        contentsMethods = new ContentsMethods();
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        contentsMethods.drawContentPlaceHolder(g, dati.grafikasDati.colorPalette.pair3[1], XY, size);

//		if (thread.dati.tablo2Draw) ContentsMethods.drawTablo2(g); //centrâlais panelis cilvçku diagnostikai
//		if (thread.dati.tablo3Draw) ContentsMethods.drawTablo3(g); //centrâlais panelis kartes diagnostikai

    }


}
