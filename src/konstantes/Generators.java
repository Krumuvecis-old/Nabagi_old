package konstantes;

class Generators {
	
	protected static void initialize() {
		Parametri.sakumaCilveki=15; //cilv�ku skaits pa�� s�kum�
		Parametri.randomKomandas=false; //vai sp�les s�kum� cilv�ki b�s pa komand�m (false -> visi anarhij�)
		
		double overallGenRate=0.5; //0.007 bija labi (0.015 televizoram)
		
		Parametri.goldGenRate=2*overallGenRate; //zelta �enerators
		Parametri.goldGenMin=1;
		Parametri.goldGenMax=7;
		
		Parametri.paikaGenRate=5*overallGenRate; //paikas �enerators
		Parametri.paikaGenMin=0.5;
		Parametri.paikaGenMax=2;
	}
	
}
