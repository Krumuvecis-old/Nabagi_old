package server.calculations;

public class KonstantesUniversal {
    public static String versija="0.11.3 (beta)";

    public static int mapCellW=20, mapCellCount=10,
            mapChunkW=mapCellW * mapCellCount, mapChunkCountX=6, mapChunkCountY=4;

    public static int laukumaPlatumsSum = mapChunkW * mapChunkCountX,
            laukumaAugstumsSum = mapChunkW * mapChunkCountY;

    public static double overallGenRate=0.05; //overall loot generation rate

}

