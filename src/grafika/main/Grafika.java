package grafika.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cilveki.CilvekuApskats;
import konstantes.Formulas;
import konstantes.Parametri;

@SuppressWarnings("serial")
class Grafika extends JPanel {
	
	protected JFrame ekrans;
	private Grafika grafika;
	
	private static PlayerThread threadTemp; //temporary lielums zîmçðanas funkcijâm
	
	private ArrayList<dataBase.Cilveks> cilvekiList = dataBase.Dati.cilvekiList;
	private ArrayList<dataBase.Komanda> komandasList = dataBase.Dati.komandasList;
	private ArrayList<dataBase.Lieta> lietasList = dataBase.Dati.lietas;
	
	//te nelikt mainîgos!!! (var likt pie thread.dati)
	
	protected void initialize(PlayerThread thread) {
		threadTemp=thread;
		
		ekrans = new JFrame(thread.dati.windowTitle);
		ekrans.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		grafika = new Grafika();
		
		ekrans.getContentPane().add(BorderLayout.CENTER, grafika);
		ekrans.setResizable(true);
		ekrans.setSize(thread.dati.ekranaPlatums, thread.dati.ekranaAugstums);
		ekrans.setLocationByPlatform(true);
		
		ekrans.setVisible(true);
		
		if (thread.dati.fullscreen) ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	void main (PlayerThread thread) {
		threadTemp=thread; //temporary update zîmçðanas cikliem, jo repaint() nepieòem argumentus
		
		ekrans.repaint();
	}
	
	protected void paintComponent(Graphics g) {
		
		drawFons(g);
		
		int nobideX=threadTemp.dati.nobideX, nobideY=threadTemp.dati.nobideY;
		
		drawLaukums(g, nobideX, nobideY);
		
		drawLoot(g, nobideX, nobideY, threadTemp.dati.lietasDrawInfo); // g,drawInfo
		drawCilveki(g, nobideX, nobideY, threadTemp.dati.cilvekiDrawInfo, threadTemp.dati.cilvekiDrawR); // g,drawInfo,drawR
		
		drawTest(g);
		drawButtons(g);
	}
	
	private void drawFons(Graphics g) {
		g.setColor(Color.black); // fons
		g.fillRect(0, 0, getWidth(), getHeight());
		
	}
	
	private void drawLaukums(Graphics g, int nobideX, int nobideY) {
		int mala=Parametri.mala;
		
		g.setColor(Parametri.malasKrasa); //laukuma mala
		g.fillRect(nobideX, nobideY, Parametri.platums, Parametri.augstums); 
		
		g.setColor(Parametri.laukumaKrasa); //laukums
		g.fillRect(nobideX+mala, nobideY+mala, Parametri.platums-(mala*2), Parametri.augstums-(mala*2));
		g.setColor(Color.black); //laukuma kontûra
		g.drawRect(nobideX+mala, nobideY+mala, Parametri.platums-(mala*2), Parametri.augstums-(mala*2));
	}
	
	private void drawLoot(Graphics g, int nobideX, int nobideY, boolean drawInfo) {
		for(int i=0;i<lietasList.size();i++) { //zîmç lietas, kas izmçtâtas pa karti
			
			
			if(lietasList.get(i).nosaukums=="Zelts") {
				g.setColor(Parametri.lietasColorZelts); //iekða
				g.fillOval((int)(nobideX+lietasList.get(i).x-Parametri.zeltaResnums/2),
						(int)(nobideY+lietasList.get(i).y-Parametri.zeltaResnums/2),
						Parametri.zeltaResnums, Parametri.zeltaResnums);
				g.setColor(Color.black); //kontûra
				g.drawOval((int)(nobideX+lietasList.get(i).x-Parametri.zeltaResnums/2),
						(int)(nobideY+lietasList.get(i).y-Parametri.zeltaResnums/2),
						Parametri.zeltaResnums, Parametri.zeltaResnums);
				if(drawInfo==true) {
					String nosaukums=""+new DecimalFormat("#.#").format(lietasList.get(i).daudzums);
					g.drawString(nosaukums,
							(int)(nobideX+lietasList.get(i).x+Parametri.zeltaResnums/2+3),
							(int)(nobideY+lietasList.get(i).y+7));
				}
				
			} else if(lietasList.get(i).nosaukums=="Paika") {
				g.setColor(Parametri.lietasColorPaika); //iekða
				g.fillOval((int)(nobideX+lietasList.get(i).x-Parametri.paikasResnums/2),
						(int)(nobideY+lietasList.get(i).y-Parametri.paikasResnums/2),
						Parametri.paikasResnums, Parametri.paikasResnums);
				g.setColor(Color.black); //kontûra
				g.drawOval((int)(nobideX+lietasList.get(i).x-Parametri.paikasResnums/2),
						(int)(nobideY+lietasList.get(i).y-Parametri.paikasResnums/2),
						Parametri.paikasResnums, Parametri.paikasResnums);
				if(drawInfo==true) {
					
					String nosaukums=""+new DecimalFormat("#.#").format(lietasList.get(i).daudzums);
					
					g.drawString(nosaukums,
							(int)(nobideX+lietasList.get(i).x-7),
							(int)(nobideY+lietasList.get(i).y+Parametri.paikasResnums/2+15));
				}
				
			} else { //neklasificçti objekti
				g.setColor(Parametri.lietasColorDefault); //iekða
				g.fillOval((int)(nobideX+lietasList.get(i).x-Parametri.lietasResnums/2),
						(int)(nobideY+lietasList.get(i).y-Parametri.lietasResnums/2),
						Parametri.lietasResnums, Parametri.lietasResnums);
				g.setColor(Color.black); //kontûra
				g.drawOval((int)(nobideX+lietasList.get(i).x-Parametri.lietasResnums/2),
						(int)(nobideY+lietasList.get(i).y-Parametri.lietasResnums/2),
						Parametri.lietasResnums, Parametri.lietasResnums);
				if(drawInfo==true) {
					String nosaukums=lietasList.get(i).nosaukums+"-"+(int)lietasList.get(i).daudzums;
					g.drawString(nosaukums,
							(int)(nobideX+lietasList.get(i).x-15),
							(int)(nobideY+lietasList.get(i).y+Parametri.lietasResnums/2+15));
				}
			}
			
			
			
		}
	}
	
	private void drawCilveki(Graphics g, int nobideX, int nobideY, boolean drawInfo, boolean drawR) {
		double resnumaKoefic=Parametri.resnumaKoefic;
		
		for(int i=0;i<cilvekiList.size();i++) {
			
			//pats apskatâmais spçlçtâjs
			
			int resnums=(int)(resnumaKoefic*cilvekiList.get(i).hpmax);
			
			
			int komanda=0; //pçc  komandas nosaka krâsu
			for (int j=0;j<komandasList.size();j++) {
				if(cilvekiList.get(i).komanda==komandasList.get(j).nosaukums) {
					komanda=j;
					break;
				}
			}
			
			double hpRatio=cilvekiList.get(i).hp/cilvekiList.get(i).hpmax;
			
			Color krasa = new Color(Color.HSBtoRGB((float)Formulas.getHue(komandasList.get(komanda).krasa),
					(float)Parametri.cilvekiKrasaSaturation,
					(float)(Parametri.cilvekiKrasaBrightnessMin+(Parametri.cilvekiKrasaBrightnessMax-Parametri.cilvekiKrasaBrightnessMin)*hpRatio)));
			
			//rumpis
			g.setColor(krasa); //iekða
			g.fillOval((int)(nobideX+cilvekiList.get(i).xyz.x-resnums/2),
					(int)(nobideY+cilvekiList.get(i).xyz.y-resnums/2),
					resnums, resnums); 
			g.setColor(Color.black);//kontûra
			g.drawOval((int)(nobideX+cilvekiList.get(i).xyz.x-resnums/2),
					(int)(nobideY+cilvekiList.get(i).xyz.y-resnums/2),
					resnums, resnums);
			
			if(cilvekiList.get(i).vards==komandasList.get(komanda).galvenais) { //karalis
				g.setColor(new Color(0,0,0)); //kroòa krâsa - melns  punkts
				double kronaResnums=resnums/2;
				g.fillOval((int)(nobideX+cilvekiList.get(i).xyz.x-kronaResnums/2),
						(int)(nobideY+cilvekiList.get(i).xyz.y-kronaResnums/2),
						(int)kronaResnums, (int)kronaResnums); //kronis
			}
			
			
			g.setColor(krasa);
			if(drawR) { //redzesloks
				g.drawOval((int)(nobideX+cilvekiList.get(i).xyz.x-cilvekiList.get(i).R1),
						(int)(nobideY+cilvekiList.get(i).xyz.y-cilvekiList.get(i).R1),
						(int)(cilvekiList.get(i).R1*2),(int)(cilvekiList.get(i).R1*2)); //R1 - tuvais
				
				g.drawOval((int)(nobideX+cilvekiList.get(i).xyz.x-cilvekiList.get(i).R2),
						(int)(nobideY+cilvekiList.get(i).xyz.y-cilvekiList.get(i).R2),
						(int)(cilvekiList.get(i).R2*2),(int)(cilvekiList.get(i).R2*2)); //R2 - tâlais
			}
			
			
			if (drawInfo) { //nosaukumi un  informâcija
				
				
				//saskaita cik kuram paika un zelts, lai varçtu izvadît
				int zeltsNr=CilvekuApskats.countInventory(i,"Zelts", false);
				double zeltsSum=0;
				if (zeltsNr>=0) zeltsSum=cilvekiList.get(i).inventory.get(zeltsNr).daudzums;
				
				int paikaNr=CilvekuApskats.countInventory(i,"Paika", false);
				double paikaSum=0;
				if (paikaNr>=0) paikaSum=cilvekiList.get(i).inventory.get(paikaNr).daudzums;
				
				
				//g.setColor(new Color(255,100,200)); //default krâsa?
				int textSize=15;
				
				int w0=-1; //pirmâ rinda (-1 vienu virs spçlçtâja, 
				
				String[] tekstListe = new String[]{
						cilvekiList.get(i).komanda+" - "+cilvekiList.get(i).vards,//+" - ["+cilvekiList.get(i).rangs[0]+"-"+cilvekiList.get(i).rangs[1]+"] - "+(int)(cilvekiList.get(i).hp/cilvekiList.get(i).hpmax*100)+"%",
						"",
						"ATK:"+(int)(cilvekiList.get(i).stiprums)+" DEF:"+(int)(cilvekiList.get(i).brunas),
						"drosme: "+(int)(cilvekiList.get(i).drosme*1000),
						//"gataviba: "+  cilvekiList.get(i).gataviba,
						//"lietas: "+cilvekiList.get(i).inventory.size(),
						"Z"+(int)(zeltsSum)+" P"+(int)(paikaSum),
						//cilvekiList.get(i).darbiba,
						//"paika: "+(int)(cilvekiList.get(i).paika/cilvekiList.get(i).paikaMax*100)+"%" //paikas proporcija
						
				};
				
				
				String[] tekstListe2;
				
				if (cilvekiList.get(i).orderi.size()>0) {
					if(cilvekiList.get(i).orderi.get(0).perk) {
						tekstListe2 = new String[]{
								cilvekiList.get(i).orderi.get(0).prece+" "+(int)cilvekiList.get(i).orderi.get(0).daudzums+" - perk",
						};
					} else { 
						tekstListe2 = new String[]{
								cilvekiList.get(i).orderi.get(0).prece+" "+(int)cilvekiList.get(i).orderi.get(0).daudzums+" - pardod",
						};
					}
					
				} else {
					tekstListe2 = new String[] {
							""
					};
				}
				
				
				
				for (int w=0;w<tekstListe.length;w++) {
					g.drawString(tekstListe[w],
							(int)(nobideX+cilvekiList.get(i).xyz.x-30),
							(int)(nobideY+cilvekiList.get(i).xyz.y+resnums/2+textSize*(w+w0)));
				}
				
				/*g.drawString("orderi "+cilvekiList.get(i).orderi.size(),
						(int)(nobideX+cilvekiList.get(i).xyz.x-30),
						(int)(nobideY+cilvekiList.get(i).xyz.y+resnums/2+textSize*(w0+tekstListe.length)));
				*/
				for (int w=0;w<tekstListe2.length;w++) {
					
					
					g.drawString(tekstListe2[w],
							(int)(nobideX+cilvekiList.get(i).xyz.x-30),
							(int)(nobideY+cilvekiList.get(i).xyz.y+resnums/2+textSize*(w+w0+tekstListe.length)));
				}
			}
		}
	}
	
	private void drawTest(Graphics g) {
		int y0=30, yw=15, w=0;
		
		g.setColor(Color.red);
		g.drawString("x: "+threadTemp.input.xPele+" y: "+threadTemp.input.yPele, 20, y0+w*yw); w++;
		g.drawString("window: " + threadTemp.windowActive, 20, y0+w*yw); w++;
		
		
		//piespiesto pogu izvade
		String teksts;
		for (int i=0; i<threadTemp.input.pogas.length; i++) {
			teksts="poga "+i+" = "+threadTemp.input.pogas[i];
			g.drawString(teksts, 100, y0+w*yw); w++;
		}
		
	}
	
	private void drawButtons(Graphics g) {
		
		Color
			bodyColorDefault=Button.bodyColorDefault, bodyColorPressed=Button.bodyColorPressed, bodyColorActive=Button.bodyColorActive, 
			contourColorDefault=Button.contourColorDefault, contourColorPressed=Button.contourColorPressed, contourColorActive=Button.contourColorActive,
			textColorDefault=Button.textColorDefault, textColorPressed=Button.textColorPressed, textColorActive=Button.textColorActive;
		
		int burtaPlatums=Button.burtaPlatums, burtaAugstums=Button.burtaAugstums;
		
		for (int i=0;i<threadTemp.dati.buttonList.size();i++) {
			
			int x=threadTemp.dati.buttonList.get(i).x, y=threadTemp.dati.buttonList.get(i).y,
					wx=threadTemp.dati.buttonList.get(i).wx, wy=threadTemp.dati.buttonList.get(i).wy;
			boolean active=threadTemp.dati.buttonList.get(i).active, pressed=threadTemp.dati.buttonList.get(i).pressed;
			
			if (pressed) g.setColor(bodyColorPressed); else g.setColor(bodyColorDefault);
			g.fillRect(x, y, wx, wy); //zîmç paðu pogu
			
			if (active) g.setColor(contourColorActive); else g.setColor(contourColorDefault);
			g.drawRect(x, y, wx, wy); //zîmç kontûru
			
			String teksts=threadTemp.dati.buttonList.get(i).title;
			int tekstaGarums=threadTemp.dati.buttonList.get(i).title.length();
			int x0=x+wx/2-tekstaGarums*burtaPlatums/2, y0=y+wy/2+burtaAugstums/2, //teksta sâkumpunkts, lai teksts bûtu pogai pa vidu
					correction=threadTemp.dati.buttonList.get(i).correction;
			
			g.setColor(textColorDefault);
			g.drawString(teksts, x0+correction, y0); //pogas nosaukums
			//g.drawRect(x0, y0-burtaAugstums, tekstaGarums*burtaPlatums, burtaAugstums); //râmîtis ap tekstu
			
		}
		
	}
}
