package calculations.konstantes;

class Cilvekiem {
	
	protected static void initialize() {
		Parametri.vardsDefault="S";
		Parametri.maxGataviba=100;
		Parametri.hpmax=20;
		
		Parametri.vmax=3;
		Parametri.ommax=30;
		
		Parametri.RMax=400; //maksimâlais redzesloks
		Parametri.R1koefic=4;  //minimâlâ redzesloka daïas koefic
		
		Parametri.dRDzimstot=0.1; //redzesloka procentuâla izmaiòa vairojoties
		Parametri.dvMaxDzimstot=0.1;
		Parametri.dommaxDzimstot=0.1; //procentuâlâs izmaiòas dzimstot
		
		Parametri.healingRateDefault=0.01;
		Parametri.healthReductionRate=0.03;
		Parametri.paikaReductionDefault=0.2; //viens - healing rate, otrs - konstanti samazinâs cilveki.get(i).paika
		Parametri.paikaMax=100;
		Parametri.paikaMin=40;
		Parametri.esanasDaudzums=1; //par vienu pilnu paikaMax
		
		Parametri.paikaNepiec = 4; //daudzums lîdz kuram mçìinâs savâkt paiku, tad skraidît apkârt
		
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
