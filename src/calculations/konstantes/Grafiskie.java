package calculations.konstantes;

import java.awt.Color;

public class Grafiskie {

	// --------------------
	//zem�k defin�t�s kr�sas komandu kr�su noteik�anai

	public static Color laukumaKrasa = new Color(70,120,70), //za�pel�ks
			malasKrasa = Color.darkGray,
			lietasColorZelts = new Color(200,200,0), //dzeltens
			lietasColorPaika = new Color(255,100,50), //br�ni oran��gs
			lietasColorDefault = new Color(150,50,100); //violets vai purpurs

	public static Color komandasColorDefault = new Color(255,0,0);
	public static boolean komandasBannedColors = true;
	public static double[] komandasBannedColorList = new double[] {
			0,0,0,			//dom�tas tuk�as ailes, kur ielikt random kr�sas vari�cijai
			Formulas.getHue(komandasColorDefault),
			Formulas.getHue(laukumaKrasa),
			Formulas.getHue(lietasColorZelts),
			Formulas.getHue(lietasColorPaika),
			Formulas.getHue(lietasColorDefault),

			300		/360.0,	//roz�, kas uz za�� fona izskat�s vienk�r�i briesm�gi
			180		/360.0,	//balans��anas kr�sa
			240		/360.0	//balans��anas kr�sa
	};

	public static void initialize() {


		
		
		
	}
	
}
