package server.calculations.komandas;

public class Biedrs {

    public int[] rangs; //0[x]-(zemnieki/amatnieki)&tirgo�i, 					1[x]-valdnieki&karot�ji

    //0,0-nabags - lasa �ener�tos objektus / str�d�			1,0 -apsargs - sarg� fermas un citus objektus
    //0,1-zemnieks - var uzb�v�t fermu 						1,1 -karot�js - staig� ap pili
    //0,2-tirgot�js	- iep�rk resursus un p�rdod kur vajag	1,2 -gvarde - staig� pil�/sarg� karali
    //0,3-ra�ot�js - iep�rk resursus un no tiem ra�o  citus	1,3 -dedicated karalis - dod visiem drosmi?

    public Biedrs(int[] _rangs){
        rangs = new int[]{_rangs[0], _rangs[1]};
    }

}
