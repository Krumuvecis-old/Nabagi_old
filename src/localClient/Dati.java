package localClient;

import localClient.grafika.Grafika;
import localClient.grafika.GrafikasDati;
import localClient.grafika.grafikaModes.setup.SetupDrawManager;
import localClient.grafika.grafikaParts.DrawManager;

import java.util.HashMap;
import java.util.Map;

public class Dati {

	public GrafikasDati grafikasDati;

	public static final String settingsFileLocation = "src/localClient/",
			settingsFileName = "Settings.txt";

	public enum ModeOption {
		setup,
		develop,
		settings,
		spectate,
		lobby,
		playerView
	}
	public ModeOption modeCurrent;
	public Map<ModeOption, DrawManager> drawManagerList = new HashMap<>();

	Dati(){
		grafikasDati = new GrafikasDati();

		drawManagerList.put(ModeOption.setup,
				new SetupDrawManager(grafikasDati.ekranaPlatums, grafikasDati.ekranaAugstums, grafikasDati.colorPalette)); //setup
		//drawManagerList.put(ModeOption.develop, ); //develop
		//drawManagerList.put(ModeOption.settings, ); //settings
		//drawManagerList.put(ModeOption.spectate, ); //spectate
		//drawManagerList.put(ModeOption.lobby, ); //lobby
		//drawManagerList.put(ModeOption.playerView, ); //playerView

		modeCurrent = ModeOption.setup;


		System.out.println("ClientThread: dati initialized");
	}

	public void update(Grafika grafika){
		grafikasDati.ekranaPlatums = grafika.getWidth();
		grafikasDati.ekranaAugstums = grafika.getHeight();

		drawManagerList.get(modeCurrent).layout.updateCalculatedValues(
				grafikasDati.ekranaPlatums,
				grafikasDati.ekranaAugstums); //lai ekr�nam var�tu notikt resize
	}


//	//zem�k vec� info par pog�m
//
//
//	protected void initialize() {
//
//		// --------------------
//		//par pog�m
//
//		buttonList = new ArrayList<Button>();
//
//		int pogasX0=5, pogasY0=20, pogasPlatums=100, pogasAugstums=30, pogasSprauga=5, w=0;
//
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Pauze",0); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"PlayerView(0)",10); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"PlayerView(rand)",10); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Tablo1",0); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Tablo2",0); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Tablo3",0); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Map",0); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"InputPanel",3); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"ColorPanel",2); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"GenRate +0.01",3); w++;
//		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"GenRate -0.01",3); w++;
//
//		inputPanelY+=(pogasAugstums+pogasSprauga)*w;
//		colorPanelY0=inputPanelY+120;
//
//
//		// --------------------
//		//par cilv�ku tablo (tablo2) z�m��anas kr�s�m
//
//		boolean tablo2Transparent=false; //caursp�d�gs teksts
//		int tablo2alfa=255;
//		if(tablo2Transparent) tablo2alfa=100; //max caursp�d�gums
//
//		tablo2krasaDefault=new Color(255,255,0,tablo2alfa); //dzeltens
//		tablo2krasaCritical=new Color(255,0,0,tablo2alfa); //sarkans
//
//
//	}
//
	
}
