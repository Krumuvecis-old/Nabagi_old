package localClient.grafika.grafikaModes.spectate;

import localClient.ClientThread;
import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.GrafikasDati;
import localClient.grafika.grafikaParts.DrawManager;
import localClient.grafika.grafikaParts.PanelL;
import localClient.grafika.grafikaParts.SampleLayout;
import server.dataBase.DataBase;

import java.awt.*;
import java.text.DecimalFormat;

public class SpectatePanelL extends PanelL {

    public SpectatePanelL(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Free view", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Select random player", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Map chunk grid", 0));
        buttonDetails.add(new Button.ButtonDetails(4, "Map cell grid", 0));
        buttonDetails.add(new Button.ButtonDetails(5, "Extend map", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, ClientThread thread, SampleLayout layout) {
        super.draw(g, thread, layout);

        Button lastButton = buttonList.get(buttonList.size() - 1);
        int lastButtonEndPoint = lastButton.y + lastButton.wy;

        drawInformation(g, lastButtonEndPoint, thread.dati);

        //te var izsaukt savas metodes
    }

    private void drawInformation(Graphics g, int y0, Dati dati){
        Color textColor = dati.grafikasDati.colorPalette.pair2[1];

        int[] textOffset = new int[]{10, 10},
                xy = new int[]{
                        XY[0] + textOffset[0],
                        XY[1] + y0 + textOffset[1]};

        int w = 1, textHeight = 15;

        g.setColor(textColor);

        g.drawString("Informâcija", xy[0], xy[1] + w * textHeight); w++;
        g.drawString("--------------------", xy[0], xy[1] + w * textHeight); w++;
		w = drawMapInfo(g, xy, w, textHeight, dati, dati.drawManagerList.get(Dati.ModeOption.spectate).layout);
		g.drawString("--------------------", xy[0], xy[1] + w * textHeight); w++;
		w = drawGenRateInfo(g, xy, w, textHeight);

    }

    private int drawMapInfo(Graphics g, int[] xy, int w, int textHeight, Dati dati, SampleLayout layout){
        g.drawString("Par karti", xy[0], xy[1] + w * textHeight);
        w++;

        g.drawString("Grafikas paneïa izmçrs (px)",
                xy[0], xy[1] + w * textHeight); w++;
        int[] contentsSize = new int[]{layout.centerPanelContentsWX, layout.centerPanelContentsWY};
        g.drawString("[ " + contentsSize[0] + " - " + contentsSize[1] + " ]",
                xy[0], xy[1] + w * textHeight); w++;

        g.drawString("Laukuma kopçjais izmçrs (x & y): ",
				xy[0], xy[1] + w * textHeight); w++;
        g.drawString("[ " + DataBase.laukumaPlatumsSum + " - " + DataBase.laukumaAugstumsSum + " ]",
                xy[0], xy[1] + w * textHeight); w++;

        DrawManager.SpectateMapInfo spectateMapInfo =
                dati.drawManagerList.get(dati.modeCurrent).spectateMapInfo;

        g.drawString("Laukuma grafiskais izmçrs (x & y): ",
                xy[0], xy[1] + w * textHeight); w++;
        g.drawString("[ " + (int)(DataBase.laukumaPlatumsSum * spectateMapInfo.merogs) + " - " + (int)(DataBase.laukumaAugstumsSum * spectateMapInfo.merogs) + " ]",
                xy[0], xy[1] + w * textHeight); w++;

        g.drawString("Zoom factor: " + (new DecimalFormat("#.##").format(spectateMapInfo.zoomFactor)) + ", " +
                        "merogs: " + (new DecimalFormat("#.##").format(spectateMapInfo.merogs)),
                xy[0], xy[1] + w * textHeight); w++;

        g.drawString("Center XY: " + spectateMapInfo.centerXY[0] + " , " +spectateMapInfo.centerXY[1],
                xy[0], xy[1] + w * textHeight); w++;

        g.drawString("Cell size: " + DataBase.mapCellW + " , " +
                        "graphical:" + (new DecimalFormat("#.##").format(
                                DataBase.mapCellW * spectateMapInfo.merogs)),
                xy[0], xy[1] + w * textHeight); w++;
        g.drawString("Chunk size: " + DataBase.mapChunkW + " , " +
                        "graphical:" + (new DecimalFormat("#.##").format(
                                DataBase.mapChunkW * spectateMapInfo.merogs)),
                xy[0], xy[1] + w * textHeight); w++;
        g.drawString("Cell count in chunk: " + DataBase.mapCellCount,
                xy[0], xy[1] + w * textHeight); w++;
        g.drawString("Overall cell count: " +
                        "[ " + DataBase.mapCellCount * DataBase.mapChunkCountX + " - " +
                        DataBase.mapCellCount * DataBase.mapChunkCountY + " ]",
                xy[0], xy[1] + w * textHeight); w++;

		return w;

	}

	private int drawGenRateInfo(Graphics g, int[] xy, int w, int textHeight){
        g.drawString("genRateInfo1", xy[0], xy[1] + w * textHeight);
        w++;

        //kopçts no vecâ

//		g.drawString("overallGenRate: "+(new DecimalFormat("#.##").format(KonstantesUniversal.overallGenRate)),
//				x0, y0 + w * rindasPlatums); w++;
//
//		for(int i=1; i<KonstantesUniversal.defaultLietas.size(); i++){
//			int x = x0, y = y0 + w * rindasPlatums;
//			drawInfoGenRateOnce(g, x, y, i);
//			w++;
//		}

		return w;
	}

//	private static void drawInfoGenRateOnce(Graphics g, int x, int y, int tips){
//		String nosaukums = KonstantesUniversal.defaultLietas.get(tips).nosaukums;
//		double genRate = KonstantesUniversal.overallGenRate * KonstantesUniversal.defaultLietas.get(tips).genKoef;
//
//		g.drawString(nosaukums + " genRate: " + (new DecimalFormat("#.##").format(genRate)) +
//						" minAmount: " + (new DecimalFormat("#.#").format(KonstantesUniversal.defaultLietas.get(tips).genMin)) +
//						" maxAmount: " + (new DecimalFormat("#.#").format(KonstantesUniversal.defaultLietas.get(tips).genMax)),
//				x, y);
//
//	}



    //te var pievienot savas metodes
}
