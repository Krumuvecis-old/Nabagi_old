package localClient.grafika.grafikaParts;

import localClient.ColorPalette;
import localClient.grafika.Button;
import localClient.Dati;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CenterPanel extends SamplePanel {

    /*
     * šī klase raksturo default centrālo paneli
     *
     */

    public CenterPanel(SampleLayout layout, Color[] colorPair){
        super(calculateLocation(layout), calculateSize(layout), colorPair);

        buttonDetails.add(new Button.ButtonDetails(1, "Zoom in", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Zoom out", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Demo pictures", 0));

        generateButtons(layout);
    }

    private static int[] calculateLocation(SampleLayout layout){
        return new int[]{layout.centerPanelX, layout.panelY};
    }

    private static int[] calculateSize(SampleLayout layout){
        return new int[]{layout.centerPanelPlatums, layout.panelAugstums};
    }

    public void generateButtons(SampleLayout layout){
        buttonList = new ArrayList<>();

        int buttonSpacing=5;
        int[] buttonSize = {100, 30},
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
                dati.grafikasDati.colorPalette.pair3);

        //te var likt papildus default funkcijas

        Button.drawButtons(g, this);
    }

    public void drawContentsBorder(Graphics g, SampleLayout layout, ColorPalette colorPalette){
        g.setColor(colorPalette.pair2[0]);
		g.fillRect(layout.centerPanelX, layout.panelY,
                layout.centerPanelBorder, layout.panelAugstums);//rietumi
		g.fillRect(layout.centerPanelX, layout.panelY,
                layout.centerPanelPlatums, layout.centerPanelBorder);//ziemeļi
		g.fillRect(layout.panelRX - layout.centerPanelBorder,
                layout.panelY, layout.centerPanelBorder, layout.panelAugstums);//austrumi
		g.fillRect(layout.centerPanelX, layout.footerY - layout.centerPanelBorder,
                layout.centerPanelPlatums, layout.centerPanelBorder);//dienvidi

        g.setColor(Color.black);
        g.drawRect(layout.centerPanelContentsX, layout.centerPanelContentsY,
                layout.centerPanelContentsWX, layout.centerPanelContentsWY); //iekšējā kontūra
        g.drawRect(layout.centerPanelX, layout.panelY,
                layout.centerPanelPlatums, layout.panelAugstums); //ārējā kontūra
    }

    //overPanels(g, x0, y0, kartesPlatums, kartesAugstums); //maliņas apkārt laukumam - jāpārliek uz sample centrePanel
//
//	private static void overPanels(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums){
//
//
//		int mala=15;//KonstantesUniversal.mala;
//		g.setColor(Grafiskie.malasKrasa); //apkārt kartei uzzīmē maliņu
//
//
//
//	}

    //te var likt papildus default funkcijas (jāizsauc no draw() metodes)





}
