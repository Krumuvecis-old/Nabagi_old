package grafika.player;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

import konstantes.Parametri;
import konstantes.Formulas;

import calculations.lietas.Lieta;
import calculations.komandas.Komanda;
import calculations.cilveki.Cilveks;
import calculations.cilveki.CilvekuApskats;



class DrawMap {
	private PlayerThread thread;
	
	private ArrayList<Cilveks> cilvekiList = galvenais.Dati.cilvekiList;
	private ArrayList<Komanda> komandasList = galvenais.Dati.komandasList;
	private ArrayList<Lieta> lietasList = galvenais.Dati.lietas;
	
	private int nobideX, nobideY;
	private double merogs, R1, R2, x0, y0;
	
	protected void main(Graphics g, PlayerThread threadTemp) {
		thread=threadTemp;
		nobideX=Dati.karteNobideX;
		nobideY=Dati.karteNobideY;
				
		if(!thread.dati.playerDead) {
			R1=thread.dati.player.R1;
			R2=thread.dati.player.R2;
			merogs=thread.dati.kartePlatums/(R2*2);
		}
		
		drawLaukums(g);
		drawLoot(g, thread.dati.lietasDrawInfo); // g,drawInfo
		drawCilveki(g); // g
		
	}
	
	private void drawLaukums(Graphics g) {
		int mala=Parametri.mala;
		int kartePlatums=thread.dati.kartePlatums;
		
		g.setColor(Parametri.malasKrasa); //laukuma mala
		g.fillRect(nobideX, nobideY, kartePlatums, kartePlatums);
		
		g.setColor(Parametri.laukumaKrasa); //laukums
		g.fillRect(nobideX+mala, nobideY+mala, kartePlatums-(mala*2), kartePlatums-(mala*2));
		g.setColor(Color.black); //laukuma kontûra
		g.drawRect(nobideX+mala, nobideY+mala, kartePlatums-(mala*2), kartePlatums-(mala*2));
	}
	
	private void drawLoot(Graphics g, boolean drawInfo) {
		for(int i=0;i<lietasList.size();i++) { //zîmç lietas, kas izmçtâtas pa karti
			
			Lieta lieta=lietasList.get(i);
			double dx = thread.dati.player.xyz.x - lieta.x,
					dy = thread.dati.player.xyz.y - lieta.y;
			
			if (Math.hypot(dx, dy) > R2) continue; //lai atmet tos kas par tâlu
			
			double[] koord;
			koord=getAbsoluteCoordinates(false, dx, dy);
			
			
			if(lieta.nosaukums=="Zelts") {
				double resnums=Parametri.zeltaResnums*merogs;
				
				g.setColor(Parametri.lietasColorZelts); //iekða
				g.fillOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
				
				g.setColor(Color.black); //kontûra
				g.drawOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
				
				if(drawInfo==true) {
					String nosaukums=""+new DecimalFormat("#.#").format(lieta.daudzums);
					g.drawString(nosaukums, (int)(koord[0]+resnums/2+3), (int)(koord[1]+7));
				}
				
			} else if(lieta.nosaukums=="Paika") {
				double resnums=Parametri.paikasResnums*merogs;
				
				g.setColor(Parametri.lietasColorPaika); //iekða
				g.fillOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
				
				g.setColor(Color.black); //kontûra
				g.drawOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
				
				if(drawInfo==true) {
					String nosaukums=""+new DecimalFormat("#.#").format(lieta.daudzums);
					g.drawString(nosaukums, (int)(koord[0]-7), (int)(koord[1]+resnums/2+15));
				}
				
			} else { //neklasificçti objekti
				double resnums=Parametri.lietasResnums*merogs;
				
				g.setColor(Parametri.lietasColorDefault); //iekða
				g.fillOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
				
				g.setColor(Color.black); //kontûra
				g.drawOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
				
				if(drawInfo==true) {
					String nosaukums=lieta.nosaukums+"-"+(int)lieta.daudzums;
					g.drawString(nosaukums, (int)(koord[0]-15), (int)(koord[1]+resnums/2+15));
				}
			}
			
			
		}
	}
	
