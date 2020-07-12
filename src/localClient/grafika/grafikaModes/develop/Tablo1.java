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
                column3X = 400;

        //column names
        printLine(g, new int[]{panelXY[0] + column1X, panelXY[1]}, w,
                "Vârds (komanda)",
                textColor);

        printLine(g, new int[]{panelXY[0] + column2X, panelXY[1]}, w,
                "Koordinâtu informâcija",
                textColor);

        printLine(g, new int[]{panelXY[0] + column3X, panelXY[1]}, w,
                "kolonna3",
                textColor);

        //content
        ArrayList<String> sortedNames = sortPlayerNames();

        for(String vards : sortedNames){
            w++;

            //1. kolonna
            drawBasicInfo(g, new int[]{panelXY[0] + column1X, panelXY[1]}, w,
                    textColor, vards);

            //2. kolonna
            drawLocationInfo(g, new int[]{panelXY[0] + column2X, panelXY[1]}, w,
                    textColor, vards);

            //3. kolonna
            //placeholder

        }
    }

    private static ArrayList<String> sortPlayerNames(){

        class SortingInfo {
            String vards;
            String parameter; //parameter type declaration

            SortingInfo(String playerName, String sortingParameter){
                vards = playerName;
                parameter = sortingParameter;
            }
        }

        ArrayList<String> parameterList = new ArrayList<>();
        ArrayList<SortingInfo> sortingInfo1 = new ArrayList<>(),
                sortingInfo2 = new ArrayList<>();

        for(String vards : DataBase.cilvekuList.keySet()){
            String sortingParameter = DataBase.cilvekuList.get(vards).komanda; //sorting paramater value

            sortingInfo1.add(new SortingInfo(vards, sortingParameter)); //unsorted list
            parameterList.add(sortingParameter);
        }

        parameterList.sort(Comparator.naturalOrder());

        //move data from one array to another
        while (parameterList.size() > 0) {
            for (int i = 0; i<sortingInfo1.size(); i++){
                if(sortingInfo1.get(i).parameter == parameterList.get(0)) {

                    sortingInfo2.add(new SortingInfo(sortingInfo1.get(i).vards, sortingInfo1.get(i).parameter));
                    sortingInfo1.remove(i);
                    parameterList.remove(0);

                    break;
                }
            }

        }

        //generate names list
        ArrayList<String> sortedNamesList = new ArrayList<>();
        for(int i = 0; i<sortingInfo2.size(); i++)
            sortedNamesList.add(sortingInfo2.get(i).vards);

        return sortedNamesList;
    }

    private void drawBasicInfo(Graphics g, int[] XY, int w, Color textColor, String vards){

        Cilveks cilveks = DataBase.cilvekuList.get(vards);

        String text = vards + " (" + cilveks.komanda + ")";

        printLine(g, XY, w,
                text,
                textColor);
    }

    private void drawLocationInfo(Graphics g, int[] XY, int w, Color textColor, String vards){

        Cilveks cilveks = DataBase.cilvekuList.get(vards);

        int[] chunkXY = new int[]{cilveks.xyz.chunkXY.get(0), cilveks.xyz.chunkXY.get(1)},
                xy = new int[]{(int)cilveks.xyz.x, (int)cilveks.xyz.y},
                xySum = new int[]{
                        (int)(cilveks.xyz.x + cilveks.xyz.chunkXY.get(0) * DataBase.mapChunkW),
                        (int)(cilveks.xyz.y + cilveks.xyz.chunkXY.get(1) * DataBase.mapChunkW)};

        String text1 = "chunk: [ " + chunkXY[0] + " / " + chunkXY[1] + " ]",
                text2 = "xy: [ " + xy[0] + " / " + xy[1] + " ]",
                text3 = "xySum: [ " + xySum[0] + " / " + xySum[1] + " ]";

        int column1w = 80, column2w = 90;

        printLine(g, XY, w,
                text1,
                textColor);

        printLine(g, new int[]{XY[0] + column1w, XY[1]}, w,
                text2,
                textColor);

        printLine(g, new int[]{XY[0] + column1w + column2w, XY[1]}, w,
                text3,
                textColor);
    }

    //	//zemâk vecie parametri
//	// --------------------
//	//zemâk par centrâlâ cilvçku tablo Parametriem (no vecâ varianta)
//
//
//	protected Color tablo2krasaDefault, tablo2krasaCritical; //paðas krâsas nosaka initialize() ciklâ
//
//		tablo2krasaDefault=new Color(255,255,0); //dzeltens
//		tablo2krasaCritical=new Color(255,0,0); //sarkans
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
