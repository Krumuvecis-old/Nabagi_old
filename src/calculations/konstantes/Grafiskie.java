package calculations.konstantes;

import calculations.KonstantesUniversal;

import java.awt.Color;

public class Grafiskie {

	// --------------------
	//zem�k defin�t�s kr�sas komandu kr�su noteik�anai

	public static Color laukumaKrasa = new Color(70,120,70), //za�pel�ks
			malasKrasa = Color.darkGray;

	public static Color komandasColorDefault = new Color(255,0,0);
	public static boolean komandasBannedColors = true;
	public static double[] komandasBannedColorList = new double[] {
			0,0,0,			//dom�tas tuk�as ailes, kur ielikt random kr�sas vari�cijai
			Formulas.getHue(komandasColorDefault),
			Formulas.getHue(laukumaKrasa),
			Formulas.getHue(KonstantesUniversal.defaultLietas.get(0).krasa),
			Formulas.getHue(KonstantesUniversal.defaultLietas.get(1).krasa),
			Formulas.getHue(KonstantesUniversal.defaultLietas.get(2).krasa),

			300		/360.0,	//roz�, kas uz za�� fona izskat�s vienk�r�i briesm�gi
			180		/360.0,	//balans��anas kr�sa
			240		/360.0	//balans��anas kr�sa
	};

	public static void initialize() {


		
		
		
	}
	
}
