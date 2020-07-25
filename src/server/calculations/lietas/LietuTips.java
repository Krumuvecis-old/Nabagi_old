package server.calculations.lietas;

import java.util.HashMap;
import java.util.Map;

public class LietuTips {

    public double izmers, masa; //uz vienu skaita vienîbu
    public double degradeCoefficient;
    public double zelts, paika;
    public double attack, defence; //cîòas parametri
    public String spriteName; //grafikai

    private LietuTips(String _spriteName,
                      double _izmers, double _masa, double _degradeCoefficient,
                      double _zelts, double _paika,
                      double _attack, double _defence){
        spriteName = _spriteName;

        izmers = _izmers;
        masa = _masa;

        degradeCoefficient = _degradeCoefficient;

        zelts = _zelts;
        paika = _paika;

        attack = _attack;
        defence = _defence;
    }

    public static Map<String, LietuTips> lietuTipi = new HashMap<>();

    public static void generateLietuTipi(){
        double defaultMasa = 1,
                defaultAttack=0, defaultDefence=0;

        //pirmajai vienmçr jâbût default lietai
        lietuTipi.put("Default",
                new LietuTips("zvaigzne",
                        10, defaultMasa, 0,
                        0, 0,
                        defaultAttack, defaultDefence));

        lietuTipi.put("Zelts",
                new LietuTips("zelts",
                        8, defaultMasa, 0.0005,
                        1, 0,
                        defaultAttack, defaultDefence));

        lietuTipi.put("Paika",
                new LietuTips("banana",
                        10, defaultMasa, 0.001,
                        0, 1,
                        defaultAttack, defaultDefence));
    }


}
