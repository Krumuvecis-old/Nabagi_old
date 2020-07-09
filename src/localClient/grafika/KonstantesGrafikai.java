package localClient.grafika;

import server.dataBase.DataBase;

import java.awt.Color;

public class KonstantesGrafikai {

    public static String ekranaNosaukums="Nabagi - versija: "+ DataBase.versija;

    public static double cilvekiKrasaSaturation=1;
    public static double cilvekiKrasaBrightnessMin=0.4; //pie hpRatio=0
    public static double cilvekiKrasaBrightnessMax=1; //pie hpRatio=1

    public static Color kronaKrasa = new Color(0,0,0); //kroòa krâsa - melns  punkts
    public static double kronaKoeficients=0.5; //kroòa resnums pret kopçjo resnumu



}
