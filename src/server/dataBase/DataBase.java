package server.dataBase;

import server.calculations.MapChunk;
import server.calculations.cilveki.Cilveks;
import server.calculations.komandas.Komanda;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {
    public static String versija;

    public static Map<String, Komanda> komandasList = new HashMap<>(); //komandu datubâze
    public static Map<String, Cilveks> cilvekuList = new HashMap<>(); //spçlçtâju datubâze

    public static Map<List<Integer>, MapChunk> laukums = new HashMap<>(); //laukums-karte

    public static int mapChunkCountX = 21, mapChunkCountY = 13;
    public static int mapCellCount = 7, mapCellW = 20;
    public static int mapChunkW = mapCellW * mapCellCount,
            laukumaPlatumsSum = mapChunkW * mapChunkCountX,
            laukumaAugstumsSum = mapChunkW * mapChunkCountY;

    public static Color laukumaKrasa = new Color(70,120,70), //zaïpelçks
            malasKrasa = Color.darkGray;

}
