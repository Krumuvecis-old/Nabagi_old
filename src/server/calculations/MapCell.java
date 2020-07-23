package server.calculations;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class MapCell {

    public int terrainType;

    public static HashMap<Integer, TerrainInfo> terrainPresets;

    public static class TerrainInfo {

        public String spriteName;
        public Color defaultColor;

        public TerrainInfo(String _spriteName, Color _defaultColor){
            spriteName = _spriteName;
            defaultColor = _defaultColor;

            terrainPresets.put(terrainPresets.size(), this);
        }

        public static void generateTerrainPresets(){
            terrainPresets = new HashMap<>();
            terrainPresets.put(terrainPresets.size(), new TerrainInfo(
                    "terrainDirt", new Color(130,80,60)));
            terrainPresets.put(terrainPresets.size(), new TerrainInfo(
                    "terrainGrass", new Color(60,100,20)));
            terrainPresets.put(terrainPresets.size(), new TerrainInfo(
                    "terrainSand", new Color(130,110,40)));
            terrainPresets.put(terrainPresets.size(), new TerrainInfo(
                    "terrainStone", new Color(90,90,90)));
        }
    }

    public MapCell(){
        terrainType = 0;
    }

    public void updateValues(){
        //te terrain maina vçrtîbas

        double terrainChangeChance = 0.0005;
        Random r = new Random();

        if(r.nextDouble() < terrainChangeChance){
            if(terrainType == 0) terrainType = 1; //izaug zâle
            else if(terrainType == 1) terrainType = 0; //novîst zâle
        }

    }


}
