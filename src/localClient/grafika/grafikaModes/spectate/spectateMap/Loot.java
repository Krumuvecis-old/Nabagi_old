package localClient.grafika.grafikaModes.spectate.spectateMap;

import java.awt.*;

public class Loot {

    Loot(){

    }

    void draw(Graphics g){

    }


//    protected static void main(Graphics g, int x0, int y0, double merogs, ArrayList<ArrayList<MapChunk>> laukums) {
//
//        //iziet cauri visiem chunkiem
//        for(int[] chunkXY={0,0}; chunkXY[0]<laukums.size(); chunkXY[0]++){
//            for(chunkXY[1]=0; chunkXY[1]<laukums.get(chunkXY[0]).size(); chunkXY[1]++){
//
//                ArrayList<Lieta> lietasList = laukums.get(chunkXY[0]).get(chunkXY[1]).lietas;
//
//                // apskata katru lietu chunk�
//                for(int i=0;i<lietasList.size();i++) { //z�m� lietas, kas izm�t�tas pa karti
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
//        Color krasa1=KonstantesUniversal.defaultLietas.get(0).krasa, // iek�ai (default-default)
//                krasa2=Color.black; // kont�rai
//
//        for(int j=1; j<KonstantesUniversal.defaultLietas.size(); j++){ //p�rbauda kuram tipam atbilst
//            if(KonstantesUniversal.defaultLietas.get(j).nosaukums.equals(lieta.nosaukums)) {
//                krasa1 = KonstantesUniversal.defaultLietas.get(j).krasa;
//                resnums = KonstantesUniversal.defaultLietas.get(j).izmers * merogs;
//                break;
//            }
//        }
//        g.setColor(krasa1); //iek�a
//        g.fillOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
//        g.setColor(krasa2); //kont�ra
//        g.drawOval((int)(x-resnums/2), (int)(y-resnums/2), (int)resnums, (int)resnums);
//
//    }

}
