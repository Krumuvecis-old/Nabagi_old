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

			Main.komandasList.get(i).getBiedruList(i); //atjauno biedru sarakstu t�l�kiem apr��iniem

			if (i==0) continue; //pirmo komandu t�l�k neapskata
			
			
			if (Main.komandasList.get(i).biedruList.size()>0){ //p�rbaudu, lai nav j�ma��s cauri tuk�aj�m komand�m
				komanduVesture(i);
				komanduApskats(i);
			}

			if (Main.komandasList.get(i).biedruList.size()<=0){ //�is ir komandu cleanup
				Main.komandasList.remove(i);
				i--;
				continue;
			}
			
		}
	}

	private static void komanduVesture(int i) {
		//i nekad neb�s 0, jo tiek atsij�ts iepriek�

		Komanda komanda = Main.komandasList.get(i);
		int skaits=komanda.biedruList.size();

		if (skaits>komanda.rekords) { //nosaka person�go rekordu
			komanda.rekords=skaits;
		}

		if (skaits>komanduVestureMaksimums) { //mekl� v�sturisko komandu-rekordisti
			komanduVestureMaksimums=skaits;
			komanduVestureLielakaKomanda=String.valueOf(komanda.nosaukums);
		}
	}
	
	private static void komanduApskats(int numurs) {
		//numurs un skaits nekad neb�s 0, jo tie tiek atsij�ti jau ieprek�
		Komanda komanda=Main.komandasList.get(numurs);
		
		komanda.mekleKarali();
		
		if (komanda.karalis<0) navKaralis(numurs); //ja komand� nav atrodams galvenais

		nakotnesKomanduApskats(komanda); //nepabeigtas n�kotnes idejas

	}

 	public static void jaunaKomanda(String galvenais) {

		Komanda.maxKomanda++;

		Komanda jaunaKomanda=new Komanda();

		jaunaKomanda.nosaukums = Komandu.komandaNosaukumsDefault + Komanda.maxKomanda;
		jaunaKomanda.galvenais=galvenais;

		jaunaKomanda.krasa=assignColor();

		Main.komandasList.add(jaunaKomanda);

	}

	public static double[] komandasTakenColors() { //j�b�t public, lai var�tu izvad�t grafiski (saglab� tikai hue v�rt�bas)
		//p�rbauda kuras kr�sas komand�m ir nepieejamas
		
		double[] bannedList; //komand�m pavisam aizliegt�s kr�sas
		if (Grafiskie.komandasBannedColors) {
			Random rand=new Random();
			bannedList = Grafiskie.komandasBannedColorList;
			bannedList[0]=rand.nextDouble(); //lai �akar�tu visu sadal�jumu
			bannedList[1]=rand.nextDouble(); //lai �akar�tu visu sadal�jumu
			bannedList[2]=rand.nextDouble(); //lai �akar�tu visu sadal�jumu
		} else bannedList = new double[0];
		
		
		double[] colorList = new double[Main.komandasList.size() + bannedList.length]; //kop�jais nepieejamo kr�su saraksts
		
		for (int i=0; i<Main.komandasList.size(); i++) { //nolasa visu eso�o komandu kr�sas un saliek sarakst�
			Color krasa = Main.komandasList.get(i).krasa;
			colorList[i] = Formulas.getHue(krasa);
		}
		
		for (int i=0;i<bannedList.length;i++) { //saliek sarakst� visas pavisam aizliegt�s kr�sas
			colorList[i+Main.komandasList.size()]=bannedList[i];
		}
		
		return colorList;
	}
	
	private static Color assignColor() { //izdod jaunas komandas kr�su
		double[] colorList=komandasTakenColors();
		
		double hue;
		
		if (colorList.length==1) { //ja ir tikai 1 aizliegt� kr�sa, pa�em tie�i pret�jo kr�su
			hue = 0.5 + colorList[0];
		} else { //ja vair�k par 1 kr�su
			Arrays.sort(colorList); //sarindo augo�� sec�b�
			
			int nr1=0, nr2=1;
			double distance=0;
			
			for (int i=0; i<colorList.length; i++) { //sal�dzina visu komandu kr�sas (konkr�ti hue v�rt�bas)
				
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
			} else { //ja liel�kais att�lums ir starp p�d�jo un pirmo
				hue = (colorList[nr1]+1+colorList[nr2])/2;
			}
			
		}
		if (hue>=1) hue-=1; //nepiecie�ams apgriezt lai nav OutOfRange
		
		return new Color(Color.HSBtoRGB((float)hue, 1, 1));
	}

	private static void navKaralis(int numurs) {
		Komanda komanda=Main.komandasList.get(numurs);
		Random r=new Random();

		if(r.nextDouble()< Komandu.komandaIzjuktChance) { //iesp�ja, ka mirstot karalim, izjuks komanda

			for (int i = 0; i< komanda.biedruList.size(); i++) { //visus komandasbiedrus p�rliek 0.komand�

				Cilveks cilveks = Cilveks.getPlayer(komanda.biedruList.get(i));

				cilveks.komanda=Main.komandasList.get(0).nosaukums; //visus ieliek 0.komand�

				//cilveks.rangs[0]=0; //�� aile lai paliek, jo karav�ri var b�t karav�ri ar� bez komandas
				cilveks.rangs[1]=0;

				komanda.biedruList.remove(i);
				i--;
			}

		} else { //kara�a titula p�rdal��ana

			komanda.karalis=komanda.bagatakais;

			Cilveks cilveks = Cilveks.getPlayer(komanda.biedruList.get(komanda.karalis));

			cilveks.rangs = new int[]{0, 3};
			komanda.galvenais = cilveks.vards;
		}

	}

	private static void nakotnesKomanduApskats(Komanda komanda){
//		if(!(komanda.karalis<0)) {//v�lreiz apskata visus, ja jauns karalis atrasts
//
//			for (int i = 0; i< komanda.biedruList.size(); i++) { //v�lreiz apskata visus komandasbiedrus
//				//if(i==komanda.karalis) continue;
//
//				//te var�tu p�rskat�t rangus vai veikt jebk�das citas darb�bas
//			}
//		}
	}

}
