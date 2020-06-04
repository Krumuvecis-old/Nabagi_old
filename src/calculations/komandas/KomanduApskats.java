package calculations.komandas;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import calculations.konstantes.Cilveku;
import calculations.konstantes.Formulas;
import calculations.Main;
import calculations.cilveki.Cilveks;
import calculations.cilveki.CilvekuApskats;
import calculations.konstantes.Grafiskie;
import calculations.konstantes.Komandu;


public class KomanduApskats {
	
	public static int komanduVestureMaksimums=0;
	public static String komanduVestureLielakaKomanda="0";
	
	public static void main() {
		for(int i=0;i<Main.komandasList.size();i++) { //apskata visas komandas
			
			playerCount(i);
			
			if (i==0) continue; //pirmo komandu tâlâk neapskata
			
			
			
			if (Main.komandasList.get(i).skaits>0){ //pârbaudu, lai nav jâmaïâs cauri tukðajâm komandâm
				
				komanduVesture(i);
				komanduApskats(i);
				
			}
			
			if (Main.komandasList.get(i).skaits<=0){ //ðis ir komandu cleanup
				Main.komandasList.remove(i);
				i--;
				continue;
			}
			
		}
	}
	
	private static void playerCount(int numurs) {
		Komanda komanda=Main.komandasList.get(numurs);
		int speletaji=0;
		for (int i=0;i<Main.cilvekiList.size();i++) {
			if (komanda.nosaukums==Main.cilvekiList.get(i).komanda) speletaji++;
		}
		komanda.skaits=speletaji;
	}
	
	private static void komanduVesture(int i) {
		//i nekad nebûs 0, jo tiek atsijâts iepriekð
		
		if (Main.komandasList.get(i).skaits>komanduVestureMaksimums) {
			komanduVestureMaksimums=Integer.valueOf(Main.komandasList.get(i).skaits);
			komanduVestureLielakaKomanda=String.valueOf(Main.komandasList.get(i).nosaukums);
		}
	}
	
	private static void komanduApskats(int numurs) {
		//numurs nekad nebûs 0, jo tiek atsijâts jau ieprekð
		//Komanda komanda=Main.komandasList.get(numurs); 
		
		int[] elite = mekleKarali(numurs); //temporary lielums
		int karalis=elite[0], bagatakais=elite[1];
		
		if (karalis<0) karalis=navKaralis(numurs, bagatakais); //ja komandâ nav atrodams galvenais
		
		if(!(karalis<0)) {//vçlreiz apskata visus, ja jauns karalis atrasts
			for (int i=0;i<Main.cilvekiList.size();i++) { //vçlreiz apskata visus komandasbiedrus
				if(i==karalis) continue;
				
				//te varçtu pârskatît rangus vai veikt jebkâdas citas darbîbas
			}
		}
	}
	
	private static int[] mekleKarali(int numurs) {
		int karalis=-1, bagatakais=-1;
		Komanda komanda=Main.komandasList.get(numurs);
		
		double bagatiba=0;
		for (int i=0;i<Main.cilvekiList.size();i++) {
			Cilveks cilveks=Main.cilvekiList.get(i);
			
			if(cilveks.komanda==komanda.nosaukums) {//apskata  visus komandas locekïus
				
				if (cilveks.vards==komanda.galvenais) { // meklç  galveno
					karalis=i;
				}
				
				
				int zeltsNr=CilvekuApskats.countInventory(i,"Zelts", false);
				double zeltsSum=0;
				if (zeltsNr>=0) zeltsSum=cilveks.inventory.get(zeltsNr).daudzums;
				
				if(bagatakais<0||bagatiba<zeltsSum) {
					bagatiba=zeltsSum;
					bagatakais=i;
				}
			}
		}
		return new int[]{karalis,bagatakais};
	}
	
