package calculations.konstantes;

class Cilvekiem {
	
	protected static void initialize() {
		Parametri.vardsDefault="S";
		Parametri.maxGataviba=100;
		Parametri.hpmax=20;
		
		Parametri.vmax=3;
		Parametri.ommax=30;
		
		Parametri.RMax=400; //maksim�lais redzesloks
		Parametri.R1koefic=4;  //minim�l� redzesloka da�as koefic
		
		Parametri.dRDzimstot=0.1; //redzesloka procentu�la izmai�a vairojoties
		Parametri.dvMaxDzimstot=0.1;
		Parametri.dommaxDzimstot=0.1; //procentu�l�s izmai�as dzimstot
		
		Parametri.healingRateDefault=0.01;
		Parametri.healthReductionRate=0.03;
		Parametri.paikaReductionDefault=0.2; //viens - healing rate, otrs - konstanti samazin�s cilveki.get(i).paika
		Parametri.paikaMax=100;
		Parametri.paikaMin=40;
		Parametri.esanasDaudzums=1; //par vienu pilnu paikaMax
		
		Parametri.paikaNepiec = 4; //daudzums l�dz kuram m��in�s sav�kt paiku, tad skraid�t apk�rt
		
		Parametri.brunasMax=5;
		Parametri.brunasMin=Parametri.brunasMax*0.7;
		Parametri.dBrunasDzimstot=0.1;
		Parametri.stiprumsMax=20;
		Parametri.stiprumsMin=Parametri.stiprumsMax*0.7;
		Parametri.dStiprumsDzimstot=0.1;
		Parametri.cenaCilvekam=15;
		Parametri.mantojumsCilvekam=0;
		Parametri.mantojumsCilvekamPaika=2;
		
		Parametri.dzimstotDefectionChance=0.02;
		
		
		Parametri.dCenaProc=0.1;
		Parametri.paikaPriceDefault=1;
		
		Parametri.sellLimitPaika=Parametri.paikaNepiec*1.5;
		Parametri.buyLimitPaika=Parametri.paikaNepiec*0.8;
		Parametri.sellLimitDefault=20;
		Parametri.buyLimitDefault=2;
		
		
		
	}
}
