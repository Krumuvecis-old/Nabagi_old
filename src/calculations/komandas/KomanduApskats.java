package calculations.komandas;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import calculations.Location;
import calculations.konstantes.Formulas;
import calculations.Main;
import calculations.cilveki.Cilveks;
import calculations.konstantes.Grafiskie;
import calculations.konstantes.Komandu;


public class KomanduApskats {
	
	public static int komanduVestureMaksimums=0;
	public static String komanduVestureLielakaKomanda="0";
	
	public static void main() {
		for(int i=0; i<Main.komandasList.size(); i++) { //apskata visas komandas

			Main.komandasList.get(i).getBiedruList(i); //atjauno biedru sarakstu tâlâkiem aprçíiniem

			if (i==0) continue; //pirmo komandu tâlâk neapskata
			
			
			if (Main.komandasList.get(i).biedruList.size()>0){ //pârbaudu, lai nav jâmaïâs cauri tukðajâm komandâm
				komanduVesture(i);
				komanduApskats(i);
			}

			if (Main.komandasList.get(i).biedruList.size()<=0){ //ðis ir komandu cleanup
				Main.komandasList.remove(i);
				i--;
				continue;
			}
			
		}
	}

	private static void komanduVesture(int i) {
		//i nekad nebûs 0, jo tiek atsijâts iepriekð

		Komanda komanda = Main.komandasList.get(i);
		int skaits=komanda.biedruList.size();

		if (skaits>komanda.rekords) { //nosaka personîgo rekordu
			komanda.rekords=skaits;
		}

		if (skaits>komanduVestureMaksimums) { //meklç vçsturisko komandu-rekordisti
			komanduVestureMaksimums=skaits;
			komanduVestureLielakaKomanda=String.valueOf(komanda.nosaukums);
		}
	}
	
	private static void komanduApskats(int numurs) {
		//numurs un skaits nekad nebûs 0, jo tie tiek atsijâti jau ieprekð
		Komanda komanda=Main.komandasList.get(numurs);
		
		komanda.mekleKarali();
		
		if (komanda.karalis<0) navKaralis(numurs); //ja komandâ nav atrodams galvenais

		nakotnesKomanduApskats(komanda); //nepabeigtas nâkotnes idejas

	}

 	public static void jaunaKomanda(String galvenais) {

		Komanda.maxKomanda++;

		Komanda jaunaKomanda=new Komanda();

		jaunaKomanda.nosaukums = Komandu.komandaNosaukumsDefault + Komanda.maxKomanda;
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

	private static void navKaralis(int numurs) {
		Komanda komanda=Main.komandasList.get(numurs);
		Random r=new Random();

		if(r.nextDouble()< Komandu.komandaIzjuktChance) { //iespçja, ka mirstot karalim, izjuks komanda

			for (int i = 0; i< komanda.biedruList.size(); i++) { //visus komandasbiedrus pârliek 0.komandâ

				Cilveks cilveks = Cilveks.getPlayer(komanda.biedruList.get(i));

				cilveks.komanda=Main.komandasList.get(0).nosaukums; //visus ieliek 0.komandâ

				//cilveks.rangs[0]=0; //ðî aile lai paliek, jo karavîri var bût karavîri arî bez komandas
				cilveks.rangs[1]=0;

				komanda.biedruList.remove(i);
				i--;
			}

		} else { //karaïa titula pârdalîðana

			komanda.karalis=komanda.bagatakais;

			Cilveks cilveks = Cilveks.getPlayer(komanda.biedruList.get(komanda.karalis));

			cilveks.rangs = new int[]{0, 3};
			komanda.galvenais = cilveks.vards;
		}

	}

	private static void nakotnesKomanduApskats(Komanda komanda){
//		if(!(komanda.karalis<0)) {//vçlreiz apskata visus, ja jauns karalis atrasts
//
//			for (int i = 0; i< komanda.biedruList.size(); i++) { //vçlreiz apskata visus komandasbiedrus
//				//if(i==komanda.karalis) continue;
//
//				//te varçtu pârskatît rangus vai veikt jebkâdas citas darbîbas
//			}
//		}
	}

}
