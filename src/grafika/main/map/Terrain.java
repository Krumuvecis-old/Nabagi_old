package grafika.main.map;

import calculations.KonstantesUniversal;
import calculations.konstantes.Grafiskie;

import java.awt.*;

public class Terrain {

    protected static void main(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums, double merogs){

        g.setColor(Grafiskie.laukumaKrasa); //uzz�m� laukumu
        g.fillRect(x0, y0, kartesPlatums, kartesAugstums);


        boolean drawChunks=true, drawCells=true; //r�ti�u z�m��anai
        Color chunkColor=Color.darkGray, cellColor=Color.green;

        if(drawChunks){ //uzz�m� chunk r�ti�as

            for(int i=0; i <= KonstantesUniversal.mapChunkCountX; i++){ //horizont�l�s l�nijas

                g.setColor(chunkColor);
                int dyChunk=(int)(i * KonstantesUniversal.mapChunkW * merogs);
                g.drawLine(x0, y0+dyChunk, kartesPlatums, y0+dyChunk);

                if(drawCells){
                    g.setColor(cellColor);
                    //uzz�m� cell r�ti�as
                    for(int j=1; j < KonstantesUniversal.mapCellCount; i++){

                        int dyCell=dyChunk + (int)(j * KonstantesUniversal.mapCellW * merogs);
                        g.drawLine(x0, y0+dyCell, kartesPlatums, y0+dyCell);


                    }

                }

            }

            for(int i=0; i <= KonstantesUniversal.mapChunkCountY; i++){ //vertik�l�s l�nijas

                g.setColor(chunkColor);
                int dxChunk=(int)(i * KonstantesUniversal.mapChunkW * merogs);
                g.drawLine(x0+dxChunk, y0, x0+dxChunk, kartesAugstums);

                if(drawCells){
                    g.setColor(cellColor);
                    //uzz�m� cell r�ti�as
                    for(int j=0; j <= KonstantesUniversal.mapChunkCountX; j++){

                        int dxCell=dxChunk + (int)(j * KonstantesUniversal.mapCellW * merogs);
                        g.drawLine(x0+dxCell, y0, x0+dxCell, kartesAugstums);


                    }

                }

            }
        }



    }

}
