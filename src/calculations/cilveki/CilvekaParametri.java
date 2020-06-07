package calculations.cilveki;

import calculations.lietas.Lieta;

import java.util.ArrayList;

public class CilvekaParametri {

    public String vards;
    public Koord xyz = new Koord(); // (x,y,v,fi) - visi double

    //primârie parametri
    public double vmax, omega;
    public double hp, hpmax;

    public double paika, paikaMax, paikaMin;
    public boolean navKoEst;

    public Darbibas darbibas = new Darbibas();

    //sensorie parametri
    public double R1, R2; //redzesloks

    //cîòas parametri
    public ArrayList<Lieta> inventory = new ArrayList<Lieta>();
    public ArrayList<Orderis> orderi = new ArrayList<Orderis>(); // (prece,perk(boolean),cena)
    //public ArrayList<Orderis> atminas = new ArrayList<Orderis>(); //atmiòas par tirdzniecîbas cenâm

    public double brunas, stiprums;
    public double gataviba;
    public double drosme;
    public String komanda;
    public int[] rangs; //0[x]-(zemnieki/amatnieki)&tirgoòi, 					1[x]-valdnieki&karotâji

    //0,0-nabags - lasa ìenerçtos objektus / strâdâ			1,0 -apsargs - sargâ fermas un citus objektus
    //0,1-zemnieks - var uzbûvçt fermu 						1,1 -karotâjs - staigâ ap pili
    //0,2-tirgotâjs	- iepçrk resursus un pârdod kur vajag	1,2 -gvarde - staigâ pilî/sargâ karali
    //0,3-raþotâjs - iepçrk resursus un no tiem raþo  citus	1,3 -dedicated karalis - dod visiem drosmi?

    public boolean autoPilot=true; //true - kontrolç spçlçtâjs

}
