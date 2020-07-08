package server.calculations;

import server.calculations.lietas.Lieta;
import server.dataBase.DataBase;

import java.util.*;


public class MapChunk {
    public Set<String> cilvekiList; //cilvçku saraksts
    public List<Lieta> lietas; //loot datubâze
    public Map<List<Integer>, MapCell> mapCells; //mazâs rûtiòas

    public MapChunk(){
        generateMapCells();

        cilvekiList = new HashSet<>();
        lietas = new ArrayList<>();
    }

    private void generateMapCells(){
        mapCells = new HashMap<>();
        int mapCellCount = DataBase.mapCellCount;

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
