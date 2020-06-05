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

        for (int i=0; i<mapCellCount;i++){
            mapCells.add(new ArrayList<MapCell>());
            for (int j=0; j<mapCellCount;j++){
                MapCell cell = new MapCell();
                mapCells.get(i).add(cell);
                mapCells.get(i).get(j).initialize();

            }
        }
    }

}
