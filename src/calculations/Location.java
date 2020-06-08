package calculations;

public class Location {
    public int[] chunkXY;
    public int i;

    public static int[] normalizeXY(int[] chunkXY){
        if(chunkXY[0]<0) chunkXY[0]+=KonstantesUniversal.mapChunkCountX; //rietumi
        if(chunkXY[1]<0) chunkXY[1]+=KonstantesUniversal.mapChunkCountY; //ziemeïi
        if(chunkXY[0]>=KonstantesUniversal.mapChunkCountX) chunkXY[0]-=KonstantesUniversal.mapChunkCountX; //austrumi
        if(chunkXY[1]>=KonstantesUniversal.mapChunkCountY) chunkXY[1]-=KonstantesUniversal.mapChunkCountY; //dienvidi


        return chunkXY;
    }
}
