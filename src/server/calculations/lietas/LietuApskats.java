package server.calculations.lietas;

import server.calculations.laukums.MapChunk;

public class LietuApskats {

	private static final double degradeCoefficient = 0.0005;
	
	public static void main(MapChunk chunk){

		//degradç pa zemi izmçtâtâs lietas un izdzçð jau degradçtâs

		for(int i = 0; i < chunk.lietas.size(); i++){
			Lieta lieta = chunk.lietas.get(i);
			lieta.daudzums -= LietuTips.lietuTipi.get(lieta.tips).degradeCoefficient;

			if(lieta.daudzums <= 0){
				chunk.lietas.remove(i);
				i--;
			}
		}
	}
	
}
