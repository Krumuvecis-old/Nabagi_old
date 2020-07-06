package calculations.konstantes;

import java.awt.Color;

public class Grafiskie {

	public static Color laukumaKrasa = new Color(70,120,70), //za�pel�ks
			malasKrasa = Color.darkGray;

	public static Color komandasColorDefault = new Color(255,0,0);
	public static boolean komandasBannedColors = true;
	public static double[] komandasBannedColorList = new double[] {
			0,0,0,			//dom�tas 3 tuk�as ailes, kur ielikt kr�su randomizatorus vari�cijai
			Formulas.getHue(komandasColorDefault),
			Formulas.getHue(laukumaKrasa),

			300		/360.0,	//roz�, kas uz za�� fona izskat�s vienk�r�i briesm�gi
			180		/360.0,	//balans��anas kr�sa
			240		/360.0	//balans��anas kr�sa
	};
	
}
