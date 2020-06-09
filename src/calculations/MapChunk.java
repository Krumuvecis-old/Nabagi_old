package calculations;

import calculations.cilveki.Cilveks;
import calculations.lietas.Lieta;

import java.util.ArrayList;

public class MapChunk {
    public ArrayList<Cilveks> cilvekiList; //cilvçku datubâze
    public ArrayList<Lieta> lietas; //loot datubâze
    public ArrayList<ArrayList<MapCell>> mapCells; //mazâs rûtiòas



    public void initialize(){
        initializeCells();

        cilvekiList = new ArrayList<Cilveks>();
        lietas = new ArrayList<Lieta>();

    }


    private void initializeCells(){
        int mapCellCount = KonstantesUniversal.mapCellCount;
        mapCells =  new  ArrayList<ArrayList<MapCell>>();

        for (int[] cellXY={0,0}; cellXY[0]<mapCellCount; cellXY[0]++){
            mapCells.add(new ArrayList<MapCell>());
            for (cellXY[1]=0; cellXY[1]<mapCellCount;cellXY[1]++){
                MapCell cell = new MapCell();
                mapCells.get(cellXY[0]).add(cell);
                mapCells.get(cellXY[0]).get(cellXY[1]).initialize();

            }
        }
    }

}
