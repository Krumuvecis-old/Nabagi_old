package server.calculations.lietas;

import java.util.HashMap;
import java.util.Map;

public class LietuTips {

    public double genKoef, genMin, genMax; //ìenerçðanas koeficienti
    public double izmers, masa; //uz vienu skaita vienîbu
    public double zelts, paika;
    public double attack, defence; //cîòas parametri
    public String spriteName; //grafikai

    private LietuTips(String _spriteName,
                      double _genKoef, double _genMin, double _genMax,
                      double _izmers, double _masa,
                      double _zelts, double _paika,
                      double _attack, double _defence){
        spriteName = _spriteName;

        genKoef = _genKoef;
        genMin = _genMin;
        genMax = _genMax;

        izmers = _izmers;
        masa = _masa;

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
                        0, 1, 1,
                        10, defaultMasa,
                        0, 0,
                        defaultAttack, defaultDefence));

        lietuTipi.put("Zelts",
                new LietuTips("zelts",
                        1, 1,5,
                        8, defaultMasa,
                        1, 0,
                        defaultAttack, defaultDefence));

        lietuTipi.put("Paika",
                new LietuTips("banana",
                        2, 0.5, 3,
                        10, defaultMasa,
                        0, 1,
                        defaultAttack, defaultDefence));
    }


}