	private void drawCilveki(Graphics g) {
		boolean drawInfo=thread.dati.cilvekiDrawInfo, drawR=thread.dati.cilvekiDrawR;
		
		if(!thread.dati.playerDead) { //atjaunina koordinâtas tikai, kad dzîvs - kad miris râdîs, to vietu, kur nomira
			x0=thread.dati.player.xyz.x;
			y0=thread.dati.player.xyz.y;
		} else playerDead(g);
		
		double resnumaKoefic=Parametri.resnumaKoefic;
		
		for(int i=0;i<cilvekiList.size();i++) {
			Cilveks player=cilvekiList.get(i);
			double dx = x0-player.xyz.x,
					dy = y0-player.xyz.y;
			
			if (Math.hypot(dx, dy) > R2) continue; //lai atmet tos kas par tâlu (true - par tâlu)
			
			double resnums=merogs*resnumaKoefic*player.hpmax;
			
			int komanda = playerGetKomanda(player);
			Color krasa = playerGetColor(player, komanda); //pçc komandas nosaka krâsu
			
			double[] koord;
			if (player==thread.dati.player) { //zîmç galveno spçlçtâju paðâ centrâ
				koord=getAbsoluteCoordinates(true, dx, dy);
				
			} else { //zîmç pârçjos spçlçtâjus
				koord=getAbsoluteCoordinates(false, dx, dy);
				
			}
			
			//rumpis
			g.setColor(krasa); //iekða
			g.fillOval((int)(koord[0] - resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums); 
			
			g.setColor(Color.black);//kontûra
			g.drawOval((int)(koord[0]-resnums/2), (int)(koord[1]-resnums/2), (int)resnums, (int)resnums);
			
			if(player.vards==komandasList.get(komanda).galvenais) { //karalis
				
				Color kronaKrasa = Parametri.kronaKrasa;
				double kronaKoeficients=Parametri.kronaKoeficients;
				
				g.setColor(kronaKrasa);
				double kronaResnums=resnums*kronaKoeficients;
				g.fillOval((int)(koord[0]-kronaResnums/2), (int)(koord[1]-kronaResnums/2),
						(int)kronaResnums, (int)kronaResnums); //kronis
			}
			
			
			g.setColor(krasa);
			if(drawR) { //redzesloks
				double R1temp=player.R1*merogs, R2temp=player.R2*merogs;
				g.drawOval((int)(koord[0]-R1temp), (int)(koord[1]-R1temp), (int)(R1temp*2),(int)(R1temp*2)); //R1 - tuvais
				g.drawOval((int)(koord[0]-R2temp), (int)(koord[1]-R2temp), (int)(R2temp*2),(int)(R2temp*2)); //R2 - tâlais
			}
			
			if (drawInfo) playerDrawInfo(g, i, player, koord, resnums); //nosaukumi un papildinformâcija
		}
	}
	
	private double[] getAbsoluteCoordinates(boolean center, double dx, double dy) { //kartes elementu absolûtâs koordinâtas ekrânâ
		
		double x, y, korekcijaX=0, korekcijaY=0;
		
		if (!center) { //tiem, kas nav centrâ, pienâkas koordinâtu korekcija
			korekcijaX = dx*merogs;
			korekcijaY = dy*merogs;
		}
		
		x=nobideX+thread.dati.kartePlatums/2+korekcijaX;
		y=nobideY+thread.dati.kartePlatums/2+korekcijaY;
		
		return new double[] {x,y};
	}
	
	private int playerGetKomanda(Cilveks player) {
		int komanda=0;
		for (int i=0; i<komandasList.size(); i++) {
			if(player.komanda==komandasList.get(i).nosaukums) {
				komanda=i;
				break;
			}
		}
		return komanda;
	}
	
	private Color playerGetColor(Cilveks player, int komanda) {
		
		
		
		double hpRatio=player.hp/player.hpmax;
		
		return new Color(Color.HSBtoRGB((float)Formulas.getHue(komandasList.get(komanda).krasa),
				(float)Parametri.cilvekiKrasaSaturation,
				(float)(Parametri.cilvekiKrasaBrightnessMin+(Parametri.cilvekiKrasaBrightnessMax-Parametri.cilvekiKrasaBrightnessMin)*hpRatio)));
		
	}
	
	private void playerDrawInfo(Graphics g, int i, Cilveks player, double[] koord, double resnums) {
		
		//saskaita cik kuram paika un zelts, lai varçtu izvadît
		int zeltsNr=CilvekuApskats.countInventory(i,"Zelts", false);
		double zeltsSum=0;
		if (zeltsNr>=0) zeltsSum=player.inventory.get(zeltsNr).daudzums;
		
		int paikaNr=CilvekuApskats.countInventory(i,"Paika", false);
		double paikaSum=0;
		if (paikaNr>=0) paikaSum=player.inventory.get(paikaNr).daudzums;
		
		//g.setColor(new Color(255,100,200)); //default krâsa?
		int textSize=15;
		String virsraksts = player.komanda+" - "+player.vards;//+" - ["+player.rangs[0]+"-"+player.rangs[1]+"] - "+(int)(player.hp/player.hpmax*100)+"%";
		
		int w0=1; //pirmâ rinda (1 zem spçlçtâja) 
		String[] tekstListe = new String[]{
				"ATK:"+(int)(player.stiprums)+" DEF:"+(int)(player.brunas),
				"drosme: "+(int)(player.drosme*1000),
				//"gataviba: "+  player.gataviba,
				//"lietas: "+player.inventory.size(),
				"Z"+(int)(zeltsSum)+" P"+(int)(paikaSum),
				//player.darbiba,
				//"paika: "+(int)(player.paika/player.paikaMax*100)+"%" //paikas proporcija
				
		};
		
		
		String[] tekstListe2;
		
		if (player.orderi.size()>0) {
			if(player.orderi.get(0).perk) {
				tekstListe2 = new String[]{
						player.orderi.get(0).prece+" "+(int)player.orderi.get(0).daudzums+" - perk",
				};
			} else { 
				tekstListe2 = new String[]{
						player.orderi.get(0).prece+" "+(int)player.orderi.get(0).daudzums+" - pardod",
				};
			}
			
		} else {
			tekstListe2 = new String[] {
					""
			};
		}
		
		
		g.drawString(virsraksts, (int)(koord[0]-30), (int)(koord[1]-resnums/2-5));
		
		for (int w=0; w<tekstListe.length;w++) {
			g.drawString(tekstListe[w], (int)(koord[0]-30), (int)(koord[1]+resnums/2+textSize*(w+w0)));
		}
		
		/*g.drawString("orderi "+player.orderi.size(),
				(int)(koord[0]-30), (int)(koord[1]+resnums/2+textSize*(w0+tekstListe.length)));
		*/
		for (int w=0;w<tekstListe2.length;w++) {
			g.drawString(tekstListe2[w], (int)(koord[0]-30), (int)(koord[1]+resnums/2+textSize*(w+w0+tekstListe.length)));
		}
	}
	
	private void playerDead(Graphics g) {
		g.setColor(Color.red);
		double[] koord=getAbsoluteCoordinates(true, x0, y0);
		int correctionX=-12, correctionY=-7;
		g.drawString("GAME OVER", (int)koord[0]+correctionX, (int)koord[1]+correctionY);
	}
	
}
