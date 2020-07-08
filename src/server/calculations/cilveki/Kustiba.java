package server.calculations.cilveki;

import server.dataBase.DataBase;

import java.util.ArrayList;
import java.util.List;

class Kustiba {
	
	protected static void main(String vards) {

		lenkuParbaude(vards);
		kustiba(vards);
		maluParbaude(vards);
	}
	
	private static void lenkuParbaude(String vards) {
		//le��u no�sin��ana, lai b�tu optim�l� diapazon� no 0 l�dz 360
		while (DataBase.cilvekuList.get(vards).xyz.fi < 0) DataBase.cilvekuList.get(vards).xyz.fi += 360;
		while (DataBase.cilvekuList.get(vards).xyz.fi >= 360) DataBase.cilvekuList.get(vards).xyz.fi -= 360;
	}

	private static void kustiba(String vards) {

		//�truma  projekcijas
		double vx = DataBase.cilvekuList.get(vards).xyz.v * Math.cos(Math.toRadians(DataBase.cilvekuList.get(vards).xyz.fi)),
				vy = DataBase.cilvekuList.get(vards).xyz.v * Math.sin(Math.toRadians(DataBase.cilvekuList.get(vards).xyz.fi));

		//kust�ba pa as�m
		DataBase.cilvekuList.get(vards).xyz.x += vx;
		DataBase.cilvekuList.get(vards).xyz.y += vy;
	}

	private static void maluParbaude(String vards) { //situ�cijas pie laukuma mal�m
		Cilveks cilveks = DataBase.cilvekuList.get(vards);

		int platums = DataBase.mapChunkW,
				chunkX = cilveks.xyz.chunkXY.get(0), //playera moment�nais chunks
				chunkY = cilveks.xyz.chunkXY.get(1);

		List<Integer> chunk0 = new ArrayList<>();
		chunk0.add(chunkX);
		chunk0.add(chunkY);

		if (cilveks.xyz.x < 0 ) { //rietumi
			cilveks.xyz.x += platums;
			chunkX--;
			if (chunkX < 0) chunkX += DataBase.mapChunkCountX;
		}
		if (cilveks.xyz.y < 0 ) { //zieme�i
			cilveks.xyz.y += platums;
			chunkY--;
			if (chunkY < 0) chunkY += DataBase.mapChunkCountY;
		}
		if (cilveks.xyz.x >= platums) { //austrumi
			cilveks.xyz.x -= platums;
			chunkX++;
			if (chunkX >= DataBase.mapChunkCountX) chunkX -= DataBase.mapChunkCountX;
		}
		if (cilveks.xyz.y >= platums) { //dienvidi
			cilveks.xyz.y -= platums;
			chunkY++;
			if (chunkY >= DataBase.mapChunkCountY) chunkY -= DataBase.mapChunkCountY;
		}

		if(chunkX != chunk0.get(0) || chunkY != chunk0.get(1)){

			DataBase.laukums.get(chunk0).cilvekiList.remove(vards); //j�iz�em no vienas tabulas

			List<Integer> chunkXY = new ArrayList<>();
			chunkXY.add(chunkX);
			chunkXY.add(chunkY);
			DataBase.laukums.get(chunkXY).cilvekiList.add(vards); //j�ieliek otr� tabul�
		}

	}

	private static void maluParbaudeVecais(){

		// zem�k atdur�an�s pret mal�m - �o v�l�k var�s izmantot, kad pievienos �kas un sienas

//		int augstums= KonstantesUniversal.laukumaAugstumsSum, platums=KonstantesUniversal.laukumaPlatumsSum, mala=KonstantesUniversal.mala;
//		double resnums= FizikasKonstantes.resnumaKoefic*cilveks.hpmax;
//
//		if(cilveks.xyz.x<=mala+resnums/2) { //kreis� mala
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
//		if(cilveks.xyz.x>=platums-mala-resnums/2) { //lab� mala
//			cilveks.xyz.x=platums-mala-resnums/2;
//
//			if(cilveks.xyz.fi<90&&cilveks.xyz.fi>=0) {
//				cilveks.xyz.fi=180-cilveks.xyz.fi;
//			}
//			if(cilveks.xyz.fi>270&&cilveks.xyz.fi<360) {
//				cilveks.xyz.fi=180+360-cilveks.xyz.fi;
//			}
//		}
//		if(cilveks.xyz.y<=mala+resnums/2) { //aug��j� mala
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
//		if(cilveks.xyz.y>=augstums-mala-resnums/2) { //apak��j� mala
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
