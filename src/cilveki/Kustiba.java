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
	
	private static void lenkuParbaude() { //le��u no�sin��ana
		for(;cilveks.xyz.fi<0;cilveks.xyz.fi+=360) {}
		for(;cilveks.xyz.fi>=360;cilveks.xyz.fi-=360) {}
	}
	
	private static void maluParbaude() { //situ�cijas pie laukuma mal�m
		
		int augstums=Parametri.augstums, platums=Parametri.platums, mala=Parametri.mala;
		double resnums=Parametri.resnumaKoefic*cilveks.hpmax;
		
		if(cilveks.xyz.x<=mala+resnums/2) { //kreis� mala
			cilveks.xyz.x=mala+resnums/2;
			
			if(cilveks.xyz.fi>=180&&cilveks.xyz.fi<270) {
				cilveks.xyz.fi=540-cilveks.xyz.fi;
			}
			if(cilveks.xyz.fi>90&&cilveks.xyz.fi<180) {
				cilveks.xyz.fi=180-cilveks.xyz.fi;
			}
			
		}
		if(cilveks.xyz.x>=platums-mala-resnums/2) { //lab� mala
			cilveks.xyz.x=platums-mala-resnums/2;
			
			if(cilveks.xyz.fi<90&&cilveks.xyz.fi>=0) {
				cilveks.xyz.fi=180-cilveks.xyz.fi;
			}
			if(cilveks.xyz.fi>270&&cilveks.xyz.fi<360) {
				cilveks.xyz.fi=180+360-cilveks.xyz.fi;
			}
		}
		if(cilveks.xyz.y<=mala+resnums/2) { //aug��j� mala
			cilveks.xyz.y=mala+resnums/2;
			
			if(cilveks.xyz.fi>=270&&cilveks.xyz.fi<360) {
				cilveks.xyz.fi=360-cilveks.xyz.fi;
			}
			if(cilveks.xyz.fi>180&&cilveks.xyz.fi<270) {
				cilveks.xyz.fi=360-cilveks.xyz.fi;
			}
			
		}
		if(cilveks.xyz.y>=augstums-mala-resnums/2) { //apak��j� mala
			cilveks.xyz.y=augstums-mala-resnums/2;
			
			if(cilveks.xyz.fi>0&&cilveks.xyz.fi<=90) {
				cilveks.xyz.fi=360-cilveks.xyz.fi;
			}
			if(cilveks.xyz.fi>90&&cilveks.xyz.fi<180) {
				cilveks.xyz.fi=360-cilveks.xyz.fi;
			}
		}
		
	}
	
	private static void kustiba() { //koordin�tu nomai�a
		
		//�truma  projekcijas
		double vx=cilveks.xyz.v*Math.cos(Math.toRadians(cilveks.xyz.fi));
		double vy=cilveks.xyz.v*Math.sin(Math.toRadians(cilveks.xyz.fi));
		
		//kust�ba pa as�m
		cilveks.xyz.x+=vx;
		cilveks.xyz.y+=vy;
	}
	
}
