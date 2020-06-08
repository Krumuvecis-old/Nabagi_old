package grafika.main.map;

import calculations.KonstantesUniversal;
import calculations.konstantes.Grafiskie;

import java.awt.*;

public class Terrain {

    protected static void main(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums, double merogs){

        drawLaukumaFons(g, x0, y0, kartesPlatums, kartesAugstums); //uzz�m� laukumu

        //�eit b�s j�z�m� reljefs un zeme

        drawRutinas(g, true,  true, x0, y0, kartesPlatums, kartesAugstums, merogs); //r�ti�u z�m��anai

    }

    private static void drawLaukumaFons(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums){
        g.setColor(Grafiskie.laukumaKrasa); //uzz�m� laukumu
        g.fillRect(x0, y0, kartesPlatums, kartesAugstums);
    }

    private static void drawRutinas(Graphics g, boolean drawChunks, boolean drawCells, int x0, int y0, int kartesPlatums, int kartesAugstums, double merogs){

        Color chunkColor=Color.gray, cellColor=Color.darkGray;

        if(drawChunks){ //uzz�m� chunk r�ti�as

            int chunkCountX=KonstantesUniversal.mapChunkCountX,
                    chunkCountY=KonstantesUniversal.mapChunkCountY;

            for(int i=0; i <= chunkCountY; i++){ //horizont�l�s l�nijas

                g.setColor(chunkColor);
                int dyChunk=(int)(i * KonstantesUniversal.mapChunkW * merogs);
                g.drawLine(x0, y0+dyChunk, x0+kartesPlatums, y0+dyChunk);

                if(drawCells && i<chunkCountY ){
                    g.setColor(cellColor);
                    //uzz�m� cell r�ti�as
                    for(int j=1; j < KonstantesUniversal.mapCellCount; j++){

                        int dyCell=dyChunk + (int)(j * KonstantesUniversal.mapCellW * merogs);
                        g.drawLine(x0, y0+dyCell, x0+kartesPlatums, y0+dyCell);


                    }

                }

            }

            for(int i=0; i <= chunkCountX; i++){ //vertik�l�s l�nijas

                g.setColor(chunkColor);
                int dxChunk=(int)(i * KonstantesUniversal.mapChunkW * merogs);
                g.drawLine(x0 + dxChunk, y0, x0 + dxChunk, y0 + kartesAugstums);

                if(drawCells && i< chunkCountX){
                    g.setColor(cellColor);
                    //uzz�m� cell r�ti�as
                    for(int j=1; j < KonstantesUniversal.mapCellCount; j++){

                        int dxCell=dxChunk + (int)(j * KonstantesUniversal.mapCellW * merogs);
                        g.drawLine(x0 + dxCell, y0, x0 + dxCell, y0 + kartesAugstums);


                    }

                }

            }
        }
    }

}
