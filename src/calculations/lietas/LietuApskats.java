package calculations.lietas;

import java.util.Random;

import calculations.KonstantesUniversal;
import calculations.Main;
import calculations.konstantes.Fizikas;
import calculations.konstantes.Lietu;

public class LietuApskats {
	
	public static void main() {
		Random r=new Random();
		
		if (r.nextDouble() < Lietu.goldGenRate) lootGenerator("Zelts", Lietu.goldGenMin, Lietu.goldGenMax); //random zelta ìenerators
		if (r.nextDouble() < Lietu.paikaGenRate) lootGenerator("Paika", Lietu.paikaGenMin, Lietu.paikaGenMax); //random paikas ìenerators
		
		//te arî vajadzçtu uztaisît lietu apskates ciklu, piemçram lai izdzçstu degradçtâs lietas
		
	}
	
	private static void lootGenerator(String nosaukums, double minimums, double maksimums) {
		Random r=new Random();
		
		int mala = KonstantesUniversal.mala; //laukuma izmçriem
		int platums = KonstantesUniversal.platums;
		int augstums = KonstantesUniversal.augstums;
		
		int i=Main.lietas.size();
		Main.lietas.add(new Lieta());
		
		double resnums;
		
		double zelts;
		double paika;
		double masa;
		double attack;
		double defence;
		double condition;
		
		
		if(nosaukums=="Zelts") {
			resnums=Fizikas.zeltaResnums;
			
			zelts=1;
			paika=0;
			masa=1;
			attack=0;
			defence=0;
			condition=1;
			
		} else if (nosaukums=="Paika") {
			resnums=Fizikas.paikasResnums;
			
			zelts=0;
			paika=1;
			masa=1;
			attack=0;
			defence=0;
			condition=1;
			
		} else { //pavisam neklasificçtas lietas
			resnums= Fizikas.lietasResnums;
			
			zelts=0;
			paika=0;
			masa=1;
			attack=0;
			defence=0;
			condition=1;
			
		}
		
		Main.lietas.get(i).x=mala+resnums/2+(platums-2*(resnums/2+mala))*r.nextDouble();
		Main.lietas.get(i).y=mala+resnums/2+(augstums-2*(resnums/2+mala))*r.nextDouble();
		Main.lietas.get(i).nosaukums=nosaukums;
		Main.lietas.get(i).daudzums=minimums+(maksimums-minimums)*r.nextDouble();
		
		Main.lietas.get(i).zelts=zelts;
		Main.lietas.get(i).paika=paika;
		Main.lietas.get(i).masa=masa;
		Main.lietas.get(i).attack=attack;
		Main.lietas.get(i).defence=defence;
		Main.lietas.get(i).condition=condition;
		
	}
	
	
}
