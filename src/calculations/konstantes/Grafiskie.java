package calculations.konstantes;

import java.awt.Color;

public class Grafiskie {

	public static Color laukumaKrasa = new Color(70,120,70), //zaïpelçks
			malasKrasa = Color.darkGray;

	public static Color komandasColorDefault = new Color(255,0,0);
	public static boolean komandasBannedColors = true;
	public static double[] komandasBannedColorList = new double[] {
			0,0,0,			//domâtas 3 tukðas ailes, kur ielikt krâsu randomizatorus variâcijai
			Formulas.getHue(komandasColorDefault),
			Formulas.getHue(laukumaKrasa),

			300		/360.0,	//rozâ, kas uz zaïâ fona izskatâs vienkârði briesmîgi
			180		/360.0,	//balansçðanas krâsa
			240		/360.0	//balansçðanas krâsa
	};
	
}
