package grafika.player;

import java.awt.Color;
import java.util.ArrayList;

import calculations.Location;
import calculations.Main;
import calculations.cilveki.Cilveks;
import grafika.player.centerPanel.DrawCenterPanel;
import grafika.player.sidePanels.DrawSidePanels;

public class Dati {
	public calculations.CalculationTimeCalculator calculationTimeCalculator = new calculations.CalculationTimeCalculator();
	protected ArrayList<Location> cilvekuPilnaisList;

	public boolean lobby=false;

	public boolean playerDead=false;
	public String playerName;
	public Cilveks player;
		
	protected String windowTitleDefault=grafika.KonstantesGrafikai.ekranaNosaukums+", PlayerView";
	protected String windowTitle;
	protected static Color fonaKrasa=Color.black, nosaukumaKrasa=Color.gray;
	protected static int nosaukumsX=5, nosaukumsY=15;
	protected boolean fullscreen;
	public int ekranaPlatums, ekranaAugstums;
	
	public ArrayList<Button> buttonList;
	
	
	// --------------------
	//zemâk par kartes zîmçðanu
	
	public DrawCenterPanel drawCenterPanel = new DrawCenterPanel();
	public DrawSidePanels drawSidePanels = new DrawSidePanels();
	
	//protected boolean karteDrawInfo=true; //zem kartes var izvadît datus par izmçriem
	
	public static int karteNobideX=15, karteNobideY=nosaukumsY*2; //laukuma nobîde ekrânâ
	
	
	public static int karteAtstatumsX=20, karteAtstatumsY=60;
	public int kartePlatums;
	
	//protected int kartesPlatums, kartesAugstums;
	protected double merogs;
	
	public boolean drawCrosshair=true;
	public static int crosshairSize=30;
	
	// --------------------
	//zemâk par loot zîmçðanu
	
	public boolean lietasDrawInfo=false; //papildinformâcijas zîmçðana
	
	// --------------------
	//zemâk par cilvçku zîmçðanu
	
	public boolean cilvekiDrawInfo=false; //papildinformâcijas un redzesloku zîmçðana

	public boolean cilvekiDrawR=false;
	
	
	// --------------------
	//zemâk par input testa paneli
	
	public boolean diagnosticsPanelDraw=true;
	public static Color diagnosticsPanelColor=nosaukumaKrasa;
	public static int diagnosticPanelPlatums=150, diagnosticPanelAugstums=200;
	protected int diagnosticsPanelWx=150,
			diagnosticsPanelX=ekranaPlatums-diagnosticsPanelWx-5, diagnosticsPanelY=30;
	
	
	protected void initialize(PlayerThread thread) {
		
		boolean MINI=true; //mini versija
		boolean TV=false; //ir vai nav pieslçgts televizoram
		
		if (MINI) {
			fullscreen=false; //mini versija
			ekranaPlatums=1000;
			ekranaAugstums=700;
		} else if (TV) {
			fullscreen=false; //televizoram
			ekranaPlatums=1700;
			ekranaAugstums=1000;
		} else {
			fullscreen=true; //portatîvajam - default
			ekranaPlatums=1350;
			ekranaAugstums=700;
		}
		
		int kartePlatumsMax=ekranaPlatums-karteNobideX-karteAtstatumsX,
				karteAugstumsMax=ekranaAugstums-karteNobideY-karteAtstatumsY;
		
		kartePlatums=Math.min(kartePlatumsMax, karteAugstumsMax);
		
		
		// --------------------
		//par pogâm
		
		buttonList = new ArrayList<Button>();
		
		int pogasX0=karteNobideX*2+kartePlatums, pogasY0=karteNobideY,
				pogasPlatums=100, pogasAugstums=30, pogasSpraugaY=5, w=0;
		
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Pauze",0); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Cilveku info",10); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Redzesloki",3); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Loot info",5); w++;
		addButton(pogasX0,pogasY0+(pogasAugstums+pogasSpraugaY)*w,pogasPlatums,pogasAugstums,"Reset",0); w++;
		
		
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
	
	protected void playerInitialize(PlayerThread thread, boolean primary) { //rada jauna spçlçtâja referenci
		playerDead=false;
		
		if (!primary) {
			playerName = Cilveks.getPlayer(cilvekuPilnaisList.get(0)).vards;
		}

		windowTitle=windowTitleDefault+" ("+playerName+")"; //nomaina loga nosaukumu
	}

	private void getPlayerTotalList(){
		cilvekuPilnaisList = new ArrayList<Location>();

		for(int[] chunkXY = {0, 0}; chunkXY[0]< Main.laukums.size(); chunkXY[0]++) {
			for( ; chunkXY[1]<Main.laukums.get(chunkXY[0]).size(); chunkXY[1]++) {



				for (int i=0; i<Main.laukums.get(chunkXY[0]).get(chunkXY[1]).cilvekiList.size(); i++){

					Location cilveks = new Location();
					cilveks.chunkXY=chunkXY;
					cilveks.i=i;
					cilvekuPilnaisList.add(cilveks);
				}

			}
		}
	}

	void update(){

	}

	int findPlayer(PlayerThread thread){

//		i=-1;
//
//		jânomaina spçlçtâja parametri fokusam
//
//
//		int[] chunkXY
//
//
//		return i;
		return 0; //pagaidâm

	}
	
	protected void playerDead() {
		playerDead=true;
	}
	
	protected void updateGraphicsValues() {
		
	}
	
}
