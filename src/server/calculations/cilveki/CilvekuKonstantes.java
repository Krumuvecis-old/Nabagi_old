package server.calculations.cilveki;

public class CilvekuKonstantes {

    public static String vardsDefault = "S";
    public static int maxGataviba = 100;

    public static double vmax = 3, ommax = 30,
            omChangeRate = 0.1; //grieðanâs atpûðoties

    public static double RMax = 200; //maksimâlais redzesloks (jaunus ìenerçjot)
    public static double R2koefic = 0.3; //minimâlâ redzesloka daïas koefic

    public static double hpmax = 20; //(jaunus ìenerçjot)

    public static double dRDzimstot = 0.1, //redzesloka procentuâla izmaiòa vairojoties
            dvMaxDzimstot = 0.1,
            dommaxDzimstot = 0.1; //procentuâlâs izmaiòas dzimstot

    public static double healingRateDefault = 0.01,
            healthReductionRate = 0.03,
            paikaReductionDefault = 0.2; //viens - healing rate, otrs - konstanti samazinâs cilveki.get(i).paika

    public static int paikaMax = 100, paikaMin = 40;

    public static double esanasDaudzums = 1; //par vienu pilnu paikaMax

    public static double paikaNepiec = 4; //daudzums lîdz kuram mçìinâs savâkt paiku, tad skraidît apkârt

    //vairoðanâs parametri

    public static int sakumaCilveki=20; //sâkuma spçlçtâju skaits
    public static boolean randomKomandas=false; //vai spçles sâkumâ cilvçki bûs pa komandâm (false -> visi anarhijâ)

    public static double dzimstotDefectionChance=0.02;

    public static double brunasMax=5, brunasMin=brunasMax*0.7, stiprumsMax=20, stiprumsMin=stiprumsMax*0.7,
            dBrunasDzimstot=0.1,
            dStiprumsDzimstot=0.1;

    public static double cenaCilvekam=15,
            mantojumsCilvekamZelts=0,
            mantojumsCilvekamPaika=2;


    public static double dCenaProc=0.1;
    public static double paikaPriceDefault=1;

    public static double sellLimitPaika=paikaNepiec*1.5,
            buyLimitPaika=paikaNepiec*0.8,
            sellLimitDefault=20,
            buyLimitDefault=2;


}
