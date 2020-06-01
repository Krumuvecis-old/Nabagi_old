package konstantes;

import java.awt.Color;

class Grafiskie {
	
	protected static void initialize() {
		Parametri.ekranaNosaukums="Nabagi - versija: "+Parametri.versija;
		
		Parametri.FPScalculationFrequency=20;
		
		Parametri.cilvekiKrasaSaturation=1;
		Parametri.cilvekiKrasaBrightnessMin=0.4; //pie hpRatio=0
		Parametri.cilvekiKrasaBrightnessMax=1; //pie hpRatio=1
		
		// --------------------
		//zemâk definçtâs krâsas komandu krâsu noteikðanai
		
		Parametri.laukumaKrasa = new Color(70,120,70); //zaïpelçks
		Parametri.malasKrasa = Color.darkGray;
		
		Parametri.lietasColorZelts = new Color(200,200,0); //dzeltens
		Parametri.lietasColorPaika = new Color(255,100,50); //brûni oranþîgs
		Parametri.lietasColorDefault = new Color(150,50,100); //violets vai purpurs
		
		Parametri.komandasColorDefault = new Color(255,0,0);
		Parametri.komandasBannedColors = true;
		Parametri.komandasBannedColorList = new double[] {
				0,0,0,			//domâtas tukðas ailes, kur ielikt random krâsas variâcijai
				Formulas.getHue(Parametri.komandasColorDefault),
				Formulas.getHue(Parametri.laukumaKrasa),
				Formulas.getHue(Parametri.lietasColorZelts),
				Formulas.getHue(Parametri.lietasColorPaika),
				Formulas.getHue(Parametri.lietasColorDefault),
				
				300		/360.0,	//rozâ, kas uz zaïâ fona izskatâs vienkârði briesmîgi
				180		/360.0,	//balansçðanas krâsa
				240		/360.0	//balansçðanas krâsa
		};
		
		
	}
	
}
