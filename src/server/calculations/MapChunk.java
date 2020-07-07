package server.calculations;

import server.calculations.lietas.Lieta;

import java.util.*;


public class MapChunk {
    public Set<String> cilvekiList; //cilv�ku saraksts
    public List<Lieta> lietas; //loot datub�ze
    public Map<List<Integer>, MapCell> mapCells; //maz�s r�ti�as

    public MapChunk(){
        generateMapCells();

        cilvekiList = new HashSet<>();
        lietas = new ArrayList<>();
    }

    private void generateMapCells(){
        mapCells = new HashMap<>();
        int mapCellCount = KonstantesUniversal.mapCellCount;

        int x, y;
        for (x = 0; x < mapCellCount; x++){
            for (y = 0; y < mapCellCount; y++){
                List<Integer> xy = new ArrayList<>();
                xy.add(x);
                xy.add(y);
                mapCells.put(xy, new MapCell());
            }
        }
    }

}
