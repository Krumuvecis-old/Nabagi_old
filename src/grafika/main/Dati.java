package grafika.main;

import grafika.KonstantesGrafikai;
import grafika.main.grafikaParts.DrawManager;

import java.awt.*;
import java.util.HashMap;

public class Dati {
	public CalculationTimeCalculator calculationTimeCalculator = new CalculationTimeCalculator();

	public String windowTitle = KonstantesGrafikai.ekranaNosaukums + ", SetupWindow";
	public Color backgroundColor = Color.black;
	public Layout layout;

	public boolean drawLayoutGrid = false;
	public Color layoutGridColor = Color.red;

	public ColorPalette colorPalette;
	public DrawManager drawManager;

	public boolean drawHeader = true,
			drawFooter = true,
			drawPanel1 = true,
				drawCalculationTime = true,
				drawInputDiagnosticsPanel = true,
			drawPanel2 = true,
				drawKomanduInfo=true,
			drawPanel3 = true,
			drawSampleImages = false;

//	//zemâk vecie parametri
//	// --------------------
//	//zemâk par centrâlâ cilvçku tablo Parametriem (no vecâ varianta)
//
//	protected boolean tablo2Draw=false;
//
//	protected int tablo2x0=280, tablo2y0=tablo1y0, tablo2rindasPlatums=14;
//
//	protected int tablo2platums1=200; //platums1
//	protected int tablo2platums2=130; //platums2
//	protected int tablo2platumsN=80; //platumsN
//
//	protected Color tablo2krasaDefault, tablo2krasaCritical; //paðas krâsas nosaka initialize() ciklâ
//
//
//	// --------------------
//	//zemâk par atïauto krâsu paneli
//
//	protected boolean colorPanelDraw=false;
//	protected Color colorPanelColor=Color.lightGray; //krâsu apïa kontûras krâsa
//	protected int colorPanelX0=10, colorPanelY0=380, colorPanelRadiuss=50;
//
//
//	// --------------------
//	//zemâk par kartes zîmçðanu
//
//	public boolean miniMapDraw=true, miniMapDrawInfo=true; //kartes zîmçðana vispâr un informâcija tai apakðâ
//	public int miniMapX=tablo2x0, miniMapY=tablo2y0-15,
//			miniMapPlatums=ekranaPlatums-miniMapX-50,
//			miniMapAugstums=ekranaAugstums-miniMapY-50;
//
//
//	// --------------------
//	//zemâk par centrâlâ (kartes diagnostikas) tablo Parametriem
//
//	protected boolean tablo3Draw=false;
//	protected Color tablo3krasa=Color.white;
//
//	//augstâk vecie parametri

	// --------------------
	//zemâk jaunâs funkcijas paòemtas no TimeScheduler

	public HashMap<String, Image> images = new HashMap<>();
	static final String imageLocation = "src/grafika/main/samplePictures/",
			settingsFileLocation = "src/grafika/main/",
			settingsFileName = "Settings.txt";

	public String playerFocusName="nav";
	public boolean playerFocused=false;

	Dati(){
		layout = new Layout();

		ColorPalette.generatePresetPalettes();
		ColorPalette defaultPalette = ColorPalette.presetPalettes.get(0);
		colorPalette = new ColorPalette(defaultPalette.pair1, defaultPalette.pair2, defaultPalette.pair3);

		String[][] imageNames = new String[][]{
				{"Zvaigzne.png", "zvaigzne"},
				{"Banans.png", "banana"}
		};
		FileHandler.loadSprites(images, imageNames, imageLocation);
		drawManager = new DrawManager(layout);

		System.out.println("SetupThread: dati initialized");
	}

	public void update(Grafika grafika){
		layout.update(grafika); //lai ekrânam varçtu notikt resize
	}




//	// --------------------
//	//zemâk vecâs nestrâdâjoðâs funkcijas
//
//
//	protected void initialize() {
//
//		// --------------------
//		//par pogâm
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
//		//par cilvçku tablo (tablo2) zîmçðanas krâsâm
//
//		boolean tablo2Transparent=false; //caurspîdîgs teksts
//		int tablo2alfa=255;
//		if(tablo2Transparent) tablo2alfa=100; //max caurspîdîgums
//
//		tablo2krasaDefault=new Color(255,255,0,tablo2alfa); //dzeltens
//		tablo2krasaCritical=new Color(255,0,0,tablo2alfa); //sarkans
//
//
//	}
//
//	private void addButton(int x, int y, int wx, int wy, String title, int correction) {
//
//		int i = buttonList.size();
//		buttonList.add(new Button());
//		buttonList.get(i).x=x;
//		buttonList.get(i).y=y;
//		buttonList.get(i).wx=wx;
//		buttonList.get(i).wy=wy;
//		buttonList.get(i).title=title;
//		buttonList.get(i).correction=correction;
//		buttonList.get(i).active=false;
//		buttonList.get(i).pressed=false;
//		buttonList.get(i).result=false;
//
//	}
//
//	public void update(){
//		playerFocusFind();
//
//	}
//
//	private void playerFocusFind() {
//
//		playerFocused = false;
//		if(!(playerFocusName.equals("nav"))){
//			String[] playerNamesList = playerNamesList(); //vârdu saraksts salîdzinâjumam
//
//			for (int i = 0; i < playerNamesList.length; i++) {
//				if (playerNamesList[i].equals(playerFocusName)){
//					playerFocused = true;
//					break;
//				}
//			}
//
//			if(!playerFocused) {
//				playerFocusName="nav";
//			}
//		}
//
//	}
//
//	private String[] playerNamesList(){
//
//		String[] namesList = new String[]{};
//
//		for(int[] chunkXY = {0, 0}; chunkXY[0]< Main.laukums.size(); chunkXY[0]++) {
//			for(chunkXY[1]=0; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++) {
//				for (int i=0; i<Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++){
//					String vards = Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.get(i).vards;
//					String[] namesListTemp = new String[namesList.length + 1];
//					for(int j=0; j<namesList.length; j++){
//						namesListTemp[j]=namesList[j];
//
//					}
//					namesListTemp[namesListTemp.length - 1] = vards;
//					namesList = namesListTemp;
//
//				}
//
//			}
//		}
//
//		return namesList;
//	}
//
//	protected void startPlayerView(boolean randomize) {
//		String[] playerList = playerNamesList();
//
//		int i=0;
//		if (randomize) {
//			i=(new java.util.Random()).nextInt(playerList.length);
//		}
//
//		String playerName = playerList[i]; //iegûst fokusçtâ spçlçtâja vârdu
//
//		if(!playerFocused) { //ja iepriekð nav fokusa, tad fokusçjas uz spçlçtâju
//			playerFocused=true;
//			playerFocusName=playerName;
//		}
//
//		grafika.player.PlayerThread threadTemp=new grafika.player.PlayerThread(); //jauns spçlçtâja logs
//		threadTemp.initialize(playerName); //palaiþ spçlçtâja logu
//
//	}
	
}
