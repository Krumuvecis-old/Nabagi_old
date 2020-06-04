package calculations.konstantes;

import java.awt.Color;

public class Grafiskie {

	// --------------------
	//zemâk definçtâs krâsas komandu krâsu noteikðanai

	public static Color laukumaKrasa = new Color(70,120,70), //zaïpelçks
			malasKrasa = Color.darkGray,
			lietasColorZelts = new Color(200,200,0), //dzeltens
			lietasColorPaika = new Color(255,100,50), //brûni oranþîgs
			lietasColorDefault = new Color(150,50,100); //violets vai purpurs

	public static Color komandasColorDefault = new Color(255,0,0);
	public static boolean komandasBannedColors = true;
	public static double[] komandasBannedColorList = new double[] {
			0,0,0,			//domâtas tukðas ailes, kur ielikt random krâsas variâcijai
			Formulas.getHue(komandasColorDefault),
			Formulas.getHue(laukumaKrasa),
			Formulas.getHue(lietasColorZelts),
			Formulas.getHue(lietasColorPaika),
			Formulas.getHue(lietasColorDefault),

			300		/360.0,	//rozâ, kas uz zaïâ fona izskatâs vienkârði briesmîgi
			180		/360.0,	//balansçðanas krâsa
			240		/360.0	//balansçðanas krâsa
	};

	public static void initialize() {


		
		
		
	}
	
}
