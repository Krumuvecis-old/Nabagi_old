package calculations.konstantes;

class Generators {
	
	protected static void initialize() {
		Parametri.sakumaCilveki=15; //cilv�ku skaits pa�� s�kum�
		Parametri.randomKomandas=false; //vai sp�les s�kum� cilv�ki b�s pa komand�m (false -> visi anarhij�)
		
		double overallGenRate=0.2; //0.007 bija labi (0.015 televizoram)
		
		Parametri.goldGenRate=overallGenRate; //zelta �enerators
		Parametri.goldGenMin=1;
		Parametri.goldGenMax=7;
		
		Parametri.paikaGenRate=2*overallGenRate; //paikas �enerators
		Parametri.paikaGenMin=1;
		Parametri.paikaGenMax=3;
	}
	
}
