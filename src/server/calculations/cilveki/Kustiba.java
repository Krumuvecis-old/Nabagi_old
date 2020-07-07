package server.calculations.cilveki;

import server.calculations.KonstantesUniversal;
import server.calculations.Main;

import java.util.ArrayList;
import java.util.List;

class Kustiba {
	
	protected static void main(String vards) {

		lenkuParbaude(vards);
		kustiba(vards);
		maluParbaude(vards);
	}
	
	private static void lenkuParbaude(String vards) {
		//leòíu noîsinâðana, lai bûtu optimâlâ diapazonâ no 0 lîdz 360
		while (Main.cilvekuList.get(vards).xyz.fi < 0) Main.cilvekuList.get(vards).xyz.fi += 360;
		while (Main.cilvekuList.get(vards).xyz.fi >= 360) Main.cilvekuList.get(vards).xyz.fi -= 360;
	}

	private static void kustiba(String vards) {

		//âtruma  projekcijas
		double vx = Main.cilvekuList.get(vards).xyz.v * Math.cos(Math.toRadians(Main.cilvekuList.get(vards).xyz.fi)),
				vy = Main.cilvekuList.get(vards).xyz.v * Math.sin(Math.toRadians(Main.cilvekuList.get(vards).xyz.fi));

		//kustîba pa asîm
		Main.cilvekuList.get(vards).xyz.x += vx;
		Main.cilvekuList.get(vards).xyz.y += vy;
	}

	private static void maluParbaude(String vards) { //situâcijas pie laukuma malâm
		Cilveks cilveks = Main.cilvekuList.get(vards);

		int platums = KonstantesUniversal.mapChunkW,
				chunkX = cilveks.xyz.chunkXY.get(0), //playera momentânais chunks
				chunkY = cilveks.xyz.chunkXY.get(1);

		List<Integer> chunk0 = new ArrayList<>();
		chunk0.add(chunkX);
		chunk0.add(chunkY);

		if (cilveks.xyz.x < 0 ) { //rietumi
			cilveks.xyz.x += platums;
			chunkX--;
			if (chunkX < 0) chunkX += KonstantesUniversal.mapChunkCountX;
		}
		if (cilveks.xyz.y < 0 ) { //ziemeïi
			cilveks.xyz.y += platums;
			chunkY--;
			if (chunkY < 0) chunkY += KonstantesUniversal.mapChunkCountY;
		}
		if (cilveks.xyz.x >= platums) { //austrumi
			cilveks.xyz.x -= platums;
			chunkX++;
			if (chunkX >= KonstantesUniversal.mapChunkCountX) chunkX -= KonstantesUniversal.mapChunkCountX;
		}
		if (cilveks.xyz.y >= platums) { //dienvidi
			cilveks.xyz.y -= platums;
			chunkY++;
			if (chunkY >= KonstantesUniversal.mapChunkCountY) chunkY -= KonstantesUniversal.mapChunkCountY;
		}

		if(chunkX != chunk0.get(0) || chunkY != chunk0.get(1)){

			Main.laukums.get(chunk0).cilvekiList.remove(vards); //jâizòem no vienas tabulas

			List<Integer> chunkXY = new ArrayList<>();
			chunkXY.add(chunkX);
			chunkXY.add(chunkY);
			Main.laukums.get(chunkXY).cilvekiList.add(vards); //jâieliek otrâ tabulâ
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
