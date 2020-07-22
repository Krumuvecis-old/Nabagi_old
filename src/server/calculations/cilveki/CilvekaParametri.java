package server.calculations.cilveki;

import server.calculations.lietas.Lieta;

import java.util.ArrayList;

public abstract class CilvekaParametri {

    public Koord xyz; // (x,y,v,fi) - visi double + chunkXY - int[2]

    //primârie parametri
    public double vmax, omega;
    public double resnums;

    //sensorie parametri
    public double R1, R2; //redzesloks (R1-tâlais, R2-tuvais)

    public double hp, hpmax;
    public double paika, paikaMax, paikaMin;
    public boolean navKoEst;

    public Darbibas darbibas = new Darbibas();

    //inventory un tirdzniecîbas parametri
    public ArrayList<Lieta> inventory = new ArrayList<>();
    public ArrayList<Orderis> orderi = new ArrayList<>(); // (prece,perk(boolean),cena)
    //public ArrayList<Orderis> atminas = new ArrayList<Orderis>(); //atmiòas par tirdzniecîbas cenâm

    //cîòas parametri
    public double brunas, stiprums;
    public double gataviba;
    public double drosme;
    public String komanda;

    public boolean autoPilot=true; //true - kontrolç spçlçtâjs

}
