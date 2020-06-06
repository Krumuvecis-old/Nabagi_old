package grafika.main;

import calculations.Main;
import calculations.cilveki.Cilveks;
import calculations.komandas.Biedrs;
import grafika.KonstantesGrafikai;

import java.awt.Color;
import java.util.ArrayList;

class Dati {
	protected calculations.CalculationTimeCalculator calculationTimeCalculator = new calculations.CalculationTimeCalculator();
	
	protected String windowTitle = KonstantesGrafikai.ekranaNosaukums + ", SetupWindow";
	protected Color fonaKrasa=Color.black, nosaukumaKrasa=Color.gray;
	protected int ekranaPlatums=1000, ekranaAugstums=700;
	
	protected int nosaukumsX=5, nosaukumsY=15;

	protected ArrayList<Biedrs> cilvekuPilnaisList;
	protected ArrayList<Button> buttonList;
	
	protected String playerFocusName;
	protected boolean playerFocused=false;
	protected int playerFocusNumber;
	
	// --------------------
	//zemâk par input testa paneli
	
	protected boolean inputPanelDraw=true;
	protected Color inputPanelColor=Color.white;
	protected int inputPanelX=5, inputPanelY=30;
	
	// --------------------
	//zemâk par galvenâ informâcijas tablo Parametriem (tablo1)
	
	protected boolean tablo1Draw=true; //tablo zîmçðana vispâr
	protected Color tablo1krasa=Color.white;
	protected int tablo1x0=120, tablo1y0=30;
	protected int tablo1tekstaPlatums=15;
	protected boolean drawCalculationTime=true;
	
	
	// --------------------
	//zemâk par centrâlâ cilvçku tablo Parametriem
	
	protected boolean tablo2Draw=false;
	
	protected int tablo2x0=280, tablo2y0=tablo1y0, tablo2rindasPlatums=14;
	
	protected int tablo2platums1=200; //platums1
	protected int tablo2platums2=130; //platums2
	protected int tablo2platumsN=80; //platumsN
	
	protected Color tablo2krasaDefault, tablo2krasaCritical; //paðas krâsas nosaka initialize() ciklâ
	
	
	// --------------------
	//zemâk par atïauto krâsu paneli
	
	protected boolean colorPanelDraw=false;
	protected Color colorPanelColor=Color.lightGray; //krâsu apïa kontûras krâsa
	protected int colorPanelX0=10, colorPanelY0=380, colorPanelRadiuss=50;
	
	
	// --------------------
	//zemâk par kartes zîmçðanu
	
	protected boolean miniMapDraw=true, miniMapDrawInfo=true; //kartes zîmçðana vispâr un informâcija tai apakðâ
	protected int miniMapX=tablo2x0, miniMapY=tablo2y0-15,
			miniMapPlatums=ekranaPlatums-miniMapX-50,
			miniMapAugstums=ekranaAugstums-miniMapY-50;
	
	
	protected void initialize() {

		//par spçlçtâjiem

		getPlayerTotalList();


		// --------------------
		//par pogâm
		
		buttonList = new ArrayList<Button>();
		
		int pogasX0=5, pogasY0=20, pogasPlatums=100, pogasAugstums=30, pogasSprauga=5, w=0;
		
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Pauze",0); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"PlayerView(0)",10); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"PlayerView(rand)",10); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Tablo1",0); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"Tablo2",0); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"MiniMap",0); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"InputPanel",3); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"ColorPanel",2); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"GenRate +0.01",3); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSprauga)*w,pogasPlatums,pogasAugstums,"GenRate -0.01",3); w++;
		
		inputPanelY+=(pogasAugstums+pogasSprauga)*w;
		colorPanelY0=inputPanelY+120;
		
		
		// --------------------
		//par cilvçku tablo (tablo2) zîmçðanas krâsâm
		
		boolean tablo2Transparent=false; //caurspîdîgs teksts
		int tablo2alfa=255;
		if(tablo2Transparent) tablo2alfa=100; //max caurspîdîgums
		
		tablo2krasaDefault=new Color(255,255,0,tablo2alfa); //dzeltens
		tablo2krasaCritical=new Color(255,0,0,tablo2alfa); //sarkans
		
		
	}
	
	private void addButton(int x, int y, int wx, int wy, String title, int correction) {
		
		int i = buttonList.size();
		buttonList.add(new Button());
		buttonList.get(i).x=x;
		buttonList.get(i).y=y;
		buttonList.get(i).wx=wx;
		buttonList.get(i).wy=wy;
		buttonList.get(i).title=title;
		buttonList.get(i).correction=correction;
		buttonList.get(i).active=false;
		buttonList.get(i).pressed=false;
		buttonList.get(i).result=false;
		
	}

	private void getPlayerTotalList(){
		cilvekuPilnaisList = new ArrayList<Biedrs>();

		for(int[] chunkXY = {0, 0}; chunkXY[0]< Main.laukums.size(); chunkXY[0]++) {
			for( ; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++) {



				for (int i=0; i<Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++){

					Biedrs cilveks = new Biedrs();
					cilveks.chunkXY=chunkXY;
					cilveks.i=i;
					cilvekuPilnaisList.add(cilveks);
				}

			}
		}
	}

	protected void playerFocusFind() {
		int number=-1;
		for (int i = 0; i< cilvekuPilnaisList.size(); i++) {
			if (Cilveks.getPlayer(cilvekuPilnaisList.get(i).chunkXY,cilvekuPilnaisList.get(i).i).vards==playerFocusName) {
				number=i;
				break;
			}
		}
		
		if(number<0) {
			playerFocused=false;
		}
		playerFocusNumber=number;
	}
	
	protected void startPlayerView(boolean randomize) {
		int i=0;
		if (randomize) i=(new java.util.Random()).nextInt(cilvekuPilnaisList.size());
		
		grafika.player.PlayerThread threadTemp=new grafika.player.PlayerThread(); //jauns spçlçtâja logs
		String playerName = Cilveks.getPlayer(cilvekuPilnaisList.get(i).chunkXY,cilvekuPilnaisList.get(i).i).vards;
		threadTemp.initialize(playerName);
		
		if(!playerFocused) { //ja nav fokusa, fokusçjas uz spçlçtâju
			playerFocused=true;
			playerFocusName=playerName;
		}
	}
	
}
