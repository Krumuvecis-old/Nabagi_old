package localClient.grafika.grafikaModes.develop;

import java.awt.*;

public class ContentsMethods {

    //ðî klase vajadzîga lai katram tablo varçtu bût savas kontekstam atbilstoðas pogas

    ContentsMethods(){

    }

    public void updateValues(){

    }

    public void drawContentPlaceHolder(Graphics g, Color textColor, int[] XY, int[] size){
        g.setColor(textColor);
        int[] textLocation = {XY[0] + size[0] / 2 - 60, XY[1] + size[1] / 2};
        g.drawString("Contents placeholder", textLocation[0], textLocation[1]);
    }

    //	//zemâk vecie parametri
//	// --------------------
//	//zemâk par centrâlâ cilvçku tablo Parametriem (no vecâ varianta)
//
//	protected boolean tablo2Draw=false;
//
//	protected int tablo2x0=280, tablo2y0=tablo1y0, tablo2rindasPlatums=14;
//
//	protected int tablo2platums1=200; //platums1
//	protected int tablo2platums2=130; //platums2
//	protected int tablo2platumsN=80; //platumsN
//
//	protected Color tablo2krasaDefault, tablo2krasaCritical; //paðas krâsas nosaka initialize() ciklâ
//

    //	//zemâk vecâ info par cilvçku tablo (tablo2) zîmçðanas krâsâm
//
//		boolean tablo2Transparent=false; //caurspîdîgs teksts
//		int tablo2alfa=255;
//		if(tablo2Transparent) tablo2alfa=100; //max caurspîdîgums
//
//		tablo2krasaDefault=new Color(255,255,0,tablo2alfa); //dzeltens
//		tablo2krasaCritical=new Color(255,0,0,tablo2alfa); //sarkans
//
//
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

    //zemâk kopçts no vecâ

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
