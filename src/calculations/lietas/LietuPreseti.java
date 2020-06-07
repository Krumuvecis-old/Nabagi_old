package calculations.lietas;

import calculations.KonstantesUniversal;

public class LietuPreseti {

    public String nosaukums;
    public double genKoef, genMin, genMax;
    public double  izmers, masa;
    public double zelts, paika;
    public double attack, defence, condition;

    public static void initialize(){
        double defaultMasa = 1, defaultAttack=0, defaultDefence=0, defaultCondition = 1;

        //pirmajai vienm�r j�b�t default lietai
        addDefaultLieta("Default", 0, 1, 1,
                10, defaultMasa, 0, 0,
                defaultAttack, defaultDefence, defaultCondition);

        addDefaultLieta("Zelts", 1, 1,5,
                6, defaultMasa, 1, 0,
                defaultAttack, defaultDefence, defaultCondition);

        addDefaultLieta("Paika", 2, 0.5, 3,
                8, defaultMasa, 0, 1,
                defaultAttack, defaultDefence, defaultCondition);


    }

    private static void addDefaultLieta(String nosaukums, double genKoef, double genMin, double genMax,
                                        double izmers, double masa, double zelts, double paika,
                                        double attack, double defence, double condition){
        LietuPreseti lieta = new LietuPreseti();
        lieta.nosaukums=nosaukums;
        lieta.genKoef=genKoef;
        lieta.genMin=genMin;
        lieta.genMax=genMax;
        lieta.izmers=izmers;
        lieta.masa=masa;
        lieta.zelts=zelts;
        lieta.paika=paika;
        lieta.attack=attack;
        lieta.defence=defence;
        lieta.condition=condition;

        KonstantesUniversal.defaultLietas.add(lieta);
    }

}
