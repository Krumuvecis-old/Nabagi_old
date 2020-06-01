package galvenais;

import java.util.Random;

import dataBase.Dati;
import dataBase.Lieta;
import konstantes.Parametri;

class Lietas {
	
	protected static void main() {
		Random r=new Random();
		
		if (r.nextDouble()<Parametri.goldGenRate) lootGenerator("Zelts", Parametri.goldGenMin, Parametri.goldGenMax); //random zelta ìenerators
		if (r.nextDouble()<Parametri.paikaGenRate) lootGenerator("Paika", Parametri.paikaGenMin, Parametri.paikaGenMax); //random paikas ìenerators
		
		//te arî vajadzçtu uztaisît lietu apskates ciklu, piemçram lai izdzçstu degradçtâs lietas
		
	}
	
	private static void lootGenerator(String nosaukums, double minimums, double maksimums) {
		Random r=new Random();
		
		int mala = Parametri.mala; //laukuma izmçriem
		int platums = Parametri.platums;
		int augstums = Parametri.augstums;
		
		int i=Dati.lietas.size();
		Dati.lietas.add(new Lieta());
		
		int resnums;
		
		double zelts;
		double paika;
		double masa;
		double attack;
		double defence;
		double condition;
		
		
		if(nosaukums=="Zelts") {
			resnums=Parametri.zeltaResnums;
			
			zelts=1;
			paika=0;
			masa=1;
			attack=0;
			defence=0;
			condition=1;
			
		} else if (nosaukums=="Paika") {
			resnums=Parametri.paikasResnums;
			
			zelts=0;
			paika=1;
			masa=1;
			attack=0;
			defence=0;
			condition=1;
			
		} else { //pavisam neklasificçtas lietas
			resnums=Parametri.lietasResnums;
			
			zelts=0;
			paika=0;
			masa=1;
			attack=0;
			defence=0;
			condition=1;
			
		}
		
		Dati.lietas.get(i).x=mala+resnums/2+(platums-2*(resnums/2+mala))*r.nextDouble();
		Dati.lietas.get(i).y=mala+resnums/2+(augstums-2*(resnums/2+mala))*r.nextDouble();
		Dati.lietas.get(i).nosaukums=nosaukums;
		Dati.lietas.get(i).daudzums=minimums+(maksimums-minimums)*r.nextDouble();
		
		Dati.lietas.get(i).zelts=zelts;
		Dati.lietas.get(i).paika=paika;
		Dati.lietas.get(i).masa=masa;
		Dati.lietas.get(i).attack=attack;
		Dati.lietas.get(i).defence=defence;
		Dati.lietas.get(i).condition=condition;
		
	}
	
	
}
