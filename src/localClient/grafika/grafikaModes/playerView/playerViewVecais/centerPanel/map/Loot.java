package localClient.grafika.grafikaModes.playerView.playerViewVecais.centerPanel.map;

import server.calculations.MapChunk;
import localClient.grafika.grafikaModes.playerView.playerViewVecais.PlayerThread;

import java.awt.*;
import java.util.ArrayList;

class Loot {

    protected void map(Graphics g, ArrayList<ArrayList<MapChunk>> laukums, PlayerThread thread, double R1, boolean drawInfo) {

        //zemâk viss kopçts no vecâ drawMap

//        for(int[] chunkXY={0,0}; chunkXY[0]<laukums.size(); chunkXY[0]++){
//            for(chunkXY[1]=0; chunkXY[1]<laukums.get(chunkXY[0]).size(); chunkXY[1]++){
//
//                ArrayList<Lieta> lietasList = laukums.get(chunkXY[0]).get(chunkXY[1]).lietas;
//
//                for(int i=0;i<lietasList.size();i++) { //zîmç lietas, kas izmçtâtas pa karti
//
//                    Lieta lieta=lietasList.get(i);
//                    double dx = thread.dati.player.xyz.x - lieta.x,
//                            dy = thread.dati.player.xyz.y - lieta.y;
//
//                    if (Math.hypot(dx, dy) > R1) continue; //lai atmet tos kas par tâlu
//
//                    double[] koord;
//                    koord = SpectateMap.getAbsoluteCoordinates(false, dx, dy);
//                    double resnums = KonstantesUniversal.defaultLietas.get(0).izmers * merogs;
//                    Color krasa1 = KonstantesUniversal.defaultLietas.get(0).krasa, krasa2=Color.black; //iekða un kontûra
//
//                    for(int j=1; j < KonstantesUniversal.defaultLietas.size(); j++){
//                        if(lieta.nosaukums.equals(KonstantesUniversal.defaultLietas.get(j).nosaukums)){
//                            resnums = KonstantesUniversal.defaultLietas.get(j).izmers * merogs;
//                            krasa1=KonstantesUniversal.defaultLietas.get(j).krasa; //iekða
//                        }
//                    }
//
//                    if(thread.dati.lietasDrawInfo) {
//                        g.setColor(krasa2);
//                        String nosaukums=""+new DecimalFormat("#.#").format(lieta.daudzums);
//                        g.drawString(nosaukums, (int)(koord[0]+resnums/2+3), (int)(koord[1]+7));
//                    }
//
//                    g.setColor(krasa1); //iekða
//                    g.fillOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
//
//                    g.setColor(krasa2); //kontûra
//                    g.drawOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
//
//                }
//
//
//            }
//        }

        //augstâk viss kopçts no vecâ drawMap

    }


    //zemâk viss kopçts no setupView
//
//    protected static void main(Graphics g, int x0, int y0, double merogs, ArrayList<ArrayList<MapChunk>> laukums) {
//
//        //iziet cauri visiem chunkiem
//        for(int[] chunkXY={0,0}; chunkXY[0]<laukums.size(); chunkXY[0]++){
//            for(chunkXY[1]=0; chunkXY[1]<laukums.get(chunkXY[0]).size(); chunkXY[1]++){
//
//                ArrayList<Lieta> lietasList = laukums.get(chunkXY[0]).get(chunkXY[1]).lietas;
//
//                // apskata katru lietu chunkâ
//                for(int i=0;i<lietasList.size();i++) { //zîmç lietas, kas izmçtâtas pa karti
//                    Lieta lieta = lietasList.get(i);
//                    drawLootOnce(g, x0, y0, merogs, chunkXY, lieta);
//
//                }
//
//            }
//        }
//
//
//
//    }
//
//
//    private static void drawLootOnce(Graphics g, int x0, int y0, double merogs, int[] chunkXY, Lieta lieta){
//        double x = x0 + merogs * (lieta.x + KonstantesUniversal.mapChunkW * chunkXY[0]),
//                y = y0 + merogs * (lieta.y + KonstantesUniversal.mapChunkW * chunkXY[1]),
//                resnums = KonstantesUniversal.defaultLietas.get(0).izmers * merogs;
//        Color krasa1=KonstantesUniversal.defaultLietas.get(0).krasa, // iekðai (default-default)
//                krasa2=Color.black; // kontûrai
//
//        for(int j=1; j<KonstantesUniversal.defaultLietas.size(); j++){ //pârbauda kuram tipam atbilst
//            if(KonstantesUniversal.defaultLietas.get(j).nosaukums.equals(lieta.nosaukums)) {
//                krasa1 = KonstantesUniversal.defaultLietas.get(j).krasa;
//                resnums = KonstantesUniversal.defaultLietas.get(j).izmers * merogs;
//                break;
//            }
//        }
//        g.setColor(krasa1); //iekða
//        g.fillOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
//        g.setColor(krasa2); //kontûra
//        g.drawOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
//
//    }

    //augstâk viss kopçts no setupView

}