	private static int navKaralis(int numurs, int bagatakais) {
		Komanda komanda=Main.komandasList.get(numurs);
		int karalis=-1;
		Random r=new Random();
		
		if(r.nextDouble()< Komandu.komandaIzjuktChance) { //iespçja, ka mirstot karalim, izjuks komanda
			
			for (int i=0;i<Main.cilvekiList.size();i++) { //visus komandasbiedrus pârliek 0.komandâ
				if(Main.cilvekiList.get(i).komanda==komanda.nosaukums) {
					Main.cilvekiList.get(i).komanda=Main.komandasList.get(0).nosaukums; //visus ieliek 0.komandâ
					
					//Main.cilvekiList.get(i).rangs[0]=0; //ðî aile lai paliek, jo karavîri var bût karavîri arî bez komandas
					Main.cilvekiList.get(i).rangs[1]=0;
					
					komanda.skaits=0; //ðis vajadzîgs, lai komandu varçtu izdzçst
				}
			}
			
		} else { //karaïa titula pârdalîðana
			
			karalis=bagatakais;
			Main.cilvekiList.get(karalis).rangs[0]=0;
			Main.cilvekiList.get(karalis).rangs[1]=3;
			komanda.galvenais=Main.cilvekiList.get(karalis).vards;
		}
		
		return karalis;
	}
	
 	public static void jaunaKomanda(String galvenais) {
		
		
		
		Komanda.maxKomanda++;
		
		Komanda jaunaKomanda=new Komanda();
		
		jaunaKomanda.nosaukums=new String(Komandu.komandaNosaukumsDefault+Komanda.maxKomanda);
		jaunaKomanda.galvenais=galvenais;
		
		jaunaKomanda.krasa=assignColor();
		
		
		Main.komandasList.add(jaunaKomanda);
			
	}
	
	public static double[] komandasTakenColors() { //jâbût public, lai varçtu izvadît grafiski (saglabâ tikai hue vçrtîbas)
		//pârbauda kuras krâsas komandâm ir nepieejamas
		
		double[] bannedList; //komandâm pavisam aizliegtâs krâsas
		if (Grafiskie.komandasBannedColors) {
			Random rand=new Random();
			bannedList = Grafiskie.komandasBannedColorList;
			bannedList[0]=rand.nextDouble(); //lai èakarçtu visu sadalîjumu
			bannedList[1]=rand.nextDouble(); //lai èakarçtu visu sadalîjumu
			bannedList[2]=rand.nextDouble(); //lai èakarçtu visu sadalîjumu
		} else bannedList = new double[0];
		
		
		double[] colorList = new double[Main.komandasList.size() + bannedList.length]; //kopçjais nepieejamo krâsu saraksts
		
		for (int i=0; i<Main.komandasList.size(); i++) { //nolasa visu esoðo komandu krâsas un saliek sarakstâ
			Color krasa = Main.komandasList.get(i).krasa;
			colorList[i] = Formulas.getHue(krasa);
		}
		
		for (int i=0;i<bannedList.length;i++) { //saliek sarakstâ visas pavisam aizliegtâs krâsas
			colorList[i+Main.komandasList.size()]=bannedList[i];
		}
		
		return colorList;
	}
	
	private static Color assignColor() { //izdod jaunas komandas krâsu
		double[] colorList=komandasTakenColors();
		
		double hue;
		
		if (colorList.length==1) { //ja ir tikai 1 aizliegtâ krâsa, paòem tieði pretçjo krâsu
			hue = 0.5 + colorList[0];
		} else { //ja vairâk par 1 krâsu
			Arrays.sort(colorList); //sarindo augoðâ secîbâ
			
			int nr1=0, nr2=1;
			double distance=0;
			
			for (int i=0; i<colorList.length; i++) { //salîdzina visu komandu krâsas (konkrçti hue vçrtîbas)
				
				int nr1temp=i, nr2temp=i+1;
				if(i>=colorList.length-1) nr2temp=0;
				
				double distanceTemp;
				if (nr2temp>nr1temp) {
					distanceTemp=colorList[nr2temp]-colorList[nr1temp];
				} else {
					distanceTemp=1+colorList[nr2temp]-colorList[nr1temp];
				}
				
				if (distanceTemp>distance) {
					distance=distanceTemp;
					nr1=nr1temp;
					nr2=nr2temp;
				}
				
			}
			
			if (nr2>nr1) { //standarts
				hue = (colorList[nr1]+colorList[nr2])/2;
			} else { //ja lielâkais attâlums ir starp pçdçjo un pirmo
				hue = (colorList[nr1]+1+colorList[nr2])/2;
			}
			
		}
		if (hue>=1) hue-=1; //nepiecieðams apgriezt lai nav OutOfRange
		
		return new Color(Color.HSBtoRGB((float)hue, 1, 1));
	}
	
}
