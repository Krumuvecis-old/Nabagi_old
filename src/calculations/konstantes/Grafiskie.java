package calculations.konstantes;

import java.awt.Color;

class Grafiskie {
	
	protected static void initialize() {
		Parametri.ekranaNosaukums="Nabagi - versija: "+Parametri.versija;
		
		Parametri.cilvekiKrasaSaturation=1;
		Parametri.cilvekiKrasaBrightnessMin=0.4; //pie hpRatio=0
		Parametri.cilvekiKrasaBrightnessMax=1; //pie hpRatio=1

		Parametri.kronaKrasa = new Color(0,0,0); //kro�a kr�sa - melns  punkts
		Parametri.kronaKoeficients=0.5; //kro�a resnums pret kop�jo resnumu
		
		
		// --------------------
		//zem�k defin�t�s kr�sas komandu kr�su noteik�anai
		
		Parametri.laukumaKrasa = new Color(70,120,70); //za�pel�ks
		Parametri.malasKrasa = Color.darkGray;
		
		Parametri.lietasColorZelts = new Color(200,200,0); //dzeltens
		Parametri.lietasColorPaika = new Color(255,100,50); //br�ni oran��gs
		Parametri.lietasColorDefault = new Color(150,50,100); //violets vai purpurs
		
		Parametri.komandasColorDefault = new Color(255,0,0);
		Parametri.komandasBannedColors = true;
		Parametri.komandasBannedColorList = new double[] {
				0,0,0,			//dom�tas tuk�as ailes, kur ielikt random kr�sas vari�cijai
				Formulas.getHue(Parametri.komandasColorDefault),
				Formulas.getHue(Parametri.laukumaKrasa),
				Formulas.getHue(Parametri.lietasColorZelts),
				Formulas.getHue(Parametri.lietasColorPaika),
				Formulas.getHue(Parametri.lietasColorDefault),
				
				300		/360.0,	//roz�, kas uz za�� fona izskat�s vienk�r�i briesm�gi
				180		/360.0,	//balans��anas kr�sa
				240		/360.0	//balans��anas kr�sa
		};
		
		
		
	}
	
}
