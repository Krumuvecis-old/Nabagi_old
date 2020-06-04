package konstantes;

class Generators {
	
	protected static void initialize() {
		Parametri.sakumaCilveki=15; //cilvçku skaits paðâ sâkumâ
		Parametri.randomKomandas=false; //vai spçles sâkumâ cilvçki bûs pa komandâm (false -> visi anarhijâ)
		
		double overallGenRate=0.5; //0.007 bija labi (0.015 televizoram)
		
		Parametri.goldGenRate=2*overallGenRate; //zelta ìenerators
		Parametri.goldGenMin=1;
		Parametri.goldGenMax=7;
		
		Parametri.paikaGenRate=5*overallGenRate; //paikas ìenerators
		Parametri.paikaGenMin=0.5;
		Parametri.paikaGenMax=2;
	}
	
}
