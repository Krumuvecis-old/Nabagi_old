package calculations.konstantes;

public class Lietu {

    public static double overallGenRate=0.2; //0.007 bija labi (0.015 televizoram)

    public static double goldGenRate, goldGenRateKoefic=1, //zelta ìenerators
            goldGenMin=1, goldGenMax=7;

    public static double paikaGenRate, paikaGenRateKoefic=2, //paikas ìenerators
            paikaGenMin=1, paikaGenMax=3;


    public static int lietasResnums, // default neklasificçtai lietai
            zeltaResnums, paikasResnums;

    public static void initialize(){

        updateGenRates();


    }

    public static void updateGenRates(){
        goldGenRate=overallGenRate*goldGenRateKoefic;
        paikaGenRate=overallGenRate*paikaGenRateKoefic;

    }

}
