package calculations;

import calculations.lietas.LietuPreseti;

import java.util.ArrayList;

public class KonstantesUniversal {
    public static String versija="0.11.1 (beta)";

    public static int mapCellW=20, mapCellCount=10,
            mapChunkW=mapCellW * mapCellCount, mapChunkCountX=4, mapChunkCountY=3;

    public static int laukumaPlatumsSum = mapChunkW * mapChunkCountX,
            laukumaAugstumsSum = mapChunkW * mapChunkCountY,
            mala = 10;

    public static ArrayList<LietuPreseti> defaultLietas = new ArrayList<LietuPreseti>();
    public static double overallGenRate=0.05; //overall loot generation rate

    public static void initialize(){



    }

}

