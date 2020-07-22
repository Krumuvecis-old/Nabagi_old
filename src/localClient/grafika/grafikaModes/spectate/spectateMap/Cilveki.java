package localClient.grafika.grafikaModes.spectate.spectateMap;

import java.awt.*;

class Cilveki {

    Cilveki(){

    }

    void draw(Graphics g){

    }

    //zemâk no vecâ

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


//    protected static void main(Graphics g, ClientThread thread, int x0, int y0, double merogs, ArrayList<ArrayList<MapChunk>> laukums, ArrayList<Komanda> komandasList) { //papildinâjums kartei
//
//        double resnumaKoefic = FizikasKonstantes.resnumaKoefic;
//
//        //iziet cauri visiem chunkiem
//        for(int[] chunkXY={0,0}; chunkXY[0]<laukums.size(); chunkXY[0]++){
//            for(chunkXY[1]=0; chunkXY[1]<laukums.get(chunkXY[0]).size(); chunkXY[1]++){
//
//                //pârbauda visus spçlçtâjus konkrçtajâ chunkâ
//                for(int i = 0; i<laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++){
//
//                    Cilveks cilveks = laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(i); //pats apskatâmais spçlçtâjs
//
//                    double resnums=resnumaKoefic*cilveks.hpmax*merogs;
//
//                    int komanda=noteiktKomandasNumuru(cilveks); //krâsas noteikðanai
//                    Color krasa = noteiktKrasuCilvekam(cilveks, komanda);
//
//                    //rumpis
//
//                    double x = x0 + merogs * (cilveks.xyz.x + chunkXY[0] * KonstantesUniversal.mapChunkW),
//                            y = y0 + merogs * (cilveks.xyz.y + chunkXY[1] * KonstantesUniversal.mapChunkW);
//
//                    g.setColor(krasa); //iekða
//                    g.fillOval((int)(x-resnums/2),
//                            (int)(y-resnums/2),
//                            (int)resnums, (int)resnums);
//                    g.setColor(Color.black);//kontûra
//                    g.drawOval((int)(x-resnums/2),
//                            (int)(y-resnums/2),
//                            (int)resnums, (int)resnums);
//
//                    if(cilveks.vards.equals(komandasList.get(komanda).galvenais)) { //karalis
//                        drawKronis(g, x, y, resnums);
//                    }
//
//                    if(cilveks.vards.equals(thread.dati.playerFocusName)) { //fokusçtâ spçlçtâja redzesloks
//                        drawRedzesloks(g, x, y, merogs, cilveks, krasa);
//
//                    }
//
//                }
//            }
//        }
//
//    }
//
//    private static int noteiktKomandasNumuru(Cilveks cilveks){
//        int komanda=0;
//
//        for (int i = 0; i< CalculationsThread.komandasList.size(); i++) {
//            if(cilveks.komanda.equals(CalculationsThread.komandasList.get(i).nosaukums)) {
//                komanda=i;
//                break;
//            }
//        }
//
//        return komanda;
//    }
//
//    private static Color noteiktKrasuCilvekam(Cilveks cilveks, int komanda){
//        double hpRatio=cilveks.hp/cilveks.hpmax;
//
//        return new Color(Color.HSBtoRGB( (float)Formulas.getHue(CalculationsThread.komandasList.get(komanda).krasa),
//                (float)GrafikasDati.cilvekiKrasaSaturation,
//                (float)(GrafikasDati.cilvekiKrasaBrightnessMin + hpRatio *
//                        (GrafikasDati.cilvekiKrasaBrightnessMax - GrafikasDati.cilvekiKrasaBrightnessMin)) ));
//
//    }
//
//    private static void drawKronis(Graphics g, double x, double y, double resnums){
//        g.setColor(new Color(0,0,0)); //kroòa krâsa - melns punkts
//        double kronaResnums=resnums/2;
//        g.fillOval((int)(x-kronaResnums/2),
//                (int)(y-kronaResnums/2),
//                (int)kronaResnums, (int)kronaResnums); //kronis
//    }
//
//    private static void drawRedzesloks(Graphics g, double x, double y, double merogs, Cilveks cilveks, Color krasa){
//        g.setColor(krasa);
//        double R2temp=cilveks.R2*merogs;
//        g.drawOval((int)(x-R2temp), (int)(y-R2temp), (int)(R2temp*2),(int)(R2temp*2)); //R2 - tuvais
//
//        double R1temp=cilveks.R1*merogs;
//        g.drawOval((int)(x-R1temp), (int)(y-R1temp), (int)(R1temp*2),(int)(R1temp*2)); //R1 - tâlais
//    }
}
