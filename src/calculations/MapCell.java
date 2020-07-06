package calculations;

import java.util.Random;

public class MapCell {

    public int terrainType;

    private static double terrainChance = 0.05;

    public MapCell(){
        Random r = new Random();
        if (r.nextDouble()<terrainChance){
            terrainType = 1;
        } else terrainType = 0;
    }

    public void updateValues(){


    }

}
