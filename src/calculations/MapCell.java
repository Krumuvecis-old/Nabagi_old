package calculations;

import java.util.Random;

public class MapCell {

    public int terrainType;

    public void initialize(){
        double terrainChance=0.05;

        Random r = new Random();
        if (r.nextDouble()<terrainChance){
            terrainType = 1;
        } else terrainType = 0;

    }

    public void updateValues(){


    }

}
