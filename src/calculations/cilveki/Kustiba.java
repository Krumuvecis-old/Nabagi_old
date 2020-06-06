package calculations.cilveki;

import calculations.KonstantesUniversal;
import calculations.Main;
import calculations.komandas.Biedrs;

class Kustiba {
	
	protected static void main(Cilveks cilveks, int i) {
		
		lenkuParbaude(cilveks);
		kustiba(cilveks);
		maluParbaude(i);

	}
	
	private static void lenkuParbaude(Cilveks cilveks) { //le��u no�sin��ana
		for( ; cilveks.xyz.fi<0; cilveks.xyz.fi+=360) {}
		for( ; cilveks.xyz.fi>=360; cilveks.xyz.fi-=360) {}
	}

	private static void kustiba(Cilveks cilveks) { //koordin�tu nomai�a

		//�truma  projekcijas
		double vx=cilveks.xyz.v*Math.cos(Math.toRadians(cilveks.xyz.fi));
		double vy=cilveks.xyz.v*Math.sin(Math.toRadians(cilveks.xyz.fi));

		//kust�ba pa as�m
		cilveks.xyz.x+=vx;
		cilveks.xyz.y+=vy;
	}

	private static void maluParbaude(int numurs) { //situ�cijas pie laukuma mal�m
		Biedrs biedrs = Cilveks.cilvekuListPilnais.get(numurs);
		Cilveks cilveks = Cilveks.getPlayer(biedrs.chunkXY, biedrs.i);

		int platums=KonstantesUniversal.mapChunkW, chunkX=biedrs.chunkXY[0], chunkY=biedrs.chunkXY[0];

		if (cilveks.xyz.x < 0 ) { //rietumi
			cilveks.xyz.x+=platums;
			chunkX--;
			if (chunkX < 0) chunkX = KonstantesUniversal.mapChunkCountX - 1;
		}
		if (cilveks.xyz.y < 0 ) { //zieme�i
			cilveks.xyz.y+=platums;
			chunkY--;
			if (chunkY < 0) chunkY = KonstantesUniversal.mapChunkCountY - 1;
		}
		if (cilveks.xyz.x >= platums) { //austrumi
			cilveks.xyz.x-=platums;
			chunkX++;
			if (chunkX >= KonstantesUniversal.mapChunkCountX) chunkX = 0;
		}
		if (cilveks.xyz.y >= platums) { //dienvidi
			cilveks.xyz.y-=platums;
			chunkY++;
			if (chunkY >= KonstantesUniversal.mapChunkCountY) chunkY = 0;
		}

		int chunkX0=biedrs.chunkXY[0], chunkY0=biedrs.chunkXY[0];
		if(chunkX!=chunkX0 || chunkY!=chunkY0){ //j�iz�em no vienas tabulas un j�ieliek otr�

			Main.laukums.get(chunkX).get(chunkY).cilvekiList.add(cilveks);
			Main.laukums.get(chunkX0).get(chunkY0).cilvekiList.remove(biedrs.i);
		}

	}

	private static void maluParbaudeVecais(){

		// zem�k atdur�an�s pret mal�m

//		int augstums= KonstantesUniversal.laukumaAugstumsSum, platums=KonstantesUniversal.laukumaPlatumsSum, mala=KonstantesUniversal.mala;
//		double resnums= Fizikas.resnumaKoefic*cilveks.hpmax;
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
