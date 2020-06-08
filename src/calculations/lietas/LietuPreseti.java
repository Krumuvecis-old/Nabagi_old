package calculations.lietas;

import calculations.KonstantesUniversal;

import java.awt.*;

public class LietuPreseti {

    public String nosaukums;
    public double genKoef, genMin, genMax;
    public double  izmers, masa;
    public double zelts, paika;
    public double attack, defence, condition;
    public Color krasa;

    public static void initialize(){
        double defaultMasa = 1, defaultAttack=0, defaultDefence=0, defaultCondition = 1;


        Color lietasColorZelts = new Color(200,200,0), //dzeltens
                lietasColorPaika = new Color(255,100,50), //brûni oranþîgs
                lietasColorDefault = new Color(150,50,100); //violets vai purpurs

        //pirmajai vienmçr jâbût default lietai
        addDefaultLieta("Default", 0, 1, 1,
                10, defaultMasa, 0, 0,
                defaultAttack, defaultDefence, defaultCondition,  lietasColorDefault);

        addDefaultLieta("Zelts", 1, 1,5,
                6, defaultMasa, 1, 0,
                defaultAttack, defaultDefence, defaultCondition, lietasColorZelts);

        addDefaultLieta("Paika", 2, 0.5, 3,
                8, defaultMasa, 0, 1,
                defaultAttack, defaultDefence, defaultCondition, lietasColorPaika);


    }

    private static void addDefaultLieta(String nosaukums, double genKoef, double genMin, double genMax,
                                        double izmers, double masa, double zelts, double paika,
                                        double attack, double defence, double condition, Color krasa){
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
        lieta.krasa=krasa;

        KonstantesUniversal.defaultLietas.add(lieta);
    }

}
