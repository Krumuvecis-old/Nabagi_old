package grafika.main.map;

import calculations.KonstantesUniversal;
import calculations.konstantes.Grafiskie;

import java.awt.*;

public class Terrain {

    protected static void main(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums, double merogs){

        drawLaukumaFons(g, x0, y0, kartesPlatums, kartesAugstums); //uzzîmç laukumu

        //ðeit bûs jâzîmç reljefs un zeme

        drawRutinas(g, true,  true, x0, y0, kartesPlatums, kartesAugstums, merogs); //rûtiòu zîmçðanai

    }

    private static void drawLaukumaFons(Graphics g, int x0, int y0, int kartesPlatums, int kartesAugstums){
        g.setColor(Grafiskie.laukumaKrasa); //uzzîmç laukumu
        g.fillRect(x0, y0, kartesPlatums, kartesAugstums);
    }

    private static void drawRutinas(Graphics g, boolean drawChunks, boolean drawCells, int x0, int y0, int kartesPlatums, int kartesAugstums, double merogs){

        Color chunkColor=Color.gray, cellColor=Color.darkGray;

        if(drawChunks){ //uzzîmç chunk rûtiòas

            int chunkCountX=KonstantesUniversal.mapChunkCountX,
                    chunkCountY=KonstantesUniversal.mapChunkCountY;

            for(int i=0; i <= chunkCountY; i++){ //horizontâlâs lînijas

                g.setColor(chunkColor);
                int dyChunk=(int)(i * KonstantesUniversal.mapChunkW * merogs);
                g.drawLine(x0, y0+dyChunk, x0+kartesPlatums, y0+dyChunk);

                if(drawCells && i<chunkCountY ){
                    g.setColor(cellColor);
                    //uzzîmç cell rûtiòas
                    for(int j=1; j < KonstantesUniversal.mapCellCount; j++){

                        int dyCell=dyChunk + (int)(j * KonstantesUniversal.mapCellW * merogs);
                        g.drawLine(x0, y0+dyCell, x0+kartesPlatums, y0+dyCell);


                    }

                }

            }

            for(int i=0; i <= chunkCountX; i++){ //vertikâlâs lînijas

                g.setColor(chunkColor);
                int dxChunk=(int)(i * KonstantesUniversal.mapChunkW * merogs);
                g.drawLine(x0 + dxChunk, y0, x0 + dxChunk, y0 + kartesAugstums);

                if(drawCells && i< chunkCountX){
                    g.setColor(cellColor);
                    //uzzîmç cell rûtiòas
                    for(int j=1; j < KonstantesUniversal.mapCellCount; j++){

                        int dxCell=dxChunk + (int)(j * KonstantesUniversal.mapCellW * merogs);
                        g.drawLine(x0 + dxCell, y0, x0 + dxCell, y0 + kartesAugstums);


                    }

                }

            }
        }
    }

}
