package localClient.grafika.grafikaModes.spectate;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class SpectateCenterPanel extends CenterPanel {

    SpectateCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Zoom in", 1));
        buttonDetails.add(new Button.ButtonDetails(2, "Zoom out", 2));

        //te var pievienot pogas

        generateButtons(layout);

        buttonResizer();
    }

    private void buttonResizer(){
        int[] buttonOffset = new int[]{0, 0},
                buttonSize = new int[]{80, 30};
        int buttonSeparation = 5;
        for (int i = 0; i<buttonList.size(); i++){
            Button button = buttonList.get(i);
            button.wx = buttonSize[0];
            button.wy = buttonSize[1];

            button.x = buttonOffset[0] + buttonSeparation;
            button.y = buttonOffset[1] + buttonSeparation + (buttonSize[1] + buttonSeparation) * i;
        }

    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);



        //te var izsaukt savas metodes

        //no vecâ:
        //if (thread.dati.miniMapDraw) Map.main(g, thread, this); //karte

        drawContentPlaceHolder(g, dati.grafikasDati.colorPalette.pair3[1]);

    }

    private void drawContentPlaceHolder(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Contents placeholder", textLocation[0], textLocation[1]);
    }

    //te var pievienot savas metodes

    //zemâk kopçts no vecâ

    //	// --------------------
//	//zemâk par kartes zîmçðanu
//
//	public boolean miniMapDraw=true, miniMapDrawInfo=true; //kartes zîmçðana vispâr un informâcija tai apakðâ
//	public int miniMapX=tablo2x0, miniMapY=tablo2y0-15,
//			miniMapPlatums=ekranaPlatums-miniMapX-50,
//			miniMapAugstums=ekranaAugstums-miniMapY-50;
//
//
//	// --------------------
//	//zemâk par centrâlâ (kartes diagnostikas) tablo Parametriem
//
//	protected boolean tablo3Draw=false;
//	protected Color tablo3krasa=Color.white;
//
//	//augstâk vecie parametri

    // --------------------
    //zemâk jaunâs funkcijas paòemtas no TimeScheduler


//    public static double cilvekiKrasaSaturation=1;
//    public static double cilvekiKrasaBrightnessMin=0.4; //pie hpRatio=0
//    public static double cilvekiKrasaBrightnessMax=1; //pie hpRatio=1
//
//    public static Color kronaKrasa = new Color(0,0,0); //kroòa krâsa - melns  punkts
//    public static double kronaKoeficients=0.5; //kroòa resnums pret kopçjo resnumu

//
//	private void drawTablo3(Graphics g){
//		//laukuma diagnostikas panelis
//		int x0 = 10 + thread.dati.miniMapX,
//				y0 = 13 + thread.dati.miniMapY,
//				platumsMax=Math.max(0, thread.dati.miniMapPlatums),
//				augstumsMax=Math.max(0, thread.dati.miniMapAugstums);
//
//		int laukumaPlatums = KonstantesUniversal.laukumaPlatumsSum,
//				laukumaAugstums=KonstantesUniversal.laukumaAugstumsSum;
//		double merogs = Math.min((double)platumsMax/laukumaPlatums,
//				(double)augstumsMax/laukumaAugstums);
//
//		int wx = (int)(KonstantesUniversal.mapChunkW * merogs),
//				wy = (int)(KonstantesUniversal.mapChunkW * merogs),
//				tekstaPlatums = 15;
//		g.setColor(thread.dati.tablo3krasa);
//		for(int[] chunkXY = {0,0}; chunkXY[0]< CalculationsThread.laukums.size(); chunkXY[0]++){
//			for(chunkXY[1]=0; chunkXY[1]<CalculationsThread.laukums.get(chunkXY[0]).size(); chunkXY[1]++){
//				int x = x0 + chunkXY[0] * wx,
//						y = y0 + chunkXY[1] * wy;
//				int w=0;
//				g.drawString("lietas: "+CalculationsThread.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.size(),
//						x,y + w * tekstaPlatums); w++;
//				g.drawString("players: "+CalculationsThread.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(),
//						x,y + w * tekstaPlatums); w++;
//				for (int i=0; i<CalculationsThread.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++){
//					g.drawString(CalculationsThread.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(i).vards,
//							x,y + w * tekstaPlatums); w++;
//				}
//			}
//		}
//	}

}
