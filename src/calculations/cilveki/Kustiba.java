package calculations.cilveki;

import calculations.KonstantesUniversal;
import calculations.Location;
import calculations.Main;

class Kustiba {
	
	protected static void main(Cilveks cilveks, Location location) {
		
		lenkuParbaude(cilveks);
		kustiba(cilveks);
		maluParbaude(location);
	}
	
	private static void lenkuParbaude(Cilveks cilveks) {
		//leòíu noîsinâðana, lai bûtu diapazonâ
		while (cilveks.xyz.fi<0) cilveks.xyz.fi+=360;
		while (cilveks.xyz.fi>=360) cilveks.xyz.fi-=360;
	}

	private static void kustiba(Cilveks cilveks) {

		//âtruma  projekcijas
		double vx = cilveks.xyz.v * Math.cos(Math.toRadians(cilveks.xyz.fi)),
				vy = cilveks.xyz.v * Math.sin(Math.toRadians(cilveks.xyz.fi));

		//kustîba pa asîm
		cilveks.xyz.x+=vx;
		cilveks.xyz.y+=vy;
	}

	private static void maluParbaude(Location location) { //situâcijas pie laukuma malâm
		Cilveks cilveks = Cilveks.getPlayer(location);

		int platums = KonstantesUniversal.mapChunkW,
				chunkX = location.chunkXY[0], //playera momentânais chunks
				chunkY = location.chunkXY[1];

		if (cilveks.xyz.x < 0 ) { //rietumi
			cilveks.xyz.x+=platums;
			chunkX--;
			if (chunkX < 0) chunkX += KonstantesUniversal.mapChunkCountX;
		}
		if (cilveks.xyz.y < 0 ) { //ziemeïi
			cilveks.xyz.y+=platums;
			chunkY--;
			if (chunkY < 0) chunkY += KonstantesUniversal.mapChunkCountY;
		}
		if (cilveks.xyz.x >= platums) { //austrumi
			cilveks.xyz.x-=platums;
			chunkX++;
			if (chunkX >= KonstantesUniversal.mapChunkCountX) chunkX -= KonstantesUniversal.mapChunkCountX;
		}
		if (cilveks.xyz.y >= platums) { //dienvidi
			cilveks.xyz.y-=platums;
			chunkY++;
			if (chunkY >= KonstantesUniversal.mapChunkCountY) chunkY -= KonstantesUniversal.mapChunkCountY;
		}

		int chunkX0=location.chunkXY[0], chunkY0=location.chunkXY[1];
		if(chunkX!=chunkX0 || chunkY!=chunkY0){ //jâizòem no vienas tabulas un jâieliek otrâ

			Main.laukums.get(chunkX).get(chunkY).cilvekiList.add(cilveks);
			Main.laukums.get(chunkX0).get(chunkY0).cilvekiList.remove(location.i);
			updateCilvekuList(new int[]{chunkX0,chunkY0}, location.i); //temporary salabojums
		}

	}

	private static void updateCilvekuList(int[] chunkXY, int iznemtais){
		for(int i=0; i<Cilveks.cilvekuListPilnais.size(); i++){
			Location location = Cilveks.cilvekuListPilnais.get(i);
			if (location.chunkXY==chunkXY && location.i >= iznemtais){
				Cilveks.cilvekuListPilnais.get(i).i--;
			}
		}
	}

	private static void maluParbaudeVecais(){

		// zemâk atdurðanâs pret malâm - ðo vçlâk varçs izmantot, kad pievienos çkas un sienas

//		int augstums= KonstantesUniversal.laukumaAugstumsSum, platums=KonstantesUniversal.laukumaPlatumsSum, mala=KonstantesUniversal.mala;
//		double resnums= Fizikas.resnumaKoefic*cilveks.hpmax;
//
//		if(cilveks.xyz.x<=mala+resnums/2) { //kreisâ mala
//			cilveks.xyz.x=mala+resnums/2;
//
//			if(cilveks.xyz.fi>=180&&cilveks.xyz.fi<270) {
//				cilveks.xyz.fi=540-cilveks.xyz.fi;
//			}
//			if(cilveks.xyz.fi>90&&cilveks.xyz.fi<180) {
//				cilveks.xyz.fi=180-cilveks.xyz.fi;
//			}
//
//		}
//		if(cilveks.xyz.x>=platums-mala-resnums/2) { //labâ mala
//			cilveks.xyz.x=platums-mala-resnums/2;
//
//			if(cilveks.xyz.fi<90&&cilveks.xyz.fi>=0) {
//				cilveks.xyz.fi=180-cilveks.xyz.fi;
//			}
//			if(cilveks.xyz.fi>270&&cilveks.xyz.fi<360) {
//				cilveks.xyz.fi=180+360-cilveks.xyz.fi;
//			}
//		}
//		if(cilveks.xyz.y<=mala+resnums/2) { //augðçjâ mala
//			cilveks.xyz.y=mala+resnums/2;
//
//			if(cilveks.xyz.fi>=270&&cilveks.xyz.fi<360) {
//				cilveks.xyz.fi=360-cilveks.xyz.fi;
//			}
//			if(cilveks.xyz.fi>180&&cilveks.xyz.fi<270) {
//				cilveks.xyz.fi=360-cilveks.xyz.fi;
//			}
//
//		}
//		if(cilveks.xyz.y>=augstums-mala-resnums/2) { //apakðçjâ mala
//			cilveks.xyz.y=augstums-mala-resnums/2;
//
//			if(cilveks.xyz.fi>0&&cilveks.xyz.fi<=90) {
//				cilveks.xyz.fi=360-cilveks.xyz.fi;
//			}
//			if(cilveks.xyz.fi>90&&cilveks.xyz.fi<180) {
//				cilveks.xyz.fi=360-cilveks.xyz.fi;
//			}
//		}
	}

	
}
