package server.calculations.lietas;

import java.util.HashMap;
import java.util.Map;

public class LietuTips {

    public double genKoef, genMin, genMax; //ìenerçğanas koeficienti
    public double  izmers, masa; //uz vienu skaita vienîbu
    public double zelts, paika;
    public double attack, defence; //cîòas parametri

    private LietuTips(double _genKoef, double _genMin, double _genMax,
                      double _izmers, double _masa,
                      double _zelts, double _paika,
                      double _attack, double _defence){
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
                new LietuTips(0, 1, 1,
                        10, defaultMasa,
                        0, 0,
                        defaultAttack, defaultDefence));

        lietuTipi.put("Zelts",
                new LietuTips(1, 1,5,
                        6, defaultMasa,
                        1, 0,
                        defaultAttack, defaultDefence));

        lietuTipi.put("Paika",
                new LietuTips(2, 0.5, 3,
                        8, defaultMasa,
                        0, 1,
                        defaultAttack, defaultDefence));
    }


}
