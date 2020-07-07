package server.calculations.konstantes;

import java.awt.*;

public class Komandu {

    public static String komandaNosaukumsDefault = "K",
            komandaNosaukumsFirst = "Anarhija";
    public static double komandaIzjuktChance = 0.3;

    public static Color komandasColorDefault = new Color(255,0,0);
    public static boolean komandasBannedColors = true;
    public static double[] komandasBannedColorList = new double[] {
            0,0,0,			//dom�tas 3 tuk�as ailes, kur ielikt kr�su randomizatorus vari�cijai
            Formulas.getHue(Komandu.komandasColorDefault),
            Formulas.getHue(Grafiskie.laukumaKrasa),

            300		/360.0,	//roz�, kas uz za�� fona izskat�s vienk�r�i briesm�gi
            180		/360.0,	//balans��anas kr�sa
            240		/360.0	//balans��anas kr�sa
    };
}
