package localClient;

import localClient.grafika.Grafika;
import localClient.grafika.GrafikasDati;
import localClient.grafika.grafikaModes.develop.DevelopDrawManager;
import localClient.grafika.grafikaModes.lobby.LobbyDrawManager;
import localClient.grafika.grafikaModes.playerView.PlayerViewDrawManager;
import localClient.grafika.grafikaModes.settings.SettingsDrawManager;
import localClient.grafika.grafikaModes.setup.SetupDrawManager;
import localClient.grafika.grafikaModes.spectate.SpectateDrawManager;
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

		generateDrawManagerList();
		modeCurrent = ModeOption.setup;

		System.out.println("ClientThread: dati initialized");
	}

	private void generateDrawManagerList(){
		drawManagerList.put(ModeOption.setup,
				new SetupDrawManager(grafikasDati.ekranaPlatums, grafikasDati.ekranaAugstums,
						grafikasDati.colorPalette)); //setup

		drawManagerList.put(ModeOption.develop,
				new DevelopDrawManager(grafikasDati.ekranaPlatums, grafikasDati.ekranaAugstums,
						grafikasDati.colorPalette)); //develop

		drawManagerList.put(ModeOption.settings,
				new SettingsDrawManager(grafikasDati.ekranaPlatums, grafikasDati.ekranaAugstums,
						grafikasDati.colorPalette)); //settings

		drawManagerList.put(ModeOption.spectate,
				new SpectateDrawManager(grafikasDati.ekranaPlatums, grafikasDati.ekranaAugstums,
						grafikasDati.colorPalette)); //spectate

		drawManagerList.put(ModeOption.lobby,
				new LobbyDrawManager(grafikasDati.ekranaPlatums, grafikasDati.ekranaAugstums,
						grafikasDati.colorPalette)); //lobby

		drawManagerList.put(ModeOption.playerView,
				new PlayerViewDrawManager(grafikasDati.ekranaPlatums, grafikasDati.ekranaAugstums,
						grafikasDati.colorPalette)); //playerView
	}

	public void update(Grafika grafika){
		grafikasDati.ekranaPlatums = grafika.getWidth();
		grafikasDati.ekranaAugstums = grafika.getHeight();

		drawManagerList.get(modeCurrent).layout.updateCalculatedValues(
				grafikasDati.ekranaPlatums,
				grafikasDati.ekranaAugstums); //lai ekrânam varçtu notikt resize
	}

	
}
