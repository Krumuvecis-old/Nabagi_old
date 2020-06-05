package calculations.lietas;

import java.util.Random;

import calculations.KonstantesUniversal;
import calculations.Main;
import calculations.MapChunk;
import calculations.konstantes.Fizikas;
import calculations.konstantes.Lietu;

public class LietuApskats {
	
	public static void main() {

		lootGeneratorAll();

		//te arî vajadzçtu uztaisît lietu apskates ciklu, piemçram lai izdzçstu degradçtâs lietas
		
	}

	private static void lootGeneratorAll(){
		Random r=new Random();

		for (int[] chunkXY={0,0}; chunkXY[0]<Main.laukums.size(); chunkXY[0]++){
			for( ; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++){

				if (r.nextDouble() < Lietu.goldGenRate){ //random zelta ìenerators
					lootGeneratorOnce(chunkXY, "Zelts", Lietu.goldGenMin, Lietu.goldGenMax);
				}
				if (r.nextDouble() < Lietu.paikaGenRate){ //random paikas ìenerators
					lootGeneratorOnce(chunkXY, "Paika", Lietu.paikaGenMin, Lietu.paikaGenMax);
				}

				//te var pievienot papildus citu lietu ìenerçðanu


			}
		}

	}
	
	private static void lootGeneratorOnce(int[] chunkXY, String nosaukums, double minimums, double maksimums) {
		Random r=new Random();

		int chunkPlatums = KonstantesUniversal.mapChunkW;
		Lieta lieta = new Lieta();

		double resnums;

		if(nosaukums=="Zelts") {
			resnums=Fizikas.zeltaResnums;

			lieta.zelts=1;
			lieta.paika=0;
			lieta.masa=1;
			lieta.attack=0;
			lieta.defence=0;
			lieta.condition=1;
			
		} else if (nosaukums=="Paika") {
			resnums=Fizikas.paikasResnums;

			lieta.zelts=0;
			lieta.paika=1;
			lieta.masa=1;
			lieta.attack=0;
			lieta.defence=0;
			lieta.condition=1;
			
		} else { //pavisam neklasificçtas lietas
			resnums= Fizikas.lietasResnums;

			lieta.zelts=0;
			lieta.paika=0;
			lieta.masa=1;
			lieta.attack=0;
			lieta.defence=0;
			lieta.condition=1;
			
		}

		lieta.x = resnums/2 + (chunkPlatums-resnums)*r.nextDouble();
		lieta.y =  resnums/2 + (chunkPlatums-resnums)*r.nextDouble();
		lieta.nosaukums = nosaukums;
		lieta.daudzums = minimums + (maksimums-minimums)*r.nextDouble();

		int i = Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.size();
		Main.laukums.get(chunkXY[0]).get(chunkXY[1]).lietas.add(lieta);

	}
	
	
}
