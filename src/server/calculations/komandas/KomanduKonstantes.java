package server.calculations.komandas;

import server.calculations.Formulas;
import server.calculations.laukums.Laukums;

import java.awt.*;

public class KomanduKonstantes {

    public static String komandaNosaukumsDefault = "K",
            komandaNosaukumsFirst = "Anarhija";
    public static double komandaIzjuktChance = 0.3;

    public static Color komandasColorDefault = new Color(255,0,0);
    public static boolean komandasBannedColors = true;
    public static double[] komandasBannedColorList = new double[] {
            0,0,0,			//domâtas 3 tukðas ailes, kur ielikt krâsu randomizatorus variâcijai
            Formulas.getHue(KomanduKonstantes.komandasColorDefault),
            Formulas.getHue(Laukums.laukumaKrasa),

            300		/360.0,	//rozâ, kas uz zaïâ fona izskatâs vienkârði briesmîgi
            180		/360.0,	//balansçðanas krâsa
            240		/360.0	//balansçðanas krâsa
    };
}
