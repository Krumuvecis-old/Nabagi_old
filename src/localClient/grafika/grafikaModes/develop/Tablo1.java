package localClient.grafika.grafikaModes.develop;

import localClient.Dati;
import server.calculations.cilveki.Cilveks;
import server.dataBase.DataBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Tablo1 extends DevelopCenterPanel.Tablo {

    Tablo1() {
        super();

        tabloName = "tablo1";
    }

    @Override
    public void draw(Graphics g, Dati dati, int[] panelXY, int boxY, int boxH){
        super.draw(g, dati, panelXY, boxY, boxH);

        Color textColor = dati.grafikasDati.colorPalette.pair3[1];

        int w = 0,
                column1X = 0,
                column2X = 100,
                column3X = 200;


        printLine(g, new int[]{panelXY[0] + column1X, panelXY[1]}, w,
                "Vârds, koordinâtas",
                textColor);

        printLine(g, new int[]{panelXY[0] + column2X, panelXY[1]}, w,
                "kolonna2",
                textColor);

        printLine(g, new int[]{panelXY[0] + column3X, panelXY[1]}, w,
                "kolonna3",
                textColor);

        ArrayList<String> sortedNames = new ArrayList<>();

        for(String vards : DataBase.cilvekuList.keySet()){
            sortedNames.add(vards);
        }

        sortedNames.sort(Comparator.naturalOrder());

        for(String vards : sortedNames){
            w++;
            drawBasicInfo(g, new int[]{panelXY[0] + column1X, panelXY[1]}, w,
                    textColor, vards);
        }
    }

    private void drawBasicInfo(Graphics g, int[] XY, int w, Color textColor, String vards){

        Cilveks cilveks = DataBase.cilvekuList.get(vards);

        String text = vards + " , " +
                "chunk: [ " + cilveks.xyz.chunkXY.get(0) + " / " + cilveks.xyz.chunkXY.get(1) + " ] , " +
                "xy: [ " + cilveks.xyz.x + " / " + cilveks.xyz.y + " ]";

        printLine(g, XY, w,
                text,
                textColor);
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


}
