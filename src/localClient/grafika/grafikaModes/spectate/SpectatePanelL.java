package localClient.grafika.grafikaModes.spectate;

import localClient.ClientThread;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.PanelL;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SpectatePanelL extends PanelL {

    public SpectatePanelL(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();
        buttonDetails.add(new Button.ButtonDetails(1, "Free view", 0));
        buttonDetails.add(new Button.ButtonDetails(2, "Select random player", 0));
        buttonDetails.add(new Button.ButtonDetails(3, "Map chunk grid", 0));
        buttonDetails.add(new Button.ButtonDetails(4, "Map cell grid", 0));

        //te var pievienot pogas

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, ClientThread thread, SampleLayout layout) {
        super.draw(g, thread, layout);

        Button lastButton = buttonList.get(buttonList.size() - 1);
        int lastButtonEndPoint = lastButton.y + lastButton.wy;

        drawInformation(g, lastButtonEndPoint, thread.dati.grafikasDati.colorPalette.pair2[1]);

        //te var izsaukt savas metodes
    }

    private void drawInformation(Graphics g, int y0, Color textColor){
        int[] textOffset = new int[]{10, 10},
                xy = new int[]{
                        XY[0] + textOffset[0],
                        XY[1] + y0 + textOffset[1]};

        int w = 1, textHeight = 15;

        g.setColor(textColor);

        g.drawString("Informâcija", xy[0], xy[1] + w * textHeight); w++;
        g.drawString("--------------------", xy[0], xy[1] + w * textHeight); w++;
		w = drawMapInfo(g, xy, w, textHeight);
		g.drawString("--------------------", xy[0], xy[1] + w * textHeight); w++;
		w = drawGenRateInfo(g, xy, w, textHeight);

    }

    private int drawMapInfo(Graphics g, int[] xy, int w, int textHeight){
        g.drawString("kartesInfo1", xy[0], xy[1] + w * textHeight);
        w++;

//		g.drawString("kartes platums: " + (int)(merogs * KonstantesUniversal.laukumaPlatumsSum) +
//						" kartes augstums: " + (int)(merogs * KonstantesUniversal.laukumaAugstumsSum) +
//						" merogs: " + (new DecimalFormat("#.##").format(merogs) ),
//				xy[0], xy[1] + w * textHeight); w++;
//		g.drawString("laukuma kopçjais platums (x): " + KonstantesUniversal.laukumaPlatumsSum +
//						" laukuma kopçjais augstums (y): " + KonstantesUniversal.laukumaAugstumsSum +
//						" chunk platums (x  & y): " + KonstantesUniversal.mapChunkW,
//				xy[0], xy[1] + w * textHeight); w++;
//		g.drawString("chunk skaits x: " + KonstantesUniversal.mapChunkCountX +
//						" chunk skaits y: " + KonstantesUniversal.mapChunkCountY +
//						" chunk platums (cells): " + KonstantesUniversal.mapCellCount,
//				xy[0], xy[1] + w * textHeight); w++;
//		g.drawString("cell skaits x: " + (KonstantesUniversal.mapCellCount * KonstantesUniversal.mapChunkCountX) +
//						" cell skaits y: " + (KonstantesUniversal.mapCellCount * KonstantesUniversal.mapChunkCountY) +
//						" cell platums (x & y): " + KonstantesUniversal.mapCellW,
//				xy[0], xy[1] + w * textHeight); w++;

		return w;

	}

	private int drawGenRateInfo(Graphics g, int[] xy, int w, int textHeight){
        g.drawString("genRateInfo1", xy[0], xy[1] + w * textHeight);
        w++;

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
