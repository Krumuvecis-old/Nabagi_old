package localClient.grafika.playerViewVecais.centerPanel.map;

import localClient.grafika.playerViewVecais.PlayerThread;
import server.dataBase.DataBase;

import java.awt.*;

class Terrain {

    protected void main(Graphics g, PlayerThread thread, int x0, int y0, double merogs){

        int kartePlatums=thread.dati.kartePlatums;

        drawFons(g, x0, y0, kartePlatums);

        //drawMapEdges(g); // vai tie��m ��ds vajadz�gs?

        drawRutinas(g, x0, y0, kartePlatums, merogs); //r�ti�u z�m��anai

        //�eit v�l b�s j�z�m� reljefs un zeme

    }

    private void drawFons(Graphics g, int x0, int y0, int kartePlatums) {

        g.setColor(DataBase.malasKrasa); //laukuma mala
        g.fillRect(x0, y0, kartePlatums, kartePlatums);

        g.setColor(DataBase.laukumaKrasa); //laukums
        g.fillRect(x0, y0, kartePlatums, kartePlatums);
        g.setColor(Color.black); //laukuma kont�ra
        g.drawRect(x0, y0, kartePlatums, kartePlatums);
    }

    private static void drawRutinas(Graphics g, int x0, int y0, int kartesPlatums, double merogs){

        boolean drawChunks=true, drawCells=true;

        if(drawChunks){ //uzz�m� chunk r�ti�as

            Color chunkColor=Color.gray, cellColor=Color.darkGray;

            //zem�k viss kop�ts no setupView

//            int chunkCountX=KonstantesUniversal.mapChunkCountX,
//                    chunkCountY=KonstantesUniversal.mapChunkCountY;
//
//            for(int i=0; i <= chunkCountY; i++){ //horizont�l�s l�nijas
//
//                g.setColor(chunkColor);
//                int dyChunk=(int)(i * KonstantesUniversal.mapChunkW * merogs);
//                g.drawLine(x0, y0+dyChunk, x0+kartesPlatums, y0+dyChunk);
//
//                if(drawCells && i<chunkCountY ){
//                    g.setColor(cellColor);
//                    //uzz�m� cell r�ti�as
//                    for(int j=1; j < KonstantesUniversal.mapCellCount; j++){
//
//                        int dyCell=dyChunk + (int)(j * KonstantesUniversal.mapCellW * merogs);
//                        g.drawLine(x0, y0+dyCell, x0+kartesPlatums, y0+dyCell);
//
//
//                    }
//
//                }
//
//            }
//
//            for(int i=0; i <= chunkCountX; i++){ //vertik�l�s l�nijas
//
//                g.setColor(chunkColor);
//                int dxChunk=(int)(i * KonstantesUniversal.mapChunkW * merogs);
//                g.drawLine(x0 + dxChunk, y0, x0 + dxChunk, y0 + kartesAugstums);
//
//                if(drawCells && i< chunkCountX){
//                    g.setColor(cellColor);
//                    //uzz�m� cell r�ti�as
//                    for(int j=1; j < KonstantesUniversal.mapCellCount; j++){
//
//                        int dxCell=dxChunk + (int)(j * KonstantesUniversal.mapCellW * merogs);
//                        g.drawLine(x0 + dxCell, y0, x0 + dxCell, y0 + kartesAugstums);
//
//
//                    }
//
//                }
//
//            }

            //augst�k viss kop�ts no setupView
        }
    }

    //zem�k viss kop�ts no vec� drawMap

//    private void drawMapEdges(Graphics g) { //vai �� metode visp�r ir vajadz�ga?
//        int laukumaPlatums=KonstantesUniversal.laukumaPlatumsSum, laukumaAugstums=KonstantesUniversal.laukumaAugstumsSum, kartesPlatums=thread.dati.kartePlatums;
//
//        int[] sturisZR = {0,0},
//                sturisZA = {laukumaPlatums,0},
//                sturisDR = {0,laukumaAugstums},
//                sturisDA = {laukumaPlatums,laukumaAugstums}; //laukuma st�ri
//
//        int[] galaPunkts1={0,0}, galaPunkts2={0,0};
//        for(int i=0; i<4; i++) {
//            if (i==0) { //rietumu mala
//                galaPunkts1[0] = sturisZR[0];
//                galaPunkts1[1] = sturisZR[1];
//
//                galaPunkts2[0] = sturisDR[0];
//                galaPunkts2[1] = sturisDR[1];
//
//            } else if (i==1) { //zieme�u mala
//                galaPunkts1[0] = sturisZR[0];
//                galaPunkts1[1] = sturisZR[1];
//
//                galaPunkts2[0] = sturisZA[0];
//                galaPunkts2[1] = sturisZA[1];
//
//            } else if (i==2) { //austrumu mala
//                galaPunkts1[0] = sturisZA[0];
//                galaPunkts1[1] = sturisZA[1];
//
//                galaPunkts2[0] = sturisDA[0];
//                galaPunkts2[1] = sturisDA[1];
//
//            } else if (i==3) { //dienvidu mala
//                galaPunkts1[0] = sturisDR[0];
//                galaPunkts1[1] = sturisDR[1];
//
//                galaPunkts2[0] = sturisDA[0];
//                galaPunkts2[1] = sturisDA[1];
//
//            }
//
//            double[] koord1=getAbsoluteCoordinates(false,x0-galaPunkts1[0],y0-galaPunkts1[1]),
//                    koord2=getAbsoluteCoordinates(false,x0-galaPunkts2[0],y0-galaPunkts2[1]); //ieg�st l�niju galapunktus
//
//            //piel�dzina koordin�tas, lai neb�tu �rpus r�mjiem
//            koord1[0]=Math.max(nobideX, Math.min(nobideX+kartesPlatums, koord1[0]));
//            koord2[0]=Math.max(nobideX, Math.min(nobideX+kartesPlatums, koord2[0]));
//            koord1[1]=Math.max(nobideY, Math.min(nobideY+kartesPlatums, koord1[1]));
//            koord2[1]=Math.max(nobideY, Math.min(nobideY+kartesPlatums, koord2[1]));
//
//            //pa�as malas z�m��ana
//            g.setColor(Color.darkGray);
//            g.drawLine((int)koord1[0], (int)koord1[1], (int)koord2[0], (int)koord2[1]);
//
//            //z�m� lielo redzesloku, lai par�d�tu z�m��anas laukumu
//            double[] koordCenter = getAbsoluteCoordinates(true,0,0);
//            g.drawOval((int)(koordCenter[0]-R2*merogs), (int)(koordCenter[1]-R2*merogs), (int)(R2*2*merogs),(int)(R2*2*merogs));
//
//            if (thread.dati.drawCrosshair) {
//                int crosshairSizeDelta=Dati.crosshairSize/2;
//                g.drawLine((int)koordCenter[0], (int)koordCenter[1]-crosshairSizeDelta, (int)koordCenter[0], (int)koordCenter[1]+crosshairSizeDelta); //vertik�l� l�nija
//                g.drawLine((int)koordCenter[0]-crosshairSizeDelta, (int)koordCenter[1], (int)koordCenter[0]+crosshairSizeDelta, (int)koordCenter[1]); //horizont�l� l�nija
//            }
//        }
//
//    }

    //augst�k viss kop�ts no vec� drawMap

}
