package calculations.cilveki;

import calculations.lietas.Lieta;

import java.util.ArrayList;

public class CilvekaParametri {

    public String vards;
    public Koord xyz = new Koord(); // (x,y,v,fi) - visi double

    //prim�rie parametri
    public double vmax, omega;
    public double hp, hpmax;

    public double paika, paikaMax, paikaMin;
    public boolean navKoEst;

    public Darbibas darbibas = new Darbibas();

    //sensorie parametri
    public double R1, R2; //redzesloks

    //c��as parametri
    public ArrayList<Lieta> inventory = new ArrayList<Lieta>();
    public ArrayList<Orderis> orderi = new ArrayList<Orderis>(); // (prece,perk(boolean),cena)
    //public ArrayList<Orderis> atminas = new ArrayList<Orderis>(); //atmi�as par tirdzniec�bas cen�m

    public double brunas, stiprums;
    public double gataviba;
    public double drosme;
    public String komanda;
    public int[] rangs; //0[x]-(zemnieki/amatnieki)&tirgo�i, 					1[x]-valdnieki&karot�ji

    //0,0-nabags - lasa �ener�tos objektus / str�d�			1,0 -apsargs - sarg� fermas un citus objektus
    //0,1-zemnieks - var uzb�v�t fermu 						1,1 -karot�js - staig� ap pili
    //0,2-tirgot�js	- iep�rk resursus un p�rdod kur vajag	1,2 -gvarde - staig� pil�/sarg� karali
    //0,3-ra�ot�js - iep�rk resursus un no tiem ra�o  citus	1,3 -dedicated karalis - dod visiem drosmi?

    public boolean autoPilot=true; //true - kontrol� sp�l�t�js

}
