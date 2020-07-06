package calculations;

import calculations.lietas.Lieta;

import java.util.*;


public class MapChunk {
    public Set<String> cilvekiList; //cilvçku saraksts
    public List<Lieta> lietas; //loot datubâze
    public Map<int[], MapCell> mapCells; //mazâs rûtiòas

    public MapChunk(){
        generateMapCells();

        cilvekiList = new HashSet<>();
        lietas = new ArrayList<>();
    }

    private void generateMapCells(){
        int mapCellCount = KonstantesUniversal.mapCellCount;
        mapCells = new HashMap<>();

        for (int[] cellXY={0,0}; cellXY[0]<mapCellCount; cellXY[0]++){
            for (cellXY[1]=0; cellXY[1]<mapCellCount; cellXY[1]++){
                MapCell cell = new MapCell();
                mapCells.put(cellXY, cell);
            }
        }
    }

}
