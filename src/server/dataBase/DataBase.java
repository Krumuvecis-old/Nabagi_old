package server.dataBase;

import server.calculations.laukums.Laukums;
import server.calculations.laukums.MapChunk;
import server.calculations.cilveki.Cilveks;
import server.calculations.komandas.Komanda;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {
    public static String versija;

    public static Map<String, Komanda> komandasList = new HashMap<>(); //komandu datub�ze
    public static Map<String, Cilveks> cilvekuList = new HashMap<>(); //sp�l�t�ju datub�ze

    public static Laukums laukums; //laukums-karte

}
