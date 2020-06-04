package calculations.konstantes;

class Generators {
	
	protected static void initialize() {
		Parametri.sakumaCilveki=15; //cilvçku skaits paðâ sâkumâ
		Parametri.randomKomandas=false; //vai spçles sâkumâ cilvçki bûs pa komandâm (false -> visi anarhijâ)
		
		double overallGenRate=0.2; //0.007 bija labi (0.015 televizoram)
		
		Parametri.goldGenRate=overallGenRate; //zelta ìenerators
		Parametri.goldGenMin=1;
		Parametri.goldGenMax=7;
		
		Parametri.paikaGenRate=2*overallGenRate; //paikas ìenerators
		Parametri.paikaGenMin=1;
		Parametri.paikaGenMax=3;
	}
	
}
