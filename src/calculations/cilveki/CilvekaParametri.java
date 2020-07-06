package calculations.cilveki;

import calculations.lietas.Lieta;

import java.util.ArrayList;

public class CilvekaParametri {

    public Koord xyz; // (x,y,v,fi) - visi double + chunkXY - int[2]

    //prim�rie parametri
    public double vmax, omega;
    public double hp, hpmax;

    public double paika, paikaMax, paikaMin;
    public boolean navKoEst;

    public Darbibas darbibas = new Darbibas();

    //sensorie parametri
    public double R1, R2; //redzesloks (R1-t�lais, R2-tuvais)

    //c��as parametri
    public ArrayList<Lieta> inventory = new ArrayList<Lieta>();
    public ArrayList<Orderis> orderi = new ArrayList<Orderis>(); // (prece,perk(boolean),cena)
    //public ArrayList<Orderis> atminas = new ArrayList<Orderis>(); //atmi�as par tirdzniec�bas cen�m

    public double brunas, stiprums;
    public double gataviba;
    public double drosme;
    public String komanda;

    public boolean autoPilot=true; //true - kontrol� sp�l�t�js

}
