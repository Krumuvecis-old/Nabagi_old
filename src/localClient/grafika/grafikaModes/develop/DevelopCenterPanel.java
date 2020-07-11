package localClient.grafika.grafikaModes.develop;

import localClient.Dati;
import localClient.grafika.Button;
import localClient.grafika.grafikaParts.CenterPanel;
import localClient.grafika.grafikaParts.SampleLayout;

import java.awt.*;

public class DevelopCenterPanel extends CenterPanel {

    DevelopCenterPanel(SampleLayout layout, Color[] colorPair){
        super(layout, colorPair);

        clearButtons();

        buttonDetails.add(new Button.ButtonDetails(1, "Scroll up", 5));
        buttonDetails.add(new Button.ButtonDetails(2, "Scroll down", 6));
        buttonDetails.add(new Button.ButtonDetails(3, "Select", 2));

        generateButtons(layout);
    }

    @Override
    public void draw(Graphics g, Dati dati, SampleLayout layout) {
        super.draw(g, dati, layout);

        drawContentPlaceHolder(g, dati.grafikasDati.colorPalette.pair3[1]);

        //zemâk kopçts no vecâ (arî zemâk 3 metodes definçtas no vecâ)
// 		if (thread.dati.tablo1Draw) drawTablo1(g); //galvenais komandu panelis sânâ
//		if (thread.dati.tablo2Draw) drawTablo2(g); //centrâlais panelis cilvçku diagnostikai
//		if (thread.dati.tablo3Draw) drawTablo3(g); //centrâlais panelis kartes diagnostikai

    }

    private void drawContentPlaceHolder(Graphics g, Color textColor){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Contents placeholder", textLocation[0], textLocation[1]);
    }

    //zemâk kopçts no vecâ
    //	private void drawTablo1(Graphics g) { //galvenais tablo pa labi no pogâm
//
//		g.setColor(thread.dati.tablo1krasa);
//
//		int nobideX=thread.dati.tablo1x0, nobideY=thread.dati.tablo1y0;
//		int tekstaPlatums=thread.dati.tablo1tekstaPlatums;
//
//		int w=0; //uzrakstîto rindu skaits
//
//		//tekoðâ informâcija par komandâm
//		g.drawString("vislielâkâ komanda: "+KomanduApskats.komanduVestureLielakaKomanda+" ("+KomanduApskats.komanduVestureMaksimums+")",nobideX,w*tekstaPlatums+nobideY);w++;
//		g.drawString("tagad kopâ spçlçtâji: "+Cilveks.cilvekuListPilnais.size(),nobideX,w*tekstaPlatums+nobideY);w++;
//		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;
//
//		for (int i=0;i<komandasList.size();i++) {
//			g.setColor(komandasList.get(i).krasa);
//			g.drawString(komandasList.get(i).nosaukums+" - "+komandasList.get(i).biedruList.size()+" speletaji",nobideX,w*tekstaPlatums+nobideY);w++;
//			g.drawString("karalis: "+komandasList.get(i).galvenais,nobideX,w*tekstaPlatums+nobideY);w++;
//		}
//
//		//vispârçja spçlçtâju statistika drusku zemâk
//		g.setColor(thread.dati.tablo1krasa);
//		g.drawString("---------------",nobideX,w*tekstaPlatums+nobideY);w++;
//		//g.drawString("vecâkais: "+cilvekiList.get(0).vards,nobideX,w*tekstaPlatums+nobideY);w++;
//		//g.drawString("jaunâkais: "+cilvekiList.get(cilvekiList.size()-1).vards,nobideX,w*tekstaPlatums+nobideY);w++;
//		g.drawString("kopâ bijuði spçlçtâji: "+Cilveks.maxCilveks,nobideX,w*tekstaPlatums+nobideY);w++;
//
//	}
//
//	private void drawTablo2(Graphics g) { //lielais cilvçku diagnostikas logs
//		//ðis logs pagaidâm nedarbojas
//
//		//tekoðâ informâcija par cilvçkiem
//
////		int nobideY=thread.dati.tablo2y0, rindasPlatums=thread.dati.tablo2rindasPlatums,
////				kolonna1x=thread.dati.tablo2x0, kolonna2x=kolonna1x+thread.dati.tablo2platums1,
////				kolonnaNx=kolonna2x+thread.dati.tablo2platums2, kolonnaNplatums=thread.dati.tablo2platumsN;
////
////		int w=1; //virsraksta rindu skaits
////
////		String teksts1="Nosaukums (komanda), ...";
////		String teksts2="invertory + orderi";
////		String teksts3="Inventory";
////
////		for(int i=(-1)*w;i<thread.dati.cilvekuPilnaisList.size();i++) { //cikls, lai iziet caur cilvçkiem
////			g.setColor(thread.dati.tablo2krasaDefault);
////			Cilveks cilveks = Cilveks.getPlayer(thread.dati.cilvekuPilnaisList.get(i));
////
////			int invSize=0;
////
////			if(!(i<0)) {
////				teksts1=cilveks.vards + " (" + cilveks.komanda+") "+
////						//"HP: "+(new DecimalFormat("#.##").format(cilveks.hp/cilveks.hpmax)+" ; "+
////						"HP: " + (int)(cilveks.hp/cilveks.hpmax*100) + "% "+
////						"paika: " + (int)(cilveks.paika/cilveks.paikaMax*100) + "% ";
////
////				teksts2="invSize:"+cilveks.inventory.size()+" ; "+
////						"orderi:"+cilveks.orderi.size();
////
////				invSize=cilveks.inventory.size();
////
////
////				if (cilveks.paika<cilveks.paikaMin- CilvekuKonstantes.paikaReductionDefault) {
////					g.setColor(thread.dati.tablo2krasaCritical);
////				}
////			}
////
////			g.drawString(teksts1, kolonna1x, nobideY+rindasPlatums*(i+w));
////			g.drawString(teksts2, kolonna2x, nobideY+rindasPlatums*(i+w));
////
////			for(int j=0;j<Math.max(1,invSize);j++) { //iziet caur visu inventory
////
////
////				if (!(i<0)) {
////					if (invSize>0) {
////						teksts3=cilveks.inventory.get(j).nosaukums+" "+(new DecimalFormat("#.##").format(cilveks.inventory.get(j).daudzums) );
////					} else { teksts3=""; }
////				}
////				g.drawString(teksts3,kolonnaNx+kolonnaNplatums*j,nobideY+rindasPlatums*(i+w));
////			}
////		}
//	}
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
