package cilveki;

import dataBase.Cilveks;
import dataBase.Dati;
import konstantes.Parametri;

class Kustiba {
	
	private static Cilveks cilveks;
	
	protected static void main(int numurs) {
		cilveks=Dati.cilvekiList.get(numurs);
		
		lenkuParbaude();
		maluParbaude();
		kustiba();
	}
	
	private static void lenkuParbaude() { //leòíu noîsinâðana
		for(;cilveks.xyz.fi<0;cilveks.xyz.fi+=360) {}
		for(;cilveks.xyz.fi>=360;cilveks.xyz.fi-=360) {}
	}
	
	private static void maluParbaude() { //situâcijas pie laukuma malâm
		
		int augstums=Parametri.augstums, platums=Parametri.platums, mala=Parametri.mala;
		double resnums=Parametri.resnumaKoefic*cilveks.hpmax;
		
		if(cilveks.xyz.x<=mala+resnums/2) { //kreisâ mala
			cilveks.xyz.x=mala+resnums/2;
			
			if(cilveks.xyz.fi>=180&&cilveks.xyz.fi<270) {
				cilveks.xyz.fi=540-cilveks.xyz.fi;
			}
			if(cilveks.xyz.fi>90&&cilveks.xyz.fi<180) {
				cilveks.xyz.fi=180-cilveks.xyz.fi;
			}
			
		}
		if(cilveks.xyz.x>=platums-mala-resnums/2) { //labâ mala
			cilveks.xyz.x=platums-mala-resnums/2;
			
			if(cilveks.xyz.fi<90&&cilveks.xyz.fi>=0) {
				cilveks.xyz.fi=180-cilveks.xyz.fi;
			}
			if(cilveks.xyz.fi>270&&cilveks.xyz.fi<360) {
				cilveks.xyz.fi=180+360-cilveks.xyz.fi;
			}
		}
		if(cilveks.xyz.y<=mala+resnums/2) { //augðçjâ mala
			cilveks.xyz.y=mala+resnums/2;
			
			if(cilveks.xyz.fi>=270&&cilveks.xyz.fi<360) {
				cilveks.xyz.fi=360-cilveks.xyz.fi;
			}
			if(cilveks.xyz.fi>180&&cilveks.xyz.fi<270) {
				cilveks.xyz.fi=360-cilveks.xyz.fi;
			}
			
		}
		if(cilveks.xyz.y>=augstums-mala-resnums/2) { //apakðçjâ mala
			cilveks.xyz.y=augstums-mala-resnums/2;
			
			if(cilveks.xyz.fi>0&&cilveks.xyz.fi<=90) {
				cilveks.xyz.fi=360-cilveks.xyz.fi;
			}
			if(cilveks.xyz.fi>90&&cilveks.xyz.fi<180) {
				cilveks.xyz.fi=360-cilveks.xyz.fi;
			}
		}
		
	}
	
	private static void kustiba() { //koordinâtu nomaiòa
		
		//âtruma  projekcijas
		double vx=cilveks.xyz.v*Math.cos(Math.toRadians(cilveks.xyz.fi));
		double vy=cilveks.xyz.v*Math.sin(Math.toRadians(cilveks.xyz.fi));
		
		//kustîba pa asîm
		cilveks.xyz.x+=vx;
		cilveks.xyz.y+=vy;
	}
	
}
