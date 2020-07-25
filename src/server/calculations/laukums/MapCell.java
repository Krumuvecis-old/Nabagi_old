package server.calculations.laukums;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class MapCell {

    public int terrainType;

    public static HashMap<Integer, TerrainInfo> terrainPresets;

    public static class TerrainInfo {

        public String spriteName;
        public Color defaultColor;

        public HashMap<String, ItemGenInfo> lootGeneratorInfo;

        public static class ItemGenInfo {
            public double genKoef, genMin, genMax; //ìenerçðanas koeficienti
            ItemGenInfo(double _genKoef, double _genMin, double _genMax){
                genKoef = _genKoef;
                genMin = _genMin;
                genMax = _genMax;
            }
        }

        public TerrainInfo(String _spriteName, Color _defaultColor, HashMap<String, ItemGenInfo> _lootGeneratorInfo){
            spriteName = _spriteName;
            defaultColor = _defaultColor;

            lootGeneratorInfo = _lootGeneratorInfo;

            terrainPresets.put(terrainPresets.size(), this);
        }

        public static void generateTerrainPresets(){
            terrainPresets = new HashMap<>();
            HashMap<String, ItemGenInfo> _lootGeneratorInfo;

            //0 - dirt
            _lootGeneratorInfo = new HashMap<>();
            generateSingleTerrainPreset("terrainDirt", new Color(130,80,60), _lootGeneratorInfo);

            //1 - grass
            _lootGeneratorInfo = new HashMap<>();
            _lootGeneratorInfo.put("Paika", new ItemGenInfo(0.01, 0.5, 3));
            generateSingleTerrainPreset("terrainGrass", new Color(60,100,20), _lootGeneratorInfo);

            //2 - sand
            _lootGeneratorInfo = new HashMap<>();
            generateSingleTerrainPreset("terrainSand", new Color(130,110,40), _lootGeneratorInfo);

            //3 - stone
            _lootGeneratorInfo = new HashMap<>();
            _lootGeneratorInfo.put("Zelts", new ItemGenInfo(0.001, 1,5));
            generateSingleTerrainPreset("terrainStone", new Color(90,90,90), _lootGeneratorInfo);
        }

        private static void generateSingleTerrainPreset(String _spriteName, Color _krasa, HashMap<String, ItemGenInfo> _lootGeneratorInfo){
            terrainPresets.put(terrainPresets.size(), new TerrainInfo(
                    _spriteName, _krasa, _lootGeneratorInfo));
        }
    }

    public MapCell(){
        terrainType = 0;
    }

    public void updateValues(){
        //te terrain maina vçrtîbas

        Random r = new Random();

        switch (terrainType) {
            case 0-> { //dubïi
                double terrainChangeChance = 0.0002;
                if(r.nextDouble() < terrainChangeChance){
                    terrainType = 1; //izaug zâle
                }
            }
            case 1 -> { //zâle
                double terrainChangeChance = 0.00002;
                if(r.nextDouble() < terrainChangeChance){
                    terrainType = 0; //novîst zâle
                }
            }
            case 2 -> {
                //smiltis
            }
            case 3 -> {
                //akmens
            }

            default -> {}
        }
    }

}
