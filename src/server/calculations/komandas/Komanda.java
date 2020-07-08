package server.calculations.komandas;

import server.calculations.cilveki.Cilveks;
import server.calculations.Formulas;
import server.dataBase.DataBase;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Komanda {
	
	public static int maxKomanda=0; //numerâcija nosaukuma doðanai, lai neatkârtotos nosaukumi

	public String galvenais;
	public Color krasa;

	public Map<String, Biedrs> biedri = new HashMap<>();
	public String karalis="", bagatakais="";
	public int rekords=0;

	public Komanda(String _galvenais){
		galvenais = _galvenais;

		if(DataBase.komandasList.size() == 0){ //pati pirmâ komanda
			krasa = KomanduKonstantes.komandasColorDefault;
		} else {
			biedri.put(_galvenais, new Biedrs(new int[]{1, 3}));
			krasa = assignColor();
		}


		maxKomanda++;
	}

	public static String jaunaKomanda(String _galvenais){
		String nosaukums;
		if(DataBase.komandasList.size()==0) nosaukums = KomanduKonstantes.komandaNosaukumsFirst;
		else nosaukums = KomanduKonstantes.komandaNosaukumsDefault + maxKomanda;

		DataBase.komandasList.put(nosaukums, new Komanda(_galvenais));
		return nosaukums;
	}

	public void pievienotiesKomandai(String vards){
		biedri.put(vards, new Biedrs(new int[]{0, 0}));
	}

	public void pamestKomandu(String vards){
		biedri.remove(vards);
	}

	void playerCleanup(String nosaukums){
		for(String vards : biedri.keySet())
			if ((!DataBase.cilvekuList.containsKey(vards)) || (!DataBase.cilvekuList.get(vards).komanda.equals(nosaukums)))
				pamestKomandu(vards);
	}

	void mekleKarali(String komandasNosaukums) {
		karalis = "";
		bagatakais = "";
		double bagatiba = 0;

		for (String vards : biedri.keySet()) {
			Cilveks cilveks = DataBase.cilvekuList.get(vards);

			if(cilveks.komanda.equals(komandasNosaukums)) { //pârbauda vai cilvçks tieðâm ir biedrs

				if (vards.equals(galvenais)) { // meklç galveno un atrod
					karalis = vards;
				}

				double zeltsSum = cilveks.countItemAmount(
						cilveks.searchInventory("Zelts", true));

				if(bagatakais.equals("") || bagatiba<zeltsSum) {
					bagatiba = zeltsSum;
					bagatakais = vards;
				}
			}
		}
	}

	public static double[] komandasTakenColors() { //jâbût public, lai varçtu izvadît grafiski (saglabâ tikai hue vçrtîbas)
		//pârbauda kuras krâsas komandâm ir nepieejamas

		double[] bannedList; //komandâm pavisam aizliegtâs krâsas
		if (KomanduKonstantes.komandasBannedColors) {
			Random rand=new Random();
			bannedList = KomanduKonstantes.komandasBannedColorList;
			bannedList[0]=rand.nextDouble(); //lai èakarçtu visu sadalîjumu
			bannedList[1]=rand.nextDouble(); //lai èakarçtu visu sadalîjumu
			bannedList[2]=rand.nextDouble(); //lai èakarçtu visu sadalîjumu
		} else bannedList = new double[0];


		double[] colorList = new double[DataBase.komandasList.size() + bannedList.length]; //kopçjais nepieejamo krâsu saraksts
		int j = 0;
		for (String nosaukums : DataBase.komandasList.keySet()) { //nolasa visu esoðo komandu krâsas un saliek sarakstâ
			Color krasa = DataBase.komandasList.get(nosaukums).krasa;
			colorList[j] = Formulas.getHue(krasa);
			j++;
		}

		for (int i=0; i<bannedList.length; i++) { //saliek sarakstâ visas pavisam aizliegtâs krâsas
			colorList[i + DataBase.komandasList.size()] = bannedList[i];
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
		if (hue >= 1) hue -= 1; //nepiecieðams apgriezt lai nav OutOfRange

		return new Color(Color.HSBtoRGB((float)hue, 1, 1));
	}

}
