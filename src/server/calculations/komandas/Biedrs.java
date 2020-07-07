package server.calculations.komandas;

public class Biedrs {

    public int[] rangs; //0[x]-(zemnieki/amatnieki)&tirgoòi, 					1[x]-valdnieki&karotâji

    //0,0-nabags - lasa ìenerçtos objektus / strâdâ			1,0 -apsargs - sargâ fermas un citus objektus
    //0,1-zemnieks - var uzbûvçt fermu 						1,1 -karotâjs - staigâ ap pili
    //0,2-tirgotâjs	- iepçrk resursus un pârdod kur vajag	1,2 -gvarde - staigâ pilî/sargâ karali
    //0,3-raþotâjs - iepçrk resursus un no tiem raþo  citus	1,3 -dedicated karalis - dod visiem drosmi?

    public Biedrs(int[] _rangs){
        rangs = new int[]{_rangs[0], _rangs[1]};
    }

}
